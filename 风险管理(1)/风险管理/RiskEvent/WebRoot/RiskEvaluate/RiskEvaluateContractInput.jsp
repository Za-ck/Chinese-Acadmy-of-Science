<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.action.riskEvaluate.*,com.dao.*,com.model.*" %>
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

    <title>风险因素识别</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
    <link href="/RiskEvent/css/webstyle.css" rel="stylesheet" type="text/css" />
    <link href="/RiskEvent/css/scroll.css" rel="stylesheet" type="text/css" />
     <script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript" ></script>
     <script language=javascript src="/RiskEvent/js/windows.js"></script>
     <script language="javascript" src="/RiskEvent/js/validation-framework.js"></script>
     <script src="/RiskEvent/js/windows.js" type="text/javascript"></script>
     
     <%--  折叠面板控件--%>
     <link type = "text/css"  rel="stylesheet" href = "/RiskEvent/js/riskEvaluate/jquery.ui.all.css"/>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/jquery.ui.core.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/jquery.ui.widget.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/jquery.ui.accordion.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/jquery.ui.button.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/jquery.ui.mouse.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/mathcontext.js"></script>
     <script language="javascript" src="/RiskEvent/js/riskEvaluate/bigdecimal.js"></script>
     <link type = "text/css"  rel="stylesheet" href = "/RiskEvent/js/riskEvaluate/demos.css"/>
     <link type = "text/css"  rel="stylesheet" href = "/RiskEvent/js/riskEvaluate/jquery.ui.accordion.css"/>
    
     <script type = "text/javascript">
		 $(function() {
				$("#accordion").accordion({
					collapsible: true
				});
				
				$("#accordion").click(function(){
					$("#updown").css("top",window.screen.availHeight/2+100+"px");
				});
				
				$("#updown").css("top",window.screen.availHeight/2+100+"px");

				$(window).scroll(function() {		
				
					if($(window).scrollTop() >= 100){
				
						$('#updown').fadeIn(300); 
				
					}else{    
				
						$('#updown').fadeOut(300);    
				
					}  
				
				});
				
				$('#updown .up').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
				
				$('#updown .down').click(function(){$('html,body').animate({scrollTop: document.body.clientHeight+'px'}, 800);});
		});				 
     </script>
     <style type="text/css">
		a:link,a:visited{
		 text-decoration:none;  /*超链接无下划线*/
		}
	</style>
