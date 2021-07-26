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

import com.model.RiskEvaluateInternation;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskEvaluateInternation entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskEvaluateInternation
 * @author MyEclipse Persistence Tools
 */

public class RiskEvaluateInternationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskEvaluateInternationDAO.class);
	// property constants
	public static final String REI_NAME = "reiName";
	public static final String REI_CATEGORY = "reiCategory";
	public static final String REI_CATENAME = "reiCatename";
	public static final String REI_MARK = "reiMark";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskEvaluateInternation transientInstance) {
		log.debug("saving RiskEvaluateInternation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskEvaluateInternation persistentInstance) {
		log.debug("deleting RiskEvaluateInternation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskEvaluateInternation findById(java.lang.String id) {
		log.debug("getting RiskEvaluateInternation instance with id: " + id);
		try {
			RiskEvaluateInternation instance = (RiskEvaluateInternation) getHibernateTemplate()
					.get("com.model.RiskEvaluateInternation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskEvaluateInternation instance) {
		log.debug("finding RiskEvaluateInternation instance by example");
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
		log.debug("finding RiskEvaluateInternation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskEvaluateInternation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByReiName(Object reiName) {
		return findByProperty(REI_NAME, reiName);
	}

	public List findByReiCategory(Object reiCategory) {
		final String queryString = "SELECT COUNT(*) from RiskEvaluateInternation as rei where rei.reiCategory='"+reiCategory+"'";
		ServletActionContext.getRequest().getSession().setAttribute("riskCount",getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String result = session.createQuery(queryString).uniqueResult().toString();
				session.close();
				return result;
			}
		}));
		return findByProperty(REI_CATEGORY, reiCategory);
	}
	


	public List findByReiCatename(Object reiCatename) {
		return findByProperty(REI_CATENAME, reiCatename);
	}

	public List findByReiPrepare(Object reiPrepare) {
		return findByProperty(REI_MARK, reiPrepare);
	}

	public List findAll() {
		log.debug("finding all RiskEvaluateInternation instances");
		try {
			String queryString = "from RiskEvaluateInternation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	public RiskEvaluateInternation merge(
			RiskEvaluateInternation detachedInstance) {
		log.debug("merging RiskEvaluateInternation instance");
		try {
			RiskEvaluateInternation result = (RiskEvaluateInternation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskEvaluateInternation instance) {
		log.debug("attaching dirty RiskEvaluateInternation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskEvaluateInternation instance) {
		log.debug("attaching clean RiskEvaluateInternation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskEvaluateInternationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskEvaluateInternationDAO) ctx
				.getBean("RiskEvaluateInternationDAO");
	}
}