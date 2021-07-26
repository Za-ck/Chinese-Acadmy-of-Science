package com.services;

import java.util.LinkedList;
import java.util.List;

import com.dao.SystemRoleDAO;
import com.model.SystemRole;

class Selectinfo{
	private String select;
	private String selectinfo;
	private String selectinfo2;
	public String getSelect() {
		return select;
	}

	public String getSelectinfo() {
		return selectinfo;
	}

	public void setSelectinfo(String selectinfo) {
		this.selectinfo = selectinfo;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getSelectinfo2() {
		return selectinfo2;
	}

	public void setSelectinfo2(String selectinfo2) {
		this.selectinfo2 = selectinfo2;
	}
	
}

public class SelectList {
	
	private List<Selectinfo> roleList;
	private SystemRoleDAO systemRoledao;
	
	
	public SystemRoleDAO getSystemRoledao() {
		return systemRoledao;
	}
	public void setSystemRoledao(SystemRoleDAO systemRoledao) {
		this.systemRoledao = systemRoledao;
	}
	@SuppressWarnings("unchecked")
	public List<Selectinfo> getRoleList() {
		//System.out.println("...........");
		List<SystemRole> temp=new LinkedList<SystemRole>();
		temp=systemRoledao.findAll();
		//System.out.println(temp.size());
		roleList=new LinkedList<Selectinfo>();
		for (SystemRole systemRole : temp) {
			Selectinfo s= new Selectinfo();
			s.setSelect(systemRole.getSrId().toString());
			s.setSelectinfo(systemRole.getSrName());
			//System.out.println(systemRole.getSrId());
			roleList.add(s);
		}
		//System.out.println("role"+roleList.size());
		return roleList;
	}
	public void setRoleList(List<Selectinfo> RoleList) {
		roleList = RoleList;
	}
	
	
}
