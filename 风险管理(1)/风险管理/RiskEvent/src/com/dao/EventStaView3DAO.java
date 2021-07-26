package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.EventStaView;
import com.model.EventStaView3;

/**
 * A data access object (DAO) providing persistence and search support for
 * EventStaView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.zn.model.EventStaView
 * @author MyEclipse Persistence Tools
 */

public class EventStaView3DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(EventStaView3DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(EventStaView3 transientInstance) {
		log.debug("saving EventStaView3 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventStaView3 persistentInstance) {
		log.debug("deleting EventStaView3 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventStaView3 findById(com.model.EventStaViewId id) {
		log.debug("getting EventStaView3 instance with id: " + id);
		try {
			EventStaView3 instance = (EventStaView3) getHibernateTemplate().get(
					"com.model.EventStaView3", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EventStaView3 instance) {
		log.debug("finding EventStaView3 instance by example");
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
		log.debug("finding EventStaView3 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EventStaView3 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EventStaView3 instances");
		try {
			String queryString = "from EventStaView3";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EventStaView3 merge(EventStaView3 detachedInstance) {
		log.debug("merging EventStaView3 instance");
		try {
			EventStaView3 result = (EventStaView3) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventStaView3 instance) {
		log.debug("attaching dirty EventStaView3 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventStaView3 instance) {
		log.debug("attaching clean EventStaView3 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventStaView3DAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EventStaView3DAO) ctx.getBean("EventStaView3DAO");
	}
	
	public List findDepNum(String queryString) {
		log.debug("finding all EventStaView3 instances");
		try {
		//	String queryString = "from EventStaView where ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}