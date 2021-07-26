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

import com.model.RiskType;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.zn.model.RiskType
 * @author MyEclipse Persistence Tools
 */

public class RiskTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskTypeDAO.class);
	// property constants
	public static final String RT_NAME = "rtName";
	public static final String RT_REMARK = "rtRemark";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskType transientInstance) {
		log.debug("saving RiskType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskType persistentInstance) {
		log.debug("deleting RiskType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskType findById(java.lang.String id) {
		log.debug("getting RiskType instance with id: " + id);
		try {
			RiskType instance = (RiskType) getHibernateTemplate().get(
					"com.model.RiskType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskType instance) {
		log.debug("finding RiskType instance by example");
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
		log.debug("finding RiskType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRtName(Object rtName) {
		return findByProperty(RT_NAME, rtName);
	}

	public List findByRtRemark(Object rtRemark) {
		return findByProperty(RT_REMARK, rtRemark);
	}

	public List findAll() {
		log.debug("finding all RiskType instances");
		try {
			String queryString = "from RiskType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all RiskType instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from RiskType").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from RiskType")
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
	
	public RiskType merge(RiskType detachedInstance) {
		log.debug("merging RiskType instance");
		try {
			RiskType result = (RiskType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskType instance) {
		log.debug("attaching dirty RiskType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskType instance) {
		log.debug("attaching clean RiskType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskTypeDAO) ctx.getBean("RiskTypeDAO");
	}
}