package poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import domain.Address;
import domain.Coordinate;
import domain.RangeOfAtention;
import domain.Schedule;

public class Bank extends Poi {
    private RangeOfAtention rangeOfAtention;


    public Bank(String name, Address address, Coordinate coordinate, String services) {
        super(name, address, coordinate);
        this.servicios = new ArrayList<>();


        String[] parts = services.split(",");
        for (String currentString : parts) {
            this.servicios.add(currentString);
        }

        List<Schedule> schedules1 = new ArrayList<Schedule>();
        schedules1.add(new Schedule("10:00", "15:00"));
        List<Integer> days1 = new ArrayList<Integer>();
        days1.add(0);
        days1.add(1);
        days1.add(2);
        days1.add(3);
        days1.add(4);
        days1.add(5);
        days1.add(6);
        this.type = this.getClass().getSimpleName();

        this.rangeOfAtention = new RangeOfAtention(schedules1, days1);
        this.getData().add(this.getName());
        this.getData().add(this.getAddress().getMainStreet());
        this.getData().add("bank");
    }


    public void setServicios(List<String> servicos){
        this.servicios=servicos;
    }

    public List<String> getServicios(){
        return this.servicios;
    }


    public RangeOfAtention getRangeOfAtention() {
        return rangeOfAtention;
    }

    public void setRangeOfAtention(RangeOfAtention rangeOfAtention) {
        this.rangeOfAtention = rangeOfAtention;
    }

    public boolean isEnable() {
        return true; // TO DO
    }

    public String getType() {
        return "Bank";
    }

    @Override
    public int getNumber() {
        return 0;
    }

    public boolean isNearBy(Coordinate coordinate) throws ClientProtocolException, IOException {
        double distance = this.getGoogleService().getDistance(coordinate, this.getCoordinate());
        return distance < 100;
    }

    @Override
    public boolean isAvailable() {
        return this.getAvailabilityService().isAvailability(this.getRangeOfAtention());
    }


}
