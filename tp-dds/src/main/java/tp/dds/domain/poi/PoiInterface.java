package tp.dds.domain.poi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import tp.dds.domain.common.ClosedSchedule;
import tp.dds.domain.common.Coordinate;


public interface PoiInterface {
	
	public boolean isAvailable();
	boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException;
	public String getType();
	public void addClosedSchedule(ClosedSchedule closedSchedule);
	public void addClosedScheduleToService(ClosedSchedule closedSchedule, String serviceName);	
}
