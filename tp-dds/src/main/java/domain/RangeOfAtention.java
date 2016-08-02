package domain;

import java.util.List;

public class RangeOfAtention {
		

	private List<Schedule> schedules;
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
	
	public void addSchedule(Schedule schedule){
		schedules.add(schedule);
	}
	
	
	
	
	

}
