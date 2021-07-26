<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="level" uri="/WEB-INF/level.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/RiskEvent/js/windows.css" rel="stylesheet" type="text/css"></link>
<link href="/RiskEvent/css/tab.css" rel="stylesheet" type="text/css"></link>
<script src="/RiskEvent/js/jquery-1.7.1.js" type="text/javascript"></script>
<script type="text/javascript" src="/RiskEvent/js/tab.js"></script>

<title>中南电力设计</title>

<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image:url(images/bg.png);
}
.menulist ul{
width:100%;
margin-top:0px;
list-style:none;
text-align:left;
list-style-position:outside;
}
.menulist ul li{
width:100%;
height:30px;
margin-left:5px;
}
.menulista{
text-decoration:none;
cursor:pointer;
color:#818181;
}
.menulist ul li a{
text-decoration:none;
cursor:pointer;
color:#818181;
}
.menulist ul li a:hover{
color:#2e9cee;
font-weight:bold;
}
.menulist ul li a span{
background:url(images/dote.png) left no-repeat;
padding-left:10px;
font-size:14px;
font-family:Arial, Helvetica, sans-serif;
}

</style>

<style>
.navPoint {
	COLOR: white;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 9pt
}

#tabs {
	padding: 0px;
	width: 100%;
	height: 100%;
	overflow-x: hidden;
	
}

</style>
<script>

	var tab=null;
	
	$( function() {
	  	tab = new TabView( {
			containerId :"tabs",
			pageid :"page",
			cid :"tab_po",
			position :"top"
		});

		tab.add( {
			id :"tab_id_index1",
			title :"待办已办",
			url :"default/processAction_unProcessed",
			isClosed :false
		});
		
		$("#tab_id_index1").find(".tab_title").dblclick( function() {
			
			tab.closeOtherTab("tab_id_index1");
			
		});
		
	});
	
	var flag = 0;
	function switchSysBar() {
		if (flag == 0) {
			document.all("frmTitle").style.width = "20";
			document.all("leftDiv").style.display = "none";
			document.all("leftDivHidden").style.display = "";
			flag = 1;
		} else {
			document.all("frmTitle").style.display = "";
			document.all("frmTitle").style.width = "183";
			document.all("leftDiv").style.display = "";
			document.all("leftDivHidden").style.display = "none";
			flag = 0;
		}
	}
	//二期代码，单击三级菜单或没有三级菜单的二级菜单
	function initTreeMenu(num, text) {
		var uls = document.getElementById("menulist")
				.getElementsByTagName("ul");
		document.getElementById("menuText").innerHTML = text;
		for ( var i = 0; i < uls.length; i++)
			uls[i].style.display = "none";

		var lis = document.getElementById("menuUl_" + num)
				.getElementsByTagName("li");
		for ( var i = 0; i < lis.length; i++) {
			var a = lis[i].getElementsByTagName("a");
			a[0].style.color = "#818181";
		}
		document.all("menuUl_" + num).style.display = "";
		//window.frames["mainFrame"].document.location.href = "Rightmain.jsp?num="
				//+ num + "&random=" + Math.floor(Math.random() * 1000 + 1);
	}
	
	//二期代码，展开二级菜单
	function changeColorUl(upnum, num) {

		var lis = document.getElementById("menuUl_" + upnum)
				.getElementsByTagName("li");
		for ( var i = 0; i < lis.length; i++) {
			//将所有二级菜单，还原成灰色
			var a = lis[i].getElementsByTagName("a");
			a[0].style.color = "#818181";
		}
		//将选择的二级菜单，变为蓝绿色
		//document.getElementById("menu"+num).style.color="#2e9cee";
		document.getElementById("menuA_" + num).style.color = "#2e9cee";
		//展开或关闭3级菜单
		if ("none" == document.getElementById("menuUl_" + num).style.display) {
			document.getElementById("menuUl_" + num).style.display = "";
		} else {
			document.getElementById("menuUl_" + num).style.display = "none";
		}
		//window.frames["mainFrame"].document.location.href="Rightmain.jsp?num="+num+"&random="+Math.floor(Math.random()*1000+1);
	}
	//二期代码，单击三级菜单或没有三级菜单的二级菜单
	function changeColorA(upnum, num) {
		//alert("fdsaf");
		var lis = document.getElementById("menuUl_" + upnum)
				.getElementsByTagName("li");
		for ( var i = 0; i < lis.length; i++) {
			//将所有二级菜单，还原成灰色
			var a = lis[i].getElementsByTagName("a");
			a[0].style.color = "#818181";
		}
		//将选择的二级菜单，变为蓝绿色
		document.getElementById("menuA_" + num).style.color = "#2e9cee";
		//因为，单击3级菜单时，会触发单击2级菜单事件，所以先将3级菜单改为隐藏
		//document.getElementById("menuUl_"+upnum).style.display="none";
		//alert(1);

	}

	function callHome() {
		tab.update({
					id : "tab_id_index1",
					url : "default/processAction_unProcessed" + "?random=" + Math.floor(Math.random() * 1000 + 1),
					title : "待办已办"
				});
		tab.activate('tab_id_index1');
		//document.getElementById("mainFrame").src = "default/processAction_unProcessed";
		var uls = document.getElementById("menulist")
				.getElementsByTagName("ul");
		document.getElementById("menuText").innerHTML = "待办已办";
		for ( var i = 0; i < uls.length; i++)
			uls[i].style.display = "none";
	}

		
	var index = 1;
	
	function showWarn(action) {
		index++;
		if(action.indexOf("?") < 0) {
 			action += "?random=" + Math.floor(Math.random() * 1000 + 1);
 		}
 		else { 
 			action += "&random=" + Math.floor(Math.random() * 1000 + 1);
 		}
		tab.add({
					id : 'tab_id_index' + (index),
					url : action,
					title : '系统消息',
					isClosed : true
				});
		//window.frames["mainFrame"].document.location.href = action + "?random="
				//+ Math.floor(Math.random() * 1000 + 1);
		var uls = document.getElementById("menulist").getElementsByTagName("ul");
		document.getElementById("menuText").innerHTML = "系统消息";
		for ( var i = 0; i < uls.length; i++)
			uls[i].style.display = "none";
		var lis = document.getElementById("menu" + 13).getElementsByTagName(
				"li");
		for ( var i = 0; i < lis.length; i++) {
			var a = lis[i].getElementsByTagName("a");
			a[0].style.color = "#818181";
		}
		document.all("menu" + 13).style.display = "";
	}

	function openTab(url, actionname) 
	{
		index++;
		var id='tab_id_index'+(index);
 		if(url.indexOf("?") < 0) {
 			url += "?random=" + Math.floor(Math.random() * 1000 + 1);
 		}
 		else { 
 			url += "&random=" + Math.floor(Math.random() * 1000 + 1);
 		}
		tab.add( {
			id :id,
			title :actionname,
			url :url,
			isClosed : true
		});
		var tab_title = $("#tab_id_index" + index).find(".tab_title").click( function() {
			
			var title = $(this).text();
    		var menuId = $("a:contains('"+title+"')").eq(0).attr("id");			//获取该级目录的id
    		var menuA = $("a:contains('"+title+"')");
    		for(var i=0; i<menuA.length;i++) {
    		
    			var menuTitle = $(menuA).eq(i).children().eq(0).text();
    			if(menuTitle == title) {
    				menuId = $(menuA).eq(i).attr("id");
    				break;
    			}
    			
    		}
    		var menuIdNum = menuId.substring(6,menuId.length);			//获取目录
			var level1 = menuIdNum.substring(0,3);
			window.parent.window.frames["topFrame"].autoCallTreeMenu(level1);
			$("#menuA_" + menuIdNum.substring(0,6)).click();
			if(menuIdNum.length > 6) {
				$("#" + menuId).parent().click(); 
			}
			
		});
		
		$("#tab_id_index" + index).find(".tab_title").dblclick( function() {
			
			tab.closeOtherTab("tab_id_index" + index);
			
		});
	}
	
	function unprocessed()
	{
		tab.closeCurrentTab();
		tab.update({
					id : "tab_id_index1",
					url : "default/processAction_unProcessed" + "?random=" + Math.floor(Math.random() * 1000 + 1)
				});
		tab.activate('tab_id_index1');
	}
	
	
	function getCurrentTabId()
	{
		return tab.getCurrentTabId();
	}
	
