package controller.response;
import java.util.HashMap;
import java.util.List;

public class PoiDTO {

	public String icon;
	public int numberLine;
	public String Direccion;
	public String zone;
	public HashMap<String,String> cgpServices;
	public List<String> bankServices;
	public String name;
	public String activity;

	public PoiDTO( int numberLine, String direccion, String zone, HashMap<String, String> cgpServices, List<String> bankServices, String name, String activity) {
		this.icon = "https://i.ytimg.com/vi/ZELci4OhE-c/maxresdefault.jpg";
		this.numberLine = numberLine;
		Direccion = direccion;
		this.zone = zone;
		this.cgpServices = cgpServices;
		this.bankServices = bankServices;
		this.name = name;
		this.activity = activity;
	}

	public int getNumberLine() {
		return numberLine;
	}

	public void setNumberLine(int numberLine) {
		this.numberLine = numberLine;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public HashMap<String, String> getCgpServices() {
		return cgpServices;
	}

	public void setCgpServices(HashMap<String, String> cgpServices) {
		this.cgpServices = cgpServices;
	}

	public List<String> getBankServices() {
		return bankServices;
	}

	public void setBankServices(List<String> bankServices) {
		this.bankServices = bankServices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}








}
