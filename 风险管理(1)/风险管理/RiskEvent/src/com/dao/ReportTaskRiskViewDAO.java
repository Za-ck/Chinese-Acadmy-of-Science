package com.dao;

import java.sql.SQLException;
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

import com.model.ReportTaskRiskView;
import com.model.RiskDepQueryView;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportTaskRiskView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportTaskRiskView
 * @author MyEclipse Persistence Tools
 */

public class ReportTaskRiskViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ReportTaskRiskViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(ReportTaskRiskView transientInstance) {
		log.debug("saving ReportTaskRiskView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportTaskRiskView persistentInstance) {
		log.debug("deleting ReportTaskRiskView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ReportTaskRiskView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportTaskRiskView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportTaskRiskView instances");
		try {
			String queryString = "from ReportTaskRiskView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportTaskRiskView merge(ReportTaskRiskView detachedInstance) {
		log.debug("merging ReportTaskRiskView instance");
		try {
			ReportTaskRiskView result = (ReportTaskRiskView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportTaskRiskView instance) {
		log.debug("attaching dirty ReportTaskRiskView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportTaskRiskView instance) {
		log.debug("attaching clean ReportTaskRiskView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportTaskRiskViewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReportTaskRiskViewDAO) ctx.getBean("ReportTaskRiskViewDAO");
	}
	
	/*------------------------------------------- 以下是自己写的方法  --------------------------------------------------------*/
	
	//根据userId,date,flowId,reIndep查找用户未处理的报告的数目
	public int getUnprocessedReportSize(String reportname, String userId, String date, String flowId,String reIndep,String userPosition) {
		final StringBuilder queryString = new StringBuilder();
		//2018.12.17
		if ("9".equals(userPosition)) {
			queryString.append("SELECT COUNT(*) FROM ReportTaskRiskView WHERE ret_UserId in (select distinct user_id from users where user_position=" + userPosition +") and ret_State='0' ");
		}else{
			queryString.append("SELECT COUNT(*) FROM ReportTaskRiskView WHERE ret_UserId='" + userId +"' and ret_State='0' ");
		}
//		queryString.append("SELECT COUNT(*) FROM ReportTaskRiskView WHERE retUserId='" + userId +"' and retState='0' ");
		if(date != null && !date.equals("")) {
			queryString.append(" and ret_LastTime like '" + date + "%'"); 
		}
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" and ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" and rer_ReportName like '%" + reportname + "%'");
		}
		if(reIndep !=null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" and fi_WriterDepName='"+ reIndep +"'");
		}
		
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createSQLQuery(queryString.toString()).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	//根据userId,date,flowId查找用户未处理的任务
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getUnprocessedReport(String reportname, String userId, String date, String flowId, String reIndep,final int offset, final int pageSize,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder();
		//2018.12.17
		if ("9".equals(userPosition)) {
			queryString.append("SELECT * FROM ReportTaskRiskView WHERE ret_UserId in (select distinct user_id from users where user_position=" + userPosition +") and ret_State='0' ");
		}else{
			queryString.append("SELECT * FROM ReportTaskRiskView WHERE ret_UserId='" + userId +"' and ret_State='0' ");
		}
//		queryString.append("SELECT * FROM ReportTaskRiskView WHERE retUserId='" + userId +"' and retState='0' ");
		if(date != null && !date.equals("")) {
			queryString.append(" and ret_LastTime like '%" + date + "%'"); 
		}
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" and ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" and rer_ReportName like '%" + reportname + "%'");
		}
		if(reIndep !=null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" and fi_WriterDepName='"+reIndep+"'");
		}
		queryString.append(" ORDER BY ret_Id DESC");
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTaskRiskView", ReportTaskRiskView.class);
				
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	private String generateProcessedReportSQL(String reportname, String userId, String dateFrom, String dateTo, String flowId,String reIndep,String userPosition) {
		
		StringBuilder queryString = new StringBuilder();
		//2018.12.15
		if ("9".equals(userPosition)) {
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId in (select distinct user_id from users where user_position=" + userPosition +") and ret.ret_State='1' ");
		}else{
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId='" + userId +"' and ret.ret_State='1' ");
		}
		
		if (dateFrom != null && !dateFrom.equals("")) {
			queryString.append(" AND ret.ret_ProcessTime between '" + dateFrom+ "' ");
		}
			   
		else {
			queryString.append(" AND ret.ret_ProcessTime between '1899-10-10' ");
		}
				
		if (dateTo != null && !dateTo.equals("")) {
			queryString.append(" AND '" + dateTo+ "' ");
		}
			   
		else {
			queryString.append(" AND '2999-12-12'");
		}
		
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" AND ret.ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" AND ret.rer_ReportName LIKE '%" + reportname + "%'");
		}
		if(reIndep != null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" AND ret.fi_WriterDepName ='" + reIndep + "'");
		}
		
		queryString.append(" AND ret.ret_Id IN (SELECT MAX(ret_Id) FROM ReportTaskRiskView WHERE ret.ret_FormId = ret_FormId AND ret.ret_FlowId = ret_FlowId AND ret.ret_UserId = ret_UserId)");
		return queryString.toString();
	}
	
	//根据userId,date,flowId查找用户已处理的报告的数目
	public int getProcessedReportSize(String reportname, String userId, String dateFrom, String dateTo, String flowId ,String reIndep,String userPosition) {
		final String queryString = "SELECT COUNT(*) " + generateProcessedReportSQL(reportname, userId, dateFrom, dateTo, flowId , reIndep,userPosition);
		System.out.println(queryString);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createSQLQuery(queryString).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	//根据userId,date,flowId查找用户已处理的任务(分组取最大值)
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getProcessedReport(String reportname, String userId, String dateFrom, String dateTo, String flowId, String reIndep,final int offset, final int pageSize,String orderbys,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder("select * "+generateProcessedReportSQL(reportname, userId, dateFrom, dateTo, flowId,reIndep,userPosition) + "ORDER BY ");
		//queryString.append("FROM ReportTaskRiskView ret WHERE ret.retUserId='" + userId +"' and ret.retState='1' ");
		
		//2018。12.18需要注意！！！
		if(orderbys != null && !orderbys.equals("")) {
			queryString.append("ret." + orderbys + ",");
		}
		queryString.append("ret.ret_ProcessTime DESC");
		
		System.out.println(queryString.toString());
		
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTaskRiskView", ReportTaskRiskView.class);
				
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	// 获取用户任务的数目
	public int getTaskSize(String reportname, String userId, String date, String flowId,String reIndep,String userPosition) {
		int unprocessed = getUnprocessedReportSize(reportname,userId,date,flowId,reIndep,userPosition);
		int processed = getProcessedReportSize(reportname,userId,date,null,flowId,reIndep,userPosition);
		return unprocessed + processed;
	}
	
	
	
	// 根据userId查找用户未处理的任务
	//2018.12.13修改
	public List<ReportTaskRiskView> getUnprocessedReport(String userId,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder();
		if ("9".equals(userPosition)) {
			//管理员权限时
			queryString.append("select * FROM ReportTaskRiskView WHERE ret_UserId in (select distinct User_Id from Users where user_position="+userPosition+") and ret_State='0' ");
		}else{
			queryString.append("select * FROM ReportTaskRiskView WHERE ret_UserId ='"+userId+"' and ret_State='0' ");
		}
		queryString.append(" ORDER BY ret_Id DESC");
		
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("task",ReportTaskRiskView.class);
				//分页显示
				List<ReportTaskRiskView> result = query.list();
				session.close();
				return result;
			}
		});	
		return list;
	}
	
	
	