</head>
  
 <body>
 <form id="riskEvaluateContractInput" name="riskEvaluateContractInput" action="riskEvaluate/RiskEvaluateContractResultAction" method="post" enctype="multipart/form-data" >
    <div class="toptitle"><div class="toptitleleft"></div><div class="topttileright"></div><div class="toptitlemiddle" align="left"><span>风险因素识别</span></div></div>
    <div class="MainDiv">
    
					       
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="CContent"  id = "tableContent">
    <tr>
    	<td class="CPanel">
    		<table border="0" cellpadding="0" cellspacing="0" style="width:100%"  >
		    <tr>
				<td width="100%">	
				<fieldset style="height:100%;">
				<legend>总包项目信息</legend>
				 <div id="errorDiv" style="color:red;font-weight:bold;font-size:12px"></div>
				        <div class="demo">		
				        <fieldset>
					 	 	  <label style = "padding-left:20;width:800px;"><font size = "3px" color="#717171">项目名称:</font>
                          <input type="text" id="projectName" name="projectName" style="width:180px;font-size:14px; "/><font size = "3px" color="#717171"><span class="red">*</span></font>
                          <button onclick="addProject();">添加项目</button>
                          &nbsp;&nbsp;&nbsp;&nbsp;
                          <button onclick="selectProject();">从项目库选择</button>
                          </label>
					      <br/>
					 	</fieldset>			 	
					 	<div id = "accordion">
					 	
					 	 <%
					 	 List<String> countList = (List<String>)request.getAttribute("elementCount"); 					  
					 	 %>
					 	 
					      <s:iterator value="contractMap" id = "contract">				      			      
					      <h3><a href="#"><font id ="a_<s:property value="key[1]"/>" size = "4px"><s:property value="key[0]"/></font></a></h3>
					      <div  id = "riskElement_<s:property value="key[1]"/>">
                          <!--添加小类的可能原因-->
					      <fieldset>
					      <input type = "button"  id = "button_<s:property value="key[1]"/>"  name = "<s:property value="key[0]"/>" value="添加<s:property value="key[0]"/>的可能原因" 
					      onclick = "addElement(event)" />
					      </fieldset>
                          <!--填写大类的影响程度-->
					      <fieldset>
					      <label style = "padding-right:10;width:10px;">
					      <input type= "checkbox" id = "checkbox_<s:property value="key[1]"/>" name = "checkbox_<s:property value="key[1]"/>" onclick="checkbox()"/>
					      </label> 
					      <label style = "padding-right:20;width:500px;"><font size = "3px" color="#717171" title = "每类风险的影响程度为0到1的小数，所有大类的影响程度和为1!"><s:property value="key[0]"/>的影响程度:</font>
					      <input type="text" id="text_<s:property value="key[1]"/>" name="text_<s:property value="key[1]"/>" style="width:180px;font-size:14px;" disabled="disabled" title = "每类风险的影响程度为0到1的小数，所有大类的影响程度和为1!" onblur = "changeText('riskElement_<s:property value="key[1]"/>')"  onfocus = "focusText('text_<s:property value="key[1]"/>')"/>
					      </label>
					      </fieldset>
                          <!--小类的可能原因和影响程度-->
					      <s:iterator value="#contract.value">
					      <fieldset>
					      <label style = "padding-right:10;width:10px;">
					      <input type= "checkbox" id = "checkbox_<s:property value="reiMark"/>" name = "checkbox_<s:property value="reiMark"/>" onclick="checkbox()" disabled = "disabled" title = "请先选择并填写<s:property value="key[0]"/>的影响程度!"/>
					      </label> 
					      <label style = "padding-right:20;width:300px;">
					      <font size = "3px" color="#717171"><s:property value="reiName"/></font>
					      </label> 
					      <label style = "padding-right:20;width:200px;"><font size = "3px" color="#717171">可能性:</font>       
					      <select style="width:119px;font-size:15px;" id = "select_<s:property value="reiMark"/>" name = "select_<s:property value="reiMark"/>" disabled="disabled">
					          <option value = "0">--请选择--</option>  
							  <option value = "1">1(很小)</option>  
							  <option value = "2">2(较小)</option>  
							  <option value = "3">3(一般)</option>  
							  <option value = "4">4(较大)</option>  
							  <option value = "5">5(很大)</option>  
						  </select> 
						  </label>
                          <label style = "padding-right:20;width:500px;"><font size = "3px" color="#717171" title = "每类风险可能原因的影响程度为0到1的小数，和为1!">影响程度:</font>
                          <input type="text" id="text_<s:property value="reiMark"/>" name="text_<s:property value="reiMark"/>" style="width:180px;font-size:14px;" disabled="disabled" title = "每类风险可能原因的影响程度为0到1的小数，和为1!" onblur = "changeSubText(this)"  onfocus = "focusTextSub(this)"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="Id_<s:property value="reiMark"/>" id="Id_<s:property value="reiMark"/>" value="<s:property value="reiId"/>"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="category_<s:property value="reiMark"/>" id="category_<s:property value="reiMark"/>" value="<s:property value="reiCategory"/>"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="Idname_<s:property value="reiMark"/>" id="Idname_<s:property value="reiMark"/>" value="<s:property value="reiName"/>"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="categoryname_<s:property value="reiMark"/>" id="categoryname_<s:property value="reiMark"/>" value="<s:property value="reiCatename"/>"/>
                          </label>
					      <br/>
					      </fieldset>
					      </s:iterator> 
					      </div> 
					      </s:iterator>
					      
					      
					       <!--被添加的大类和第一个小类，先隐藏起来-->
					       <h3 id = "h3_209" style = "display:none"><a href="#"><font id ="a_209" size = "4px">大类209</font></a></h3>
					       <div id ="riskElement_209" style = "display:none">
					       <fieldset>
					       <!--添加大类里的小类-->
					        <span style="float:left">
					        <input type = "button"  id = "button_209" name = "大类209" value="添加209的可能原因" 
					        onclick = "addElement(event)" />
					        </span>
					       <!--删除添加的大类-->
