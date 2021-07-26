package com.action.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.dao.UsersDAO;
import com.model.Users;

public class personAction {
	private String userId;
	private String userIdcard;
	private String userTel;
	private String userEmail;
	private String userPassword;
	private String newPassword;
	private String confirmPassword;
	private String depName;
	Users user = new Users();
	private UsersDAO userdao;
	
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UsersDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UsersDAO userdao) {
		this.userdao = userdao;
	}

	// 显示个人信息
	public String personInfoQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userid");
		user = userdao.findById(userId);
		depName = session.getAttribute("userdep").toString();
		if (user == null) {
			return "fail";
		}// 如果员工编号不存在，则直接退出

		else
			return "success";
	}

	// 修改个人信息
	public String personInfoSave() {
		user.setUserId(this.userId);
		user.setUserIdcard(this.userIdcard);
		user.setUserEmail(this.userEmail);
		user.setUserTel(this.userTel);
		if (userdao.merge(user) == null) {// 如果修改不成功，则返回"fail";
			return "fail";
		} else {
			return "success";
		}

	}

	// 查看个人密码
	public String passwordQuery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		userId = (String) session.getAttribute("userid");
		user = userdao.findById(userId);
		if (user == null) {
			return "fail";
		}// 如果员工编号不存在，则直接退出
		else {
			this.userPassword = userdao.findById(userId).getUserPassword();
			return "success";
		}
	}

	// 修改个人密码
	public String passwordSave() {
		if (!user.getUserPassword().equals(this.userPassword))
			return "fail";
		if (!(this.newPassword.equals(this.confirmPassword)))
			return "fail";
		user.setUserId(this.userId);
		user.setUserPassword(this.newPassword);
		if (userdao.merge(user) == null) {// 如果修改不成功，则返回"fail";
			return "fail";
		} else {
			this.userPassword = userdao.findById(this.userId).getUserPassword();
			return "success";
		}
	}

}
