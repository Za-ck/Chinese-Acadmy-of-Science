<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>新建风险报告</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" >
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
		<script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
		<script src="/RiskEvent/js/validation-framework.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="/RiskEvent/jquery-easyui-1.4.4/jquery.min.js"></script>
		<script type="text/javascript" src="/RiskEvent/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/RiskEvent/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="/RiskEvent/jquery-easyui-1.4.4/themes/gray/easyui.css"></link>
		<link rel="stylesheet" type="text/css" href="/RiskEvent/jquery-easyui-1.4.4/themes/icon.css"></link> 
	</head>

	<body onload="init()" >
		<form id="riskReportInput" name="riskReportInput" action="riskReport/riskReportInputAction_addRiskReport" method="post">
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
												新建风险报告
											</legend>
											<div id="errorDiv" style="color: red; font-weight: bold; font-size: 12px"></div>
											<table border="0" class="contentInfo" cellpadding="2" cellspacing="1" style="width: 100%">
												<tr>
													<td align="right" width="13%">报告名称：</td>
													<td>
					  									<%-- easyui的comboBox --%>
					  									<input id="reportname" name="reportname" class="easyui-combobox" style="width:470px;" data-options="valueField:'reportId',textField:'reportName'" /><span class="red">*</span>

					  									<%--历史布局 --%>
					  								    <%-- <input name="reportname" type="text" class="text" style="width:500px" id="reportname" value="${report.rerReportName }" onblur="checkName();"/><span class="red">*</span> --%>
					  								    <input name="reportId" id="reportId" type="hidden" value="${report.rerReportId }"/>
					  									<input name="taskId" id="taskId" type="hidden" value="${taskId}"/>
					  								</td>
					  								<td align="right" width="10%">年度区间：</td>
					  								<td width="35%">
					  									<span class="red"><input name="annual" id="annual" style="width:173px;" class="Wdate" value="${report.rerAnnual }" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>*</span>
					  								</td>
					  							</tr>
					  							<tr style="display:none">
					  								<td align="right" width="13%">配合部门：</td>
					  								<td colspan="3">
					    								<input name="namelist" id="namelist" type="hidden" value="${namelist }"/>
					    								<input name="Idlist" id="Idlist" type="hidden" value="${Idlist }"/>
					    								<input name="leaderIds" id="leaderIds" type="hidden" value="${leaderIds }" />
					  								</td>			  								
													
					  							</tr>
					  							<tr>
					  								<td align="right" width="13%">补充描述：</td>
					  								<td colspan="3">
														<textarea id="reportremark" name="reportremark" cols="108" rows="5" >${report.rerRemark }</textarea>
													</td>
					  							</tr>
					  							
					  							<tr>
					  								<td align="right" width="13%">相关附件：</td>
					  								<td colspan="3">
					  									<label id="fileTip"><span class="red">请先保存再上传附件</span></label>
					  									<input type="button" value="上传附件" id="upload" onclick="uploadfile()" style="height: 24px;display: none;" />
					  									<span id="span1" class="red">*</span>
					  								</td>
					  								
					  							</tr>
					  							<tr>
     												<td></td>
    												<td colspan="3">
    													
    													<div id="file" style="width: 885px">
    														<table id='filetable' class='tablestyle' width='100%' height='100%' >
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
																		<td><a href="/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId=${singlefile.fileId }" target="_blank">下载</a></td>
																		<td><a href="javascript:void(0)" onClick="deleteFile('filetable',this,'${singlefile.fileId }');return false;">删除</a></td>
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
							<input type="button" value="保存" id="save" style="width:70px;height: 24px;" onClick="firstSave();"/>
							<input type="button" value="新建" id="savenext1" style="width:70px;height: 24px;display:none" onClick="javascript:window.location.href='/RiskEvent/RiskReport/RiskReportInput.jsp'"/>
							<input type="button" value="修改" id="savenext2" style="width:70px;height: 24px;display:none" onClick="saveBasicInfo()"/>
							<input type="button" value="发送" id="savenext" style="width:70px;height: 24px;display:none" onClick="saveBasicInfo()"/>
							<input type="button" value="删除" id="deletereport" style= "width:70px;height: 24px;display: none;" onclick="delReport()"/>
 							<input type="button" value="查看流程" style="width:70px;height: 24px;" onClick="window.open('/RiskEvent/riskReport/riskReportReadAction_ReadFlow?taskId=${taskId}')" />
						</td>
						
					</tr>
					
				</table>
			</div>
		</form>
		<script type="text/javascript">
			var selectFlag = false;
			var fileSize = parseInt("${fn:length(filelist)}");   //当前文件表中文件的数量
			var oldReportname = "";
			var reportnameflag = true;		//用户报告名称的验证
			var isReload = false;			//表示是否是刷新当前页面
			var filedelete = false;
			
			
			$("#reportname").combobox({
					onSelect:function(){
						selectFlag = true;
						//获得reportId
						var reportId = $("#reportname").combobox('getValue');
						
						window.location.href = "riskReport/riskReportInputAction_updateRiskReport?reportId=" + reportId + "&tmpnum="+new Date().getTime();
					},
					onChange:function(){
						
						selectFlag = false;
						
						
					}
			});
			
			$("#reportname").next().children(":text").blur(function() {
					if(!selectFlag){
						checkName();
					}
				});
			

			
			
			//页面初始化
			function init()
			{
				stopBack();
				// 获取已经发送的报告 
				$("#reportname").combobox('reload', 'riskReport/riskReportInputAction_getRiskReportList');
				var reportId = document.getElementById("reportId").value;
				
				// 如果不是新建一个报告
				if (reportId !== null && reportId !== undefined && reportId !== '')
				{
					$("#fileTip").css('display','none');
               		$("#deletereport").css("display","inline");
               		$("#save").css("display","none");
               		$("#savenext").css("display","none");
               		$("#savenext1").css("display","inline");
               		$("#savenext2").css("display","inline");
               		$("#upload").css("display","inline");
               		reportnameflag = true;
               		selectFlag = true;
               		$("#reportname").combobox("setValue","${reportname }");
               		oldReportname = $("#reportname").combobox('getText');
               		
				}
				//如果是新建一个报告
				else {
					$("#reportname").combobox("setValue","${reportname }");
					
				}
			}
    		
    		//上传文件
    		function uploadfile()
    		{
    			var url = "${pageContext.request.contextPath }/RiskReport/FileUpload.jsp";
    			var args = new Array();
    			args[0] = document.getElementById("reportId").value;		//报告编号
    			args[1] = "riskReport/riskReportInputAction";		//处理上传文件的action
    			args[2] = window;
    			window.showModalDialog(url,args,"status:false;dialogWidth:450px;dialogHeight:400px;resizable:yes;scroll:yes");
    		}
    		
    		//验证表单
    		function validate()
    		{
    			
    			var flag = true;
    			//flag = doValidate('riskReportInput');
    			//$("#reportname").width(500);
    			//$("#annual").width(172);
    			//$("#namelist").width(778);
    			
    			//对reportname annual reportremark进行验证
    			var reportnameValue = $("#reportname").combobox('getText');
    			if(reportnameValue == '' || reportnameValue == null){
    				alert("报告名称不能为空");
    				flag = false;
    				return flag;
    			}
    			var annualValue = $("#annual").val();
    			if(annualValue == '' || annualValue == null){
    				alert("年度区间不能为空");
    				flag = false;
    				return flag;
    			}
    			var reportremark1 = $("#reportremark").val();

    			 /* if(reportremark1!=reportremark){
    			 alert("补充描述信息发生改变");
    			 } */
    			if(reportremark1.length > 500){
    				alert("补充描述字数长度不能超过500");
    				flag = false;
    				return flag;
    			}
    			
    			if(!reportnameflag)
    			{
    				//$("#reportname").css({border:"2px solid #FFCC88"});
    				if(flag)
    				{
    					alert("报告名称已经存在");
    				}
    				flag = false;
    			}
    			
    			return flag;
    		}
    	
    	//验证表单
    		/*function validate1()
    		{
    			
    			var flag = true;
    			//flag = doValidate('riskReportInput');
    			//$("#reportname").width(500);
    			//$("#annual").width(172);
    			//$("#namelist").width(778);
    			
    			//对reportname annual reportremark进行验证
    			var reportnameValue = $("#reportname").combobox('getText');
    			if(reportnameValue == '' || reportnameValue == null){
    				alert("报告名称不能为空");
    				flag = false;
    				return flag;
    			}
    			var annualValue = $("#annual").val();
    			if(annualValue == '' || annualValue == null){
    				alert("年度区间不能为空");
    				flag = false;
    				return flag;
    			}
    			var reportremark1 = $("#reportremark").val();
    			 if(reportremark1!=reportremark){
    			 if(confirm("补充信息新建或发生修改，确定修改并发送吗？")){
    			 alert("你点了确定");
    			 flag=true;    			 
    			 }else{alert("你点了取消");
    			 flag=false;
    			 }
    			// reportremark1=reportremark;
    			 }
    			var reportremark1=reportremark;
    			if(reportremark1.length > 500){
    				alert("补充描述字数长度不能超过500");
    				flag = false;
    				return flag;
    			}
    			
    			if(!reportnameflag)
    			{
    				//$("#reportname").css({border:"2px solid #FFCC88"});
    				if(flag)
    				{
    					alert("报告名称已经存在");
    				}
    				flag = false;
    			}
    			
    			return flag;
    		}*/
    	var reportremark = $("#reportremark").val();
    	function OnInput (reportremark) {
            alert ("The new content: " + event.target.value);
        }
    	//验证报告名称是否重复
    	function checkName() 
    	{
    		//var reportname = document.getElementById("reportname").value;
    		var reportname = $("#reportname").combobox('getText');
    		if(reportname == oldReportname || reportname == "")
    		{
    			return;
    		}
    		
    		//var params="reportname="+encodeURI(encodeURI(reportname))+"&tmpnum="+new Date().getTime();
    		var params="reportname="+ reportname +"&tmpnum="+new Date().getTime();
    		$.ajax({
               url: 'riskReport/riskReportInputAction_checkReportName',
               type: 'post',
               dateType: 'json',
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
               		if(data == "fail")
               		{
               			//$("#reportname").css({border:"2px solid #FFCC88"});
               			reportnameflag = false;
              			alert("报告名称已经存在");
               		}
               		else
               		{
               			//$("#reportname").css({border:"1px solid #1383f5"});
               			reportnameflag = true;
               		}
               		oldReportname = reportname;
               },
               error: function () 
               {
               		alert("数据访问错误");
           	   }
       		});
    	}
    	
    	//收集表单参数
    	function collectParams()
    	{
			var reportname = $("#reportname").combobox('getText');
			var taskId = document.getElementById("taskId").value;
			var reportId = document.getElementById("reportId").value;
			var annual = document.getElementById("annual").value;
			var namelist = document.getElementById("namelist").value;
			var Idlist = document.getElementById("Idlist").value;
			var reportremark = document.getElementById("reportremark").value;
			
			var params="reportname="+ reportname +"&annual="+annual
					    + "&namelist=" + namelist + "&Idlist=" + Idlist 
					    + "&reportremark=" + reportremark
						+ "&reportId=" + reportId + "&taskId=" + taskId;
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
    		var url = "riskReport/riskReportInputAction_firstSave";
			var params = collectParams();
			//alert(params);
			submitform(url,params);
    	}
		
		//保存并下一页
		function build()
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
				riskReportInput.action="riskReport/riskReportInputAction_build";
		
			riskReportInput.submit();
		}
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
				riskReportInput.action="riskReport/riskReportInputAction_saveBasicInfo";
		
			riskReportInput.submit();
		}
		
		//提交表单
		function submitform(url,params) 
		{
    		$.ajax({
               url: url,
               type: 'post',
               dateType: 'json',
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
               		//alert("保存成功");
               		var strs = new Array();
               		strs = data.split("-");
               		document.getElementById("reportId").value = strs[0];
               		document.getElementById("taskId").value = strs[1];
           			
           			$("#fileTip").css('display','none');
               		$("#deletereport").css("display","inline");
               		$("#save").css("display","none");
               		$("#savenext").css("display","inline");
               		$("#upload").css("display","inline");
               },
               error: function () 
               {
               		alert("操作失败");
           	   }
       		});
		}
		
		//删除报告
		function delReport()
		{
			var reportId = document.getElementById("reportId").value;
			var params="reportId="+ reportId +"&tmpnum="+new Date().getTime();
			$.ajax({
               url: "riskReport/riskReportInputAction_delRiskReport",
               type: 'post',
               dateType: 'json',
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
               		if(data == "success")
               		{
               			alert("删除成功");
               			isReload = true;
               			window.location.href = "riskReport/riskReportInputAction?tmpnum="+new Date().getTime();
               			//window.location.reload();
               		}
               		//if(data == "fail")
               		//{
               			//alert("报告已经发送，请先撤回再删除");
               		//}
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
			var data = eval(files);
			$("#filetable tr[id=nofile]").remove();			//将"没有文件"那一行删除
			//修改将当前表中的文件的序号
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
							+ "<td><a href=\"/RiskEvent/riskReport/riskReportInputAction_downloadFile?fileId="+data[i].fileId+"\" target=\"_blank\">下载</a></td>"
							+ "<td><a href=\"javascript:void(0)\" onClick=\"deleteFile('filetable',this,"+ data[i].fileId +");return false;\">删除</a></td>"
							+ "</tr>");
				
            }
			//执行完文件添加操作后，修改记录当前文件数量的fileSize的值
			fileSize=fileSize+data.length;
		}
		
		//点击删除按钮删除一行
		function deleteFile(tableID, obj, fileId) 
		{
			//参数为表格ID，触发对象
    		//获得触发对象的行号，parentNode的个数取决于触发对象为TR的第几级子项，input=>td=>tr，所以parentNode有两个
    		var rowIndex = obj.parentNode.parentNode.rowIndex;
    		var table = document.getElementById(tableID);
    		
    		var params="fileId="+ fileId +"&tmpnum="+new Date().getTime();
    		$.ajax({
               url: "riskReport/riskReportInputAction_deleteFile",
               type: 'post',
               dateType: 'json',
               cache:false, 
       		   async:false,  		//同步ajax
               data: params,
               contentType: 'application/x-www-form-urlencoded',			//很重要
               success: function (data) 
               {   
            	    //由于某行删除后对其后面的行的序号有影响，需要修改
            	    //获取当前文件表的行数
            	    var cur_trs = document.getElementById("filetable").rows.length; 
            	    for(var i=rowIndex+1;i<cur_trs;i++){
            	    	//更新待删除行之下每一行的序号
            	    	document.getElementById("filetable").rows[i].cells[0].innerHTML -=1 ;
            	    }
               		table.deleteRow(rowIndex);
               		//修改当前文件表中的文件的数量
               		fileSize= fileSize-1;
               },
               error: function () 
               {
               		alert("删除文件失败");
               		//return false;
           	   }
       		});
    		//obj.parentElement.parentElement.parentElement.deleteRow(rowIndex); //再简化：省略tableID参数
		}
		
	
		//离开页面时触发
		window.onbeforeunload = function() {
			//alert("确定离开本页面?");
			//var reportId = document.getElementById("reportId").value;
			//var reportname = document.getElementById("reportname").value;
			var taskId = document.getElementById("taskId").value;
			var reportId = document.getElementById("reportId").value;
			
			var params="reportId="+reportId+"&taskId="+taskId+"&tmpnum="+new Date().getTime();
			
			if(!isReload)
			{
				if (reportId == null || reportId == undefined || reportId == '')
				{
					return;
				}
				//openBox('提示',350,150,0,'正在保存工作，请等待....','');
				$.ajax({
              		url: "riskReport/riskReportInputAction_cancelAction",
               		type: "post",
               		dateType: "json",
               		cache:false, 
       				async:false,  		//同步ajax
               		data: params,
               		contentType: "application/x-www-form-urlencoded",			//很重要
               		success: function (data) 
               		{   
               			
               		},
               		error: function () 
               		{
               			alert("生成待办时失败");
           	   		}
       			});
			}
			else
			{
				//直接离开页面
			}
		};
	</script>
	</body>
</html>

