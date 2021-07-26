package com.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.model.RiskAssessSituation;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskAssessSituation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskAssessSituation
 * @author MyEclipse Persistence Tools
 */

public class RiskAssessSituationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskAssessSituationDAO.class);
	// property constants
	public static final String RA_DEP_ID = "raDepId";
	public static final String RA_DEP_NAME = "raDepName";
	public static final String RA_YEAR = "raYear";
	public static final String RA_QUARTER = "raQuarter";
	public static final String RA_NUMBER_DEAL = "raNumberDeal";
	public static final String RA_NUMBER_NEED = "raNumberNeed";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskAssessSituation transientInstance) {
		log.debug("saving RiskAssessSituation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
//public void update()
	public void delete(RiskAssessSituation persistentInstance) {
		log.debug("deleting RiskAssessSituation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskAssessSituation findById(java.lang.Integer id) {
		log.debug("getting RiskAssessSituation instance with id: " + id);
		try {
			RiskAssessSituation instance = (RiskAssessSituation) getHibernateTemplate()
					.get("com.model.RiskAssessSituation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(RiskAssessSituation instance) {
		log.debug("finding RiskAssessSituation instance by example");
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

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RiskAssessSituation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskAssessSituation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByRaDepId(Object raDepId) {
		return findByProperty(RA_DEP_ID, raDepId);
	}

	public List findByRaDepName(Object raDepName) {
		return findByProperty(RA_DEP_NAME, raDepName);
	}

	public List findByRaYear(Object raYear) {
		return findByProperty(RA_YEAR, raYear);
	}

	public List findByRaQuarter(Object raQuarter) {
		return findByProperty(RA_QUARTER, raQuarter);
	}

	public List findByRaNumberDeal(Object raNumberDeal) {
		return findByProperty(RA_NUMBER_DEAL, raNumberDeal);
	}

	public List findByRaNumberNeed(Object raNumberNeed) {
		return findByProperty(RA_NUMBER_NEED, raNumberNeed);
	}


	public RiskAssessSituation merge(RiskAssessSituation detachedInstance) {
		log.debug("merging RiskAssessSituation instance");
		try {
			RiskAssessSituation result = (RiskAssessSituation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskAssessSituation instance) {
		log.debug("attaching dirty RiskAssessSituation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskAssessSituation instance) {
		log.debug("attaching clean RiskAssessSituation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskAssessSituationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskAssessSituationDAO) ctx.getBean("RiskAssessSituationDAO");
	}
	
	
	/*-----------------------自己写的方法-------------------------*/


	public void update(String inputDepId, String inputDepName, String date, String plandate) 
	{
	    try{
	  		String year = date.substring(0, 4);
	  		int quarter = month2Quarter(date.substring(5, 7));
	  		int planquarter = month2Quarter(plandate.substring(5, 7));
	  		String pyear = plandate.substring(0, 4);	  		
	  		// 如果是延后跨季度处理,则不需要更新planQuarter,提前处理则不然
	  		if((year+quarter).compareTo((pyear+planquarter)) < 0) {
	  			// 先更新planQuarter
				List<RiskAssessSituation> plist = getSituationList(inputDepId,pyear,planquarter + "");
				if(plist != null && plist.size() > 0) {	
					RiskAssessSituation pras = plist.get(0);
					int inputnum = pras.getRaNumberNeed();
					pras.setRaNumberNeed(inputnum - 1);
					merge(pras);
				}
				
	  		}		    
			// 再更新Quarter
			List<RiskAssessSituation> list = getSituationList(inputDepId,year,quarter + "");
			// 如果list不为空,则要更新RiskAssessSituation表中的记录,将该记录实际应对的风险事件的数目加1
			if(list != null && list.size() > 0) {				
				RiskAssessSituation ras = list.get(0);
				int dealnum = ras.getRaNumberDeal();
				ras.setRaNumberDeal(dealnum + 1);
				merge(ras);				
			}
			else {
				RiskAssessSituation ras = new RiskAssessSituation();
				ras.setRaDepId(inputDepId);
				ras.setRaDepName(inputDepName);
				ras.setRaNumberDeal(1);
				ras.setRaNumberNeed(0);
				ras.setRaYear(year);
				ras.setRaQuarter(quarter + "");
				save(ras);
			}			
	  	}
	  	catch(RuntimeException re)
	  	{
	  		throw re;
	  	}
	}
	
	// 根据depId,year,quarter查表
	public List<RiskAssessSituation> getSituationList(String depId, String year, String quarter) {
		
		final String queryString = "FROM RiskAssessSituation WHERE raDepId='" + depId + "' AND raQuarter='"+ quarter + "' AND raYear='"+ year +"'";
		//System.out.println(queryString);
		List<RiskAssessSituation> list = getHibernateTemplate().execute( new HibernateCallback<List<RiskAssessSituation>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<RiskAssessSituation> doInHibernate(Session session) throws HibernateException, SQLException {
				List<RiskAssessSituation> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		
		return list;
	}
	
	private int month2Quarter(String month) {
		
		try {
			int monthInt = Integer.parseInt(month);
			int quarter = (monthInt - 1) / 3;
			return (quarter + 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		
	}

	public List checkDeal(String planyear, String planquarter, String depIdDeal) {
		final String queryString = "FROM RiskAssessSituation WHERE raDepId='" + depIdDeal + "' AND raQuarter='"+ planquarter + "' AND raYear='"+ planyear +"'";
		List<RiskAssessSituation> list = getHibernateTemplate().execute( new HibernateCallback<List<RiskAssessSituation>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<RiskAssessSituation> doInHibernate(Session session) throws HibernateException, SQLException {
				List<RiskAssessSituation> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
	
}