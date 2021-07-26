package com.dao;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.model.UsersFunction;

public class UsersFunctionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
	.getLogger(UsersFunctionDAO.class);
	protected void initDao() {
		// do nothing
	}
	public List findAll() {
		log.debug("finding all UsersFunction instances");
		try {
			String queryString = "from UsersFunction";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public UsersFunction findById(java.lang.Integer id) {
		log.debug("getting UsersFunction instance with id: " + id);
		try {
			UsersFunction instance = (UsersFunction) getHibernateTemplate()
					.get("com.zn.model.UsersFunction", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findUsersFunction(Integer roleid) {
		log.debug("finding all UsersFunction instances");
		try {
			String queryString = "from UsersFunction as usersfunction where usersfunction.id.srId = '"+roleid+"' order by usersfunction.id.fmCategory,usersfunction.id.fmId";
			List<UsersFunction> list=new LinkedList<UsersFunction>();
			list=getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}
