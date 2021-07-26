<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>查看部门提交报告</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" />
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/validation-framework.js" type="text/javascript"></script>

	</head>

	<body onload="init()" >
		<form id="riskReportDepInput" name="riskReportDepInput" action="" method="post">
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>全面风险管理报告编制提交流程</span>
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
												查看部门报告
											</legend>
                                          <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="contentInfo">
												<tr>
													<td align="right" width="13%">报告名称：</td>
													<td width="42%">
					    								<input type="text" id="reportname" name="reportname" style="width: 490px" readonly="readonly" value="${reportname }"/>
					  								</td>
					  								<td align="right" width="10%">年度区间：</td>
					  								<td width="35%">
					  									<input name="annual" id="annual" style="width:173px;" class="Wdate" value="${annual}" readonly="readonly"/>
					  								</td>
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">参编部门：</td>
					  								<td>
					    								<input name="depname" id="depname" type="text" style="width: 163px" readonly="readonly" value="${depname }" />												
					    								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配合部门编写人：</span>
				    								  	<input name="writername" id="writername" type="text" style="width: 163px" readonly="readonly" value="${writername }"/>
				  								  	</td>
                                                    <td align="right" width="10%">参编部门负责人：</td>		  								
													<td>
                                                        <input type="text" id="leadername" name="leadername" style="width: 173px" readonly="readonly" value="${leadername }"/>
                                                  	</td>
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">补充描述：</td>
					  								<td colspan="3">
														<textarea name="depremark" cols="108" rows="5" readonly="readonly">${depremark}</textarea>
													</td>
					  							</tr>
					  							<!-- 添加上启动流程的相关附件 -->
					  							<tr>
					  								<td align="right" width="13%">流程附件：</td>
					  								<td colspan="3"></td>
					  							</tr>
					  							<tr>
					  								<td></td>
					  								<td colspan="3">
					  									<div id="file1" style="width: 885px">
					  										<table id='filetable_new' class='tablestyle' width='100%' >
					  											<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
					  												<td width="4%" align="center">序号</td>
                													<td width="37%" align="center">文件名称</td>
                													<td width="14%" align="center">流程名称</td>
                													<td width="7%" align="center">创建人</td>
                													<td width="17%" align="center">创建人部门</td>
                													<td width="16%" align="center">创建日期</td>
                													<td width="5%" align="center">下载</td>
					  											</tr>
					  											<!-- 后面为启动流程的附件 -->
					  											<c:if test="${fn:length(startFileList) <= 0}">
        															<tr>
            															<td colspan="7">没有文件</td>
            														</tr>
 																</c:if>
 																<!-- 启动流程 -->
 																<c:forEach items="${startFileList}" var="oneFile" varStatus="status">
 																	<tr bgcolor="#ffffff" title="启动流程附件">
 																		<td>${status.index+1 }</td>
 																		<td>${oneFile.fileFilename}</td>
 																		<td>风险报告启动流程</td>
 																		<td>${oneFile.fileUploadername }</td>
 																		<td>${oneFile.fileUploaderDepName }</td>
 																		<td>${oneFile.fileDate }</td>
 																		<td><a href="/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId=${oneFile.fileId }" target="_blank">下载</a></td>
 																	</tr>
 																</c:forEach>
					  										</table>
					  									</div>
					  								</td>
					  							</tr>
					  							
					  							<tr>
					  								<td align="right" width="13%">相关附件：</td>
					  							</tr>
					  							<tr>
     												<td></td>
    												<td colspan="3">
    													
    													<div id="file" style="width: 887px">
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
																		<td><a href="/RiskEvent/riskReport/riskReportDepInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																	</tr>
    															</c:forEach>
    														</table>
    														
    													</div>
     												</td>
     											</tr>
     											<!-- 新增历史版本 -->
     											<tr>
   													<td align="right" width="13%">历史版本：</td>
   													<td colspan="3"></td>
     											</tr>
     											<tr>
    													<td></td>
   													<td colspan="3">
	   													<div id="file" style="width: 885px">
	   														<table id='historytable' class='tablestyle' width='100%' >
	   															<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
	   																<td width="10%" align="center">序号</td>
	               													<td width="55%" align="center">文件名</td>
	               													<td width="10%" align="center">创建人</td>
	               													<td width="20%" align="center">创建日期</td>
	               													<td width="5%" align="center">下载</td>
	           													</tr>
	           													<c:if test="${fn:length(historylist) <= 0}">
	       															<tr id="nofile">
	           															<td colspan="5">没有文件</td>
	           														</tr>
																	</c:if>
	           													<c:forEach items="${historylist }" var="singlefile" varStatus="status">
	  																<tr>
	  																	<td>${ status.index + 1}</td>
	             			 											<td id="filename">${singlefile.fileFilename }</td>
																		<td>${singlefile.fileUploadername }</td>
																		<td>${singlefile.fileDate }</td>
																		<td><a href="/RiskEvent/riskReport/riskReportDepInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																	</tr>
	   															</c:forEach>
	   														</table>
	   													</div>
    												</td>
     											</tr>
     											
     											
					  							<c:forEach items="${handleList}" var="handle">
					  								<c:if test="${!empty handle.retView}">
					  									<tr>
					  										<td align="right" width="13%">【${handle.retDepName }-${handle.retUserName }】处理意见：</td>
					  										<td colspan="3">
					  											<textarea name="handleview" cols="108" rows="5" readonly>${handle.retView }</textarea>
					  										</td>
					  									</tr>
					  								</c:if>
					  									
					  							</c:forEach>
     											
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
 							<input type="button" value="查看流程" style="width:70px;height: 24px;" onClick="window.open('riskReport/riskReportDepReadAction_ReadFlow?taskId=${taskId}')" />
						</td>
						
					</tr>
					
				</table>
			</div>
			<input name="reportId" id="reportId" type="hidden" value="${reportId}"/>
			<input name="repdepId" id="repdepId" type="hidden" value="${repdepId}" />
			<input name="taskId" id="taskId" type="hidden" value="${taskId}" />
			<input type="hidden" name="actionName" id="actionName" value="${actionName}"/>
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
		              type: 'post',
		              dateType: 'json',
		              data: params,
		              contentType: 'application/x-www-form-urlencoded',			//很重要
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

