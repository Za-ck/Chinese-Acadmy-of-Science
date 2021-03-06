package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.AllStaView;

/**
 * A data access object (DAO) providing persistence and search support for
 * AllStaView entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.AllStaView
 * @author MyEclipse Persistence Tools
 */

public class AllStaViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AllStaViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(AllStaView transientInstance) {
		log.debug("saving AllStaView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AllStaView persistentInstance) {
		log.debug("deleting AllStaView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AllStaView findById(com.model.AllStaViewId id) {
		log.debug("getting AllStaView instance with id: " + id);
		try {
			AllStaView instance = (AllStaView) getHibernateTemplate().get(
					"com.model.AllStaView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AllStaView instance) {
		log.debug("finding AllStaView instance by example");
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
		log.debug("finding AllStaView instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AllStaView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AllStaView instances");
		try {
			String queryString = "from AllStaView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AllStaView merge(AllStaView detachedInstance) {
		log.debug("merging AllStaView instance");
		try {
			AllStaView result = (AllStaView) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AllStaView instance) {
		log.debug("attaching dirty AllStaView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AllStaView instance) {
		log.debug("attaching clean AllStaView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AllStaViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AllStaViewDAO) ctx.getBean("AllStaViewDAO");
	}

	//??????????????????????????????????????????????????????????????????????????????????
	public List<AllStaView> findByYearandQuar(String year, Integer quarter) {
		log.debug("finding AllStaView instances by year and quarter");
		try {
			String queryString = "from AllStaView as model where model.year= '"+year+"' and model.quarter= "+quarter+" ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//????????
	@SuppressWarnings("unchecked")
	public List<AllStaView> findByYear(String year) {
		log.debug("finding AllStaView instances by year and quarter");
		try {
			String queryString = "from AllStaView as model where model.year= '"+year+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//??????????????????????????????????????????????????????????????????????????????????_part????????????????????????????????????
	@SuppressWarnings("unchecked")
	public List<AllStaView> findByYQ_part(String year, Integer quarter,String dep) {
		log.debug("finding AllStaView instances by year and quarter");
		try {
			String queryString = "from AllStaView as model where model.year= '"+year+"' and model.quarter= "+quarter+" and model.reIndep='"+dep+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//????????
	@SuppressWarnings("unchecked")
	public List<AllStaView> findByYear_part(String year,String dep) {
		log.debug("finding AllStaView instances by year and quarter");
		try {
			String queryString = "from AllStaView as model where model.year= '"+year+"' and model.reIndep='"+dep+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}