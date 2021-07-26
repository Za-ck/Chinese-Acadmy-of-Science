<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>新建汇总审批报告</title>

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
		<form id="riskReportCheckInput" name="riskReportCheckInput" action="" method="post">
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>全面风险管理报告编制审批流程</span>
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
												填写汇总报告
											</legend>
                                          <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
											<table width="100%" border="0" cellpadding="2" cellspacing="1" class="contentInfo">
												<tr>
													<td align="right" width="13%">报告名称：</td>
													<td width="42%">
														<select name="reportname" id="reportname" style="width: 472px" onchange="changeReportId()">
															<option value="">---请选择---</option>
															<c:forEach items="${reportCheckList }" var="report">
																<option value="${report.rerReportId}" <c:if test="${reportId==report.rerReportId}">selected</c:if>>${report.rerReportName}</option>
															</c:forEach>
														</select>
														<span class="red">*</span>
					    								
					  								</td>
					  								<td align="right" width="10%">年度区间：</td>
					  								<td width="35%">
					  									<input name="annual" id="annual" style="width:172px;" class="Wdate" value="${annual}" readonly="readonly"/>&nbsp;<span class="red">*</span>
					  								</td>
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">编写部门：</td>
					  								<td>
					    								<input name="depname" id="depname" type="text" style="width: 186px" readonly="readonly" value="${depname }" />&nbsp;<span class="red">*</span>												
					    								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编写人：</span>
				    								  	<input name="username" id="username" type="text" style="width: 170px" readonly="readonly" value="${username }"/>&nbsp;<span class="red">*</span>
				  								  	</td>	
                                                    
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">补充描述：</td>
					  								<td colspan="3">
														<textarea name="remark" cols="108" rows="5">${remark}</textarea>
													</td>
					  							</tr>
					  							
					  							<!-- 添加上启动和上报流程的相关附件 -->
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
					  											<!-- 后面为启动和上报流程的附件 -->
					  											<c:if test="${fn:length(startFileList) + fn:length(flowFileList) <= 0}">
        															<tr>
            															<td colspan="7">没有文件</td>
            														</tr>
 																</c:if>
 																<!-- 判断是否流程附件 ，如果有则新增一行-->
 																<c:if test="${fn:length(startFileList) > 0 }">
 																	<tr bgcolor="#ffffff" title="单击查看相关附件" onclick="startFileOpt(this)" style="cursor: pointer;">
 																		<td colspan="7">风险报告启动流程</td>
 																	</tr>
 																</c:if>
 																<!-- 启动流程 -->
 																<c:forEach items="${startFileList}" var="oneFile" varStatus="status">
 																	<tr bgcolor="#ffffff" style="display:none" title="启动流程附件">
 																		<td>${status.index+1 }</td>
 																		<td>${oneFile.fileFilename}</td>
 																		<td>风险报告启动流程</td>
 																		<td>${oneFile.fileUploadername }</td>
 																		<td>${oneFile.fileUploaderDepName }</td>
 																		<td>${oneFile.fileDate }</td>
 																		<td><a href="/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId=${oneFile.fileId }" target="_blank">下载</a></td>
 																	</tr>
 																</c:forEach>
 																
 																
 																<!-- 判断是否流程附件 ，如果有则新增一行-->
 																<c:if test="${fn:length(flowFileList) > 0}">
 																	<tr bgcolor="#fff2cc" title="单击查看相关附件" onclick="depFileOpt(this)" style="cursor: pointer;">
 																		<td colspan="7">风险报告上报流程</td>
 																	</tr>
 																</c:if>
 																<!-- 上报流程 -->
 																<c:forEach items="${flowFileList}" var="twoFile" varStatus="status">
 																	<tr bgcolor="#fff2cc" style="display:none" title="上报流程附件">
 																		<td>${fn:length(startFileList) + status.index + 1}</td>
 																		<td>${twoFile.fileFilename}</td>
 																		<td>风险报告上报流程</td>
 																		<td>${twoFile.fileUploadername }</td>
 																		<td>${twoFile.fileUploaderDepName }</td>
 																		<td>${twoFile.fileDate }</td>
 																		<td><a href="/RiskEvent/riskReport/riskReportDepInputAction_downloadFile?fileId=${twoFile.fileId }" target="_blank">下载</a></td>
 																	</tr>
 																</c:forEach>
					  										</table>
					  									</div>
					  								</td>
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">相关附件：</td>
					  								<td colspan="3">
					  									<label id="fileTip"><span class="red">请先保存再上传附件</span></label>
					  									<c:if test="${empty readonly}">
					  										<input type="button" value="上传附件" id="upload" onclick="uploadfile()" style="height: 24px;display: none;" />
					  										<span class="red">*</span>
					  									</c:if>
					  									
					  								</td>
					  								
					  							</tr>
					  							<tr>
     												<td></td>
    												<td colspan="3">
    													
    													<div id="file" style="width: 885px">
    														<table id='filetable' class='tablestyle' width='100%' >
    															<tr id="firsttr" style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
    																<td width="10%" align="center">序号</td>
                													<td width="50%" align="center">文件名</td>
                													<td width="10%" align="center">创建人</td>
                													<td width="20%" align="center">创建日期</td>
                													<td width="5%" align="center">下载</td>
                													<td width="5%" align="center">删除</td>
                													
            													</tr>
            													<c:if test="${fn:length(filelist) <= 0}">
        															<tr id="nofile">
            															<td colspan="6">没有文件</td>
            														</tr>
 																</c:if>
            													<c:forEach items="${filelist }" var="singlefile" varStatus="status">
   																	<tr>
   																		<td>${ status.index + 1}</td>
              			 												<td id="filename">${singlefile.fileFilename }</td>
																		<td>${singlefile.fileUploadername }</td>
																		<td>${singlefile.fileDate }</td>
																		<td><a href="/RiskEvent/riskReport/riskReportCheckInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																		<c:choose>
																			<c:when test="${singlefile.fileSendflag eq 0}">
																				<td><a href="javascript:void(0)" onClick="deleteFile('filetable',this,'${singlefile.fileId }');return false;">删除</a></td>
																			</c:when>
																		
																			<c:otherwise>
																				<td><a href="###" ><span style="color: gray;">删除</span></a></td>
																			</c:otherwise>
																		</c:choose>
																		
																	</tr>
    															</c:forEach>
    														</table>
    														
    													</div>
     												</td>
     											</tr>
     											<c:if test="${!empty history }">
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
																		<td><a href="/RiskEvent/riskReport/riskReportCheckInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																		
																	</tr>
    															</c:forEach>
    														</table>
    														
    													</div>
     													</td>
     												</tr>
     											</c:if>
     											
					  							<c:forEach items="${handleList}" var="handle">
					  								<c:if test="${!empty handle.retView}">
					  									<tr>
					  										<td align="right" width="13%">【${handle.retDepName }-${handle.retUserName }】处理意见：</td>
					  										<td colspan="3">
					  											<textarea cols="108" rows="5" readonly>${handle.retView }</textarea>
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
							<input type="button" value="保存" id="save" style="width:70px;height: 24px;" onClick="firstSave()"/>
							<input type="button" value="发送" id="savenext" style="width:70px;height: 24px;display:none" onClick="saveBasicInfo()"/>
 							<input type="button" value="查看流程" style="width:70px;height: 24px;" onClick="window.open('riskReport/riskReportCheckReadAction_ReadFlow?taskId=${taskId}')" />
						</td>
						
					</tr>
					
				</table>
			</div>
			<input name="reportId" id="reportId" type="hidden" value="${reportId}"/>
			<input name="repcheck" id="repdepId" type="hidden" value="${repcheck}" />
			<input name="taskId" id="taskId" type="hidden" value="${taskId}" />
			<input name="newfiles" id="newfiles" type="hidden" />

		</form>
		<script type="text/javascript">
			var fileSize = parseInt("${fn:length(filelist)}");   //当前文件表中文件的数量
			var oldReportId = "";
			var isReload = false;		//表示没有发送
			var oldReportId = "";
			var oldReportname = "";
			
			//页面初始化
			function init()
			{
				stopBack();
				oldReportId = document.getElementById("reportId").value;			//刚刚进入页面时候的reportId
				oldReportname = $('#reportname option:selected').text();			//刚刚进入页面时候的reportname
				var repcheck = document.getElementById("repcheck").value;
				if (repcheck != null && repcheck !== undefined && repcheck !== '') 
				{
					$("#fileTip").remove();
					$("#savenext").css("display","inline");
					$("#save").css("display","none");
               		$("#upload").css("display","inline");
				}
			}
			
			// 更改选中的ReportId
			function changeReportId()
			{
				var reportId = $("#reportname option:selected").val();
				openBox("提示",350,150,0,"正在准备数据，请等待....","");
				window.location.href = "riskReport/riskReportCheckInputAction?reportId=" + reportId;
			}
    		
    		//上传文件
    		function uploadfile()
    		{
    			var url = "${pageContext.request.contextPath }/RiskReport/FileUpload.jsp";
    			var args = new Array();
    			args[0] = document.getElementById("reportId").value;		//报告编号
    			args[1] = "riskReport/riskReportCheckInputAction";		//处理上传文件的action
    			args[2] = window;
    			window.showModalDialog(url,args,"status:false;dialogWidth:450px;dialogHeight:400px;resizable:yes;scroll:yes");
    		}
    		
    		//验证表单
    		function validate()
    		{
    			var flag = true;
    			
    			flag = doValidate("riskReportCheckInput");
    			$("#reportname").width(472);
    			$("#depleader").width(172);
    			$("#annual").width(172);
    			
    			return flag;
    		}
    	
    	//收集表单参数
    	function collectParams()
    	{
			var repcheck = document.getElementById("repcheck").value;
			var taskId = document.getElementById("taskId").value;
			var reportId = document.getElementById("reportId").value;
			var reportname = $("#reportname option:selected").text();
			var remark = document.getElementById("remark").value;
			
			var params="repcheck="+ repcheck +"&taskId="+taskId
						+ "&reportId=" + reportId + "&reportname=" 
						+ reportname + "&remark=" + remark;
			return params;
    	}
    	
    	//第一次保存
	   	function firstSave() 
	   	{
	   		var flag = validate();
	   		if(!flag)
	   		{
	   			return;
	   		}
	   		
	   		var url = "riskReport/riskReportCheckInputAction_firstSave";
			var params = collectParams();
			//alert(params);
			submitform(url,params);
	   	}
		
		//保存报告
		function saveBasicInfo()
    	{
    		var flag = validate();
	   		if(!flag)
	   		{
	   			return;
	   		}
	   		var files = document.getElementsByName("filename");
	   		if(files.length <= 0)
	   		{
	   			alert("至少要上传一个附件");
	   			return;
	   		}
			
			isReload = true;
			openBox("提示",350,150,0,"正在保存，请等待...."," ");
			riskReportCheckInput.action="riskReport/riskReportCheckInputAction_saveBasicInfo";
			riskReportCheckInput.submit();
			
		}
		
		//提交表单
		function submitform(url,params) 
		{
    		$.ajax({
               url: url,
               type: "post",
               dateType: "json",
               data: params,
               contentType: "application/x-www-form-urlencoded",			//很重要
               success: function (data) 
               {   
               		if(data == "fail") 
            		{
            			alert("该报告已经由其他人填写");
            		}
            		else 
            		{
            			//alert("保存成功");
	              		var strs = new Array();
	              		strs = data.split("-");
	              		document.getElementById("repcheck").value = strs[0];
	              		document.getElementById("taskId").value = strs[1];
	              		
	              		$("#fileTip").remove();
						$("#save").css("display","none");
						$("#savenext").css("display","inline");
            			$("#upload").css("display","inline");
            		}
               		
               },
               error: function () 
               {
               		alert("操作失败");
           	   }
       		});
		}
		
		//在文件表格中添加文件
		function addfile(files)
		{
			//alert(files);
			var data = eval(files);
			var fileIds = "";
			//alert(data[0].fileId);
			$("#filetable tr[id=nofile]").remove();			//将"没有文件"那一行删除
			
			for(var j=0;j<fileSize;j++){
				//修改后每行的序号为：data.length+1+j
				document.getElementById("filetable").rows[1+j].cells[0].innerHTML = data.length+1+j;
			}
			for(var i=0;i<data.length;i++)
            {   
				$("#firsttr").after("<tr>"
							+"<td>"+(data.length-i)+"</td>"
               			 	+ "<td id=\"filename\">"+data[i].filename+"</td>"
							+ "<td>"+data[i].uploadername+"</td>"
							+ "<td>"+data[i].filedate+"</td>"
							+ "<td><a href=\"/RiskEvent/riskReport/riskReportCheckInputAction_downloadFile?fileId="+data[i].fileId+"\" target=\"_blank\">下载</a>"
							+ "<td><a href=\"javascript:void(0)\" onClick=\"deleteFile('filetable',this,"+ data[i].fileId +");return false;\">删除</a>"
							+ "</tr>");
				fileIds += data[i].fileId + "@";
				
            }
            document.getElementById("newfiles").value += fileIds;
            fileSize=fileSize+data.length;
		}
		
		//点击删除按钮删除一行
		function deleteFile(tableID, obj, fileId) 
		{	
			//如果报告已经发送，则不允许删除
			/*if(isCommitted)
			{
				alert("报告已经提交，不允许删除附件");
				return false;				//点击一个链接触发的事件，如果不返回false,页面会刷新
			}*/
			//参数为表格ID，触发对象
    		//获得触发对象的行号，parentNode的个数取决于触发对象为TR的第几级子项，input=>td=>tr，所以parentNode有两个
    		var rowIndex = obj.parentNode.parentNode.rowIndex;
    		var table = document.getElementById(tableID);
    		//由于某行删除后对其后面的行的序号有影响，需要修改
    	    //获取当前文件表的行数
    	    var cur_trs = document.getElementById("filetable").rows.length; 
    	    for(var i=rowIndex+1;i<cur_trs;i++){
    	    	//更新待删除行之下每一行的序号
    	    	document.getElementById("filetable").rows[i].cells[0].innerHTML -=1 ;
    	    }
    	    
    		table.deleteRow(rowIndex);
    		fileSize= fileSize-1;
    		//alert(fileId);
    		var params="fileId="+ fileId +"&tmpnum="+new Date().getTime();
    		$.ajax({
               url: "riskReport/riskReportCheckInputAction_deleteFile",
               type: 'post',
               dateType: 'json',
               cache:false, 
       		   async:false,  		//同步ajax
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
               		//return false;
               },
               error: function () 
               {
               		alert("删除文件失败");
               		//return false;
           	   }
       		});
    		//obj.parentElement.parentElement.parentElement.deleteRow(rowIndex); //再简化：省略tableID参数
		}
		
		
		//为流程附件增加点击展开收起效果
		function startFileOpt(cur_tr){
			//判断风险报告流程的附件是不是展开的状态
			var startFiles = $(cur_tr).nextAll("tr[title=启动流程附件]");
			var state = startFiles[0].style.display;
			if(state == 'none'){    //如果是折叠状态
				//将首行文字设置为红色
				$(cur_tr).children().css('color','red');
				//将附件设置为可见
				for(var i=0;i<startFiles.length;i++){
					startFiles[i].style.display = 'block';
				}
			}else{   //如果是展开状态
				$(cur_tr).children().css('color','#818181');
			    //隐藏附件
				for(var i=0;i<startFiles.length;i++){
					startFiles[i].style.display = 'none';
				}
			}
		}
	
		function depFileOpt(cur_tr){
			var depFiles = $(cur_tr).nextAll("tr[title=上报流程附件]");
			var state = depFiles[0].style.display;
			if(state == 'none'){    //如果是折叠状态
				//将首行文字设置为红色
				$(cur_tr).children().css('color','red');
				//将附件设置为可见
				for(var i=0;i<depFiles.length;i++){
					depFiles[i].style.display = 'block';
				}
			}else{
				$(cur_tr).children().css('color','#818181');
			    //隐藏附件
				for(var i=0;i<depFiles.length;i++){
					depFiles[i].style.display = 'none';
				}
			}
		}
		
		
		//离开页面时触发(离开页面需要做操作的一定是因为任务没有完成)
		window.onbeforeunload = function() {
			//alert("确定离开本页面?");
			//var reportId = document.getElementById("reportId").value;
			
			if (oldReportId == null || oldReportId == undefined || oldReportId == '') {
				return;
			}
			var taskId = document.getElementById("taskId").value;
			var repcheck = document.getElementById("repcheck").value;
			var params="reportId="+ oldReportId +"&taskId="+taskId+"&repcheck="+repcheck +"&tmpnum="+new Date().getTime();
			if(isReload) 
			{
				//直接离开页面
			}
			else
			{
				openBox("提示",350,150,0,"正在保存工作，请等待....","");
				$.ajax({
              		url: "riskReport/riskReportCheckInputAction_cancelAction",
               		type: 'post',
               		dateType: 'json',
               		cache:false, 
       				async:false,  		//同步ajax
               		data: params,
               		contentType: 'application/x-www-form-urlencoded',			//很重要
               		success: function (data) 
               		{   
               			
               		},
               		error: function () 
               		{
               			alert("创建或者更新待办时失败");
           	   		}
       			});
			}
			
		};
	</script>
	</body>
</html>

