package com.form;

public class RiskAssessNum implements Comparable{


	private String depName;
	private String depId;
	private String year;
	private String yearTo;
	private String quarter;
	private String quarterTo;
	private String depProperty;
	private Integer numberInputAll;
	private Integer assessResultQuarter;
	private Integer standardNum;
	private Integer unFinishedNum;
	private Integer NumInPass;
	private String TimeFrom;
	private String TimeTo;
	private String Time;
	
	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYearTo() {
		return yearTo;
	}
	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public String getQuarterTo() {
		return quarterTo;
	}
	public void setQuarterTo(String quarterTo) {
		this.quarterTo = quarterTo;
	}
	public String getTimeFrom() {
		return TimeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		TimeFrom = timeFrom;
	}
	
	public Integer getNumInPass() {
		return NumInPass;
	}
	public void setNumInPass(Integer numInPass) {
		NumInPass = numInPass;
	}
	public String getTimeTo() {
		return TimeTo;
	}
	public void setTimeTo(String timeTo) {
		TimeTo = timeTo;
	}
	
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getDepProperty() {
		return depProperty;
	}
	public void setDepProperty(String depProperty) {
		this.depProperty = depProperty;
	}
	public Integer getNumberInputAll() {
		return numberInputAll;
	}
	public void setNumberInputAll(Integer numberInputAll) {
		this.numberInputAll = numberInputAll;
	}
	
	public Integer getAssessResultQuarter() {
		return assessResultQuarter;
	}
	public void setAssessResultQuarter(Integer assessResultQuarter) {
		this.assessResultQuarter = assessResultQuarter;
	}
	public Integer getStandardNum() {
		return standardNum;
	}
	public void setStandardNum(Integer standardNum) {
		this.standardNum = standardNum;
	}
	public Integer getUnFinishedNum() {
		return unFinishedNum;
	}
	public void setUnFinishedNum(Integer unFinishedNum) {
		this.unFinishedNum = unFinishedNum;
	}
	@Override
	public int compareTo(Object obj) {
		
		if (this == obj)
			return 0;
		if (obj == null)
			return 1;
		if (getClass() != obj.getClass())
			return 1;
		RiskAssessNum other = (RiskAssessNum) obj;
		if (assessResultQuarter == null) {
			if (other.assessResultQuarter != null)
				return -1;
		} else if(other.assessResultQuarter == null){
			if (assessResultQuarter != null)
				return 1;
			
		}else if (assessResultQuarter - other.assessResultQuarter > 0)
			return 1;
		return 0;
		
	}
	
}
