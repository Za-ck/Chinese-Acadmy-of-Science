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
import com.model.UsersQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * Department entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.Department
 * @author MyEclipse Persistence Tools
 */

public class DepartmentDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DepartmentDAO.class);
	// property constants
	public static final String DEP_NAME = "depName";
	public static final String DEP_SORT = "depSort";
	public static final String DEP_REMARK = "depRemark";

	protected void initDao() {
		// do nothing
	}

	public void save(Department transientInstance) {
		log.debug("saving Department instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Department persistentInstance) {
		log.debug("deleting Department instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List<Department> searchAllByDepId(String depId) 
	{
		
		try {
			String queryString = "from Department as model where model.depId='"+depId+"'";
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	public Department findById(java.lang.String id) {
		log.debug("getting Department instance with id: " + id);
		try {
			Department instance = (Department) getHibernateTemplate().get(
					"com.model.Department", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List searchDepsort(String DepId){
		try{
			String queryString="";
			queryString="from Department as model where model.depId='"+DepId+"'";
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
	}
	
	

	
	public List findByExample(Department instance) {
		log.debug("finding Department instance by example");
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
		log.debug("finding Department instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Department as model where model."
					+ propertyName + "= ? ORDER BY depName";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	
	public List findByDepName(Object depName) {
		return findByProperty(DEP_NAME, depName);
	}

	public List findByDepSort(Object depSort) {
		return findByProperty(DEP_SORT, depSort);
	}

	public List findByDepRemark(Object depRemark) {
		return findByProperty(DEP_REMARK, depRemark);
	}

	public List<Department> findAll() {
		
		try {
			String queryString = "from Department ORDER BY depName";

			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public List<Department> findDepQuery() 
	{
		
		try {
			String queryString = "from Department as model where model.depId!='FB' and model.depId!='YZBGH' ORDER BY depName ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public List findALLrelevantdep() {
		
		try {
			String queryString = "from Department where Dep_sort>0";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public List findAll(final int offset, final int pageSize) {
		
		try {
			final String queryString = "from Department ORDER BY depName ";
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
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
			
			throw re;
		}
	}
	public Department merge(Department detachedInstance) {
		
		try {
			Department result = (Department) getHibernateTemplate().merge(
					detachedInstance);
			
			return result;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachDirty(Department instance) {
		
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachClean(Department instance) {
		
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public static DepartmentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DepartmentDAO) ctx.getBean("DepartmentDAO");
	}
	
	public List search(String depNameString,final int offset, final int pageSize) {
		log.debug("finding all Department instances");
		try {
			final String queryString = "from Department as dep where dep.depName like '%"+depNameString+"%'  ORDER BY depName";
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


	//根据部门Id查找部门名称
	public String getdepname(String userDep) {
		log.debug("finding depname by depId");
		try {
			String queryString = "select distinct depName from Department as model where model.depId='"+userDep+"'";
			List list = getHibernateTemplate().find(queryString);
			String depname="";
			if(list!=null){
				for(Object depList : list)
				{ 
					depname=(String) depList;
				}
			}
			return depname;
		} catch (RuntimeException re) {
			log.error("find depname failed", re);
			throw re;
		}
	}

	public String getDepSort(String depId) {
		
		try {
			String queryString = "select distinct depSort from Department as model where model.depId='"+depId+"'";
			List list = getHibernateTemplate().find(queryString);
			String depSort="";
			if(list!=null){
				for(Object sortList : list)
				{ 
					depSort= sortList.toString();
				}
			}
			return depSort;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	
	public void deleteBySql(String depId) {
		
		final String queryString2 = "DELETE FROM Department where depId='" + depId + "'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString2).executeUpdate();
				session.close();
				return result;
			}
			
		});
		
	}
	
	public List<Department> getDepAssess() {
		
		try {
			//获取需考核的部门，并排序
			//2019.01.08
			String queryString = "from Department as model where model.depAssess=1 ORDER BY depName";
			List<Department> list = getHibernateTemplate().find(queryString);
			return list;
		} catch (Exception re) {
			
			throw new RuntimeException(re);
		}
	}
	
	// 设置考核部门
	public void depAssess(List<String> depIdlist) {
		
		String depIds = "";
		for(String depId : depIdlist) {
			depIds += "'" + depId + "',";
		}
		if(!depIds.equals("")) {
			depIds = depIds.substring(0, depIds.length()-1);
			final String queryString = "UPDATE Department SET depAssess = 1 WHERE depId IN (" + depIds +")";
			getHibernateTemplate().execute(new HibernateCallback<Integer>(){
				@Override
				public Integer doInHibernate(Session session)
						throws HibernateException, SQLException {
					int result =  session.createQuery(queryString).executeUpdate();
					session.close();
					return result;
				}
			});
			
			final String queryString1 = "UPDATE Department SET depAssess = 0 WHERE depId NOT IN (" + depIds +")";
			getHibernateTemplate().execute(new HibernateCallback<Integer>(){
				@Override
				public Integer doInHibernate(Session session)
						throws HibernateException, SQLException {
					int result =  session.createQuery(queryString1).executeUpdate();
					session.close();
					return result;
				}
			});
		}
		
	}
}