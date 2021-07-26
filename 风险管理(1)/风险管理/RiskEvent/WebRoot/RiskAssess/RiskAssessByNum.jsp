<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
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
		<link href="/RiskEvent/css/webstyle.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="/RiskEvent/IconPage/page.css">
		<script language="javascript" src="IconPage/page.js"></script>
		<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
		<script language="javascript"
			src="/RiskEvent/js/validation-framework.js"></script>
		<script src="/RiskEvent/js/DatePicker/WdatePicker.js"
			type="text/javascript" defer></script>
		<script language=javascript src="/RiskEvent/js/windows.js"></script>
	</head>

	<body style="font-size: 12px; margin-top: 5px;" onload="init()">

		<form id="riskAssessManage" name="riskAssessManage" method="post"
			action="riskAssess/riskAssessByNumAction">
			<div class="toptitle">
				<div class="toptitleleft"></div>
				<div class="topttileright"></div>
				<div class="toptitlemiddle">
					<span>风险管理考核--风险填报</span>
				</div>
			</div>

			<div class="queryDiv">
				<div style="display: ; text-align: center">
					<ul style="list-style-type: none; width: inherit; margin-top: 5px;">



						<li style="float: left; margin-left: 0px; font-size: 14px;">
							录入部门：
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
							查询时间：
							<s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间','3':'应对时间'}"  style="width:154px;" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>

							<input name="raYear" class="Wdate" style="width: 105px;"
							     
								value="<s:property value='raYear'/>"
								onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
						</li>
						<li style="float: left; margin-left: 10px; font-size: 14px;">
							<select name="raQuarter" class="Wdate" style="width: 105px;">
								<option value="1"
									<%if("1".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>
									第1季度
								</option>
								<option value="2"
									<%if("2".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>
									第2季度
								</option>
								<option value="3"
									<%if("3".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>
									第3季度
								</option>
								<option value="4"
									<%if("4".equals(request.getAttribute("raQuarter")+""))out.print("selected"); %>>
									第4季度
								</option>
							</select>
						</li>
						<li style="float: left; margin-left: 10px; font-size: 14px;">
							至
							<input name="raYearTo" class="Wdate" style="width: 105px;"
								value="<s:property value='raYearTo'/>"
								onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"
								onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy'})" />
						</li>
						<li style="float: left; margin-left: 10px; font-size: 14px;">
							<select name="raQuarterTo" class="Wdate" style="width: 105px;">
								<option value="1"
									<%if("1".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>
									第1季度
								</option>
								<option value="2"
									<%if("2".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>
									第2季度
								</option>
								<option value="3"
									<%if("3".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>
									第3季度
								</option>
								<option value="4"
									<%if("4".equals(request.getAttribute("raQuarterTo")+""))out.print("selected"); %>>
									第4季度
								</option>
							</select>
						</li>
						
						
						
					</ul>
					<div
						style="width: 100%; float: left; height: 30px; text-align: center; margin-top: 10px;">
						<input type="button" class="right-buttonShort" name="buttonQuery"
							id="buttonQuery" value="查询" onClick="query();" />
						<input type="button" class="right-buttonShort" name="buttonReset"
							id="buttonReset" value="清空" onClick="reSetmess();" />
					</div>
				</div>
				
			</div>

			<div id='pagetable'>
				<table id='mytable' class='tablestyle' width='100%' height='100%' style="font-size: 14px">
					<tr style="background-image: url(IconPage/images/thbg.png)">
						<td width="5%" align="center" height="30">
							序号
						</td>
						<td width="20%" align="center">
							时间区间
						</td>
						<td width="9%" align="center">
							部门属性
						</td>
						<td width="20%" align="center">
							部门名称
						</td>
						<td width="10%" align="center">
							需填报
						</td>
						<td width="10%" align="center">
							实际填报
						</td>
						<td width="10%" align="center">
							未填报
						</td>
						<td width="10%" align="center">
							流转中
						</td>
						<td width="10%" align="center">
							考核分数<img src='IconPage/images/up.png' style='cursor:pointer;' id="score" onClick="changeUpDown()" />
						</td>
					</tr>
					<tbody id='body_RiskAssessManage'>
						<%
            		int num=0;
            		String RaYear = (String)request.getAttribute("raYear");
            		String RaQuarter = (String)request.getAttribute("raQuarter");
            		String RaYearTo = (String)request.getAttribute("raYearTo");
            		String RaQuarterTo = (String)request.getAttribute("raQuarterTo");
            		String RaYear1 = (String)request.getAttribute("raYear1");
            		String RaQuarter1 = (String)request.getAttribute("raQuarter1");
            		String RaYearTo1 = (String)request.getAttribute("raYearTo1");
            		String RaQuarterTo1 = (String)request.getAttribute("raQuarterTo1");
            		
            		%>
						<s:iterator value='raList'>
							<tr>
								<td align='center'><%=++num%></td>
								<td align='center'>
									<s:property value='TimeFrom' />
								</td>
								<td align='center'>
									<s:property value='DepProperty' />
								</td>
								<td align='center'>
									<s:property value='DepName' />
								</td>
								<td align='center'>
									<s:property value='StandardNum' />
								</td>
								<td title="单击查看实际填报的风险事件信息" style="cursor:pointer" onClick="showModelessDialog('riskAssess/riskAssessPassedAction?depId=<s:property value='DepId'/>&yearFrom=<%=RaYear%>&yearTo=<%=RaYearTo%>&quarterFrom=<%=RaQuarter%>&quarterTo=<%=RaQuarterTo%>',window,'status:false;dialogWidth:1000px;dialogHeight:400px;resizable:yes;scroll:yes');"><a style="text-decoration: underline"><s:property
												value='NumberInputAll' /></a></td>
								<td align='center'>
									<s:property value='UnFinishedNum' />
								</td>

								<td title="单击查看流转中的风险事件信息" style="cursor:pointer" onClick="showModelessDialog('riskAssess/riskAssessPassAction?depId=<s:property value='DepId'/>&yearFrom=<%=RaYear%>&yearTo=<%=RaYearTo%>&quarterFrom=<%=RaQuarter%>&quarterTo=<%=RaQuarterTo%>',window,'status:false;dialogWidth:1000px;dialogHeight:400px;resizable:yes;scroll:yes');"><a style="text-decoration: underline"><s:property
												value='NumInPass' /></a></td>
								<td align='center'>
									<s:property value='assessResultQuarter' />
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td align='center'>小计</td>
							<td align='center'></td>
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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="6">
						<img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" />
					</td>
				</tr>
			</table>

			<input type="text" style="visibility: hidden" name="actionName" id="actionName" value="<s:property value="actionName"/>" />
			<input type="hidden" name="order" id="order" value="${order }" />
		</form>
		<script>
<%
  String params=request.getParameter("operation");
  String[] temps=null;
  String operation="",result="";
  if(params!=null){
	 temps=params.split("\\*");	 
      operation=temps[0];
      result=temps[1].split("=")[1];
 }
%>
//var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if(operation=="assess"&&result=="success") alert("考核成功!");
	else if(operation=="assess"&&result=="fail") alert("删除失败!");	
}

function openWindow(url) {
	showModelessDialog(url,window,'status:false;dialogWidth:1800px;dialogHeight:400px;resizable:yes;scroll:yes');
}

function init(){

//应录入小计
var tblObj = document.getElementById("mytable");
	var length = tblObj.rows.length;
	var Total1=0;
	
  for( var i=1; i<length-1 ; i++ )
  {
     Total1 += parseInt(tblObj.rows.item(i).cells[4].innerText);
  }
  tblObj.rows.item(length-1).cells[4].innerText=Total1;
//实际录入小计

	var Total2=0;
  for( var i=1; i<length-1 ; i++ )
  {
     Total2 += parseInt(tblObj.rows.item(i).cells[5].innerText);
  }
  tblObj.rows.item(length-1).cells[5].innerText=Total2;
  
  //未完成小计
	var Total3=0;
  for( var i=1; i<length-1 ; i++ )
  {
     Total3 += parseInt(tblObj.rows.item(i).cells[6].innerText);
  }
	tblObj.rows.item(length-1).cells[6].innerText=Total3;
//流转中小计

	var Total4=0;
  for( var i=1; i<length-1 ; i++ )
  { 
  	var value=tblObj.rows.item(i).cells[7].innerText;
  	if (value !== null && value !== undefined && value !== '')
     	Total4 += parseInt(tblObj.rows.item(i).cells[7].innerText);
  }
	tblObj.rows.item(length-1).cells[7].innerText=Total4;
	
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
	
}



//查询
function query()
{
	document.forms[0].action="riskAssess/riskAssessQueryAction"; 
	document.forms[0].submit();
}
//alert("与上query()同，只是把enter键作为触发事件");
function EnterPress(e){
	var e=e || window.event;
	if(e.keyCode == 13){
		document.forms[0].action="riskAssess/riskAssessQueryAction"; 
		document.forms[0].submit();
	};
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
	document.getElementById('choosedId').value="0";
}


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

function openWindow(reId,eventname) {

	//var url = "riskInput/REIQReadAction?reId=" + reId;
	var riskId = reId.substring(0,reId.lastIndexOf("-"));
	window.open("/RiskEvent/riskInput/REIQReadAction?reId="+reId);
	//window.parent.openTab(url,"查看详情-" + eventname);

};

</script>
	</body>
</html>
