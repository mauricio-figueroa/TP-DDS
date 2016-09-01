package poi;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

import domain.ClosedSchedule;
import domain.Coordinate;


public interface PoiInterface {
	
	public boolean isAvailable();
	boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException;
	public String getType();
	public void addClosedSchedule(ClosedSchedule closedSchedule);
	public void addClosedScheduleToService(ClosedSchedule closedSchedule, String serviceName);	
}
