package com.dao;


import java.util.List;



import org.hibernate.LockMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.form.InputStandardform;
import com.model.InputStandard;
import com.model.RiskDimension;

/**
 * A data access object (DAO) providing persistence and search support for
 * Probability entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.Probability
 * @author MyEclipse Persistence Tools
 */

public class InputStandardDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(InputStandardDAO.class);
	// property constants
	public static final String RD_Id = "rdId";
	public static final String RD_depProperty = "rdDepProperty";
	public static final String RD_inputStandard = "rdInputStandard";
	
	protected void initDao() {
		// do nothing
	}
	//处理日志文件log
	public void save(RiskDimension transientInstance) {
		log.debug("saving RiskDimension instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void delete(RiskDimension persistentInstance) {
		log.debug("deleting RiskDimension instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InputStandard findById(java.lang.Integer id) {
		log.debug("getting InputStandard instance with id: " + id);
		try {
			InputStandard instance = (InputStandard) getHibernateTemplate().get(
					"com.model.InputStandard", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findAll() {
		
			String query="";
			try{
				query="from InputStandard";
				return getHibernateTemplate().find(query);
				
			}catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		
		
	}
	
	

	public static InputStandardDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (InputStandardDAO) ctx.getBean("RiskDimensionDAO");
	}
	
	public InputStandard findInputNeedbyProperty(String Deproperty) {
		try {
			String queryString = " from InputStandard where rdDepProperty = '"+Deproperty+"'";
			return (InputStandard) getHibernateTemplate().find(queryString).get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public InputStandard merge(InputStandard detachedInstance) {
		
		try {
			InputStandard result = (InputStandard) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	} 
}
