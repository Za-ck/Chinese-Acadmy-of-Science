package com.dao;

import java.sql.SQLException;
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

import com.model.ReadFlowView;
import com.model.ReportTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReadFlowView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReadFlowView
 * @author MyEclipse Persistence Tools
 */

public class ReadFlowViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReadFlowViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(ReadFlowView transientInstance) {
		log.debug("saving ReadFlowView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReadFlowView persistentInstance) {
		log.debug("deleting ReadFlowView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReadFlowView findById(java.lang.Integer id) {
		log.debug("getting ReadFlowView instance with id: " + id);
		try {
			ReadFlowView instance = (ReadFlowView) getHibernateTemplate().get(
					"com.model.ReadFlowView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReadFlowView instance) {
		log.debug("finding ReadFlowView instance by example");
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
		log.debug("finding ReadFlowView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReadFlowView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReadFlowView instances");
		try {
			String queryString = "from ReadFlowView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReadFlowView merge(ReadFlowView detachedInstance) {
		log.debug("merging ReadFlowView instance");
		try {
			ReadFlowView result = (ReadFlowView) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReadFlowView instance) {
		log.debug("attaching dirty ReadFlowView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReadFlowView instance) {
		log.debug("attaching clean ReadFlowView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReadFlowViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReadFlowViewDAO) ctx.getBean("ReadFlowViewDAO");
	}
	
	
	/*----------------- 以下是自己写的方法  ----------------------*/
	// 根据formId获取工作记录
	public List<ReadFlowView> getRecordlist(String formId) {
		
		final String queryString = "FROM ReadFlowView WHERE retFormId='" + formId +"' ORDER BY retId ASC";
		List<ReadFlowView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReadFlowView>>() {
			
			public List<ReadFlowView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString);
				@SuppressWarnings("unchecked")
				List<ReadFlowView> list = query.list();
				
				session.close();
				return list;
			}
		});
		
		return list;
	}
	
}