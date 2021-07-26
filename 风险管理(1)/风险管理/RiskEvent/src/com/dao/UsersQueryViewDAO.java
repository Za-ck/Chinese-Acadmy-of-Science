package com.dao;

import java.sql.SQLException;
import java.util.Collections;
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

import com.model.UsersQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * UsersQueryView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.UsersQueryView
 * @author MyEclipse Persistence Tools
 */

public class UsersQueryViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UsersQueryViewDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(UsersQueryView transientInstance) {
		log.debug("saving UsersQueryView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UsersQueryView persistentInstance) {
		log.debug("deleting UsersQueryView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UsersQueryView findById(String id) {
		log.debug("getting UsersQueryView instance with id: " + id);
		try {
			UsersQueryView instance = (UsersQueryView) getHibernateTemplate()
					.get("com.model.UsersQueryView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UsersQueryView instance) {
		log.debug("finding UsersQueryView instance by example");
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
		log.debug("finding UsersQueryView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UsersQueryView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		
		try {
			String queryString = "from UsersQueryView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public UsersQueryView merge(UsersQueryView detachedInstance) {
		
		try {
			UsersQueryView result = (UsersQueryView) getHibernateTemplate()
					.merge(detachedInstance);
			
			return result;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachDirty(UsersQueryView instance) {
		
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachClean(UsersQueryView instance) {
		
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public static UsersQueryViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UsersQueryViewDAO) ctx.getBean("UsersQueryViewDAO");
	}
	
	public List findAll(final int offset, final int pageSize,final String orderbys,final String state) {
		
		try {
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from UsersQueryView"+state).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery("from UsersQueryView "+state+(orderbys.equals("")?"order by depName asc,userId asc":"order by "+orderbys))
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
	//2019
	public List findAll1(final int offset, final int pageSize,final String orderbys,final String state) {
		
		try {
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from UsersQueryView"+state).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery("from UsersQueryView "+state+(orderbys.equals("")?"":"order by "+orderbys))
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
	@SuppressWarnings("unchecked")
	public List reqQuery(String dep,String name,String usernumber,final int offset, final int pageSize) {
		try {
			
			String queryString ="";
			if(dep.equals("--请选择--")){
				queryString = "from UsersQueryView  where userName like'%"+name+"%' and userId like'%"+usernumber+"%' and userRname='"+1+"'";
			}
			
			else{
				queryString = "from UsersQueryView  where userName like'%"+name+"%' and userId like'%"+usernumber+"%' and depName like '%"+dep+"%' and userRname='"+1+"'";
			}
			queryString+=" order by depName asc,userId asc";
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			final String queryString1 =queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
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
	//查询有效用户或者无效用户
	public List reqQueryNew(String dep,String name,String usernumber,String userState,final int offset, final int pageSize) {
		try {
			String state = "";
			if("--请选择--".equals(userState)){
				state = "";
			}else if("已启用".equals(userState)){
				state = " and userRname='1'";
			}else{
				state = " and userRname='0'";
			}
			String queryString ="";
			if(dep.equals("--请选择--")){
				queryString = "from UsersQueryView  where userName like'%"+name+"%' and userId like'%"+usernumber+"%'"+state;
				//queryString = "from UsersQueryView  where userName = '"+name+"' and userId = '"+usernumber+"' "+state;
			}
			
			else{
				queryString = "from UsersQueryView  where userName like'%"+name+"%' and userId like'%"+usernumber+"%' and depName like '%"+dep+"%'"+state;
				//queryString = "from UsersQueryView  where userName = '"+name+"' and userId = '"+usernumber+"' and depName = '"+dep+"' "+state;
			}
			queryString+=" order by depName asc,userId asc";
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			final String queryString1 =queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
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
	
	public List findflowtopeople(String depid,String role)
	{
		try {
			//排序是为了，流转到企业发展部时，默认显示刘丽娜刘工，因为刘工的角色是9,
			//and userRname='"+1+"' 表示有效用户；若userRname='"+0+"'，则表示被隐性删除的用户
			String queryString = "from UsersQueryView as us where us.depId='"+depid+"' and us.srReid='"+role+"' and us.userRname='"+1+"' order by us.srId desc ";
			List list = getHibernateTemplate().find(queryString);
			//Collections.shuffle(list);
			return list;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	//打回给录入部门员工,获取流转到人下拉框的，默认用户
	public List findflowtopeopleOne(String depid,String role,String userName)
	{
		try {
			//and userRname='"+1+"' 表示有效用户；若userRname='"+0+"'，则表示被隐性删除的用户
			String queryString = "from UsersQueryView as us where us.depId='"+depid+"' and us.srReid='"+role+"' and us.userRname='"+1+"' and us.userName='"+userName+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	//查找部门领导
	@SuppressWarnings("unchecked")
	public List<UsersQueryView> findLeaderByDepId(String depId) {
		final String queryString = "from UsersQueryView  where depId = '"+depId+"' and srReid='"+1+"' and userRname='"+1+"'";
		List<UsersQueryView> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				List<UsersQueryView> result = session.createQuery(queryString).list();
				session.close();
				return result;
			}
		});		
		Collections.shuffle(list);
		return list;
	}
	
	//查找部门员工
	@SuppressWarnings("unchecked")
	public List<UsersQueryView> findStaffByDepId(String depId) {
		final String queryString = "from UsersQueryView  where depId = '"+depId+"' and srReid='"+0+"' and userRname='"+1+"'";
		List<UsersQueryView> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				List<UsersQueryView> result = session.createQuery(queryString).list();
				session.close();
				return result;
			}
		});
		Collections.shuffle(list);
		return list;
	}
	
	//sql where in 获取用户的列表
	public List<UsersQueryView> getUsersInList(List<String> userIdlist) {
		
		String userIds = "";
		for(String userId : userIdlist) {
			userIds += "'" + userId + "',";
		}
		if(!userIds.equals("")) {
			userIds = userIds.substring(0, userIds.length()-1);
			final String queryString = "FROM UsersQueryView WHERE userId IN (" + userIds +")";
			return getHibernateTemplate().execute(new HibernateCallback<List<UsersQueryView>>(){
				@SuppressWarnings("unchecked")
				@Override
				public List<UsersQueryView> doInHibernate(Session session)
						throws HibernateException, SQLException {
					List<UsersQueryView> result =  session.createQuery(queryString).list();
					session.close();
					return result;
				}
			});
		}
		
		return null;
		
	}
	
	// 获取部门下的用户的数目
	public List<UsersQueryView> getUserNum(String depId) {
		
		final String queryString2 = "FROM UsersQueryView WHERE userRname='1' AND depId='" + depId +"'";
		return getHibernateTemplate().execute(new HibernateCallback<List<UsersQueryView>>(){

			@Override
			public List<UsersQueryView> doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<UsersQueryView> result = session.createQuery(queryString2).list();
				session.close();
				return result;
			}
			
		});
		
	}
	
	
}