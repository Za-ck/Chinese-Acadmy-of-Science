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

import com.model.ReportFormInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * FormInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.ReportFormInfo
 * @author MyEclipse Persistence Tools
 */

public class ReportFormInfoDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportFormInfoDAO.class);
	// property constants
	public static final String FI_FORM_ID = "fiFormId";
	public static final String FI_FLOW_ID = "fiFlowId";
	public static final String FI_FLOW_STATUS = "fiFlowStatus";
	public static final String FI_WRITER_ID = "fiWriterId";
	public static final String FI_WRITER_NAME = "fiWriterName";
	public static final String FI_WRITER_DEP_ID = "fiWriterDepId";
	public static final String FI_WRITER_DEP_NAME = "fiWriterDepName";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportFormInfo transientInstance) {
		log.debug("saving FormInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportFormInfo persistentInstance) {
		log.debug("deleting FormInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportFormInfo findById(java.lang.Integer id) {
		log.debug("getting FormInfo instance with id: " + id);
		try {
			ReportFormInfo instance = (ReportFormInfo) getHibernateTemplate().get(
					"com.model.FormInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportFormInfo instance) {
		log.debug("finding FormInfo instance by example");
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
		log.debug("finding FormInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FormInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFiFormId(Object fiFormId) {
		return findByProperty(FI_FORM_ID, fiFormId);
	}

	public List findByFiFlowId(Object fiFlowId) {
		return findByProperty(FI_FLOW_ID, fiFlowId);
	}

	public List findByFiFlowStatus(Object fiFlowStatus) {
		return findByProperty(FI_FLOW_STATUS, fiFlowStatus);
	}

	public List findByFiWriterId(Object fiWriterId) {
		return findByProperty(FI_WRITER_ID, fiWriterId);
	}

	public List findByFiWriterName(Object fiWriterName) {
		return findByProperty(FI_WRITER_NAME, fiWriterName);
	}

	public List findByFiWriterDepId(Object fiWriterDepId) {
		return findByProperty(FI_WRITER_DEP_ID, fiWriterDepId);
	}

	public List findByFiWriterDepName(Object fiWriterDepName) {
		return findByProperty(FI_WRITER_DEP_NAME, fiWriterDepName);
	}

	public List findAll() {
		log.debug("finding all FormInfo instances");
		try {
			String queryString = "from FormInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportFormInfo merge(ReportFormInfo detachedInstance) {
		log.debug("merging FormInfo instance");
		try {
			ReportFormInfo result = (ReportFormInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportFormInfo instance) {
		log.debug("attaching dirty FormInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportFormInfo instance) {
		log.debug("attaching clean FormInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportFormInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReportFormInfoDAO) ctx.getBean("FormInfoDAO");
	}
	
	/*-----------------------------------以下是自己写的方法--------------------------------*/
	//更改formInfo的流转状态
	public void updateFormState(String formId, String status, String statement) {
		
		final String queryString = "UPDATE ReportFormInfo SET fiFlowStatus = '"+status+"',fiStatements='"+statement+"' WHERE fiFormId='" + formId +"'";
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
	
	//更新formInfo的最后处理时间
	public void updateFormLastDate(String formId, String dateStr) {
		
		final String queryString = "UPDATE ReportFormInfo SET fiLastdate = '"+ dateStr +"' WHERE fiFormId='" + formId +"'";
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