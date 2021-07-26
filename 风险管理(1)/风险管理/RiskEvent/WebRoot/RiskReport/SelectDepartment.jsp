<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>选择部门</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/RiskEvent/IconPage/page.css" rel="stylesheet" type="text/css" >
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
		<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
	</head>

	<body class="ContentBody">
		<form action="" method="post" enctype="multipart/form-data">
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle" align="left">
					<span>风险报告发送</span>
				</div>
			</div>
			<div class="MainDiv" >
				<table style="width: 99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
					<tr>
						<td class="CPanel" align="center">
							<table border="0" cellpadding="0" cellspacing="0" align="center" style="width:100%">
								<tr>
									<td>
										<fieldset>
										  <legend>选择部门</legend>
											<table style="width: 85%;" border="0" align="center" cellspacing="10">
												<tr>
													<td style="height: 35px;width: 45%" align="left">
														<p>
															请选择参编部门：
															&nbsp;&nbsp;&nbsp;
															<input type="button" style="width:70px;height: 24px;" value="全选" onClick="funcDeptSel();return false;" />
															<input type="button" style="width:70px;height: 24px;" value="清空" onClick="funcInvertDeptSel();return false;" />
														</p>
													</td>
													<td style="height: 35px;width: 55%" align="left">
														<p>请选择参编部门负责人：</p>
													</td>
													
												</tr>
													
												<tr>
													<td>
    													<div style="width: 100%;height: 600px;overflow: auto;">
    													<table id='deptable' class='tablestyle' style="width: 100%;" >
   															<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
               													<td width="40%" align="center">选择</td>
               													<td width="60%" align="center">部门名称</td>
               													
           													</tr>
           													
           													<c:forEach items="${deplist }" var="dept">
  																	<tr onclick="changeDep(this);">
             			 												<td align="center">
             			 													<input type="checkbox" name="idCheckDept" value="${dept.depId }" onClick="selectDep(this);" <c:if test="${fn:contains(Idlist ,dept.depId )}">checked="checked"</c:if> />
             			 												</td>
																	<td>${dept.depName }</td>
																</tr>
   															</c:forEach>
   														</table>
    													</div>
   														
     												</td>
     												<td>
     													<div style="width: 100%;height: 160px;overflow: auto;">
     														<table id='leadertable' class='tablestyle' style="width: 100%" >
    															<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">
                													<td width="20%" align="center">选择</td>
                													<td width="30%" align="center">用户编号</td>
                													<td width="50%" align="center">用户名</td>
                													<td style="display:none;">部门编号</td>
            													</tr>
            													
    														</table>
     													</div>
     													
     													<p>已选择的部门和部门负责人:</p>
														
														<div style="width: 100%;height: 380px;overflow:auto;">
															<table id="selecttable" class="tablestyle" style="width: 100%" >
   																<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">          													
               														<td width="30%" align="center">配合部门负责人</td>
               														<td width="50%" align="center">配合部门</td>
               														<td width="20%" align="center">删除</td>
               														<td style="display:none;">部门编号</td>
               														<td style="display:none;">用户编号</td>
           														</tr>
           													
           														<c:forEach items="${leaderlist }" var="leader">
  																	<tr>
             			 												<td>${leader.userName }</td>
																		<td>${leader.depName }</td>
																		<td><a href="javascript:void(0)" onClick="deleteSelectDep(this);return false;">删除</a></td>
																		<td style="display:none;">${leader.depId }</td>
																		<td style="display:none;">${leader.userId }</td>
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
						<td colspan="2" align="center" height="50px">
							<input type="button" style="width:70px;height: 24px;" value="上一页" onClick="javascript:history.back(-1)" />
							<input type="button" style="width:70px;height: 24px;" value="确定" onClick="saveReport('commit');"/> 
						</td>
					</tr>
				</table>
			</div>
		</form>
		<input type="hidden" id="reportId" name="reportId" value="${reportId }" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId }" />
		<input type="hidden" id="reportname" name="reportname" value="${reportname }" />
	</body>
	
	<script type="text/javascript">
		Array.prototype.indexOf = function(val) 
		{
            for (var i = 0; i < this.length; i++) 
            {
                if (this[i] == val) return i;
            }
            return -1;
        };
        Array.prototype.remove = function(val) 
        {
            var index = this.indexOf(val);
            if (index > -1) 
            {
                this.splice(index, 1);
            }
        };
	
		var preDepIds = "";			//用于保存上一次选中的部门的ID
		var currentDepName = "";	//当前选择的部门名称
		var currentdepid = "";		//当前选择的部门的编号
		var isCommitted = false;

		//清空部门
		function funcInvertDeptSel()
		{
		    var sum = document.getElementsByName("idCheckDept");
			for(var i=0;i<sum.length;i++)
			{    
				sum[i].checked=false;
			}
			$("#selecttable tr:gt(0)").remove(); //删除除第一行以外的所有的行
		}
		
		//全选部门
		function funcDeptSel()
		{
		    var sum = document.getElementsByName("idCheckDept");
			for(var i=0;i<sum.length;i++)
			{    
				sum[i].checked=true;
			}
		}
		
		// 部门前面的复选框的单击事件
		function selectDep(target)
		{
			
			if(target.checked =="checked" || target.checked ==true)
			{
				
			}
			else
			{
				//删除selecttable中的一行
				var selecttable = document.getElementById("selecttable");
				for (var j = 1; j < selecttable.rows.length; j++) 
				{    
					//遍历Table的所有Row
  					tableInfo = selecttable.rows[j].cells[3].innerHTML;   //获取Table中单元格的内容
  					//alert(tableInfo);
					if(tableInfo == target.value) 
					{
						$("#selecttable").find("tr").eq(j).remove();
						break;
					}
	    						
				}
			}
			
		}
		
		// 点击部门表中的某一行的时候改变这一行的颜色并且获取该部门的领导
		function changeDep(target)
		{
			var sTable = document.getElementById("deptable");
			var depAll = document.getElementsByName("idCheckDept");
			
		    for(var i=1;i<sTable.rows.length;i++) //遍历除第一行外的所有行
		    {
		        if (sTable.rows[i] != target) //判断是否当前选定行
		        {
		            sTable.rows[i].bgColor = "#ffffff"; //设置背景色
		        }
		        else
		        {
		            sTable.rows[i].bgColor = "#d3d3d3";
		            
		            currentDepName = sTable.rows[i].cells[1].innerHTML;
		            currentdepid = depAll[i-1].value;
		            var leaderid = "";
		            // 遍历selecttable,查看该部门选择了哪个负责人
				    var selecttable = document.getElementById("selecttable");
				    for (var j = 1; j < selecttable.rows.length; j++) 
					{    
						//遍历Table的所有Row
   						tableInfo = selecttable.rows[j].cells[3].innerHTML;   //获取Table中单元格的内容,部门编号
   						//alert(tableInfo);
						if(tableInfo == currentdepid) 
						{
							leaderid = selecttable.rows[j].cells[4].innerHTML;
							break;
						}
  						
					}
		            
		            //alert(currentDepName);
		            //访问后台数据库
		            var params="deptId="+currentdepid+"&tmpnum="+new Date().getTime();
		            $.ajax({
			               url: "riskReport/riskReportInputAction_getLeaderByDepId",
			               type: "post",
			               dateType: "json",
			               data: params,
			               contentType: "application/x-www-form-urlencoded",
			               success: function (response) 
			               {     
			              		var data = eval("("+ response+")");
			              		
			              		$("#leadertable tr:gt(0)").remove(); //删除除第一行以外的所有的行
			              		
			              		var checked = "";
			              		
			              		for(var i=0;i<data.length;i++)
			               		{   
			               			if(data[i].userid == leaderid) 
			               			{
			               				checked = "checked";
			               			}
			               			else
			               			{
			               				checked = "";
			               			}
			               			
			               			$("#leadertable").append("<tr>"
			               			 				+ "<td><input type=\"checkbox\" id=\"idCheckLeader\" "+checked+" value=\""+data[i].userid+"\" onClick=\"selectLeader(this)\"/></td>"
													+ "<td>"+data[i].userid+"</td>"
													+ "<td>"+data[i].username+"</td>"
													+ "<td style=\"display:none;\">"+data[i].depid+"</td>"
													+ "</tr>");
			                 		
							    }
							    $("#leadertable tr td").attr("align","center");
							    
							    
			               },
			               error: function () 
			               {
			               		alert("error");
			           	   }
			       });
		        }
		    } 
		}
		
		function selectLeader(target)
		{
			var depleader = document.getElementsByName("idCheckLeader");
			var leadertable = document.getElementById("leadertable");
			
			// 遍历deptable,查看该部门是否已经选择了,如果没有选择的话那么就不能够选择这个部门的负责人
			var deptable = document.getElementsByName("idCheckDept");
			var flag = true;
			if(null != deptable)
			{
				for(var i=0; i < deptable.length;i++)
				{
					if(currentdepid == deptable[i].value)
					{
						if(deptable[i].checked =="checked" || deptable[i].checked ==true)
						{
							flag = true;
						}
						else
						{
							flag = false;
						}
					}
				}
			}
			
			if(null != depleader)
			{
				var tableInfo = "";
				for(var i=0;i<depleader.length;i++)
				{
					// 如果是选中该部门
					if(depleader[i].checked =="checked" || depleader[i].checked ==true)
					{
						//如果对应的部门没有选中，那么部门负责人是不可选的
						if(!flag)
						{
							depleader[i].checked = false;
							continue;
						}
						if(depleader[i] == target)
						{
							//alert(currentdepid);
							// 先遍历selecttable然后删除一行,然后添加一行
							var selecttable = document.getElementById("selecttable");
							for (var j = 1; j < selecttable.rows.length; j++) 
							{    
								//遍历Table的所有Row
            					tableInfo = selecttable.rows[j].cells[3].innerHTML;   //获取Table中单元格的内容
            					//alert(tableInfo);
           						if(tableInfo == currentdepid) 
           						{
           							$("#selecttable").find("tr").eq(j).remove();
           							break;
           						}
        						
    						}
    						//alert(leadertable.rows[i+1].cells[1].innerHTML);
    						$("#selecttable").append("<tr>"
			               			 				+ "<td>"+leadertable.rows[i+1].cells[2].innerHTML+"</td>"
													+ "<td>"+currentDepName+"</td>"
													+ "<td>"+"<a href=\"javascript:void(0)\" onClick=\"deleteSelectDep(this);return false;\">删除</a>"+"</td>"
													+ "<td style=\"display:none;\">"+currentdepid+"</td>"
													+ "<td style=\"display:none;\">"+leadertable.rows[i+1].cells[1].innerHTML+"</td>"
													+ "</tr>");
						}
						else
						{
							// 取消选中
							//alert(depleader[i].value);
							depleader[i].checked = false;
						}
					}
					
				}
			}
		}
		
		function deleteSelectDep(target) 
		{
			var rowIndex = target.parentNode.parentNode.rowIndex;
			var table = document.getElementById("selecttable");
			
			var depid = table.rows[rowIndex].cells[3].innerHTML;
			
			table.deleteRow(rowIndex);
			
			//遍历deptable,取消该用户所在部门前面的勾勾
			var depAll = document.getElementsByName("idCheckDept");
			
			if(null != depAll)
			{
				for(var i=0; i < depAll.length;i++)
				{
					if(depid == depAll[i].value)
					{
						depAll[i].checked = "";
					}
				}
			}
		}
		
		// 收集已经选择的部门以及部门负责人
		function collectParams()
		{
			//封装json格式的数组
			var jsonArray = "[";
			var selecttable = document.getElementById("selecttable");
			for (var i = 1; i < selecttable.rows.length; i++) 
			{
				jsonArray += "{" + "username:'"+selecttable.rows[i].cells[0].innerHTML+"',"
							+ "depname:'"+selecttable.rows[i].cells[1].innerHTML+"',"
							+ "depid:'"+selecttable.rows[i].cells[3].innerHTML+"',"
							+ "userid:'"+selecttable.rows[i].cells[4].innerHTML+"'},";
			}
			jsonArray = jsonArray.substring(0, jsonArray.length -1);
			if (jsonArray == null || jsonArray == undefined || jsonArray == '') 
			{
				return "[]";
			} 
			jsonArray += "]";
			return jsonArray;
		}
		
		// 发送或者保存报告
		function saveReport(operation)
		{
			var jsonArray = collectParams();
			if(jsonArray == "[]")
			{
				alert("您还没有选择部门");
				return;
			}
			var reportId = document.getElementById("reportId").value;
			var taskId = document.getElementById("taskId").value;
			var reportname = document.getElementById("reportname").value;
			
			var params="operation="+ operation +"&reportId="+reportId
					    + "&taskId=" + taskId + "&leaderlist=" + jsonArray
					    + "&reportname=" + reportname;
			var url = "riskReport/riskReportInputAction_saveOrCommitReport";
			
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
               			isCommitted = true;
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
		
		
		//离开页面时触发
		window.onbeforeunload = function() {
			//alert("确定离开本页面?");
			//var reportId = document.getElementById("reportId").value;
			//var reportname = document.getElementById("reportname").value;
			var taskId = document.getElementById("taskId").value;
			var reportId = document.getElementById("reportId").value;
			
			var params="reportId="+reportId+"&taskId="+taskId+"&tmpnum="+new Date().getTime();
			
			if(!isCommitted)
			{
				if (reportId == null || reportId == undefined || reportId == '')
				{
					return;
				}
				openBox("提示",350,150,0,"正在保存工作，请等待....","");
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
	
</html>
