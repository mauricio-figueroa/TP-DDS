package controller.controllers;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import controller.response.BusquedaDTO;
import dao.AdminDAO;
import dao.EntityManagerProvider;
import dao.TerminalDao;
import dao.model.Action;
import domain.*;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import internalService.PoiService;
import poi.*;
import users.Admin;
import users.Terminal;

import javax.persistence.EntityManager;

@Controller
public class AdminController {

    private Admin admin;

    public AdminController() {
        admin = new Admin();
    }


    @RequestMapping(value = ("/register-admin"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity registerAdmin(@RequestParam(value="user", required = true) String user,
                                        @RequestParam(value="pass",required = true) String pass,
                                        @RequestParam(value="mail",required = true) String mail){
        EntityManager entityManager= EntityManagerProvider.getInstance().getEntityManager();
        AdminDAO adminDAO = new AdminDAO(entityManager);
        Admin adminToReg= new Admin(null,user,pass,"asdsa","email");
        Admin admin= adminDAO.saveOrUpdate(adminToReg);
        return new ResponseEntity(admin.getId(),HttpStatus.OK);
    }

    @RequestMapping(value = ("/terminal-add"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addTerminal(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "lat", required = true) double lat,
            @RequestParam(value = "lon", required = true) double lon,
            @RequestParam(value = "action", required = false) List<String> actions) {

        if (actions.stream().allMatch(action -> EnumUtils.isValidEnum(EnumActions.class, action))) {
            List<Action> listOfActions = new ArrayList<>();
            Action action= new Action(com.sun.deploy.util.StringUtils.join(actions,","));
            listOfActions.add(action);
            Terminal terminal=new Terminal(name,password, new Coordinate(lat, lon), listOfActions);
            boolean state = admin.addTerminal(terminal);
            EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
            TerminalDao terminalDao = new TerminalDao(entityManager);
            terminalDao.saveOrUpdate(terminal);
            return new ResponseEntity(state, HttpStatus.OK);
        } else {
            String error = "ACTIONS MUST BE " + EnumActions.values();
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = ("/show-terminal"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity showTerminals() {

        List<String> terminals = new ArrayList<String>();
        for (Terminal currentTerminal : admin.getPoiService().getTerminales()) {
            terminals.add(currentTerminal.getNombre());
        }
        return new ResponseEntity(terminals, HttpStatus.OK);
    }

    @RequestMapping(value = ("/terminal-remove"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity removeTerminal(
            @RequestParam(value = "name", required = true) String name) {

        boolean state = admin.removeTerminal(name);
        return new ResponseEntity(state, HttpStatus.OK);
    }


    @RequestMapping(value = ("/reportByDate"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity reportByDate() {

        Map<String, Integer> reportsByDate = PoiService.getReportService().getReportesTotalesPorFecha();
        return new ResponseEntity(reportsByDate, HttpStatus.OK);
    }


    @RequestMapping(value = ("/reportByTerminal"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity reportByTerminal(
            @RequestParam(value = "name", required = true) String name) {

        Map<String, Integer> reportsByDate = PoiService.getReportService().getParcialesPorTerminal(name);
        return new ResponseEntity(reportsByDate, HttpStatus.OK);
    }

    @RequestMapping(value = ("/all-reports"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity allReports() {
        Map<String, Integer> reportsByDate = PoiService.getReportService().getReportesTotalesTodasLasTerminales();
        return new ResponseEntity(reportsByDate, HttpStatus.OK);
    }

    @RequestMapping(value = ("/reportePorNombreTerminal"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BusquedaDTO>> reportePorNombreTerminalPorFecha(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "desde", required = false) String desde,
            @RequestParam(value = "hasta", required = false) String hasta) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (name == null) {
            return this.funcionRecontraChotaComoTodoLaApp(desde, hasta);
        }


        if (desde == null & hasta == null) {
            ReportePorTerminal reporte = PoiService.getReportService().buscarReporteTerminal(name);

            List<BusquedaDTO> busquedas = new ArrayList<>();

            for (LineaReporte currentRow : reporte.getBusquedas()) {
                BusquedaDTO busquedaDto = new BusquedaDTO(name, currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());
                busquedas.add(busquedaDto);
            }

            return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);
        }

        if (desde != null & hasta == null) {
            ReportePorTerminal reporte = PoiService.getReportService().buscarReporteTerminal(name);

            List<BusquedaDTO> busquedas = new ArrayList<>();

            for (LineaReporte currentRow : reporte.getBusquedas()) {
                BusquedaDTO busquedaDto = new BusquedaDTO(name, currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());


                try {
                    Date date = formatter.parse(desde);
                    boolean b = currentRow.getFechaBusqueda().after(date);
                    if (b) {
                        busquedas.add(busquedaDto);
                    }
                } catch (Exception e) {
                    System.out.println("me chupa un huevo");
                }
            }


            return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);


        }


        if (hasta != null && desde == null) {
            ReportePorTerminal reporte = PoiService.getReportService().buscarReporteTerminal(name);

            List<BusquedaDTO> busquedas = new ArrayList<>();

            for (LineaReporte currentRow : reporte.getBusquedas()) {
                BusquedaDTO busquedaDto = new BusquedaDTO(name, currentRow.getFechaBusqueda().toString(),StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());

                try {
                    Date date = formatter.parse(hasta);
                    boolean b = currentRow.getFechaBusqueda().before(date);
                    if (b) {
                        busquedas.add(busquedaDto);
                    }
                } catch (Exception e) {
                    System.out.println("me chupa un huevo");
                }
            }

            return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);

        }

        if (hasta != null && desde != null) {


            ReportePorTerminal reporte = PoiService.getReportService().buscarReporteTerminal(name);

            List<BusquedaDTO> busquedas = new ArrayList<>();

            for (LineaReporte currentRow : reporte.getBusquedas()) {
                BusquedaDTO busquedaDto = new BusquedaDTO(name, currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());

                try {
                    Date dateDesde = formatter.parse(desde);
                    Date dateHasta = formatter.parse(hasta);
                    boolean despues = currentRow.getFechaBusqueda().after(dateDesde);
                    boolean antes = currentRow.getFechaBusqueda().before(dateHasta);

                    if (despues && antes) {
                        busquedas.add(busquedaDto);
                    }
                } catch (Exception e) {
                    System.out.println("me chupa un huevo");
                }
            }

            return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);


        }

        List<BusquedaDTO> busquedas = new ArrayList<>();
        return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);


    }

    private ResponseEntity<List<BusquedaDTO>> funcionRecontraChotaComoTodoLaApp(String desde, String hasta) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        List<BusquedaDTO> busquedas = new ArrayList<>();
        List<ReportePorTerminal> reportes = PoiService.getReportService().getReportes();

        for (ReportePorTerminal currentReport : reportes) {


            if (desde == null & hasta == null) {


                for (LineaReporte currentRow : currentReport.getBusquedas()) {
                    BusquedaDTO busquedaDto = new BusquedaDTO(currentReport.getNombreTerminal(), currentRow.getFechaBusqueda().toString(),StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());
                    busquedas.add(busquedaDto);
                }

            }


            if (desde != null & hasta == null) {


                for (LineaReporte currentRow : currentReport.getBusquedas()) {
                    BusquedaDTO busquedaDto = new BusquedaDTO(currentReport.getNombreTerminal(), currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());


                    try {
                        Date date = formatter.parse(desde);
                        boolean b = currentRow.getFechaBusqueda().after(date);
                        if (b) {
                            busquedas.add(busquedaDto);
                        }
                    } catch (Exception e) {
                        System.out.println("me chupa un huevo");
                    }
                }




            }


            if (hasta != null && desde == null) {


                for (LineaReporte currentRow : currentReport.getBusquedas()) {
                    BusquedaDTO busquedaDto = new BusquedaDTO(currentReport.getNombreTerminal(), currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());

                    try {
                        Date date = formatter.parse(hasta);
                        boolean b = currentRow.getFechaBusqueda().before(date);
                        if (b) {
                            busquedas.add(busquedaDto);
                        }
                    } catch (Exception e) {
                        System.out.println("me chupa un huevo");
                    }
                }


            }

            if (hasta != null && desde != null) {


                for (LineaReporte currentRow : currentReport.getBusquedas()) {
                    BusquedaDTO busquedaDto = new BusquedaDTO(currentReport.getNombreTerminal(), currentRow.getFechaBusqueda().toString(), StringUtils.join(currentRow.getPalabraBuscada(),","), currentRow.getCantPoisBusqueda());

                    try {
                        Date dateDesde = formatter.parse(desde);
                        Date dateHasta = formatter.parse(hasta);
                        boolean despues = currentRow.getFechaBusqueda().after(dateDesde);
                        boolean antes = currentRow.getFechaBusqueda().before(dateHasta);

                        if (despues && antes) {
                            busquedas.add(busquedaDto);
                        }
                    } catch (Exception e) {
                        System.out.println("me chupa un huevo");
                    }
                }



            }

        }

        return new ResponseEntity<List<BusquedaDTO>>(busquedas, HttpStatus.OK);
    }


    @RequestMapping(value = ("/poi-remove"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity removePoi(
            @RequestParam(value = "name", required = true) String name) {

        boolean state = admin.removePoi(name);
        return new ResponseEntity(state, HttpStatus.OK);
    }

    @RequestMapping(value = ("/poi-modify"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity modifyPoi(
            @RequestParam(value = "name", required = true) String name) {
        Poi poi = null; // TODO Agregar muchos parametros y filtrar tipo por
        // type
        boolean state = admin.modifyPoi(poi, name);
        return new ResponseEntity(state, HttpStatus.OK);
    }


    @RequestMapping(value = ("/poi-addBank"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addPoiBank(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "mainStreet", required = true) String mainStreet,
            @RequestParam(value = "lat", required = true) double lat,
            @RequestParam(value = "lon", required = true) double lon,
            @RequestParam(value = "services", required = false) String services) {

        Poi poi = new Bank(name, new Address(mainStreet), new Coordinate(lat, lon), services);

        admin.addPoi(poi);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = ("/poi-addBusStation"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addPoiBusStation(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "mainStreet", required = true) String mainStreet,
            @RequestParam(value = "lat", required = true) double lat,
            @RequestParam(value = "lon", required = true) double lon,
            @RequestParam(value = "busNumber", required = true) Integer busNumber) {


        Poi poi = new BusStation(name, new Address(mainStreet), new Coordinate(lat, lon), busNumber);
        admin.addPoi(poi);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = ("/poi-addCGP"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addPoiCGP(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "mainStreet", required = true) String mainStreet,
            @RequestParam(value = "lat", required = true) double lat,
            @RequestParam(value = "lon", required = true) double lon,
            @RequestParam(value = "communeRadius", required = true) double communeRadius) {

        Poi poi = new CGP(name, new Address(mainStreet), new Coordinate(lat, lon), communeRadius, new ArrayList<CGPService>());
        admin.addPoi(poi);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = ("/poi-addCGPServiceInTo"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addPoiCGPService(
            @RequestParam(value = "nameOfCGP", required = true) String nameOfCGP,
            @RequestParam(value = "serviceName", required = true) String serviceName,
            @RequestParam(value = "daysOfAttention", required = true) List<Integer> daysOfAttention) {


        for (Integer index : daysOfAttention) {
            System.out.println(index);
        }
        CGPService cgpService = new CGPService(serviceName, new RangeOfAtention(null, daysOfAttention));
        boolean status = admin.addCGPServiceToCGP(nameOfCGP, cgpService);
        return new ResponseEntity(status, HttpStatus.OK);

    }

    public ResponseEntity addScheduleToCGPService(
            @RequestParam(value = "nameOfCGP", required = true) String nameOfCGP,
            @RequestParam(value = "serviceName", required = true) String serviceName,
            @RequestParam(value = "hourMax", required = true) String hourMax,
            @RequestParam(value = "hourMin", required = true) String hourMin) {

        boolean status = admin.addScheduleToCGPService(nameOfCGP, serviceName, hourMax, hourMin);
        return new ResponseEntity(status, HttpStatus.OK);

    }


    @RequestMapping(value = ("/poi-addComercial"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addComercial(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "mainStreet", required = true) String mainStreet,
            @RequestParam(value = "lat", required = true) double lat,
            @RequestParam(value = "lon", required = true) double lon,
            @RequestParam(value = "rubro", required = true) String rubro,
            @RequestParam(value = "maxDistance", required = true) double maxDistance) {
        CategoryShop categoryShop = null;
        switch (rubro) {
            case "Library":
                categoryShop = Library.getInstance(maxDistance);
                break;
            case "Newspaper":
                categoryShop = Newspaper.getInstance(maxDistance);
                break;
        }

        Poi poi = new ComercialShop(name, new Address(mainStreet), new Coordinate(lat, lon), categoryShop);
        admin.addPoi(poi);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = ("/get-users"), method = RequestMethod.GET)
    @ResponseBody
    public List<Terminal> getUsers(){
        EntityManager entityManager= EntityManagerProvider.getInstance().getEntityManager();
        List<String> users= new ArrayList<>();
        TerminalDao terminalDao= new TerminalDao(entityManager);
        List<Terminal> terminales= terminalDao.getAll();
        return terminales;
    }

    @RequestMapping(value = ("/get-actions"), method = RequestMethod.GET)
    @ResponseBody
    public List<EnumActions> getActions(){
        return Arrays.asList(EnumActions.values());
    }



    @RequestMapping(value = ("/addActionToUser"), method = RequestMethod.GET)
    @ResponseBody
    public void addActionToUser(
            @RequestParam(value = "adminName", required = true) String adminName,
            @RequestParam(value = "user", required = true) String user,
            @RequestParam(value = "actions", required = true) String actions ){

        String[] parts = actions.split(",");

        List<String> actions2 =new ArrayList<>(Arrays.asList(parts));


        Admin admin= new Admin(adminName);
        admin.addActionsToUser(user,"Terminal",actions2);
    }



}
