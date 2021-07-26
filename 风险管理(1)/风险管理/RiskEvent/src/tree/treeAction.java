package tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class treeAction extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	private String id;      //树形的Id
	   private String nodeId;  //节点id
	   private String pid;     //节点上级节点
	   private String nodeName="";//节点名称 
	   private String url="#";     //节点的链接 
       private String title="";   //节点标题 
	   private String target="#";  //节点链接目标 
	   private String icon="";    //节点图标 
	   private String iconOpen="";//打开的图标 
	   private String open="false";    //节点是否打开 
	   private String useCheckbox="false";//是否使用复选框 
	   private String linefeed="false";//图标和文字换行 
	   private String turnfeed="false";//整个节点换行是否换行 
	   private String dataProvider;//数据集 
	   private JSONArray pagelist=null;        //表绑定数据
	   private String rightclick="false";//是否使用右键 
	   private String addUsed="false";//是否使用右键添加功能 
	   private String addLink="#";//添加的链接 
	   private String deleteUsed="false";//是否使用右键删除 
	   private String deleteLink="#";//删除的链接 
	   private String updateUsed="false";//是否使用修改功能 
	   private String updateLink="#";//修改链接 
	   private String dataArray;
	   private String bgcolor="#fffefd";//弹出框背景色
	   private String rightWidht="80px";
	   private String rightHeight="25px";
	   private String checkedState=""; //checkbox中从数据源中判断是否被选中的成员变量
	   
	  
	   
	public String getCheckedState() {
		return checkedState;
	}
	public void setCheckedState(String checkedState) {
		this.checkedState = checkedState;
	}
	public String getRightWidht() {
		return rightWidht;
	}
	public void setRightWidht(String rightWidht) {
		this.rightWidht = rightWidht;
	}
	public String getRightHeight() {
		return rightHeight;
	}
	public void setRightHeight(String rightHeight) {
		this.rightHeight = rightHeight;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getDataArray() {
		return dataArray;
	}
	public void setDataArray(String dataArray) {
		this.dataArray = dataArray;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getUseCheckbox() {
		return useCheckbox;
	}
	public void setUseCheckbox(String useCheckbox) {
		this.useCheckbox = useCheckbox;
	}
	public String getLinefeed() {
		return linefeed;
	}
	public void setLinefeed(String linefeed) {
		this.linefeed = linefeed;
	}
	public String getTurnfeed() {
		return turnfeed;
	}
	public void setTurnfeed(String turnfeed) {
		this.turnfeed = turnfeed;
	}
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	public String getRightclick() {
		return rightclick;
	}
	public void setRightclick(String rightclick) {
		this.rightclick = rightclick;
	}
	public String getAddUsed() {
		return addUsed;
	}
	public void setAddUsed(String addUsed) {
		this.addUsed = addUsed;
	}
	public String getAddLink() {
		return addLink;
	}
	public void setAddLink(String addLink) {
		this.addLink = addLink;
	}
	public String getDeleteUsed() {
		return deleteUsed;
	}
	public void setDeleteUsed(String deleteUsed) {
		this.deleteUsed = deleteUsed;
	}
	public String getDeleteLink() {
		return deleteLink;
	}
	public void setDeleteLink(String deleteLink) {
		this.deleteLink = deleteLink;
	}
	public String getUpdateUsed() {
		return updateUsed;
	}
	public void setUpdateUsed(String updateUsed) {
		this.updateUsed = updateUsed;
	}
	public String getUpdateLink() {
		return updateLink;
	}
	public void setUpdateLink(String updateLink) {
		this.updateLink = updateLink;
	}
	
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return Tag.EVAL_PAGE;
	}
	
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		JspWriter out= pageContext.getOut();
		try{
			StringBuffer contents = new StringBuffer();
			contents.append("<script type=\"text/javascript\">\n");
			contents.append("d = new dTree('d');\n");
			if(this.useCheckbox.equalsIgnoreCase("true"))
				contents.append("d.config.useCheckbox = true;\n");
			if(this.linefeed.equalsIgnoreCase("true"))
				contents.append("d.config.linefeed = true;\n");
			if(this.turnfeed.equalsIgnoreCase("true"))
				contents.append("d.config.linefeed = true;\n");		
			if(this.rightclick.equalsIgnoreCase("true"))
				contents.append("d.config.rightClick=true;\n");
			    contents.append(getTreeBody());			   
			    contents.append("document.write(d);\n");
			    contents.append(getTreeCheck());
			    contents.append(" function addnewTreeNode(){");
			    contents.append(" window.location.href='"+this.addLink+"?"+this.nodeId+"='+document.getElementById('selectItem').value;");
			    contents.append(" }");
			    
			    contents.append(" function updateTreeNode(){");
			    contents.append(" window.location.href='"+this.updateLink+"?updateFlag=update&"+nodeId+"='+document.getElementById('selectItem').value;");
			    contents.append(" }");
			    
			    contents.append(" function deleteTreeNode(){");
			    contents.append(" window.location.href='"+this.deleteLink+"?"+this.nodeId+"='+document.getElementById('selectItem').value;");
			    contents.append(" }");
			    contents.append("</script>\n");
			    out.print(contents.toString());
			    out.println(insertDiv());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}
	
	   public String getTreeCheck(){
		   StringBuffer treeBody = new StringBuffer();
		   try{
			if(this.pagelist!=null){	
		    for(int i=0; i<this.pagelist.size(); i++){
			JSONObject t = pagelist.getJSONObject(i);
			String nodeid=t.getString(this.nodeId);
		   if(this.useCheckbox.equalsIgnoreCase("true")&&!this.checkedState.equals("")&&!t.getString(this.pid).equals("-1"))
		     {				  
			    if(t.getString(this.checkedState).equalsIgnoreCase("true"))				 
			    treeBody.append("document.getElementById('"+nodeid+"').checked=true;\n");			   
			 }					
		    }		
			}
	   } catch(Exception ex){
		   ex.printStackTrace();
		   return "";
	   }
	   return treeBody.toString();
	   }
	   
	   public String getTreeBody(){
		   StringBuffer treeBody = new StringBuffer();
		   try{
			   Object obj;
			   try {
				obj = ServletActionContext.getRequest().getAttribute(
						dataProvider);
				ServletActionContext.getRequest().removeAttribute(dataProvider);
			} catch (Exception e) {
				// TODO: handle exception
				obj=null;
			}
			if(obj==null) this.pagelist=null;
			else this.pagelist=JSONArray.fromObject(obj);
				if(this.pagelist!=null){		
			for(int i=0; i<this.pagelist.size(); i++){
			JSONObject t = pagelist.getJSONObject(i);
			String nodeid=t.getString(this.nodeId);
			treeBody.append("d.add('"+nodeid+"',");//id
			treeBody.append("'"+t.getString(this.pid)+"',");//上级节点
			if(!this.nodeName.equals(""))
			treeBody.append("'"+t.getString(this.nodeName)+"',");
			else treeBody.append("'',");
			if(!this.url.equals("#"))
			treeBody.append("'"+t.getString(this.url)+"',");
			else treeBody.append("'',");
			if(!this.title.equals(""))
			treeBody.append("'"+t.getString(this.title)+"',");
			else treeBody.append("'',");
			if(!this.target.equals("#"))
			treeBody.append("'"+t.getString(this.target)+"',");
			else treeBody.append("'',");
			if(!this.icon.equals(""))
			treeBody.append("'"+this.icon+"',");
			else treeBody.append("'',");
			if(!this.iconOpen.equals(""))
			treeBody.append("'"+this.iconOpen+"',");
			else treeBody.append("'',");			
			if(!this.open.equalsIgnoreCase("false"))
			treeBody.append("true");
			else treeBody.append("false");
			treeBody.append(");\n");						
			}
				}
		   }
		   catch(Exception ex){
			   ex.printStackTrace();
			   return "";
		   }
		   return treeBody.toString();
	   }
	   
	   public String insertDiv(){
		   StringBuffer divBody = new StringBuffer();
		   divBody.append("<input type = \"hidden\" id=\"selectItem\">\n");
		   divBody.append("<div id=\"itemMenu\" style=\"display:none\">\n");
		   if(this.rightclick.equalsIgnoreCase("true"))
		   divBody.append("<table border='0' cellspacing=\"0\" bgcolor='"+this.bgcolor+"' width='"+this.rightWidht+"'>");
		   if(this.addUsed.equalsIgnoreCase("true"))
		   divBody.append("<tr><td  height='"+this.rightHeight+"' align=\"center\" style='border-bottom:1px inset #c3d5ee;'><a style='cursor:pointer;font-family:Verdana, Arial, Helvetica, sans-serif;font-size: 11;color: black;text-decoration: none;' onclick='parent.addnewTreeNode()' >新增</a></td></tr>\n");
		   if(this.updateUsed.equalsIgnoreCase("true"))
		   divBody.append("<tr><td height='"+this.rightHeight+"' align=\"center\" style='border-bottom:1px inset #c3d5ee;'><a style='cursor:pointer;font-family:Verdana, Arial, Helvetica, sans-serif;font-size: 11;color: black;text-decoration: none;' onclick='parent.updateTreeNode()' >修改</a></td></tr>\n");
		   if(this.deleteUsed.equalsIgnoreCase("true"))
		   divBody.append("<tr><td height='"+this.rightHeight+"' align=\"center\" style='border-bottom:1px inset #c3d5ee;'><a style='cursor:pointer;font-family:Verdana, Arial, Helvetica, sans-serif;font-size: 11;color: black;text-decoration: none;' onclick='parent.deleteTreeNode()' >删除</a></td></tr></table></div>\n");		   
		   return divBody.toString();
	   }
}
