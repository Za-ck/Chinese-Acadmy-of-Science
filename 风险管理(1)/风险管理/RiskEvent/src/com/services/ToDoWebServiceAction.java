package com.services;

import java.net.UnknownHostException;

public class ToDoWebServiceAction {
	public String getXml(String TaskId, String TaskApp, String TaskUrl,
			String TaskTitle, String LogonName, String UserName,
			String OrgName, String ReceiveTime, String CreatorName,
			String CreatorOrgName, String PreUserName, String PreOrgName) {
		/*
		 * 获取本地Ip地址
		 */
		String localhostIp = "188.188.0.21";// 默认
		try {
			localhostIp = java.net.InetAddress.getLocalHost().getHostAddress();// 本地Ip地址
		} catch (UnknownHostException e) {
			localhostIp = "188.188.0.21";// RiskEvent所在Tomcat的Ip地址
		}
		String localhostUrl = "http://" + localhostIp + ":8080/RiskEvent/";
		/*
		 * 获取xml格式字符串
		 */
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<ArrayOfTask xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
				+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
				+ "<Task><TaskId>"
				+
				// TaskId(0000000001) +
				TaskId + "</TaskId><TaskApp>" +
				// TaskApp(CWBX RiskEvent) +
				TaskApp + "</TaskApp><TaskUrl><![CDATA[" +
				// TaskUrl(待办事项的连接url) +
				localhostUrl + TaskUrl + "]]></TaskUrl><TaskTitle>" +
				// TaskTitle（李四的财务报销单 待审核风险事件） +
				TaskTitle + "</TaskTitle><LogonName>" +
				// LogonName（0000 userid 1914） +
				LogonName + "</LogonName><UserName>" +
				// UserName（张三） +
				UserName + "</UserName><OrgName>" +
				// OrgName（信息档案部-应用开发科） +
				OrgName + "</OrgName><ReceiveTime>" +
				// ReceiveTime（2013-11-11 14:33:20） +
				ReceiveTime + "</ReceiveTime><CreatorName>" +
				// CreatorName（李四） +
				CreatorName + "</CreatorName><CreatorOrgName>" +
				// CreatorOrgName（信息档案部-应用开发科） +
				CreatorOrgName + "</CreatorOrgName><PreUserName>" +
				// PreUserName（王五） +
				PreUserName + "</PreUserName><PreOrgName>" +
				// PreOrgName（信息档案部-应用开发科） +
				PreOrgName + "</PreOrgName></Task></ArrayOfTask>";

		// System.out.println("-------99-----:/n"+xml+"/n----------99----------");
		return xml;
	}

}
