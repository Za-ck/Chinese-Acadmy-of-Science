package com.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.model.ReportTask;
import com.services.TaskExecutionWebServer;
import com.util.GenerateSequenceUtil;

public class ReportTaskDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ReportTaskDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportTask transientInstance) {
		log.debug("saving ReportTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportTask persistentInstance) {
		log.debug("deleting ReportTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportTask findById(java.lang.Integer id) {
		log.debug("getting ReportTask instance with id: " + id);
		try {
			ReportTask instance = (ReportTask) getHibernateTemplate().get(
					"com.model.ReportTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportTask instance) {
		log.debug("finding ReportTask instance by example");
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
		log.debug("finding ReportTask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReportTask as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportTask instances");
		try {
			String queryString = "from ReportTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportTask merge(ReportTask detachedInstance) {
		log.debug("merging ReportTask instance");
		try {
			ReportTask result = (ReportTask) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportTask instance) {
		log.debug("attaching dirty ReportTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportTask instance) {
		log.debug("attaching clean ReportTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportTaskDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReportTaskDAO) ctx.getBean("ReportTaskDAO");
	}

	/*---------------------------------------- 以下是自己写的方法  -------------------------------------------------*/

	// 根据reportId删除ReportTask(只在报告启动流程中使用)
	public void delReportTaskByReportId(String reportId) {
		final String queryString = "DELETE FROM ReportTask WHERE retReportId='"+ reportId + "'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
	}
	
	// 根据taskId获取ReportTask
	@SuppressWarnings("unchecked")
	public ReportTask getReportTaskByTaskId(String taskId) {
		List<ReportTask> list = findByProperty("retTaskId", taskId);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	// 根据reportId获取ReportTaskList
	@SuppressWarnings("unchecked")
	public List<ReportTask> getReportTaskListByReportId(String reportId) {
		List<ReportTask> list = findByProperty("retReportId", reportId);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	// 根据reportTask的主键判断已处理的任务是否可以撤回
	public int getRevocationFlag(String taskId) {
		
		final String queryString = "FROM ReportTask WHERE retPreTaskId='" + taskId + "'";
		
		List<ReportTask> list = getHibernateTemplate().execute( new HibernateCallback<List<ReportTask>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportTask> doInHibernate(Session session) throws HibernateException, SQLException {
				List<ReportTask> list = (List<ReportTask>) session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		
		//如果是报告的最后一个状态,不可撤回
		if(list == null || list.size() <= 0) {
			return 0;
		}
		
		for(ReportTask rt : list) {
			Integer state = rt.getRetModifyFlag();
			//如果任务已经对报告进行修改了
			if(state.intValue() == 1) {
				return 0;		//表示任务不可撤回
			}
		}
		
		return 1;			//表示任务可以撤回
	}
	
	// 根据taskId撤回任务
	public ReportTask revocateTask(final String taskId) {
		
		try {
			
			// 1.查询该任务之后的任务
			List<String> tasklist = this.getHibernateTemplate().execute( new HibernateCallback<List<String>>() {
				
					@SuppressWarnings("unchecked")
					@Override
					public List<String> doInHibernate(Session session) throws HibernateException, SQLException {
						String queryString = "SELECT retTaskId FROM ReportTask WHERE retPreTaskId='" + taskId +"'";
						List<String> result = session.createQuery(queryString).list();
						session.close();
						return result;
					}
			});
			
			if(tasklist != null && tasklist.size() > 0) {
				
				for(String task : tasklist) {
					TaskExecutionWebServer.deleteTask(task, "RiskEvent");
				}
				
			}
			
			// 1.删除该任务之后的任务
			this.getHibernateTemplate().execute( new HibernateCallback<Integer>() {
					
				@Override
				public Integer doInHibernate(Session session) throws HibernateException, SQLException {
					String queryString = "DELETE FROM ReportTask WHERE retPreTaskId='" + taskId +"'";
					int result = session.createQuery(queryString).executeUpdate();
					session.close();
					return result;
				}
			});
			
			// 2.将任务修改一个撤回的标识,添加一个未处理的任务
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ReportTask task = getReportTaskByTaskId(taskId);
			
			ReportTask rt = (ReportTask) task.clone();
			rt.setRetTaskId(GenerateSequenceUtil.generateTaskId());
			rt.setRetNextStatus("");
			rt.setRetState("0");
			rt.setRetPreTaskId(task.getRetTaskId());
			rt.setRetProcessTime("");
			rt.setRetLastTime(df.format(new Date()));
			save(rt);
			
			task.setRetNextStatus(task.getRetFlowStatus());
			task.setRetProcessTime(df.format(new Date()));
			task.setRetTaskFlag(1);
			task.setRetView("撤回");
			merge(task);
			
			return rt;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// 根据reportId和流程Id获得任务列表
	public List<ReportTask> getTaskList(final String reportId, final String flowId) {
		
		// 1.查询该任务之后的任务
		List<ReportTask> tasklist = this.getHibernateTemplate().execute( new HibernateCallback<List<ReportTask>>() {
			
				@SuppressWarnings("unchecked")
				@Override
				public List<ReportTask> doInHibernate(Session session) throws HibernateException, SQLException {
					String queryString = "FROM ReportTask WHERE retReportId='" + reportId +"' AND retFlowId='" + flowId + "'";
					List<ReportTask> result = session.createQuery(queryString).list();
					session.close();
					return result;
				}
		});
		
		return tasklist;
	}
	
	
	// 根据taskId获取此任务之前有处理意见的任务记录
//	public List<ReportTask> getHandleViewListBeforeTask(String taskId) {
//		
//		ReportTask task = getReportTaskByTaskId(taskId);
//		
//		// 对同一个表单Id操作的记录,获取这些记录就可以获取到任务的记录
//		String formId = task.getRetFormId();
//		int dbid = task.getRetId();
//		final String queryString = "FROM ReportTask WHERE retFormId='" + formId +"' AND  retId < " + dbid + " AND retView != '' AND retTaskFlag != 1 ORDER BY retId ASC";
//		List<ReportTask> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTask>>() {
//			
//			public List<ReportTask> doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				
//				Query query = session.createQuery(queryString);
//				@SuppressWarnings("unchecked")
//				List<ReportTask> list = query.list();
//				
//				session.close();
//				return list;
//			}
//		});
		
//		String retview = task.getRetView();
//		if(retview != null && !retview.equals("")) {
//			if(list == null) {
//				list = new ArrayList<ReportTask>();
//			}
//			list.add(task);
//		}
		
//		String preTaskId = task.getRetPreTaskId();
//		while(preTaskId != null && !preTaskId.equals("")) {
//			task = getReportTaskByTaskId(preTaskId);
//			String view = task.getRetView();
//			if(view != null && !view.equals("")) {
//				list.add(task);
//			}
//			preTaskId = task.getRetPreTaskId();
//		}
//		
//		Collections.reverse(list);
		
//		return list;
//	}
	
	// 根据formId获取处理意见
	public List<ReportTask> getHandleViewList(String formId) {
		
		final String queryString = "FROM ReportTask WHERE retFormId='" + formId +"' AND retView != '' AND retTaskFlag != 1 ORDER BY retId ASC";
		List<ReportTask> list = getHibernateTemplate().execute(new HibernateCallback<List<ReportTask>>() {
			
			public List<ReportTask> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString);
				@SuppressWarnings("unchecked")
				List<ReportTask> list = query.list();
				
				session.close();
				return list;
			}
		});
		
		return list;
	}
	
	//根据userId,flowId查找用户未处理的任务
	//2018.11.21修改
	@SuppressWarnings("unchecked")
	public List<String> getUnprocessedReport(String userId,String userPosition, String flowId) {
		
		//2018.11.21修改
		//对于系统管理员显示的为权限内的待办事件
		
		//获取用户的user_position
		//若为9，则按角色查询
		if ("9".equals(userPosition)) {
			System.out.println("===========================================");
			System.out.println("此为系统管理员登录");
			final StringBuilder queryString = new StringBuilder();
			//sqlquery
			queryString.append("select * from ReportTask where ret_userId in (select distinct user_id from users where user_position="+userPosition+") and ret_state='0'");
			if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
				queryString.append(" and RET_flowId='" + flowId + "'"); 
			}
			
			//sql查询
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List<ReportTask> list = session.createSQLQuery(queryString.toString())
								  .addEntity("repoettask",ReportTask.class)
					              .list();
					List<String> result = new ArrayList<String>();
					for(ReportTask task : list) {
						if(!result.contains(task.getRetReportId())) {
							result.add(task.getRetReportId());
						}
						
					}
					session.close();
					return result;
				}
			});		
			System.out.println("===========================================");
			return list;
			
		}
		//不为系统管理员的情况
		final StringBuilder queryString = new StringBuilder();
		queryString.append("FROM ReportTask WHERE retUserId='" + userId +"' and retState='0' ");
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" and retFlowId='" + flowId + "'"); 
		}
		
		List<String> list = getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			
			public List<String> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				List<ReportTask> list = query.list();
				List<String> result = new ArrayList<String>();
				for(ReportTask task : list) {
					if(!result.contains(task.getRetReportId())) {
						result.add(task.getRetReportId());
					}
					
				}
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	//根据userId,flowId查找用户在部门提交报告中未处理的任务
	@SuppressWarnings("unchecked")
	public List<String> getNonWrittenReport(String depId, String flowId) {
		
		final StringBuilder queryString = new StringBuilder();
		queryString.append("FROM ReportTask WHERE (retUserId=null OR retUserId='') AND retDepId='" + depId + "'");
		if(flowId != null && !flowId.equals("") && !flowId.equals("none")) {
			queryString.append(" AND retFlowId='" + flowId + "'"); 
		}
		
		List<String> list = getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			
			public List<String> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				List<ReportTask> list = query.list();
				List<String> result = new ArrayList<String>();
				for(ReportTask task : list) {
					if(!result.contains(task.getRetReportId())) {
						result.add(task.getRetReportId());
					}
					
				}
				session.close();
				return result;
			}
		});
		
		return list;
	}
	
	// 根据reportId,flowId,userId查找某个用户特定的未完成的任务(在同一个流程中，一个人只可能有一个没有完成的任务,部门报告提交流程除外(因为报告发起人可能审核多个人提交上来的属于同一个报告名称的报告))
	public List<ReportTask> getSpecificUnprocessedTaskList(String reportId, String flowId, String userId,String userPosition) {
		final StringBuilder queryString=new StringBuilder();
		
		if ("9".equals(userPosition)) {
			queryString.append("select * FROM ReportTask WHERE ret_UserId in (select distinct user_id From Users where user_Position=" + userPosition +") and ret_State='0' and ret_FlowId='" + flowId + "' and ret_ReportId='" + reportId + "'");
		}else{
			queryString.append("select * FROM ReportTask WHERE ret_UserId='" + userId +"' and ret_State='0' and ret_FlowId='" + flowId + "' and ret_ReportId='" + reportId + "'");
		}
		
		List<ReportTask> task = getHibernateTemplate().execute(new HibernateCallback<List<ReportTask>>() {
			
			@SuppressWarnings("unchecked")
			public List<ReportTask> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createSQLQuery(queryString.toString()).addEntity("ReportTask", ReportTask.class);
				List<ReportTask> task = query.list();
				
				session.close();
				return task;
			}
		});
		
		return task;
		
	}
	
	// 查看还未编写的报告(本来以前是自动触发待办的,现在不需要自动触发待办所以需要这个函数)
	public ReportTask getNonWrittenTask(String reportId, String flowId, String depId) {
		
		final String queryString = "FROM ReportTask WHERE (retUserId='' or retUserId=null) and retState='0' and retFlowId='" + flowId + "' and retReportId='" + reportId + "' and retDepId='" + depId + "'" ;
		ReportTask task = getHibernateTemplate().execute(new HibernateCallback<ReportTask>() {
			
			public ReportTask doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				ReportTask task = (ReportTask) query.uniqueResult();
				
				session.close();
				return task;
			}
		});
		
		return task;
		
	}
	
	public ReportTask getSpecificUnprocessedTask(String formId) {
		
		final String queryString = "FROM ReportTask WHERE retState='0' and retFormId='" + formId + "'" ;
		ReportTask task = getHibernateTemplate().execute(new HibernateCallback<ReportTask>() {
			
			public ReportTask doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(queryString.toString());
				ReportTask task = (ReportTask) query.uniqueResult();
				
				session.close();
				return task;
			}
		});
		
		return task;
		
	}
	
