package poi;

import domain.RangeOfAtention;
import internalService.AvailabilityService;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name = "cgpService")
public class CGPService {


    @Id @GeneratedValue
    private long id;

    @Column(name = "serviceNAme")
    private String serviceName;

    public CGPService() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "range_of_atention")
    private RangeOfAtention rangeOfAtention;

    public CGPService(String serviceName, RangeOfAtention rangeOfAtention) {
        super();
        this.serviceName = serviceName;
        this.rangeOfAtention = rangeOfAtention;

    }

    public RangeOfAtention getRangeOfAtention() {
        return rangeOfAtention;
    }

    public void setRangeOfAtention(RangeOfAtention rangeOfAtention) {
        this.rangeOfAtention = rangeOfAtention;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isAvailable(Date date, AvailabilityService availabilityService) {
        boolean available = availabilityService.isAvailability(this.getRangeOfAtention());
        return available;
    }

}
