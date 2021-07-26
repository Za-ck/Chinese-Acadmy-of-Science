package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.LinkStaView2;
import com.model.LinkStaView4;

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

public class LinkStaView4DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LinkStaView4DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(LinkStaView4 transientInstance) {
		log.debug("saving LinkStaView4 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LinkStaView4 persistentInstance) {
		log.debug("deleting LinkStaView4 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkStaView4 findById(com.model.LinkStaView2Id id) {
		log.debug("getting LinkStaView4 instance with id: " + id);
		try {
			LinkStaView4 instance = (LinkStaView4) getHibernateTemplate().get(
					"com.model.LinkStaView4", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LinkStaView4 instance) {
		log.debug("finding LinkStaView4 instance by example");
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
		log.debug("finding LinkStaView4 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LinkStaView4 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all LinkStaView4 instances");
		try {
			String queryString = "from LinkStaView4";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkStaView4 merge(LinkStaView4 detachedInstance) {
		log.debug("merging LinkStaView4 instance");
		try {
			LinkStaView4 result = (LinkStaView4) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkStaView4 instance) {
		log.debug("attaching dirty LinkStaView4 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkStaView4 instance) {
		log.debug("attaching clean LinkStaView4 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkStaView4DAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LinkStaView4DAO) ctx.getBean("LinkStaView4DAO");
	}
	
	public List findByYearandQuar(String year, Integer quarter) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView4 as model where model.id.year= '"+year+"' and model.id.quarter= "+quarter+" order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	//ȫ��
	public List findByYear(String year) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from LinkStaView4 as model where model.id.year= '"+year+"' order by model.id.fileId asc";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	
}