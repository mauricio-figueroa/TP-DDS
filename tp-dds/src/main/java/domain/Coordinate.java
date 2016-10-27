package domain;


import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Id
    @GeneratedValue
    protected long id;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "latitude")
    private double latitude;


    public Coordinate(double lat, double lon) {
        longitude = lon;
        latitude = lat;
    }

    public Coordinate() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}