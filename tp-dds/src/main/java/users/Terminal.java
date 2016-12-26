package users;

import dao.model.Action;
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
public class Terminal extends User{

    @Transient
    private Coordinate coordinates;
    @Transient
    private PoiService poiService;



    public Terminal(String nombre,String contrasenia, Coordinate coordinate, List<Action> actions) {
        super(nombre,contrasenia,actions);
        this.coordinates = coordinate;
        this.poiService = PoiService.getInstance();
    }

    public Terminal() {
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
