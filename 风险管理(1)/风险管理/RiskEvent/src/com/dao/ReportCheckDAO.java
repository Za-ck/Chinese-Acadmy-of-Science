package com.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ReportCheck;

public class ReportCheckDAO extends HibernateDaoSupport {
	
	private static final Logger log = LoggerFactory.getLogger(ReportCheckDAO.class);
	
	public static final String RC_REPORT_ID = "rcReportId";
	public static final String RC_REPORT_NAME = "rcReportName";
	public static final String RC_DEP_ID = "rcDepId";
	public static final String RC_DEP_NAME = "rcDepName";
	public static final String RC_WRITER_ID = "rcWriterId";
	public static final String RC_WRITER_NAME = "rcWriterName";
	public static final String RC_LEADER_ID = "rcLeaderId";
	public static final String RC_LEADER_NAME = "rcLeaderName";
	public static final String RC_FLOW_ID = "rcFlowId";
	public static final String RC_FLOW_STATUS = "rcFlowStatus";
	public static final String RC_DEAN_ID = "rcDeanId";
	public static final String RC_MODIFY_DATE = "rcModifyDate";
	public static final String RC_REMARK = "rcRemark";
	public static final String RC_DEAN_NAME = "rcDeanName";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportCheck transientInstance) {
		log.debug("saving ReportCheck instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportCheck persistentInstance) {
		log.debug("deleting ReportCheck instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportCheck findById(java.lang.String id) {
		log.debug("getting ReportCheck instance with id: " + id);
		try {
			ReportCheck instance = (ReportCheck) getHibernateTemplate().get(
					"com.model.ReportCheck", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportCheck instance) {
		log.debug("finding ReportCheck instance by example");
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
		log.debug("finding ReportCheck instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReportCheck as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRcReportId(Object rcReportId) {
		return findByProperty(RC_REPORT_ID, rcReportId);
	}

	public List findByRcReportName(Object rcReportName) {
		return findByProperty(RC_REPORT_NAME, rcReportName);
	}

	public List findByRcDepId(Object rcDepId) {
		return findByProperty(RC_DEP_ID, rcDepId);
	}

	public List findByRcDepName(Object rcDepName) {
		return findByProperty(RC_DEP_NAME, rcDepName);
	}

	public List findByRcWriterId(Object rcWriterId) {
		return findByProperty(RC_WRITER_ID, rcWriterId);
	}

	public List findByRcWriterName(Object rcWriterName) {
		return findByProperty(RC_WRITER_NAME, rcWriterName);
	}

	public List findByRcLeaderId(Object rcLeaderId) {
		return findByProperty(RC_LEADER_ID, rcLeaderId);
	}

	public List findByRcLeaderName(Object rcLeaderName) {
		return findByProperty(RC_LEADER_NAME, rcLeaderName);
	}

	public List findByRcFlowId(Object rcFlowId) {
		return findByProperty(RC_FLOW_ID, rcFlowId);
	}

	public List findByRcFlowStatus(Object rcFlowStatus) {
		return findByProperty(RC_FLOW_STATUS, rcFlowStatus);
	}

	public List findByRcDeanId(Object rcDeanId) {
		return findByProperty(RC_DEAN_ID, rcDeanId);
	}

	public List findByRcModifyDate(Object rcModifyDate) {
		return findByProperty(RC_MODIFY_DATE, rcModifyDate);
	}

	public List findByRcRemark(Object rcRemark) {
		return findByProperty(RC_REMARK, rcRemark);
	}

	public List findByRcDeanName(Object rcDeanName) {
		return findByProperty(RC_DEAN_NAME, rcDeanName);
	}

	public List findAll() {
		log.debug("finding all ReportCheck instances");
		try {
			String queryString = "from ReportCheck";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportCheck merge(ReportCheck detachedInstance) {
		log.debug("merging ReportCheck instance");
		try {
			ReportCheck result = (ReportCheck) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportCheck instance) {
		log.debug("attaching dirty ReportCheck instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportCheck instance) {
		log.debug("attaching clean ReportCheck instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportCheckDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportCheckDAO) ctx.getBean("ReportCheckDAO");
	}
	
	/*--------------------------以下是自己写的函数------------------------------*/
	// 根据reportId获取ReportCheck
	public ReportCheck getSpecificRepCheck(String reportId) {
		
		final String queryString = "FROM ReportCheck WHERE rcReportId='" + reportId + "'";
		
		ReportCheck rc = getHibernateTemplate().execute( new HibernateCallback<ReportCheck>() {
			@Override
			public ReportCheck doInHibernate(Session session) throws HibernateException, SQLException {
				ReportCheck rch = (ReportCheck) session.createQuery(queryString).uniqueResult();
				session.close();
				return rch;
			}
		});
		return rc;
	}
	
	public void revocateReportCheck(String repcheck,String flowStatus) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ReportCheck rd = findById(repcheck);
		rd.setRcFlowStatus(flowStatus);
		rd.setRcModifyDate(df.format(new Date()));
		
		merge(rd);
	}
	
	
}