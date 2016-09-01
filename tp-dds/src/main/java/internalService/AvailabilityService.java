package internalService;

import domain.ClosedSchedule;
import domain.Holidays;
import domain.RangeOfAtention;
import domain.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class AvailabilityService {

    private static AvailabilityService instance = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityService.class);
    private static Holidays holidays;

    public static Holidays getHolidays() {
        return holidays;
    }

    public static void setHolidays(Holidays holidays) {
        AvailabilityService.holidays = holidays;
    }

    protected AvailabilityService() {
    }

    public static AvailabilityService getInstance() {
        if (instance == null) {
            instance = new AvailabilityService();
            holidays = new Holidays();
        }
        return instance;
    }

/*
    //TODO Closed Dates FIXME!!!!!!!!!!!!!1
    @SuppressWarnings("deprecation")
    public boolean isAvailability(RangeOfAtention rangeOfAtention) {
        Date date = new Date();
        boolean isAvailability = false;
        String today = (date.getDate()) + "/" + (date.getMonth() + 1);
        LOGGER.info("today" + today);
        List<String> holidaysDays = holidays.getHolidays();
        LOGGER.info("" + date.getDay());


        if ((rangeOfAtention.getDaysOfAttention().contains(date.getDay()) && (!holidaysDays.contains(today)))) {
            for (Schedule schedule : rangeOfAtention.getSchedules()) {
                if (date.before(schedule.getHourMax()) && (date.after(schedule.getHourMin()))) {
                    isAvailability = true;
                }
            }
        }
        return isAvailability;
    }
    */

    @SuppressWarnings("deprecation")
    public boolean isAvailability(RangeOfAtention rangeOfAtention) {
        Date date = new Date();
        boolean isAvailability = false;
        String today = (date.getDate()) + "/" + (date.getMonth() + 1);
        LOGGER.info("today" + today);
        List<String> holidaysDays = holidays.getHolidays();
        LOGGER.info("" + date.getDay());


        if ((rangeOfAtention.getDaysOfAttention().contains(date.getDay()) && this.isClosedDay(rangeOfAtention))) {
            for (Schedule schedule : rangeOfAtention.getSchedules()) {
                if (date.before(schedule.getHourMax()) && (date.after(schedule.getHourMin()))) {
                    isAvailability = true;
                }
            }
        }
        return isAvailability;
    }

    public boolean isClosedDay(RangeOfAtention rangeOfAtention) {
        Date date = new Date();
        String today = (date.getDate()) + "/" + (date.getMonth() + 1);
        LOGGER.info("today" + today);
        List<String> holidaysDays = holidays.getHolidays();
        LOGGER.info("" + date.getDay());
        for (ClosedSchedule currentSchedule : rangeOfAtention.getClosedSchedule()) {
            if (currentSchedule.getDateClosed() == date) {
                if (currentSchedule.isAllDay()) {
                    return true;
                } else {
                    if (date.before(currentSchedule.getSchedule().getHourMax()) && date.after(currentSchedule.getSchedule().getHourMin())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
