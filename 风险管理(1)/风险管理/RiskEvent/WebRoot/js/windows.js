 var closeLoginBox=function(){
	 var loginBox=document.getElementById('loginBox');
	 var cover=document.getElementById('cover');
		 
	 document.body.removeChild(loginBox);
	 document.body.removeChild(cover);
	 
	 }
var openBox=function(title,width,height,flag,text,url){
 var cover=document.createElement('div');
     cover.setAttribute('id','cover');
     cover.style.width=document.body.clientWidth+'px';
	 cover.style.height=document.body.clientHeight+'px';
	 cover.style.background='#929292';
	 cover.style.position='absolute';
	 cover.style.left='0';
	 cover.style.top='0';
	 cover.zIndex='99';
	 cover.style.opacity=10;
	 cover.style.filter='alpha(opacity=10)'
	 document.body.appendChild(cover);
 var loginBox=document.createElement('div');
     loginBox.setAttribute('id','loginBox');
     loginBox.style.width=width+'px';
	 loginBox.style.height=height+'px';
     loginBox.style.border='2px solid #dcdee0';
     loginBox.style.position='absolute';
     loginBox.style.left=(screen.width-width)/2-92+'px';
	 loginBox.style.top=document.documentElement.scrollTop+(document.body.clientHeight-height)/3+'px';
     loginBox.style.overflow='hidden';
     loginBox.style.marginLeft='0px';
     loginBox.style.width=width;
	 loginBox.style.boxShadow='0 0 19px #dcdee0'
	 loginBox.style.borderRadius='10px';
	 document.body.appendChild(loginBox);
var loginBoxHandle=document.createElement('h1');
     loginBoxHandle.setAttribute('id','loginBoxHandle');
	 loginBoxHandle.style.fontSize='14px';
	 loginBoxHandle.style.color='#ffffff';
	 loginBoxHandle.style.background='#117ed0';
	 loginBoxHandle.style.textAlign='left';
	 loginBoxHandle.style.padding='5px 10px';
	 loginBoxHandle.style.margin='0';
	 loginBoxHandle.innerHTML=title+'<span onclick="closeLoginBox()" title="关闭" style="position:absolute; cursor:pointer; font-size:16px;right:8px; top:5px">×</span>';
     loginBox.appendChild(loginBoxHandle);
if(flag==1){
	url=(url.indexOf("?")==-1)?url+"?random="+Math.floor(Math.random()*1000+1):url+"&random="+Math.floor(Math.random()*1000+1);
var iframe=document.createElement('iframe');
    iframe.setAttribute('src',url);
	iframe.setAttribute('frameborder',0);
	iframe.setAttribute('scrolling','auto');
	iframe.setAttribute('width',width);
	iframe.setAttribute('height',height);
	loginBox.appendChild(iframe);
	}
     else{
var content =document.createElement("div");
    content.setAttribute('id','content');
    content.style.width="100%";
    content.style.height="100%";
    content.style.background="#ffffff";
    content.style.overflow='auto';
    content.style.textAlign='center';
    content.style.fontSize='13px';
    content.style.padding='5px';
    content.innerHTML='<span style="font-size:14px;right:8px;height:100%;top:5px;color:#FF0000">'+text+'</span>';
    loginBox.appendChild(content);
     }
//	 new dragDrop({
//         target:document.getElementById('loginBox'),
//         bridge:document.getElementById('loginBoxHandle')
//});		
}
	 

/* new Dragdrop({
 *         target      拖拽元素 HTMLElemnt 必选
 *         bridge     指定鼠标按下哪个元素时开始拖拽，实现模态对话框时用到 
 *         dragX      true/false false水平方向不可拖拽 (true)默认
 *         dragY     true/false false垂直方向不可拖拽 (true)默认
 *         area      [minX,maxX,minY,maxY] 指定拖拽范围 默认任意拖动
 *         callback 移动过程中的回调函数
 * });
*/
Array.prototype.max = function() {
    return Math.max.apply({},this)
}
Array.prototype.min = function() {
    return Math.min.apply({},this)
}
var getByClass=function(searchClass){
        var tags = document.getElementsByTagName('*');
            var el = new Array();
        for(var i=0;i<tags.length;i++){
            if(tags[i].className==searchClass){
                el.push(tags[i])
                };
            }
            return el
    }
