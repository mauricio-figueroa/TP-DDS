package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import domain.Address;
import domain.Coordinate;
import domain.RangeOfAtention;
import domain.Schedule;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Bank extends Poi {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "range_of_atention")
	private RangeOfAtention rangeOfAtention;

	public List<String> services;



	public Bank(String name, Address address, Coordinate coordinate,String services){
		super(name, address, coordinate);
		this.services=new ArrayList<>();
		icon="http://www.freeiconspng.com/uploads/full-size-jpg-preview-bank-building-icon-6.jpg";


		String[] parts = services.split(",");
		for (String currentString:parts) {
			this.services.add(currentString);
		}
		
		List<Schedule> schedules1=new ArrayList<Schedule>();
		schedules1.add(new Schedule("10:00", "15:00"));
		List<Integer> days1=new ArrayList<Integer>();
		days1.add(0);
		days1.add(1);
		days1.add(2);
		days1.add(3);
		days1.add(4);
		days1.add(5);
		days1.add(6);
		this.type=this.getClass().getSimpleName();
		
		this.rangeOfAtention=new RangeOfAtention(schedules1, days1);
		this.getData().add(this.getName());
		this.getData().add(this.getAddress().getMainStreet());
		this.getData().add("bank");
		}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public RangeOfAtention getRangeOfAtention() {
		return rangeOfAtention;
	}

	public void setRangeOfAtention(RangeOfAtention rangeOfAtention) {
		this.rangeOfAtention = rangeOfAtention;
	}

	public boolean isEnable() {
		return true; // TO DO
	}

	public String getType() {
		return "Bank";
	}

	@Override
	public int getNumber() {
		return 0;
	}

	public boolean isNearBy(Coordinate coordinate) throws ClientProtocolException, IOException {
		double distance = this.getGoogleService().getDistance(coordinate, this.getCoordinate());
		return distance < 100;
	}

	@Override
	public boolean isAvailable() {
		return this.getAvailabilityService().isAvailability( this.getRangeOfAtention());
	}
	
	


}
