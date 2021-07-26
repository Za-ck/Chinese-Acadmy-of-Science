package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
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

import com.model.FunctionLimit;
import com.model.Risk;

/**
 * A data access object (DAO) providing persistence and search support for
 * FunctionLimit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.zn.model.FunctionLimit
 * @author MyEclipse Persistence Tools
 */

public class FunctionLimitDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(FunctionLimitDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(FunctionLimit transientInstance) {
		log.debug("saving FunctionLimit instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FunctionLimit persistentInstance) {
		log.debug("deleting FunctionLimit instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FunctionLimit findById(java.lang.Integer id) {
		log.debug("getting FunctionLimit instance with id: " + id);
		try {
			FunctionLimit instance = (FunctionLimit) getHibernateTemplate()
					.get("com.zn.model.FunctionLimit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FunctionLimit instance) {
		log.debug("finding FunctionLimit instance by example");
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
		log.debug("finding FunctionLimit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FunctionLimit as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all FunctionLimit instances");
		try {
			String queryString = "from FunctionLimit";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FunctionLimit merge(FunctionLimit detachedInstance) {
		log.debug("merging FunctionLimit instance");
		try {
			FunctionLimit result = (FunctionLimit) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FunctionLimit instance) {
		log.debug("attaching dirty FunctionLimit instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FunctionLimit instance) {
		log.debug("attaching clean FunctionLimit instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FunctionLimitDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FunctionLimitDAO) ctx.getBean("FunctionLimitDAO");
	}
	
	//����û���ɫ��Ų�ѯȨ����Ϣ
	public List findbyroleIdPar(Integer roleid,Integer pid) {
		log.debug("finding all FunctionLimit instances");
		try {
			String queryString1 = "from FunctionLimit as functionLimit where functionLimit.systemRole.srId = '"+roleid+"' and functionLimit.functionModule.fmCategory="+pid+"";
			List<FunctionLimit> list=new LinkedList<FunctionLimit>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//����û���ɫ��Ų�ѯȨ����Ϣ
	public List findbyroleId(Integer userRole) {
		log.debug("finding all FunctionLimit instances");
		try {
			String queryString1 = "from FunctionLimit as functionLimit where functionLimit.systemRole.srId = '"+userRole+"'";
			List<FunctionLimit> list=new LinkedList<FunctionLimit>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean deletebyroleId(final Integer userRole) {
		
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				   public Object doInHibernate(Session session) throws HibernateException,
				 SQLException {
				 String queryString = "delete from FunctionLimit as functionLimit where functionLimit.systemRole.srId = '"+userRole+"'";
				 Query query = session.createQuery(queryString);
				 query.executeUpdate();
				 session.close();
				 return true;
				 }
				 });
		} catch (RuntimeException re) {
			
			throw re;
		}
		return false;
	}
	
	//����û���ɫ��ź�ģ���Ų�ѯ�Ƿ����
	public List findbyroleIdModule(Integer userRole,Integer moduleId) {
		log.debug("finding all FunctionLimit instances");
		try {
			String queryString1 = "from FunctionLimit as functionLimit where functionLimit.systemRole.srId = '"+userRole+"' and functionLimit.functionModule.fmId='"+moduleId+"'";
			List<FunctionLimit> list=new LinkedList<FunctionLimit>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}