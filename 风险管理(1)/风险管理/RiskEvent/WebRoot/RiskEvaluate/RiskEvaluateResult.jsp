<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@page import="com.model.Users"%>
<%@page import="com.model.InvestmentProject"%>
<%@page import="com.model.ProjectElement"%>
<%@page import="com.dao.InvestmentProjectDAO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <base href="<%=basePath%>">
    
    <title>国际投资项目评估结果</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/RiskEvent/IconPage/page.css">
	<script language="javascript" src="IconPage/page.js"></script>
	<script language="javascript" src="/RiskEvent/js/editTable.js"></script>
	<script language="javascript" src="IconPage/jquery-1.3.2.min.js"></script>
	<script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
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
<%--	var numTd = $("td.edit-td"); --%>
<%--	numTd.dblclick(function() {  --%>
<%--		var tdObj = $(this);  --%>
<%--		if (tdObj.children("input").length > 0) {  --%>
<%--			//当前td中input，不执行click处理  --%>
<%--			return false;  --%>
<%--		}  --%>
<%--		var text = tdObj.html();   --%>
<%--		//清空td中的内容  --%>
<%--		tdObj.html("");  --%>
<%--		//创建一个文本框 --%>
<%--		//将文本框插入到td中  --%>
<%--		var inputObj = $("<input type='text'>")--%>
<%--			.css("font-size","12px").width(tdObj.width()*0.4).height("20px")--%>
<%--			.css("background-color","#fff")  --%>
<%--			.css("borderRadius","4px")--%>
<%--			.css("textAlign","center")--%>
<%--			.css("border","2px solid yellow")					    --%>
<%--			.val(text).appendTo(tdObj);  --%>
<%--		//是文本框插入之后就被选中  --%>
<%--		inputObj.trigger("focus").trigger("select"); --%>
<%--		$(this).mouseleave(function() {  --%>
<%--			var inputtext = inputObj.val();--%>
<%--				//将td的内容修改成文本框中的内容  --%>
<%--				tdObj.html(inputtext);    --%>
<%--		}); --%>
<%--	})--%>
	$('tr.parent').click(function(){   // 获取所谓的父行
			$(this)
				.toggleClass("selected")   // 添加/删除高亮
				.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	}).click();
});

</script>
	
</head>
 <%
    Users us  = (Users)(request.getSession().getAttribute("loginUser"));
    String userId=us.getUserId();
    boolean havaProjectByuserId=(Boolean)request.getAttribute("havaProjectByuserId");
    InvestmentProject ipResult=(InvestmentProject)request.getAttribute("ipResult");
    List<ProjectElement> peList=(List<ProjectElement>)request.getAttribute("peList");
    Map<String,LinkedList<ProjectElement>> resultMap=(Map<String,LinkedList<ProjectElement>>)request.getAttribute("resultMap");
    request.getSession().setAttribute("investmentProject",ipResult);
    request.getSession().setAttribute("peList",peList);
    request.getSession().setAttribute("resultMap",resultMap);
    
    
 %>
 <body style="font-size:12px; margin-top:5px;">
 <form id="riskEvaluateResult" name="riskEvaluateResult" method="post" enctype="multipart/form-data">
    <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle"><span>项目评估结果</span></div></div>
    <input type="text" style="visibility:hidden;width:10px;" name="Project_id" id="Project_id" value= ${ipResult.ipId } >
    <table style="width:100%;">
    <tr>
    	<td width="120" align="left"> <a onClick="reportExport()" style="width:150px;" onMouseOver="this.style.cursor='hand'"><img  width="32" height="32" src="images/Word.png" onMouseOver="this.style.cursor='hand'"/><span>导出评估报告</span></a>
     	</td> 
     	<td width="120" align="right">
     	<%if(havaProjectByuserId){%>
     	<button id="updateProjectButton" name="updateProjectButton" onclick="updateProject()">覆盖原项目</button>
     	<%} else{%>
     	<button id="saveProjectButton" name="saveProjectButton" onclick="saveProject()">保存该项目</button>
     	<%} %>
     	</td>
    </tr>
    		
    
    <tr style="position:relative;">  
    <td>
    <!--项目信息表-->
    <span style="overflow:auto;">
    <table id='mytable' class='tablestyle' width='100%'>
    	<tr style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">		
    				<div align = "center">
    				<br/>  
                    <label style = "padding-left:50;"><font size = "3px" >表1 项目评估结果</font></label> <br/>  
                    </div>	
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">项目名称</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">项目类型</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">评估部门</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">评估人</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">评估时间</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">评估均值</td>
                		<td width="10%" align="center" style="color:#818181;height:30px;font-size:15px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">评估方差</td>
						
            		</tr>					
					<tbody id="body_RiskEventInputQuery">			
            		<tr>		
            			<td align="center">${ipResult.ipName}</td>
            			<td align="center">${ipResult.ipMark}</td>
            			<td align="center">${ipResult.ipDepname}</td>
            			<td align="center">${ipResult.ipUsername}</td>
            			<td align="center">${ipResult.ipTime}</td>           			
            			<td align="center">${ipResult.ipExpectedvalue}</td>           			
            			<td align="center">${ipResult.ipVariance}</td>

					</tr>
            		</tbody>
				
        </table>
        
        <div align = "center">
        <br/>
        <label style = "padding-left:50;"><font size = "3px" >表2 项目的风险因素和可能原因列表</font></label> <br/>     
        </div>	
