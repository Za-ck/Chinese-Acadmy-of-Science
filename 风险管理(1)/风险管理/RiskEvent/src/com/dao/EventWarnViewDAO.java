package com.dao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

import com.model.EventWarnView;

/**
 * A data access object (DAO) providing persistence and search support for
 * EventWarnView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see DAO.EventWarnView
 * @author MyEclipse Persistence Tools
 */

public class EventWarnViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(EventWarnViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(EventWarnView transientInstance) {
		log.debug("saving EventWarnView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventWarnView persistentInstance) {
		log.debug("deleting EventWarnView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventWarnView findById(java.lang.Integer id) {
		log.debug("getting EventWarnView instance with id: " + id);
		try {
			EventWarnView instance = (EventWarnView) getHibernateTemplate()
					.get("com.model.EventWarnView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EventWarnView instance) {
		log.debug("finding EventWarnView instance by example");
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
		log.debug("finding EventWarnView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EventWarnView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EventWarnView instances");
		try {
			String queryString = "from EventWarnView order by RM_plandate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all EventWarnView instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from EventWarnView").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from EventWarnView")
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

	public EventWarnView merge(EventWarnView detachedInstance) {
		log.debug("merging EventWarnView instance");
		try {
			EventWarnView result = (EventWarnView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventWarnView instance) {
		log.debug("attaching dirty EventWarnView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventWarnView instance) {
		log.debug("attaching clean EventWarnView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventWarnViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EventWarnViewDAO) ctx.getBean("EventWarnViewDAO");
	}
	
	public List findByUser(String userName)
	{
		String queryString = "from EventWarnView as re where re.id.userName = '"+userName+"' ";
		return getHibernateTemplate().find(queryString);		
	}
	
	public List findYourself(String dep)
	{
		String queryString = "from EventWarnView as re where re.id.depId = '"+dep+"' order by RM_plandate";
		return getHibernateTemplate().find(queryString);
	}
	
	@SuppressWarnings("unchecked")
	public List<EventWarnView> findAll(String dep)
	{
		String queryString = "from EventWarnView";
		return getHibernateTemplate().find(queryString);
	}
	
	public List findByIndep(String dep) {   
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String ly_time = sdf.format(new java.util.Date()); 
		String queryString = "from EventWarnView as re where re.id.depName like '%"+dep+"%' ";
		return getHibernateTemplate().find(queryString);
     }
	public List findWarn(final int offset, final int pageSize){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String ly_time = sdf.format(new java.util.Date()); 
		final String queryString = "from EventWarnView as re where re.id.rmPlandate <'"+ly_time+"'order by RM_plandate";
		try {					   
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString)
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
	public List bianhaoQuery(String bianhao,final int offset, final int pageSize){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String ly_time = sdf.format(new java.util.Date()); 
		String querysql = "from EventWarnView as re where re.id.rmPlandate <'"+ly_time+"'and id.depId='"+bianhao+"' order by RM_plandate";
		if(bianhao.equals("none1"))
		{
			querysql = "from EventWarnView as re where re.id.rmPlandate <'"+ly_time+"' order by RM_plandate";
		}
		final String queryString =querysql;
		try {					   
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString)
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

}