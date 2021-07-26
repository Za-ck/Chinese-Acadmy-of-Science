package com.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.EventFileView;
import com.model.EventFlowFileView;
import com.model.ReportTaskRiskView;
import com.model.RiskReplyView;
import com.model.Users;

public class RiskReplyViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskReplyViewDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(RiskReplyView transientInstance) {
		log.debug("saving RiskReplyView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskReplyView persistentInstance) {
		log.debug("deleting RiskReplyView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskReplyView findById(String id) {
		log.debug("getting RiskReplyView instance with id: " + id);
		try {
			RiskReplyView instance = (RiskReplyView) getHibernateTemplate()
					.get("com.zxs.springdemo.RiskReplyView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<?> findByExample(RiskReplyView instance) {
		log.debug("finding RiskReplyView instance by example");
		try {
			List<?> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<?> findByProperty(String propertyName, Object value) {
		log.debug("finding RiskReplyView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskReplyView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<?> findAll() {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "from RiskReplyView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskReplyView merge(RiskReplyView detachedInstance) {
		log.debug("merging RiskReplyView instance");
		try {
			RiskReplyView result = (RiskReplyView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public static RiskReplyViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskReplyViewDAO) ctx.getBean("RiskReplyViewDAO");
	}
	

	//根据年份和部门编号查找记录
	public List<?> findByYearDep(String year,String dep) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "from RiskReplyView as model where model.year='"+year+"' and model.reIndep='"+dep+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//
	@SuppressWarnings("unchecked")
	public List<RiskReplyView> findDetail(String riskname, String dep, String dateFrom, String dateTo) {
		log.debug("finding all RiskReplyView instances");
		try {			
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";		
			}		
			String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and model.reIndep='"+dep+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			//String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and ((model.riskDep='本部门' and model.depName='"+dep+"') or model.riskDep='"+dep+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
		//"风险处理查询"页面查看本部门的所有已发布的风险信息
	   public List<?> findhandlerisk(String dep,final int offset, final int pageSize)
	   {
		   try {
			   Session session = getHibernateTemplate().getSessionFactory().openSession();	
			   final String queryString = "select re.RE_Id,re.RE_state,re.RE_creator,re.RE_date,rm.RM_plandate,rm.RM_taketime,rm.RM_sign from RiskEvent re,RiskManage rm where re.RE_indep='"+dep+"' and re.RE_Id=rm.RM_reId and re.RE_state='*' order by re.RE_date desc";
			   Query query = session.createSQLQuery(queryString);
			   ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
				List<?> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
					
					public Object doInHibernate(Session session) throws HibernateException,
							SQLException {
						
						List<?> result = session.createSQLQuery(queryString)
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

	//查找部门
	public List<?> findDep(String riskname, String reIndep, String dateFrom,String dateTo, String power) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";
			}
			if("--请选择--".equals(reIndep)){
				queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			}else{
				if("1".equals(power))
					queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and model.depName='"+reIndep+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
				else
					queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and ((model.riskDep='本部门' and model.depName='"+reIndep+"') or model.riskDep='"+reIndep+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			}
			 return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	  public List<?> findManageList(String dep,final int offset, final int pageSize) {
		try {
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dt = df.format(date).toString();
			Session session = getHibernateTemplate().getSessionFactory().openSession();	
			String query1 = "select RE_Id,Risk_name,Dep_name,RE_date,RM_plandate from RiskReplyView re where re.RM_plandate<'"+dt+"' and re.RM_taketime=''";
			if(dep.equals("--请选择--")){
				//query1 += "";
			}else{
				query1 += " and re.Dep_name='"+dep+"'";
			}
			query1 += "order by re.RE_date desc";
			final String queryString = query1;
			Query query = session.createSQLQuery(queryString);
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",query.list().size());
			List<?> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
					
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List<?> result = session.createSQLQuery(queryString)
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
	  
	 //"应对措施"页面的高级查询
	   public List findManageListbyCondition(String dep,String dateFrom,String dateTo,final int offset, final int pageSize) {
		   try {
			   String queryadvance = "select RE_Id,Risk_name,Dep_name,RE_date,RM_plandate from RiskReplyView re where re.RM_taketime=''";
			   if (!dateFrom.equals(""))
				   queryadvance += " and re.RE_date between '" + dateFrom+ "'";
				else 
					queryadvance += " and re.RE_date between '1899-10-10'";
			   if (!dateTo.equals(""))
				   queryadvance += " and '" + dateTo+ "'";
				else 
					queryadvance += " and '2999-12-12'";
			   if(dep.equals("--请选择--"))
				   queryadvance += "";
			   else
				   queryadvance += " and re.Dep_Name='"+dep+"'";
			   
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

	public String getFileName(String reEventId, String i) {
		// TODO Auto-generated method stub		
		String name="";
		try {
			String queryadvance = "select File_name from EventFileView as file where file.RE_Id='"+reEventId+"' and file.File_type='"+i+"'";
			List list = getHibernateTemplate().find(queryadvance);
			//System.out.println("查找filename的sql语句："+queryadvance);
			if(list.isEmpty()){return "";}
			else{
				for(int j=0;j<list.size();j++)
					name += (j+1)+"."+list.get(j)+";";
				return name;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void updatesupervised(String reId, String date, String name) {
	    try{
	  		Session session = getHibernateTemplate().getSessionFactory().openSession();		
	  		Transaction trans=session.beginTransaction();
	  		String updateString="update RiskManage set rmSuperviseMan='"+name+"', rmSuperviseTime='"+date+"' where RM_reId='"+reId+"'";
	  		Query queryupdate=session.createQuery(updateString);
	  		int ret=queryupdate.executeUpdate();
	  		trans.commit();	   
	  		session.close();
	  	}
	  	catch(RuntimeException re)
	  	{
	  		re.printStackTrace();
	  		throw re;
	  	}
	}

	public String getFlowFileName(String reId) {
		// TODO Auto-generated method stub		
		String name="";
		try {
			String queryadvance = "select flowFileName from EventFlowFileView as file where file.reId='"+reId+"'";
			List list = getHibernateTemplate().find(queryadvance);
			//System.out.println("查找flowFilename的sql语句："+queryadvance);
			if(list.isEmpty()){return "";}
			else{
				for(int j=0;j<list.size();j++)
					name += (j+1)+"."+list.get(j)+";";
				return name;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	public List findRisk135(String userName, String dateFrom, String dateTo) {
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";				
			}
			queryString = "select distinct riskName from RiskReplyView as model where model.reCreator='"+userName+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDep135(String userName, String reIndep, String dateFrom,
			String dateTo) {
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";
			}
			queryString = "select distinct reIndep,depName from RiskReplyView as model where model.depName='"+reIndep+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<RiskReplyView> findDetail135(String riskname, Users us,
			String dateFrom, String dateTo) {
		try {			
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";				
			}		
			String name = us.getUserName();
			String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and model.reCreator='"+name+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRisk24(String depName, String dateFrom, String dateTo) {
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";				
			}
			queryString = "select distinct riskName from RiskReplyView as model where (model.depName='"+depName+"' or model.riskDep='"+depName+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDep24(String riskname, String depName2,String dateFrom, String dateTo) {
		// TODO Auto-generated method stub
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";
			}
			queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and (model.riskDep='"+depName2+"' or model.depName='"+depName2+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<RiskReplyView> findDetail24(String riskname, String depId,
			String depName2, String dateFrom, String dateTo) {
		try {			
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";		
			}		
			String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and model.reIndep='"+depId+"'and (model.riskDep='"+depName2+"' or model.depName='"+depName2+"')and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			//String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and ((model.riskDep='本部门' and model.depName='"+dep+"') or model.riskDep='"+dep+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//风险监控里面得到风险事件的列表
	@SuppressWarnings("unchecked")
	public List<RiskReplyView> findSupRisk(String conditon) 
	{
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			queryString = "from RiskReplyView as model where "+conditon+"  order by riskName ASC,depName ASC";
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<?> findSupDep(String riskname, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and "+condition;
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<RiskReplyView> findSupDetail(String riskname, String dep,
			String condition) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and model.reIndep='"+dep+"' and"+condition;
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	//————————————————————以下是新增的函数——————————————————————————
	public List<RiskReplyView> findRisk_measure(String condition) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			queryString = "from RiskReplyView as model where "+condition+" order by riskName ASC,depName ASC";
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<?> findDep_measure(String riskname, String condition) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			queryString = "select distinct reIndep,depName from RiskReplyView as model where model.riskName='"+riskname+"' and "+condition;
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<RiskReplyView> findDetail_measure(String riskname,
			String reIndep, String reMadep, String dateFrom, String dateTo) {
		log.debug("finding all KeyStaView instances");
		try {			
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";		
			}		
			String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and model.reIndep='"+reIndep+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			//String queryString = "from RiskReplyView as model where model.riskName='"+riskname+"' and ((model.riskDep='本部门' and model.depName='"+dep+"') or model.riskDep='"+dep+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"'";
			if("--请选择--".equals(reMadep)){
				queryString += "";
			}else{
				queryString += " and ((model.riskDep='本部门' and model.depName='"+reMadep+"') or model.riskDep='"+reMadep+"')";
			}
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//获取流程文件具体信息
	@SuppressWarnings("unchecked")
	public List<EventFileView> findFile(String reId) {
		try {				
			final String queryString = "from EventFileView as model where model.reId='"+reId+"'";
			return getHibernateTemplate().executeFind(new HibernateCallback<List<EventFileView>>(){

				@Override
				public List<EventFileView> doInHibernate(Session s)
						throws HibernateException, SQLException {
					 Query query = s.createQuery(queryString);
					 List<EventFileView> result = query.list();
					return result;
				}
				
			});
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	//以下是优化后的代码
	@SuppressWarnings("unchecked")
	public List<RiskReplyView> findRisk135_new(String userName, String condition) {
		try {
			String queryString = "";
			queryString = "from RiskReplyView as model where model.reCreator='"+userName+"' and "+condition+" order by riskName ASC,depName ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//查找所有的二级风险
	@SuppressWarnings("unchecked")
	public List<RiskReplyView> findRisk_new(String reIndep, String dateFrom, String dateTo, String power) {
		log.debug("finding all RiskReplyView instances");
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01";
			}else{
				dateFrom =dateFrom+" 00:00:00";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31";		
			}else{
				dateTo =dateTo+" 23:59:59";
			}
			if("--请选择--".equals(reIndep)){
				queryString = "from RiskReplyView as model where model.rmdate between '"+dateFrom+"' and '"+dateTo+"' order by riskName ASC,depName ASC";
			}else{
				if("1".equals(power))
					queryString = "from RiskReplyView as model where model.depName='"+reIndep+"' and model.rmdate between '"+dateFrom+"' and '"+dateTo+"' order by riskName ASC,depName ASC";
				else
					queryString = "from RiskReplyView as model where ((model.riskDep='本部门' and model.depName='"+reIndep+"') or model.riskDep='"+reIndep+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"' order by riskName ASC,depName ASC";
			}
			 return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RiskReplyView> findRisk24_new(String depName, String dateFrom, String dateTo) {
		try {
			String queryString = "";
			if("".equals(dateFrom)||dateFrom == null){
				dateFrom="2000-01-01 00:00:00";
			}else{
				dateFrom=dateFrom+" 00:00:00";
			}
			if("".equals(dateTo)||dateTo == null){
				dateTo = "2050-12-31 23:59:59";				
			}else{
				dateTo = dateTo+" 23:59:59";
			}
			queryString = "from RiskReplyView as model where (model.depName='"+depName+"' or model.riskDep='"+depName+"') and model.rmdate between '"+dateFrom+"' and '"+dateTo+"' order by riskName ASC,depName ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<EventFileView> getAllEventFile(String condition) {
		try {
			String queryString = "";
			queryString = "from EventFileView where reId IN (select model.reId from RiskReplyView as model where "+condition+")";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<EventFlowFileView> getAllFlowFile(String condition) {
		try {
			String queryString = "";
			queryString = "from EventFlowFileView where reId IN (select model.reId from RiskReplyView as model where "+condition+")";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public List<RiskReplyView> getAllNotifies(){
		try {
			String queryString = "";
			queryString = "from RiskReplyView where datediff(DAY,GETDATE(),RM_plandate)=7";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public RiskReplyView findByReId(String reId){
		try {
			String queryString = "";
			queryString = "from RiskReplyView where reId='"+reId+"'";
			return (RiskReplyView) getHibernateTemplate().find(queryString).get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<RiskReplyView> getUnprocessedReply(String userId,
			String userDep) {
		try {
			String queryString = "";
			//queryString = "from RiskReplyView where reIndep='"+userDep+"' and (takeTime=null or takeTime='') and DATEDIFF(DAY, GETDATE(), RM_plandate)>=0";
			queryString = "from RiskReplyView where reIndep='"+userDep+"' and (takeTime=null or takeTime='') and datediff(DAY,GETDATE(),RM_plandate)<=7 order by remodifydate desc";
			return (List<RiskReplyView>) getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//结合riskmanage进行查询
	public List<RiskReplyView> getProcessedReply(String username, String userDep, String dateFrom, String dateTo, final int offset, final int pageSize, String orderbys) {
			StringBuilder queryString = new StringBuilder();
		
			queryString.append("SELECT rrv.re_indep,rrv.dep_name,rrv.rt_name,rrv.risk_id,rrv.risk_name,rrv.re_eventid,rrv.re_eventname,rrv.year,rrv.re_id,rrv.re_detail,rrv.RM_strategy,rrv.rm_reply,rrv.RM_plandate,rrv.RM_planres,rrv.RE_creator,rrv.Risk_dep,rrv.RE_verifier,rrv.Dep_queue,rrv.RE_date,rrv.RE_modifydate,rrv.RM_taketime,rrv.RM_superviseMan,rrv.RM_superviseTime");
			queryString.append(" FROM RiskReplyView rrv left join RiskManage on rrv.RE_Id = RiskManage.RM_reId where RM_replyman='"+username+"' and RE_indep='"+userDep+"'");
			
			if (dateFrom != null && !dateFrom.equals("")) {
				queryString.append(" AND rrv.re_modifydate between '" + dateFrom+ "' ");
			}
				   
			else {
				queryString.append(" AND rrv.re_modifydate between '1899-10-10' ");
			}
					
			if (dateTo != null && !dateTo.equals("")) {
				queryString.append(" AND '" + dateTo+ "' ");
			}
				   
			else {
				queryString.append(" AND '2999-12-12'");
			}
			
			queryString.append(" order by ");
			
			if(orderbys != null && !orderbys.equals("")) {
				queryString.append("rrv." + orderbys + ",");
			}
			queryString.append(" rrv.re_modifydate desc");
			
			
			final String queryString1 = queryString.toString();
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createSQLQuery(queryString1)
								.addEntity(RiskReplyView.class).setFirstResult(offset).setMaxResults(pageSize)
								.list();
					session.close();
					return result;
				}
			});	
			return (List<RiskReplyView>)list;
	}

	//根据userId,date,flowId查找用户已处理的报告的数目
	//结合riskmanage进行查询
	public int getProcessedReportSize(String reportname, String username, String dateFrom, String dateTo, String flowId ,String reIndep,String userDep) {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT count(*) FROM RiskReplyView left join RiskManage on RiskReplyView.RE_Id = RiskManage.RM_reId where RM_replyman='"+username+"' and RE_indep='"+userDep+"'");
		
		if (dateFrom != null && !dateFrom.equals("")) {
			queryString.append(" AND RiskReplyView.re_modifydate between '" + dateFrom+ "' ");
		}
			   
		else {
			queryString.append(" AND RiskReplyView.re_modifydate between '1899-10-10' ");
		}
				
		if (dateTo != null && !dateTo.equals("")) {
			queryString.append(" AND '" + dateTo+ "' ");
		}
			   
		else {
			queryString.append(" AND '2999-12-12'");
		}
		//queryString.append(" order by RiskReplyView.re_modifydate desc");
		
		final String queryString1=queryString.toString();
		System.out.println(queryString1);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createSQLQuery(queryString1).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	
	
	
	
}