<%--         <%if(canModify){ %>--%>
<%--         <div style="text-align:right">--%>
<%--         	<button type="button" class="save-change" onclick="updateProject();">保存修改</button>--%>
<%--         </div>--%>
<%--         <%} %>--%>
    </td>
    </tr>
    <tr style="position:relative;">
    <td>
     </span>
    <!--项目具体录入信息表-->   
    <span style="overflow:auto;">
    <table id="depTab" width="100%" class="tablestyle">
    <thead>
    	<tr  style="position:relative;top:expression(this.offsetParent.scrollTop);padding:0px;">
    	
			  <th width="10%">风险因素</th>
			  <th width="10%">影响程度</th>
			  <th width="10%">可能原因</th>
			  <th width="10%">发生可能性</th>
			  <th width="10%">影响程度</th>
			  <th style="display:none;">ID</th>
		    </tr> 
    </thead>
    <tbody id="peCategorynameList">
	<s:iterator value="resultMap" id = "contract">
	<s:if test = "#contract.value.size>1">
    <s:iterator value="#contract.value">
    	<tr id="peCategorynameTr">
    	    
            <td id="peCategoryname" name="peCategoryname" align="center"><s:property value="peCategoryname"/></td>
            <td align="center"><s:property value="peCateimpact"/></td> 
            <td align="center"><s:property value="peEvaluatename"/></td>
            <td align="center"><s:property value="peProdegree"/></td> 
            <td align="center"><s:property value="peImpactdegree"/></td> 
            <td style="display:none;"><s:property value="peId"/></td>    
		</tr>
	</s:iterator>
	</s:if>
    </s:iterator>
    </tbody>     
    </table>  
    
    <!--    柱状图--> 
    <div align = "center">
    <br/>
    <img id="evaluateImg" src="<s:property value="graphURL"/>" width="1000" height="500" border="0" style="vertical-align:middle;"/><br/>   
    <label style = "padding-left:50;"><font size = "3px" >图1 项目评估结果柱状图</font></label> <br/>      
    </div>
    <!--    评估建议-->
    <div align = "left"  id = "adviseMC">
   	<img id="adviseImg" src="images/advise.png" border="0" style="vertical-align:left;"/><br/>
   	</div>
  
   	<!--    评估建议表-->
   	<span style="overflow:auto;">
   	 <table id='mytable' class='tablestyle' width='100%'>   	  	 	
    				<div align = "center">
                    <label style = "padding-left:50;"><font size = "3px" >表3 项目评估投资建议表</font></label> <br/>  
                    </div>	
                    <tr>
                		<td width="40%" align="center" style="color:#818181;height:30px;font-size:16px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">综合评定值大于0.5发生的次数总和N</td>
                		<td width="60%" align="center" style="color:#818181;height:30px;font-size:16px;font-weight:lighter;text-align:center;border:1px solid #dadbdb;">项目评估投资建议</td>
						
            		</tr>					
					<tbody id="body_RiskEventInputQuery">			
            		<tr>			     	
            			<td align="center" style ="font-size:16px;">0≤N<1000</td>
            			<td align="center" style ="font-size:16px;">此投资项目的投资风险很低，可以投资。</td>
					</tr>
            		<tr>			     	
            			<td align="center" style ="font-size:16px;">1000≤N<3000</td>
            			<td align="center" style ="font-size:16px;">此投资项目的投资风险较低，可以考虑投资。</td>
					</tr>
            		<tr>			     	
            			<td align="center" style ="font-size:16px;">3000≤N<5000</td>
            			<td align="center" style ="font-size:16px;">此投资项目的投资风险较高，请慎重考虑。</td>
					</tr>
            		<tr>			     	
            			<td align="center" style ="font-size:16px;">5000≤N≤10000</td>
            			<td align="center" style ="font-size:16px;">此投资项目的投资风险很高，建议不要投资。</td>
					</tr>
            		</tbody>	
        </table>  	
   	
   	<fieldset>
   		<label style = "padding-left:10;"><font size = "3.5px" >在项目评估结果柱状图中，综合评定值区间越大则表明项目投资风险越大。</font></label> <br/>
   	 	<label style = "padding-left:10;"><font size = "3.5px" >如图1所示，在一万次模拟中综合评定值大于0.5的发生次数总和为${totalNum}次。投资建议：<font style="color:#2A00FF;">${advise}</font></font></label> <br/> 
   	</fieldset>  	   	
   	<!--    评估说明-->
   	<div align = "left"  id = "remarkMC" onClick="showdiv('monteCarloImg')">
   	<img id="remarkImg" src="images/remarkMC.png" border="0" style="vertical-align:left;" onMouseOver="this.style.cursor='hand'"/><br/>
   	</div>
    <div align = "center" id ="monteCarloImg" style = "display:none;">
    <img id="monteCarloImg" src="images/monteCarlo.png" border="0" style="vertical-align:middle;"/><br/>   
    <label style = "padding-left:50;"><font size = "3px" >图2 风险评估过程说明图</font></label> <br/> 
    </div>  
       
    </span>
    </td>
    </tr>
    </table> 
