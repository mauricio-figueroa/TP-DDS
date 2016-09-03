package tp.dds.api.appiGoogleDTO;

import java.util.ArrayList;
import java.util.List;

public class ApiDirectionsGoogleDTO {
	private ArrayList<String> destinationAddresses;
	private ArrayList<String> originAddresses;
	private List<DTO> rows;
	private String status;
	
	


	public ArrayList<String> getDestinationAddresses() {
		return destinationAddresses;
	}
	public void setDestinationAddresses(ArrayList<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}
	public ArrayList<String> getOriginAddresses() {
		return originAddresses;
	}
	public void setOriginAddresses(ArrayList<String> originAddresses) {
		this.originAddresses = originAddresses;
	}

	public List<DTO> getRows() {
		return rows;
	}
	public void setRows(List<DTO> rows) {
		this.rows = rows;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
