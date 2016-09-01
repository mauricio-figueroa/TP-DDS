package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.ClosedSchedule;
import domain.Coordinate;

public class BusStation extends Poi {
	
	private String numberBusStation ;


	

	public BusStation(String name, Address address, Coordinate coordinate, String aNumberBusStation) {
		super(name, address, coordinate);
		numberBusStation = aNumberBusStation;
		
		this.getData().add(name);
		this.getData().add( numberBusStation);
		this.getData().add(address.getMainStreet());
		this.getData().add("bus");
		}

	public String getNumberBusStation() {
		return numberBusStation;
	}

	public void setNumberBusStation(String numberBusStation) {
		this.numberBusStation = numberBusStation;
	}

	@Override
	public boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException {
		double distance = this.getGoogleService().getDistance(coordinate, this.getCoordinate());
		return distance < 100;
	}

	@Override
	public String getType() {
		return "BusStation";
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	@Override
	public void addClosedSchedule(ClosedSchedule closedSchedule) {

	}

}
