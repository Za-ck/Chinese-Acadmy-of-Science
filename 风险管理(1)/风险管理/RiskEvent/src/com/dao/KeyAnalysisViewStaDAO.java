package com.dao;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.AllAnalysisView;
import com.model.KeyAnalysisView;
import com.model.ReportView;
import com.model.ReportViewId;

/**
 * A data access object (DAO) providing persistence and search support for
 * AllAnalysisView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.KeyAnalysisView
 * @author MyEclipse Persistence Tools
 */

public class KeyAnalysisViewStaDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(KeyAnalysisViewStaDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public List findDepByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisViewSta instances");
		try {
			String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisViewSta as model "
					+ condition + " order by model.depQueue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskAdminByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisViewSta instances");
		try {
			String queryString = "select distinct reCreator from KeyAnalysisViewSta as model "
					+ condition;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAdminTimeByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisViewSta instances");
		try {
			String queryString = "select distinct SUBSTRING(reDate,0,11) from KeyAnalysisViewSta as model "
					+ condition;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskVerifierByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisViewSta instances");
		try {
			String queryString = "select distinct inputLeader from KeyAnalysisViewSta as model "
					+ condition;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findVerifyTimeByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisViewSta instances");
		try {
			String queryString = "select distinct SUBSTRING(verifyTime,0,11) from KeyAnalysisViewSta as model "
					+ condition;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}