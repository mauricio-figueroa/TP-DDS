package internalService;

import domain.Coordinate;
import domain.Reloj;
import externalServices.BankService.BankService;
import observers.subjectBusqueda.SubjectBusquedas;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import poi.Bank;
import poi.Poi;
import users.Admin;
import users.Terminal;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PoiService {

    @Autowired
    private BankService bankService;
    @Autowired
    private SubjectBusquedas subjectBusquedas;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ProcessService processService;

    private List<Terminal> terminales = new ArrayList<Terminal>();
    private List<Admin> admins = new ArrayList<Admin>();
    private List<Poi> allPois = new ArrayList<Poi>();


    public Terminal searchTerminal(String terminalName) {
        Terminal searchTerminal = null;
        for (Terminal currentTerminal : terminales) {
            if (currentTerminal.getNombre().equalsIgnoreCase(terminalName)) {
                searchTerminal = currentTerminal;
            }
        }
        return searchTerminal;

    }


    public void removeAllPois() {
        allPois.clear();
    }

    public List<Poi> getAllPois() {
        return allPois;
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

    public List<Poi> searchPois(String string, String nombreTerminal) throws AddressException, MessagingException, InterruptedException {
        Reloj reloj = new Reloj();
        reloj.Contar();
        List<Poi> pois = new ArrayList<Poi>();
        for (Poi poi : allPois) {

            for (String text : poi.getData()) {
                if (text.contains(string)) {
                    pois.add(poi);
                    break;
                }
            }
        }
        System.out.println(pois.size());
        //Thread.sleep(6000);
        reloj.Detener();
        int segundosQueTardo = reloj.getSegundos();
        System.out.println("Segundoooos: " + segundosQueTardo);
        this.subjectBusquedas.notificarObservador(string, nombreTerminal, pois, segundosQueTardo);
        return pois;
    }

    public List<Bank> searchBank(String bank, String service) {
        return getBanksFromExternalService(bank, service);

    }

    public Map<String, Integer> getReportesTotalesPorFecha() {
        return reportService.getReportesTotalesPorFecha();
    }

    public Map<String, Integer> getParcialesPorTerminal(String nombreTerminal) {
        return reportService.getParcialesPorTerminal(nombreTerminal);
    }

    public Map<String, Integer> getReportesTodasLasTerminales() {
        return this.reportService.getReportesTotalesTodasLasTerminales();
    }


    public List<Bank> getBanksFromExternalService(String bank, String service) {
        return bankService.getBanksFromService(bank, service);
    }


    public void resetReports() {
        reportService.resetReports();
    }

    public void resetAllPois() {
        allPois = new ArrayList<Poi>();

    }

    public void resetAllTerminals() {
        terminales = new ArrayList<Terminal>();

    }

    public void addTerminal(Terminal terminal) {
        this.terminales.add(terminal);
    }

    public List<Terminal> getTerminales() {
        return terminales;
    }
}