<%--					        <span style="float:right">--%>
<%--					        <input type = "button"  id = "button_delete" value="删除209的风险类型"   --%>
<%--					        onclick = "hideDiv()" />					      --%>
<%--					        </span>--%>
					        
					       </fieldset>
					       <fieldset>
					       <label style = "padding-right:10;width:10px;">
					       <input type= "checkbox" id = "checkbox_209" name = "checkbox_209" onclick="checkbox()"/>
					       </label> 
					       <label style = "padding-right:20;width:500px;" ><font id = "label_209" size = "3px" color="#717171" title = "每类风险的影响程度为0到1的小数，所有大类的影响程度和为1!">大类的影响程度:</font>
					       <input type="text" id="text_209" name="text_209" style="width:180px;font-size:14px;" disabled="disabled" title = "每类风险的影响程度为0到1的小数，所有大类的影响程度和为1!"  onblur = "changeText('riskElement_209')"  onfocus = "focusText('text_209')"/>
					       </label>
					       </fieldset>
					       <fieldset>
					      <label style = "padding-right:10;width:10px;">
					      <input type= "checkbox" id = "checkbox_209000" name = "checkbox_209000" disabled = "disabled" title = "请先选择并填写大类的影响程度!" onclick="checkbox()"/>
					      </label> 
					      <label style = "padding-right:20;width:300px;">
					      <font size = "3px" color="#717171" id = "sub_209000">小类209000</font>
					      </label> 
					      <label style = "padding-right:20;width:200px;"><font size = "3px" color="#717171">可能性:</font>       
					      <select style="width:119px;font-size:15px;" id = "select_209000" name = "select_209000" disabled="disabled">
					          <option value = "0">--请选择--</option>  
							  <option value = "1">1(很小)</option>  
							  <option value = "2">2(较小)</option>  
							  <option value = "3">3(一般)</option>  
							  <option value = "4">4(较大)</option>  
							  <option value = "5">5(很大)</option>  
						  </select> 
						  </label>
                          <label style = "padding-right:20;width:500px;"><font size = "3px" color="#717171" title = "每类风险可能原因的影响程度为0到1的小数，和为1!">影响程度:</font>
                          <input type="text" id="text_209000" name="text_209000" style="width:180px;font-size:14px;" disabled="disabled" title = "每类风险可能原因的影响程度为0到1的小数，和为1!" onblur = "changeSubText(this)"  onfocus = "focusTextSub(this)"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="Id_209000" id="Id_209000" value="209000"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="category_209000" id="category_209000" value="209"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="categoryname_209000" id="categoryname_209000" value="209大类"/>
                          <input type="text" style="visibility:hidden;width:10px;" name="Idname_209000" id="Idname_209000" value="209000小类"/>
                          </label>
					      <br/>
					      </fieldset>		      	
					      </div>
					      
					      
					    </div><%-- End accordion --%>
					    <%--i用来判断当前一级风险的个数 --%>
					    <%int i = 209; %>
					    <input type = "hidden" id  = "hidden" value =<%=i%>>
					    <fieldset>
					       <label style = "padding-left:20;"><font size = "3px" color="#717171">待添加的风险因素名称</font>
					       <input type = "text" id = "text_add" />
					       </label>
					       <label style = "padding-left:20;"><font size = "3px" color="#717171">可能原因</font>
					       <input type = "text" id = "element_text" />
					       </label>
					       <input type = "button"  id = "button_add" value="添加总包项目的风险类型" 
					       onclick = "showDiv()"/>      
					    </fieldset>					    
					  
					    </div><!-- End demo -->
					  	<input type="text" style="visibility:hidden;width:10px;" name="itemsString" id="itemsString" value="3;8;3;3;7;4;1;1;1"/>		    
				</fieldset>				
			</td>
		</tr>
		</table>	 			
    	</td>
    </tr>
    <tr >
			<td colspan="2" align="center" height="50px">
				<input type="button" value="提交" style="width: 100px;height: 30px" onclick="checkValidate()"/>
			</td>
	</tr>　  
</table>

</div>
</form> 
<div id="updown"><span class="up"></span><span class="down"></span></div>
<script language=javascript>
var items = new Array();   //全局变量items用来保存每类风险可能原因的个数
items = <%=countList %>
var itemsString = "";   //把数组转化成字符串


var elementName1 = ""; //增加的可能原因名称