	// 批量新增
	public void insertBatch(final List<ReportTask> list) {
		
		getHibernateTemplate().execute(new HibernateCallback<String>(){
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				if (list != null && list.size() > 0) {  
		            try {
		                session.beginTransaction();
		                ReportTask task = null; 
		                for (int i = 0; i < list.size(); i++) {  
		                	task = list.get(i); 
		                    session.save(task); 
		                    // 批插入的对象立即写入数据库并释放内存  
		                    if (i % 10 == 0) {  
		                        session.flush();  
		                        session.clear();  
		                    }  
		                }  
		                session.getTransaction().commit(); // 提交事物  
		                return "success";
		            } catch (Exception e) {  
		                e.printStackTrace(); // 打印错误信息  
		                session.getTransaction().rollback(); // 出错将回滚事物  
		                throw new RuntimeException(e);
		            } finally {  
		               session.close();
		            }  
		        }
				return "fail";
			}
			
		});
		
	}
	
	// 根据报告的编号更新报告的名称
	public void updateReportname(String reportId, String reportname) {
		
		final String queryString = "UPDATE ReportTask SET retReportName = '"+reportname+"' WHERE retReportId='" + reportId +"'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result =  session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
	}

	public List<String> getSendedRepIdList() {
		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT DISTINCT retReportId FROM ReportTask RET WHERE 1=1");
		//必须要排除启动流程中保存但是未被发送的报告
		queryString.append(" AND RET.retReportId NOT IN( SELECT DISTINCT retReportId FROM ReportTask WHERE retFlowId = 'BGBXQD' and retState = '0' and retFlowStatus = '1')");
		List<String> list = getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			public List<String> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(queryString.toString());
				List<String> result = query.list();
				session.close();
				return result;
			}
		});
		return list;
	}
}