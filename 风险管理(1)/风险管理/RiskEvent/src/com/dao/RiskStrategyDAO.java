package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.RiskStrategy;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskStrategy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.entity.RiskStrategy
 * @author MyEclipse Persistence Tools
 */

public class RiskStrategyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskStrategyDAO.class);
	// property constants
	public static final String STRAG_VALUE = "stragValue";
	public static final String STRAG_STATUE = "stragStatue";
	public static final String STRAG_COLOR = "stragColor";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskStrategy transientInstance) {
		log.debug("saving RiskStrategy instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskStrategy persistentInstance) {
		log.debug("deleting RiskStrategy instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void delete(final int strategyid) {
		log.debug("deleting RiskStrategy instance by id");
		try {
               	getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
				 SQLException {
				 String queryString = "delete from RiskStrategy where strategy.strategyId="+strategyid+"";
				 Query query = session.createQuery(queryString);
				 query.executeUpdate();
				 session.close();
				 return true;
				 }
				 });		
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public RiskStrategy findById(java.lang.Integer id) {
		log.debug("getting RiskStrategy instance with id: " + id);
		try {
			RiskStrategy instance = (RiskStrategy) getHibernateTemplate().get(
					"com.model.RiskStrategy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskStrategy instance) {
		log.debug("finding RiskStrategy instance by example");
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
	public List findByProperty(String propertyName, Object value,String condition) {
		log.debug("finding RiskStrategy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskStrategy as model where model."
					+ propertyName + "= ? "+condition;
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RiskStrategy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskStrategy as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStragValue(Object stragValue) {
		return findByProperty(STRAG_VALUE, stragValue);
	}

	public List findByStragStatue(Object stragStatue) {
		return findByProperty(STRAG_STATUE, stragStatue);
	}

	public List findByStragColor(Object stragColor) {
		return findByProperty(STRAG_COLOR, stragColor);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all RiskStrategy instances");
		try {
			String queryString = "from RiskStrategy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List FeedBack(int var,int shijiancha){
		String queryString = "from RiskStrategy as re where re.strategy.strategyId='"+var+"' and '"+shijiancha+"'<re.remark and '"+shijiancha+"'>=re.stragValue order by re.stragValue";
		return getHibernateTemplate().find(queryString);
	}
	 public void updatestate(Integer riskStrId,Integer stragValue,String stragStatue,String stragColor,int remark)
	   {
		   try{
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   String updateString="update RiskStrategy set stragValue='"+stragValue+"',stragStatue='"+stragStatue+"',stragColor='"+stragColor+"',remark='"+remark+"' where riskStrId='"+riskStrId+"'";
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
	public RiskStrategy merge(RiskStrategy detachedInstance) {
		log.debug("merging RiskStrategy instance");
		try {
			RiskStrategy result = (RiskStrategy) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskStrategy instance) {
		log.debug("attaching dirty RiskStrategy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskStrategy instance) {
		log.debug("attaching clean RiskStrategy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskStrategyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskStrategyDAO) ctx.getBean("RiskStrategyDAO");
	}
}