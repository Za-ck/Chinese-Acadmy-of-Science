package com.dao;
// default package

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.EventStaView2;
import com.model.EventStaView2Id;

/**
 * A data access object (DAO) providing persistence and search support for
 * EventStaView2 entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .EventStaView2
 * @author MyEclipse Persistence Tools
 */

public class EventStaView2DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(EventStaView2DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(EventStaView2 transientInstance) {
		log.debug("saving EventStaView2 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventStaView2 persistentInstance) {
		log.debug("deleting EventStaView2 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	//查询外键riskId=======================================================================================
    public List find(String choosedId,String year){
    	try{String datem="";
    	String queryString = "from RiskEvent as model where model.reState='*' and ";
    	queryString+="model.reLastdate"+" between'"+ year+"-"+01+"-"+01+"' and '"+ year+"-"+12+"-"+31+"' ";
    	return getHibernateTemplate().find(queryString);
    	}catch(RuntimeException re){
    		log.error("find by year and quarter failed", re);
			throw re;
    	}
    	
    }
	public EventStaView2 findById(EventStaView2Id id) {
		log.debug("getting EventStaView2 instance with id: " + id);
		try {
			EventStaView2 instance = (EventStaView2) getHibernateTemplate()
					.get("EventStaView2", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EventStaView2 instance) {
		log.debug("finding EventStaView2 instance by example");
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
		log.debug("finding EventStaView2 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EventStaView2 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EventStaView2 instances");
		try {
			String queryString = "from EventStaView2";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EventStaView2 merge(EventStaView2 detachedInstance) {
		log.debug("merging EventStaView2 instance");
		try {
			EventStaView2 result = (EventStaView2) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventStaView2 instance) {
		log.debug("attaching dirty EventStaView2 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventStaView2 instance) {
		log.debug("attaching clean EventStaView2 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventStaView2DAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EventStaView2DAO) ctx.getBean("EventStaView2DAO");
	}
	
	public List findByYearandQuar(String year, Integer quarter) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from EventStaView2 as model where model.id.year= '"+year+"' and model.id.quarter= "+quarter+" order by model.id.riskId";
			return getHibernateTemplate().find(queryString);
			
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
	public List findByYear(String year) {
		log.debug("finding by year and quarter");
		try {
			String queryString = "from EventStaView2 as model where model.id.year= '"+year+"' order by model.id.riskId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by year and quarter failed", re);
			throw re;
		}
	}
}