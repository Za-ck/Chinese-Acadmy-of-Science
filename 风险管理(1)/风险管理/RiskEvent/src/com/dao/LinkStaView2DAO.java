package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.LinkStaView2;

/**
 * A data access object (DAO) providing persistence and search support for
 * LinkStaView2 entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.LinkStaView2
 * @author MyEclipse Persistence Tools
 */

public class LinkStaView2DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LinkStaView2DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(LinkStaView2 transientInstance) {
		log.debug("saving LinkStaView2 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LinkStaView2 persistentInstance) {
		log.debug("deleting LinkStaView2 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkStaView2 findById(com.model.LinkStaView2Id id) {
		log.debug("getting LinkStaView2 instance with id: " + id);
		try {
			LinkStaView2 instance = (LinkStaView2) getHibernateTemplate().get(
					"com.model.LinkStaView2", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LinkStaView2 instance) {
		log.debug("finding LinkStaView2 instance by example");
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
		log.debug("finding LinkStaView2 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LinkStaView2 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all LinkStaView2 instances");
		try {
			String queryString = "from LinkStaView2";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkStaView2 merge(LinkStaView2 detachedInstance) {
		log.debug("merging LinkStaView2 instance");
		try {
			LinkStaView2 result = (LinkStaView2) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkStaView2 instance) {
		log.debug("attaching dirty LinkStaView2 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkStaView2 instance) {
		log.debug("attaching clean LinkStaView2 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkStaView2DAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LinkStaView2DAO) ctx.getBean("LinkStaView2DAO");
	}
	
	public List findByYearandQuar(String year, Integer quarter) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView2 as model where model.id.year= '"+year+"' and model.id.quarter= "+quarter+" order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	//È«Äê
	public List findByYear(String year) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView2 as model where model.id.year= '"+year+"' order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	
}