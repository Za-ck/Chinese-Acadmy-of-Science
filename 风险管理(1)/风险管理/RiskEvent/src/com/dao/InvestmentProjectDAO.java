package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.InvestmentProject;
import com.model.ProjectElement;

/**
 * A data access object (DAO) providing persistence and search support for
 * InvestmentProject entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.InvestmentProject
 * @author MyEclipse Persistence Tools
 */

public class InvestmentProjectDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(InvestmentProjectDAO.class);
	// property constants
	public static final String IP_NAME = "ipName";
	public static final String IP_EXPECTEDVALUE = "ipExpectedvalue";
	public static final String IP_MARK = "ipMark";
	public static final String IP_TIME = "ipTime";
	public static final String IP_VARIANCE = "ipVariance";
	public static final String IP_REGION1 = "ipRegion1";
	public static final String IP_REGION2 = "ipRegion2";
	public static final String IP_REGION3 = "ipRegion3";
	public static final String IP_REGION4 = "ipRegion4";
	public static final String IP_REGION5 = "ipRegion5";
	public static final String IP_REGION6 = "ipRegion6";
	public static final String IP_REGION7 = "ipRegion7";
	public static final String IP_REGION8 = "ipRegion8";
	public static final String IP_REGION9 = "ipRegion9";
	public static final String IP_REGION10 = "ipRegion10";

	protected void initDao() {
		// do nothing
	}

	public void save(InvestmentProject transientInstance) {
		log.debug("saving InvestmentProject instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InvestmentProject persistentInstance) {
		log.debug("deleting InvestmentProject instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InvestmentProject findById(java.lang.String id) {
		log.debug("getting InvestmentProject instance with id: " + id);
		try {
			InvestmentProject instance = (InvestmentProject) getHibernateTemplate()
					.get("com.model.InvestmentProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InvestmentProject instance) {
		log.debug("finding InvestmentProject instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding InvestmentProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InvestmentProject as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIpName(Object ipName) {
		return findByProperty(IP_NAME, ipName);
	}

	public List findByIpExpectedvalue(Object ipExpectedvalue) {
		return findByProperty(IP_EXPECTEDVALUE, ipExpectedvalue);
	}

	public List findByIpMark(Object ipMark) {
		return findByProperty(IP_MARK, ipMark);
	}

	public List findByIpTime(Object ipTime) {
		return findByProperty(IP_TIME, ipTime);
	}

	public List findByIpVariance(Object ipVariance) {
		return findByProperty(IP_VARIANCE, ipVariance);
	}

	public List findByIpRegion1(Object ipRegion1) {
		return findByProperty(IP_REGION1, ipRegion1);
	}

	public List findByIpRegion2(Object ipRegion2) {
		return findByProperty(IP_REGION2, ipRegion2);
	}

	public List findByIpRegion3(Object ipRegion3) {
		return findByProperty(IP_REGION3, ipRegion3);
	}

	public List findByIpRegion4(Object ipRegion4) {
		return findByProperty(IP_REGION4, ipRegion4);
	}

	public List findByIpRegion5(Object ipRegion5) {
		return findByProperty(IP_REGION5, ipRegion5);
	}

	public List findByIpRegion6(Object ipRegion6) {
		return findByProperty(IP_REGION6, ipRegion6);
	}

	public List findByIpRegion7(Object ipRegion7) {
		return findByProperty(IP_REGION7, ipRegion7);
	}

	public List findByIpRegion8(Object ipRegion8) {
		return findByProperty(IP_REGION8, ipRegion8);
	}

	public List findByIpRegion9(Object ipRegion9) {
		return findByProperty(IP_REGION9, ipRegion9);
	}

	public List findByIpRegion10(Object ipRegion10) {
		return findByProperty(IP_REGION10, ipRegion10);
	}

	public List findAll() {
		log.debug("finding all InvestmentProject instances");
		try {
			String queryString = "from InvestmentProject";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InvestmentProject merge(InvestmentProject detachedInstance) {
		log.debug("merging InvestmentProject instance");
		try {
			InvestmentProject result = (InvestmentProject) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InvestmentProject instance) {
		log.debug("attaching dirty InvestmentProject instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InvestmentProject instance) {
		log.debug("attaching clean InvestmentProject instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InvestmentProjectDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (InvestmentProjectDAO) ctx.getBean("InvestmentProjectDAO");
	}
	
	public List findAllGJOrderByName(String condition) {
		log.debug("finding all GJInvestmentProject instances order by name");
		try {
			String queryString = "from InvestmentProject as model where model.ipMark='国际投资项目' "+condition+" order by ipName,ipDepname";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all GJInvestmentProject failed", re);
			throw re;
		}
	}
	public List findAllGCOrderByName(String condition) {
		log.debug("finding all GJInvestmentProject instances order by name");
		try {
			String queryString = "from InvestmentProject as model where model.ipMark='总包项目' "+condition+" order by ipName,ipDepname";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all GJInvestmentProject failed", re);
			throw re;
		}
	}
	
	//删掉原来的旧项目
	public void deleteInvestmentProject(InvestmentProject investmentProject){
		   try{
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   //先删掉原来的评估项目
			   String deleteString="delete from InvestmentProject where ipName='"+investmentProject.getIpName()+"' and " +
			   		"ipUserId='"+investmentProject.getIpUserId()+"'";
			   Query queryDelete=session.createQuery(deleteString);
			   int ret=queryDelete.executeUpdate();;
			   trans.commit();	 
			   session.close();
		   }
		   catch(RuntimeException re)
		   {
			 //throw re;
				re.printStackTrace();
		   }
	}
	
	   
	   
	   
	   //查找所有项目名字
	   public List<String> findIpNameList(){
		   log.debug("finding all IpNameList");
			try {
				String queryString = "select distinct ipName from InvestmentProject as model where model.ipMark='国际投资项目' order by ipName";
				return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("finding all IpNameList failed", re);
				throw re;
			}
	   }
	   
	 //查找所有项目名字
	   public List<String> findContractIpNameList(){
		   log.debug("finding all IpNameList");
			try {
				String queryString = "select distinct ipName from InvestmentProject as model where model.ipMark='总包项目' order by ipName";
				return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("finding all IpNameList failed", re);
				throw re;
			}
	   }
	   
	   public boolean haveProjectByUserID(String projectName,String userID){
		   String queryString = "from InvestmentProject as model where model.ipMark='国际投资项目' and model.ipName='"+projectName+"' and model.ipUserId='"+userID+"'";
		   System.out.println("queryString*****************"+queryString);
		   if(getHibernateTemplate().find(queryString).size()>0){
			   return true;
		   };
		   return false;
	   }
	   public boolean haveContractProjectByUserID(String projectName,String userID){
		   String queryString = "from InvestmentProject as model where model.ipMark='总包项目' and model.ipName='"+projectName+"' and model.ipUserId='"+userID+"'";
		   System.out.println("queryString*****************"+queryString);
		   if(getHibernateTemplate().find(queryString).size()>0){
			   return true;
		   };
		   return false;
	   }
	   
	   public String findIdByProject(String projectName,String userID){
		   String queryString = "from InvestmentProject as model where model.ipMark='国际投资项目' and model.ipName='"+projectName+"' and model.ipUserId='"+userID+"'";
		   List<InvestmentProject> list=getHibernateTemplate().find(queryString);
		   return list.get(0).getIpId();
	   }
}