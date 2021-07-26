package com.model;



/**
 * ReportDepartment entity. @author MyEclipse Persistence Tools
 */

public class ReportDepartment {


    // Fields    

     private String rdId;
     private String rdReportId;
     private String rdReportName;
     private String rdDepId;
     private String rdDepName;
     private String rdWriterId;
     private String rdWriterName;
     private String rdChargerId;
     private String rdChargerName;
     private String rdFlowId;
     private String rdFlowstatus;
     private String rdRemark;
     private String rdNextStatus;
     private String rdModifyDate;

    // Constructors

    /** default constructor */
    public ReportDepartment() {
    }

   
    // Property accessors

    public String getRdReportId() {
        return this.rdReportId;
    }
    
    public String getRdId() {
		return rdId;
	}


	public void setRdId(String rdId) {
		this.rdId = rdId;
	}


	public void setRdReportId(String rdReportId) {
        this.rdReportId = rdReportId;
    }

    public String getRdReportName() {
        return this.rdReportName;
    }
    
    public void setRdReportName(String rdReportName) {
        this.rdReportName = rdReportName;
    }

    public String getRdDepId() {
        return this.rdDepId;
    }
    
    public void setRdDepId(String rdDepId) {
        this.rdDepId = rdDepId;
    }

    public String getRdDepName() {
        return this.rdDepName;
    }
    
    public void setRdDepName(String rdDepName) {
        this.rdDepName = rdDepName;
    }

    public String getRdWriterId() {
        return this.rdWriterId;
    }
    
    public void setRdWriterId(String rdWriterId) {
        this.rdWriterId = rdWriterId;
    }

    public String getRdWriterName() {
        return this.rdWriterName;
    }
    
    public void setRdWriterName(String rdWriterName) {
        this.rdWriterName = rdWriterName;
    }

    public String getRdChargerId() {
        return this.rdChargerId;
    }
    
    public void setRdChargerId(String rdChargerId) {
        this.rdChargerId = rdChargerId;
    }

    public String getRdChargerName() {
        return this.rdChargerName;
    }
    
    public void setRdChargerName(String rdChargerName) {
        this.rdChargerName = rdChargerName;
    }

    public String getRdFlowId() {
        return this.rdFlowId;
    }
    
    public void setRdFlowId(String rdFlowId) {
        this.rdFlowId = rdFlowId;
    }

    public String getRdFlowstatus() {
        return this.rdFlowstatus;
    }
    
    public void setRdFlowstatus(String rdFlowstatus) {
        this.rdFlowstatus = rdFlowstatus;
    }

    public String getRdRemark() {
        return this.rdRemark;
    }
    
    public void setRdRemark(String rdRemark) {
        this.rdRemark = rdRemark;
    }


	public String getRdNextStatus() {
		return rdNextStatus;
	}


	public void setRdNextStatus(String rdNextStatus) {
		this.rdNextStatus = rdNextStatus;
	}


	public String getRdModifyDate() {
		return rdModifyDate;
	}


	public void setRdModifyDate(String rdModifyDate) {
		this.rdModifyDate = rdModifyDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rdChargerId == null) ? 0 : rdChargerId.hashCode());
		result = prime * result
				+ ((rdFlowId == null) ? 0 : rdFlowId.hashCode());
		result = prime * result
				+ ((rdFlowstatus == null) ? 0 : rdFlowstatus.hashCode());
		result = prime * result + ((rdId == null) ? 0 : rdId.hashCode());
		result = prime * result
				+ ((rdModifyDate == null) ? 0 : rdModifyDate.hashCode());
		result = prime * result
				+ ((rdRemark == null) ? 0 : rdRemark.hashCode());
		result = prime * result
				+ ((rdReportId == null) ? 0 : rdReportId.hashCode());
		result = prime * result
				+ ((rdWriterId == null) ? 0 : rdWriterId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportDepartment other = (ReportDepartment) obj;
		if (rdChargerId == null) {
			if (other.rdChargerId != null)
				return false;
		} else if (!rdChargerId.equals(other.rdChargerId))
			return false;
		if (rdFlowId == null) {
			if (other.rdFlowId != null)
				return false;
		} else if (!rdFlowId.equals(other.rdFlowId))
			return false;
		if (rdFlowstatus == null) {
			if (other.rdFlowstatus != null)
				return false;
		} else if (!rdFlowstatus.equals(other.rdFlowstatus))
			return false;
		if (rdId == null) {
			if (other.rdId != null)
				return false;
		} else if (!rdId.equals(other.rdId))
			return false;
		if (rdModifyDate == null) {
			if (other.rdModifyDate != null)
				return false;
		} else if (!rdModifyDate.equals(other.rdModifyDate))
			return false;
		if (rdRemark == null) {
			if (other.rdRemark != null)
				return false;
		} else if (!rdRemark.equals(other.rdRemark))
			return false;
		if (rdReportId == null) {
			if (other.rdReportId != null)
				return false;
		} else if (!rdReportId.equals(other.rdReportId))
			return false;
		if (rdWriterId == null) {
			if (other.rdWriterId != null)
				return false;
		} else if (!rdWriterId.equals(other.rdWriterId))
			return false;
		return true;
	}
    
	
}