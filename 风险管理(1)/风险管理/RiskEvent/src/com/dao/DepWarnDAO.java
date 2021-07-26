package com.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
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

import com.model.DepWarn;

/**
 * A data access object (DAO) providing persistence and search support for
 * DepWarn entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.DepWarn
 * @author MyEclipse Persistence Tools
 */

public class DepWarnDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(DepWarnDAO.class);
	// property constants
	public static final String DW_WARNNUM = "dwWarnnum";

	protected void initDao() {
		// do nothing
	}

	public void save(DepWarn transientInstance) {
		log.debug("saving DepWarn instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			re.printStackTrace();
		}
	}

	public void delete(DepWarn persistentInstance) {
		log.debug("deleting DepWarn instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			re.printStackTrace();
		}
	}

	public DepWarn findById(java.lang.Integer id) {
		log.debug("getting DepWarn instance with id: " + id);
		try {
			DepWarn instance = (DepWarn) getHibernateTemplate().get(
					"com.model.DepWarn", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			re.printStackTrace();
			return null;
		}
	}

	public List findByExample(DepWarn instance) {
		log.debug("finding DepWarn instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
			return null;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DepWarn instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DepWarn as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			re.printStackTrace();
			return null;
		}
	}

	public List findByDwWarnnum(Object dwWarnnum) {
		return findByProperty(DW_WARNNUM, dwWarnnum);
	}

	public List findAll() {
		log.debug("finding all DepWarn instances");
		try {
			String queryString = "from DepWarn";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}
	}
	public List findEvent() {
		log.debug("finding all DepWarn instances");
		try {
			String queryString = "select distinct riskEvent from DepWarn order by riskEvent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public List findAll(final int offset, final int pageSize) {
		log.debug("finding all DepWarn instances");
		try {
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from DepWarn").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery("from DepWarn order by DW_time desc")
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
			re.printStackTrace();
			return null;
		}
	}
	//多条件查询本部门信息
	public List allKindQuery(String reIndepName,String dateFrom,String dateTo,final int offset, final int pageSize) {
		/*String queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%'";
		return getHibernateTemplate().find(queryString);*/
try {
	String queryString ="";
	//if(event.equals("--请选择--"))
		queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%' and dwTime between '"+dateFrom+"' and '"+dateTo+"'";
	//else
		//queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%'and riskEvent='"+event+"' and dwTime between '"+dateFrom+"' and '"+dateTo+"'";
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
			re.printStackTrace();
			return null;
		}
	}
	
	public List reqQuery(String reIndepName,final int offset, final int pageSize) {
		/*String queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%'";
		return getHibernateTemplate().find(queryString);*/
		try {
			if("none1".equals(reIndepName)) {
				reIndepName = "";
			}
			final String queryString = "from DepWarn as re4 where re4.department.depId like '%"+reIndepName+"%'";
			int size=getHibernateTemplate().find(queryString).size();
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",size);
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
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
			re.printStackTrace();
			return null;
		}
	}
	public DepWarn merge(DepWarn detachedInstance) {
		log.debug("merging DepWarn instance");
		try {
			DepWarn result = (DepWarn) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			re.printStackTrace();
			return null;
		}
	}

	public void attachDirty(DepWarn instance) {
		log.debug("attaching dirty DepWarn instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			re.printStackTrace();
		}
	}

	public void attachClean(DepWarn instance) {
		log.debug("attaching clean DepWarn instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			re.printStackTrace();
		}
	}

	public static DepWarnDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DepWarnDAO) ctx.getBean("DepWarnDAO");
	}
}