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

import com.model.Finance;

/**
 * A data access object (DAO) providing persistence and search support for
 * Finance entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Finance
 * @author MyEclipse Persistence Tools
 */

public class FinanceDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(FinanceDAO.class);
	// property constants
	public static final String FIN_DETAIL = "finDetail";
	public static final String FIN_ASSET = "finAsset";
	public static final String FIN_INCOME = "finIncome";
	public static final String FIN_PROFIT = "finProfit";
	public static final String FIN_PROPERTY = "finProperty";

	protected void initDao() {
		// do nothing
	}

	public void save(Finance transientInstance) {
		log.debug("saving Finance instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Finance persistentInstance) {
		log.debug("deleting Finance instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Finance findById(java.lang.Integer id) {
		log.debug("getting Finance instance with id: " + id);
		try {
			Finance instance = (Finance) getHibernateTemplate().get(
					"com.model.Finance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Finance instance) {
		log.debug("finding Finance instance by example");
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
		log.debug("finding Finance instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Finance as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFinDetail(Object finDetail) {
		return findByProperty(FIN_DETAIL, finDetail);
	}

	public List findByFinAsset(Object finAsset) {
		return findByProperty(FIN_ASSET, finAsset);
	}

	public List findByFinIncome(Object finIncome) {
		return findByProperty(FIN_INCOME, finIncome);
	}

	public List findByFinProfit(Object finProfit) {
		return findByProperty(FIN_PROFIT, finProfit);
	}

	public List findByFinProperty(Object finProperty) {
		return findByProperty(FIN_PROPERTY, finProperty);
	}

	public List findAll() {
		log.debug("finding all Finance instances");
		try {
			String queryString = "from Finance";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all Finance instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from Finance").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from Finance")
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

	public Finance merge(Finance detachedInstance) {
		log.debug("merging Finance instance");
		try {
			Finance result = (Finance) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Finance instance) {
		log.debug("attaching dirty Finance instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Finance instance) {
		log.debug("attaching clean Finance instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FinanceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FinanceDAO) ctx.getBean("FinanceDAO");
	}
}