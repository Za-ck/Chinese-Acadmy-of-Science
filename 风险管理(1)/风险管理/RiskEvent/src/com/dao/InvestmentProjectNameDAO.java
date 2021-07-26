package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.InvestmentProjectName;
import com.model.Law;
import com.util.GenerateSequenceUtil;

/**
 * A data access object (DAO) providing persistence and search support for Law
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.Law
 * @author MyEclipse Persistence Tools
 */

public class InvestmentProjectNameDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(InvestmentProjectNameDAO.class);
	// property constants
	public static final String InvestmentProjectName_LEVEL = "InvestmentProjectNameLevel";
	public static final String InvestmentProjectName_DETAIL = "InvestmentProjectNameDetail";

	protected void initDao() {
		// do nothing
	}

	public void save(InvestmentProjectName transientInstance) {
		log.debug("saving InvestmentProjectName instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvestmentProjectName persistentInstance) {
		log.debug("deleting InvestmentProjectName instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvestmentProjectName findById(java.lang.Integer id) {
		log.debug("getting InvestmentProjectName instance with id: " + id);
		try {
			InvestmentProjectName instance = (InvestmentProjectName) getHibernateTemplate()
					.get("com.model.InvestmentProjectName", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvestmentProjectName instance) {
		log.debug("finding InvestmentProjectName instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value,String type) {
		log.debug("finding InvestmentProjectName instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from InvestmentProjectName as model where model."
					+ propertyName + "= ? and type='"+type+"'";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByInvestmentProjectNameLevel(Object InvestmentProjectNameLevel) {
//		return findByProperty(InvestmentProjectName_LEVEL, InvestmentProjectNameLevel);
//	}
//
//	public List findByInvestmentProjectNameDetail(Object InvestmentProjectNameDetail) {
//		return findByProperty(InvestmentProjectName_DETAIL, InvestmentProjectNameDetail);
//	}

	public List findAll() {
		log.debug("finding all InvestmentProjectName instances");
		try {
			String queryString = "from InvestmentProjectName";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAllGJ(final int offset, final int pageSize,final String projectNameString) {
		log.debug("finding all InvestmentProjectName instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from InvestmentProjectName where type='GJ' and name like '%"+projectNameString+"%'").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from InvestmentProjectName where type='GJ' and name like '%"+projectNameString+"%' order by id desc")
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllGC(final int offset, final int pageSize,final String projectNameString) {
		log.debug("finding all InvestmentProjectName instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from InvestmentProjectName where type='GC' and name like '%"+projectNameString+"%'").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from InvestmentProjectName where type='GC' and name like '%"+projectNameString+"%' order by id desc")
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public String insertProjectName(String projectName){
		if(findByProperty("name", projectName,"GJ").size()>0){
			return "否";
		}
		InvestmentProjectName investmentProjectName=new InvestmentProjectName();
		investmentProjectName.setName(projectName);
		investmentProjectName.setType("GJ");
		save(investmentProjectName);
		return "是";
	}
	
	public String insertContractProjectName(String projectName){
		if(findByProperty("name", projectName,"GC").size()>0){
			return "否";
		}
		InvestmentProjectName investmentProjectName=new InvestmentProjectName();
		investmentProjectName.setName(projectName);
		investmentProjectName.setType("GC");
		save(investmentProjectName);
		return "是";
	}
	
//	public String insertProjectName(String projectName){
//		if(findByProperty("name", projectName).size()>0){
//			return "否";
//		}
//		InvestmentProjectName investmentProjectName=new InvestmentProjectName();
//		investmentProjectName.setId(GenerateSequenceUtil.generateInvestProjectNameId());
//		investmentProjectName.setName(projectName);
//		investmentProjectName.setType("GJ");
//		save(investmentProjectName);
//		return "是";
//	}

}