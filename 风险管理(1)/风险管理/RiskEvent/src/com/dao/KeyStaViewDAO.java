package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.KeyStaView;

/**
 * A data access object (DAO) providing persistence and search support for
 * KeyStaView entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.KeyStaView
 * @author MyEclipse Persistence Tools
 */

public class KeyStaViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(KeyStaViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(KeyStaView transientInstance) {
		log.debug("saving KeyStaView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KeyStaView persistentInstance) {
		log.debug("deleting KeyStaView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KeyStaView findById(com.model.KeyStaViewId id) {
		log.debug("getting KeyStaView instance with id: " + id);
		try {
			KeyStaView instance = (KeyStaView) getHibernateTemplate().get(
					"com.model.KeyStaView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KeyStaView instance) {
		log.debug("finding KeyStaView instance by example");
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
		log.debug("finding KeyStaView instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KeyStaView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all KeyStaView instances");
		try {
			String queryString = "from KeyStaView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KeyStaView merge(KeyStaView detachedInstance) {
		log.debug("merging KeyStaView instance");
		try {
			KeyStaView result = (KeyStaView) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KeyStaView instance) {
		log.debug("attaching dirty KeyStaView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KeyStaView instance) {
		log.debug("attaching clean KeyStaView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KeyStaViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KeyStaViewDAO) ctx.getBean("KeyStaViewDAO");
	}
	
	public List findByYear(String year) {
		log.debug("finding all KeyStaView instances");
		try {
			String queryString = "from KeyStaView as model where model.year='"+year+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	//根据年份查找有记录的部门
	public List findDep(final String year) {
		
		log.debug("finding all KeyStaView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyStaView as model where model.year ='"+year+"' order by model.depQueue";
					 return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	//根据年份查找当前部门是否有记录
	public List findDep_part(final String year,String dep) {
		log.debug("finding all KeyStaView instances");
		try {
			 String queryString = "select distinct reIndep,depName from KeyStaView as model where model.year ='"+year+"' and model.reIndep='"+dep+"'";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	//根据部门查找该部门的风险
	public List findRisk(final String year,final String dep) {
		log.debug("finding all KeyStaView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue from KeyStaView as model where model.reIndep = '"+dep+"' and model.year ='"+year+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	//根据部门、风险查找相应的记录
	public List findDetail( String year, String dep,String riskId ) {
		
		
		log.debug("finding all KeyStaView instances");
		try {
			String queryString = "from KeyStaView as model where model.year='"+year+"' and model.reIndep='"+dep+"' and  model.riskId='"+riskId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	//根据部门、年份查找相应的记录
	public List findByYearDep(String year,String dep) {
		log.debug("finding all KeyStaView instances");
		try {
			String queryString = "from KeyStaView as model where model.year='"+year+"' and model.reIndep='"+dep+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}