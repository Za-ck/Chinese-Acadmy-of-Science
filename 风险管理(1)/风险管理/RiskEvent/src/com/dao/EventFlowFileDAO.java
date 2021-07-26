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

import com.model.EventFile;
import com.model.EventFlowFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * EventFlowFile entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.EventFlowFile
 * @author MyEclipse Persistence Tools
 */

public class EventFlowFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(EventFlowFileDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(EventFlowFile transientInstance) {
		log.debug("saving EventFlowFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventFlowFile persistentInstance) {
		log.debug("deleting EventFlowFile instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventFlowFile findById(java.lang.Integer id) {
		log.debug("getting EventFlowFile instance with id: " + id);
		try {
			EventFlowFile instance = (EventFlowFile) getHibernateTemplate().get(
					"com.model.EventFlowFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EventFlowFile instance) {
		log.debug("finding EventFlowFile instance by example");
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
		log.debug("finding EventFlowFile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EventFlowFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EventFlowFile instances");
		try {
			String queryString = "from EventFlowFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all Client instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from EventFlowFile").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery("from EventFlowFile")
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


	public EventFlowFile merge(EventFlowFile detachedInstance) {
		log.debug("merging EventFlowFile instance");
		try {
			EventFlowFile result = (EventFlowFile) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventFlowFile instance) {
		log.debug("attaching dirty EventFlowFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventFlowFile instance) {
		log.debug("attaching clean EventFlowFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventFlowFileDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EventFlowFileDAO) ctx.getBean("EventFlowFileDAO");
	}
	
	//通过风险事件的ID查找管理责任部门负责人
	public List<?> findMLeader(String reId) {
		try {
			String queryString = "select distinct mandepleader from ManageDepLeaderView as model where model.reId='"
					+ reId + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//通过风险事件的ID查找管理责任部门负责人
	public List<?> findILeader(String reId) {
		try {
			String queryString = "select distinct inputleader from InputLeaderView as model where model.reId='"
					+ reId + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}