	//根据userId,date,flowId查找用户的任务(分组取最大值)
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getTask(String reportname, String userId, String date, String flowId,String reIndep, final int offset, final int pageSize,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder();
		//2018.12.17
		if ("9".equals(userPosition)) {
			queryString.append("select * FROM ReportTaskRiskView ret WHERE ret.ret_UserId in (select distinct user_id from users where user_position=" + userPosition +")");
		}else{
			queryString.append("select * FROM ReportTaskRiskView ret WHERE ret.ret_UserId='" + userId +"'");
		}
//		queryString.append("FROM ReportTaskRiskView ret WHERE ret.retUserId='" + userId +"'");
		if(date != null && !date.equals("")) {
			queryString.append(" and (ret.ret_ProcessTime like '%" + date + "%' OR ret.ret_LastTime like '%" + date + "%')"); 
		}
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" and ret.ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" and ret.rer_ReportName like '%" + reportname + "%'");
		}
		if(reIndep !=null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" and ret.fi_WriterDepName='"+reIndep+"'");
		}
		
		//queryString.append(" and ret.retId IN (SELECT MAX(retId) FROM ReportTaskRiskView WHERE ret.rerReportId = rerReportId AND ret.retUserId = retUserId AND ret.retFlowId= retFlowId) ORDER BY ret.rerReportId");
		queryString.append(" and (ret.ret_Id IN (SELECT MAX(ret_Id) FROM ReportTaskRiskView WHERE ret.ret_FormId = ret_FormId AND ret.ret_FlowId = ret_FlowId AND ret.ret_UserId = ret_UserId ) OR ret.ret_FormId=null) ORDER BY ret.ret_Id DESC");
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTaskRiskView",ReportTaskRiskView.class);
				
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	
	private String generateUnProcessedTaskSQL(String reportname, String userId, String date, String flowId,String reIndep,String userPosition) {
		
		StringBuilder queryString = new StringBuilder();
		//2018.12.17
		if ("9".equals(userPosition)) {
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId in (select distinct user_id from users where user_position=" + userPosition +") and ret.fi_FlowStatus!='*' ");
		}else{
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId='" + userId +"' and ret.fi_FlowStatus!='*' ");
		}
//		queryString.append("FROM ReportTaskRiskView ret WHERE ret.retUserId='" + userId +"' and ret.fiFlowStatus!='*' ");
		
		if(date != null && !date.equals("")) {
			queryString.append(" and ret_LastTime like '" + date + "%'"); 
		}
		
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" AND ret.ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" AND ret.rer_ReportName LIKE '%" + reportname + "%'");
		}
		if(reIndep != null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" AND ret.fi_WriterDepName ='" + reIndep + "'");
		}
		
		queryString.append(" AND ret.ret_Id IN (SELECT MAX(ret_Id) FROM ReportTaskRiskView WHERE ret.ret_FormId = ret_FormId AND ret.ret_FlowId = ret_FlowId AND ret.ret_UserId = ret_UserId)");
		return queryString.toString();
	}
	
	//根据userId,date,flowId,reIndep查找用户未完成的任务的数目
	public int getUnprocessedTaskSize(String reportname, String userId, String date, String flowId,String reIndep,String userPosition) {
		final String queryString = "SELECT COUNT(*) " + generateUnProcessedTaskSQL(reportname, userId, date, flowId , reIndep,userPosition);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createSQLQuery(queryString).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	//根据userId,date,flowId查找用户未完成的任务
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getUnprocessedTask(String reportname, String userId, String date, String flowId, String reIndep,final int offset, final int pageSize,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder("select * "+generateUnProcessedTaskSQL(reportname, userId,date, flowId,reIndep,userPosition)  + " ORDER BY " );
		
		queryString.append(" ret.ret_ProcessTime DESC");
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTaskRiskView", ReportTaskRiskView.class);
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	private String generateProcessedTaskSQL(String reportname, String userId, String dateFrom, String dateTo, String flowId,String reIndep,String userPosition) {
		
		StringBuilder queryString = new StringBuilder();
		//2018.12.17
		if ("9".equals(userPosition)) {
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId in (select distinct user_id from users where user_Position =" + userPosition +") and ret.fi_FlowStatus='*' ");
		}else{
			queryString.append("FROM ReportTaskRiskView ret WHERE ret.ret_UserId='" + userId +"' and ret.fi_FlowStatus='*' ");
		}
//		queryString.append("FROM ReportTaskRiskView ret WHERE ret.retUserId='" + userId +"' and ret.fiFlowStatus='*' ");
		if (dateFrom != null && !dateFrom.equals("")) {
			queryString.append(" AND ret.ret_ProcessTime between '" + dateFrom+ "' ");
		}
			   
		else {
			queryString.append(" AND ret.ret_ProcessTime between '1899-10-10' ");
		}
				
		if (dateTo != null && !dateTo.equals("")) {
			queryString.append(" AND '" + dateTo+ "' ");
		}
			   
		else {
			queryString.append(" AND '2999-12-12'");
		}
		
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" AND ret.ret_FlowId='" + flowId + "'"); 
		}
		if(reportname != null && !reportname.equals("")) {
			queryString.append(" AND ret.rer_ReportName LIKE '%" + reportname + "%'");
		}
		if(reIndep != null && !reIndep.equals("") && !reIndep.equals("--请选择--")){
			queryString.append(" AND ret.fi_WriterDepName ='" + reIndep + "'");
		}
		
		queryString.append(" AND ret.ret_Id IN (SELECT MAX(ret_Id) FROM ReportTaskRiskView WHERE ret.ret_FormId = ret_FormId AND ret.ret_FlowId = ret_FlowId AND ret.ret_UserId = ret_UserId)");
		return queryString.toString();
	}
	
	//根据userId,date,flowId查找用户已处理的报告的数目
	public int getProcessedTaskSize(String reportname, String userId, String dateFrom, String dateTo, String flowId ,String reIndep,String userPosition) {
		final String queryString = "SELECT COUNT(*) " + generateProcessedTaskSQL(reportname, userId, dateFrom, dateTo, flowId , reIndep,userPosition);
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = Integer.parseInt(session.createSQLQuery(queryString).uniqueResult().toString());
				session.close();
				return result;
			}
		});
	}
	
	//根据userId,date,flowId查找用户已处理的任务(分组取最大值)
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getProcessedTask(String reportname, String userId, String dateFrom, String dateTo, String flowId, String reIndep,final int offset, final int pageSize,String orderbys,String userPosition) {
		
		final StringBuilder queryString = new StringBuilder("select * "+generateProcessedTaskSQL(reportname, userId, dateFrom, dateTo, flowId,reIndep,userPosition) + "ORDER BY ");
		//queryString.append("FROM ReportTaskRiskView ret WHERE ret.retUserId='" + userId +"' and ret.retState='1' ");
		if(orderbys != null && !orderbys.equals("")) {
			queryString.append("ret." + orderbys + ",");
		}
		queryString.append("ret.ret_ProcessTime DESC");
		
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTaskRiskView", ReportTaskRiskView.class);
				
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	//查询流程的task
	public List<ReportTaskRiskView> getFirstTask(String reportname,String flowId){
		final StringBuilder queryString = new StringBuilder();
		queryString.append("FROM ReportTaskRiskView ret WHERE 1=1");
		queryString.append(" and ret.rerReportName = '"+ reportname + "'");
		queryString.append(" and ret.retFlowId='" + flowId + "'"); 
		queryString.append(" and ret.retId IN (SELECT MAX(retId) FROM ReportTaskRiskView WHERE ret.rerReportName=rerReportName AND ret.retFlowId=retFlowId)");
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				
				List<ReportTaskRiskView> result = query.list();
				session.close();
				return result;
			}
		});
		return list;
	}
	
	public List<ReportTaskRiskView> getFlowTask(String writerId, String reportname,String flowId){
		final StringBuilder queryString = new StringBuilder();
		queryString.append("FROM ReportTaskRiskView ret where 1=1");
		queryString.append(" and ret.fiWriterId ='"+ writerId +"'");
		queryString.append(" and ret.rerReportName = '"+ reportname + "'");
		queryString.append(" and ret.retFlowId='" + flowId + "'"); 
		queryString.append(" and ret.retId IN (SELECT MAX(retId) FROM ReportTaskRiskView WHERE ret.rerReportName=rerReportName AND ret.retFlowId=retFlowId AND ret.fiWriterId = fiWriterId)");
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				
				List<ReportTaskRiskView> result = query.list();
				session.close();
				return result;
			}
		});
		return list;
	}
	
	
	
	/*-----------------------------------------------------------------以下是报告台账模块------------------------------------------------------------*/
	// 报告台账的SQL语句
	private String generateReportBookSQL(String reportname, String date, String userId,String userPosition, String state) {
		
		StringBuilder queryString = new StringBuilder();
		
		String reportIdStr = "";
		//2018.11.22修改
		if ("9".equals(userPosition)) {
			//为系统管理员
			reportIdStr += "SELECT DISTINCT rt.rer_ReportId FROM ReportTaskRiskView rt,Users u WHERE rt.ret_UserId=u.user_Id and u.user_Position="+userPosition;
		}else{
			//不为系统管理员，就
			reportIdStr += "SELECT DISTINCT rt.rer_ReportId FROM ReportTaskRiskView rt WHERE rt.ret_UserId='" + userId +"'";
		}
//		reportIdStr += "SELECT DISTINCT rerReportId FROM ReportTaskRiskView rt WHERE rt.retUserId='" + userId +"'";
		//"SELECT DISTINCT rerReportId FROM ReportTaskRiskView WHERE retUserId='" + userId +"'";
		
		if(date != null && !date.equals("")) {
			reportIdStr += " AND rt.rer_Date like '%" + date + "%'"; 
		}
		
		if(reportname != null && !reportname.equals("")) {
			reportIdStr += " AND rt.rer_ReportName like '%" + reportname + "%'";
		}
		
		queryString.append("FROM ReportTaskRiskView ret WHERE ret.rer_ReportId IN (" + reportIdStr + ") ");
		
		if(state != null && !state.equals("")) {
			
			// 未编写
			if(state.equals("0")) {
				queryString.append(" AND (ret.ret_State = '0' OR ret.ret_FlowId != 'HZBGSP') ");
			}
			// 已编写
			if(state.equals("1")) {
				queryString.append(" AND (ret.ret_State = '1' AND ret.ret_FlowId='HZBGSP') ");
			}
			
		}
		
		queryString.append(" AND ret.ret_Id IN (SELECT MAX(ret_Id) FROM ReportTaskRiskView WHERE ret.rer_ReportId = rer_ReportId)");
		
		return queryString.toString();
	}
	
	// 查找报告的数目
	public int getReportSize(String reportname, String date, String userId,String userPosition, String state) {
		//2018.11.22修改
		final String queryString = "SELECT COUNT(*) " + generateReportBookSQL(reportname, date, userId,userPosition, state);
		
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Integer result = Integer.parseInt(session.createSQLQuery(queryString).uniqueResult().toString());
				session.close();
				System.out.println("==============="+result+"===============");
				return result;
			}
		});
		
	}
	
	// 查找报告台账
	//2018.11.22修改
	@SuppressWarnings("unchecked")
	public List<ReportTaskRiskView> getReport(String reportname, String date, String userId,String userPosition, String state, final int offset, final int pageSize) {
		
		final String queryString = "SELECT * " + generateReportBookSQL(reportname, date, userId,userPosition, state) + " ORDER BY ret.ret_Id DESC";
		
		List<ReportTaskRiskView> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTaskRiskView>>() {
			
			public List<ReportTaskRiskView> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString).addEntity("task",ReportTaskRiskView.class);
				//分页显示
				List<ReportTaskRiskView> result = query.setFirstResult(offset).setMaxResults(pageSize).list();
				session.close();
				return result;
			}
		});
		
		return list;
		
	}
	
}