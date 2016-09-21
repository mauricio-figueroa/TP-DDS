package tp.dds.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tp.dds.domain.common.Coordinate;
import tp.dds.domain.poi.Poi;
import tp.dds.domain.users.Terminal;
import tp.dds.services.internalService.PoiService;

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
            @RequestParam(value = "searchName", required = true) String searchName,
            @RequestParam(value = "terminalName", required = true) String terminalName) throws AddressException, MessagingException, InterruptedException {


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Terminal terminal = poiService.searchTerminal(terminalName);
        List<Poi> pois = terminal.searchPoi(searchName);
        return new ResponseEntity(pois,responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = ("/is-near-by"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity isNearBy(
            @RequestParam(value = "searchName", required = true) String searchName,
            @RequestParam(value = "terminalName", required = true) String terminalName) throws AddressException, MessagingException, InterruptedException, ClientProtocolException, IOException {

        Terminal terminal = poiService.searchTerminal(terminalName);
        List<Poi> pois = terminal.searchPoi(searchName);
        Map<String, Boolean> isNear = new HashMap<String, Boolean>();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        for (Poi poi : pois) {
            isNear.put(poi.getName(), terminal.isNearBy(poi));
        }
        return new ResponseEntity(isNear,responseHeaders, HttpStatus.OK);
    }
}
