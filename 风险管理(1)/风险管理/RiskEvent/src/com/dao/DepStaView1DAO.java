package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.DepStaView;
import com.model.DepStaView1;
import com.model.DepStaViewId;

/**
 * A data access object (DAO) providing persistence and search support for
 * DepStaView entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.DepStaView
 * @author MyEclipse Persistence Tools
 */

public class DepStaView1DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DepStaView1DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(DepStaView1 transientInstance) {
		log.debug("saving DepStaView1 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DepStaView1 persistentInstance) {
		log.debug("deleting DepStaView1 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DepStaView1 findById(com.model.DepStaViewId id) {
		log.debug("getting DepStaView1 instance with id: " + id);
		try {
			DepStaView1 instance = (DepStaView1) getHibernateTemplate().get(
					"com.model.DepStaView1", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DepStaView1 instance) {
		log.debug("finding DepStaView1 instance by example");
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
		log.debug("finding DepStaView1 instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DepStaView1 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all DepStaView1 instances");
		try {
			String queryString = "from DepStaView1";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DepStaView1 merge(DepStaView1 detachedInstance) {
		log.debug("merging DepStaView1 instance");
		try {
			DepStaView1 result = (DepStaView1) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DepStaView1 instance) {
		log.debug("attaching dirty DepStaView1 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DepStaView1 instance) {
		log.debug("attaching clean DepStaView1 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DepStaView1DAO getFromApplicationContext(ApplicationContext ctx) {
		return (DepStaView1DAO) ctx.getBean("DepStaView1DAO");
	}
	
	public List findByYearandQuar(String year, Integer quarter) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from DepStaView1 as model where model.id.year= '"+year+"' and model.id.quarter= "+quarter+" order by model.id.reIndep";
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
			String queryString = "from DepStaView1 as model where model.id.year= '"+year+"' order by model.id.reIndep";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	public List findByDepandRT(String depname, String rtname,String year, Integer quarter) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from DepStaView1 as model where model.id.reIndep= '"+depname+"' and model.id.rtName= '"+rtname+"' and model.id.year= '"+year+"' and model.id.quarter= "+quarter+" order by model.id.reIndep";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
}