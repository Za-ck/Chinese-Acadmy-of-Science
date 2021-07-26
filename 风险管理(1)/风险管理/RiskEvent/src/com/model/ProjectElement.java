package com.model;

/**
 * ProjectElement entity. @author MyEclipse Persistence Tools
 */

public class ProjectElement {

	// Fields

	private Integer peId;
	private String peProjectid;
	private String peEvaluateid;
	private String peProbablity;   //发生可能性
	private String peProdegree;    //可能性描述
	private String peImpactdegree;
	private String peCateimpact;
	private String peCategory;  //父节点编号
	private String peEvaluatename;  //父节点编号
	private String peCategoryname;  //父节点编号

	// Constructors

	/** default constructor */
	public ProjectElement() {
	}

	/** minimal constructor */
	public ProjectElement(Integer peId, String peProjectid, String peEvaluateid,
			String peProbablity, String peImpactdegree) {
		this.peId = peId;
		this.peProjectid = peProjectid;
		this.peEvaluateid = peEvaluateid;
		this.peProbablity = peProbablity;
		this.peImpactdegree = peImpactdegree;
	}

	/** full constructor */
	public ProjectElement(Integer peId, String peProjectid, String peEvaluateid,
			String peProbablity, String peProdegree, String peImpactdegree,
			String peCateimpact,String peCategory,String peEvaluatename,String peCategoryname) {
		this.peId = peId;
		this.peProjectid = peProjectid;
		this.peEvaluateid = peEvaluateid;
		this.peProbablity = peProbablity;
		this.peProdegree = peProdegree;
		this.peImpactdegree = peImpactdegree;
		this.peCateimpact = peCateimpact;
		this.peCategory = peCategory;
		this.peEvaluatename = peEvaluatename;
		this.peCategoryname = peCategoryname;
	}

	// Property accessors

	public Integer getPeId() {
		return this.peId;
	}

	public void setPeId(Integer peId) {
		this.peId = peId;
	}

	public String getPeProjectid() {
		return this.peProjectid;
	}

	public void setPeProjectid(String peProjectid) {
		this.peProjectid = peProjectid;
	}

	public String getPeEvaluateid() {
		return this.peEvaluateid;
	}

	public void setPeEvaluateid(String peEvaluateid) {
		this.peEvaluateid = peEvaluateid;
	}

	public String getPeProbablity() {
		return this.peProbablity;
	}

	public void setPeProbablity(String peProbablity) {
		this.peProbablity = peProbablity;
	}

	public String getPeProdegree() {
		return this.peProdegree;
	}

	public void setPeProdegree(String peProdegree) {
		this.peProdegree = peProdegree;
	}

	public String getPeImpactdegree() {
		return this.peImpactdegree;
	}

	public void setPeImpactdegree(String peImpactdegree) {
		this.peImpactdegree = peImpactdegree;
	}

	public String getPeCateimpact() {
		return peCateimpact;
	}

	public void setPeCateimpact(String peCateimpact) {
		this.peCateimpact = peCateimpact;
	}

	public String getPeCategory() {
		return peCategory;
	}

	public void setPeCategory(String peCategory) {
		this.peCategory = peCategory;
	}

	public String getPeEvaluatename() {
		return peEvaluatename;
	}

	public void setPeEvaluatename(String peEvaluatename) {
		this.peEvaluatename = peEvaluatename;
	}

	public String getPeCategoryname() {
		return peCategoryname;
	}

	public void setPeCategoryname(String peCategoryname) {
		this.peCategoryname = peCategoryname;
	}
	
	


}