var categoryNumber = "";//判断点击的哪个添加按钮
var categoryCount = "";//当前点击的按钮的原始可能原因的个数
var categoryName = "";//当前点击的按钮的父类名称

function showDiv(){
     var i = $("#hidden").val();
     if(i>209){
     	 $("#text_add").val("");
     	 $("#element_text").val(""); //清除小类名称
     	 alert("风险因素的类型最多10个！");
     	 return;
     }
    if($("#text_add").val()==null||$("#text_add").val()==""||$("#element_text").val()==null||$("#element_text").val()==""){
		    alert("请填写待添加的大类名称和可能原因的名称！");
	}else{
    	$('#a_'+i).html($("#text_add").val());
    	$('#sub_209000').html($("#element_text").val()); //小类名称
		$('#label_'+i).html($("#text_add").val()+'的影响程度');
		$('#button_'+i).val('添加'+$("#text_add").val()+'的可能原因');
		$('#button_delete').val('删除'+$("#text_add").val()+'风险类型');
		$("#categoryname_209000").val($("#text_add").val()); //设置隐藏域大类名称
		$("#Idname_209000").val($("#element_text").val()); //设置隐藏域第一个可能原因的名称
		
		$("#button_209").attr("name",$("#text_add").val()); //更改button的name属性为大类的名称
		
		$("#text_add").val("");
		$("#element_text").val(""); //清除小类名称
	
		
		$('#h3_'+i).show();
		$('#riskElement_'+i).show();
		var i= parseInt(i)+1;
		$('#hidden').val(i);  //改变id为hidden的value值+1
       items[9] = 1;//增加items这个List的元素
	}  
}
function hideDiv(){
     var i = $("#hidden").val();
     i= parseInt(i)-1;
     $('#hidden').val(i);  //改变id为hidden的value值-1
     if(i<=209){
    	$("#text_add").val("");     //清除大类名称
    	$("#element_text").val(""); //清除小类名称
    	$("#checked_209").attr("checked",false);
    	$("#text_209").val("");
    	$("#text_209").attr("disabled",true);
    	$("#checked_209000").attr("checked",false);
    	$("#checked_209000").attr("disabled",true);
    	$("#select_209000").attr("disabled",true);
    	$("#select_209000").attr("selectedIndex",0);
    	$("#text_209000").attr("disabled",true);
    	$("#text_209000").val("");
    	
	    $('#h3_'+i).hide();
	    $('#riskElement_'+i).hide();
	    items.pop();//删除最后一个元素
    }
}

