function goTopEx(){
        var obj=document.getElementById("goTopBtn");
        var bottom=document.getElementById("goBottomBtn");
        function getScrollTop(){
        	var top=document.documentElement.scrollTop;
                return top;
            }      
        function setScrollTop(value){
                document.documentElement.scrollTop=value;
            }    
        window.onscroll=function(){
        	if(getScrollTop()>0){obj.style.display="";bottom.style.display="";}
        	else {obj.style.display="none";bottom.style.display="none"};
        	}
        obj.onclick=function(){
            var goTop=setInterval(scrollMove,10);
            function scrollMove(){
                    setScrollTop(getScrollTop()/1.1);
                    if(getScrollTop()<1)clearInterval(goTop);
                }
        }
         bottom.onclick=function(){
            var godown=setInterval(scrollMovedown,10);
            function scrollMovedown(){
                    setScrollTop(getScrollTop()*1.1);
                    var he=document.documentElement.scrollHeight*1;
                    var ch=document.documentElement.clientHeight*1;
                    if((getScrollTop()*1+ch+5)>he)clearInterval(godown);
                }
        }
    }