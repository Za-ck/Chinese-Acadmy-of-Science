package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts2.ServletActionContext;

import com.model.UsersFunction;

public class levelAction extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String level;
	private String imagelist = "";

	public String getImagelist() {
		return imagelist;
	}

	public void setImagelist(String imagelist) {
		this.imagelist = imagelist;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		JspWriter out = pageContext.getOut();
		try {
			if (level.equalsIgnoreCase("1"))
				out.print(GetUsersPrimaryFunction());
			else if (level.equalsIgnoreCase("2"))
				out.print(GetUsersJuniorFunction());
			else if (level.equalsIgnoreCase("3"))
				out.print(GetUsersThirdFunction());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	/*
	 * 获取一级权限
	 */
	@SuppressWarnings("unchecked")
	public String GetUsersPrimaryFunction() {
		StringBuffer functions = new StringBuffer();
		List<UsersFunction> UFList = (List<UsersFunction>) ServletActionContext
				.getRequest().getSession().getAttribute("UFList");
		if (UFList == null)
			return "";
		for (int i = 0; i < UFList.size(); i++) {
			if (UFList.get(i).getId().getFmAction().equals("#")) {
				functions.append("<a id=\"menuLevel"
						+ UFList.get(i).getId().getfmId()
						+ "\" href=\"#\" onClick=\"callTreeMenu("
						+ UFList.get(i).getId().getfmId() + ",'"
						+ UFList.get(i).getId().getFmName() + "')\"><span>"
						+ UFList.get(i).getId().getFmName() + "</span></a>\n");
			}
		}
		return functions.toString();
	}

	/*
	 * 获取二级权限，Middle左边部分菜单,二期代码,GetUsersJuniorFunction_2nd
	 */
	@SuppressWarnings("unchecked")
	public String GetUsersJuniorFunction() 
	{
		
		StringBuffer functions = new StringBuffer();
		List<UsersFunction> UFList = (List<UsersFunction>) ServletActionContext
				.getRequest().getSession().getAttribute("UFList");
		if (UFList == null)
			return "";
		for (int i = 0; i < UFList.size(); i++) 
		{
			if (UFList.get(i).getId().getFmAction().equals("#")) 
			{
				int comp = UFList.get(i).getId().getfmId();
				functions.append("<ul id=\"menuUl_" + comp
						+ "\" style=\"display: none;margin:10px;\">\n");
				for (int j = 0; j < UFList.size(); j++) 
				{
					if (UFList.get(j).getId().getFmCategory() == comp)
					{
						int comp2 = UFList.get(j).getId().getfmId();
						if(UFList.get(j).getId().getFmAction().equals("##"))
						{
							functions.append("<li ><a onclick=\"changeColorUl(" + comp
									+ "," + comp2
									+ ")\" id=\"menuA_"
									+ comp2
									+ "\" href=\"#\" ><span>"
									+ UFList.get(j).getId().getFmName()
									+ "</span></a></li>\n");
							//三级菜单：<ul id="menuUl_110_010"style="display: none;">
							functions.append("<ul id=\"menuUl_" + comp2
									+ "\" style=\"display: none;margin:10px;\">\n");
							for (int k = 0; k < UFList.size(); k++) 
							{
								if (UFList.get(k).getId().getFmCategory() == comp2)
								{
									int comp3 = UFList.get(k).getId().getfmId();
//									functions.append("<li onclick=\"changeColorA(" + comp2
//											+ "," + comp3
//											+ ")\"><a id=\"menuA_"
//											+ comp3
//											+ "\" href=\""
//											+ UFList.get(k).getId().getFmAction()
//											+ "\" target=\"mainFrame\"><span>"
//											+ UFList.get(k).getId().getFmName()
//											+ "</span></a></li>\n");
									functions.append("<li onclick=\"changeColorA(" + comp2
											+ "," + comp3
											+ ")\"><a id=\"menuA_"
											+ comp3
											+ "\" onclick=\"openTab('"
											+ UFList.get(k).getId().getFmAction() + "','" + UFList.get(k).getId().getFmName()
											+ "')\"><span>"
											+ UFList.get(k).getId().getFmName()
											+ "</span></a></li>\n");
									//System.out.println(functions);
								}
							}
							functions.append("</ul>\n");
							 //<li onclick="changeColor(110,110010)"><a id="menuA_110010" href="#" ><span>风险流转</span></a></li>
						}
						else
						{
//							functions.append("<li onclick=\"changeColorA(" + comp
//									+ "," + comp2
//									+ ")\"><a id=\"menuA"
//									+ comp2
//									+ "\" href=\""
//									+ UFList.get(j).getId().getFmAction()
//									+ "\" target=\"mainFrame\"><span>"
//									+ UFList.get(j).getId().getFmName()
//									+ "</span></a></li>\n");
							functions.append("<li onclick=\"changeColorA(" + comp
									+ "," + comp2
									+ ")\"><a id=\"menuA_"
									+ comp2
									+ "\" onclick=\"openTab('"
									+ UFList.get(j).getId().getFmAction() + "','" + UFList.get(j).getId().getFmName()
									+ "')\"><span>"
									+ UFList.get(j).getId().getFmName()
									+ "</span></a></li>\n");
							//System.out.println(functions);
						}
						
					}
				}
				functions.append("</ul>\n");
			}
		}
		return functions.toString();
	}
	
	
	
	/*
	 * 获取二级权限，Middle左边部分菜单，一期代码
	 */
	/*@SuppressWarnings("unchecked")
	public String GetUsersJuniorFunction_1nd() 
	{
		StringBuffer functions = new StringBuffer();
		List<UsersFunction> UFList = (List<UsersFunction>) ServletActionContext
				.getRequest().getSession().getAttribute("UFList");
		if (UFList == null)
			return "";
		for (int i = 0; i < UFList.size(); i++) 
		{
			if (UFList.get(i).getId().getFmAction().equals("#")) {
				int comp = UFList.get(i).getId().getfmId();
				functions.append("<ul id=\"menu" + comp
						+ "\"style=\"display: none;\">\n");
				for (int j = 0; j < UFList.size(); j++) {
					if (UFList.get(j).getId().getFmCategory() == comp)
						functions.append("<li onclick=\"changeColor(" + comp
								+ "," + UFList.get(j).getId().getfmId()
								+ ")\"><a id=\"menu"
								+ UFList.get(j).getId().getfmId()
								+ "\" href=\""
								+ UFList.get(j).getId().getFmAction()
								+ "\"target=\"mainFrame\"><span>"
								+ UFList.get(j).getId().getFmName()
								+ "</span></a></li>\n");
				}
				functions.append("</ul>\n");
			}
		}
		return functions.toString();
	}*/

	/*
	 * 获取三级权限，Middle左边部分菜单
	 */
	@SuppressWarnings("unchecked")
	public String GetUsersThirdFunction() {
		StringBuffer functions = new StringBuffer();
		List<UsersFunction> UFList = (List<UsersFunction>) ServletActionContext
				.getRequest().getSession().getAttribute("UFList");
		if (UFList == null)
			return "";
		for (int i = 0; i < UFList.size(); i++) {
			if (UFList.get(i).getId().getFmAction().equals("#")) {
				int comp = UFList.get(i).getId().getfmId();
				functions.append("<ul id=\"imgmenu" + comp
						+ "\"style=\"display: none;\">\n");
				for (int j = 0; j < UFList.size(); j++) {
					if (UFList.get(j).getId().getFmCategory() == comp)
						functions.append("<li><a href=\""
								+ UFList.get(j).getId().getFmAction()
								+ "\" > <img src=\""
								+ UFList.get(j).getId().getImages()
								+ "\"/><span>"
								+ UFList.get(j).getId().getFmName()
								+ "</span></a></li>\n");
				}
				functions.append("</ul>\n");
			}
		}
		return functions.toString();
	}
}
