package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.UserStrategy;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserStrategy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.entity.UserStrategy
 * @author MyEclipse Persistence Tools
 */

public class UserStrategyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserStrategyDAO.class);
	// property constants
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(UserStrategy transientInstance) {
		
		try {
			getHibernateTemplate().save(transientInstance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void delete(UserStrategy persistentInstance) {
		
		try {
			getHibernateTemplate().delete(persistentInstance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public UserStrategy findById(java.lang.Integer id) {
		
		try {
			UserStrategy instance = (UserStrategy) getHibernateTemplate().get(
					"com.model.UserStrategy", id);
			return instance;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public List findByExample(UserStrategy instance) {
		log.debug("finding UserStrategy instance by example");
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
		log.debug("finding UserStrategy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UserStrategy as model where model."
					+ propertyName + "= ?";
			//System.out.println(queryString);
			return getHibernateTemplate().find(queryString,value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByProperty(String queryString) {
		log.debug("finding UserStrategy instance with property: "+queryString);
		try {		
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all UserStrategy instances");
		try {
			String queryString = "from UserStrategy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserStrategy merge(UserStrategy detachedInstance) {
		log.debug("merging UserStrategy instance");
		try {
			UserStrategy result = (UserStrategy) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserStrategy instance) {
		log.debug("attaching dirty UserStrategy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserStrategy instance) {
		log.debug("attaching clean UserStrategy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserStrategyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserStrategyDAO) ctx.getBean("UserStrategyDAO");
	}
	
	
	public void deleteByUserDep(String depId) {
		
		final String queryString2 = "DELETE FROM UserStrategy where department.depId='" + depId + "'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString2).executeUpdate();
				session.close();
				return result;
			}
			
		});
	}
	
}