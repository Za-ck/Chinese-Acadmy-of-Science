<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<style type="text/css"></style>
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
</head>

<form name="userManage" id="userManage" method="post" action="systemManage/UserManageAction">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>登录用户管理</span></div></div>

<div class="queryDiv">
        <div style="display:;text-align: center">
        <ul style="list-style-type:none;width:inherit;margin-top:5px;">
        
		<li style="float:left;margin-left:0px;font-size:14px;"> 所属部门：<s:select name="reIndep" theme="simple" list="alldepList" listValue="depName" listKey="depName" class="text" style="width:240px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li>
   
<li style="float:left;margin-left:20px;font-size:14px;"> 姓名：<input height="30" name="usernameString" id="userNameString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="" />
</li>
        <li style="float:left;margin-left:10px;font-size:14px;"> 登陆账号：<input height="30" name="userIdString" id="suserIdString" type="text" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" value="" />
</li>  
 <li style="float:left;margin-left:10px;font-size:14px;"> 用户状态：<s:select name="userState" theme="simple" style="width:90px" list="alluserstateList" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
</li> 
<li><span><a onclick="query()"><img src="images/mag.png"/>统计查询</a></span></li>
        </ul>
        
		</div>
  </div>
		
<div id='pageMenu' style="background-image:url(IconPage/images/bg.png)">
<a href='/RiskEvent/SystemManage/UMAddPreAction'><img  src='IconPage/images/add.png'/><span>新增</span></a>&nbsp;&nbsp;
<a onClick="checkUpdate()" ><img src='IconPage/images/update.png'/><span>修改</span></a>&nbsp;&nbsp;
<a onClick="checkDelete()"><img src='IconPage/images/close.png'/><span>删除</span></a>&nbsp;&nbsp;
<a onClick="checkOperation()"><img src='IconPage/images/beiyong.png'/><span>初始化密码</span></a>&nbsp;&nbsp;
<a onClick="checkEnable()"><img src='IconPage/images/update.png'/><span>启用</span></a>&nbsp;&nbsp;
<img  src='IconPage/images/line.gif'/>
</div>
<div id='pagetable'> 
    <table id='mytable' class='tablestyle' width='100%' height='100%' >
    	<tr style="background-image:url(IconPage/images/thbg.png)">				
						<td width="5%" align="center" height="30">
						<!--<a href="###" onClick="funcAllSel();return false;"><span>全选</span></a>-<a href="###" onClick="funcInvertSel();return false;"><span>反选</span></a>-->
						<img src='IconPage/images/check.png' onClick="allselect()" width="13" height="13" id="allcheck"/><span id="isallselected">全选</span>
						</td>
                		<td width="5%" align="center">序号</td>
						<td width="13%" align="center">所属部门<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersdepName" onClick="changeUpDown('depName')" /></td>
                		<td width="13%" align="center">用户角色<img src='IconPage/images/up.png' style='cursor:pointer;' id="orderssrId" onClick="changeUpDown('srId')" /></td>
                		<td width="12%" align="center">姓名<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersuserName" onClick="changeUpDown('userName')" /></td>
                		<td width="8%" align="center">登录账号<img src='IconPage/images/up.png' style='cursor:pointer;' id="ordersuserId" onClick="changeUpDown('userId')" /></td>
                		<td width="13%" align="center">办公电话</td>
                		<td width="8%" align="center">手机</td>
						<td width="13%" align="center">Email</td>
						<%int num=0;%>
	  </tr>
					
					<tbody id="body_userManage">
            		<s:iterator value="useList">
            		<tr align="center">
              			<td align="center" height="25"><input type="checkbox" name="idCheck"  value="<s:property value='userId'/>"/></td>
						<td align="center"><%=++num%></td>
              			<td align="center"><s:property value="depName"/></td>
            			<td align="center"><s:property value="srName"/></td>
            			<td align="center"><s:property value="userName"/></td>
            			<td align="center"><s:property value="userId"/></td>
            			<td align="center"><s:property value="userTel"/></td>
            			<td align="center"><s:property value="userCellphone"/></td>
						<td align="center"><s:property value="userEmail"/></td>
            		</tr>
            		</s:iterator>
            		</tbody>
				
	</table>
  </div>
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        			<tr>
          				<td height="6"><img src="/RiskEvent/images/main/spacer.gif" width="1" height="1" /></td>
        			</tr>
				</table>
				<div id='pageDIV' style="background-image:url(IconPage/images/bg.png)">
					<a onClick="firstpage()"><img border='0' title='首页' src='IconPage/images/first.gif'/></a>
                    <a onClick="prepage()"><img border='0'title='上一页' src='IconPage/images/prev.gif'/></a>
                    <img border='0' src='IconPage/images/line.gif'/><span>&nbsp;&nbsp;&nbsp;&nbsp;第</span>
                    <input id='current_pagenum' style='width:45px' type='text' value="<s:property value="current_pagenum"/>"/> <span>页&nbsp;&nbsp;</span>
                    <img border='0' src='IconPage/images/hl.png'/><span>共
                  <%
                     int pc=Integer.valueOf(request.getSession().getAttribute("pagecount").toString());
                     int pg=0;
                     if(pc%10==0){ pg=pc/10;out.print(pg);}
                     else {pg=(pc/10)+1;out.print(pg);}
                  %>页&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
                  <a onClick="nextpage(<%=pg%>)"><img border='0' title='下一页' src='IconPage/images/next.gif'/></a>&nbsp;&nbsp;
				  <a onClick="lastpage(<%=pg%>)"><img border='0' title='尾页' src='IconPage/images/last.gif'/></a>
				  &nbsp;&nbsp;
                 <img border='0' src='IconPage/images/line.gif'/>&nbsp;&nbsp;&nbsp;&nbsp;
				 <a onClick="jumppage(<%=pg%>)"><img border='0' style='cursor:pointer;' title='跳转' src='IconPage/images/load.png'/></a>
				 &nbsp;&nbsp;&nbsp;&nbsp;<img border='0' src='IconPage/images/line.gif'/>
