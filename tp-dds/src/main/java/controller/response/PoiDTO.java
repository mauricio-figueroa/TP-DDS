package controller.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.Address;
import domain.RangeOfAtention;

public class PoiDTO {
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	String name;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	String type;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	Address address;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	RangeOfAtention rangeOfAtention;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	double communeRadius;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	int numberBusStation;
	
	
	public PoiDTO(String name, String type, Address address,
			RangeOfAtention rangeOfAtention, double communeRadius,
			int numberBusStation) {
		super();
		this.name = name;
		this.type = type;
		this.address = address;
		this.rangeOfAtention = rangeOfAtention;
		this.communeRadius = communeRadius;
		this.numberBusStation = numberBusStation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public RangeOfAtention getRangeOfAtention() {
		return rangeOfAtention;
	}
	public void setRangeOfAtention(RangeOfAtention rangeOfAtention) {
		this.rangeOfAtention = rangeOfAtention;
	}
	public double getCommuneRadius() {
		return communeRadius;
	}
	public void setCommuneRadius(double communeRadius) {
		this.communeRadius = communeRadius;
	}
	public int getNumberBusStation() {
		return numberBusStation;
	}
	public void setNumberBusStation(int numberBusStation) {
		this.numberBusStation = numberBusStation;
	}
	
	
	
	
	

}
