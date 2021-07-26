package com.services;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Holder;

import org.zhongsoft.pendtasks.PendingTaskPortService;
import org.zhongsoft.pendtasks.PendingTaskPortServiceSoap;


public class TaskExecutionWebServer {

	/**
	 * @param args
	 */
	private TaskExecutionWebServer(){}
	
	private static final TaskExecutionWebServer tews=new TaskExecutionWebServer();
	public static TaskExecutionWebServer getInstance()
	{
		return tews;
	}
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(10);
	public void submit(Callable<String> task) {
		 exec.submit(task);
	}
	
	public static void addTask(final String TaskId, final String TaskApp, final String TaskUrl, final String TaskTitle,final String ReceiverId,
			final String ReceiverName,final String ReceiverDepName,final String ReceiveTime,final String CreatorName,final String CreatorDepName,
			final String PreUserName,final String PreDepName) {
		
		Callable<String> add = new Callable<String>(){
			public String call(){
				try {
					//将参数转换成xml格式数据
					String xml="";
					ToDoWebServiceAction toDoWebServiceAction=new ToDoWebServiceAction();
					xml=toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl, TaskTitle, ReceiverId, 
							ReceiverName, ReceiverDepName, ReceiveTime, CreatorName, CreatorDepName, PreUserName, PreDepName
							);
					//通过自动生成的WebService调用接口
					PendingTaskPortService service = new PendingTaskPortService();  
					PendingTaskPortServiceSoap soap = service.getPendingTaskPortServiceSoap();  
					Holder<Boolean> addTasksResult = new Holder<Boolean>();//类似C++引用 
					Holder<String> errorMsg = new Holder<String>();//类似C++引用 
					soap.addTasks(xml, addTasksResult, errorMsg);
					if(true==addTasksResult.value)
					{
						System.out.println("成功调用接口："+errorMsg.value+"\n");
						return "success";
					}
					else
					{
						System.out.println("调用接口失败："+errorMsg.value+"\n");
						return "fail";
					}
				}catch(Exception e) {
					e.printStackTrace();
					return "fail";
				}
				
			}
		};
		
		exec.submit(add);
	}
	
	public static void deleteTask(final String TaskId,final String TaskApp) {
		
		Callable<String> delete = new Callable<String>(){

			@Override
			public String call() throws Exception {
				try {
					/*
					 * 将待办信息，集成到中南院办公系统：调用WebService接口，增删改待办信息
					 */
					// 获取接口所需参数
					String TaskUrl = "";
					String TaskTitle = "已删除待办("+TaskId+")";
					String LogonName = "@@@";// 接收人Id
					String UserName = "";// 接收人姓名
					String OrgName = "";// 接收人部门名称
					String ReceiveTime = "";// 接收时间
					String CreatorName = "";// 录入人姓名
					String CreatorOrgName = "";// 录入人部门名称
					String PreUserName = "";// 本人姓名
					String PreOrgName = "";// 本人部门名称
					// 将参数转换成xml格式数据
					String xml = "";
					ToDoWebServiceAction toDoWebServiceAction = new ToDoWebServiceAction();
					xml = toDoWebServiceAction.getXml(TaskId, TaskApp, TaskUrl,
							TaskTitle, LogonName, UserName, OrgName, ReceiveTime,
							CreatorName, CreatorOrgName, PreUserName, PreOrgName);
					// 通过myeclipse自动生成的WebService客户端调用接口
					PendingTaskPortService service = new PendingTaskPortService();
					PendingTaskPortServiceSoap soap = service
							.getPendingTaskPortServiceSoap();
					Holder<Boolean> updateTasksResult = new Holder<Boolean>();// 类似C++引用
					Holder<String> errorMsg = new Holder<String>();// 类似C++引用
					soap.updateTasks(xml, updateTasksResult, errorMsg);
					if (true == updateTasksResult.value) {
						System.out.println("成功调用接口：" + errorMsg.value + "\n");
					} else {
						System.out.println("调用接口失败：" + errorMsg.value + "\n");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return "fail";
				}
				return "success";
			}
			
		};
		
		exec.submit(delete);
		
	}
	
}
