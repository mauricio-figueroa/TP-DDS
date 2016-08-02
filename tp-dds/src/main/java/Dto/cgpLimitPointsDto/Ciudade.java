package Dto.cgpLimitPointsDto;
import java.util.ArrayList;
import java.util.List;


public class Ciudade {

	private String city;
	private List<List<Integer>> limitPoints = new ArrayList<List<Integer>>();

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<List<Integer>> getLimitPoints() {
		return limitPoints;
	}

	public void setLimitPoints(List<List<Integer>> limitPoints) {
		this.limitPoints = limitPoints;
	}

}