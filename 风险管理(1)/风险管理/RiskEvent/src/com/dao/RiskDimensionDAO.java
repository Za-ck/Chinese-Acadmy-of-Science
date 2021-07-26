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

import com.model.Probability;
import com.model.RiskDimension;

/**
 * A data access object (DAO) providing persistence and search support for
 * Probability entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.Probability
 * @author MyEclipse Persistence Tools
 */

public class RiskDimensionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskDimensionDAO.class);
	// property constants
	public static final String RD_Id = "rdId";
	public static final String RD_dimName = "rdDimName";
	public static final String RD_increaseScore = "rdIncreaseScore";
	public static final String RD_decreaseScore = "rdDecreaseScore";
	public static final String RD_weight = "rdWeight";
	public static final String RD_dimId = "rdDimId";
	public static final String RD_remark2 = "rdRemark2";
	protected void initDao() {
		// do nothing
	}
	//处理日志文件log
	public void save(RiskDimension transientInstance) {
		log.debug("saving RiskDimension instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void delete(RiskDimension persistentInstance) {
		log.debug("deleting RiskDimension instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskDimension findById(java.lang.Integer id) {
		log.debug("getting RiskDimension instance with id: " + id);
		try {
			RiskDimension instance = (RiskDimension) getHibernateTemplate().get(
					"com.model.RiskDimension", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(RiskDimension instance) {
		log.debug("finding RiskDimension instance by example");
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
		log.debug("finding RiskDimension instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskDimension as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByRdDimName(Object rdDimName) {
		return findByProperty(RD_dimName, rdDimName);
	}
	
	public List findByRdIncreaseScore(Object rdIncreaseScore) {
		return findByProperty(RD_increaseScore, rdIncreaseScore);
	}
	
	public List findByRdDecreaseScore(Object rdDecreaseScore) {
		return findByProperty(RD_decreaseScore, rdDecreaseScore);
	}
	
	public List findByRdWeight(Object rdWeight) {
		return findByProperty(RD_weight, rdWeight);
	}
	
	public List findByRdDimId(Object rdDimId) {
		return findByProperty(RD_dimId, rdDimId);
	}

	public List findAll() {
		log.debug("finding all RiskDimension instances");
		try {
			String queryString = "from RiskDimension";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all RiskDimension instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from RiskDimension").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from RiskDimension")
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
	
	public RiskDimension merge(RiskDimension detachedInstance) {
		log.debug("merging RiskDimension instance");
		try {
			RiskDimension result = (RiskDimension) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(RiskDimension instance) {
		log.debug("attaching dirty RiskDimension instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskDimension instance) {
		log.debug("attaching clean RiskDimension instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskDimensionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskDimensionDAO) ctx.getBean("RiskDimensionDAO");
	}
	
	public RiskDimension findDimbyDimId(String DimId) {
		try {
			String queryString = "from RiskDimension where rdDimId = '"+DimId+"'";
			return (RiskDimension) getHibernateTemplate().find(queryString).get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}    
}
