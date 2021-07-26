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

import com.model.Reputation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Reputation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Reputation
 * @author MyEclipse Persistence Tools
 */

public class ReputationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReputationDAO.class);
	// property constants
	public static final String REP_LEVEL = "repLevel";
	public static final String REP_DETAIL = "repDetail";
	public static final String REP_SUPERIOR = "repSuperior";
	public static final String REP_PARTNER = "repPartner";
	public static final String REP_PUBLIC = "repPublic";

	protected void initDao() {
		// do nothing
	}

	public void save(Reputation transientInstance) {
		log.debug("saving Reputation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reputation persistentInstance) {
		log.debug("deleting Reputation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reputation findById(java.lang.Integer id) {
		log.debug("getting Reputation instance with id: " + id);
		try {
			Reputation instance = (Reputation) getHibernateTemplate().get(
					"com.model.Reputation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reputation instance) {
		log.debug("finding Reputation instance by example");
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
		log.debug("finding Reputation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reputation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRepLevel(Object repLevel) {
		return findByProperty(REP_LEVEL, repLevel);
	}

	public List findByRepDetail(Object repDetail) {
		return findByProperty(REP_DETAIL, repDetail);
	}

	public List findByRepSuperior(Object repSuperior) {
		return findByProperty(REP_SUPERIOR, repSuperior);
	}

	public List findByRepPartner(Object repPartner) {
		return findByProperty(REP_PARTNER, repPartner);
	}

	public List findByRepPublic(Object repPublic) {
		return findByProperty(REP_PUBLIC, repPublic);
	}

	public List findAll() {
		log.debug("finding all Reputation instances");
		try {
			String queryString = "from Reputation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all Reputation instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from Reputation").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from Reputation")
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

	public Reputation merge(Reputation detachedInstance) {
		log.debug("merging Reputation instance");
		try {
			Reputation result = (Reputation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reputation instance) {
		log.debug("attaching dirty Reputation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reputation instance) {
		log.debug("attaching clean Reputation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReputationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReputationDAO) ctx.getBean("ReputationDAO");
	}
}