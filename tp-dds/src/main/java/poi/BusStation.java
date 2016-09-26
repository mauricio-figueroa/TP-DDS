package poi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import domain.Address;
import domain.Coordinate;

public class BusStation extends Poi {

    private Integer numberBusStation;


    public BusStation(String name, Address address, Coordinate coordinate, Integer aNumberBusStation) {
        super(name, address, coordinate);
        numberBusStation = aNumberBusStation;
        icon="http://www.clker.com/cliparts/L/B/N/E/8/K/bus-station-red-hi.png";


        this.type = this.getClass().getSimpleName();
        this.getData().add(name);
        this.getData().add(numberBusStation.toString());
       // this.getData().add(address.getMainStreet());
        this.getData().add("bus");
    }

    public Integer getNumberBusStation() {
        return numberBusStation;
    }

    public void setNumberBusStation(Integer numberBusStation) {
        this.numberBusStation = numberBusStation;
    }

    @Override
    public boolean isNearby(Coordinate coordinate) throws ClientProtocolException, IOException {
        double distance = this.getGoogleService().getDistance(coordinate, this.getCoordinate());
        return distance < 100;
    }

    @Override
    public String getType() {
        return "BusStation";
    }

    @Override
    public int getNumber() {
        return  this.numberBusStation;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}
