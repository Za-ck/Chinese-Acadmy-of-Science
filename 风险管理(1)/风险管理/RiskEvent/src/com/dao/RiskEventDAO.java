package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import sun.text.resources.FormatData;

import com.model.Department;
import com.model.Risk;
import com.model.RiskEvent;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskEvent entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.RiskEvent
 * @author MyEclipse Persistence Tools
 */

public class RiskEventDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskEventDAO.class);
	// property constants
	public static final String RE_TYPE = "reType";
	public static final String RE_EVENT_ID = "reEventId";
	public static final String RE_REMARK = "reRemark";
	public static final String RE_STATE = "reState";
	public static final String RE_INDEP = "reIndep";
	public static final String RE_CREATOR = "reCreator";
	public static final String RE_MODIFIER = "reModifier";
	public static final String RE_CHECKFLAG = "reCheckFlag";
	public static final String RE_LASTDATE = "reLastDate";


	protected void initDao() {
		// do nothing
	}

	public void save(RiskEvent transientInstance) {
		log.debug("saving RiskEvent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			//throw re;
			re.printStackTrace();
		}
	}

	public void delete(RiskEvent persistentInstance) {
		log.debug("deleting RiskEvent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			//throw re;
			re.printStackTrace();
		}
	}
	
	//根据风险事件的ID删除风险事件
	@SuppressWarnings("unchecked")
	public void delete(String reId) {
		log.debug("deleting RiskEvent instance");
		try {
			//查询所有的记录条数
			final String delsql = "DELETE FROM RiskEvent WHERE RE_Id ='" + reId + "'";
						ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								int result = session.createSQLQuery(delsql).executeUpdate();  
								session.close();
								return result;
							}
							
						}));
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			//throw re;
			re.printStackTrace();
		}
	} 

	public RiskEvent findById(java.lang.String id) {
		log.debug("getting RiskEvent instance with id: " + id);
		try {
			RiskEvent instance = (RiskEvent) getHibernateTemplate().get(
					"com.model.RiskEvent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}

	public List findByExample(RiskEvent instance) {
		log.debug("finding RiskEvent instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RiskEvent instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskEvent as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}
	//调用存储过程，将信息插入RiskEvent、RiskImpact、RiskManage表中
	@SuppressWarnings("deprecation")
	public String callinputProc(RiskEvent instance,String depart) throws SQLException
	{
		try{
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Connection con = session.connection();   
		String procedure = "{call proc_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(procedure);
		cstmt.setString(1, instance.getReId());
		cstmt.setString(2, instance.getReType());
		cstmt.setString(3,instance.getReRiskId());
		cstmt.setString(4, instance.getReEventId());
		cstmt.setString(5, instance.getReDetail());
		cstmt.setString(6,instance.getReState());
		cstmt.setString(7, instance.getReIndep());
		cstmt.setString(8, instance.getReDate().toString());
		cstmt.setString(9, instance.getReCreator());
		cstmt.setString(10, depart);
		cstmt.setString(11, instance.getReModifier());
		cstmt.setString(12, instance.getReModifydate());
		cstmt.setString(13, instance.getReEventname());
		cstmt.setString(14, instance.getReLastdate());
		cstmt.setInt(15, instance.getReCheckflag());
		cstmt.execute();   		
		session.close();
		}
		catch(SQLException e){
			//throw e;
			e.printStackTrace();
			}
		
		return "success";
	}

	public List findByReType(Object reType) {
		return findByProperty(RE_TYPE, reType);
	}

	public List findByReEventId(Object reEventId) {
		return findByProperty(RE_EVENT_ID, reEventId);
	}

	public List findByReRemark(Object reRemark) {
		return findByProperty(RE_REMARK, reRemark);
	}
	
    //设外键后，只能通过department进行查询
	public List findByDepartment(Department department) {
		return findByProperty("department", department);
	}
	public List findByReCreator(Object reCreator) {
		return findByProperty(RE_CREATOR, reCreator);
	}

	public List findByReModifier(Object reModifier) {
		return findByProperty(RE_MODIFIER, reModifier);
	}

	public List findAll() {
		log.debug("finding all RiskEvent instances");
		try {
			String queryString = "from RiskEvent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize,final String depId,final String orderbys) {
		log.debug("finding all RiskEvent instances");
		try {
			final String queryString = "SELECT COUNT(*) from RiskEvent as re where re.reIndep='"+depId+"'";
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString).uniqueResult().toString();
					session.close();
					return result;
				}
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from RiskEvent as re where re.reIndep='"+depId+"'"+(orderbys.equals("")?"order by re.reModifydate desc":"order by re."+orderbys))
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
	
	public RiskEvent merge(RiskEvent detachedInstance) {
		log.debug("merging RiskEvent instance");
		try {
			RiskEvent result = (RiskEvent) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}
   //事件处理中，显示所有部门的所有风险事件
	public List findAllEvent(final int offset, final int pageSize,final String orderbys) {
		log.debug("finding all RiskEvent instances");
		try {
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from RiskEvent as re").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery("from RiskEvent as re "+(orderbys.equals("")?"order by re.reModifydate desc":"order by re."+orderbys))
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
			//throw re;
			re.printStackTrace();
			return null;
		}
	}
	//事件处理中，高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId、部门名称reIndep
	@SuppressWarnings("unchecked")
	public List findEventByQueryCondition(String dateFrom,String dateTo,String riskId,String reIndep,String stateId, final int offset, final int pageSize,final String orderbys) {
		try {
			
			String queryState="";
			 if ("0".equals(stateId)) 
			    {
				   queryState="";
				}
			    else if ("1".equals(stateId)) {
			    	queryState=" and re7.reState='*' ";
			    }
			    else if ("2".equals(stateId)) {
			    	queryState=" and re7.reState='0' and re7.reAct='0' ";
			    }
			    else if ("3".equals(stateId)) {
			    	queryState=" and re7.reState='0' and re7.reAct!='0' ";
			    }
			    else if ("4".equals(stateId)) {
			    	queryState=" and re7.reState!='*' and re7.reState!='0' and re7.reAct='0' ";
			    }
			    else if ("5".equals(stateId)) {
			    	queryState=" and re7.reState='1' and re7.reAct!='0' ";

			    }
			    else if ("6".equals(stateId)) {
			    	queryState=" and re7.reState!='*' and re7.reState!='0' and re7.reState!='1' and re7.reAct!='0' ";

				}
			String queryString ="";
			if(riskId.equals("none1") && reIndep.equals("none1")){
				queryString = "from RiskEvent as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' "+queryState+(orderbys.equals("")?"order by re7.reDate desc":"order by re7."+orderbys);
			}
			else if(riskId.equals("none1") && !reIndep.equals("none1")){
				queryString = "from RiskEvent as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' and re7.reIndep = '"+reIndep+"' "+queryState+(orderbys.equals("")?"order by re7.reDate desc":"order by re7."+orderbys);
			}
			else if(!riskId.equals("none1") && reIndep.equals("none1")){
				queryString = "from RiskEvent as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' and re7.reRiskId = '"+riskId+"' "+queryState+(orderbys.equals("")?"order by re7.reDate desc":"order by re7."+orderbys);
			}
			else{
				queryString = "from RiskEvent as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' and re7.reIndep = '"+reIndep+"' and re7.reRiskId = '"+riskId+"' "+queryState+(orderbys.equals("")?"order by re7.reDate desc":"order by re7."+orderbys);
			}

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
			//throw re;
			re.printStackTrace();
			return null;
		}	
	}
	
	public void attachDirty(RiskEvent instance) {
		log.debug("attaching dirty RiskEvent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			//throw re;
			re.printStackTrace();
		}
	}

	public void attachClean(RiskEvent instance) {
		log.debug("attaching clean RiskEvent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			//throw re;
			re.printStackTrace();
		}
	}

	public static RiskEventDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskEventDAO) ctx.getBean("RiskEventDAO");
	}
	//根据录入时间间隔，查询此段时间内录入的风险事件
	public List findByTimeBetween(String dateBegin,String dateEnd) {
		String queryString = "from RiskEvent as re2 where re2.reDate between '"+dateBegin+"' and '"+dateEnd+"' order by re2.reDate desc";
		return getHibernateTemplate().find(queryString);		
	} 
	
	//RiskEventInputQueryAction,高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId、部门ID reIndep
	@SuppressWarnings("unchecked")
	public List findByQueryCondition(String dateFrom,String dateTo,String riskId,String reIndep,final int offset, final int pageSize,final String depId,final String orderbys) {
		try {
			
			String queryString ="";
			if(riskId.equals("none1") && reIndep.equals("none1")){
				queryString = "from RiskEvent as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"' and re4.reIndep='"+depId+"'"+(orderbys.equals("")?"order by re4.reDate desc":"order by re4."+orderbys);
			}
			else if(riskId.equals("none1") && !reIndep.equals("none1")){
				queryString = "from RiskEvent as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"' and re4.reIndep = '"+reIndep+"' and re4.reIndep='"+depId+"'"+(orderbys.equals("")?"order by re4.reDate desc":"order by re4."+orderbys);
			}
			else if(!riskId.equals("none1") && reIndep.equals("none1")){
				queryString = "from RiskEvent as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"' and re4.reRiskId = '"+riskId+"' and re4.reIndep='"+depId+"'"+(orderbys.equals("")?"order by re4.reDate desc":"order by re4."+orderbys);
			}
			else{
				queryString = "from RiskEvent as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"' and re4.reIndep = '"+reIndep+"' and re4.reRiskId = '"+riskId+"' and re4.reIndep='"+depId+"'"+(orderbys.equals("")?"order by re4.reDate desc":"order by re4."+orderbys);
			}
			final String queryString1 = queryString;
			final String queryString2 = "SELECT COUNT(*) " + queryString.substring(0, queryString.indexOf("order by"));
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
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
			//throw re;
			re.printStackTrace();
			return null;
		}	
	}
	//RiskEventQueryAction,条件查询，查询条件:日期始dateFrom、日期终dateTo、录入部门名称reIndep、管理责任部门名称riskDep
	public List reqQuery(String dateFrom,String dateTo,String reIndep,String riskDep) {
		String queryString = "from RiskEvent as re4 where re4.reDate between '"+dateFrom+"' and '"+dateTo+"' and re4.department.depName like '%"+reIndep+"%' and re4.risk.riskDep like '%"+riskDep+"%' order by re4.reDate desc";
		return getHibernateTemplate().find(queryString);		
	}
	
	//每个部门员工登录进去后，可查询的更显时间信息
	public List findByPerson(Department department) {
		String queryString = "from RiskEvent as re where re.reIndep = '"+department.getDepId()+"' order by re.reDate desc";
		return getHibernateTemplate().find(queryString);		
	}

	public List findByEventIdDESC(String value,String year)
	{
		try {
			String queryString = "from RiskEvent as re5 where re5.reRiskId = '"+value+"' and re5.reDate> '"+year+"' order by re5.reEventId desc";
			//String queryString = "from RiskEvent as re5 where re5.risk= ? order by re5.reEventId desc";
			//List results =getHibernateTemplate().find(queryString, value);
			List results =getHibernateTemplate().find(queryString);
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}		
	}
	@SuppressWarnings("unchecked")
	public List<RiskEvent> findByEventIdDESC(String value)
	{
		try {
			String riskCurrent="1";
			String queryString = "from RiskEvent as re5 where re5.reRiskId = '"+value+"' order by re5.reEventId desc";
			//String queryString = "from RiskEvent as re5 where re5.risk= ? order by re5.reEventId desc";
			//List results =getHibernateTemplate().find(queryString, value);
			List<RiskEvent> results =getHibernateTemplate().find(queryString);
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}		
	}


	//流转时，选择流转到的人员之后，更新event信息，将下一个需要审核的审核人和当前审核操作等信息更新
   public void updateevent(String id,String riskStatus,String verifier,String act)
   {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reState='"+riskStatus+"',reVerifier='"+verifier+"',reAct='"+act+"',reRemark=' ' where reId='"+id+"'";
		   
		   Query queryupdate=session.createQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		   re.printStackTrace();
		   //return;
	   }
   }
    //院领导通过时更新event信息
   public void updatepassevent(String id,String riskStatus,String act)
   {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set RE_state='"+riskStatus+"',RE_verifier=null,RE_act='"+act+"',RE_remark=' ' where RE_Id='"+id+"'";
		   Query queryupdate=session.createSQLQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
}
   //录入人录完风险信息后，点击‘提交’，提交时更新event的状态信息，将下一个审核人的编号更新到RiskEvent表中
   public void updatestate(String id,String riskStatus,String verifier)
   {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set RE_state='"+riskStatus+"',RE_verifier='"+verifier+"',RE_act='1',RE_remark=' ' where RE_Id='"+id+"'";
		   Query queryupdate=session.createQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
   }
   //查找待审核的风险事件
   public List findevent(String verifier,final int offset, final int pageSize)
   {
	   try {
		   final String queryString = "from RiskEvent where RE_state!='*' and RE_state!='0' and RE_verifier='"+verifier+"' order by RE_modifydate desc";
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
			log.error("find all failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
   }
 
   //查找风险事件库的已办
   @SuppressWarnings("unchecked")
   public List findcheckedevent(String verifier,final int offset, final int pageSize,String userPosition)
   {
	   try {//根据RiskRecord表中审核人的记录，查找出已审核的风险事件
		   Session session = getHibernateTemplate().getSessionFactory().openSession();	
		   //final String queryString = "select t.*,c.Dep_name,b.RE_creator,b.RE_state,b.RE_date,b.RE_act,b.RE_riskId,b.RE_eventname,a.RR_time from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier='"+verifier+"' and a.RR_Id in (select max(RR_Id) from RiskRecord where a.RR_verifier=RR_verifier and a.RR_reId = RR_reId)) t left join RiskEvent b on t.RR_reId = b.RE_Id left join Department c on c.Dep_Id = b.RE_indep order by RR_time desc";
		   //final String queryString = "select t.*,b.RE_indep,b.RE_creator,b.RE_state,b.RE_date,b.RE_act,b.RE_riskId from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier='"+verifier+"') t left join RiskEvent b on t.RR_reId = b.RE_Id";
		   String begin = "select t.*,c.Dep_name,b.RE_creator,b.RE_state,b.RE_date,b.RE_act,b.RE_riskId,b.RE_eventname,t.RR_time,d.Risk_name ";
		   String queryadvance = "";
		   //2018.12.18
		   if ("9".equals(userPosition)) {
			   queryadvance+=" from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier in (select distinct user_id from users where user_position="+userPosition+")";
		   }else{
			   queryadvance+=" from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier='"+verifier+"'";
		   }
		   
		   queryadvance += " and a.RR_Id in (select max(RR_Id) from RiskRecord where a.RR_verifier=RR_verifier and a.RR_reId = RR_reId)";
		   queryadvance+=") t left join RiskEvent b on t.RR_reId = b.RE_Id left join Department c on c.Dep_Id = b.RE_indep " ;
		   queryadvance += " left join Risk d on d.Risk_Id = b.RE_riskId where 1=1 and b.RE_state != '0' order by t.RR_time desc";
		   final String queryString = begin + queryadvance;
		   //Query query = session.createSQLQuery(queryadvance);
		   //System.out.println(queryString);
		   //ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
		   final String queryString2 = "select count(*) " + queryadvance.substring(0, queryadvance.indexOf("order by"));
		   
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
					
					List result = session.createSQLQuery(queryString)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
			
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
   }
   //根据条件查找已审核的风险事件
   @SuppressWarnings("unchecked")
   public List findadcheckedevent(String verifier,String dateFrom,String dateTo,final int offset, final int pageSize,String orderbys,String userPosition)
   {
	   try {//CheckedFlowQuery页面的高级查询
		   String begin = "select t.*,c.Dep_name,b.RE_creator,b.RE_state,b.RE_date,b.RE_act,b.RE_riskId,b.RE_eventname,t.RR_time,d.Risk_name ";
		   String queryadvance = "";
		   //2018.12.18
		   if ("9".equals(userPosition)) {
			   queryadvance+="from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier in (select distinct user_id from users where user_position="+userPosition+")";
		   }else{
			   queryadvance+="from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier='"+verifier+"'";
		   }
		   
		   if (dateFrom != null && !dateFrom.equals(""))
			   queryadvance += " and a.RR_time between '" + dateFrom+ "'";
			else 
				queryadvance += " and a.RR_time between '1899-10-10'";
		   if (dateTo != null && !dateTo.equals(""))
			   queryadvance += " and '" + dateTo+ "'";
			else 
				queryadvance += " and '2999-12-12'";
		   queryadvance += " and a.RR_Id in (select max(RR_Id) from RiskRecord where a.RR_verifier=RR_verifier and a.RR_reId = RR_reId)";
		   queryadvance += " and a.RR_view !='撤回'";
		   queryadvance+=") t left join RiskEvent b on t.RR_reId = b.RE_Id left join Department c on c.Dep_Id = b.RE_indep " ;
		   queryadvance += " left join Risk d on d.Risk_Id = b.RE_riskId  where 1=1 and (b.RE_state != '0' or (b.RE_state ='0' and b.RE_act = '0')) order by ";
		   
		   if(orderbys != null && !orderbys.equals("")) {
			   queryadvance += orderbys + ",";
			}
		   queryadvance += "t.RR_time desc";
		   
		   final String queryString = begin + queryadvance; 
//		   //System.out.printf(queryString);
//		   Session session = getHibernateTemplate().getSessionFactory().openSession();	
//		   //final String queryString = "select t.*,b.RE_indep,b.RE_creator,b.RE_state,b.RE_date,b.RE_act from (select distinct a.RR_reId,a.RR_time,a.RR_verifier,a.RR_behaviour from RiskRecord a where a.RR_verifier='"+verifier+"') t left join RiskEvent b on t.RR_reId = b.RE_Id";
//		   Query query = session.createSQLQuery(queryString);
//		   //System.out.printf("zzzz");
//		   ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
		   final String queryString2 = "select count(*) " + queryadvance.substring(0, queryadvance.indexOf("order by"));
		   
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
					
					List result = session.createSQLQuery(queryString)
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
			//throw re;
			re.printStackTrace();
			return null;
		}
   }
   //流转的‘审核’页面中，点击“保存”可以暂存当前审核人的审核意见到RiskEvent的remark字段中
   public void updatetempview(String id,String view)
   {
	   try{
		   
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set RE_remark='"+view+"' where RE_Id='"+id+"'";
		   Query queryupdate=session.createQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
   }
   
   //"风险处理查询"页面查看本部门的所有已发布的风险信息
   public List findhandlerisk(String dep,final int offset, final int pageSize)
   {
	   try {
		   Session session = getHibernateTemplate().getSessionFactory().openSession();	
		   final String queryString = "select re.RE_Id,re.RE_state,re.RE_creator,re.RE_date,rm.RM_plandate,rm.RM_taketime,rm.RM_sign from RiskEvent re,RiskManage rm where re.RE_indep='"+dep+"' and re.RE_Id=rm.RM_reId and re.RE_state='*' order by re.RE_date desc";
		   Query query = session.createSQLQuery(queryString);
		   ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createSQLQuery(queryString)
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
   
 
 //"风险处理查询"页面的高级查询
   public List findhandlebyCondition(String dep,String dateFrom,String dateTo,String riskId,String handle,final int offset, final int pageSize)
   {
	   try {
		   String queryadvance = "select re.RE_Id,re.RE_state,re.RE_creator,re.RE_date,rm.RM_plandate,rm.RM_taketime,rm.RM_sign from RiskEvent re,RiskManage rm where re.RE_indep='"+dep+"' and re.RE_Id=rm.RM_reId and re.RE_state='*'";
		   if(!riskId.equals("0"))
			   queryadvance += " and re.RE_riskId = '" + riskId+ "'";
		   if(!handle.equals("-1"))
			   queryadvance += " and rm.RM_sign = " + handle+ "";
		   if (!dateFrom.equals(""))
			   queryadvance += " and re.RE_date between '" + dateFrom+ "'";
			else 
				queryadvance += " and re.RE_date between '1899-10-10'";
		   if (!dateTo.equals(""))
			   queryadvance += " and '" + dateTo+ "'";
			else 
				queryadvance += " and '2999-12-12'";
		   queryadvance += "order by re.RE_date desc";
		   final String queryString = queryadvance; 
		   Session session = getHibernateTemplate().getSessionFactory().openSession();	
		   Query query = session.createSQLQuery(queryString);
		   ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createSQLQuery(queryString)
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

   
   //点击“修改”进入修改页面，更新riskevent的基本信息
   public void updateeventinput(RiskEvent riskevent)
   {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set RE_detail='"+riskevent.getReDetail()+"',RE_eventname='"+riskevent.getReEventname()+"',RE_modifydate='"+riskevent.getReModifydate()+"',RE_modifier='"+riskevent.getReModifier()+"' where RE_Id='"+riskevent.getReId()+"'";
		   Query queryupdate=session.createQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
   }
   
 //查找待审核的风险事件的个数，用于页面提醒
   public List findeventnum(String verifier)
   {
	   try {
		   String queryString = "from RiskEvent where ((reState!='*' and reState!='0') or (reState='0' and reAct='0')) and reVerifier='"+verifier+"'";
		   //String queryString = "from RiskEvent where RE_state!='*' and RE_state!='0' and RE_verifier='"+verifier+"'";
		   List results =getHibernateTemplate().find(queryString);
		   return results;
			
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
   }
   //每个月是否录入风险事件的提醒，通过查询RiskEvent表中是否存在录入时间是当月内 录入部门是用户所在部门的风险事件
   public List findnumremind(String dep,String monbegin,String monend)
   {
	   try{
		   String queryString="from RiskEvent where RE_indep='"+dep+"' and RE_date between '"+monbegin+"' and '"+monend+"'";
		   List results =getHibernateTemplate().find(queryString);
			return results;	
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
   }

   
   // 更新风险事件的最后处理时间
   public void updateLastDate(String reId, String dateStr) {
	   
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reLastdate='"+dateStr+"' where reId='"+reId+"'";
		   Query queryupdate=session.createQuery(updateString);
		   int ret=queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		   re.printStackTrace();
		   //return;
	   }
	   
   }
 //风险流转中事件数量查询
   public List<?> findInPassCount(String choosedId,String Year,String YearTo,String Quarter,String QuarterTo ){
	   try {
		   String datem="";
		   //lect re.RE_Id,re.RE_state,re.RE_creator,re.RE_date,rm.RM_plandate,rm.RM_taketime,rm.RM_sign from RiskEvent re,RiskManage rm where re.RE_indep='"+dep+"' and re.RE_Id=rm.RM_reId and re.RE_state='*'
		  // re.RE_indep,String queryString ="Select count(*),reIndep,,rm.RM_plandate from RiskEvent Model,RiskManage rm where reCheckflag='0' ";
		 String queryString ="select count(*),re.reIndep,rm.rmTaketime from RiskEvent re,RiskManage rm where  re.reId=rm.rmReId ";//re.reCheckflag='0'  and
		  
		   //修改部分
		   if("2".equals(choosedId)){
			   datem="re.reLastdate";
		   }
		   else if("3".equals(choosedId)){
			  // queryString="select re.reIndep,rm.rmPlandate from RiskEvent re,RiskManage rm where re.reId=rm.rmReId ";
			   datem="rm.rmTaketime";
			   if(Integer.parseInt(Year)<2017){
				   Year="2017";
			   }
		   }
		   
		   else{
			   datem="re.reDate";
		   }
		   if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)<=0)
		   {
			   queryString = queryString + "and "+datem+" between'"+ Year+"-"+Quarter+"' and '"+ YearTo+"-"+QuarterTo+"' group by re.reIndep,rm.rmTaketime";
		   } 
		   else if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)>0)
		   {
			   queryString = queryString + "and "+datem+" between'"+ Year+"-"+QuarterTo+"' and '"+ YearTo+"-"+Quarter+"' group by re.reIndep,rm.rmTaketime";
		   }
		   else if(Year.compareTo(YearTo)>=0){
			   queryString = queryString + "and "+datem+" between'"+ YearTo+"-"+Quarter+"' and '"+ Year+"-"+QuarterTo+"' group by re.reIndep,rm.rmTaketime";
		   }
		   else if(Year.compareTo(YearTo)<0){
			   queryString = queryString + "and "+datem+" between'"+ Year+"-"+Quarter+"' and '"+ YearTo+"-"+QuarterTo+"' group by re.reIndep,rm.rmTaketime";
		   }
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
   }

   public List findInpass(String DepId,String Date,String DateTo){
	   try{
			String queryString = "";
			if(Date.compareTo(DateTo)<=0)
				 queryString = "from  RiskEvent as model where model.reCheckflag='0' and model.reDate between'"+Date+"'and'"+DateTo+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
			 else
				 queryString = "from  RiskEvent as model where model.reCheckflag='0' and model.reDate between'"+DateTo+"'and'"+Date+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
			final String queryString1 =queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString1).list();
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
   //获取实际填报的风险
   public List findInpassedd(String DepId,String Year,String YearTo,String Quarter,String QuarterTo){
	   try{
			String queryString = "";
			//reAssessYear reAssessQuarter 2015-04-15 16:47:37
			//2019.02.21
			//由于含有checkflag为1但是assessYear以及assessQuarter为空的情况
			//并且reLastdate不能当作判断季度的最终标准
			queryString+="from RiskEvent as model where model.reCheckflag='1' and ((model.reAssessYear is null and model.reAssessQuarter is null and ";
			if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0) {
				queryString+="reLastdate between '"+Year+"-";
				if ("1".equals(Quarter)) {
					queryString+="01-01 00:00:00'";
				}else if ("2".equals(Quarter)) {
					queryString+="04-01 00:00:00'";
				}else if ("3".equals(Quarter)) {
					queryString+="09-01 00:00:00'";
				}else{
					queryString+="11-01 00:00:00'";
				}
				if ("1".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-04-01 00:00:00' ";
				}else if ("2".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-09-01 00:00:00' ";
				}else if ("3".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-11-01 00:00:00' ";
				}else{
					queryString+=" and '"+YearTo+"-12-31 23:59:59' ";
				}
			}else if (Year.equals(YearTo)&& Quarter.compareTo(QuarterTo) > 0){
				queryString+="reLastdate between '"+Year+"-";
				if ("1".equals(QuarterTo)) {
					queryString+="01-01 00:00:00'";
				}else if ("2".equals(QuarterTo)) {
					queryString+="04-01 00:00:00'";
				}else if ("3".equals(QuarterTo)) {
					queryString+="09-01 00:00:00'";
				}else{
					queryString+="11-01 00:00:00'";
				}
				if ("1".equals(Quarter)) {
					queryString+=" and '"+YearTo+"-04-01 00:00:00' ";
				}else if ("2".equals(Quarter)) {
					queryString+=" and '"+YearTo+"-09-01 00:00:00' ";
				}else if ("3".equals(Quarter)) {
					queryString+=" and '"+YearTo+"-11-01 00:00:00' ";
				}else{
					queryString+=" and '"+YearTo+"-12-31 23:59:59' ";
				}
			}else if (Year.compareTo(YearTo) < 0){
				queryString+="reLastdate between '"+Year+"-";
				if ("1".equals(Quarter)) {
					queryString+="01-01 00:00:00'";
				}else if ("2".equals(Quarter)) {
					queryString+="04-01 00:00:00'";
				}else if ("3".equals(Quarter)) {
					queryString+="09-01 00:00:00'";
				}else{
					queryString+="11-01 00:00:00'";
				}
				if ("1".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-04-01 00:00:00' ";
				}else if ("2".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-09-01 00:00:00' ";
				}else if ("3".equals(QuarterTo)) {
					queryString+=" and '"+YearTo+"-11-01 00:00:00' ";
				}else{
					queryString+=" and '"+YearTo+"-12-31 23:59:59' ";
				}
			}else{
				queryString+="reLastdate between '"+YearTo+"-";
				if ("1".equals(QuarterTo)) {
					queryString+="01-01 00:00:00'";
				}else if ("2".equals(QuarterTo)) {
					queryString+="04-01 00:00:00'";
				}else if ("3".equals(QuarterTo)) {
					queryString+="09-01 00:00:00'";
				}else{
					queryString+="11-01 00:00:00'";
				}
				if ("1".equals(Quarter)) {
					queryString+=" and '"+Year+"-04-01 00:00:00' ";
				}else if ("2".equals(Quarter)) {
					queryString+=" and '"+Year+"-09-01 00:00:00' ";
				}else if ("3".equals(Quarter)) {
					queryString+=" and '"+Year+"-11-01 00:00:00' ";
				}else{
					queryString+=" and '"+Year+"-12-31 23:59:59' ";
				}
			}
			queryString+=") or (";
			
			if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0){
				queryString += " model.reAssessYear Between'"
						+ Year
						+ "' and '"
						+ YearTo
						+ "' and model.reAssessQuarter between '"
						+ Quarter
						+ "' and '"
						+ QuarterTo
						+ "' ";}
			else if (Year.equals(YearTo)
					&& Quarter.compareTo(QuarterTo) > 0){
				queryString += " model.reAssessYear Between'"
						+ Year
						+ "' and '"
						+ YearTo
						+ "' and model.reAssessQuarter between '"
						+ QuarterTo
						+ "' and '"
						+ Quarter
						+ "' ";}
			else if (Year.compareTo(YearTo) < 0){
				queryString += " ((model.reAssessYear ='"
					+ Year
					+ "' and model.reAssessQuarter >='"
					+ Quarter
					+ "') or (model.reAssessYear ='"
					+ YearTo
					+ "' and model.reAssessQuarter <= '"
					+ QuarterTo
					+ "') or ('"
					+ Year
					+ "' < "
					+ "model.reAssessYear and model.reAssessYear"
					+ " < '"
					+ YearTo
					+ "'))";}
			else{
				queryString += " ((model.reAssessYear ='"
					+ YearTo
					+ "' and model.reAssessQuarter >='"
					+ QuarterTo
					+ "') or (model.reAssessYear ='"
					+ Year
					+ "' and model.reAssessQuarter <= '"
					+ Quarter
					+ "') or ('"
					+ YearTo
					+ "' < "
					+ "model.reAssessYear and model.reAssessYear"
					+ " < '"
					+ Year
					+ "'))" ;
		}
			queryString+=")) and model.reIndep = '"+DepId+"' ORDER BY reCheckflag DESC";
//			/*if(Date.compareTo(DateTo)<=0)
//				 queryString = "from  RiskEvent as model where reCheckflag='1'  and model.reAssessDate between'"+Date+"'and'"+DateTo+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
//			 else
//				 queryString = "from  RiskEvent as model where reCheckflag='1'  and model.reAssessDate between'"+DateTo+"'and'"+Date+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
//			*/
			
			
			System.out.println(queryString);
			final String queryString1 =queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString1).list();
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
   
   public List findInpassed(String DepId,String Date,String DateTo){
	   try{
			String queryString = "";
			if(Date.compareTo(DateTo)<=0)
				 queryString = "from  RiskEvent as model where reCheckflag='1' and model.reDate between'"+Date+"'and'"+DateTo+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
			 else
				 queryString = "from  RiskEvent as model where reCheckflag='1' and model.reDate between'"+DateTo+"'and'"+Date+"' and model.reIndep = '"+DepId+ "'  ORDER BY reCheckflag DESC";
			final String queryString1 =queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString1).list();
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
   public void updatCheckFlag(String reIdGet)
   {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reCheckflag=1 where reId='"+reIdGet+"'";
		   Query queryupdate=session.createQuery(updateString);
		   queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
   }
   
   //2015-12-03   批量考核時，將考核狀態置为1，并将考核年份和季度出入到riskevent表中
   public void updateAssess(String reIdGet, String assessYear, String assessQuarter) {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reCheckflag=1,reAssessYear='"+assessYear+"',reAssessQuarter='"+assessQuarter+"' where reId='"+reIdGet+"'";
		   Query queryupdate=session.createQuery(updateString);
		   queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
	
   }
   public void updateAssess1(String reIdGet, String assessYear, String assessQuarter) {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reCheckflag=1,reAssessYear='"+assessYear+"',reAssessQuarter='"+assessQuarter+"' where reId='"+reIdGet+"'";
		   Query queryupdate=session.createQuery(updateString);
		   queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
	
   }
   public void updateAssesss(String reIdGet, String assessYear, String assessQuarter) {
	   try{
		   Session session = getHibernateTemplate().getSessionFactory().openSession();		
		   Transaction trans=session.beginTransaction();
		   String updateString="update RiskEvent set reCheckflag=1,reAssessYear='"+assessYear+"',reAssessQuarter='"+assessQuarter+"' where reId='"+reIdGet+"'";
		   Query queryupdate=session.createQuery(updateString);
		   queryupdate.executeUpdate();
		   trans.commit();	   
		   session.close();
	   }
	   catch(RuntimeException re)
	   {
		 //throw re;
			re.printStackTrace();
	   }
	
   }
}

