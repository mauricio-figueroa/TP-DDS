package controller.response;

import java.util.HashMap;
import java.util.List;

public class PoiDTO {


    public String icon;
    public int numberLine;
    public String type;
    public String direccion;
    public String zone;
    public HashMap<String, List<Integer>> cgpServices;
    public List<String> bankServices;
    public String name;
    public String activity;


    public PoiDTO(String icon, String type, String direccion, String zone, List<String> bankServices) {
        this.icon = icon;
        this.type = type;
        this.direccion = direccion;
        this.zone = zone;
        this.bankServices = bankServices;
    }


    public PoiDTO(String icon, String type, String direccion, String zone, HashMap<String, List<Integer>> cgpServices) {
        this.icon = icon;
        this.type = type;
        this.direccion = direccion;
        this.zone = zone;
        this.cgpServices = cgpServices;
    }

    public PoiDTO(String icon, String type, int numberLine) {
        this.icon = icon;
        this.type = type;
        this.numberLine = numberLine;
    }

    public PoiDTO(String icon, String type, String direccion, String name, String activity) {
        this.icon = icon;
        this.type = type;
        this.direccion = direccion;
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
        return direccion;
    }

    public void setDireccion(String direccion) {
        direccion = direccion;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, List<Integer>> getCgpServices() {
        return cgpServices;
    }

    public void setCgpServices(HashMap<String, List<Integer>> cgpServices) {
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
