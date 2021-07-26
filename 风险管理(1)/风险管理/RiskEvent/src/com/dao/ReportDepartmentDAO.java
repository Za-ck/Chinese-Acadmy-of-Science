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

import com.model.ReportDepartment;

/**
 	* A data access object (DAO) providing persistence and search support for ReportDepartment entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.model.ReportDepartment
  * @author MyEclipse Persistence Tools 
 */

public class ReportDepartmentDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ReportDepartmentDAO.class);
	

	protected void initDao() {
		//do nothing
	}
    
    public void save(ReportDepartment transientInstance) {
        log.debug("saving ReportDepartment instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ReportDepartment persistentInstance) {
        log.debug("deleting ReportDepartment instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ReportDepartment findById( java.lang.String id) {
        log.debug("getting ReportDepartment instance with id: " + id);
        try {
            ReportDepartment instance = (ReportDepartment) getHibernateTemplate()
                    .get("com.model.ReportDepartment", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ReportDepartment instance) {
        log.debug("finding ReportDepartment instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ReportDepartment instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ReportDepartment as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	public List findAll() {
		log.debug("finding all ReportDepartment instances");
		try {
			String queryString = "from ReportDepartment";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ReportDepartment merge(ReportDepartment detachedInstance) {
        log.debug("merging ReportDepartment instance");
        try {
            ReportDepartment result = (ReportDepartment) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ReportDepartment instance) {
        log.debug("attaching dirty ReportDepartment instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ReportDepartment instance) {
        log.debug("attaching clean ReportDepartment instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ReportDepartmentDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ReportDepartmentDAO) ctx.getBean("ReportDepartmentDAO");
	}
	
	/*------------------------------  以下是自己写的函数 --------------------------------------*/
	
	// 根据reportId和writerId获取ReportDepartment
	public ReportDepartment getSpecificReDepInput(String reportId, String writerId) {
		
		final String queryString = "FROM ReportDepartment WHERE rdReportId='" + reportId + "' and rdWriterId='" + writerId + "'";
		
		ReportDepartment rd = getHibernateTemplate().execute( new HibernateCallback<ReportDepartment>() {
			@Override
			public ReportDepartment doInHibernate(Session session) throws HibernateException, SQLException {
				ReportDepartment rrd = (ReportDepartment) session.createQuery(queryString).uniqueResult();
				session.close();
				return rrd;
			}
		});
		return rd;
	}
	
	public void revocateReportDep(String repdepId,String flowStatus) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ReportDepartment rd = findById(repdepId);
		rd.setRdFlowstatus(flowStatus);
		rd.setRdModifyDate(df.format(new Date()));
		
		merge(rd);
	}
	
	public List<ReportDepartment> getRepDepList(String reportId) {
		
		final String queryString = "FROM ReportDepartment WHERE rdReportId='" + reportId + "'";
		
		List<ReportDepartment> list = getHibernateTemplate().execute( new HibernateCallback<List<ReportDepartment>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportDepartment> doInHibernate(Session session) throws HibernateException, SQLException {
				List<ReportDepartment> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
	
	// 根据reportId和writerId获取ReportDepartment
	public ReportDepartment getReDepInput(String reportId, String depId) {
		
		final String queryString = "FROM ReportDepartment WHERE rdReportId='" + reportId + "' and rdDepId='" + depId + "'";
		
		ReportDepartment rd = getHibernateTemplate().execute( new HibernateCallback<ReportDepartment>() {
			@Override
			public ReportDepartment doInHibernate(Session session) throws HibernateException, SQLException {
				ReportDepartment rrd = (ReportDepartment) session.createQuery(queryString).uniqueResult();
				session.close();
				return rrd;
			}
		});
		return rd;
	}
	
}