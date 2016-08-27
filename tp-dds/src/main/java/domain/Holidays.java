package domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Holidays {

    private final String NAVIDAD = "25/12";
    private final String INDEPENDENCIA = "9/7";
    private final String DIA_DEL_TRABAJADOR = "1/5";
    private List<String> holidays = new ArrayList<String>();


    public List<String> getHolidays() {
        return holidays;
    }


    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }


    public Holidays() {
        holidays.add(NAVIDAD);
        holidays.add(INDEPENDENCIA);
        holidays.add(DIA_DEL_TRABAJADOR);

    }


}
