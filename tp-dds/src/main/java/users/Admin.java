package users;

import java.util.List;


import domain.Schedule;
import internalService.PoiService;
import poi.CGP;
import poi.CGPService;
import poi.Poi;

public class Admin {

	private long id;
	private List<List<String>> actions;
	private String nombre;
	private PoiService poiService;



	public Admin(List<List<String>> actions, String nombre) {
		super();
		this.actions = actions;
		this.nombre = nombre;
		this.poiService = PoiService.getInstance();
	}
	
	public void turnOffAPoi(){

		new Thread(() -> poiService.getProcessService().turnOffAPoi(this)).start();
	}
	
	
	public void updateComercialShops(String path){
		new Thread(() -> poiService.getProcessService().updateComercialShops(path,this)).start();
		
	}
	
	public void addActionsToUser(String nombre,String type,List actions){
		new Thread(() -> poiService.getProcessService().addActionsToUser(nombre,type,actions,this)).start();
	}
	
	public void multiplyProcess(List<Runnable> process ){
		process.forEach(methods -> methods.run() );
	}
	
	

	public List<List<String>> getActions() {
		return actions;
	}

	public void setActions(List<List<String>> actions) {
		this.actions = actions;
	}

	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	

	public Admin() {
		poiService = PoiService.getInstance();
		
	}
	
	public boolean addTerminal(Terminal terminal){
		poiService.getTerminales().add(terminal);
		return true;
	}
	
	
	
	public boolean removeTerminal(String name){
		int index=0;
		boolean status=false;
		for(Terminal currenTerminal:poiService.getTerminales()){
			if(currenTerminal.getNombre().equalsIgnoreCase(name)){
				poiService.getTerminales().remove(index);
				status=true;
			}
			index++;
		}
		return status;
	}
	

	public boolean removePoi(String poiName) {
		int index = 0;
		
		for (Poi currentPoi : poiService.getAllPois()) {

			if (currentPoi.getName().equalsIgnoreCase(poiName)) {
				poiService.getAllPois().remove(index);
				return true;
			}
			index++;
		}
		return false;
	}

	public void addPoi(Poi poi) {
		poiService.getAllPois().add(poi);
	}

	// Consultar o interpretar otras formas.
	//NO actualiza la lista cuando actualizo el elemento.
	//EN cualquier momento borro todo el elemento a la meirda y pongo el nuevo pasado por parametro.
	public boolean modifyPoi(Poi poi, String poiName) {

		for (Poi currentPoi : poiService.getAllPois()) {

			if (currentPoi.getName().equalsIgnoreCase(poiName)) {
				currentPoi = poi;
				return true;

			}

		}
		return false;
	}

	public boolean addCGPServiceToCGP(String cgpName, CGPService cgpService) {

		for (Poi currentPoi : poiService.getAllPois()) {
			if (currentPoi.getName().equalsIgnoreCase(cgpName)
					&& currentPoi.getType().equalsIgnoreCase("CGP")) {

				CGP cgp = (CGP) currentPoi;
				cgp.addService(cgpService);
				return true;
			}
		}
		return false;

	}

	public PoiService getPoiService() {
		return poiService;
	}

	public void setPoiService(PoiService poiService) {
		this.poiService = poiService;
	}

	public boolean addScheduleToCGPService(String nameOfCGP,
			String serviceName, String hourMax, String hourMin) {

		for (Poi currentPoi : poiService.getAllPois()) {
			if (currentPoi.getType() == "CGP"
					&& currentPoi.getName().equalsIgnoreCase(nameOfCGP)) {
				CGP cgp = (CGP) currentPoi;
				for (CGPService currentService : cgp.getServices()) {
					if (currentService.getServiceName().equalsIgnoreCase(
							serviceName)) {
						Schedule schedule = new Schedule(hourMin, hourMax);
						currentService.getRangeOfAtention().addSchedule(
								schedule);
						return true;
					}
				}
			}
		}
		return false;

	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

}
