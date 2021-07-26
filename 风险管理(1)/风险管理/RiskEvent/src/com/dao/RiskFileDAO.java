package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.RiskFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskFile entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.RiskFile
 * @author MyEclipse Persistence Tools
 */

public class RiskFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskFileDAO.class);
	// property constants
	public static final String RISK_ID = "riskId";
	public static final String FILE_ID = "fileId";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskFile transientInstance) {
		log.debug("saving RiskFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskFile persistentInstance) {
		log.debug("deleting RiskFile instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskFile findById(java.lang.Integer id) {
		log.debug("getting RiskFile instance with id: " + id);
		try {
			RiskFile instance = (RiskFile) getHibernateTemplate().get(
					"com.model.RiskFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskFile instance) {
		log.debug("finding RiskFile instance by example");
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

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RiskFile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRiskId(Object riskId) {
		return findByProperty(RISK_ID, riskId);
	}

	public List findByFileId(Object fileId) {
		return findByProperty(FILE_ID, fileId);
	}

	public List findAll() {
		
		try {
			String queryString = "from RiskFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public RiskFile merge(RiskFile detachedInstance) {
		
		try {
			RiskFile result = (RiskFile) getHibernateTemplate().merge(
					detachedInstance);
			
			return result;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachDirty(RiskFile instance) {
		
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachClean(RiskFile instance) {
		log.debug("attaching clean RiskFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskFileDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskFileDAO) ctx.getBean("RiskFileDAO");
	}
	
	/*-------------------------------------------以下是自己写的方法--------------------------*/
	public void updateRiskId(String newRiskId, String oldRiskId) {
		
			
		final String queryString = "UPDATE RiskFile SET riskId = '" + newRiskId + "' WHERE riskId='" + oldRiskId + "'";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
			
		
	}
}