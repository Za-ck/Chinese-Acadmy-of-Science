package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.LinkStaView;

/**
 * A data access object (DAO) providing persistence and search support for
 * LinkStaView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.LinkStaView
 * @author MyEclipse Persistence Tools
 */

public class LinkStaViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LinkStaViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(LinkStaView transientInstance) {
		log.debug("saving LinkStaView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LinkStaView persistentInstance) {
		log.debug("deleting LinkStaView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkStaView findById(com.model.LinkStaViewId id) {
		log.debug("getting LinkStaView instance with id: " + id);
		try {
			LinkStaView instance = (LinkStaView) getHibernateTemplate().get(
					"com.model.LinkStaView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LinkStaView instance) {
		log.debug("finding LinkStaView instance by example");
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
		log.debug("finding LinkStaView instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LinkStaView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all LinkStaView instances");
		try {
			String queryString = "from LinkStaView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkStaView merge(LinkStaView detachedInstance) {
		log.debug("merging LinkStaView instance");
		try {
			LinkStaView result = (LinkStaView) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkStaView instance) {
		log.debug("attaching dirty LinkStaView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkStaView instance) {
		log.debug("attaching clean LinkStaView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkStaViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LinkStaViewDAO) ctx.getBean("LinkStaViewDAO");
	}
	
	public List findDepNum(String queryString) {
		log.debug("finding all LinkStaView instances");
		try {
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByYearandQuar_part(String year, Integer quarter,String dep) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView as model where model.id.year= '"+year+"' and model.id.quarter= "+quarter+" and model.id.reIndep='"+dep+"' order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	//È«Äê
	public List findByYear_part(String year,String dep) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView as model where model.id.year= '"+year+"'  and model.id.reIndep='"+dep+"' order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
}