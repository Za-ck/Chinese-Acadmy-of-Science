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
					<span>筛选考核部门</span>
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
															请选择参与考核部门：
															&nbsp;&nbsp;&nbsp;
															<input type="button" style="width:70px;height: 24px;" value="全选" onClick="funcDeptSel();return false;" />
															<input type="button" style="width:70px;height: 24px;" value="清空" onClick="funcInvertDeptSel();return false;" />
														</p>
													</td>
													<td style="height: 35px;width: 55%" align="left">
														<p>已选择参与考核部门：</p>
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
             			 													<input type="checkbox" name="idCheckDept" value="${dept.depId }" onClick="selectDep(this);" <c:if test="${dept.depAssess == 1}">checked="checked"</c:if> />
             			 												</td>
																	<td>${dept.depName }</td>
																</tr>
   															</c:forEach>
   														</table>
    													</div>
   														
     												</td>
     												<td>
     													
														<div style="width: 100%;height: 600px;overflow:auto;">
															<table id="selecttable" class="tablestyle" style="width: 100%" >
   																<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">          													
               														<td width="50%" align="center">考核部门</td>
               														<td width="20%" align="center">删除</td>
               														<td style="display:none;">部门编号</td>
           														</tr>
           													
           														<c:forEach items="${depassesslist }" var="depassess">
  																	<tr>
																		<td>${depassess.depName }</td>
																		<td><a href="javascript:void(0)" onClick="deleteSelectDep(this);return false;">删除</a></td>
																		<td style="display:none;">${depassess.depId }</td>
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
							
							<input type="button" style="width:70px;height: 24px;" value="提交" onClick="depassess();"/> 
						</td>
					</tr>

				</table>
			</div>
		</form>
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
	
		var currentDepName = "";	//当前选择的部门名称
		var currentdepid = "";		//当前选择的部门的编号

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
		    var sTable = document.getElementById("deptable");
		    
		    // 先遍历selecttable然后删除一行,然后添加一行
			var selecttable = document.getElementById("selecttable");
			for (var j = 1; j < selecttable.rows.length; j++) 
			{    
				$("#selecttable").find("tr").eq(j).remove();
				
			}
		    
			for(var i=0;i<sum.length;i++)
			{    
				sum[i].checked=true;
				
				$("#selecttable").append("<tr>"
							+ "<td>"+sTable.rows[i+1].cells[1].innerHTML+"</td>"
							+ "<td>"+"<a href=\"javascript:void(0)\" onClick=\"deleteSelectDep(this);return false;\">删除</a>"+"</td>"
							+ "<td style=\"display:none;\">"+sum[i].value+"</td>"
							+ "</tr>");
				
			}
		}
		
		// 部门前面的复选框的单击事件
		function selectDep(target)
		{
			var sTable = document.getElementById("deptable");
			var sum = document.getElementsByName("idCheckDept");
			//如果就在selecttable中新增一行
			if(target.checked =="checked" || target.checked ==true)
			{
				// 先遍历selecttable然后删除一行,然后添加一行
				var selecttable = document.getElementById("selecttable");
				for (var j = 1; j < selecttable.rows.length; j++) 
				{    
					//遍历Table的所有Row
					tableInfo = selecttable.rows[j].cells[2].innerHTML;   //获取Table中单元格的内容
					
					if(tableInfo == target.value) 
					{
						return;
					}
     						
 				}
 				
				//alert(leadertable.rows[i+1].cells[1].innerHTML);
				for (var j = 0; j < sum.length; j++) 
				{    
					//遍历Table的所有Row
					tableInfo = sum[j].value;   //获取Table中单元格的内容
					
					if(tableInfo == target.value) 
					{
						$("#selecttable").append("<tr>"
							+ "<td>"+sTable.rows[j+1].cells[1].innerHTML+"</td>"
							+ "<td>"+"<a href=\"javascript:void(0)\" onClick=\"deleteSelectDep(this);return false;\">删除</a>"+"</td>"
							+ "<td style=\"display:none;\">"+target.value+"</td>"
							+ "</tr>");
						return;
					}
     						
 				}
			}
			else
			{
				//删除selecttable中的一行
				var selecttable = document.getElementById("selecttable");
				for (var j = 1; j < selecttable.rows.length; j++) 
				{    
					//遍历Table的所有Row
  					tableInfo = selecttable.rows[j].cells[2].innerHTML;   //获取Table中单元格的内容
  					//alert(tableInfo);
					if(tableInfo == target.value) 
					{
						$("#selecttable").find("tr").eq(j).remove();
						break;
					}
	    						
				}
			}
			
		}
		
		// 点击部门表中的某一行的时候改变这一行的颜色
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
		        }
		    }
		}
		
		function deleteSelectDep(target) 
		{
			var rowIndex = target.parentNode.parentNode.rowIndex;
			var table = document.getElementById("selecttable");
			
			var depid = table.rows[rowIndex].cells[2].innerHTML;
			
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
			var depIdList = "";
			var selecttable = document.getElementById("selecttable");
			for (var i = 1; i < selecttable.rows.length; i++) 
			{
				depIdList += selecttable.rows[i].cells[2].innerHTML + ",";
			}
			depIdList = depIdList.substring(0, depIdList.length -1);
			if (depIdList == null || depIdList == undefined || depIdList == '') 
			{
				return "";
			} 
			return depIdList;
		}
		
		function depassess()
		{
			var depIds = collectParams();
			
			var params="depIds=" + depIds;
			var url = "dataUnit/DepartmentAssessAction";
			
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
               		if(data == "success")
               		{
               			alert("设置成功");
               		}
               		else
               		{
               			alert("设置失败");
               		}
               },
               error: function () 
               {
               		alert("操作失败");
           	   }
       		});
			
					
		}
		
	</script>
	
</html>
