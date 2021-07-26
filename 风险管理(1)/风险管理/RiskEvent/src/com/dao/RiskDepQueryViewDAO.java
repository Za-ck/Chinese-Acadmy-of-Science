package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ReportTaskRiskView;
import com.model.RiskDepQueryView;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskDepQueryView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskDepQueryView
 * @author MyEclipse Persistence Tools
 */

public class RiskDepQueryViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskDepQueryViewDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(RiskDepQueryView transientInstance) {
		log.debug("saving RiskDepQueryView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskDepQueryView persistentInstance) {
		log.debug("deleting RiskDepQueryView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskDepQueryView findById(String id) {
		log.debug("getting RiskDepQueryView instance with id: " + id);
		try {
			RiskDepQueryView instance = (RiskDepQueryView) getHibernateTemplate()
					.get("com.model.RiskDepQueryView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskDepQueryView instance) {
		log.debug("finding RiskDepQueryView instance by example");
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
		log.debug("finding RiskDepQueryView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskDepQueryView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RiskDepQueryView instances");
		try {
			String queryString = "from RiskDepQueryView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskDepQueryView merge(RiskDepQueryView detachedInstance) {
		log.debug("merging RiskDepQueryView instance");
		try {
			RiskDepQueryView result = (RiskDepQueryView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskDepQueryView instance) {
		log.debug("attaching dirty RiskDepQueryView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskDepQueryView instance) {
		log.debug("attaching clean RiskDepQueryView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskDepQueryViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskDepQueryViewDAO) ctx.getBean("RiskDepQueryViewDAO");
	}
	
	//查找待处理的风险事件
   @SuppressWarnings("unchecked")
   public List findevent(String verifier,String creator,final int offset, final int pageSize,String userPosition)
   {
	   try {
		   String strbuilder="";
		   //2018.12.17
		   if ("9".equals(userPosition)) {
			   //2018.12.13
			   //处于管理员的情况时
			   strbuilder+="from RiskDepQueryView where ((re_State!='*' and re_Verifier in (select distinct User_Id from Users where user_position="+userPosition+") or (re_State='0' and re_Creator='"+creator+"'))) ";
		   }else{
			   //2018.12.13
			   //不处于管理员的情况正常查询即可
			   strbuilder+="from RiskDepQueryView where ((re_State!='*' and re_Verifier='"+verifier+"') or (re_State='0' and re_Creator='"+creator+"')) ";
		   }
		   
		   final String queryString = "SELECT * "+strbuilder+"order by re_Modifydate desc";
		   final String queryString2 = "SELECT COUNT(*) " + strbuilder;
		   System.out.println(queryString2);
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createSQLQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createSQLQuery(queryString).addEntity("task", RiskDepQueryView.class)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
			
		} catch (RuntimeException re) {
			
			re.printStackTrace();
			return null;
		}
   }
 //查找待处理的风险事件
   @SuppressWarnings("unchecked")
   public List findevent1(String verifier,String creator,final int offset, final int pageSize,String dateFrom,String dateTo,String userPosition)
   {
	   String date="";
		  
	   try {
		  
		   date="re_Date"; 
			  //from RiskDepQueryView where ((reState!='*' and reVerifier='"+verifier+"') or (reState='0' and reCreator='"+creator+"')) 
		   String queryString = "";
		   
		   //2018.12.17
		   if ("9".equals(userPosition)) {
			   //2018.12.13
			   //处于管理员的情况时
			   queryString+="from RiskDepQueryView where ((re_State!='*' and re_Verifier in (select distinct User_Id from Users where user_position="+userPosition+") or (re_State='0' and re_Creator='"+creator+"'))) ";
		   }else{
			   //2018.12.13
			   //不处于管理员的情况正常查询即可
			   queryString+="from RiskDepQueryView where ((re_State!='*' and re_Verifier='"+verifier+"') or (re_State='0' and re_Creator='"+creator+"'))";
		   }
		   
		   if (dateFrom != null && !dateFrom.equals(""))
			   queryString += " and "+ date+" between '" + dateFrom+ "'";
		   else 
			   queryString += " and  " + date+ " between '1899-10-10'";
		   if (dateTo != null && !dateTo.equals(""))
			   queryString += " and '" + dateTo+ "'";
		   else 
			   queryString += " and '2999-12-12'";
		   
		   final String queryString1="select * "+queryString+" order by re_Modifydate desc";
		   final String queryString2 = "SELECT COUNT(*) " + queryString;
		   
		   System.out.println(queryString2);
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){
             
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createSQLQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createSQLQuery(queryString1).addEntity("task", RiskDepQueryView.class)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
			
		} catch (RuntimeException re) {
			
			re.printStackTrace();
			return null;
		}
   }
   

   // 查找所有待处理的风险事件
   public List findeventall(String verifier,String creator,String userPosition) {
	   
	   try {
		   final StringBuilder queryString=new StringBuilder();
		   if ("9".equals(userPosition)) {
			   //2018.12.13
			   //处于管理员的情况时
			   queryString.append("select * from RiskDepQueryView where ((re_State!='*' and re_Verifier in (select distinct User_Id from Users where user_position="+userPosition+") or (re_State='0' and re_Creator='"+creator+"'))) order by re_Modifydate desc");
		   }else{
			   //2018.12.13
			   //不处于管理员的情况正常查询即可
			   queryString.append("select * from RiskDepQueryView where ((re_State!='*' and re_Verifier='"+verifier+"') or (re_State='0' and re_Creator='"+creator+"')) order by re_Modifydate desc");
		   }
		   
		   List<RiskDepQueryView> list = getHibernateTemplate().execute(new HibernateCallback<List<RiskDepQueryView>>() {
				
				public List<RiskDepQueryView> doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					Query query = session.createSQLQuery(queryString.toString()).addEntity("task",RiskDepQueryView.class);
					//分页显示
					List<RiskDepQueryView> result = query.list();
					session.close();
					return result;
				}
			});		
			return list;
			
		} catch (RuntimeException re) {
			
			re.printStackTrace();
			return null;
		}
   }
	
}