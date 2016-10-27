package domain;


import javax.persistence.*;

@Entity
@Table(name = "town")
public class Town {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "town")
    private String town;
    @Column(name = "district")
    private String district;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;

    public String getFullAddress() {
        return (town + ", " + district + ", " + province + ", " + country + ".");

    }
}
