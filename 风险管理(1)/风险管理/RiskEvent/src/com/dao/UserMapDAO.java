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

import com.model.UserMap;

/**
 * A data access object (DAO) providing persistence and search support for
 * Client entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Client
 * @author MyEclipse Persistence Tools
 */

public class UserMapDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserMapDAO.class);
	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserMap transientInstance) {
		
		try {
			getHibernateTemplate().save(transientInstance);
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	public void delete(UserMap persistentInstance) {
		log.debug("deleting UserMap instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserMap findById(java.lang.Integer id) {
		log.debug("getting UserMap instance with id: " + id);
		try {
			UserMap instance = (UserMap) getHibernateTemplate().get(
					"com.model.UserMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserMap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserMap as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserMap instances");
		try {
			String queryString = "from UserMap";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all UserMap", re);
			throw re;
		}
	}

	public UserMap merge(UserMap detachedInstance) {
		log.debug("merging UserMap instance");
		try {
			UserMap result = (UserMap) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
	

	
}