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
    
    <title>风险部门分析</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equi	v="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="javascript" src="IconPage/page.js"></script>
    <link rel="stylesheet" type="text/css" href="/RiskEvent/css/webstyle.css">
<script src="/RiskEvent/js/jquery-1.7.1.js"></script>
<script src="/RiskEvent/js/DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script> 
<script language=javascript src="/RiskEvent/js/windows.js"></script>

<style>
.Freezing_tdd 
{ 
top:expression(document.getElementById('div-1').scrollTop-1); 
    position:relative ; 
   z-index:100 
} 
.parent	{height:50px; background:#FFF38F;cursor:pointer;color:red}  /* 偶数行样式*/
.odd{ background:#FFFFEE;}  /* 奇数行样式*/
.selected{
background:#FFF38F;
height:50px;
border-bottom:1px solid #000000;
color:#000000;
}

.tablestyle{
    overflow:auto;
	font-size:12px;
    border-collapse:collapse;
	border:2px solid #dddddd;
}
.tablestyle td {border:1px solid #e2e1e1;text-align:center; height:25px;}
.tablestyle thead th {
	color:#818181;
	height:30px;
	border:1px solid #dadbdb;
	font-size:15px;
    font-weight:lighter;
	text-align:center;
	background-image: url("/RiskEvent/IconPage/images/thbg2.png");
    }

</style>

<script type="text/javascript">

$(function(){
	$('tr.parent').click(function(){   // 获取所谓的父行
			$(this)
				.toggleClass("selected")   // 添加/删除高亮
				.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	}).click();
})

</script>
</head>

<body onload="getRiskData();">
<form id="KeyRiskDepAnalysis" name="KeyRiskDepAnalysis" action="riskAnalysis/KeyAnalysisRiskDepAction" method="post">
<tr>
        <td style="width:60">查询时间：
        </td>
        <td style="width:60" align="right"><s:select name="choosedId" theme="simple" list="#{'0':'--请选择--','1':'录入时间','2':'发布时间'}" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()"></s:select>
		</td>
        <td style="width:60">	
        <input name="dateFrom" id="dateFrom" class="Wdate" style="width:90px" value="<s:property value='dateFrom'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>
        <td style="width:10">至：
        </td>  
        <td>
        <input name="dateTo" id="dateTo" class="Wdate" style="width:90px" value="<s:property value='dateTo'/>" onKeyPress="EnterPress(event)" onKeyDown="EnterPress()" onFocus="WdatePicker({isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>
        </td>  
        <td nowrap width="6%">风险类别：</td>
			 <td width="30%"><span class="red">
			    <s:select name="risktype" theme="simple" list="allrtList" listValue="rtName" listKey="rtId" class="text" style="width:80px"  onchange="getRiskData()">
			    </s:select>                                           
                *</span>
			    <span class="red">
			     <select name="riskname"  style="width:280px">					    
                     <option value="<s:property value='riskname'/>"><s:property value="riskname" /></option>
            	 </select>
            	*</span>
	    </td>
	    <input type="hidden" name="riskname2" value="<s:property value="riskname"/>">
        <td style="width:30">
        <input type="button" name="buttonQuery" id="buttonQuery" value="查询" onClick="queryByValue();" />
        </td>
        <table class="tablesquery"  align="right">
	   <td align="center">
        <a href="riskAnalysis/KeyAnaRiskDepBarExAction"  style="width:120px;"><img  width="32" height="32" src="images/Word.png"/><span>导出统计图</span></a>	
       </td>  
       </table>
	</tr>
	<tr><table></table></tr>
	<table align="center">
     <tr style="position:relative;">
     	<td align="center">
     			<img id="riskDepDiskImg" alt="" src="<s:property value="riskDepDiskPath"/>" />
     	</td>
	</tr>
	<tr>     		
     		<td align="center"><font style="font-family: 宋体;font-weight: bold;font-size: 20px;">二级风险部门综合评定比例饼状图</font></td>
    </tr>
</table>

	</form>
</body>
<script type="text/javascript">


     


function queryByValue(){
    openBox('统计提示',350,150,0,'正在统计中，请等待....','');
	KeyRiskDepAnalysis.action="riskAnalysis/KeyAnalysisRiskDepByValueAction";
	KeyRiskDepAnalysis.submit();
}

function getRiskData(){
           var tempname=document.getElementById("risktype").value;
           var riskname2 = document.getElementById("riskname2").value;
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
                   opt2.value="请选择";   
                     opt2.text="请选择";  
                        ObjTarget.add(opt2); 
               for(var i=0;i<data1.length;i++)
               {   
                 var opt=document.createElement("option");   
                   opt.value=data1[i].riskName;   
                     opt.text=data1[i].riskName;  
                        ObjTarget.add(opt); 
                         if(opt.text == riskname2) {
                	  opt.selected = true;
                  }
                }
               },
               error: function () {
               alert("error");
           }
       });
       }
    </script>
    
</html>
