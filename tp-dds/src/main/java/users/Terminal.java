package users;

import domain.Coordinate;
import internalService.PoiService;
import org.apache.http.client.ClientProtocolException;
import poi.Bank;
import poi.Poi;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Entity
@Table(name = "Terminal")
public class Terminal{
    @Id
    @GeneratedValue
    private long id;

    @Column(name="NOMBRE",unique = true)
    private String nombre;

    @Column(name="CONTRASENIA")
    private String contrasenia;



    @Transient
    private Coordinate coordinates;
    @Transient
    private PoiService poiService;
    @Transient
    private List<List<String>> actions;

    public Terminal(String nombre,String contrasenia, Coordinate coordinate, List<List<String>> actions) {
        this.contrasenia=contrasenia;
        this.nombre = nombre;
        this.coordinates = coordinate;
        this.actions = actions;
        this.poiService = PoiService.getInstance();
    }

    public Terminal() {
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
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
