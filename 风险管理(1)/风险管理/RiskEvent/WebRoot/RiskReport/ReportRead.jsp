<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>档案袋</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" ></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			function readfile(reportId,depId,curtr)
			{
				var url = "riskReport/riskReportReadAction_readFile?reportId" + reportId;  //获取相关附件的后台处理url
				//判断点击行的下一行是否为附件表头行
				var arr = $(curtr).next("tr[title=相关附件表]");
				//如果下一行不是附件表头行
				if(arr.length == 0){
					//改变点击行的样式
					$(curtr).css('background-color','white').children().css('color','red');
					//插入附件表头行
					$(curtr).after("<tr bgcolor=#fff2cc title=相关附件表>"
							+"<td>"+"序号"+"</td>"
							+"<td>"+"文件名称"+"</td>"
							+"<td>"+"流程名称"+"</td>"
							+"<td>"+"创建人"+"</td>"
							+"<td>"+"创建时间"+"</td>"
							+"<td>"+"下载"+"</td>"
							+"</tr>");
					var first = $(curtr).next("tr[title=相关附件表]");
					search(url,reportId,depId,first[0]);
				}else{
					//还原点击行的样式
					$(curtr).css('background-color','#f9f9f9').children().css('color','#818181');
					//删除附件表
					var filenum = getFileNum(url,reportId,depId);     //返回点击行附件的数目
					var index = $(curtr).index();   //获取当前行的索引
					var table = document.getElementById("selecttable1");
					for(var i=0;i<=filenum;i++){   //加上附件表头，共需删除filenum+1行
						table.deleteRow(index+1);
					}
				}
			}
			
			function readdepfile(reportId,depId,curtr)
			{
				var url = "riskReport/riskReportDepReadAction_readFile?reportId" + reportId;
				//判断点击行的下一行是否为附件表头行
				var arr = $(curtr).next("tr[title=相关附件表]");
				//如果下一行不是附件表头行
				if(arr.length == 0){
					//改变点击行的样式
					$(curtr).css('background-color','white').children().css('color','red');
					//插入附件表头行
					$(curtr).after("<tr bgcolor=#fff2cc title=相关附件表>"
							+"<td>"+"序号"+"</td>"
							+"<td>"+"文件名称"+"</td>"
							+"<td>"+"流程名称"+"</td>"
							+"<td>"+"创建人"+"</td>"
							+"<td>"+"创建时间"+"</td>"
							+"<td>"+"下载"+"</td>"
							+"</tr>");
					var first = $(curtr).next("tr[title=相关附件表]");
					search(url,reportId,depId,first[0]);
				}else{
					//还原点击行的样式
					$(curtr).css('background-color','#f9f9f9').children().css('color','#818181');
					//删除附件表
					var filenum = getFileNum(url,reportId,depId);     //返回点击行附件的数目
					var index = $(curtr).index();   //获取当前行的索引
					var table = document.getElementById("selecttable1");
					for(var i=0;i<=filenum;i++){   //加上附件表头，共需删除filenum+1行
						table.deleteRow(index+1);
					}
				}
			}
			
			function readendfile(reportId,depId,curtr)
			{
				var url = "riskReport/riskReportCheckReadAction_readFile?reportId" + reportId;
				//判断点击行的下一行是否为附件表头行
				var arr = $(curtr).next("tr[title=相关附件表]");
				//如果下一行不是附件表头行
				if(arr.length == 0){
					//改变点击行的样式
					$(curtr).css('background-color','white').children().css('color','red');
					//插入附件表头行
					$(curtr).after("<tr bgcolor=#fff2cc title=相关附件表>"
							+"<td>"+"序号"+"</td>"
							+"<td>"+"文件名称"+"</td>"
							+"<td>"+"流程名称"+"</td>"
							+"<td>"+"创建人"+"</td>"
							+"<td>"+"创建时间"+"</td>"
							+"<td>"+"下载"+"</td>"
							+"</tr>");
					var first = $(curtr).next("tr[title=相关附件表]");
					search(url,reportId,depId,first[0]);
				}else{
					//还原点击行的样式
					$(curtr).css('background-color','#f9f9f9').children().css('color','#818181');
					//删除附件表
					var filenum = getFileNum(url,reportId,depId);     //返回点击行附件的数目
					var index = $(curtr).index();   //获取当前行的索引
					var table = document.getElementById("selecttable1");
					for(var i=0;i<=filenum;i++){   //加上附件表头，共需删除filenum+1行
						table.deleteRow(index+1);
					}
				}
			}
			
			//搜索相关附件并插入至相应的位置
			function search(url,reportId,depId,first_tr){
				var params="reportId="+reportId+"&depId="+depId+"&tmpnum="+new Date().getTime();
				 $.ajax({
		               url: url,
		               type: "post",
		               dateType: "json",
		               data: params,
		               contentType: "application/x-www-form-urlencoded",
		               success: function (response) 
		               {     
		              		var data = eval("("+ response+")");  //返回的附件数据
		              		for(var i=0;i<data.length;i++)
		               		{
		              			//在附件表表头后面插入附件数据行
		               			$(first_tr).after("<tr bgcolor=#fff2cc>"
		               							+ "<td>"+(data.length-i)+"</td>"
		               			 				+ "<td>"+data[i].filename+"</td>"
		               			 				+ "<td>"+data[i].flowname+"</td>"
												+ "<td>"+data[i].uploader+"</td>"
												+ "<td>"+data[i].uploadtime+"</td>"
												+ "<td><a href="+data[i].path +" target=\"_blank\">下载</a>"
												+ "</tr>");
						    }
		               },
		               error: function () 
		               {
		               		alert("error");
		           	   }
		       });
			}
			
			function getFileNum(url,reportId,depId){
				var datalength = 0;
				var params="reportId="+reportId+"&depId="+depId+"&tmpnum="+new Date().getTime();
				 $.ajax({
		               url: url,
		               type: "post",
		               async: false,   //必须设置为同步，因为ajax没有返回数据就return了
		               dateType: "json",
		               data: params,
		               contentType: "application/x-www-form-urlencoded",
		               success: function (response) 
		               {  
		              		var data = eval("("+ response+")");  //返回的附件数据
		              		datalength = data.length;
		               },
		               error: function () 
		               {
		               		alert("error");
		           	   }
		       });
			 return datalength;
			}
		</script>
		
	</head>

	<body onload="init()" >
		<form>
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>档案袋</span>
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
												报告基本信息
											</legend>
											<div id="errorDiv" style="color: red; font-weight: bold; font-size: 12px"></div>
											<table border="0" class="contentInfo" cellpadding="2" cellspacing="1" style="width: 100%">
												<tr>
													<td align="right" width="12%">报告名称：</td>
													<td width="46%">
					    								<input name="reportname" type="text" class="text" style="width:500px" id="reportname" value="${report.rerReportName }" readonly="readonly"/>
					  								</td>
					  								<td align="right" width="9%">年度区间：
					  								<td width="35%">
					  									<input name="annual" id="annual" style="width:133px;" class="Wdate" value="${report.rerAnnual }" readonly="readonly"/>
					  								</td>
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">报告发起部门：</td>
					  								<td>
					    								<input name="depname" id="depname" type="text" class="text" style="width: 214px" readonly="readonly" value="企业发展与法律事务部" />&nbsp;												
					    								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发起人：</span>
				    								  	<input name="username" id="username" type="text" class="text" style="width: 173px" readonly="readonly" value="${report.rerPromoterName }"/>&nbsp;
				  								  	</td>	
                                                    
					  							</tr>
					  							<tr>
					  								<td align="right" width="12%">补充描述：</td>
					  								<td colspan="3">
														<textarea name="reportremark" cols="108" rows="5" readonly>${report.rerRemark }</textarea>
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
           															
  																	<tr>
  																		<td>${leader.rrdChargeId }</td>
             			 												<td>${leader.rrdChargeName }</td>
																		<td>${leader.rrdDepName }</td>
																	</tr>
   																</c:forEach>
   															</table>
    													</div>
					  								</td>			  								
													
					  							</tr>
					  							<tr>
					  								<td align="right" width="12%">相关流程：</td>
					  								<td colspan="3"></td>
					  							</tr>
     											<tr>
					  								<td></td>
					  								<td colspan="3">
					    								<div style="width: 885px;">
															<table id="selecttable1" class="tablestyle" style="width: 100%" >
   																<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
   																	<td width="5%" align="center">序号</td>
   																	<td width="35%" align="center">报告名称</td>          													
               														<td width="17%" align="center">流程名称</td>
               														<td width="13%" align="center">状态</td>
               														<td width="10%" align="center">发起人</td>
               														<td width="20%" align="center">部门名称</td>
           														</tr>
           														
           														<tr title="单击查看相关附件" style="cursor: pointer;" onclick="readfile('${startflow.reportId}','${startflow.depId }',this)">
           															<td>1</td>
           															<td>${startflow.reportname }</td>          													
               														<td>${startflow.flowname }</td>
               														<%--<td>${startflow.flowstate }</td>--%>
               														<td><a href="${starttask.rfFlowActionName}_ReadFlow?reportId=${starttask.rerReportId }&taskId=${starttask.retTaskId}" target="_blank">${starttask.fiStatements }</a></td>
               														<td>${startflow.beginner }</td>
               														<td>${startflow.depname }</td>
           														</tr>
           														
           														<c:if test="${depflow!= null && fn:length(depflow) > 0}">
           															<c:forEach items="${depflow }" var="flow" varStatus="status">
  																		<tr title="单击查看相关附件" style="cursor: pointer;" onclick="readdepfile('${flow.reportId}','${flow.depId }',this)">
  																			<td>${status.index+2 }</td>
  																			<td>${flow.reportname }</td>          													
               																<td>${flow.flowname }</td>
               																<td>${flow.flowstate }</td>
               																<td>${flow.beginner }</td>
               																<td>${flow.depname }</td>
																		</tr>
   																	</c:forEach>
           														</c:if>
           														
           														<c:if test="${!empty endflow }">
           															<tr  title="单击查看相关附件" style="cursor: pointer;" onclick="readendfile('${endflow.reportId}','${endflow.depId }',this)">
           																<td>${fn:length(depflow)+2 }</td>
																		<td>${endflow.reportname }</td>          													
           																<td>${endflow.flowname }</td>
           																<%-- <td>${endflow.flowstate }</td>--%>
           																<td><a href="${endtask.rfFlowActionName}_ReadFlow?reportId=${endtask.rerReportId }&taskId=${endtask.retTaskId}" target="_blank">${endtask.fiStatements }</a></td>
           																<td>${endflow.beginner }</td>
           																<td>${endflow.depname }</td>
																	</tr>
           														</c:if>
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
				</table>
			</div>
			
		</form>
	</body>
</html>

