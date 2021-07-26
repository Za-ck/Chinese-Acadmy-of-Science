<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>汇总审批报告提交</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<script language=javascript src="/RiskEvent/js/windows.js"></script>
		<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
		
	</head>

	<body class="ContentBody">
		<form action="" method="post" enctype="multipart/form-data" name="DepFlowConfirm" id="DepFlowConfirm" >
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle">
					<span>提交汇总审批报告</span>
				</div>
			</div>
			<div class="MainDiv">
				<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
					<tr>
						<td class="CPanel">

							<table border="0" cellpadding="0" cellspacing="0" style="width:100%">

								<tr>
									<td width="80%">
										<fieldset style="height:100%;">
											<legend>提交信息</legend>
											<div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
											<table class="contentInfo" border="0" cellpadding="2" cellspacing="1" style="width:100%">
												<tr>
													<td nowrap align="right" width="35%">提交到部门：</td>
													<td width="65%">
														<input name="depname" type="text" class="text" style="width:172px" value="${depname }" readonly="readonly" />
														<span class="red">*</span>
													</td>
												</tr>
												<tr>
													<td height="10"></td>
												</tr>
												<tr>
													<td nowrap align="right" width="35%">提交到人员：</td>
													<td>
														<select name="flowperson" id="flowperson" style="width: 172px">
                                                        	
                                                        	<c:forEach items="${users }" var="user">
																<option value="${user.userId}" <c:if test="${userId==user.userId}">selected</c:if>>${user.userName}</option>
															</c:forEach>
                                                        </select>
                                                        <span class="red">*</span>
													</td>
												</tr>
												<tr>
													<td height="10"><br><br></td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="50px">
							<input type="button" value="上一页" style= "width:70px;height: 24px;" onClick="javascript:history.back(-1)"/> 
							<input type="button" style="width:70px;height: 24px;" value="确定" onClick="saveReport('commit');"/> 
						</td>
					</tr>
					
				</table>
			</div>
			
			<input type="hidden" id="repcheck" name="repcheck" value="${repcheck }" />
			<input type="hidden" id="taskId" name="taskId" value="${taskId }" />
			<input type="hidden" id="reportId" name="reportId" value="${reportId }" />
			<input type="hidden" id="depId" name="depId" value="${depId }" />
			<input type="hidden" id="actionName" name="actionName" value="${actionName }" />
			<input type="hidden" id="fileIds" name="fileIds" value="${fileIds }" />
			
		</form>
		
		<script type="text/javascript">
	
			//var isCommitted = false;
			// 发送或者保存报告
			function saveReport(operation)
			{
				var reportId = document.getElementById("reportId").value;
				var taskId = document.getElementById("taskId").value;
				var repcheck = document.getElementById("repcheck").value;
				var depId = document.getElementById("depId").value;
				var depname = document.getElementById("depname").value;
				var userId = $("#flowperson option:selected").val();
				var username = $("#flowperson option:selected").text();
				var fileIds = document.getElementById("fileIds").value;
				
				var params="operation="+ operation +"&reportId="+reportId
						    + "&taskId=" + taskId + "&repcheck=" + repcheck
						    + "&depId=" + depId + "&depname=" + depname
						    + "&userId=" + userId + "&username=" + username
						    + "&fileIds=" + fileIds;
						    
				var url = document.getElementById("actionName").value;
				
				$.ajax({
		              url: url,
		              type: "post",
		              dateType: "json",
		              cache:false, 
		      		  async:false,  		//同步ajax
		              data: params,
		              contentType: "application/x-www-form-urlencoded",			//很重要
		              success: function (data) 
		              {   
		              		if(data == "commit")
		              		{
		              			//isCommitted = true;
		              			//alert("发送成功");
		              			//跳转到待办页面
		              			//window.location.href = "/RiskEvent/default/processAction_unProcessed";
		              			window.parent.unprocessed();
		              		}
		              		if(data == "save")
		              		{
		              			alert("保存成功");
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