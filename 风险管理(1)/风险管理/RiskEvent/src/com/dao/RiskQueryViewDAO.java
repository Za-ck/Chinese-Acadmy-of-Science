package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
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

import com.model.Risk;
import com.model.RiskQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskQueryView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskQueryView
 * @author MyEclipse Persistence Tools
 */

public class RiskQueryViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskQueryViewDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(RiskQueryView transientInstance) {
		log.debug("saving RiskQueryView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskQueryView persistentInstance) {
		log.debug("deleting RiskQueryView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskQueryView findById(String id) {
		log.debug("getting RiskQueryView instance with id: " + id);
		try {
			RiskQueryView instance = (RiskQueryView) getHibernateTemplate()
					.get("com.model.RiskQueryView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskQueryView instance) {
		log.debug("finding RiskQueryView instance by example");
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
		log.debug("finding RiskQueryView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskQueryView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RiskQueryView instances");
		try {
			String queryString = "from RiskQueryView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllbyName() {
		log.debug("finding all RiskQueryView instances");
		try {
			String queryString = "from RiskQueryView as risk order by risk.riskName";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskQueryView merge(RiskQueryView detachedInstance) {
		log.debug("merging RiskQueryView instance");
		try {
			RiskQueryView result = (RiskQueryView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskQueryView instance) {
		log.debug("attaching dirty RiskQueryView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskQueryView instance) {
		log.debug("attaching clean RiskQueryView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskQueryViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskQueryViewDAO) ctx.getBean("RiskQueryViewDAO");
	}
	
	//褰撳勾鎵�湁椋庨櫓
	public List findCurrentYearAll() {
		log.debug("finding all RiskQueryView instances");
		try {
			
			String riskCurrent="1";
			final String queryString = "from RiskQueryView as model where model.riskCurrent = '"+riskCurrent+"' order by model.riskQueue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//褰撳勾鎵�湁椋庨櫓
	public List findCurrentYearAll(final int offset, final int pageSize) {
		log.debug("finding all Department instances");
		try {
			String riskCurrent="1";
			final String queryString = "from RiskQueryView as model where model.riskCurrent = '"+riskCurrent+"' order by model.riskName";
			//findAll();
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
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
	
	//鏍规嵁椋庨櫓绫诲瀷淇℃伅鏌ヨ椋庨櫓
	//瀵煎嚭Excel鏌ヨ
	public List searchNew(String year,String riskTypeId) {
		
		log.debug("finding all Risk instances");
		try {
			//String queryString = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%' or risk.riskType.rtName like '%"+riskTypeId+"%'";
			String queryString ="";
			if(year.equals("2012"))
			{
				//2019.01.08
				queryString = "from RiskQueryView as risk where length(risk.riskId)<12 and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') and risk_current='1'  order by risk.riskQueue";
			}
			else
			{
				//2019.01.08
				queryString = "from RiskQueryView as risk where risk.riskId like '%"+year+"%' and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') and risk_current='1' order by risk.riskQueue";
			}
			
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByTypeAndYearDESC(String riskTypeId,String year)
	{
		try {
			
			String queryString1 = "from RiskQueryView as model where model.riskType = '"+riskTypeId+"' and model.riskId like '%"+year+"%'  order by model.riskId desc";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	
	//鍒嗛〉鏌ヨ
	public List searchNew(String year,String riskTypeId,final int offset, final int pageSize) {
		
		log.debug("finding all Department instances");
		try {
			
			//final String queryString = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%' or risk.riskType.rtName like '%"+riskTypeId+"%'";		
			String queryString1 ="";
			if(year.equals("2012"))
			{
				//2019.01.08
				queryString1 = "from RiskQueryView as risk where length(risk.riskId)<12 and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') and risk_current='1' order by risk.riskName";
			}
			else
			{
				//2019.01.08
				queryString1 = "from RiskQueryView as risk where risk.riskId like '%"+year+"%' and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') and risk_current='1' order by risk.riskName";
			}
			
			final String queryString =queryString1;
			
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