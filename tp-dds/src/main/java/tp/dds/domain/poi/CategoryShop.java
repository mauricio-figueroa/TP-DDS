package tp.dds.domain.poi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import tp.dds.domain.common.Coordinate;
import tp.dds.services.externalServices.GoogleDistanceService.GoogleDistanceService;
import tp.dds.services.internalService.AvailabilityService;

public interface CategoryShop {

	
	public boolean isNearby(Coordinate coordinatePoiService, Coordinate coordinate,
			GoogleDistanceService googleService) throws ClientProtocolException, IOException;

	public boolean isAvailable(AvailabilityService availabilityService);
	public String getType();

}
