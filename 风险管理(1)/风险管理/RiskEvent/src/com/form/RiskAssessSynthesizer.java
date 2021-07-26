package com.form;

public class RiskAssessSynthesizer implements Comparable{


	private String depName;
	private String year;
	private String yearTo;
	private String quarter;
	private String quarterTo;
	private String depProperty;
	private Integer numberInputAll;
	private Double assessResultQuarter;
	private Double assessResultQuarterByNum;
	private Double assessResultQuarterBySituation;
	private String TimeFrom;
	private String TimeTo;
	private String Time;
	
	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
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
	
	public Double getAssessResultQuarterByNum() {
		return assessResultQuarterByNum;
	}
	public void setAssessResultQuarterByNum(Double assessResultQuarterByNum) {
		this.assessResultQuarterByNum = assessResultQuarterByNum;
	}
	public Double getAssessResultQuarterBySituation() {
		return assessResultQuarterBySituation;
	}
	public void setAssessResultQuarterBySituation(
			Double assessResultQuarterBySituation) {
		this.assessResultQuarterBySituation = assessResultQuarterBySituation;
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
	
	public Double getAssessResultQuarter() {
		return assessResultQuarter;
	}
	public void setAssessResultQuarter(double assessResultQuarter) {
		this.assessResultQuarter = assessResultQuarter;
	}
	
	@Override
	public int compareTo(Object obj) {
		
		if (this == obj)
			return 0;
		if (obj == null)
			return 1;
		if (getClass() != obj.getClass())
			return 1;
		RiskAssessSynthesizer other = (RiskAssessSynthesizer) obj;
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
	

