function senfe(o,a,b,c,d){
var tr=document.getElementById(o).getElementsByTagName("tr");
for(var i=0;i<tr.length;i++){
tr[i].style.backgroundColor=(tr[i].sectionRowIndex%2==0)?a:b;
tr[i].onclick=function(){
   if(this.x!="1"){
    this.x="1";
    this.style.backgroundColor=d;
   }else{
    this.x="0";
    this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
   }
}
tr[i].onmouseover=function(){
	   if(this.x!="1")this.style.backgroundColor=c;
	}
tr[i].onmouseout=function(){
	   if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
	}
	}
 }// JavaScript Document// JavaScript Document
  function SortTable(id)
    {
        this.init(id);
    }
    SortTable.prototype = 
    {
        data:[],
        _table:null,
        norder:[],
        _moveOffset:0,
        init:function(id)
        {
            
            this._table = $("#"+id);
            var trs = this._table.find("tbody tr");
            var _this = this;
            trs.each(function(){
                var row = [];
                $(this).children().each(function(){
                    row.push($(this).html());
                });
                _this.data.push(row);
                
            });
            
            var theads = this._table.find("thead tr").children();
            var _this = this;
            
            theads.each(function(idl){
                if($(this).attr("class") && $(this).attr("class").indexOf("order") >= 0)
                {
                    if($(this).children("a").length <= 0)
                    {
                        var slink = $("<a href='javascript:'></a>");
                        slink.text($(this).text());
                        $(this).text("");
                        $(this).append(slink);
                    }                 
                    $(this).children("a").click(function(){
                        _this.sort(idl);
                    });
                }
                if(idl >= theads.length-1)
                    return;
                //拖曳宽度初始化
                var dra = $("<div></div>").appendTo(this);
                dra.css(
                	{
                	position:"absolute",
                	width:"2px",
                	'float':"right",
                	borderLeft:this.style.borderLeft,
                	borderRight:this.style.borderRight,
                	cursor:"col-resize"}
                );
                dra.attr("index",idl);
                
                var td = $(this);
                td.css("width",td.width());
                var tdoffset = td.offset();
                dra.css({height:td.height(),top:tdoffset.top+1,left:(tdoffset.left+td.width()+2)});
                td.click(function(_e){
                    var et = _e?_e:event;
                    et.stopPropagation(); 
                    return false;
                });
                dra.mousedown(function(_event){
                    //alert(_event.pageX);
                    _this._moveOffset = {index:$(this).attr("index"),y:_event.pageY,x:_event.pageX,tdwidth:td.width(),ntdwidth:td.next().width()};
                });
                $(_this._table).mouseup(function(){
                    _this._moveOffset=0;
                });
                $(_this._table).mousemove(function(_event){
    
                    
                    if(!_this._moveOffset)
                        return;
                    var _width = _event.pageX-_this._moveOffset.x;
                    
                    var dratd = theads[_this._moveOffset.index];
                    
                    $(dratd).css("width",_this._moveOffset.tdwidth+_width);
                    ntd = $(dratd).next();
                    ntd.css("width",_this._moveOffset.ntdwidth-_width);
                    var tdoffset = td.offset();
                    dra.css({height:td.height(),top:tdoffset.top+1,left:(tdoffset.left+td.width()+2)});
                });
            })
        },
        sort:function(index)
        {
            var isOrder = true;
            
            while(isOrder)
            {
                isOrder = false;
                for(var i = 0;i < this.data.length-1;i++)
                {
                    
                    if(!this.norder[index])
                    {
                        
                        if(this.data[i][index] > this.data[i+1][index])
                        {
                            var tmp = this.data[i];
                            this.data[i] = this.data[i+1];
                            this.data[i+1] = tmp;
                            isOrder = true;
                        }
                    }
                    else
                    {
                        
                        if(this.data[i][index] < this.data[i+1][index])
                        {
                            var tmp = this.data[i];
                            this.data[i] = this.data[i+1];
                            this.data[i+1] = tmp;
                            isOrder = true;
                        }
                    }
                }
            }
            if(!this.norder[index])
                this.norder[index] = true;
            else 
                this.norder[index] = false;
            
            this.fillTable();
        },
        fillTable:function()
        {
            var tbody = this._table.find("tbody")
            tbody.children().remove();
            for(var i = 0;i < this.data.length;i++)
            {
                var row = $("<tr></tr>").appendTo(tbody);
                for(var j = 0; j < this.data[i].length; j++)
                {
                    
                    row.append("<td >"+ this.data[i][j] +"</td>");
                }
            }
        }
    }