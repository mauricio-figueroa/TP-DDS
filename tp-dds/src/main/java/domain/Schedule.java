package domain;


import javax.persistence.*;
import java.util.Date;


@SuppressWarnings("deprecation")
@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue
    protected long id;

    @Column
    private Date hourMax;

    @Column
    private Date hourMin;

    public Date getHourMax() {
        return hourMax;
    }

    public Schedule() {
    }

    public void setHourMax(Date hourMax) {
        this.hourMax = hourMax;
    }

    public Date getHourMin() {
        return hourMin;
    }

    public void setHourMin(Date hourMin) {
        this.hourMin = hourMin;
    }

    public Schedule(String hourMinString, String hourMaxString) {

        initMinHour(hourMinString);
        initMaxHour(hourMaxString);

    }

    private void initMinHour(String hourMinString) {

        String[] hourMinSplit = hourMinString.split(":");
        int hourMinInt = Integer.parseInt(hourMinSplit[0]);
        int minuteMinInt = Integer.parseInt(hourMinSplit[1]);
        this.hourMin = new Date();
        hourMin.setHours(hourMinInt);
        hourMin.setMinutes(minuteMinInt);
        hourMin.setSeconds(0);

    }

    private void initMaxHour(String hourMaxString) {

        String[] hourMaxSplit = hourMaxString.split(":");
        int hour = Integer.parseInt(hourMaxSplit[0]);
        int minute = Integer.parseInt(hourMaxSplit[1]);
        this.hourMax = new Date();
        hourMax.setHours(hour);
        hourMax.setMinutes(minute);
        hourMax.setSeconds(0);
    }

}