</script>

</head>

<body style="overflow-x:hidden;">
	<table style="width:100%;height:100%;border: 0px;border-collapse:collapse;" >
		<tr>
			<td width="190" id=frmTitle nowrap="nowrap" align="center" valign="top" style="border-collapse:collapse;">
				<div id="leftDiv" style="width: 100%; height: 100%;overflow: auto;">
					<table id="__01" style="width:100%;height:100%;border: 0px;border-collapse:collapse;" >
						<tr>
							<td background="images/treeLeft_01.gif" width="183" height="71">
								<div style="width: 90%; height:40px; text-align:right; ">
									<div id="menuText"
										style="float:left;width:75%;height:40px; font-size:17px;color:#1281d3; font-weight:bolder; text-align:right; margin-top:5px;">功能菜单
									</div>
									<img id="leftImag" title="隐藏功能" src="images/close.png" style="cursor: pointer;width:30px;height:30px" onclick="switchSysBar()"></img>
								</div>
							</td>
						</tr>
						<tr>
							<td background="images/treeLeft_02.gif" width="183" height="16">&nbsp;
								
							</td>
						</tr>
						<tr>
							<td background="images/treeLeft_02.gif" width="183" valign="top"
								style="margin-top:0px; padding:0px;">
								<div id="menulist" class="menulist">
									<level:level level="2"></level:level>
								</div></td>
						</tr>
						<tr>
							<td background="images/treeLeft_04.gif" width="183" height="31">&nbsp;
							</td>
						</tr>
					</table>
				</div>
				<div id="leftDivHidden" onClick="switchSysBar()" title="展开功能"
					style="width:20px;height:500px;cursor:pointer;text-align:left; background:url(images/open2.png) center no-repeat; display:none;">
				</div>
			</td>
			<td>
				<div id="tabs" style="height:5%"></div>
				<div id="page" style="height:95%"></div>
				
			</td>
		</tr>

	</table>
	
	 <%
  if (session.getAttribute("inremind") == "1") {
  %>
  	<script src="/RiskEvent/js/Notice/notice.js" type="text/javascript"></script>
  <div id="winpop" >
  <div class="title">您有新的消息<span class="close" onClick="tips_pop()">×</span></div>
  <div class="con">您本月部门还未录入任何风险事件</div>
  </div>
  <%
  }
  %>
</body>
</html>


