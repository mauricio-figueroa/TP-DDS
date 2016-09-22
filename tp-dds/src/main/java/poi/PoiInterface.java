package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import domain.Coordinate;


public interface PoiInterface {
	
	public boolean isAvailable();
	boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException;
	public String getType();
	public int getNumber();
}
