package com.form;

public class RiskAssessSituationM implements Comparable{


	private String depName;
	private String depId;
	private String year;
	private String YearTo;
	private String QuarterTo;
	private String quarter;
	private String TimeFrom;
	private String depProperty;
    private Integer numberDealAll;
    private Integer numberNeed;
	private Integer assessResultQuarter;
	private Integer standardNum;
	private Integer unFinishedNum;
	private Integer toDealnumber;//待应对
	
	public Integer getToDealnumber() {
		return toDealnumber;
	}
	public void setToDealnumber(Integer toDealnumber) {
		this.toDealnumber = toDealnumber;
	}
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
		return YearTo;
	}
	public void setYearTo(String yearTo) {
		YearTo = yearTo;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public String getQuarterTo() {
		return QuarterTo;
	}
	public void setQuarterTo(String quarterTo) {
		QuarterTo = quarterTo;
	}
	public String getTimeFrom() {
		return TimeFrom;
	}
	public void setTimeFrom(String TimeFrom) {
		this.TimeFrom = TimeFrom ;
	}
	public String getDepProperty() {
		return depProperty;
	}
	public void setDepProperty(String depProperty) {
		this.depProperty = depProperty;
	}
	public Integer getNumberDealAll() {
		return numberDealAll;
	}
	public void setNumberDealAll(Integer numberDealAll) {
		this.numberDealAll = numberDealAll;
	}
	
	public Integer getNumberNeed() {
		return numberNeed;
	}
	public void setNumberNeed(Integer numberNeed) {
		this.numberNeed = numberNeed;
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
		RiskAssessSituationM other = (RiskAssessSituationM) obj;
		System.out.println("assessResultQuarter============================"+assessResultQuarter);
		System.out.println("other.assessResultQuarter======================"+other.assessResultQuarter);
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
