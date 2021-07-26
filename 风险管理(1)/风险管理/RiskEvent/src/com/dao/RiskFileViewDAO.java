package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.RiskFileView;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskFileView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskFileView
 * @author MyEclipse Persistence Tools
 */
public class RiskFileViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskFileViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(RiskFileView transientInstance) {
		log.debug("saving RiskFileView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskFileView persistentInstance) {
		log.debug("deleting RiskFileView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findByExample(RiskFileView instance) {
		log.debug("finding RiskFileView instance by example");
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
		log.debug("finding RiskFileView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskFileView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RiskFileView instances");
		try {
			String queryString = "from RiskFileView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskFileView merge(RiskFileView detachedInstance) {
		log.debug("merging RiskFileView instance");
		try {
			RiskFileView result = (RiskFileView) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskFileView instance) {
		log.debug("attaching dirty RiskFileView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskFileView instance) {
		log.debug("attaching clean RiskFileView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskFileViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskFileViewDAO) ctx.getBean("RiskFileViewDAO");
	}
	
	
	/*------------------------------------以下是自己写的方法----------------------------------*/
	@SuppressWarnings("unchecked")
	public List<RiskFileView> getRelatedFile(String riskId, String type) {
		
		try {
			final String queryString ="from RiskFileView as rf where rf.riskId='"+riskId+"' and rf.fileType='" + type +"'";
			List<RiskFileView> list = getHibernateTemplate().executeFind(new HibernateCallback<List<RiskFileView>>() {			
				public List<RiskFileView> doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List<RiskFileView> result = session.createQuery(queryString).list();
					session.close();
					return result;
				}
			});					
			return list;
			
		}catch (Exception re) {
			//throw re;
			re.printStackTrace();
			return null;
		}
		}
		public List<RiskFileView> getRelatedFile(String riskId, String type,String fileNameString) {
			
			try {
				final String queryString ="from RiskFileView as rf where (rf.fileName like '%"+fileNameString+"%') and rf.riskId='"+riskId+"' and rf.fileType='" + type +"'";
				@SuppressWarnings("unchecked")
				List<RiskFileView> list = getHibernateTemplate().executeFind(new HibernateCallback<List<RiskFileView>>() {			
					public List<RiskFileView> doInHibernate(Session session) throws HibernateException,
							SQLException {
						
						List<RiskFileView> result = session.createQuery(queryString).list();
						session.close();
						return result;
					}
				});					
				return list;
				
			}catch (Exception re) {
				//throw re;
				re.printStackTrace();
				return null;
			}
		
	}
	
}