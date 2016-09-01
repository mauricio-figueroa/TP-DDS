package domain;

import java.util.ArrayList;
import java.util.List;

public class RangeOfAtention {

	private List<Schedule> schedules;
	private List<Integer> daysOfAttention;
	private List<ClosedSchedule> closedSchedules;

	public RangeOfAtention(List<Schedule> schedules, List<Integer> daysOfAttention) {
		super();
		this.schedules = schedules;
		this.daysOfAttention = daysOfAttention;
		this.closedSchedules = new ArrayList<ClosedSchedule>();
	}

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

	public void addSchedule(Schedule schedule) {
		schedules.add(schedule);
	}

	public List<ClosedSchedule> getClosedSchedule() {
		return closedSchedules;
	}

	public void setClosedSchedule(List<ClosedSchedule> closedSchedule) {
		this.closedSchedules = closedSchedule;
	}

	public void addClosedSchedule(ClosedSchedule closedSchedule) {
		this.closedSchedules.add(closedSchedule);
	}

}
