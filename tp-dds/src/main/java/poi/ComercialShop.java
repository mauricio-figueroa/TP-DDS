package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.Coordinate;

import javax.persistence.Column;

public class ComercialShop extends Poi {

	private CategoryShop category;

	public CategoryShop getCategory() {
		return category;
	}

	public void setCategory(CategoryShop category) {
		this.category = category;
	}

	public ComercialShop(String name, Address address, Coordinate coordinate, CategoryShop category) {
		super(name, address, coordinate);
		this.category = category;
		this.getData().add(this.getName());
		this.type=this.getClass().getSimpleName();
		this.getData().add(this.getAddress().getMainStreet());
		icon="http://images.clipartpanda.com/shopping-bag-clipart-yToyAeqTE.jpeg";
	}

	public String getType() {
		return "ComercialShop";
	}

	@Override
	public int getNumber() {
		return 0;
	}


	@Override
	public boolean isNearby(Coordinate coordinatePoiService) throws ClientProtocolException, IOException {
		return category.isNearby(coordinatePoiService, this.getCoordinate(), this.getGoogleService());
	}

	@Override
	public boolean isAvailable() {
		return category.isAvailable(this.getAvailabilityService());
	}

}
