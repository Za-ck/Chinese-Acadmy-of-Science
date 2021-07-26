package com.model;

/**
 * RiskEventDepQueryViewId entity. @author MyEclipse Persistence Tools
 */

public class RiskEventDepQueryView {

	// Fields

	private String reId;
	private String reType;
	private String reRiskId;
	private String reEventId;
	private String reDetail;
	private String reEventname;
	private String reOccurdate;
	private String reState;
	private String reIndep;
	private String reDate;
	private String reCreator;
	private String reModifydate;
	private String reModifier;
	private String reVerifier;
	private String reAct;
	private String reRemark;
	private String depName;

	// Constructors

	/** default constructor */
	public RiskEventDepQueryView() {
	}

	/** minimal constructor */
	public RiskEventDepQueryView(String reId, String reType, String reRiskId,
			String reEventId, String reState, String reIndep, String reDate,
			String reCreator, String depName) {
		this.reId = reId;
		this.reType = reType;
		this.reRiskId = reRiskId;
		this.reEventId = reEventId;
		this.reState = reState;
		this.reIndep = reIndep;
		this.reDate = reDate;
		this.reCreator = reCreator;
		this.depName = depName;
	}

	/** full constructor */
	public RiskEventDepQueryView(String reId, String reType, String reRiskId,
			String reEventId, String reDetail, String reEventname,
			String reOccurdate, String reState, String reIndep, String reDate,
			String reCreator, String reModifydate, String reModifier,
			String reVerifier, String reAct, String reRemark, String depName) {
		this.reId = reId;
		this.reType = reType;
		this.reRiskId = reRiskId;
		this.reEventId = reEventId;
		this.reDetail = reDetail;
		this.reEventname = reEventname;
		this.reOccurdate = reOccurdate;
		this.reState = reState;
		this.reIndep = reIndep;
		this.reDate = reDate;
		this.reCreator = reCreator;
		this.reModifydate = reModifydate;
		this.reModifier = reModifier;
		this.reVerifier = reVerifier;
		this.reAct = reAct;
		this.reRemark = reRemark;
		this.depName = depName;
	}

	// Property accessors

