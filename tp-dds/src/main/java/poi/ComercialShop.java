package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.ClosedSchedule;
import domain.Coordinate;
import domain.RangeOfAtention;

public class ComercialShop extends Poi {

	private CategoryShop category;
	private RangeOfAtention rangeOfAtention;

	public ComercialShop(String name, Address address, Coordinate coordinate, CategoryShop category) {
		super(name, address, coordinate);
		this.category = category;
		this.getData().add(this.getName());
		this.getData().add(this.getAddress().getMainStreet());
	}
	
	public ComercialShop(String name, Address address, Coordinate coordinate, CategoryShop category,RangeOfAtention rangeOfAtention) {
		super(name, address, coordinate);
		this.category = category;
		this.getData().add(this.getName());
		this.getData().add(this.getAddress().getMainStreet());
		this.rangeOfAtention=rangeOfAtention;
	}

	public String getType() {
		return "ComercialShop";
	}


	@Override
	public boolean isNearby(Coordinate coordinatePoiService) throws ClientProtocolException, IOException {
		return category.isNearby(coordinatePoiService, this.getCoordinate(), this.getGoogleService());
	}

	@Override
	public boolean isAvailable() {
		return category.isAvailable(this.getAvailabilityService());
	}

	@Override
	public void addClosedSchedule(ClosedSchedule closedSchedule) {
		this.rangeOfAtention.addClosedSchedule(closedSchedule);
		
	}


}
