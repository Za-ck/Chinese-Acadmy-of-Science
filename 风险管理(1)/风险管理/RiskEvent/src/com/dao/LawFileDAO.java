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

import com.model.LawFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * LawFile entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ymy.hib.po.LawFile
 * @author MyEclipse Persistence Tools
 */

public class LawFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(LawFileDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(LawFile transientInstance) {
		log.debug("saving LawFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LawFile persistentInstance) {
		log.debug("deleting LawFile instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LawFile findById(java.lang.Integer id) {
		log.debug("getting LawFile instance with id: " + id);
		try {
			LawFile instance = (LawFile) getHibernateTemplate().get(
					"com.model.LawFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LawFile instance) {
		log.debug("finding LawFile instance by example");
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
		log.debug("finding LawFile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LawFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all LawFile instances");
		try {
			String queryString = "from LawFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all LawFile instances");
		try {		
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from LawFile").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from LawFile order by fileDate desc")
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
	public LawFile merge(LawFile detachedInstance) {
		log.debug("merging LawFile instance");
		try {
			LawFile result = (LawFile) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LawFile instance) {
		log.debug("attaching dirty LawFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LawFile instance) {
		log.debug("attaching clean LawFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LawFileDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LawFileDAO) ctx.getBean("LawFileDAO");
	}
	
	//按条件查找
	@SuppressWarnings("unchecked")
	public List findByfileTitle(final int offset, final int pageSize,final String filetitle) {
		log.debug("finding all LawFile instances");
		try {		
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from LawFile").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from LawFile as model where model.filetitle like '%"+filetitle+"%'")
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