&nbsp;&nbsp;&nbsp;&nbsp;<span>共有&nbsp;&nbsp;<%=request.getSession().getAttribute("pagecount")%>&nbsp;&nbsp;条记录,当前第&nbsp;&nbsp;<%=request.getSession().getAttribute("current_pagenum")%>/<%=pg%>页</span>
</div> 
        	<input type="text" style="visibility:hidden"  name="actionName" id="actionName" value="<s:property value="actionName"/>"/>
			<input type="text" style="visibility:hidden"  name="updownflag" id="updownflag" value="<s:property value="updownflag"/>"/>
			<input type="text" style="visibility:hidden"  name="updownid" id="updownid" value="<s:property value="updownid"/>"/>
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
var operation="<%=operation%>";
var result="<%=result%>";
if(operation!=""){
	if((operation=="addupdate")&&(result=="success")) alert("提交成功!");
	else if(operation=="delete"&&result=="success") alert("删除成功!");
	else if(operation=="enable"&&result=="success") alert("启用成功!");
	else if(operation=="addupdate"&&result=="fail") alert("提交失败!");
	else if(operation=="delete"&&result=="fail") alert("删除失败!");	
	else if(operation=="enable"&&result=="fail") alert("启用失败!");
	else if (operation=="reset"&&result=="success") alert("密码初始化成功!");
}

var flag=0;

//上一页
function prepage(){
		var s="systemManage/queryAction?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
    	current_page=(current_page.value)*1-1;
    	if(0==current_page)	return;
    	userManage.action=s+current_page+"&pageNum=10";
    	userManage.submit();
}
//下一页
function nextpage(maxpage){
		var s="systemManage/queryAction?current_pagenum=";
    	var current_page = document.getElementById("current_pagenum");
		if(maxpage == current_page)	return;	
		current_page=(current_page.value)*1+1;
		//alert("maxpage="+maxpage);  
		//alert("current_page="+current_page);   
		if(maxpage < current_page)	return;	
    	userManage.action=s+current_page+"&pageNum=10";
    	userManage.submit();
}
//首页
function firstpage(){
    	var s="systemManage/queryAction?current_pagenum=";
    	userManage.action=s+1+"&pageNum=10";
    	userManage.submit();
}
//尾页
function lastpage(pageno){
		var s="systemManage/queryAction?current_pagenum=";
    	userManage.action=s+pageno+"&pageNum=10";
    	userManage.submit();
}
//跳转
function jumppage(maxpage){
		var s="systemManage/queryAction?current_pagenum=";	
		var pageno=document.getElementById("current_pagenum").value;
		//alert("pageno="+pageno);
		//alert("maxpage"+maxpage);
		if(maxpage < pageno || pageno < 1)	return;	
    	userManage.action=s+pageno+"&pageNum=10";
    	userManage.submit();
}