function addElement(evt){
	
	    var obj = window.event?event.srcElement:evt.target;
		categoryNumber = obj.id.toString().substring(7,10);   //判断点击的哪个按钮，给全局变量categoryNumber赋值，如200
		var position = obj.id.toString().substring(9,10);     //点击的按钮的items数组位置 ，如items[0]
		items[position] = items[position]+1;				  //给当前点击的按钮对应的items位置的值加1
        categoryCount = items[position]-1;                    //方便后面设置被添加的可能原因的id
        categoryName = obj.name.toString();
        if(items[position]>10){
        	alert("每类风险因素的可能原因最多为10个！");
        }else{
        	showModelessDialog("riskEvaluate/AddElement",window,"status:false;dialogWidth:300px;dialogHeight:150px;resizable:yes;");	 
        }
	 

}
function addElement_Impel(){
	
	 //alert("添加可能原因");				
	 var div = document.getElementById("riskElement_"+categoryNumber);
	 
	 
	 var hidden_name = document.createElement("input");//影藏域节点名称
	 hidden_name.type = "hidden";
	 hidden_name.setAttribute("id","Idname_"+categoryNumber+"00"+categoryCount);
	 hidden_name.setAttribute("name","Idname_"+categoryNumber+"00"+categoryCount);
	 hidden_name.setAttribute("value",elementName1);
	 
	 
	 var hidden_id = document.createElement("input");//影藏域节点id
	 hidden_id.type = "hidden";
	 hidden_id.setAttribute("id","Id_"+categoryNumber+"00"+categoryCount);
	 hidden_id.setAttribute("name","Id_"+categoryNumber+"00"+categoryCount);
	 hidden_id.setAttribute("value",categoryNumber+"00"+categoryCount);
	 
	 
	 var hidden_category = document.createElement("input");  //父节点编号
	 hidden_category.type = "hidden";
	 hidden_category.setAttribute("id","category_"+categoryNumber+"00"+categoryCount);
	 hidden_category.setAttribute("name","category_"+categoryNumber+"00"+categoryCount);
	 hidden_category.setAttribute("value",categoryNumber);
	 
	 var hidden_categoryname = document.createElement("input");  //父节点名称
	 hidden_categoryname.type = "hidden";
	 hidden_categoryname.setAttribute("id","categoryname_"+categoryNumber+"00"+categoryCount);
	 hidden_categoryname.setAttribute("name","categoryname_"+categoryNumber+"00"+categoryCount);
	 hidden_categoryname.setAttribute("value",categoryName);
	 
	 
	 
	  
	 var input_checkbox = document.createElement("input");
	 input_checkbox.type = "checkbox";
	 //input_checkbox.checked = true;
	 input_checkbox.setAttribute("id","checkbox_"+categoryNumber+"00"+categoryCount);    //设置被添加的可能原因的checkbox的id
	 
	 var category11 = document.getElementById("checkbox_" + categoryNumber);
	 if(category11.checked == true || category11.checked == "checked") {
	 	
	 }
	 else {
	 	input_checkbox.disabled = true;
	 }
	 
	 $(input_checkbox).click(function()
		{
			checkbox();
		});
	  
	 var input_select = document.createElement("select");	 
	 input_select.options[0] = new Option("--请选择--","0");
	 input_select.options[1] = new Option("1(很小)","1");
	 input_select.options[2] = new Option("2(较小)","2");
	 input_select.options[3] = new Option("3(一般)","3");
	 input_select.options[4] = new Option("4(较大)","4");
	 input_select.options[5] = new Option("5(很大)","5");	 
     input_select.style.cssText = "width:119px;font-size:15px";
	 input_select.setAttribute("id","select_"+categoryNumber+"00"+categoryCount);    //设置被添加的可能原因的select的id
	 input_select.setAttribute("name","select_"+categoryNumber+"00"+categoryCount);    //设置被添加的可能原因的select的name
	 input_select.disabled = true;
	 	 
	 var input_text = document.createElement("input");
	 input_text.type = "text";
	 input_text.style.cssText = "width:180px;font-size:15px";
	 input_text.setAttribute("id","text_"+categoryNumber+"00"+categoryCount);    //设置被添加的可能原因的text的id	 
	 input_text.setAttribute("name","text_"+categoryNumber+"00"+categoryCount);    //设置被添加的可能原因的text的name 
	 input_text.disabled = true;
	 $(input_text).blur(function(){
	 	changeSubText(this);
	 });
	 $(input_text).focus(function(){
	 	focusTextSub(this);
	 });
	 
	  
<%--	 var del = document.createElement("a");--%>
<%--		del.href = "javascript:void(0)";--%>
<%--		del.style.Fontsize = "15px";--%>
<%--		del.innerHTML ="删除";--%>
<%--		--%>
<%--		--%>
<%--		$(del).click(function()--%>
<%--		{--%>
<%--			this.parentNode.parentNode.removeChild(this.parentNode);--%>
<%--		});--%>

	var fieldset = document.createElement("fieldset");
	
	fieldset.appendChild(hidden_name);	      //增加三个隐藏域
	fieldset.appendChild(hidden_id);	      
	fieldset.appendChild(hidden_category);	
	fieldset.appendChild(hidden_categoryname);	
	
	fieldset.appendChild(input_checkbox);	
	
	$(fieldset).append('<label style = "padding-left:10;width:300px;"><font size = "3px" color="#717171">'+elementName1+'</font></label> ');	
	
	
	$(fieldset).append('<label style = "padding-left:9;"><font size = "3px" color="#717171">可能性:</font></label>');
	
	fieldset.appendChild(input_select);	
	
	$(fieldset).append('<label style = "padding-left:27;"><font size = "3px" color="#717171">影响程度:</font></label>');	
	
	fieldset.appendChild(input_text);
	$(fieldset).append("&nbsp;&nbsp;&nbsp;");
<%--	fieldset.appendChild(del);--%>
	
	div.appendChild(fieldset);
	
}
//验证大类影响程度并将影响程度显示在折叠面板上
function changeText(divID){
	var patrn = /(^(0\.\d{1,2})?|1|1.0|1.00)$/;
	var divNum = divID.toString().substring(12,15);
	var superValue = document.getElementById("text_"+divNum).value;
	if((!patrn.test(superValue)&&superValue!=null&&superValue!="")||superValue=="0.0"||superValue=="0.00"||superValue=="0"){ 	
		     alert("影响程度为大于0小于等于1之间的数,保留两位小数！");
		      $("#text_"+divNum).val("");
		     document.getElementById('a_'+divNum).innerHTML = superValue.substring(0,superA.lastIndexOf("["));
		     $("#text_"+divNum).focus();
		     return;
		}
	var superA = document.getElementById("a_"+divNum).innerHTML;
	if(superA.lastIndexOf("[")>-1){
		
		superA = superA.substring(0,superA.lastIndexOf("["));
	}
	
	$('#a_'+divNum).html(superA);
	var superChange = superA+"["+superValue+"]";
	if(superValue==""){
		superChange=superA;
	}
	document.getElementById('a_'+divNum).innerHTML = superChange;
	
	//改变text中值，实时验证和为1
	var sumDegree = 0;    //保存影响程度和
	for(var i = 200;i<=209;i++){
		if(document.getElementById("checkbox_"+i)){
			if(document.getElementById("checkbox_"+i).checked){
				
				if($("#text_"+i).val()!=null&&$("#text_"+i).val()!=""){
					sumDegree = new BigDecimal(sumDegree.toString()).add(new BigDecimal($("#text_"+i).val().toString()));
			    }
	        }
		}else{
			continue;
		}
	}
	if(new BigDecimal(sumDegree.toString())>1){
		alert("所选风险因素的影响程度和大于1,请确认后输入！");
		$("#text_"+divNum).val("");
		document.getElementById('a_'+divNum).innerHTML = superA;
		//document.getElementById('checkbox_'+divNum).setAttribute('checked',false); 
		return;
	}
	
}
//实时验证小类影响程度是否为2位小数
function changeSubText(obj){
	var textID = $(obj).attr("id");
	var divID = textID.toString().substring(5,11);
	var patrn = /(^(0\.\d{1,2})?|1|1.0|1.00)$/;
	var subValue = document.getElementById("text_"+divID).value;
	if((!patrn.test(subValue)&&subValue!=null&&subValue!="")||subValue=="0.0"||subValue=="0.00"||subValue=="0"){ 	
		     alert("影响程度为大于0小于等于1之间的数,保留两位小数！");
		     $("#text_"+divID).val("");
		     //$("#text_"+divID).focus();
		     return;
		}
		
	//改变text中值，实时验证和为1
	var sumDegree = 0;    //保存影响程度和
	var subtextID = divID.toString().substring(2,3);
	for(var i = 0;i<=9;i++){
		if(document.getElementById("checkbox_20"+subtextID+"00"+i)){
			if(document.getElementById("checkbox_20"+subtextID+"00"+i).checked){				
				if($("#text_20"+subtextID+"00"+i).val()!=null&&$("#text_20"+subtextID+"00"+i).val()!=""){
					sumDegree = new BigDecimal(sumDegree.toString()).add(new BigDecimal($("#text_20"+subtextID+"00"+i).val().toString()));
			    }
	        }
		}else{
			continue;
		}
	}
	if(new BigDecimal(sumDegree.toString())>1){
		alert("该类风险因素的可能原因的影响程度的和大于1,请确认后输入！");
		$("#text_"+divID).val("");
//		document.getElementById('checkbox_'+divID).setAttribute('checked',false); 
//		document.getElementById('text_'+divID).setAttribute('disabled','disabled');
//		document.getElementById('select_'+divID).setAttribute('disabled','disabled');
//		document.getElementById('select_'+divID).setAttribute('selectedIndex',0);
		return;
	}
		
}

