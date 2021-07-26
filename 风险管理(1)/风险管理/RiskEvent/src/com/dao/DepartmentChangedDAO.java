package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Department;
import com.model.DepartmentChanged;
//import com.model.UsersQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * Department entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.DepartmentChanged
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unused")
public class DepartmentChangedDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DepartmentChangedDAO.class);
	// property constants
	/*public static final String DEP_NAME = "depName";
	public static final String DEP_SORT = "depSort";
	public static final String DEP_REMARK = "depRemark";
*/
	protected void initDao() {
		// do nothing
	}

	public void save(DepartmentChanged transientInstance) {
		log.debug("saving DepartmentChanged instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
	public void delete(DepartmentChanged persistentInstance) {
		log.debug("deleting DepartmentChanged instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DepartmentChanged findById(java.lang.String id) {
		log.debug("getting DepartmentChanged instance with id: " + id);
		try {
			DepartmentChanged instance = (DepartmentChanged) getHibernateTemplate().get(
					"com.model.DepartmentChanged", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
/*	public List searchAll(){
		try{
			String queryString="";
			queryString="SELECT count(*) from DepartmentChanged";
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString1).list();
							session.close();
							return result;
						}
					});
			return list;
		}catch(RuntimeException re){
			throw re;
		}
	}*/
	public List<DepartmentChanged> searchAll() 
	{
		
		try {
			String queryString = "from DepartmentChanged as model ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public List<DepartmentChanged> searchAllx(String DepId) 
	{try{
		String queryString="";
		queryString="from DepartmentChanged as model where model.depId='"+DepId+"'";
		final String queryString1 = queryString;
		List list = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						
						List result = session.createQuery(queryString1).list();
						session.close();
						return result;
					}
				});
		return list;
	}catch(RuntimeException re){
		throw re;
	}}
	@SuppressWarnings("unchecked")
	public List<DepartmentChanged> searchAllByDepId(String depId) 
	{
		
		try {
			String queryString = "from DepartmentChanged as model where model.depId='"+depId+"' order by model.id";
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public List findByExample(DepartmentChanged instance) {
		log.debug("finding DepartmentChanged instance by example");
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
		log.debug("finding DepartmentChanged instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DepartmentChanged as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	
	public List<DepartmentChanged> findAll() {
		
		try {
			String queryString = "from DepartmentChanged ORDER BY depName";

			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public DepartmentChanged merge(DepartmentChanged detachedInstance) {
		
		try {
			DepartmentChanged result = (DepartmentChanged) getHibernateTemplate().merge(
					detachedInstance);
			
			return result;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachDirty(DepartmentChanged instance) {
		
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachClean(DepartmentChanged instance) {
		
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public static DepartmentChangedDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DepartmentChangedDAO) ctx.getBean("DepartmentChangedDAO");
	}
}