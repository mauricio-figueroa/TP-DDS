package tp.dds.domain.common;

public class Coordinate {

	private double longitude;
	private double latitude;
	
	
	public Coordinate(double lat, double lon){
		longitude=lon;
		latitude=lat;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}