//实时提示大类的影响程度剩余值
function focusText(textID){
	var sumDegree = 0;    //保存影响程度和
	for(var i = 200;i<=209;i++){
		if(document.getElementById("checkbox_"+i)){
			if(document.getElementById("checkbox_"+i).checked){
				var textIdCopy = "text_"+i;
				if($("#text_"+i).val()!=null&&$("#text_"+i).val()!=""&&textIdCopy!=textID){
					sumDegree = new BigDecimal(sumDegree.toString()).add(new BigDecimal($("#text_"+i).val().toString()));
			    }
	        }
		}else{
			continue;
		}
	}
		if(isNaN(sumDegree)){
			$("#"+textID).val(1.00);
		}else{
			var textValue = $("#"+textID).val();
			if(textValue == null || textValue == "") {
				var temp = (new BigDecimal("1").subtract(new BigDecimal(sumDegree.toString()))).toString();
				if(temp==0||temp==0.0||temp==0.00){
					alert("所选风险因素的影响程度和已经等于1，不能再添加了！");
				}else{
					$("#"+textID).val(temp);
				}
			}		
		}
}
//实时提示可能原因的影响程度剩余值
function focusTextSub(obj){
	var textID = $(obj).attr("id");
	var sumDegree = 0;    //保存影响程度和
	var subtextID = textID.toString().substring(7,8);
	for(var i = 0;i<=9;i++){
		if(document.getElementById("checkbox_20"+subtextID+"00"+i)){
			if(document.getElementById("checkbox_20"+subtextID+"00"+i).checked){
				var textIdCopy = "text_20"+subtextID+"00"+i;
				if($("#text_20"+subtextID+"00"+i).val()!=null&&$("#text_20"+subtextID+"00"+i).val()!=""&&textIdCopy!=textID){
					sumDegree = new BigDecimal(sumDegree.toString()).add(new BigDecimal($("#text_20"+subtextID+"00"+i).val().toString()));
			    }
	        }
		}else{
			continue;
		}
	}
		if(isNaN(sumDegree)){
			$("#"+textID).val(1.00);
		}else{
			var textValue = $("#"+textID).val();
			if(textValue == null || textValue == "") {
				var temp =(new BigDecimal("1").subtract(new BigDecimal(sumDegree.toString()))).toString();
				if(temp==0||temp==0.0||temp==0.00){
					alert("该类风险因素的可能原因的影响程度和已经等于1，不能再添加了！");
				}else{
					$("#"+textID).val(temp);
				}
			}
		}
}

