<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>查看风险报告</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" ></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>

	</head>

	<body onload="init()" >
		<form>
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>全面风险管理报告编制启动流程</span>
				</div>
			</div>
			<div class="MainDiv">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent">
					<tr>
						<td class="CPanel">
							<table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
								<tr>
									<td width="100%">
										<fieldset style="height: 100%;">
											<legend>
												查看风险报告启动流程
											</legend>
											<div id="errorDiv" style="color: red; font-weight: bold; font-size: 12px"></div>
											<table border="0" class="contentInfo" cellpadding="2" cellspacing="1" style="width: 100%">
												<tr>
													<td align="right" width="12%">报告名称：</td>
													<td width="46%">
					    								<input name="reportname" type="text" class="text" style="width:500px" id="reportname" value="${report.rerReportName }" readonly/>
					  								</td>
					  								<td align="right" width="9%">年度区间：
					  								<td width="35%">
					  									<input name="annual" id="annual" style="width:133px;" class="Wdate" value="${report.rerAnnual }" readonly/>
					  								</td>
					  							</tr>
					  							
					  							<tr>
					  								<td align="right" width="12%">补充描述：</td>
					  								<td colspan="3">
														<textarea name="reportremark" cols="108" rows="5" readonly>${report.rerRemark }</textarea>
													</td>
					  							</tr>
					  							
					  							<tr>
					  								<td align="right" width="12%">相关附件：</td>
					  								<td colspan="3"></td>
					  							</tr>
					  							<tr>
     												<td></td>
    												<td colspan="3">
    													
    													<div id="file" style="width: 885px">
    														<table id='filetable' class='tablestyle' width='100%' height='100%' >
    															<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
    																<td width="10%" align="center">序号</td>
                													<td width="55%" align="center">文件名</td>
                													<td width="10%" align="center">创建人</td>
                													<td width="20%" align="center">创建日期</td>
                													<td width="5%" align="center">下载</td>
            													</tr>
            													<c:if test="${fn:length(filelist) <= 0}">
        															<tr id="nofile">
            															<td colspan="5">没有文件</td>
            														</tr>
 																</c:if>
            													<c:forEach items="${filelist }" var="singlefile" varStatus="status">
   																	<tr>
   																		<td>${ status.index + 1}</td>
              			 												<td id="filename">${singlefile.fileFilename }</td>
																		<td>${singlefile.fileUploadername }</td>
																		<td>${singlefile.fileDate }</td>
																		<td><a href="/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																	</tr>
    															</c:forEach>
    														</table>
    														
    													</div>
     												</td>
     											</tr>
     											<tr>
					  								<td align="right" width="12%">参编部门：</td>
					  								<td colspan="3"></td>
					  							</tr>
					  							<tr>
					  								<td></td>
					  								<td colspan="3">
					    								<div style="width: 885px;">
															<table id="selecttable" class="tablestyle" style="width: 100%" >
   																<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
   																	<td align="center">用户编号</td>          													
               														<td align="center">参编部门负责人</td>
               														<td align="center">参编部门</td>
               														
           														</tr>
           													
           														<c:forEach items="${leaderlist }" var="leader">
           															<%
																		String styleclass = "";
				 													%>
				
																	<c:if test="${userId eq leader.rrdChargeId }">
																		<% styleclass = "red"; %>
																	</c:if>
  																	<tr>
  																		<td><span class="<%=styleclass %>">${leader.rrdChargeId }</span></td>
             			 												<td><span class="<%=styleclass %>">${leader.rrdChargeName }</span></td>
																		<td><span class="<%=styleclass %>">${leader.rrdDepName }</span></td>
																	</tr>
   																</c:forEach>
   															</table>
    													</div>
					  								</td>			  								
													
					  							</tr>
     											
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td align="center" height="50px">
							<input type="button" value="撤回" id="revocation" style="width:70px;height: 24px;" onClick="revocationReport()"/>
 							<input type="button" value="查看流程" style="width:70px;height: 24px;" onClick="window.open('riskReport/riskReportReadAction_ReadFlow?taskId=${taskId}')" />
						</td>
						
					</tr>
					
				</table>
			</div>
			<input name="reportId" id="reportId" type="hidden" value="${report.rerReportId }"/>
			<input type="hidden" name="actionName" id="actionName" value="${actionName}"/>
			<input name="taskId" id="taskId" type="hidden" value="${taskId}"/>
		</form>
		<script type="text/javascript">
			
			//页面初始化
			function init()
			{
				stopBack();
			}
			
			//撤回报告
			function revocationReport()
			{
				var reportId = document.getElementById("reportId").value;
				var reportname = document.getElementById("reportname").value;
				var taskId = document.getElementById("taskId").value;
				var actionName = document.getElementById("actionName").value;
				var params="reportId="+ reportId +"&reportname="+reportname+"&taskId="+taskId + "&tmpnum="+new Date().getTime();
				$.ajax({
		              url: actionName + "_revocationRiskReport",
		              type: "post",
		              dateType: "json",
		              data: params,
		              contentType: "application/x-www-form-urlencoded",			//很重要
		              success: function (data) 
		              {
		              		if(data == "fail")
		              		{
		              			alert("不可撤回");
		              		}
		              		else
		              		{
		              			alert("撤回成功");
		              			window.location.href = "/RiskEvent/" + data;
		              		}
		              },
		              error: function () 
		              {
		              		alert("操作失败");
		          	   }
		      		});
			}
		</script>
	</body>
</html>

