package controller.controllers;

import controller.response.PoiDTO;
import dao.EntityManagerProvider;
import dao.MongoDBManager;
import dao.SearchDao;
import domain.Search;
import internalService.PoiService;
import internalService.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import poi.*;
import users.Terminal;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

@Controller
public class TerminalController {

    //TODO
    private PoiService poiService;

    public TerminalController() {
        poiService = PoiService.getInstance();
    }

    @RequestMapping(value = ("/search-poi-from"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchPoiFrom(
            @RequestParam(value = "searchName", required = true) List<String> searchList,
            @RequestParam(value = "terminalName", required = true) String terminalName) throws AddressException, MessagingException, InterruptedException {

        List<PoiDTO> poisDTO = new ArrayList<PoiDTO>();
        Terminal terminal = poiService.searchTerminal(terminalName);
        if (terminal == null) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.OK);


        }
        List<Poi> pois = terminal.searchPoi(searchList);


        for (Poi currentPoi : pois) {

            switch (currentPoi.getType()) {
                case "Bank":
                    Bank bank = (Bank) currentPoi;

                    poisDTO.add(new PoiDTO(bank.getIcon(), bank.getType(), bank.getAddress().getMainStreet(), bank.getAddress().getMainStreet(), bank.getServices(),bank.getName()));
                    break;

                case "BusStation":
                    BusStation busStation = (BusStation) currentPoi;
                    poisDTO.add(new PoiDTO(busStation.getName(),busStation.getIcon(), busStation.getType(), busStation.getNumberBusStation()));
                    break;
                case "CGP":
                    CGP cgp = (CGP) currentPoi;
                    HashMap<String, List<Integer>> cgpService = new HashMap<String, List<Integer>>();
                    cgp.getServices().forEach(service -> cgpService.put(service.getServiceName(), service.getRangeOfAtention().getDaysOfAttention()));
                    poisDTO.add(new PoiDTO(cgp.getIcon(), cgp.getType(), cgp.getAddress().getMainStreet(), cgp.getAddress().getMainStreet(), cgpService));
                    break;

                case "ComercialShop":
                    ComercialShop comercial = (ComercialShop) currentPoi;
                    poisDTO.add(new PoiDTO(comercial.getIcon(), comercial.getType(), comercial.getAddress().getMainStreet(), comercial.getAddress().getMainStreet(), comercial.getCategory().getType()));

                    break;
            }

        }
        Search search= new  Search(poisDTO, new Date(), terminalName, StringUtils.join(searchList,","), poisDTO.size());

        EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();

        SearchDao searchDao=new SearchDao(entityManager);
        searchDao.saveOrUpdate(search);
        return new ResponseEntity<List<PoiDTO>>(poisDTO, HttpStatus.OK);
    }


    @RequestMapping(value = ("/is-near-by"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity isNearBy(
            @RequestParam(value = "searchName", required = true) String searchName,
            @RequestParam(value = "terminalName", required = true) String terminalName) throws AddressException, MessagingException, InterruptedException, ClientProtocolException, IOException {

        Terminal terminal = poiService.searchTerminal(terminalName);
        List<Poi> pois = terminal.searchPoi(searchName);
        Map<String, Boolean> isNear = new HashMap<String, Boolean>();

        for (Poi poi : pois) {
            isNear.put(poi.getName(), terminal.isNearBy(poi));
        }

        return new ResponseEntity(isNear, HttpStatus.OK);
    }

    private UsuarioService usuarioService = new UsuarioService();


    @RequestMapping(value = ("/crearUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addUser(@RequestParam(value = "user", required = true) String user,
                                  @RequestParam(value = "pw", required = true) String pw) {

        this.usuarioService.addUser(user, pw);

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

    @RequestMapping(value = ("/validarUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity validarUsuario(@RequestParam(value = "user", required = true) String user,
                                         @RequestParam(value = "pw", required = true) String pw) {


        return new ResponseEntity<Integer>(this.usuarioService.existeUsuario2(user,pw),HttpStatus.OK);

    }

}