function checkbox(){
	
	for(var i = 0;i<items.length;i++){
		var count = items[i];	
		if(document.getElementById("checkbox_20"+i)){
			if(document.getElementById("checkbox_20"+i).checked){
				document.getElementById("text_20"+i).removeAttribute('disabled');
				for(var j=0;j<count;j++){
					if(document.getElementById("checkbox_20"+i+"00"+j)){
						document.getElementById("checkbox_20"+i+"00"+j).removeAttribute('disabled');
					 
						if(document.getElementById("checkbox_20"+i+"00"+j).checked){ 
				          document.getElementById("text_20"+i+"00"+j).removeAttribute('disabled');
				          document.getElementById("select_20"+i+"00"+j).removeAttribute('disabled'); 
			
				        }
						else{
				          document.getElementById("text_20"+i+"00"+j).setAttribute('disabled','disabled');
				          document.getElementById("text_20"+i+"00"+j).value="";
				          document.getElementById("select_20"+i+"00"+j).setAttribute('disabled','disabled');
				          document.getElementById("select_20"+i+"00"+j).setAttribute('selectedIndex',0);
			           } 
					}
					else{
						continue;
					}
					
               }
		   }
		   else{
			 document.getElementById("text_20"+i).setAttribute('disabled','disabled');
			 document.getElementById("text_20"+i).value="";
			 for(var j=0;j<count;j++){
				 if(document.getElementById("checkbox_20"+i+"00"+j)){
					 document.getElementById("checkbox_20"+i+"00"+j).setAttribute('disabled','disabled');
					 document.getElementById("checkbox_20"+i+"00"+j).setAttribute('checked',false);    //兼容模式下有效
					 document.getElementById("text_20"+i+"00"+j).setAttribute('disabled','disabled');
			         document.getElementById("text_20"+i+"00"+j).value="";
			         document.getElementById("select_20"+i+"00"+j).setAttribute('disabled','disabled');
			         document.getElementById("select_20"+i+"00"+j).setAttribute('selectedIndex',0);
				 }else{
					 continue;
				 }
				 
				 //取消选中去掉影响程度
	         changeText("riskElement_20"+i);

	         }
		  }
		}
		else{
			continue;
		}		
	}
}

