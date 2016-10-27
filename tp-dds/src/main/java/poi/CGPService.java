package poi;

import domain.RangeOfAtention;
import internalService.AvailabilityService;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cgpService")
public class CGPService {

    @Column(name = "serviceNAme")
    private String serviceName;

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
