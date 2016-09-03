package tp.dds.domain.common;

import java.util.List;

public class Address {

	
	private String mainStreet;
	private int number;
	private List<String> betweenStreet;
	private Town town;
	 
	
	
	public Address(String mainStreet) {
		super();
		this.mainStreet = mainStreet;
	
	}

	public String getMainStreet() {
		return mainStreet;
	}

	public void setMainStreet(String mainStreet) {
		this.mainStreet = mainStreet;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<String> getBetweenStreet() {
		return betweenStreet;
	}

	public void setBetweenStreet(List<String> betweenStreet) {
		this.betweenStreet = betweenStreet;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public String getAddress(){
	return ("Address: " + mainStreet+ " " + number + " .Between streets " + betweenStreet.get(0) + " and " + betweenStreet.get(1)+ "." );
	}
	
	public String getFullAddress(){
		return (getAddress() + town.getFullAddress());
	}
}
