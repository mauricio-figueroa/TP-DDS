package users;

import domain.EmailErrorProcessResolution;
import domain.ErrorProcessResolution;
import domain.RepeatErrorProcessResolution;
import domain.Schedule;
import internalService.PoiService;
import internalService.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import poi.CGP;
import poi.CGPService;
import poi.Poi;

import java.util.List;
import java.util.concurrent.Callable;

public class Admin {

    @Autowired
    private ProcessService processService;

    @Autowired
    private PoiService poiService;

    private long id;
    private List<List<String>> actions;
    private String nombre;
    private String mail;
    private ErrorProcessResolution errorResolution;


    public Admin(List<List<String>> actions, String nombre, String mail, String resolutionType) {

        this.actions = actions;
        this.nombre = nombre;
        this.mail = mail;


        if (resolutionType == "Email") {
            errorResolution = new EmailErrorProcessResolution();
        } else if (resolutionType == "Repeat") {
            errorResolution = new RepeatErrorProcessResolution();

        }
    }

    public Admin() {

    }


    public void turnOffAPoi() {
        Admin admin = this;
        errorResolution.errorResolution(this.processService.turnOffAPoi(this), this, new Callable<String>() {

            @Override
            public String call() {
                return processService.turnOffAPoi(admin);
            }
        });
    }


    public void updateComercialShops(String path) {
        Admin admin = this;
        new Thread(() ->
                errorResolution.errorResolution(this.processService.updateComercialShops(path, this), this, new Callable<String>() {

                    @Override
                    public String call() {
                        return processService.updateComercialShops(path, admin);
                    }
                })).start();

    }

    public void addActionsToUser(String nombre, String type, List actions) {
        Admin admin = this;
        new Thread(() ->
                errorResolution.errorResolution(this.processService.addActionsToUser(nombre, type, actions, this), this, new Callable<String>() {
                    @Override
                    public String call() {
                        return processService.addActionsToUser(nombre, type, actions, admin);
                    }
                })).start();

    }

    public void multiplyProcess(List<Runnable> process) {
        process.forEach(methods -> methods.run());
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public List<List<String>> getActions() {
        return actions;
    }


    public String getNombre() {
        return nombre;
    }




    public boolean addTerminal(Terminal terminal) {
        poiService.addTerminal(terminal);
        return true;
    }


    public boolean removeTerminal(String name) {
        int index = 0;
        boolean status = false;
        for (Terminal currenTerminal : poiService.getTerminales()) {
            if (currenTerminal.getNombre().equalsIgnoreCase(name)) {
                poiService.getTerminales().remove(index);
                status = true;
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
