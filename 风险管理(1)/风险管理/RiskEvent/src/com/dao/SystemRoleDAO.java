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

import com.model.SystemRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * SystemRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.zn.model.SystemRole
 * @author MyEclipse Persistence Tools
 */

public class SystemRoleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SystemRoleDAO.class);
	// property constants
	public static final String SR_NAME = "srName";

	protected void initDao() {
		// do nothing
	}

	public void save(SystemRole transientInstance) {
		log.debug("saving SystemRole instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(SystemRole transientInstance) {
		log.debug("saving SystemRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SystemRole persistentInstance) {
		log.debug("deleting SystemRole instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemRole findById(java.lang.Integer id) {
		log.debug("getting SystemRole instance with id: " + id);
		try {
			SystemRole instance = (SystemRole) getHibernateTemplate().get(
					"com.model.SystemRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SystemRole instance) {
		log.debug("finding SystemRole instance by example");
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
		log.debug("finding SystemRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SystemRole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySrName(Object srName) {
		return findByProperty(SR_NAME, srName);
	}
	public List findAll() {
		log.debug("finding all Department instances");
		try {
			String queryString = "from SystemRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll(final int offset, final int pageSize,String orderbys) {
		log.debug("finding all SystemRole instances");
		try {
			final String queryString = "from SystemRole "+(orderbys.equals("")?"":"order by "+orderbys) ;
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;						
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//多条件查询权限信息
	public List jueseQuery(String juese,final int offset, final int pageSize,String orderbys) {
		/*String queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%'";
		return getHibernateTemplate().find(queryString);*/
try {
	String queryString ="";
	if(juese.equals("--请选择--"))
		queryString = "from SystemRole"+(orderbys.equals("")?"":"order by "+orderbys);
	else
		queryString = "from SystemRole as re4 where re4.srName = '"+juese+"'"+(orderbys.equals("")?"":"order by "+orderbys);
			int size=getHibernateTemplate().find(queryString).size();
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",size);
			final String queryString1=queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString1)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SystemRole merge(SystemRole detachedInstance) {
		log.debug("merging SystemRole instance");
		try {
			SystemRole result = (SystemRole) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemRole instance) {
		log.debug("attaching dirty SystemRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemRole instance) {
		log.debug("attaching clean SystemRole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SystemRoleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SystemRoleDAO) ctx.getBean("SystemRoleDAO");
	}
}