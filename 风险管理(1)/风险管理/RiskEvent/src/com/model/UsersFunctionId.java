package com.model;

/**
 * UsersFunctionId entity. @author MyEclipse Persistence Tools
 */
public class UsersFunctionId  implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer srId;
	private Integer fmId;
	private String fmName;
	private String fmAction;
	private Integer fmCategory;
    private String images;
	// Constructors

    
	/** default constructor */
	public UsersFunctionId() {
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	/** minimal constructor */
	public UsersFunctionId(Integer srId, Integer fmId, String fmName) {
		this.srId = srId;
		this.fmId = fmId;
		this.fmName = fmName;
	}

	/** full constructor */
	public UsersFunctionId(Integer srId, Integer fmId, String fmName,
			String fmAction, Integer fmCategory) {
		this.srId = srId;
		this.fmId = fmId;
		this.fmName = fmName;
		this.fmAction = fmAction;
		this.fmCategory = fmCategory;
	}

	// Property accessors

	public Integer getSrId() {
		return this.srId;
	}

	public void setSrId(Integer srId) {
		this.srId = srId;
	}

	public Integer getfmId() {
		return this.fmId;
	}

	public void setfmId(Integer fmId) {
		this.fmId = fmId;
	}

	public String getFmName() {
		return this.fmName;
	}

	public void setFmName(String fmName) {
		this.fmName = fmName;
	}

	public String getFmAction() {
		return this.fmAction;
	}

	public void setFmAction(String fmAction) {
		this.fmAction = fmAction;
	}

	public Integer getFmCategory() {
		return this.fmCategory;
	}

	public void setFmCategory(Integer fmCategory) {
		this.fmCategory = fmCategory;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersFunctionId))
			return false;
		UsersFunctionId castOther = (UsersFunctionId) other;

		return ((this.getSrId() == castOther.getSrId()) || (this.getSrId() != null
				&& castOther.getSrId() != null && this.getSrId().equals(
				castOther.getSrId())))
				&& ((this.getfmId() == castOther.getfmId()) || (this.getfmId() != null
						&& castOther.getfmId() != null && this.getfmId()
						.equals(castOther.getfmId())))
				&& ((this.getFmName() == castOther.getFmName()) || (this
						.getFmName() != null
						&& castOther.getFmName() != null && this.getFmName()
						.equals(castOther.getFmName())))
				&& ((this.getFmAction() == castOther.getFmAction()) || (this
						.getFmAction() != null
						&& castOther.getFmAction() != null && this
						.getFmAction().equals(castOther.getFmAction())))
				&& ((this.getFmCategory() == castOther.getFmCategory()) || (this
						.getFmCategory() != null
						&& castOther.getFmCategory() != null && this
						.getFmCategory().equals(castOther.getFmCategory())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSrId() == null ? 0 : this.getSrId().hashCode());
		result = 37 * result
				+ (getfmId() == null ? 0 : this.getfmId().hashCode());
		result = 37 * result
				+ (getFmName() == null ? 0 : this.getFmName().hashCode());
		result = 37 * result
				+ (getFmAction() == null ? 0 : this.getFmAction().hashCode());
		result = 37
				* result
				+ (getFmCategory() == null ? 0 : this.getFmCategory()
						.hashCode());
		return result;
	}

}
