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

import com.model.ReportFlowRule;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportFlowRule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportFlowRule
 * @author MyEclipse Persistence Tools
 */

public class ReportFlowRuleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportFlowRuleDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportFlowRule transientInstance) {
		log.debug("saving ReportFlowRule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportFlowRule persistentInstance) {
		log.debug("deleting ReportFlowRule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportFlowRule findById(java.lang.Integer id) {
		log.debug("getting ReportFlowRule instance with id: " + id);
		try {
			ReportFlowRule instance = (ReportFlowRule) getHibernateTemplate()
					.get("com.model.ReportFlowRule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(ReportFlowRule instance) {
		log.debug("finding ReportFlowRule instance by example");
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

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ReportFlowRule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportFlowRule as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all ReportFlowRule instances");
		try {
			String queryString = "from ReportFlowRule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportFlowRule merge(ReportFlowRule detachedInstance) {
		log.debug("merging ReportFlowRule instance");
		try {
			ReportFlowRule result = (ReportFlowRule) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportFlowRule instance) {
		log.debug("attaching dirty ReportFlowRule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportFlowRule instance) {
		log.debug("attaching clean ReportFlowRule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportFlowRuleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportFlowRuleDAO) ctx.getBean("ReportFlowRuleDAO");
	}
	
	/*----------------- 以下是自己写的方法  ----------------------*/
	public String getFlowExplain(String flowId, String status) {
		
		final String queryString = "FROM ReportFlowRule WHERE rfFlowId ='" + flowId + "' and rfFlowStatus ='" + status + "'";
		ReportFlowRule rule = getHibernateTemplate().execute(new HibernateCallback<ReportFlowRule>(){

			@Override
			public ReportFlowRule doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportFlowRule result = (ReportFlowRule) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
			
		});
		
		return rule.getRfFlowExplain();
	}
	
	// 通过流程ID和流程状态编号获取一个具体的流程状态
	public ReportFlowRule getFlowState(String flowId, String status) {
		
		final String queryString = "FROM ReportFlowRule WHERE rfFlowId ='" + flowId + "' and rfFlowStatus ='" + status + "'";
		ReportFlowRule rule = getHibernateTemplate().execute(new HibernateCallback<ReportFlowRule>(){

			@Override
			public ReportFlowRule doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportFlowRule result = (ReportFlowRule) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
			
		});
		
		return rule;
	}
	
	//获取下一个状态
	public String getNextStatus(String flowId, String status) {
		
		final String queryString = "FROM ReportFlowRule WHERE rfFlowId ='" + flowId + "' and rfFlowStatus ='" + status + "'";
		ReportFlowRule rule = getHibernateTemplate().execute(new HibernateCallback<ReportFlowRule>(){

			@Override
			public ReportFlowRule doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportFlowRule result = (ReportFlowRule) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
			
		});
		
		return rule.getRfNextStatus();
		
	}
	
	//获取回退的状态(用于审核不通过的情况)
	public String getBackStatus(String flowId, String status) {
		final String queryString = "FROM ReportFlowRule WHERE rfFlowId ='" + flowId + "' and rfFlowStatus ='" + status + "'";
		ReportFlowRule rule = getHibernateTemplate().execute(new HibernateCallback<ReportFlowRule>(){

			@Override
			public ReportFlowRule doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportFlowRule result = (ReportFlowRule) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
			
		});
		
		return rule.getRfBackStatus();
	}
	
	//获取流程从上一个状态流转到当前位置所做的操作
	public String getAction(String flowId, String status) {
		final String queryString = "FROM ReportFlowRule WHERE rfFlowId ='" + flowId + "' and rfNextStatus ='" + status + "'";
		ReportFlowRule rule = getHibernateTemplate().execute(new HibernateCallback<ReportFlowRule>(){

			@Override
			public ReportFlowRule doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportFlowRule result = (ReportFlowRule) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
			
		});
		return rule.getRfAction();
	}
}