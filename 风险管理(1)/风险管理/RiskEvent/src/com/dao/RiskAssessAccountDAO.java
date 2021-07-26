package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.model.RiskAssessAccount;





/**
 * A data access object (DAO) providing persistence and search support for
 * RiskAssessAccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskAssessAccount
 * @author MyEclipse Persistence Tools
 */

public class RiskAssessAccountDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskAssessAccountDAO.class);
	// property constants
	public static final String RA_DEP_ID = "raDepId";
	public static final String RA_DEP_NAME = "raDepName";
	public static final String RA_YEAR = "raYear";
	public static final String RA_QUARTER = "raQuarter";
	public static final String RA_MONTH = "raMonth";
	public static final String RA_NUMBER_INPUT = "raNumberInput";
	public static final String RA_NUMBER_DEAL = "raNumberDeal";
	public static final String RA_ASSESS_RESULT_MONTH = "raAssessResultMonth";
	public static final String RA_DIM_ID = "raDimId";
	public static final String RA_DIM_NAME = "raDimName";
	public static final String RA_REMARK1 = "raRemark1";
	public static final String RA_REMARK2 = "raRemark2";
	public static final String RA_DEP_PROPERTY = "raDepProperty";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskAssessAccount transientInstance) {
		log.debug("saving RiskAssessAccount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskAssessAccount persistentInstance) {
		log.debug("deleting RiskAssessAccount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskAssessAccount findById(java.lang.Integer id) {
		log.debug("getting RiskAssessAccount instance with id: " + id);
		try {
			RiskAssessAccount instance = (RiskAssessAccount) getHibernateTemplate()
					.get("com.model.RiskAssessAccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskAssessAccount instance) {
		log.debug("finding RiskAssessAccount instance by example");
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
		log.debug("finding RiskAssessAccount instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskAssessAccount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

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

	public List findByRaMonth(Object raMonth) {
		return findByProperty(RA_MONTH, raMonth);
	}

	public List findByRaNumberInput(Object raNumberInput) {
		return findByProperty(RA_NUMBER_INPUT, raNumberInput);
	}

	public List findByRaNumberDeal(Object raNumberDeal) {
		return findByProperty(RA_NUMBER_DEAL, raNumberDeal);
	}

	public List findByRaAssessResultMonth(Object raAssessResultMonth) {
		return findByProperty(RA_ASSESS_RESULT_MONTH, raAssessResultMonth);
	}

	public List findByRaDimId(Object raDimId) {
		return findByProperty(RA_DIM_ID, raDimId);
	}

	public List findByRaDimName(Object raDimName) {
		return findByProperty(RA_DIM_NAME, raDimName);
	}

	public List findByRaRemark1(Object raRemark1) {
		return findByProperty(RA_REMARK1, raRemark1);
	}

	public List findByRaRemark2(Object raRemark2) {
		return findByProperty(RA_REMARK2, raRemark2);
	}

	public List findByRaDepProperty(Object raDepProperty) {
		return findByProperty(RA_DEP_PROPERTY, raDepProperty);
	}

	public List findAll() {
		log.debug("finding all RiskAssessAccount instances");
		try {
			String queryString = "from RiskAssessAccount";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskAssessAccount merge(RiskAssessAccount detachedInstance) {
		log.debug("merging RiskAssessAccount instance");
		try {
			RiskAssessAccount result = (RiskAssessAccount) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskAssessAccount instance) {
		log.debug("attaching dirty RiskAssessAccount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskAssessAccount instance) {
		log.debug("attaching clean RiskAssessAccount instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskAssessAccountDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskAssessAccountDAO) ctx.getBean("RiskAssessAccountDAO");
	}
	public List CheckInput(String year1, String month1, String depId) {
		try {
			final String queryString = "FROM RiskAssessAccount as model WHERE model.raDepId='"
					+ depId + "' AND model.raMonth='" + month1 + "' AND model.raYear='"
					+ year1 + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			//log.error("attach failed", re);
			//throw re;
			
			List<RiskAssessAccount> checkinput=new LinkedList<RiskAssessAccount>();
			return checkinput;
			//re.printStackTrace();
		}
	}
	
	public void updateDepProperty(String depId, String depSort) {
		
		final String queryString2 = "UPDATE RiskAssessAccount set raDepProperty='"+depSort+"' where raDepId='" + depId + "'";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString2).executeUpdate();
				session.close();
				return result;
			}
			
		});
		
	}
	
}