package tp.dds.domain.poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import tp.dds.domain.common.Coordinate;
import tp.dds.domain.common.RangeOfAtention;
import tp.dds.domain.common.Schedule;
import tp.dds.services.externalServices.GoogleDistanceService.GoogleDistanceService;
import tp.dds.services.internalService.AvailabilityService;

public class Library implements CategoryShop {
	private static Library instance=null;
	private double distanceMaxInMetters;
	private RangeOfAtention rangeOfAtention;
	
	  protected Library() {
	   }

	  public static Library getInstance(double distanceMaxInMetters) {
	      if(instance == null) {
	         instance = new Library();
	         instance.setDistanceMaxInMetters(distanceMaxInMetters);
	         setRangeOfAtention();
	         
	      }
	      return instance;
	   }
	  
	  public String getType(){
		  return "Library";
	  }

	  

	public double getDistanceMaxInMetters() {
		return distanceMaxInMetters;
	}

	public void setDistanceMaxInMetters(double distanceMaxInMetters) {
		this.distanceMaxInMetters = distanceMaxInMetters;
	}

	public RangeOfAtention getRangeOfAtention() {
		return rangeOfAtention;
	}

	public void setRangeOfAtention(RangeOfAtention rangeOfAtention) {
		this.rangeOfAtention = rangeOfAtention;
	}

	@Override
	public boolean isNearby(Coordinate coordinatePoiService, Coordinate coordinatePoi,
			GoogleDistanceService googleService) throws ClientProtocolException, IOException {
		double distance=googleService.getDistance(coordinatePoiService, coordinatePoi);
		return distance<this.distanceMaxInMetters;
	}

	@Override
	public boolean isAvailable(AvailabilityService availabilityService) {
		return availabilityService.isAvailability(this.rangeOfAtention);
	}
	
	private static void setRangeOfAtention(){
		  List<Schedule> schedules= new ArrayList<Schedule>();
	         Schedule schedule1= new Schedule("09:00","12:00");
	         Schedule schedule2= new Schedule("16:00","22:00");
	         schedules.add(schedule1);
	         schedules.add(schedule2);
	         List<Integer> days= new ArrayList<Integer>();
	         days.add(1);
	         days.add(2);
	         days.add(3);
	         days.add(4);
	         days.add(5);
	         instance.setRangeOfAtention(new RangeOfAtention(schedules,days));
		
	}
}
