package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Strategy;

/**
 * A data access object (DAO) providing persistence and search support for
 * Strategy entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.entity.Strategy
 * @author MyEclipse Persistence Tools
 */

public class StrategyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StrategyDAO.class);
	// property constants
	public static final String STRATEGY_NAME = "strategyName";
	public static final String CREATE_DATE = "createDate";
	public static final String CREATOR = "creator";

	protected void initDao() {
		// do nothing
	}

	public void save(Strategy transientInstance) {
		log.debug("saving Strategy instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Strategy persistentInstance) {
		log.debug("deleting Strategy instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Strategy findById(java.lang.Integer id) {
		log.debug("getting Strategy instance with id: " + id);
		try {
			Strategy instance = (Strategy) getHibernateTemplate().get(
					"com.model.Strategy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Strategy instance) {
		log.debug("finding Strategy instance by example");
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
		log.debug("finding Strategy instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Strategy as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStrategyName(Object strategyName) {
		return findByProperty(STRATEGY_NAME, strategyName);
	}

	public List findByCreateDate(Object createDate) {
		return findByProperty(CREATE_DATE, createDate);
	}

	public List findByCreator(Object creator) {
		return findByProperty(CREATOR, creator);
	}

	public List findAll() {
		log.debug("finding all Strategy instances");
		try {
			String queryString = "from Strategy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize) {
		try {		
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from Strategy").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from Strategy")
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
	public Strategy merge(Strategy detachedInstance) {
		log.debug("merging Strategy instance");
		try {
			Strategy result = (Strategy) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Strategy instance) {
		log.debug("attaching dirty Strategy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Strategy instance) {
		log.debug("attaching clean Strategy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StrategyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StrategyDAO) ctx.getBean("StrategyDAO");
	}
}