<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" import="com.model.Users" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>风险管理考核</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
	<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
	<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript"></script> 
	<script language=javascript src="/RiskEvent/js/windows.js"></script>
</head>

<body style="font-size:12px; margin-top:5px;" onload="init();">
<form id="riskAssessManage" name="riskAssessManage" method="post" action="riskAssess/riskAssessBySituationAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险管理考核--应对情况</span></div></div>
<div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px;">
		<li style="float:left;margin-left:0px;font-size:14px;"> 录入部门：
		  <!--<s:select name="raDepId" theme="simple" list="alldepList" listValue="depName" listKey="depId"></s:select>-->
		  <c:choose>
							
			<c:when test="${empty readonly }">
				<s:select name="raDepId" theme="simple" list="alldepList"
					listValue="depName" listKey="depId" ></s:select>
			</c:when>
			<c:otherwise>
				<s:select name="raDepId" theme="simple" list="alldepList"
					listValue="depName" listKey="depId" disabled="true"></s:select>
				<input type="hidden" name="raDepId" value="${raDepId }" />
			</c:otherwise>
		
		 </c:choose>
</li>

 <li style="float: left; margin-left: 10px; font-size: 14px;">
							时间：
						

							<input name="raYear" class="Wdate" style="width: 105px;"
							     
								value="<s:property value='raYear'/>"
								onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
						</li> 
        <li style="float:left;margin-left:10px;font-size:14px;"> 
                  <select name="raQuarter"  class="Wdate" style="width:105px;">
                         <option value="1"<%if("1".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>第1季度</option>
                         <option value="2"<%if("2".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>第2季度</option>
                         <option value="3"<%if("3".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>第3季度</option>
                         <option value="4"<%if("4".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>第4季度</option>
                  </select>
</li>  
<li style="float:left;margin-left:10px;font-size:14px;"> 至<input name="raYearTo" class="Wdate" style="width:105px;" value="<s:property value='raYearTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})"/>
</li>
        <li style="float:left;margin-left:10px;font-size:14px;"> 
                  <select name="raQuarterTo" class="Wdate" style="width:105px;">
                         <option value="1"<%if("1".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>第1季度</option>
                         <option value="2"<%if("2".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>第2季度</option>
                         <option value="3"<%if("3".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>第3季度</option>
                         <option value="4"<%if("4".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>第4季度</option>
                  </select>
</li> 
 
        </ul>
        <div style="width:100%;float: left;height:30px;text-align: center;margin-top:10px;">
        <input type="button" class="right-buttonShort" name="buttonQuery" id="buttonQuery" value="查询" onClick="query();" />					     
		<input type="button" class="right-buttonShort" name="buttonReset" id="buttonReset" value="清空" onClick="reSetmess();"/>
        </div>
		</div>
		</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' style="font-size: 14px">
    	<tr style="background-image:url(/RiskEvent/IconPage/images/thbg.png)">				
						<td width="5%" align="center" height="30">序号</td>
						<td width="22%" align="center">时间区间</td>       
                		<td width="28%" align="center">部门名称</td>
                		<td width="9%" align="center">需应对</td>
                		<td width="9%" align="center">实际应对</td>
                		<td width="9%" align="center">未达标</td>
                		<td width="9%" align="center">待应对</td>
                		<td width="9%" align="center">考核分数<img src='/RiskEvent/IconPage/images/up.png' style='cursor:pointer;' id="score" onClick="changeUpDown()" /></td>
            		</tr>
            		<tbody id='body_RiskAssessManage'>
            		<%
            		int num=0;
            		String RaYear = (String)request.getAttribute("raYear");
            		String RaQuarter = (String)request.getAttribute("raQuarter");
            		String RaYearTo = (String)request.getAttribute("raYearTo");
            		String RaQuarterTo = (String)request.getAttribute("raQuarterTo");
            		String RaYear11 = (String)request.getAttribute("raYear11");
            		String RaQuarter11 = (String)request.getAttribute("raQuarter11");
            		String RaYearTo11 = (String)request.getAttribute("raYearTo11");
            		String RaQuarterTo11 = (String)request.getAttribute("raQuarterTo11");
            		
            		%>
            		<s:iterator value='rbList'>
            		<tr>
						<td align='center'><%=++num%></td>
            			<td align='center'><s:property value='TimeFrom'/></td>
            			<td align='center'><s:property value='DepName'/></td>
            			<td align='center'><s:property value='StandardNum'/></td>
            			<td align='center'><s:property value='NumberDealAll'/></td>
            			<!--  <td align='center'><a style="cursor:pointer" onClick="howModelessDialog('riskAssess/riskAssessUnFinishedAction?depId=<s:property value='DepId'/>&yearFrom=<%=RaYear%>&yearTo=<%=RaYearTo%>&quarterFrom=<%=RaQuarter%>&quarterTo=<%=RaQuarterTo%>'><s:property value='UnFinishedNum'/>',window,'status:false;dialogWidth:800px;dialogHeight:250px;resizable:yes;scroll:yes');"></a></td>-->
            			<td title="单击查看未应对的风险事件信息" style="cursor:pointer" onClick="showModelessDialog('riskAssess/riskAssessUnFinishedAction?depId=<s:property value='DepId'/>&yearFrom=<%=RaYear%>&yearTo=<%=RaYearTo%>&quarterFrom=<%=RaQuarter%>&quarterTo=<%=RaQuarterTo%>',window,'status:false;dialogWidth:1000px;dialogHeight:400px;resizable:yes;scroll:yes');"><a style="text-decoration: underline"><s:property value='UnFinishedNum'/></a></td>
            			<td title="单击查看待应对的风险事件信息" style="cursor:pointer" onClick="showModelessDialog('riskAssess/riskAssessUnFinishedAction1?depId=<s:property value='DepId'/>&yearFrom=<%=RaYear%>&yearTo=<%=RaYearTo%>&quarterFrom=<%=RaQuarter%>&quarterTo=<%=RaQuarterTo%>',window,'status:false;dialogWidth:1000px;dialogHeight:400px;resizable:yes;scroll:yes');"><a style="text-decoration: underline"><s:property value='ToDealnumber'/></a></td>
            			<td align='center'><s:property value='assessResultQuarter'/></td>
					</tr>
            		</s:iterator>
            		
            		<tr >
						<td align='center'>小计</td>
            			<td align='center'></td>
            			<td align='center'></td>
            			<td align='center'></td>
            			<td align='center'></td>
            			<td align='center'></td>
            			<td align='center'></td>
            			<td align='center'></td>
					</tr>
            		</tbody>
        		</table>
        		</div>
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				     
        	<input type="text" style="visibility:hidden"  name="actionName" id="actionName" value="<s:property value="actionName"/>"/>
			<input type="hidden" name="order" id="order" value="${order }" />
     </form>
<script>
//应处理小计
function init()
{
	var tblObj = document.getElementById("mytable");
	var length = tblObj.rows.length;
	var Total=0;
	
  for( var i=1; i<length-1 ; i++ )
  {
     Total += parseInt(tblObj.rows.item(i).cells[3].innerText);
  }
  tblObj.rows.item(length-1).cells[3].innerText=Total;
//实际处理小计
var tblObj = document.getElementById("mytable");
	var length = tblObj.rows.length;
	var Total=0;
  for( var i=1; i<length-1 ; i++ )
  {
     Total += parseInt(tblObj.rows.item(i).cells[4].innerText);
  }
  tblObj.rows.item(length-1).cells[4].innerText=Total;
  //未处理小计
var tblObj = document.getElementById("mytable");
	var length = tblObj.rows.length;
	var Total=0;
  for( var i=1; i<length-1 ; i++ )
  {
     Total += parseInt(tblObj.rows.item(i).cells[5].innerText);
  }
	tblObj.rows.item(length-1).cells[5].innerText=Total;
	
	var orderString = document.getElementById("order").value;
			
	if (orderString !== null || orderString !== undefined || orderString !== '') 
	{
		var path=document.getElementById("score");
		if(orderString.toLowerCase() == "DESC".toLowerCase())
		{
			path.src="/RiskEvent/IconPage/images/dn.png";
		}
		else 
		{
			path.src="/RiskEvent/IconPage/images/up.png";
		}
	};
	//待处理小计
	var tblObj = document.getElementById("mytable");
	var length = tblObj.rows.length;
	var Total=0;
  for( var i=1; i<length-1 ; i++ )
  {
     Total += parseInt(tblObj.rows.item(i).cells[6].innerText);
  }
  tblObj.rows.item(length-1).cells[6].innerText=Total;
}


//查询
function query()
{
	document.forms[0].action="riskAssess/riskAssessQueryAction2?current_pagenum=1";
	document.forms[0].submit();
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		document.forms[0].action="riskAssess/riskAssessQueryAction2?current_pagenum=1";
		document.forms[0].submit();
	}
}
//清空
function reSetmess()
{

    var mydate = new Date();
    var Year;
    var Month;
    var Quarter=0;
    Year=mydate.getFullYear();
    Month=mydate.getMonth()+1;
    switch(Month){
    case 1:
	case 2:
	case 3:
		Quarter+=1;
		break;
	case 4:
    case 5:
    case 6:
        Quarter+=2;
        break ;
    case 7:
    case 8:
    case 9:
        Quarter+=3;
        	break ;
        case 10:
        case 11:
        case 12:
        Quarter+=4;
	}
			
    document.getElementById('raDepId').value="none1";
	document.getElementById('raYear').value=Year;
	document.getElementById('raQuarter').value= Quarter;
	document.getElementById('raYearTo').value= Year;
	document.getElementById('raQuarterTo').value= Quarter;
	document.getElementById('choosedIdl').value="0";

};

function changeUpDown(){
	
	var path=document.getElementById("score");
	if(path.src.indexOf('up.png')!=-1) { 
		path.src="/RiskEvent/IconPage/images/dn.png";
 		ordersorts='desc'; 
 		var s=document.getElementById("actionName").value+"?order=";
		document.forms[0].action=s+ordersorts;
		document.forms[0].submit();
    }
	else {
		path.src="/RiskEvent/IconPage/images/up.png"; 
		ordersorts='asc'; 
		var s=document.getElementById("actionName").value+"?order=";
		document.forms[0].action=s+ordersorts;
		document.forms[0].submit();
 	};
};

</script>
</body>
</html>