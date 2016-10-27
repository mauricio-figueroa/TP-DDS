package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.Coordinate;
import externalServices.GoogleDistanceService.GoogleDistanceService;
import internalService.AvailabilityService;

import javax.persistence.*;

@Entity
@Table(name = "poi")
public abstract class Poi implements PoiInterface {

    @Id
    @GeneratedValue
    protected long id;

    @Column(name = "actived")
    protected boolean actived;

    @Column(name = "type")
    protected String type;

    @Transient
    protected String icon;

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    @Column(name = "name")
    protected String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address")
    protected Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cordinate")
    protected Coordinate coordinate;

    @Transient
    protected GoogleDistanceService googleService = GoogleDistanceService.getInstance();
    @Transient
    protected AvailabilityService availabilityService = AvailabilityService.getInstance();
    @Transient
    private List<String> data = new ArrayList<String>();

    public Poi(String name, Address address, Coordinate coordinate) {
        this.name = name;
        this.address = address;
        this.coordinate = coordinate;
        this.actived = true;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isActived() {
        return actived;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Poi [name=" + name + ", coordinate=" + coordinate + "]";
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public GoogleDistanceService getGoogleService() {
        return googleService;
    }

    public void setGoogleService(GoogleDistanceService googleService) {
        this.googleService = googleService;
    }

    public AvailabilityService getAvailabilityService() {
        return availabilityService;
    }

    public void setAvailabilityService(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Poi() {
        super();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate cordinate) {
        this.coordinate = cordinate;
    }

    public boolean isNearby(Coordinate cordinate) throws ClientProtocolException, IOException {
        return this.googleService.getDistance(this.coordinate, cordinate) < 500;
    }

    public boolean isAvailable() {
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
