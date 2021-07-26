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

import com.model.Operation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Operation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Operation
 * @author MyEclipse Persistence Tools
 */

public class OperationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(OperationDAO.class);
	// property constants
	public static final String OPE_LEVEL = "opeLevel";
	public static final String OPE_DETAIL = "opeDetail";

	protected void initDao() {
		// do nothing
	}

	public void save(Operation transientInstance) {
		log.debug("saving Operation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Operation persistentInstance) {
		log.debug("deleting Operation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Operation findById(java.lang.Integer id) {
		log.debug("getting Operation instance with id: " + id);
		try {
			Operation instance = (Operation) getHibernateTemplate().get(
					"com.model.Operation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Operation instance) {
		log.debug("finding Operation instance by example");
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
		log.debug("finding Operation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Operation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOpeLevel(Object opeLevel) {
		return findByProperty(OPE_LEVEL, opeLevel);
	}

	public List findByOpeDetail(Object opeDetail) {
		return findByProperty(OPE_DETAIL, opeDetail);
	}

	public List findAll() {
		log.debug("finding all Operation instances");
		try {
			String queryString = "from Operation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all Operation instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from Operation").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from Operation")
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
	
	public Operation merge(Operation detachedInstance) {
		log.debug("merging Operation instance");
		try {
			Operation result = (Operation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Operation instance) {
		log.debug("attaching dirty Operation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Operation instance) {
		log.debug("attaching clean Operation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OperationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OperationDAO) ctx.getBean("OperationDAO");
	}
}