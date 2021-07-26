package com.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
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

import com.model.FunctionLimit;
import com.model.RiskRecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskRecord entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.RiskRecord
 * @author MyEclipse Persistence Tools
 */

public class RiskRecordDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskRecordDAO.class);
	// property constants
	public static final String RR_VIEW = "rrView";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskRecord transientInstance) {
		log.debug("saving RiskRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskRecord persistentInstance) {
		log.debug("deleting RiskRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskRecord findById(java.lang.Integer id) {
		log.debug("getting RiskRecord instance with id: " + id);
		try {
			RiskRecord instance = (RiskRecord) getHibernateTemplate().get(
					"com.model.RiskRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskRecord instance) {
		log.debug("finding RiskRecord instance by example");
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

	public List findByProperty(String propertyName,String propertyName2, Object value,Object value2) {
		log.debug("finding RiskRecord instance with property: " + propertyName
				+ ", value: " + value);
		
		try {
			String queryString1 = "from RiskRecord as model where model."+ propertyName+"='"+value+"' and model."+propertyName2+"='"+value2+"' order by model.rrTime desc";	
			//System.out.println(queryString1);
			return getHibernateTemplate().find(queryString1);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
		
	}
	
	public List FindBy3PropertyDESC(String propertyName1, String value1, String propertyName2, String value2, 
			String propertyName3, String value3, String propertyName4)
	{
		try {
			String queryString1 = "from RiskRecord as model where model."  + propertyName1 + "='" + value1 + "' and model." + propertyName2 + "='" + value2 + "' and model." + propertyName3 + "='" + value3 + "' order by model."+propertyName4+" desc";
			return getHibernateTemplate().find(queryString1);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RiskRecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskRecord as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRrView(Object rrView) {
		return findByProperty(RR_VIEW, rrView);
	}

	public List findAll() {
		log.debug("finding all RiskRecord instances");
		try {
			String queryString = "from RiskRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskRecord merge(RiskRecord detachedInstance) {
		log.debug("merging RiskRecord instance");
		try {
			RiskRecord result = (RiskRecord) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskRecord instance) {
		log.debug("attaching dirty RiskRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskRecord instance) {
		log.debug("attaching clean RiskRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskRecordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskRecordDAO) ctx.getBean("RiskRecordDAO");
	}
	//��ѯ���һ�ε���˼�¼
	public List findpreRecord(String reid)
	{
		try {//select top 1 *
			String queryString = "from RiskRecord where rrReId='"+reid+"' order by rrTime desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	public List findcheckedevent(String verifier,final int offset, final int pageSize)
	{
		   try {
				String queryString = "from RiskRecord where RE_verifier='"+verifier+"' and RE";
				List results =getHibernateTemplate().find(queryString);
				return results;
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}		
	}
	
	public List findAllRecord(String reid)
	{
		try {//select top 1 *
			String queryString = "from RiskRecord where rrReId='"+reid+"' order by rrTime asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findRecordNum(String reid,String userid,String time)
	{
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();	
			final String queryString = "select * from RiskRecord where RR_reId='"+reid+"' and RR_verifier='"+userid+"' and RR_time < '"+time+"'";
			List list  = session.createSQLQuery(queryString).list();
			   //List list = session.createSQLQuery(queryString) 
				return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//查询风险事件的流转记录个数
	public Integer getCount(String reId) 
	{
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			final String queryString = "SELECT COUNT(*) from RiskRecord where RR_reId='" + reId + "'";
			return (Integer)session.createSQLQuery(queryString).uniqueResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//根据风险事件编号删除对应的记录表中的最后一条记录
	public void revocationRiskEvent(String reId) {
		
		final String queryString = "DELETE FROM RiskRecord re WHERE re.rrReId='"+reId +"' AND re.rrId IN (select max(rrId) from RiskRecord where re.rrReId = rrReId)";
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
	
	//根据风险事件编号获取最后最后一条审核记录
	public RiskRecord getLastRecord(String reId) {

		final String queryString = "FROM RiskRecord re WHERE re.rrReId='"+reId +"' AND re.rrId IN (select max(rrId) from RiskRecord where re.rrReId = rrReId)";
		return getHibernateTemplate().execute(new HibernateCallback<RiskRecord>(){
			@Override
			public RiskRecord doInHibernate(Session session)
					throws HibernateException, SQLException {
				RiskRecord result =  (RiskRecord) session.createQuery(queryString).uniqueResult();
				session.close();
				return result;
			}
		});
		
	}
}