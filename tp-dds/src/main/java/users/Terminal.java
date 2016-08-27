package users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.client.ClientProtocolException;

import domain.Coordinate;
import internalService.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poi.Bank;
import poi.Poi;

@Component
public class Terminal {

    @Autowired
    private PoiService poiService;
    private long id;
    private String nombre;
    private Coordinate coordinates;
    private List<List<String>> actions;

    public Terminal(){

    }

    public Terminal(String name, Coordinate coordinate, List<List<String>> listOfActions) {
        this.nombre = name;
        this.coordinates = coordinate;
        this.actions=listOfActions;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public List<List<String>> getActions() {
        return actions;
    }


    public void setActions(List<List<String>> actions) {
        this.actions = actions;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Coordinate getCoordinates() {
        return coordinates;
    }


    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }


    public List<Bank> searchBank(String bank, String service) {
        return poiService.searchBank(bank, service);

    }

    public PoiService getPoiService() {
        return poiService;
    }


    public void setPoiService(PoiService poiService) {
        this.poiService = poiService;
    }


    public Terminal(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isAvailable(Poi poi) {
        return poiService.isAvailable(poi);
    }

    public List<Poi> searchPoi(String textoBuscado) throws AddressException, MessagingException, InterruptedException {
        return this.poiService.searchPois(textoBuscado, this.getNombre());
    }


    public boolean isNearBy(Poi poi) throws ClientProtocolException, IOException {
        return poiService.isNearby(poi, coordinates);
    }

}
