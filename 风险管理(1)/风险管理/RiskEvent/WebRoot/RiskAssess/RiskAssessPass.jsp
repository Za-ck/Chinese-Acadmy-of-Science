<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="com.model.Users" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String depId = request.getParameter("depId");
String depNameInpass = (String)request.getAttribute("depNameInpass");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>流转中风险事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="X-UA-Compatible"content="IE=7" /><!--以IE7模式渲染-->   
	<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
</head>

<body style="margin:0px;text-align: center;width: 98%">
<form id="UnFinished" name="UnFinished" method="post">
	<table id="table"  border="1" cellspacing="0" cellpadding="0" class="linktable" width='100%'>
		<thead align="center">
		<%int num = 0;%>
			<tr>
			<td colspan='10' height='50'><%=depNameInpass%></td>
			</tr>
			<tr>
			  <td align="center" height="30" width="5%">
			  <img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
			  </td>
			  <td align="center" width="5%">序号</td>
			  <td align="center" width="14%">风险事件编号</td>
			  <td align="center" width="18%">风险事件名称</td>
			  <td align="center" width="14%">录入时间</td>
			  <td align="center" width="9%">录入人</td>
			  <td align="center" width="9%">流转状态</td>
			  <td align="center" width="8%">考核状态</td>
			  <td align="center" width="9%">考核年份</td>
			  <td align="center" width="14%">考核季度</td>
			</tr>
			
			<s:iterator value='InpassList'>
			<%
            	int recheakflag =  (Integer)request.getAttribute("reCheakFlag"); 
            	String assessStatus = "已考核";
            	if(recheakflag==0){
            		assessStatus="未考核";
            	}else{
            		assessStatus="已考核";
            	}
            %>
            <tr id="trId" height="40px">
<%--            <%if(recheakflag==0){%>--%>
				<td align='center'><input name="idCheck" type="checkbox" value="<s:property value='reId'/>"/></td>
<%--			<%}else{ %>--%>
<%--				<td align='center'>&nbsp;</td>--%>
<%--			<%} %>			--%>
				<td align='center'><%=++num%></td>
            	<td align='center'><s:property value='reId'/></td>
            	<td align='center'><s:property value='reEventname'/></td>
            	<td align='center'><s:property value='reDate'/></td>
            	<td align='center'><s:property value='reCreator'/></td>
            	<td align="center"><a href="javascript:void(0)" onclick="openDetail('<s:property value='reId'/>','<s:property value='reEventname'/>')">
                      <s:if test='reState=="*"'>
                                                              已发布</s:if>
                         <s:elseif test='reState=="0"'>
                         	<s:if test='reAct=="0"'>未修改</s:if>
                         	<s:else>未提交</s:else>
                         </s:elseif>
            			<s:elseif test='reAct=="0"'>   	
            			未通过
            			</s:elseif>      
            			<s:else>
            			<s:if test='reState=="1"'>
                                                              已提交</s:if>
                        <s:else>
            			等待审核中...
            			</s:else>
            			</s:else>
            	</a>
            	</td>
            	<td name="assessStatus"><%= assessStatus%></td>
