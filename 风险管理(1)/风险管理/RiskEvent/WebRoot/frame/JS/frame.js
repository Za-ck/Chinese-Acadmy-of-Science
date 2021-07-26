// JavaScript Document
//top 调用

function callTreeMenu(num){
//parent.document.getElementById("centerFrame").initTreeMenu(num);
initTreeMenu(num);
}
//middle调用

function initTreeMenu(num){
for(var i=1;i<=9;i++)
if(i==num)
document.all("menu"+num).style.display="";
else document.all("menu"+i).style.display="none";
document.rightMain.src="Rightmain.html";
initMaglist(num);
}
// right 调用
 function initMaglist(num){
for(var i=1;i<=9;i++)
if(i==num) document.all("imgmenu"+num).style.display="";
else document.all("imgmenu"+i).style.display="none";

 }
