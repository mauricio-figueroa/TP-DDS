package tp.dds.domain.common;


public class Department extends Address {

	public Department(String mainStreet) {
		super(mainStreet);
		// TODO Auto-generated constructor stub
	}

	private int floor;
	private String department;
	private String unit;
	
	public String getFullAddress(){
		return ("Department: " + department +  ". Floor: " + floor + " . Unit: " + unit); 
	}
}