</form> 
</body>
<script>
senfe('depTab','#f7f7f7','#fff','#d9ebf5','#e9f2f7');

openBox('评估提示',350,150,0,'正在评估中，请等待....','');
//得到table对象
    var mytable=document.getElementById("depTab");
    //向table中插入一行
   var index = mytable.rows.length; 
   var num=1;
   for(var i=1;i<index;i++)
   {
    var text1 = mytable.rows[i].cells[0].innerText.replace(" ","");//获取行的第一个单元格firstChild中的内容
     mytable.rows[i].className="child_row_"+num;
      var j=i+1;
     if(j<index)//如果下一行不是最后一行
    { 
    var text2 = mytable.rows[j].cells[0].innerText.replace(" ","");
     if(!(text1==text2))
   {  
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    num++;
    }
    }
    }
    if(j==index)
    {
    mytable.rows[j-1].className="parent";
    mytable.rows[j-1].id="row_"+num;
    }
    
    var maxCol = 3, val, count, start;  //maxCol：合并单元格作用到多少列    
    for(var col = maxCol-1; col >= 0 ; col--){
        count = 1;
        val = "";
        for(var i=0; i<mytable.rows.length; i++){
            if(val == mytable.rows[i].cells[col].innerHTML){
                count++;
            }else{
                if(count > 1){ //合并
                    start = i - count;
                    mytable.rows[start].cells[col].rowSpan = count;
                    for(var j=start+1; j<i; j++){

                        mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
                    }
                    count = 1;
                }
                val = mytable.rows[i].cells[col].innerHTML;
            }
        }
        if(count > 1 ){ //合并，最后几行相同的情况下
            start = i - count;
            mytable.rows[start].cells[col].rowSpan = count;
            for(var j=start+1; j<i; j++){
               // mytable.rows[j].cells[col].style.display = "none";
                mytable.rows[j].cells[col].parentNode.removeChild( mytable.rows[j].cells[col]);
            }
        }
    }
closeLoginBox();
    
function reportExport(){
	riskEvaluateResult.action="riskEvaluate/tempEvaluateReportAction";
	riskEvaluateResult.submit();
}   

function showdiv(targetid){  
      var target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";

            } else {
                target.style.display="block";
            }
   
}

function updateProject1() {
     
      var $peCategorynameList = $("#peCategorynameList").children("tr");
      var projectId=document.getElementById("Project_id").value;
<%--      alert(projectId);--%>
<%--      alert($peCategorynameList.length);--%>
      var peTableList="";
      var i=0;
	   for(i=0;i<$peCategorynameList.length;i++){
		   var $tds=$peCategorynameList.eq(i).children("td");
		   var temp="";
		   if($tds.eq(2).text()!="小计"){
			   if($tds.length<5){
				   var $tempTds=$peCategorynameList.eq(i-1).children("td");
				   temp+=$tempTds.eq(0).text()+";"+$tempTds.eq(1).text()+";";
<%--				   alert(temp);--%>
			   }
		   for(var j=0;j<$tds.length;j++){
			   
			   temp+=$tds.eq(j).text();
			   if(j<$tds.length-1){
				   temp+=";"
			   }
		   }
<%--		   alert(temp);--%>
		   peTableList+=temp+"@";
		  
		   }
		 
		   
	   }
	   peTableList=peTableList.substring(0,peTableList.length-1);
<%--	   alert(peTableList);--%>
	   riskEvaluateResult.action="riskEvaluate/updateEvaluateReportAction?peTableList="+peTableList+"&&project_id="+projectId;
	   riskEvaluateResult.submit();
}
   
   function saveProject(){
	   $.ajax({
               url: 'default/ajaxSaveProjectAction?tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {
            	         $("#saveProjectButton").attr("disabled","disabled");
            	         $("#saveProjectButton").attr("value","已保存");
                    	alert("保存项目成功");
               
               },
               error: function () {
               alert("error");
           }
       });
   }
   
     function updateProject(){
	   $.ajax({
               url: 'default/ajaxUpdateProjectAction?tmpnum='+ Math.random().toString(),
               type: 'post',
               dateType: 'json',
               data: '',
               contentType: 'text/html;charset=utf-8',
               success: function (data) {
            	         $("#updateProjectButton").attr("disabled","disabled");
            	         $("#updateProjectButton").attr("value","已覆盖");
                    	alert("覆盖项目成功");
               
               },
               error: function () {
               alert("error");
           }
       });
   }
</script>

</html>
