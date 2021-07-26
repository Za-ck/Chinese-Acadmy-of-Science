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

import com.model.ReportMessage;
import com.model.ReportTaskRiskView;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportMessage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportMessage
 * @author MyEclipse Persistence Tools
 */

public class ReportMessageDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportMessageDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportMessage transientInstance) {
		log.debug("saving ReportMessage instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportMessage persistentInstance) {
		log.debug("deleting ReportMessage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportMessage findById(java.lang.Integer id) {
		log.debug("getting ReportMessage instance with id: " + id);
		try {
			ReportMessage instance = (ReportMessage) getHibernateTemplate()
					.get("com.model.ReportMessage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportMessage instance) {
		log.debug("finding ReportMessage instance by example");
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
		log.debug("finding ReportMessage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportMessage as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportMessage instances");
		try {
			String queryString = "from ReportMessage";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportMessage merge(ReportMessage detachedInstance) {
		log.debug("merging ReportMessage instance");
		try {
			ReportMessage result = (ReportMessage) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportMessage instance) {
		log.debug("attaching dirty ReportMessage instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportMessage instance) {
		log.debug("attaching clean ReportMessage instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportMessageDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportMessageDAO) ctx.getBean("ReportMessageDAO");
	}
	
	/*------------------------------------------以下是自己写的函数------------------------------*/
	private String generateReportMessageSQL(String state, String userId, String dateFrom, String dateTo) {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("FROM ReportMessage rm WHERE rm.rmUserId='" + userId +"' ");
		if (dateFrom != null && !dateFrom.equals("")) {
			queryString.append(" AND rm.rmDate between '" + dateFrom+ "' ");
		}
			   
		else {
			queryString.append(" AND rm.rmDate between '1899-10-10' ");
		}
				
		if (dateTo != null && !dateTo.equals("")) {
			queryString.append(" AND '" + dateTo+ "' ");
		}
			   
		else {
			queryString.append(" AND '2999-12-12' ");
		}
		
		if(state != null && !state.equals("")) {
			queryString.append(" AND rm.rmState=" + state);
		}
		
		return queryString.toString();
	}
	
	public int getReportMessageSize(String state, String userId, String dateFrom, String dateTo) {
		final String queryString = "SELECT COUNT(*) " + generateReportMessageSQL(state, userId, dateFrom, dateTo);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createQuery(queryString).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	public List<ReportMessage> getReportMessage(String state, String userId, String dateFrom, String dateTo, final int offset, final int pageSize,String orderbys) {
		
		final StringBuilder queryString = new StringBuilder(generateReportMessageSQL(state, userId, dateFrom, dateTo) + " ORDER BY ");
		if(orderbys != null && !orderbys.equals("")) {
			queryString.append("rm." + orderbys + ",");
		}
		queryString.append("rm.rmDate DESC");
		
		List<ReportMessage> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportMessage>>() {
			
			@SuppressWarnings("unchecked")
			public List<ReportMessage> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				
				List<ReportMessage> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	public void updateMessageState(String rmId, String state) {
		
		
		final String queryString = "UPDATE ReportMessage SET rmState = "+state+" WHERE rmId='" + rmId +"'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result =  session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		
	}
}