package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Address;
import domain.Coordinate;

public class CGP extends Poi {

	private double communeRadius;
	private ArrayList<CGPService> services;
	private static final Logger LOGGER= LoggerFactory.getLogger(CGP.class);

	public CGP(String name, Address address, Coordinate coordinate, double communeRadius,
			ArrayList<CGPService> services) {
		super(name, address, coordinate);
		this.type=this.getClass().getSimpleName();
		this.communeRadius = communeRadius;
		this.services = services;
		this.getData().add(this.getName());
		for (CGPService cgpService : services) {
			this.getData().add(cgpService.getServiceName());
		}
		this.getData().add("cgp");
		for (CGPService cgpService : services) {
			this.getData().add(cgpService.getServiceName());
		}
		
	}
	

	public double getCommuneRadius() {
		return communeRadius;
	}

	public void setCommuneRadius(double communeRadius) {
		this.communeRadius = communeRadius;
	}

	public ArrayList<CGPService> getServices() {
		return services;
	}

	public void setServices(ArrayList<CGPService> services) {
		this.services = services;
	}

	@Override
	public boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException {
		double distance = this.getGoogleService().getDistance(coordinate, this.getCoordinate());
		return distance < communeRadius;
	}

	@Override
	public String getType() {
		return "CGP";
	}

	@Override
	public int getNumber() {
		return 0;
	}

	@Override
	public boolean isAvailable() {
		Date date= new Date();
		for (CGPService cgpService : services) {
			if(cgpService.isAvailable(date, this.getAvailabilityService())){
				return true;
			}
	}				
		return false ;
	}

	public boolean isAvailable( String serviceName) {
		Date date= new Date();
		if (!(this.getServices().isEmpty())) {
			for(CGPService currentCGPService :services) {
				if (currentCGPService.getServiceName().equals(serviceName)) {
					LOGGER.info(currentCGPService.getServiceName());
					return currentCGPService.isAvailable(date, this.getAvailabilityService());
				}
			}
		}
		return false;
	}
	
	public void addService(CGPService cgpService){
		services.add(cgpService);
	}

}
