package com.dao;



import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.FunctionModule;

/**
 * A data access object (DAO) providing persistence and search support for
 * FunctionModule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.FunctionModule
 * @author MyEclipse Persistence Tools
 */

public class FunctionModuleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(FunctionModuleDAO.class);
	// property constants
	public static final String FM_NAME = "fmName";
	public static final String FM_CATEGORY = "fmCategory";
	public static final String FM_CATENAME = "fmCatename";
	public static final String FM_REMARK = "fmRemark";

	protected void initDao() {
		// do nothing
	}

	public void save(FunctionModule transientInstance) {
		log.debug("saving FunctionModule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FunctionModule persistentInstance) {
		log.debug("deleting FunctionModule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FunctionModule findById(java.lang.Integer id) {
		log.debug("getting FunctionModule instance with id: " + id);
		try {
			FunctionModule instance = (FunctionModule) getHibernateTemplate()
					.get("com.zn.model.FunctionModule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FunctionModule instance) {
		log.debug("finding FunctionModule instance by example");
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
		log.debug("finding FunctionModule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FunctionModule as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFmName(Object fmName) {
		return findByProperty(FM_NAME, fmName);
	}

	public List findByFmCategory(Object fmCategory) {
		return findByProperty(FM_CATEGORY, fmCategory);
	}

	public List findByFmCatename(Object fmCatename) {
		return findByProperty(FM_CATENAME, fmCatename);
	}

	public List findByFmRemark(Object fmRemark) {
		return findByProperty(FM_REMARK, fmRemark);
	}

	public List findAll() {
		log.debug("finding all FunctionModule instances");
		try {
			String queryString = "from FunctionModule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FunctionModule merge(FunctionModule detachedInstance) {
		log.debug("merging FunctionModule instance");
		try {
			FunctionModule result = (FunctionModule) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FunctionModule instance) {
		log.debug("attaching dirty FunctionModule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FunctionModule instance) {
		log.debug("attaching clean FunctionModule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FunctionModuleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FunctionModuleDAO) ctx.getBean("FunctionModuleDAO");
	}
}