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

import com.model.Safe;

/**
 * A data access object (DAO) providing persistence and search support for Safe
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.Safe
 * @author MyEclipse Persistence Tools
 */

public class SafeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(SafeDAO.class);
	// property constants
	public static final String SAF_LEVEL = "safLevel";
	public static final String SAF_SAFETY = "safSafety";
	public static final String SAF_ENVIRONMENT = "safEnvironment";

	protected void initDao() {
		// do nothing
	}

	public void save(Safe transientInstance) {
		log.debug("saving Safe instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Safe persistentInstance) {
		log.debug("deleting Safe instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Safe findById(java.lang.Integer id) {
		log.debug("getting Safe instance with id: " + id);
		try {
			Safe instance = (Safe) getHibernateTemplate().get("com.model.Safe",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Safe instance) {
		log.debug("finding Safe instance by example");
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
		log.debug("finding Safe instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Safe as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySafLevel(Object safLevel) {
		return findByProperty(SAF_LEVEL, safLevel);
	}

	public List findBySafSafety(Object safSafety) {
		return findByProperty(SAF_SAFETY, safSafety);
	}

	public List findBySafEnvironment(Object safEnvironment) {
		return findByProperty(SAF_ENVIRONMENT, safEnvironment);
	}

	public List findAll() {
		log.debug("finding all Safe instances");
		try {
			String queryString = "from Safe";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all Safe instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from Safe").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from Safe")
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

	public Safe merge(Safe detachedInstance) {
		log.debug("merging Safe instance");
		try {
			Safe result = (Safe) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Safe instance) {
		log.debug("attaching dirty Safe instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Safe instance) {
		log.debug("attaching clean Safe instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SafeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SafeDAO) ctx.getBean("SafeDAO");
	}
}