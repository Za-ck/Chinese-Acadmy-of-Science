<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">

    <title>新增风险事件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
     <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" charset="utf-8"></script>
     <script language=javascript src="/RiskEvent/js/windows.js"></script>
     <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
</head>
  
 <body>
<form id="riskEventInput" name="riskEventInput" action="riskInput/RiskAddUpdateAction" method="post" enctype="multipart/form-data"  onSubmit="changetype();return doValidate('riskEventInput')">
<div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>风险事件录入</span></div></div>
<div class="MainDiv">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent"  >
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%"  >
		<tr>
			<td width="100%">			
				<fieldset style="height:100%;">
				<legend>新增风险事件</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
					 <table border="0" class="contentInfo" cellpadding="2" cellspacing="1" style="width:100%"  >
					 <tr>
					    <td nowrap align="right" width="13%">风险类别：</td>
			          <td width="42%"><span class="red">
			    		  
					  <s:select id="risktype" name="risktype" theme="simple" list="allrtList" listValue="rtName" listKey="rtId" class="text" style="width:80px"  onchange="getRiskData()"></s:select>                                           
                        *</span>
						<span class="red">
					    <select id="riskname" name="riskname" style="width:360px" onChange="getEvent()">					    
                        	<option value="<s:property value='selecrtId'/>"><s:property value="selecrtName" /></option>
            			</select>
            			*</span>
						</td>
					    <td align="right" width="13%">事件名称：</td>
					    <td width="32%"><span class="red">
					    <input id="eventname" name="eventname" type="text" class="text" style="width:154px" value="<s:property value='eventname'/>"/>*</span>
					  </td>
					  </tr>	
                      <tr>
					    <td nowrap align="right" width="13%">事件编号：</td>
					    <td width="42%">
					       <input id="eventid" name="eventid" type="text" class="text" style="width:154px" onFocus="this.blur()" value="<s:property value='eventid'/>"/>（自动生成）<span class="red">*</span></td>
					    <td align="right" width="13%">录入部门：</td>
				    <td width="32%"><span class="red">
					       <input id="indep" name="indep" type="text" class="text" style="width:154px" onFocus="this.blur()" value="<s:property value='indep'/>"/>*</span>	</td>
					  </tr>		                  				    
					  <tr>
					    <td width="13%" nowrap align="right">风险事件描述：</td>
					    <td colspan="3">
							<textarea title="按照”动因+影响“的方式进行描述，包括事件发生的时间" id="riskremark" name="riskremark" cols="100" rows="10"><s:property value="riskremark"/></textarea><span class="red">*</span>
						</td>
					  </tr>
					  </table>
				</fieldset>				
			</td>
		</tr>
		</table>	 
	</td>
  </tr>
		
		<tr  >
			<td colspan="2" align="center" height="50px">
				<!-- <input type="button" id="save" name="save" class="button"  value="保存" /> -->
				<input type="submit" name="nextpage" class="button"   value="保存并下一页" >  
			</td>
		</tr>　  
		<tr ><td><input type="text" style="visibility:hidden" name="updateFlag" id="updateFlag" value="<s:property value='updateFlag'/>"/></td></tr>
		<tr ><td><input type="text" style="visibility:hidden" name="riskeventid" id="riskeventid" value="<s:property value='riskeventid'/>"/></td></tr>
</table>
</div>
</form>
<script type="text/javascript">
$("#save").click(function(){
	changetype();
	var risktype = $('#risktype option:selected').val();
	var riskname = $('#riskname option:selected').val();
	var eventname = $('#eventname').val();
	var eventid = $('#eventid').val();
	var riskremark= $('#riskremark').val();
	var updateFlag = $('#updateFlag').val();
	var riskeventid = $('#riskeventid').val();
	
	if(doValidate('riskEventInput')){
		 $.ajax({
               url: 'riskInput/RiskAddUpdateAction?risktype='+risktype+'&riskname='+riskname+'&eventname='+eventname+'&eventid='+eventid+'&riskremark='+riskremark+'&updateFlag='+updateFlag+'&riskeventid='+riskeventid,
               type: 'post',
               dateType: 'json',
               data:'', /* {'risktype':risktype,'riskname':riskname,'eventname':eventname,'eventid':eventid,'riskremark':riskremark,'updateFlag':'wlswls','riskeventid':riskeventid}, */
               contentType: 'text/html;charset=utf-8',
               success: function (data) {  
               	alert('保存成功');   
				document.getElementById("eventid").disabled='true';
  				document.getElementById("risktype").disabled='true';
 				document.getElementById("riskname").disabled='true';
  				document.getElementById("indep").disabled='true';
		 		}
		 });
	} 
});

if(document.getElementById("updateFlag").value=="update")
{
  document.getElementById("eventid").disabled='true';
  document.getElementById("risktype").disabled='true';
  document.getElementById("riskname").disabled='true';
  document.getElementById("indep").disabled='true';
}

function getRiskData(){
           var tempname=document.getElementById("risktype").value;
           
           $.ajax({
               url: 'default/ajaxRiskAction?tmprisk=' + tempname+'&tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {     
              var data1 = eval("("+ data+")");
              var ObjTarget=document.getElementById("riskname");
              ObjTarget.innerHTML="";
              var opt2=document.createElement("option");   
                   opt2.value="none1";   
                     opt2.text="--请选择--";  
                        ObjTarget.add(opt2); 
               for(var i=0;i<data1.length;i++)
               {   
                 var opt=document.createElement("option");   
                   opt.value=data1[i].riskId;   
                     opt.text=data1[i].riskName;  
                        ObjTarget.add(opt); 
                }
               },
               error: function () {
               alert("error");
           }
       });
       }
function getEvent()
{
var tempname=document.getElementById("riskname").value;
          $.ajax({
               url: 'default/ajaxEventAction?tempriskid='  + tempname+'&tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {     
              var data1 = eval("("+ data+")");
              var ObjTarget=document.getElementById("eventid");
              ObjTarget.innerText=data1;
               },
               error: function () {
               alert("error");
           }
       });
}      
       function changetype()
       {
        document.getElementById("eventid").disabled=0;
        document.getElementById("risktype").disabled=0;
        document.getElementById("riskname").disabled=0;
		if("none1"==document.getElementById("risktype").value){
			document.getElementById("risktype").value="";
		}
		if("none1"==document.getElementById("riskname").value){
			document.getElementById("riskname").value="";
		}
       }
       
   </script>
</body>
</html>