function dragDrop(option){
    this.target=option.target;
    this.dragX=option.dragX!=false;
    this.dragY=option.dragY!=false;
    this.disX=0;
    this.disY=0;
    this.bridge =option.bridge;
    this.area=option.area;
    this.callback=option.callback;
    this.target.style.zIndex='0';
    var _this=this;
	     this.bridge && (this.bridge.onmouseover=function(){ _this.bridge.style.cursor='move'});
     this.bridge?this.bridge.onmousedown=function(e){ _this.mousedown(e)}:this.target.onmousedown=function(e){ _this.mousedown(e)}
         }
    dragDrop.prototype={
        mousedown:function(e){
             var e=e||event;
             var _this=this;    
             this.disX=e.clientX-this.target.offsetLeft;
             this.disY=e.clientY-this.target.offsetTop;
             this.target.style.cursor = 'move';
           
             if(window.captureEvents){ 
             e.stopPropagation();
          e.preventDefault();}
              else{
                e.cancelBubble = true;
                }
            if(this.target.setCapture){
                this.target.onmousemove=function(e){_this.move(e)}
                this.target.onmouseup=function(e){_this.mouseup(e)}
                this.target.setCapture()
                }
                else{
            document.onmousemove=function(e){_this.move(e)}
            document.onmouseup=function(e){_this.mouseup(e)}
                }
                    },
    move:function(e){
	 this.target.style.margin=0;
                var e=e||event;
                var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
                var moveX=e.clientX-this.disX;
                var moveY=e.clientY-this.disY;              
                if(this.area){
                moveX < _this.area[0] && (moveX = this.area[0]); // left 最小值
                moveX > _this.area[1] && (moveX = this.area[1]); // left 最大值
                moveY < _this.area[2] && (moveY = this.area[2]); // top 最小值
                moveY > _this.area[3] && (moveY = this.area[3]); // top 最大值                    
                    }
                    
                this.dragX && (this.target.style.left=moveX+'px');
                this.dragY && (this.target.style.top=moveY+'px');
                //限定范围
                 parseInt(this.target.style.top)<0 && (this.target.style.top=0);
                 parseInt(this.target.style.left)<0 && (this.target.style.left=0);
                 parseInt(this.target.style.left)>document.documentElement.clientWidth-this.target.offsetWidth&&(this.target.style.left=document.documentElement.clientWidth-this.target.offsetWidth+"px");
                 parseInt(this.target.style.top)>scrollTop+document.documentElement.clientHeight-this.target.offsetHeight && (this.target.style.top=scrollTop+document.documentElement.clientHeight-this.target.offsetHeight+'px');
                if(this.callback){
                    var obj = {moveX:moveX,moveY:moveY};
                    this.callback.call(this,obj)
                    }
            return false
            },
     mouseup:function (e)
            {
             var e=e||event;
             this.target.style.cursor = 'default';
             var _this=this;
			  this.target.onmousemove=null;
			  this.target.onmouseup=null;
            document.onmousemove=null;
            document.onmouseup=null;
            if(this.target.releaseCapture) {this.target.releaseCapture()}
            }    
        }	

	//防止按回退键的时候浏览器回退到上一页
   	function stopBack()
    {
    	document.getElementsByTagName("body")[0].onkeydown = function(){  
          
        		//获取事件对象  
        		var elem = event.relatedTarget || event.srcElement || event.target ||event.currentTarget;   
          
        		if(event.keyCode==8)
        		{//判断按键为backSpace键  
          
                	//获取按键按下时光标做指向的element  
                	var elem = event.srcElement || event.currentTarget;   
                  
                	//判断是否需要阻止按下键盘的事件默认传递  
                	var name = elem.nodeName;  
                  
                	if(name!='INPUT' && name!='TEXTAREA'){  
                    	return _stopIt(event);  
                	}  
                	var type_e = elem.type.toUpperCase();  
                	if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')){  
                        	return _stopIt(event);  
                	}  
                	if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){  
                        return _stopIt(event);  
                	}  
            	}  
        	}
    }
    
    function _stopIt(e)
	{  
        if(e.returnValue)
        {  
            e.returnValue = false ;  
        }  
        if(e.preventDefault )
        {  
            e.preventDefault();  
        }                 
  
        return false;  
	} 
    