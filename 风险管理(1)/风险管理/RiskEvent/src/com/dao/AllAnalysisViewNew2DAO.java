package com.dao;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.RuleFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * AllAnalysisView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.AllAnalysisViewNew
 * @author MyEclipse Persistence Tools
 */

public class AllAnalysisViewNew2DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AllAnalysisViewNew2DAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}


	public List findFile(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisViewNew2 instances");
		try {
			 String queryString = "select fileName,count(*) from AllAnalysisViewNew2 as model " +
			 condition+" and fileName != ''"+" GROUP BY fileName";
			 //System.out.println("sql*********"+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	
	public List<RuleFile> findFlowRuleFileList(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisViewNew2 instances");
		try {
			 String queryString = "select fileName,count(*) from AllAnalysisViewNew2 as model " +
			 condition+" and fileName != ''"+" GROUP BY fileName";
			 List<RuleFile> ruleFileList=new LinkedList<RuleFile>();
			 List fileList=getHibernateTemplate().find(queryString);
			 for (Object file : fileList) {
				 Object[] files=(Object[])file;
				 RuleFile ruleFile=new RuleFile();
				 ruleFile.setRuleName((String)files[0]);
				 ruleFile.setEventNum((Long)files[1]+"");
				 ruleFileList.add(ruleFile);
			}
			 return ruleFileList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
}