package domain;

import org.joda.time.LocalDateTime;

public class ClosedSchedule {

	private LocalDateTime dateClosed;
	private boolean allDay;
	private Schedule schedule;

	public ClosedSchedule(LocalDateTime dateClosed, boolean allDay) {
		super();
		this.dateClosed = dateClosed;
		this.allDay = allDay;
	}

	public ClosedSchedule(LocalDateTime dateClosed, boolean allDay, Schedule schedule) {
		super();
		this.dateClosed = dateClosed;
		this.allDay = allDay;
		this.schedule = schedule;
	}

	public LocalDateTime getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(LocalDateTime dateClosed) {
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
