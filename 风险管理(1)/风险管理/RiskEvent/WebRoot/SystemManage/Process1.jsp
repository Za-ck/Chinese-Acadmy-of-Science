<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>流程添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <script src="/RiskEvent/js/jquery-1.7.1.min.js"></script>
    <script src="/RiskEvent/js/colorpick/jquery.colorpicker.js"></script>
    <style>
     .add{
    cursor:pointer;
	margin-top:10px;
	margin-left:10px;
	width:90px;
	height:45px;
	color:#ffffff;
	background-color:#0066FF;
	text-align:center;
	float:left;}
	 .add span{
	width:100%;
	height:45px;
	color:#ffffff;
	line-height:45px;
	font-weight:bolder;
	font-size:12px;}
	 .delete{
	 cursor:pointer;
	margin-top:10px;
	margin-left:10px;
	width:90px;
	height:45px;
	color:#ffffff;
	background-color:#CC0000;
	text-align:center;
	float:left;}
	 .delete span{
	width:100%;
	height:45px;
	color:#ffffff;
	line-height:45px;
	font-weight:bolder;
	font-size:12px;}
    .processName{
	width:100%;
	margin-top:5px;
	margin-left:10px;
	}
	.processName span{
	font-size:14px;
	color:#818181;
	font-weight:bolder;}
	.content{
	margin-top:45px;
	width:100%;
	}
	.submitAdd{
	cursor:pointer;
	margin-top:5px;
	height:35px;
	text-align:center;}
	.submitAdd span{
	background-color:#CCCCCC;
	color:#FFFFFF;
	line-height:35px;
	width:70px;
	height:30px;
	border:1px solid #81818;}
	 .flowChart{
	width:100%;
	height:300px;
	border:1px solid #e4e4e4;
	padding:10x;}
	.direction{
	margin:10px 0px 10px 0px;
	width:120px;
	height:120px;
	float:left;
	text-align:center;
	display:block;
	line-height:120px;  
	}
	.flowNode{
	margin:10px 0px 10px 0px;
	border:2px solid #818181;
	width:120px;
	height:120px;
	float:left;
	text-align:center;
	padding:5px;
	display:block;}
	.divover{
	border:2px solid #1281d3;
	margin:10px 0px 10px 0px;
	width:120px;
	height:120px;
	float:left;
	text-align:center;
	padding:5px;
	display:block;}
	.divout{
	border:2px solid #818181;
	margin:10px 0px 10px 0px;
	width:120px;
	height:120px;
	float:left;
	text-align:center;
	padding:5px;
	display:block;
	}
	.depDesp{
	border-bottom:1px dotted #818181;
	width:120px;
	height:22px;
	line-height:20px;
	font-size:13px;
	text-align:left;}
	.depDesp1{
	border-bottom:1px dotted #818181;
	width:120px;
	height:20px;
	line-height:20px;
	font-size:13px;
	text-align:center;
	}
	.depDesp span{
	font-size:11px;}
	.textshow{
	color:#1281d3;
	margin-left:5px;
	font-family：Arial, Helvetica, sans-serif;
	font-size:12px;
	font-weight:bolder;}
	.textinfo{
	color:#818181;
	margin-left:5px;
	text-align:left;
	font-family:Arial, Helvetica, sans-serif;
	font-size:13px;
	}
	.nodecontent{
	width:260px;
	float:left;}
	.updatech{
	text-align:center;	
	background-color:#FF9900;
	color:#FFFFFF;
	line-height:20px;
	width:30px;
	height:20px;
	border:1px solid #81818;
	cursor:pointer;
	}
	.deletech{
	text-align:center;
	margin-left:5px;
	background-color:#CC3300;
	color:#FFFFFF;
	line-height:20px;
	width:30px;
	height:20px;
	border:1px solid #81818;
	cursor:pointer;
	}
    </style>
  </head>
  
  <body>
   <form id="processadd" name="processadd" method="post" action="" >
   <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险流转策略</span></div></div>
   <div class="MainDiv">
   <div class="processName">
     <label>
      <input type="hidden" id="updateFlag" name="updateFlag" value="<s:property value='updateFlag'/>"/>
     </label>
     <span>风险类型:</span>
     <label style="width:500px">

     <s:select name="riskType" theme="simple" list="riskList" listValue="riskName" listKey="riskId" class="text"  style="width:350px"></s:select>    
     </label>
   </div>
    <div class="add" onClick="addStrategy()"><span>添加策略项</span></div><div class="delete" onClick="deleteStrategy()"><span>移除策略项</span></div> <p>&nbsp;</p>
   <div class="flowChart">
  
 <div class="nodecontent">
   <div class="flowNode" style="border:2px solid #3aa4ff;">
   <div class="depDesp"><span class="textinfo">状态：已提交</span><img src="/RiskEvent/frame/icon/Blue Round.png"  width="20" height="20"/></div>
   <div class="depDesp"><span class="textinfo">提交部门:</span></div>
   <div class="depDesp"><span class="textshow">录入部门</span></div>
   </div>
   <div class="direction"><img src="/RiskEvent/frame/icon/flowArrow.png"/></div>
</div>

<div id="contentlist">
<%int num=0;%>
<s:iterator value="flowRuleLists">
<div class="nodecontent" id="listinfo<s:property value='frId' />">
<div class='flowNode' style='border:2px solid #3aa4ff;'>
<div class='depDesp'><span class='textinfo'>状态：已提交</span><img src='/RiskEvent/frame/icon/Blue Round.png'  width='20' height='20'/></div>
<div class='depDesp'>
<%
  if (2 > num++) {
%>
<span class="textinfo">提交部门：</span>
<%
}
else{
%>
<span class="textinfo">审核部门：</span>
<%
}
%>
</div><div class='depDesp'><span class='textshow' id="listinfo<s:property value='frId' />dep" ><s:property value='frDepart' /></span></div>
<div class='depDesp'><span class='textinfo'>提交对象</span><span class='textshow' id="listinfo<s:property value='frId' />role" >
<s:if test="frRole==0">员工</s:if>
<s:elseif test="frRole==1">领导</s:elseif>
</span></div>
<div class='depDesp1'><span class='updatech' onclick='updateinfo("listinfo<s:property value='frId' />")'>修改</span><span class='deletech' onclick='deleteinfo("listinfo<s:property value='frId' />")'>删除</span></div></div>
 <div class='direction'><img src='/RiskEvent/frame/icon/flowArrow.png'/>
</div>

</div>
</s:iterator>
</div>


<div class="nodecontent">
<div class="flowNode" style="border: 2px solid #9eda10;">
<div class="depDesp"><span class="textinfo">状态：审核发布</span><img src="/RiskEvent/frame/icon/Green Round.png"  width="20" height="20"/></div>
<%--<div class="depDesp"><span class="textinfo">发布部门：院长工作部</span></div>--%>
<div class="depDesp"><span class="textinfo">发布部门：发布</span></div>
<div class="depDesp"><span class="textinfo">发布对象：领导</span></div>
<div class="depDesp"><span class="textshow" style="color:#818181">审核之后自动发布</span></div>
</div>
</div>

   </div>
   
    <div id="addInfo" style="display:none">
    <table width="100%" border="1" class="contentInfo" cellpadding="0" cellspacing="0">
      <tr>
        <td width="18%" height="35px;" align="right">提交部门:</td>
        <td width="17%">
        <s:select name="rfDepart" theme="simple" list="alldepList" listValue="depName" listKey="depId" style="width:154px"></s:select>   
      </td>
        <td width="32%" align="right">提交用户类型:</td>
        <td width="33%" >
        	<s:select name="rfRole" id="rfRole" theme="simple" list="#{'0':'员工','1':'领导'}" style="width:154px"></s:select>       
       </td>
      </tr>
       <tr>
        <td height="35px" colspan="4" align="center"><div class="submitAdd" onClick="submitStrategy()"><span>确定</span></div></td>
      </tr>
    </table>
  </div>
   </div>
     <div style="text-align: center">
  <input type="button" name="submitbt" value="" class="save" onClick="submitStrategyifno()" />　
				<input type="button" name="Submit2" value="" class="back" onClick="javascript:history.back(-1)">
			</div>
 </form>
 <script>
      var num=100;
      var updateflag=0;
      var updataNum;
      
   function  addStrategy(){
    	var add=document.getElementById("addInfo").style.display="";
    }
       
  function deleteStrategy(){
       var tbody=document.getElementById("contentlist");
	   var num=tbody.childNodes.length;
	   for(var n=0;n<num;n++)
	  {    var trid=tbody.childNodes[0].id;
		   tbody.removeChild(document.getElementById(trid));   
	  }
    }
   function deleteinfo(idnum){
    	 var pa=document.getElementById(idnum);
         pa.parentNode.removeChild(pa);
    }
    function updateinfo(idnum){
      document.getElementById("addInfo").style.display="";
      var tbody=document.getElementById(idnum+'dep');
      var role=document.getElementById(idnum+'role');
	  document.getElementById("rfDepart").value=tbody.innerText;
	  if(role.innerText=="领导")
      document.getElementById("rfRole").value=1;
      else document.getElementById("rfRole").value=0;
	  updateflag=1;
	  updataNum=idnum;
    }
   function submitStrategy(){
    	if(updateflag==0){
    	var contentlist=document.getElementById("contentlist");
    	var listinfo=document.createElement('div');
    	listinfo.id='listinfo'+(num*1+1);
    	var ids='listinfo'+(num*1+1);
    	listinfo.className="nodecontent";
    	var depart=document.getElementById("rfDepart").value;
    	var role=document.getElementById("rfRole").value;
    	if(role=='0') role="员工";
    	else role="领导";
    	listinfo.innerHTML="<div class='flowNode' style='border:2px solid #3aa4ff;'><div class='depDesp'><span class='textinfo'>状态：已提交</span><img src='/RiskEvent/frame/icon/Blue Round.png'  width='20' height='20'/></div><div class='depDesp'><span class='textinfo'>提交部门:</span></div><div class='depDesp'><span class='textshow' id=\""+ids+"dep\" >"+depart+"</span></div><div class='depDesp'><span class='textinfo'>提交对象</span><span class='textshow' id=\""+ids+"role\" >"+role+"</span></div><div class='depDesp1'><span class='updatech' onclick='updateinfo(\""+ids+"\")'>修改</span><span class='deletech' onclick='deleteinfo(\""+ids+"\")'>删除</span></div></div></div>" +
    	"<div class='direction'><img src='/RiskEvent/frame/icon/flowArrow.png'/>" +
    	"</div>";
    	contentlist.appendChild(listinfo);	
    	var add=document.getElementById("addInfo").style.display="none";
    	num++;}
    	else{
        document.getElementById(updataNum+"dep").innerText=document.getElementById("rfDepart").value;
	    var role=document.getElementById("rfRole").value;   
	    if(role=='0') role="员工";
    	else role="领导";
	     document.getElementById(updataNum+"role").innerText=role;
	    updateflag=0;
	    updataNum=null;
	    var add=document.getElementById("addInfo").style.display="none";
    	}
    }
   
   function submitStrategyifno(){
       var listbody=document.getElementById("contentlist");
	   var num=listbody.childNodes.length;
	   var listinfo='';
	   for(var n=0;n<num;n++)
	  { 	
	  var trid=listbody.childNodes[n].id;
	  var deps=document.getElementById(trid+"dep").innerText+"";
	  var roles=document.getElementById(trid+"role").innerText+"";
	  listinfo+=';'+deps+',';
	  roles=roles.replace(/(^\s*)|(\s*$)/g, ""); 
	  if(roles=="领导"){ listinfo+="1";}
	  else listinfo+="0";
	  }
	 processadd.action="riskFlow/updateOrAddRisk1?listinfo="+listinfo;
	 processadd.submit();
    }
 </script>
  </body>
</html>
