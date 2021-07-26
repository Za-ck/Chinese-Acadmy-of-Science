package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ReportRisk;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportRisk entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.ReportRisk
 * @author MyEclipse Persistence Tools
 */

public class ReportRiskDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ReportRiskDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportRisk transientInstance) {
		log.debug("saving ReportRisk instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportRisk persistentInstance) {
		log.debug("deleting ReportRisk instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	

	public ReportRisk findById(String id) {
		log.debug("getting ReportRisk instance with id: " + id);
		try {
			ReportRisk instance = (ReportRisk) getHibernateTemplate().get(
					"com.model.ReportRisk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportRisk instance) {
		log.debug("finding ReportRisk instance by example");
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
		log.debug("finding ReportRisk instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReportRisk as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportRisk instances");
		try {
			String queryString = "from ReportRisk";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportRisk merge(ReportRisk detachedInstance) {
		log.debug("merging ReportRisk instance");
		try {
			ReportRisk result = (ReportRisk) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportRisk instance) {
		log.debug("attaching dirty ReportRisk instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportRisk instance) {
		log.debug("attaching clean ReportRisk instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static ReportRiskDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReportRiskDAO) ctx.getBean("ReportRiskDAO");
	}

	/*---------------------------------------下面是自己写的方法-----------------------------------*/
	
	public List<ReportRisk> getReportRiskListByUserId(String userId) {
		
		List<ReportRisk> rrList = new ArrayList<ReportRisk>();
		
		final String queryString = "FROM ReportRisk WHERE rerPromoterId='" + userId +"' order by rerReportName";
		
		rrList = getHibernateTemplate().execute(new HibernateCallback<List<ReportRisk>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportRisk> doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<ReportRisk> result =  session.createQuery(queryString).list();
				session.close();
				return result;
			}
		});
		
		return rrList;
		
	}
	
	//sql where in 获取报告的列表
	public List<ReportRisk> getReportRiskInList(List<String> reportIdList) {
		
		String reportIds = "";
		for(String reportId : reportIdList) {
			reportIds += "'" + reportId + "',";
		}
		if(!reportIds.equals("")) {
			reportIds = reportIds.substring(0, reportIds.length()-1);
			final String queryString = "FROM ReportRisk WHERE rerReportId IN (" + reportIds +")";
			return getHibernateTemplate().execute(new HibernateCallback<List<ReportRisk>>(){
				@SuppressWarnings("unchecked")
				@Override
				public List<ReportRisk> doInHibernate(Session session)
						throws HibernateException, SQLException {
					List<ReportRisk> result =  session.createQuery(queryString).list();
					session.close();
					return result;
				}
			});
		}
		
		return null;
		
	}
	
	// 根据reportId获取ReportRisk
	@SuppressWarnings("unchecked")
	public ReportRisk getReportRiskByReportId(String reportId) {
		List<ReportRisk> list = findByProperty("rerReportId", reportId);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	// 根据reportId更新ReportRisk中已经完成报告的数目
	public int updateDoneNum(String reportId) {
		
		final String queryString = "UPDATE ReportRisk SET rerDoneNum = rerDoneNum + 1 WHERE rerReportId='" + reportId +"'";
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result =  session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
	}
	
	// 根据reportId更新ReportRisk中已经接收报告的数目
	public int updateReceiveNum(String reportId) {
		
		final String queryString = "UPDATE ReportRisk SET rerReceiveNum = rerReceiveNum + 1 WHERE rerReportId='" + reportId +"'";
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result =  session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
	}
	
	// 撤回报告的时候设置ReportRisk的取消次数
	public int resetCancelNum(String reportId) {
		
		final String queryString = "UPDATE ReportRisk SET rerCancelNum = 0 WHERE rerReportId='" + reportId +"'";
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
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