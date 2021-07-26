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

import com.model.RiskEventDepQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskEventDepQueryView entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskEventDepQueryView
 * @author MyEclipse Persistence Tools
 */

public class RiskEventDepQueryViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskEventDepQueryViewDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(RiskEventDepQueryView transientInstance) {
		log.debug("saving RiskEventDepQueryView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskEventDepQueryView persistentInstance) {
		log.debug("deleting RiskEventDepQueryView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskEventDepQueryView findById(String id) {
		log.debug("getting RiskEventDepQueryView instance with id: " + id);
		try {
			RiskEventDepQueryView instance = (RiskEventDepQueryView) getHibernateTemplate()
					.get("com.model.RiskEventDepQueryView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskEventDepQueryView instance) {
		log.debug("finding RiskEventDepQueryView instance by example");
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
		log.debug("finding RiskEventDepQueryView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskEventDepQueryView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RiskEventDepQueryView instances");
		try {
			String queryString = "from RiskEventDepQueryView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskEventDepQueryView merge(RiskEventDepQueryView detachedInstance) {
		log.debug("merging RiskEventDepQueryView instance");
		try {
			RiskEventDepQueryView result = (RiskEventDepQueryView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskEventDepQueryView instance) {
		log.debug("attaching dirty RiskEventDepQueryView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskEventDepQueryView instance) {
		log.debug("attaching clean RiskEventDepQueryView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskEventDepQueryViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskEventDepQueryViewDAO) ctx
				.getBean("RiskEventDepQueryViewDAO");
	}
	
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize,final String depId,final String orderbys) {
		log.debug("finding all RiskEvent instances");
		try {
			final String queryString = "SELECT COUNT(*) from RiskEventDepQueryView as re ";//where re.reIndep='"+depId+"'
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from RiskEventDepQueryView as re "+(orderbys.equals("")?"order by re.reModifydate desc":"order by re."+orderbys))//where re.reIndep='"+depId+"'
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
			re.printStackTrace();
			return null;
		}
	}
	
	//RiskEventInputQueryAction,高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId、部门ID reIndep
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findByQueryCondition(String dateFrom,String dateTo,String riskId,String reIndep,String reventStr,final int offset, final int pageSize,final String depId,final String orderbys) {
		try {
			
			String queryString ="from RiskEventDepQueryView as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"'  ";//and re4.reIndep='" + depId + "'
			/*if(!reIndep.equals("none1")){
				queryString += " and re4.reIndep='"+reIndep+"' ";
			}
			if(!riskId.equals("none1")){
				queryString += " and re4.reRiskId = '"+riskId+"' ";
			}
			if(reventStr != null && !reventStr.equals("")){
				queryString += " and (re4.reId like '%"+reventStr+"%' or re4.reEventname like '%"+reventStr+"%')";
			}
			*/
			if(orderbys != null && !orderbys.equals("")) {
				
				queryString += "order by re4."+orderbys;
				
			}
			else {
				
				queryString += "order by re4.reDate desc";
				
			}
			
			final String queryString1 = queryString;
			final String queryString2 = "SELECT COUNT(*) " + queryString.substring(0, queryString.indexOf("order by"));
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery(queryString1)
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
			//throw re;
			re.printStackTrace();
			return null;
		}	
	}

	//根据录入时间间隔，查询此段时间内录入的风险事件
	public List findByTimeBetween(String dateBegin,String dateEnd) {
		String queryString = "from RiskEventDepQueryView as re2 where re2.reDate between '"+dateBegin+"' and '"+dateEnd+"' order by re2.reDate desc";
		return getHibernateTemplate().find(queryString);		
	} 
}