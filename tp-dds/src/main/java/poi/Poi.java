package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.Coordinate;
import externalServices.GoogleDistanceService.GoogleDistanceService;
import internalService.AvailabilityService;

public abstract class Poi implements PoiInterface {

	protected String name;
	protected Address address;
	protected Coordinate coordinate;
	protected GoogleDistanceService googleService = GoogleDistanceService.getInstance();
	protected AvailabilityService availabilityService = AvailabilityService.getInstance();
	private List<String> data=new ArrayList<String>();

	public Poi(String name, Address address, Coordinate coordinate) {
		this.name = name;
		this.address = address;
		this.coordinate = coordinate;
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

}
