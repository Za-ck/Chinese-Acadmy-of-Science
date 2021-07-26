package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ReportFlowRuleInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportFlowRuleInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportFlowRuleInfo
 * @author MyEclipse Persistence Tools
 */

public class ReportFlowRuleInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportFlowRuleInfoDAO.class);
	// property constants
	public static final String FR_FLOW_ID = "frFlowId";
	public static final String FR_FLOW_NAME = "frFlowName";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportFlowRuleInfo transientInstance) {
		log.debug("saving ReportFlowRuleInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportFlowRuleInfo persistentInstance) {
		log.debug("deleting ReportFlowRuleInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportFlowRuleInfo findById(java.lang.Integer id) {
		log.debug("getting ReportFlowRuleInfo instance with id: " + id);
		try {
			ReportFlowRuleInfo instance = (ReportFlowRuleInfo) getHibernateTemplate()
					.get("com.model.ReportFlowRuleInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportFlowRuleInfo instance) {
		log.debug("finding ReportFlowRuleInfo instance by example");
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
		log.debug("finding ReportFlowRuleInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportFlowRuleInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFrFlowId(Object frFlowId) {
		return findByProperty(FR_FLOW_ID, frFlowId);
	}

	public List findByFrFlowName(Object frFlowName) {
		return findByProperty(FR_FLOW_NAME, frFlowName);
	}

	public List findAll() {
		log.debug("finding all ReportFlowRuleInfo instances");
		try {
			String queryString = "from ReportFlowRuleInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportFlowRuleInfo merge(ReportFlowRuleInfo detachedInstance) {
		log.debug("merging ReportFlowRuleInfo instance");
		try {
			ReportFlowRuleInfo result = (ReportFlowRuleInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportFlowRuleInfo instance) {
		log.debug("attaching dirty ReportFlowRuleInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportFlowRuleInfo instance) {
		log.debug("attaching clean ReportFlowRuleInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportFlowRuleInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportFlowRuleInfoDAO) ctx.getBean("ReportFlowRuleInfoDAO");
	}
}