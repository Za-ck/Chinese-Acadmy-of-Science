package com.model;


public class UsersQueryView {

	private String userId;
	private String userName;
	private String userCellphone;
	private String userTel;
	private String userEmail;
	private String srName;
	private String depName;
	private String userRname;
	private String depId;
	private String srReid;
	private int srId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCellphone() {
		return userCellphone;
	}
	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getUserRname() {
		return userRname;
	}
	public void setUserRname(String userRname) {
		this.userRname = userRname;
	}
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	
	public String getSrReid() {
		return srReid;
	}
	public void setSrReid(String srReid) {
		this.srReid = srReid;
	}
	
	public int getSrId() {
		return srId;
	}
	public void setSrId(int srId) {
		this.srId = srId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depId == null) ? 0 : depId.hashCode());
		result = prime * result + ((depName == null) ? 0 : depName.hashCode());
		result = prime * result + srId;
		result = prime * result + ((srName == null) ? 0 : srName.hashCode());
		result = prime * result + ((srReid == null) ? 0 : srReid.hashCode());
		result = prime * result
				+ ((userCellphone == null) ? 0 : userCellphone.hashCode());
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userRname == null) ? 0 : userRname.hashCode());
		result = prime * result + ((userTel == null) ? 0 : userTel.hashCode());
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
		UsersQueryView other = (UsersQueryView) obj;
		if (depId == null) {
			if (other.depId != null)
				return false;
		} else if (!depId.equals(other.depId))
			return false;
		if (depName == null) {
			if (other.depName != null)
				return false;
		} else if (!depName.equals(other.depName))
			return false;
		if (srId != other.srId)
			return false;
		if (srName == null) {
			if (other.srName != null)
				return false;
		} else if (!srName.equals(other.srName))
			return false;
		if (srReid == null) {
			if (other.srReid != null)
				return false;
		} else if (!srReid.equals(other.srReid))
			return false;
		if (userCellphone == null) {
			if (other.userCellphone != null)
				return false;
		} else if (!userCellphone.equals(other.userCellphone))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRname == null) {
			if (other.userRname != null)
				return false;
		} else if (!userRname.equals(other.userRname))
			return false;
		if (userTel == null) {
			if (other.userTel != null)
				return false;
		} else if (!userTel.equals(other.userTel))
			return false;
		return true;
	}
}
