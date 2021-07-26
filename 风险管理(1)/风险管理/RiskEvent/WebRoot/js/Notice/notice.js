 /*
     * ����
     */
 function tips_pop(){
var MsgPop=document.getElementById("winpop");//��ȡ�����������,��IDΪwinpop�Ķ���
var popH=parseInt(MsgPop.style.height);//��parseInt������ĸ߶�ת��Ϊ����,�Է�������Ƚ�
if (popH==0){         //���ڵĸ߶���0
MsgPop.style.display="block";//��ô�����صĴ�����ʾ����
show=setInterval("changeH('up')",2);//��ʼ��ÿ0.002����ú���changeH("up"),��ÿ0.002�������ƶ�һ��
}
else {         //����
hide=setInterval("changeH('down')",2);//��ʼ��ÿ0.002����ú���changeH("down"),��ÿ0.002�������ƶ�һ��
}
}
function changeH(str) {
var MsgPop=document.getElementById("winpop");
var popH=parseInt(MsgPop.style.height);
if(str=="up"){     //������������UP
if (popH<=100){    //���ת��Ϊ��ֵ�ĸ߶�С�ڵ���100
MsgPop.style.height=(popH+4).toString()+"px";//�߶�����4������
}
else{
clearInterval(show);//�����ȡ������������,��˼�������߶ȳ���100�����,�Ͳ���������
}
}
if(str=="down"){
if (popH>=4){       //������������down
MsgPop.style.height=(popH-4).toString()+"px";//��ô���ڵĸ߶ȼ���4������
}
else{        //����
clearInterval(hide);    //�����ȡ������������,��˼�������߶�С��4����ȵ�ʱ��,�Ͳ��ټ���
MsgPop.style.display="none";  //��Ϊ�����б߿�,���Ի��ǿ��Կ���1~2����û����ȥ,��ʱ��Ͱ�DIV���ص�
}
}
}
window.onload=function(){    //����
document.getElementById('winpop').style.height='0px';//�Ҳ�֪��ΪʲôҪ��ʼ������߶�,CSS�ﲻ���Ѿ���ʼ������,֪���ĸ�����һ��
setTimeout("tips_pop()",800);     //3������tips_pop()�������
}
    