<%--            	<%if(recheakflag==1){%>--%>
<%--            	<td align="center">--%>
<%--            			<input id="yearNew" name="yearNew" class="Wdate" style="width: 105px;border:none;text-align: center;"--%>
<%----%>
<%--								value="<s:property value="reAssessYear"/>" --%>
<%--								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />--%>
<%--            	</td>--%>
<%--            	<td align="center">--%>
<%--            	第<s:property value="reAssessQuarter"/>季度--%>
<%--            	</td>--%>
<%--            	<%}else{ %>--%>
            	<td>
            		<input id="assessYear" name="year" class="Wdate" style="width: 105px;border:none;text-align: center;"

							<%if(recheakflag==1){%> value="<s:property value="reAssessYear"/>" <%}else{%>	value="<%=request.getAttribute("year") %>" <%}%>
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
<%--								<%=request.getAttribute("year") %>--%>
            	</td>
            	<td class="assessMonth">
            		<select name="quarter" style="width: 105px;border:none;text-align: center;">
            		<%if(recheakflag==0){%>
            		<option value="1"
							<%if("1".equals(request.getAttribute("reAssessQuarter")+""))out.print("selected"); %>>
							第1季度
						</option>
						<option value="2"
							<%if("2".equals(request.getAttribute("reAssessQuarter")+""))out.print("selected"); %>>
							第2季度
						</option>
						<option value="3"
							<%if("3".equals(request.getAttribute("reAssessQuarter")+""))out.print("selected"); %>>
							第3季度
						</option>
						<option value="4"
							<%if("4".equals(request.getAttribute("reAssessQuarter")+""))out.print("selected"); %>>
							第4季度
						</option>
            		<%}else{%>
						<option value="1"
							<%if("1".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
							第1季度
						</option>
						<option value="2"
							<%if("2".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
							第2季度
						</option>
						<option value="3"
							<%if("3".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
							第3季度
						</option>
						<option value="4"
							<%if("4".equals(request.getAttribute("quarter")+""))out.print("selected"); %>>
							第4季度
						</option>
						<%}%>
					</select>
            	</td>
<%--            	<%} %>--%>
            <td>
            <input type="hidden" name="assessStatus" id="assessStatus" value="<%= recheakflag%>" />
            </td>
			</tr>
			</s:iterator>
			<%  
			Users us = (Users) session.getAttribute("loginUser");
     		int pt = us.getUserPosition();		
    		if(pt==9){
     		%>
			<tr>
				<td colspan='10' align='center' height='50'><input type="button" class="right-buttonShort" name="ifSubmit" id="ifSubmit" value="考核" onClick="checkAssess()" /></td>
			</tr>
			<%}else{ }%>
		</thead>
		<tbody align="center">		
		<s:iterator value="#request.resultList" >	
			<tr>
			    <td align="center"><s:property value="raSort"/></td>
			    <td align="center">&nbsp;<s:property value="raAssessResult"/></td>
		  </tr>
		  </s:iterator>
		  <s:if test="#request.resultList.size()==0">
		  <tr>
			  <td>暂无数据</td>
			  <td>暂无数据</td>
		  </tr>
		  </s:if>
		</tbody>	
	</table>
	<input type="hidden" name="reIds" id="reIds" value="" />
	<input type="hidden" name="depId" id="depId" value ="${depId }" />
	</form>
<script>
var flag=0;
function openpage(){
	window.location.replace("");
}
function allselect(){ 
	if(flag==0){ 
		var sum = document.getElementsByName("idCheck"); 
		for(i = 0; i < sum.length; i++){ sum[i].checked=true; 
	} 
	document.getElementById("allcheck").src="IconPage/images/checked.png";
	document.getElementById("isallselected").innerText="取消";
	flag=1;
	}else{ 
		var sum = document.getElementsByName("idCheck"); 
		for(i = 0; i < sum.length; i++){
			sum[i].checked=false; 
		}
		document.getElementById("allcheck").src="IconPage/images/check.png";
		document.getElementById("isallselected").innerText="全选";
		flag=0;
	}; 
}

function checkAssess() {
<%--	alert("考核");--%>
    var reIdList = "";
    var yearList = "";
    var quarterList = "";
    var assessStatusList = "";
	flag=false;
	var sum = document.getElementsByName("idCheck");
	var years = document.getElementsByName("year");
	var quarters = document.getElementsByName("quarter");
	var assessStatuss = document.getElementsByName("assessStatus");
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
		{ 
			flag = true;
			reIdList += sum[i].value + "@";
			yearList += years[i].value + "@";
			quarterList += quarters[i].value + "@";	
			assessStatusList +=assessStatuss[i].value+"@";
		}
	}
<%--	alert(assessStatusList);--%>
<%--    alert(yearList);--%>
	if(flag == false)
	{ 
		alert("请至少选择一条考核项!"); 
		return false;
	} 
	document.getElementById("reIds").value = reIdList;
   	var depId = document.getElementById("depId").value;
   	var reIds = document.getElementById("reIds").value;
   	if(confirm("确定考核?")){
   		var path="riskAssess/riskAssessAssessAction?depId=" + depId+"&reIds="+reIds+"&years="+yearList+"&quarters="+quarterList+"&assessStatuss="+assessStatusList+"&tmpnum="+ Math.random().toString();
   	}
    $.ajax({
       url: path,
       type: 'post',
       dateType: 'json',
       data: '',
       contentType: 'text/html;charset=utf-8',
       success:function (data){
       		if(data == "fail") {
       			alert("没有考核权限");
       			return;
       		}
       		var parentWindow = dialogArguments;
       		parentWindow.query();
       		window.close();
       	},
        error: function () {
 			alert("操作失败");
		}
	});
}
function openDetail(reId,eventname) {

	var parentWindow = dialogArguments;
	parentWindow.openWindow(reId,eventname);

};
</script>
</body>
</html>
