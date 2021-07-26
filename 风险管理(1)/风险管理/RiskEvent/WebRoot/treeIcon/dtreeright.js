function showMenu(id){
      document.getElementById("selectItem").value= id;
    if("" == id){
    }
    else{
	 popMenu(itemMenu,80,"111");
	 }
      event.returnValue=false;
      event.cancelBubble=true;
      return false;
}
/**
*显示弹出菜单
*menuDiv:右键菜单的内容
*width:行显示的宽度
*rowControlString:行控制字符串，0表示不显示，1表示显示，如“101”，则表示第1、3行显示，第2行不显示
*/
function popMenu(menuDiv,width,rowControlString){
    //创建弹出菜单
    var pop=window.createPopup();
    //设置弹出菜单的内容
    pop.document.body.innerHTML=menuDiv.innerHTML;
    var rowObjs=pop.document.body.all[0].rows;
    //获得弹出菜单的行数
    var rowCount=rowObjs.length;
    //循环设置每行的属性
    for(var i=0;i<rowObjs.length;i++)   {
        //如果设置该行不显示，则行数减一
        var hide=rowControlString.charAt(i)!='1';
        if(hide){
            rowCount--;
        }
        //设置是否显示该行
        rowObjs[i].style.display=(hide)?"none":"";
        //设置鼠标滑入该行时的效果
        rowObjs[i].cells[0].onmouseover=function(){
            this.style.background="#c5daf8";
            this.style.color="white";
        }
        //设置鼠标滑出该行时的效果
        rowObjs[i].cells[0].onmouseout=function(){
            this.style.background="#fffefd";
            this.style.color="#000000";
        }
    }
    //屏蔽菜单的菜单
    pop.document.oncontextmenu=function(){
            return false;
    }
    //选择右键菜单的一项后，菜单隐藏
    pop.document.onclick=function(){
            pop.hide();
    }
    //显示菜单
    pop.show(event.clientX-1,event.clientY,width,rowCount*25,document.body);
    return true;

}