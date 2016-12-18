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
import poi.Bank;
import poi.Poi;

public class Terminal {

    private long id;
    private String nombre;
    private Coordinate coordinates;
    private PoiService poiService;
    private List<List<String>> actions;

    public Terminal(String nombre, Coordinate coordinate, List<List<String>> actions) {
        this.nombre = nombre;
        this.coordinates = coordinate;
        this.actions = actions;
        this.poiService = PoiService.getInstance();
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
        poiService = PoiService.getInstance();
    }

    public boolean isAvailable(Poi poi) {
        return poiService.isAvailable(poi);
    }

    public List<Poi> searchPoi(List<String> textosBuscados) throws AddressException, MessagingException, InterruptedException {
        return this.poiService.searchPois(textosBuscados, this.getNombre());
    }

    public List<Poi> searchPoi(String textosBuscados) throws AddressException, MessagingException, InterruptedException {
        return this.poiService.searchPois(textosBuscados, this.getNombre());
    }


    public boolean isNearBy(Poi poi) throws ClientProtocolException, IOException {
        return poiService.isNearby(poi, coordinates);
    }

}
