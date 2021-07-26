package com.model;



/**
 * ReportTask entity. @author MyEclipse Persistence Tools
 */

public class ReportTask implements Cloneable{


    // Fields    

     private int retId;
     private String retTaskId;
     private String retReportId;
     private String retReportName;
     private String retFlowId;
     private String retFlowStatus;
     private String retDepId;
     private String retUserId;
     private String retUserName;
     private String retView;
     private String retState;
     private String retDepName;
     private String retNextStatus;
     private String retProcessTime;
     private String retLastTime;
     private String retPreTaskId;
     private String retFormId;
     private int retModifyFlag;
     private int retTaskFlag;

    // Constructors

    /** default constructor */
    public ReportTask() {
    	
    }
    
    public Integer getRetId() {
		return retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public String getRetTaskId() {
		return retTaskId;
	}

	public void setRetTaskId(String retTaskId) {
		this.retTaskId = retTaskId;
	}

	public String getRetReportId() {
        return this.retReportId;
    }

	public void setRetReportId(String retReportId) {
        this.retReportId = retReportId;
    }

    public String getRetReportName() {
        return this.retReportName;
    }
    
    public void setRetReportName(String retReportName) {
        this.retReportName = retReportName;
    }

    public String getRetFlowId() {
        return this.retFlowId;
    }
    
    public void setRetFlowId(String retFlowId) {
        this.retFlowId = retFlowId;
    }

    public String getRetFlowStatus() {
        return this.retFlowStatus;
    }
    
    public void setRetFlowStatus(String retFlowStatus) {
        this.retFlowStatus = retFlowStatus;
    }

    public String getRetDepId() {
        return this.retDepId;
    }
    
    public void setRetDepId(String retDepId) {
        this.retDepId = retDepId;
    }

    public String getRetUserId() {
        return this.retUserId;
    }
    
    public void setRetUserId(String retUserId) {
        this.retUserId = retUserId;
    }

    public String getRetUserName() {
        return this.retUserName;
    }
    
    public void setRetUserName(String retUserName) {
        this.retUserName = retUserName;
    }

    public String getRetView() {
        return this.retView;
    }
    
    public void setRetView(String retView) {
        this.retView = retView;
    }

    public String getRetState() {
        return this.retState;
    }
    
    public void setRetState(String retState) {
        this.retState = retState;
    }

    public String getRetDepName() {
        return this.retDepName;
    }
    
    public void setRetDepName(String retDepName) {
        this.retDepName = retDepName;
    }

    public String getRetNextStatus() {
        return this.retNextStatus;
    }
    
    public void setRetNextStatus(String retNextStatus) {
        this.retNextStatus = retNextStatus;
    }

    public String getRetProcessTime() {
        return this.retProcessTime;
    }
    
    public void setRetProcessTime(String retProcessTime) {
        this.retProcessTime = retProcessTime;
    }

	public String getRetLastTime() {
		return retLastTime;
	}

	public void setRetLastTime(String retLastTime) {
		this.retLastTime = retLastTime;
	}

	public String getRetPreTaskId() {
		return retPreTaskId;
	}

	public void setRetPreTaskId(String retPreTaskId) {
		this.retPreTaskId = retPreTaskId;
	}

	public String getRetFormId() {
		return retFormId;
	}

	public void setRetFormId(String retFormId) {
		this.retFormId = retFormId;
	}

	public Integer getRetModifyFlag() {
		return retModifyFlag;
	}

	public void setRetModifyFlag(Integer retModifyFlag) {
		this.retModifyFlag = retModifyFlag;
	}

	public Integer getRetTaskFlag() {
		return retTaskFlag;
	}

	public void setRetTaskFlag(Integer retTaskFlag) {
		this.retTaskFlag = retTaskFlag;
	}
    
	public Object clone()   
    {   
       Object o=null;   
       try   
        {   
            o=super.clone();   
        }   
       catch(CloneNotSupportedException e)   
        {   
            e.printStackTrace(); 
        }   
       return o;   
    }  
}