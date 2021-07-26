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

import com.model.ReportRiskDep;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportRiskDep entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportRiskDep
 * @author MyEclipse Persistence Tools
 */

public class ReportRiskDepDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportRiskDepDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportRiskDep transientInstance) {
		log.debug("saving ReportRiskDep instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportRiskDep persistentInstance) {
		log.debug("deleting ReportRiskDep instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	
	

	public ReportRiskDep findById(java.lang.Integer id) {
		log.debug("getting ReportRiskDep instance with id: " + id);
		try {
			ReportRiskDep instance = (ReportRiskDep) getHibernateTemplate()
					.get("com.model.ReportRiskDep", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportRiskDep instance) {
		log.debug("finding ReportRiskDep instance by example");
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
		log.debug("finding ReportRiskDep instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportRiskDep as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportRiskDep instances");
		try {
			String queryString = "from ReportRiskDep";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportRiskDep merge(ReportRiskDep detachedInstance) {
		log.debug("merging ReportRiskDep instance");
		try {
			ReportRiskDep result = (ReportRiskDep) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportRiskDep instance) {
		log.debug("attaching dirty ReportRiskDep instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportRiskDep instance) {
		log.debug("attaching clean ReportRiskDep instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static ReportRiskDepDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportRiskDepDAO) ctx.getBean("ReportRiskDepDAO");
	}
	
	//-------------------- 以下是自己写的函数------------------------------------------//
	
	//根据报告编号删除报告跟部门的对应关系
	public void deleteByReportId(String reportId) {
		final String queryString = "DELETE FROM ReportRiskDep WHERE rrdReportId = '" + reportId +"'";
		getHibernateTemplate().execute(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
			
		});
	}
	

	//根据reportId和部门Id获取ReportRiskDep
	public ReportRiskDep findReportRiskDepById(String reportId, String depId) {
		final String queryString = "FROM ReportRiskDep WHERE rrdDepId='"+depId+"' and rrdReportId='" + reportId +"'";
		return getHibernateTemplate().execute(new HibernateCallback<ReportRiskDep>(){
			@Override
			public ReportRiskDep doInHibernate(Session session)
					throws HibernateException, SQLException {
				ReportRiskDep result = (ReportRiskDep) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
		});
	}
	
	//根据reportId删除ReportRiskDep
	public void delReportRiskDepByReportId(String reportId) {
		final String queryString = "DELETE FROM ReportRiskDep WHERE rrdReportId='" + reportId +"'";
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
	
	//撤回报告时修改ReportRiskDep的状态(报告启动流程)
	public void revocationReportRiskDep(String reportId) {
		final String queryString = "UPDATE ReportRiskDep SET rrdStatus = '1' WHERE rrdReportId='" + reportId +"'";
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
	
	//根据reportId获取ReportRiskDep
	public List<ReportRiskDep> getReportRiskDepByReportId(String reportId) {
		final String queryString = "FROM ReportRiskDep WHERE rrdReportId='" + reportId +"'";
		List<ReportRiskDep> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportRiskDep>>(){
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportRiskDep> doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<ReportRiskDep> result =  session.createQuery(queryString).list();
				session.close();
				return result;
			}
		});
		return list;
	}
	
	// 批量新增
	public void insertBatch(final List<ReportRiskDep> list) {
		
		getHibernateTemplate().execute(new HibernateCallback<String>(){
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				if (list != null && list.size() > 0) {  
		            try {
		                session.beginTransaction();
		                ReportRiskDep rrd = null; 
		                for (int i = 0; i < list.size(); i++) {  
		                	rrd = list.get(i); 
		                    session.save(rrd); 
		                    // 批插入的对象立即写入数据库并释放内存  
		                    if (i % 10 == 0) {  
		                        session.flush();  
		                        session.clear();  
		                    }  
		                }  
		                session.getTransaction().commit(); // 提交事物  
		                return "success";
		            } catch (Exception e) {  
		                e.printStackTrace(); // 打印错误信息  
		                session.getTransaction().rollback(); // 出错将回滚事物  
		                throw new RuntimeException(e);
		            } finally {  
		               session.close();
		            }  
		        }
				return "fail";
			}
			
		});
		
	}
}