package tp.dds.domain.common;

public class Town {

	private String town;
	private String district;
	private String province;
	private String country;
	
	public String getFullAddress(){
		return (town + ", " + district + ", " + province + ", " + country + ".");
		
	}
}