//提交验证
function checkValidate(){

	//提交确认
    if (!confirm("提交后不能修改，确定提交？")) {
        return;
    }

	var sum = 0;         //对勾选的可能原因计数，如果为0，则不提交后台计算
	var flag=false;
	var projectName = document.getElementById("projectName").value;
	if((null ==projectName||""==projectName)){
		alert("项目名称未填写！");
		return;
	}
		
	//var patrn = /(^0\.[0-9]\d*|1|1.0)$/;
	var patrn = /(^(0\.\d{1,2})?|1|1.0|1.00)$/;
	var superSum = 0;   //大类的影响程度之和
	var subCount = new Array();
	subCount = [0,0,0,0,0,0,0,0,0,0];  //小类的影响程度之和
	var selectflag = false;
	for(var i = 0;i<items.length;i++){
		var count = items[i];
		var superValue = 0;
		for(var j=0;j<count;j++){
		flag = false;
		if(document.getElementById("checkbox_20"+i)){
			if(document.getElementById("checkbox_20"+i).checked){
			superValue = document.getElementById("text_20"+i).value;
			if(!patrn.test (superValue)){
				flag =true;
				break;
			}
			if(document.getElementById("checkbox_20"+i+"00"+j)){
				if(document.getElementById("checkbox_20"+i+"00"+j).checked){ 
				sum=sum+1;
				var textValue = document.getElementById("text_20"+i+"00"+j).value;
				var selectValue = document.getElementById("select_20"+i+"00"+j).selectedIndex;
				    if(0==selectValue) {
							selectflag = true;
							break;
						}
					if((!patrn.test (textValue))){					
						flag =true;
						break;
					}else{
						//subCount[i] = Number(subCount[i])+Number(textValue);
						subCount[i] = new BigDecimal(subCount[i].toString()).add(new BigDecimal(textValue.toString()));
					}
		       }
			}else{
				continue;
			}	
		  }
		}else{
			continue;
		}
		}
		if(true==selectflag) {
			alert("存在未选择的发生可能性！");
			return;
		}
		if(true==flag){
		alert("影响程度为大于0小于等于1之间的数！");
		return;
	    }
		//superSum = Number(superSum)+Number(superValue);
		superSum = new BigDecimal(superSum.toString()).add(new BigDecimal(superValue.toString()));
	    superValue=0;	//将superValue变量的值清空
	}
	//未勾选任何一个可能原因
	if(sum<1){
		alert("至少有一类风险因素才能进行风险评估！");
		return;
	}
	//验证小类的影响程度和是否为1
	for(var i=0;i<10;i++){
		if(document.getElementById("checkbox_20"+i)){
			if(document.getElementById("checkbox_20"+i).checked&&subCount[i]!=1){
			var riskName = document.getElementById("a_20"+i).innerHTML;  //保存验证未通过的名称
			alert(riskName+"的可能原因的影响程度之和不为1,请核对！");
			return;
		    }
		}else{
			continue;
		}
	}
	//验证大类的影响程度和是否为1
	if(superSum!=1){
		alert("风险因素的影响程度和不为1，请核对！");
		return;
	}
	//把items这个list传到后台
	for(var i = 0;i<items.length;i++){
		itemsString = itemsString + items[i];
		itemsString = itemsString + ";";
	}
	itemsString = itemsString.substring(0,2*items.length - 1);
	$('#itemsString').val(itemsString);
	
    
    riskEvaluateContractInput.action = "riskEvaluate/RiskEvaluateContractResultAction";
    riskEvaluateContractInput.submit();
}

 function selectProject(){
	var projectName=document.getElementById("projectName").value;
	projectName=encodeURI(encodeURI(projectName));//为避免乱码，对有中文的字符串进行加码
	showModelessDialog("riskEvaluate/selectContractProjectName?projectName="+projectName,window,"status:false;dialogWidth:600px;dialogHeight:490px;resizable:yes;scroll:yes");
 }
 
 function updateProject(){
	document.getElementById("projectName").value=projectName1;
}
function addProject(){
	showModelessDialog("/RiskEvent/RiskEvaluate/addContractProjectName.jsp",window,"status:false;dialogWidth:300px;dialogHeight:150px;resizable:yes;");
}
</script>

</body>
</html>