	public String getReId() {
		return this.reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getReType() {
		return this.reType;
	}

	public void setReType(String reType) {
		this.reType = reType;
	}

	public String getReRiskId() {
		return this.reRiskId;
	}

	public void setReRiskId(String reRiskId) {
		this.reRiskId = reRiskId;
	}

	public String getReEventId() {
		return this.reEventId;
	}

	public void setReEventId(String reEventId) {
		this.reEventId = reEventId;
	}

	public String getReDetail() {
		return this.reDetail;
	}

	public void setReDetail(String reDetail) {
		this.reDetail = reDetail;
	}

	public String getReEventname() {
		return this.reEventname;
	}

	public void setReEventname(String reEventname) {
		this.reEventname = reEventname;
	}

	public String getReOccurdate() {
		return this.reOccurdate;
	}

	public void setReOccurdate(String reOccurdate) {
		this.reOccurdate = reOccurdate;
	}

	public String getReState() {
		return this.reState;
	}

	public void setReState(String reState) {
		this.reState = reState;
	}

	public String getReIndep() {
		return this.reIndep;
	}

	public void setReIndep(String reIndep) {
		this.reIndep = reIndep;
	}

	public String getReDate() {
		return this.reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getReCreator() {
		return this.reCreator;
	}

	public void setReCreator(String reCreator) {
		this.reCreator = reCreator;
	}

	public String getReModifydate() {
		return this.reModifydate;
	}

	public void setReModifydate(String reModifydate) {
		this.reModifydate = reModifydate;
	}

	public String getReModifier() {
		return this.reModifier;
	}

	public void setReModifier(String reModifier) {
		this.reModifier = reModifier;
	}

	public String getReVerifier() {
		return this.reVerifier;
	}

	public void setReVerifier(String reVerifier) {
		this.reVerifier = reVerifier;
	}

	public String getReAct() {
		return this.reAct;
	}

	public void setReAct(String reAct) {
		this.reAct = reAct;
	}

	public String getReRemark() {
		return this.reRemark;
	}

	public void setReRemark(String reRemark) {
		this.reRemark = reRemark;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RiskEventDepQueryView))
			return false;
		RiskEventDepQueryView castOther = (RiskEventDepQueryView) other;

		return ((this.getReId() == castOther.getReId()) || (this.getReId() != null
				&& castOther.getReId() != null && this.getReId().equals(
				castOther.getReId())))
				&& ((this.getReType() == castOther.getReType()) || (this
						.getReType() != null
						&& castOther.getReType() != null && this.getReType()
						.equals(castOther.getReType())))
				&& ((this.getReRiskId() == castOther.getReRiskId()) || (this
						.getReRiskId() != null
						&& castOther.getReRiskId() != null && this
						.getReRiskId().equals(castOther.getReRiskId())))
				&& ((this.getReEventId() == castOther.getReEventId()) || (this
						.getReEventId() != null
						&& castOther.getReEventId() != null && this
						.getReEventId().equals(castOther.getReEventId())))
				&& ((this.getReDetail() == castOther.getReDetail()) || (this
						.getReDetail() != null
						&& castOther.getReDetail() != null && this
						.getReDetail().equals(castOther.getReDetail())))
				&& ((this.getReEventname() == castOther.getReEventname()) || (this
						.getReEventname() != null
						&& castOther.getReEventname() != null && this
						.getReEventname().equals(castOther.getReEventname())))
				&& ((this.getReOccurdate() == castOther.getReOccurdate()) || (this
						.getReOccurdate() != null
						&& castOther.getReOccurdate() != null && this
						.getReOccurdate().equals(castOther.getReOccurdate())))
				&& ((this.getReState() == castOther.getReState()) || (this
						.getReState() != null
						&& castOther.getReState() != null && this.getReState()
						.equals(castOther.getReState())))
				&& ((this.getReIndep() == castOther.getReIndep()) || (this
						.getReIndep() != null
						&& castOther.getReIndep() != null && this.getReIndep()
						.equals(castOther.getReIndep())))
				&& ((this.getReDate() == castOther.getReDate()) || (this
						.getReDate() != null
						&& castOther.getReDate() != null && this.getReDate()
						.equals(castOther.getReDate())))
				&& ((this.getReCreator() == castOther.getReCreator()) || (this
						.getReCreator() != null
						&& castOther.getReCreator() != null && this
						.getReCreator().equals(castOther.getReCreator())))
				&& ((this.getReModifydate() == castOther.getReModifydate()) || (this
						.getReModifydate() != null
						&& castOther.getReModifydate() != null && this
						.getReModifydate().equals(castOther.getReModifydate())))
				&& ((this.getReModifier() == castOther.getReModifier()) || (this
						.getReModifier() != null
						&& castOther.getReModifier() != null && this
						.getReModifier().equals(castOther.getReModifier())))
				&& ((this.getReVerifier() == castOther.getReVerifier()) || (this
						.getReVerifier() != null
						&& castOther.getReVerifier() != null && this
						.getReVerifier().equals(castOther.getReVerifier())))
				&& ((this.getReAct() == castOther.getReAct()) || (this
						.getReAct() != null
						&& castOther.getReAct() != null && this.getReAct()
						.equals(castOther.getReAct())))
				&& ((this.getReRemark() == castOther.getReRemark()) || (this
						.getReRemark() != null
						&& castOther.getReRemark() != null && this
						.getReRemark().equals(castOther.getReRemark())))
				&& ((this.getDepName() == castOther.getDepName()) || (this
						.getDepName() != null
						&& castOther.getDepName() != null && this.getDepName()
						.equals(castOther.getDepName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getReId() == null ? 0 : this.getReId().hashCode());
		result = 37 * result
				+ (getReType() == null ? 0 : this.getReType().hashCode());
		result = 37 * result
				+ (getReRiskId() == null ? 0 : this.getReRiskId().hashCode());
		result = 37 * result
				+ (getReEventId() == null ? 0 : this.getReEventId().hashCode());
		result = 37 * result
				+ (getReDetail() == null ? 0 : this.getReDetail().hashCode());
		result = 37
				* result
				+ (getReEventname() == null ? 0 : this.getReEventname()
						.hashCode());
		result = 37
				* result
				+ (getReOccurdate() == null ? 0 : this.getReOccurdate()
						.hashCode());
		result = 37 * result
				+ (getReState() == null ? 0 : this.getReState().hashCode());
		result = 37 * result
				+ (getReIndep() == null ? 0 : this.getReIndep().hashCode());
		result = 37 * result
				+ (getReDate() == null ? 0 : this.getReDate().hashCode());
		result = 37 * result
				+ (getReCreator() == null ? 0 : this.getReCreator().hashCode());
		result = 37
				* result
				+ (getReModifydate() == null ? 0 : this.getReModifydate()
						.hashCode());
		result = 37
				* result
				+ (getReModifier() == null ? 0 : this.getReModifier()
						.hashCode());
		result = 37
				* result
				+ (getReVerifier() == null ? 0 : this.getReVerifier()
						.hashCode());
		result = 37 * result
				+ (getReAct() == null ? 0 : this.getReAct().hashCode());
		result = 37 * result
				+ (getReRemark() == null ? 0 : this.getReRemark().hashCode());
		result = 37 * result
				+ (getDepName() == null ? 0 : this.getDepName().hashCode());
		return result;
	}

}