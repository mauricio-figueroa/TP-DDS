package internalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.client.ClientProtocolException;

import domain.Coordinate;
import domain.Reloj;
import externalServices.BankService.BankService;
import observers.subjectBusqueda.SubjectBusquedas;
import poi.Bank;
import poi.Poi;
import users.Admin;
import users.Terminal;


public class PoiService {

	private static PoiService instance = null;
	private static List<Poi> allPois;
	private static BankService bankService;
	private SubjectBusquedas subjectBusquedas = SubjectBusquedas.getInstance();
	private static ReportService reportService;
	private static List<Terminal> terminales;
	private static List<Admin> admins;
	private static ProcessService processService;
	
	
	public static PoiService getInstance() {

		if (instance == null) {
			instance = new PoiService();
			allPois = new ArrayList<Poi>();
			bankService = BankService.getInstance();
			reportService=ReportService.getInstance();
			terminales=new ArrayList<Terminal>();
			terminales.add(new Terminal("TerminalTest",new Coordinate(14.6,15.3),null));
			processService= ProcessService.getInstance();
			
		}
		return instance;
	}

	public static List<Admin> getAdmins() {
		return admins;
	}

	public static void setAdmins(List<Admin> admins) {
		PoiService.admins = admins;
	}

	public static ProcessService getProcessService() {
		return processService;
	}

	public static void setProcessService(ProcessService processService) {
		PoiService.processService = processService;
	}

	public Terminal searchTerminal(String terminalName){
		Terminal searchTerminal= null;
		for(Terminal currentTerminal: terminales){
			if (currentTerminal.getNombre().equalsIgnoreCase(terminalName)){
				searchTerminal= currentTerminal;
			}
		}
		return searchTerminal;
		
	}


	public Terminal searchTerminalByName(String name){
		return terminales.stream().filter(terminal-> terminal.getNombre().equalsIgnoreCase(name)).findFirst().get();
	}
	
	
	public static BankService getBankService() {
		return bankService;
	}

	public static void setBankService(BankService bankService) {
		PoiService.bankService = bankService;
	}

	public SubjectBusquedas getSubjectBusquedas() {
		return subjectBusquedas;
	}

	public void setSubjectBusquedas(SubjectBusquedas subjectBusquedas) {
		this.subjectBusquedas = subjectBusquedas;
	}

	public static ReportService getReportService() {
		return reportService;
	}

	public static void setReportService(ReportService reportService) {
		PoiService.reportService = reportService;
	}

	public static List<Terminal> getTerminales() {
		return terminales;
	}

	public static void setTerminales(List<Terminal> terminales) {
		PoiService.terminales = terminales;
	}

	public static void setInstance(PoiService instance) {
		PoiService.instance = instance;
	}



	public void removeAllPois() {
		allPois.clear();
	}

	public List<Poi> getAllPois() {
		return allPois;
	}

	public void addPoi(Poi poi){
		allPois.add(poi);
	}

	public void setAllPois(List<Poi> allPois) {
		this.allPois = allPois;
	}

	public String poiType(Poi poi) {
		return poi.getType();
	}

	public boolean isNearby(Poi poi1, Coordinate coordinates) throws ClientProtocolException, IOException {
		return poi1.isNearby(coordinates);
	}

	public boolean isAvailable(Poi poi) {
		return poi.isAvailable();
	}

	public List<Poi> searchPois(List<String> textosBuscados, String nombreTerminal) throws AddressException, MessagingException, InterruptedException {
		Reloj reloj=new Reloj();
		reloj.Contar();
		List<Poi> pois = new ArrayList<Poi>();

		textosBuscados.forEach(texto ->
		{for (Poi poi : allPois) {

			for (String text : poi.getData()) {
				if (text.contains(texto)) {
					pois.add(poi);
					break;
				}
			}
		}
		});

		System.out.println(pois.size());
		//Thread.sleep(6000);
		reloj.Detener();
		int segundosQueTardo=reloj.getSegundos();
		System.out.println("Segundoooos: "+segundosQueTardo);
		this.subjectBusquedas.notificarObservador(textosBuscados, nombreTerminal, pois,segundosQueTardo);
		return pois;
	}

	public List<Poi> searchPois(String textoBuscado, String nombreTerminal) throws AddressException, MessagingException, InterruptedException {
		Reloj reloj=new Reloj();
		reloj.Contar();
		List<Poi> pois = new ArrayList<Poi>();

		for (Poi poi : allPois) {

			for (String text : poi.getData()) {
				if (text.contains(textoBuscado)) {
					pois.add(poi);
					break;
				}

		}
		}

		System.out.println(pois.size());
		//Thread.sleep(6000);
		reloj.Detener();
		int segundosQueTardo=reloj.getSegundos();
		System.out.println("Segundoooos: "+segundosQueTardo);
		this.subjectBusquedas.notificarObservador(Arrays.asList(textoBuscado), nombreTerminal, pois,segundosQueTardo);
		return pois;
	}


	public List<Bank> searchBank(String bank, String service) {
		return getBanksFromExternalService(bank, service);

	}
	
	public Map<String,Integer> getReportesTotalesPorFecha(){
		return reportService.getReportesTotalesPorFecha();
	}
	
	public Map<String, Integer> getParcialesPorTerminal(String nombreTerminal){
		return reportService.getParcialesPorTerminal(nombreTerminal);
	}

	public Map<String, Integer> getReportesTodasLasTerminales(){
		return this.reportService.getReportesTotalesTodasLasTerminales();
	}
	
	
	

	public List<Bank> getBanksFromExternalService(String bank, String service) {
		return bankService.getBanksFromService(bank, service);
	}
	
	
	public void resetReports(){
		reportService.resetReports();
	}

	public void resetAllPois() {
		allPois=new ArrayList<Poi>();
		
	}
	
	public void resetAllTerminals() {
		terminales= new ArrayList<Terminal>();
		
	}
}
