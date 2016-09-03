package tp.dds.domain.common;

import java.util.Calendar;

public class ProcessStory {
	
	
	private Calendar initDate ;
	private Calendar endDate;
	private String processName;
	private String userRan;
	private String result;
	private String errorMessage;
	
	public ProcessStory(Calendar initDate, Calendar endDate,
			String processName, String userRan, String result,
			String errorMessage) {
		super();
		this.initDate = initDate;
		this.endDate = endDate;
		this.processName = processName;
		this.userRan = userRan;
		this.result = result;
		this.errorMessage = errorMessage;
	}
	
	
	@Override
	public String toString() {
		return "ProcessStory [initDate=" + initDate + ", endDate=" + endDate
				+ ", processName=" + processName + ", userRan=" + userRan
				+ ", result=" + result + ", errorMessage=" + errorMessage + "]";
	}


	public Calendar getInitDate() {
		return initDate;
	}
	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getUserRan() {
		return userRan;
	}
	public void setUserRan(String userRan) {
		this.userRan = userRan;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	

}
