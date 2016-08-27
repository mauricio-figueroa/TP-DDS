package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.Coordinate;
import externalServices.GoogleDistanceService.GoogleDistanceService;
import internalService.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Poi implements PoiInterface {

	protected long id;
	protected boolean actived;
	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	protected String name;
	protected Address address;
	protected Coordinate coordinate;
	@Autowired
	protected GoogleDistanceService googleService;
	@Autowired
	private AvailabilityService availabilityServices;
	private List<String> data=new ArrayList<String>();

	public Poi(String name, Address address, Coordinate coordinate) {
		this.name = name;
		this.address = address;
		this.coordinate = coordinate;
		this.actived=true;
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
		return this.availabilityServices;
	}

	public void setAvailabilityService(AvailabilityService availabilityService) {
		this.availabilityServices = availabilityService;
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
