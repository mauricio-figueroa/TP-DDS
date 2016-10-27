package domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "range_of_atention")
public class RangeOfAtention {


    @Id
    @GeneratedValue
    private long id;

    public RangeOfAtention() {
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="fk_range_of_atention")
    private List<Schedule> schedules;


    @Transient
    private List<Integer> daysOfAttention;


    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Integer> getDaysOfAttention() {
        return daysOfAttention;
    }

    public void setDaysOfAttention(List<Integer> daysOfAttention) {
        this.daysOfAttention = daysOfAttention;
    }

    public RangeOfAtention(List<Schedule> schedules, List<Integer> daysOfAttention) {
        super();
        this.schedules = schedules;
        this.daysOfAttention = daysOfAttention;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }


}
