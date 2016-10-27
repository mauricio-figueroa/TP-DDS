package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "mainStreet")
    private String mainStreet;

    @Column(name = "number")
    private int number;
    private List<String> betweenStreet;
    private Town town;


    public Address(String mainStreet) {
        this.mainStreet = mainStreet;
        this.number = 123;
        this.betweenStreet = new ArrayList<String>();

    }

    public Address(String mainStreet, int number, List<String> betweenStreets) {
        this.betweenStreet = betweenStreets;
        this.number = number;
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

    public String getAddress() {
        return ("Address: " + mainStreet + " " + number + " .Between streets " + betweenStreet.get(0) + " and " + betweenStreet.get(1) + ".");
    }

	public String getFullAddress(){
		return (getAddress() + town.getFullAddress());
	}
}
