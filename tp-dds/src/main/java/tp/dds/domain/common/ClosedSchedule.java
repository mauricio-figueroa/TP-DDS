package tp.dds.domain.common;

import java.util.Date;

import org.joda.time.LocalDateTime;

public class ClosedSchedule {

	private Date dateClosed;
	private boolean allDay;
	private Schedule schedule;

	public ClosedSchedule(Date dateClosed, boolean allDay) {
		super();
		this.dateClosed = dateClosed;
		this.allDay = allDay;
	}

	public ClosedSchedule(Date dateClosed, boolean allDay, Schedule schedule) {
		super();
		this.dateClosed = dateClosed;
		this.allDay = allDay;
		this.schedule = schedule;
	}

	public Date getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}
