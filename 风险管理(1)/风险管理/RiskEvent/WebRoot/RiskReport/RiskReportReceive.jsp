<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>接收报告</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" >
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/validation-framework.js" type="text/javascript"></script>
		
		<script type="text/javascript">
		
			//接收报告
			function receiveReport()
			{
				if(confirm("确实要结束吗?"))
          		{
          			var reportId = document.getElementById("reportId").value;
					var taskId = document.getElementById("taskId").value;
					var params="reportId="+ reportId +"&taskId="+taskId + "&tmpnum="+new Date().getTime();
					$.ajax({
			              url: "riskReport/riskReportInputAction_doReceiveReport",
			              type: 'post',
			              dateType: 'json',
			              cache:false, 
		      				  async:false,  		//同步ajax
			              data: params,
			              contentType: 'application/x-www-form-urlencoded',			//很重要
			              success: function (data) 
			              {   
			              		if(data == "success")
			              		{
			              			//alert("接收报告成功");
			              			//window.location.href = "default/processAction_unProcessed";
			              			//$("#receive").css("display","none");
			              			window.parent.unprocessed();
			              		}
			              		if(data == "fail")
			              		{
			              			alert("报告已经撤回");
			              		}
			              },
			              error: function () 
			              {
			              		alert("操作失败");
			          	   }
			      		});
          		
          		}
				
			}
		
		</script>

	</head>

	<body onload="stopBack()" >
		<form id="riskReportInput" name="riskReportInput" action="" method="post">
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>全面风险管理报告编制启动流程</span>
				</div>
			</div>
			<div class="MainDiv">
				<div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<td class="CPanel">
							<table border="0" cellpadding="0" cellspacing="0"
								style="width: 100%">
								<tr>
									<td width="100%">
										<fieldset style="height: 100%;">
											<legend>
												接收报告
											</legend>
											<table border="0" class="contentInfo" cellpadding="2" cellspacing="1" style="width: 100%">
												<tr>
													<td align="right" width="13%">报告名称：</td>
													<td width="42%">
					    								<input name="reportname" type="text" class="text" style="width:510px" id="reportname" value="${report.rerReportName }" readonly/>
					  									<input name="reportId" id="reportId" type="hidden" value="${report.rerReportId }"/>
					  									<input name="taskId" id="taskId" type="hidden" value="${taskId}"/>
					  								</td>
					  								<td align="right" width="10%">
					  									年度区间：
					  								</td>
					  								<td width="35%">
					  									<input name="annual" id="annual" style="width:173px;" class="Wdate" value="${report.rerAnnual }" readonly/>
					  								</td>
					  							</tr>
					  							
					  							<c:if test="${!empty report.rerRemark}">
					  								<tr>
					  									<td align="right" width="12%">补充描述：</td>
					  									<td colspan="3">
															<textarea name="reportremark" cols="108" rows="5" readonly="readonly">${report.rerRemark }</textarea>
														</td>
					  								</tr>
					  							</c:if>
					  							
					  							<tr>
					  								<td align="right" width="12%">相关附件：</td>
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
					  								<td align="right" width="12%">配合部门：</td>
					  								<td colspan="3"></td>
					  							</tr>
					  							<tr>
					  								<td></td>
					  								<td colspan="3">
					    								<div style="width: 885px;">
															<table id="selecttable" class="tablestyle" style="width: 100%" >
   																<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
   																	<td align="center">用户编号</td>          													
               														<td align="center">配合部门负责人</td>
               														<td align="center">配合部门</td>
               														
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
							<input type="button" value="结束"  id="receive" style="width:70px;height: 24px;" onClick="receiveReport()"/>
							<input type="button" value="查看流程" style="width:70px;height: 24px;" onClick="window.open('riskReport/riskReportReadAction_ReadFlow?taskId=${taskId}')" />
						</td>
						
					</tr>
					
				</table>
			</div>
			
		</form>

	</body>
	
</html>

