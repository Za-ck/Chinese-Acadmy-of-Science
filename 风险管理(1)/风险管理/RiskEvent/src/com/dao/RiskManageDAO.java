package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.RiskManage;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskManage entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.RiskManage
 * @author MyEclipse Persistence Tools
 */

public class RiskManageDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskManageDAO.class);
	// property constants
	public static final String RM_CHANCE = "rmChance";
	public static final String RM_CONTROL = "rmControl";
	public static final String RM_REPLY = "rmReply";
	public static final String RM_STRATEGY = "rmStrategy";
	public static final String RM_PLANRES = "rmPlanres";
	public static final String RM_PLANDATE = "rmPlandate";
	public static final String RM_SIGN = "rmSign";
	public static final String RM_WARNVALUE = "rmWarnvalue";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskManage transientInstance) {
		log.debug("saving RiskManage instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskManage persistentInstance) {
		log.debug("deleting RiskManage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskManage findById(java.lang.String id) {
		log.debug("getting RiskManage instance with id: " + id);
		try {
			RiskManage instance = (RiskManage) getHibernateTemplate().get(
					"com.model.RiskManage", id);
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			throw new RuntimeException(re);
		}
	}

	public List findByExample(RiskManage instance) {
		log.debug("finding RiskManage instance by example");
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
		log.debug("finding RiskManage instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskManage as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRmChance(Object rmChance) {
		return findByProperty(RM_CHANCE, rmChance);
	}

	public List findByRmControl(Object rmControl) {
		return findByProperty(RM_CONTROL, rmControl);
	}

	public List findByRmReply(Object rmReply) {
		return findByProperty(RM_REPLY, rmReply);
	}

	public List findByRmStrategy(Object rmStrategy) {
		return findByProperty(RM_STRATEGY, rmStrategy);
	}

	public List findByRmPlanres(Object rmPlanres) {
		return findByProperty(RM_PLANRES, rmPlanres);
	}

	public List findByRmPlandate(Object rmPlandate) {
		return findByProperty(RM_PLANDATE, rmPlandate);
	}

	public List findByRmSign(Object rmSign) {
		return findByProperty(RM_SIGN, rmSign);
	}

	public List findByRmWarnvalue(Object rmWarnvalue) {
		return findByProperty(RM_WARNVALUE, rmWarnvalue);
	}

	public List findAll() {
		log.debug("finding all RiskManage instances");
		try {
			String queryString = "from RiskManage";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskManage merge(RiskManage detachedInstance) {
		log.debug("merging RiskManage instance");
		try {
			RiskManage result = (RiskManage) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
    
	public void attachDirty(RiskManage instance) {
		log.debug("attaching dirty RiskManage instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskManage instance) {
		log.debug("attaching clean RiskManage instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskManageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskManageDAO) ctx.getBean("RiskManageDAO");
	}
	
    public void updatemanage(RiskManage instance)
    {
    	try{
 		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
 		   Transaction trans=session.beginTransaction();
 		   String updateString="update RiskManage set RM_chance='"+instance.getRmChance()+"',RM_control='"+instance.getRmControl()+"',RM_plandate='"+instance.getRmPlandate()+"',RM_planres='"+instance.getRmPlanres()+"',RM_reply='"+instance.getRmReply()+"',RM_strategy='"+instance.getRmStrategy()+"' where RM_reId='"+instance.getRmReId()+"'";
 		   Query queryupdate=session.createQuery(updateString);
 		   int ret=queryupdate.executeUpdate();
 		   trans.commit();	   
 		  session.close();
 	   }
 	   catch(RuntimeException re)
 	   {
 		   throw re;
 	   }
    }
    //�����Ѵ���ķ����¼�������riskmanage���sign�ֶΣ�1��ʾ��ʵʩӦ�Դ�ʩ��0��ʾδʵʩ
    public void updatehandled(int sign,String id,String date, String name)
    {
    	try{
  		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
  		   Transaction trans=session.beginTransaction();
  		   String updateString="update RiskManage set RM_sign="+sign+", RM_Taketime='"+date+"', RM_replyman='"+name+"' where RM_reId='"+id+"'";
  		   Query queryupdate=session.createQuery(updateString);
  		   int ret=queryupdate.executeUpdate();
  		   trans.commit();	   
  		 session.close();
  	   }
  	   catch(RuntimeException re)
  	   {
  		   throw re;
  	   }
    }
    
}