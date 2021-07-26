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
    
    <title>策略新增与修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    
<script language=javascript src="/RiskEvent/js/windows.js"></script>
<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
    <script src="/RiskEvent/js/jquery-1.7.1.min.js"></script>
    <script src="/RiskEvent/js/colorpick/jquery.colorpicker.js"></script>
    <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
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
	.strategName{
	width:100%;
	margin-top:5px;
	margin-left:10px;
	}
	.strategName span{
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
	
	.listinfo{
	padding:5px;
	border:1px solid #81818;
	width:650px;
	font-size:14px;
	margin-left:10px;
	height:35px;
	}
	.listinfo:HOVER{
	border:2px solid #ff6600;}
	.listinfo span{
	margin-left:5px;
	line-height:35px;}
	.updatech{
	text-align:center;
	margin-right:5px;
	margin-left:35px;
	background-color:#FF9900;
	color:#FFFFFF;
	line-height:35px;
	width:50px;
	height:30px;
	border:1px solid #81818;
	cursor:pointer;
	}
	.deletech{
	margin-right:0px;
	text-align:center;
	margin-left:5px;
	background-color:#CC3300;
	color:#FFFFFF;
	line-height:35px;
	width:50px;
	height:30px;
	border:1px solid #81818;
	cursor:pointer;
	}
	.desp{
	font-size: 14px;
	font-weight: bolder;
	color: #818181;}
	.values{
	width:35px;
	font-size: 14px;
	font-weight: bold;
	color: #1281d3;}
    </style>
  </head>
  
  <body>
  <form id="strategyadd" name="strategyadd" method="post" action="RiskStrategySet/updateOrAddSetinfo" onSubmit="return doValidate('strategyadd')">
   <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>风险策略信息</span></div></div>
   <div class="strategName"><span>风险策略名称:</span>
     <label>
     <span class="red">
     <input type="text" name="strategyName" id="strategyName" value="<s:property value='strategyName'/>">
      *</span>
     <input type="hidden" id="strategyId" name="strategyId" value="<s:property value='strategyId'/>"/>
     <input type="hidden" id="updateFlag" name="updateFlag" value="<s:property value='updateFlag'/>"/>
    
     </label>
     <span>责任部门名称:</span><s:select name="riskDeps" theme="simple" list="alldepList" listValue="depName" listKey="depId" class="text" style="width:154px" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
     <span class="red">*</span>
   </div>
   <div style="width: 100%;height:400px;">
  <div class="add" onClick="addStrategy()"><span>添加策略项</span></div><div class="delete" onClick="deleteStrategy()"><span>移除策略项</span></div> <p>&nbsp;</p>
  <div id="contentlist" class="content"> 
 
  <s:iterator value="riskStrategyList">
  <div id="listinfo<s:property value='riskStrId'/>" class="listinfo">
  <span class='desp'>策略状态:</span><span class='values'><s:property value='stragStatue'/></span> 
  <span class='desp'>策略最小值:</span>
  <span class='values'><s:property value='stragValue'/></span> 
  <span class='desp'>策略最大值:</span>
  <span class='values'><s:property value='remark'/></span> 
  <span class='desp'>颜色:</span> 
  <span style="border: 1px solid #818181;background-color:<s:property value='stragColor'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
  <span class='updatech' onclick='updateinfo("listinfo<s:property value='riskStrId'/>")'>修改</span> 
  <span class='deletech' onclick='deleteinfo("listinfo<s:property value='riskStrId'/>")'>删除</span>
  </div>
  </s:iterator>
 
   </div>
  <div id="addInfo" style="display:none">
    <table width="100%" border="1" class="contentInfo"  
    cellpadding="0" cellspacing="0">
      <tr>
        <td width="18%" height="35px;" align="right">策略最小值:</td>
        <td width="17%"><input  name="stragValue" id="stragValue" type="text" class="text" style="width:150px;height:30px;margin-left:5px;" /></td>
        <td width="32%" align="right">策略最 大值:</td>
        <td width="33%" ><input  name="maxvalue" id="maxvalue" type="text" class="text" style="width:150px;height:30px;margin-left:5px;" /></td>
      </tr>
      <tr>
        <td align="right" height="35px">策略预警状态:</td>
        <td><input  name="statue" id=statue" type="text" class="text" style="width:150px;height:30px;margin-left:5px;" /></td>
        <td align="right">策略预警颜色:</td>
        <td>
        <span id="colorshow" style="border: 1px solid #818181;height:20px;line-height:20px;cursor:pointer;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <input type="hidden" id="colorpickvalue" value="#ffffff"/>
        </td>
      </tr>
       <tr>
        <td height="35px" colspan="4" align="center"><div class="submitAdd" onClick="submitStrategy()"><span>确定</span></div></td>
      </tr>
    </table>
  </div>
  </div>
  <div style="text-align: center">
  <input type="button" name="submitbt" value="" class="save" onclick="submitStrategyifno()" />　
				<input type="button" name="Submit2" value="" class="back" onClick="window.location.href='RiskStrategySet/riskStrategyAction'">
			</div>
</form>
    <script type="text/javascript">
      var num=100;
      var updateflag=0;
      var updataNum;
      $("#colorshow").colorpicker({
      fillcolor:true,
      success:function(o,color){
      $("#colorshow").css("background-color",color);
      $("#colorpickvalue").val(color);
            }
      });
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
    function submitStrategy(){
    
    	var statue=document.getElementById("statue").value;
    	var min=document.getElementById("stragValue").value;
    	var max=document.getElementById("maxvalue").value;
    	var colors=document.getElementById("colorpickvalue").value;
    	if(!statue||!min||!max)
    	{
   	alert("不允许为空值");
   	return false;
   	}
   	
    	if(updateflag==0){
    	var contentlist=document.getElementById("contentlist");
    	var listinfo=document.createElement('div');
    	listinfo.id='listinfo'+(num*1+1);
    	var ids='listinfo'+(num*1+1);
    	listinfo.className="listinfo";
    	
    //	if((statue==null)||(min==null)||(max==null)))
    //	{
    //	alert("sssss");
    //	return false;
    //	}
    	//alert(statue+","+min+","+max+","+colors+"wswwwwwwwwwwww");
    	listinfo.innerHTML="<span class='desp'>策略状态:</span><span class='values'>"+statue+"</span> <span class='desp'>策略最小值:</span> <span class='values'>"+min+"</span> <span class='desp'>策略最大值:</span> <span class='values'>"+max+"</span> <span class='desp'>颜色:</span> <span style='border: 1px solid #818181;background-color:"+colors+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span class='updatech' onclick='updateinfo(\""+ids+"\")'>修改</span> <span class='deletech' onclick='deleteinfo(\""+ids+"\")'>删除</span>";
    	contentlist.appendChild(listinfo);	
    	var add=document.getElementById("addInfo").style.display="none";
    	num++;}
    	else{
        var tbody=document.getElementById(updataNum);
	    var childrens=tbody.children;
	    childrens[1].innerText= document.getElementById("statue").value;
        childrens[3].innerText=document.getElementById("stragValue").value;
        childrens[5].innerText=document.getElementById("maxvalue").value;	  
	    childrens[7].style.backgroundColor=document.getElementById("colorpickvalue").value;
	   
	    updateflag=0;
	    updataNum=null;
	    var add=document.getElementById("addInfo").style.display="none";
    	}
    	
    	
    }
    function updateinfo(idnum){
      document.getElementById("addInfo").style.display="";
      var tbody=document.getElementById(idnum);
	  var childrens=tbody.children;
	  document.getElementById("statue").value=childrens[1].innerText;
      document.getElementById("stragValue").value=childrens[3].innerText;
      document.getElementById("maxvalue").value=childrens[5].innerText;
	  document.getElementById("colorshow").style.backgroundColor=childrens[7].style.backgroundColor;
	  document.getElementById("colorpickvalue").value=childrens[7].style.backgroundColor;
	  updateflag=1;
	  updataNum=idnum;
    }
    function submitStrategyifno(){
    if(doValidate('strategyadd'))
    {
       var listbody=document.getElementById("contentlist");
	   var num=listbody.childNodes.length;
	   var listinfo='';
	   for(var n=0;n<num;n++)
	  { 	
	  var trid=listbody.childNodes[n].id;
	  var tbodys=document.getElementById(trid);
	  var childrens=tbodys.children;
	  var ren1=encodeURI(encodeURI(childrens[1].innerText));
	   var ren2=encodeURI(encodeURI(childrens[3].innerText));
	    var ren3=encodeURI(encodeURI(childrens[5].innerText));
	     var ren4=encodeURI(encodeURI(childrens[7].style.backgroundColor.substring(1)));
	     
	  listinfo+=';'+ren1+',';
      listinfo+=ren2+',';
      listinfo+=ren3+',';
	  listinfo+=ren4;
	  
	 
	 
	  }

	  var select=document.getElementById("riskDeps");
	  strategyadd.action="RiskStrategySet/updateOrAddSetinfo?listinfo="+listinfo;
	   strategyadd.submit();
	} 
    }
    
   </script>
  </body>
</html>
