package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import domain.Coordinate;
import externalServices.GoogleDistanceService.GoogleDistanceService;
import internalService.AvailabilityService;

public interface CategoryShop {

	
	public boolean isNearby(Coordinate coordinatePoiService, Coordinate coordinate,
			GoogleDistanceService googleService) throws ClientProtocolException, IOException;

	public boolean isAvailable(AvailabilityService availabilityService);
	public String getType();

}