//修改
function checkUpdate(){ 
flag=false;
var count =0;var countf=0;var state=0;
var sum=document.getElementsByName("idCheck"); 

for(i = 0; i < sum.length; i++)
{ if(sum[i].checked == true)
  { 
  flag = true;count++;countf=i;
  }
}  
  if(flag == false){ 
  alert("请选择一条记录修改!"); 
  return false;} 
  if(count > 1){ 
  alert("此功能只限于每次修改一条记录!"); return false;} 
  var pageno=document.getElementById("current_pagenum").value;
  window.location.href='systemManage/UMUpdatePreAction?userId='+sum[countf].value+'&updateFlag=update&current_pagenum='+pageno;
}
//删除
function checkDelete()
{ 
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
		{ 
			flag = true;
			break;
		}
	} 
	if(flag == false)
	{ 
		alert("请至少选择一条删除项!"); 
		return false;
	} 
	if(confirm("确定删除?"))
	{
		document.forms[0].action='systemManage/UMMultiDelAction?current_pagenum=1&pageNum=10';
		document.forms[0].submit(); 
		return true;
	}
}
//启用无效用户
function checkEnable()
{ 
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++)
	{ 
		if(sum[i].checked == true)
		{ 
			flag = true;
			break;
		}
	} 
	if(flag == false)
	{ 
		alert("请至少选择一条记录!"); 
		return false;
	} 
	if(confirm("确定启用用户?"))
	{
		document.forms[0].action='systemManage/UMMultiEnableAction?current_pagenum=1&pageNum=10';
		document.forms[0].submit(); 
		return true;
	}
}

//对字段排序 
var orderbycmd=0;
var ordersorts=""; 
if("IconPage/images/dn.png"==document.getElementById("updownflag").value){
	document.getElementById(document.getElementById("updownid").value).src="IconPage/images/dn.png";
}
function changeUpDown(sorts){
	if(orderbycmd==1) return;
	orderbycmd=1; 
	var path=document.getElementById('orders'+sorts); 
	document.getElementById("updownid").value=path.id;
	if(path.src.indexOf('up.png')!=-1) { 
		path.src="IconPage/images/dn.png";
		document.getElementById("updownflag").value="IconPage/images/dn.png";
 		ordersorts=sorts+' desc'; 
 		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10&orderbys=";
		document.forms[0].action=s+ordersorts;
		document.forms[0].submit();
    } 
	else {
		path.src="IconPage/images/up.png"; 
		document.getElementById("updownflag").value="IconPage/images/up.png";
		ordersorts=sorts+' asc'; 
		var s=document.getElementById("actionName").value+"?current_pagenum=1&pageNum=10&orderbys=";
		userManage.action=s+ordersorts;
		userManage.submit();
 	} 
}
//全选,取消
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
	} 
}
//初始化密码
function checkOperation(){
	flag=false;
	var sum=document.getElementsByName("idCheck"); 
	for(i = 0; i < sum.length; i++){ 
		if(sum[i].checked == true){ 
			flag = true;break;
		}
	}
	if(flag == false){ 
		alert("请至少选择一条要初始化密码的记录!"); 
		return false;
	} 
	if(confirm("确认要初始化密码？")){
		document.forms[0].action='systemManage/UMResetAction';
		document.forms[0].submit(); 
		return true;
	}
}
function query()
{
	userManage.action="systemManage/queryAction?current_pagenum=1&pageNum=10";
	userManage.submit();
}
function showAllInfo()
{
	userManage.action="systemManage/UserManageAction?current_pagenum=1&pageNum=10";
	userManage.submit();
}
//查询无效用户
function queryDeleted()
{
	userManage.action="systemManage/UMQueryDeletedAction?current_pagenum=1&pageNum=10";
	userManage.submit();
}

</script>

</body>
</html>
