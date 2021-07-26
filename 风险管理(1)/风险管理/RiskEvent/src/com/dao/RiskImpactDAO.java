package com.dao;

import com.model.RiskImpact;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskImpact entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.RiskImpact
 * @author MyEclipse Persistence Tools
 */

public class RiskImpactDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskImpactDAO.class);
	// property constants
	public static final String RI_KPI = "riKpi";
	public static final String RI_PRODEGREE = "riProdegree";
	public static final String RI_PROBABILITY = "riProbability";
	public static final String RI_BUSAREA = "riBusarea";
	public static final String RI_SOURCE = "riSource";
	public static final String RI_FLOW = "riFlow";
	public static final String RI_INCHARGEDEP = "riInchargedep";
	public static final String RI_FINANCE = "riFinance";
	public static final String RI_MONEY = "riMoney";
	public static final String RI_FAME = "riFame";
	public static final String RI_LAW = "riLaw";
	public static final String RI_CLIENT = "riClient";
	public static final String RI_EMPLOYEE = "riEmployee";
	public static final String RI_OPERATION = "riOperation";
	public static final String RI_SAFE = "riSafe";
	public static final String RI_FINDEGREE = "riFindegree";
	public static final String RI_FINCRITERIA = "riFincriteria";
	public static final String RI_FAMEDEGREE = "riFamedegree";
	public static final String RI_FAMECRITERIA = "riFamecriteria";
	public static final String RI_LAWDEGREE = "riLawdegree";
	public static final String RI_LAWCRITERIA = "riLawcriteria";
	public static final String RI_CLIDEGREE = "riClidegree";
	public static final String RI_CLICRITERIA = "riClicriteria";
	public static final String RI_EMPDEGREE = "riEmpdegree";
	public static final String RI_EMPCRITERIA = "riEmpcriteria";
	public static final String RI_OPEDEGREE = "riOpedegree";
	public static final String RI_OPECRITERIA = "riOpecriteria";
	public static final String RI_SAFEDEGREE = "riSafedegree";
	public static final String RI_SAFECRITERIA = "riSafecriteria";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskImpact transientInstance) {
		log.debug("saving RiskImpact instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskImpact persistentInstance) {
		log.debug("deleting RiskImpact instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskImpact findById(java.lang.String id) {
		log.debug("getting RiskImpact instance with id: " + id);
		try {
			RiskImpact instance = (RiskImpact) getHibernateTemplate().get(
					"com.model.RiskImpact", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskImpact instance) {
		log.debug("finding RiskImpact instance by example");
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
		log.debug("finding RiskImpact instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RiskImpact as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRiKpi(Object riKpi) {
		return findByProperty(RI_KPI, riKpi);
	}

	public List findByRiProdegree(Object riProdegree) {
		return findByProperty(RI_PRODEGREE, riProdegree);
	}

	public List findByRiProbability(Object riProbability) {
		return findByProperty(RI_PROBABILITY, riProbability);
	}

	public List findByRiBusarea(Object riBusarea) {
		return findByProperty(RI_BUSAREA, riBusarea);
	}

	public List findByRiSource(Object riSource) {
		return findByProperty(RI_SOURCE, riSource);
	}

	public List findByRiFlow(Object riFlow) {
		return findByProperty(RI_FLOW, riFlow);
	}

	public List findByRiInchargedep(Object riInchargedep) {
		return findByProperty(RI_INCHARGEDEP, riInchargedep);
	}

	public List findByRiFinance(Object riFinance) {
		return findByProperty(RI_FINANCE, riFinance);
	}

	public List findByRiMoney(Object riMoney) {
		return findByProperty(RI_MONEY, riMoney);
	}

	public List findByRiFame(Object riFame) {
		return findByProperty(RI_FAME, riFame);
	}

	public List findByRiLaw(Object riLaw) {
		return findByProperty(RI_LAW, riLaw);
	}

	public List findByRiClient(Object riClient) {
		return findByProperty(RI_CLIENT, riClient);
	}

	public List findByRiEmployee(Object riEmployee) {
		return findByProperty(RI_EMPLOYEE, riEmployee);
	}

	public List findByRiOperation(Object riOperation) {
		return findByProperty(RI_OPERATION, riOperation);
	}

	public List findByRiSafe(Object riSafe) {
		return findByProperty(RI_SAFE, riSafe);
	}

	public List findByRiFindegree(Object riFindegree) {
		return findByProperty(RI_FINDEGREE, riFindegree);
	}

	public List findByRiFincriteria(Object riFincriteria) {
		return findByProperty(RI_FINCRITERIA, riFincriteria);
	}

	public List findByRiFamedegree(Object riFamedegree) {
		return findByProperty(RI_FAMEDEGREE, riFamedegree);
	}

	public List findByRiFamecriteria(Object riFamecriteria) {
		return findByProperty(RI_FAMECRITERIA, riFamecriteria);
	}

	public List findByRiLawdegree(Object riLawdegree) {
		return findByProperty(RI_LAWDEGREE, riLawdegree);
	}

	public List findByRiLawcriteria(Object riLawcriteria) {
		return findByProperty(RI_LAWCRITERIA, riLawcriteria);
	}

	public List findByRiClidegree(Object riClidegree) {
		return findByProperty(RI_CLIDEGREE, riClidegree);
	}

	public List findByRiClicriteria(Object riClicriteria) {
		return findByProperty(RI_CLICRITERIA, riClicriteria);
	}

	public List findByRiEmpdegree(Object riEmpdegree) {
		return findByProperty(RI_EMPDEGREE, riEmpdegree);
	}

	public List findByRiEmpcriteria(Object riEmpcriteria) {
		return findByProperty(RI_EMPCRITERIA, riEmpcriteria);
	}

	public List findByRiOpedegree(Object riOpedegree) {
		return findByProperty(RI_OPEDEGREE, riOpedegree);
	}

	public List findByRiOpecriteria(Object riOpecriteria) {
		return findByProperty(RI_OPECRITERIA, riOpecriteria);
	}

	public List findByRiSafedegree(Object riSafedegree) {
		return findByProperty(RI_SAFEDEGREE, riSafedegree);
	}

	public List findByRiSafecriteria(Object riSafecriteria) {
		return findByProperty(RI_SAFECRITERIA, riSafecriteria);
	}

	public List findAll() {
		log.debug("finding all RiskImpact instances");
		try {
			String queryString = "from RiskImpact";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RiskImpact merge(RiskImpact detachedInstance) {
		log.debug("merging RiskImpact instance");
		try {
			RiskImpact result = (RiskImpact) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskImpact instance) {
		log.debug("attaching dirty RiskImpact instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskImpact instance) {
		log.debug("attaching clean RiskImpact instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskImpactDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskImpactDAO) ctx.getBean("RiskImpactDAO");
	}
}