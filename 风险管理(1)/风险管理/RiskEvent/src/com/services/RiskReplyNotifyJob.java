package com.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.xml.ws.Holder;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;

import com.dao.DepartmentDAO;
import com.dao.RiskReplyViewDAO;
import com.dao.UsersDAO;
import com.model.Department;
import com.model.RiskEvent;
import com.model.RiskReplyView;
import com.model.UserMap;
import com.model.Users;

/**
 * 定义作业类，定时执行任务，对于中南电力设计院定时推送
 * 
 * @author lenovo
 * @time 下午8:24:41
 */
public class RiskReplyNotifyJob extends QuartzJobBean {
	private int timeout;
	private RiskReplyViewDAO riskReplyViewDAO;
	private UsersDAO userDAO;
	private DepartmentDAO departmentDAO;
	
	
	
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	  
	/** 
	* 要调度的具体任务 
	*/  
	@Override  
	protected void executeInternal(JobExecutionContext context)  throws JobExecutionException { 
		riskReplyViewDAO = (RiskReplyViewDAO)context.getJobDetail().getJobDataMap().get("riskReplyViewDAO");
		userDAO = (UsersDAO)context.getJobDetail().getJobDataMap().get("userDAO");
		departmentDAO =(DepartmentDAO)context.getJobDetail().getJobDataMap().get("departmentDAO");

		//1.获取今日待推送的riskreply
		List<RiskReplyView> riskreplys = riskReplyViewDAO.getAllNotifies();
		System.out.println(riskreplys.size());
		//2.进行riskreply的一件一件的更新
		for (RiskReplyView riskReplyView : riskreplys) {
			//inThreads的参数说明
			//userOut:待办发起人
			//userIn:待办接收人,现还未确定待办接收人为谁
			//调用外部接口
			System.out.println(riskReplyView.getRmPlandate());
			Users userOut = (Users) userDAO.findByProperty("userPosition", 9).get(0);
			Users userIn = (Users) userDAO.findByUserDep(riskReplyView.getReIndep()).get(0);
			inThreads(userOut, riskReplyView.getReId(), userIn.getUserId());
			//更新内部待办
			
		}
		System.out.println("应对管理推送完成");  
	}  
	
	
	/*
	 * 调用待办，将其放入线程池，由Tomcat服务器执行
	 * 此处只存在update的情况，删除情况在风险应对进行处理时进行处理
	 */
	public String inThreads(final Users Out_us, final String Out_reId,final String Out_userid)
	{
		try{
			Callable<String> update=new Callable<String>(){
				public String call(){
					return updateTasks( Out_us,Out_reId, Out_userid);
				}
			};
			TaskExecutionWebServer tews=TaskExecutionWebServer.getInstance();
			tews.submit(update);
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/*
	 * 更新待办
	 */
	public String updateTasks(Users Out_us,String Out_reId,String Out_userid)
	{
		try{
			Users receiver=userDAO.findById(Out_userid);//流转到人
			RiskReplyView riskReplyView=riskReplyViewDAO.findByReId(Out_reId);//风险应对事件
			if(null == riskReplyView) return "fail";
			/*
			 * 将待办信息，集成到中南院办公系统：调用WebService接口，增删改待办信息
			 */
			//获取接口所需参数
			String TaskId=Out_reId;//reId
			String TaskApp="RiskEvent";//代表风险事件项目
			String LogonName=Out_userid;//接收人Id
			String UserName="";//接收人姓名
			String OrgName="";//接收人部门名称
			
			Department dModel=new Department();
			LogonName=receiver.getUserId();
			UserName=receiver.getUserName();
			dModel=departmentDAO.findById(receiver.getUserDep());
			if(null != dModel) OrgName=dModel.getDepName();
			
			//Out_System_Flag=out,表示这条链接是从外部系统链接进来的；Out_System_Flag=in,表示系统内调用action
			//String TaskUrl="riskFlow/Out_RiskStatusAction?current_pagenum=1&userid="+LogonName;//待审核风险事件
			String TaskUrl="default/loginSingleSystemAction?userid="+LogonName;//待审核风险事件
			String TaskTitle="待处理风险应对事件（"+Out_reId+"）";
			Date d=new Date();
			DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ReceiveTime=f.format(d);//接收时间
			String CreatorName=riskReplyView.getReCreator();//录入人姓名
			String CreatorOrgName="";//录入人部门名称
			dModel=new Department();
			dModel=departmentDAO.findById(riskReplyView.getReIndep());
			if(null != dModel) CreatorOrgName=dModel.getDepName();
			String PreUserName=Out_us.getUserName();//本人姓名
			String PreOrgName="";////本人部门名称
			dModel=new Department();
			dModel=departmentDAO.findById(Out_us.getUserDep());
			if(null != dModel) PreOrgName=dModel.getDepName();
			//将参数转换成xml格式数据
			String xml="";
			ToDoWebServiceAction toDoWebServiceAction=new ToDoWebServiceAction();
			xml=toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl, TaskTitle, LogonName, 
					UserName, OrgName, ReceiveTime, CreatorName, CreatorOrgName, PreUserName, PreOrgName
					);
			//通过myeclipse自动生成的WebService客户端调用接口
			PendingTaskPortService service = new PendingTaskPortService();  
			PendingTaskPortServiceSoap soap = service.getPendingTaskPortServiceSoap();  
			Holder<Boolean> updateTasksResult = new Holder<Boolean>();//类似C++引用 
			Holder<String> errorMsg = new Holder<String>();//类似C++引用 
			soap.updateTasks(xml, updateTasksResult, errorMsg);
			if(true==updateTasksResult.value)
			{
				System.out.println("成功调用接口："+errorMsg.value+"\n");
			}
			else
			{
				System.out.println("调用接口失败："+errorMsg.value+"\n");
			}
		}catch(Exception e){
				e.printStackTrace();
				return "fail";
		}
		return "success";
	}
}
