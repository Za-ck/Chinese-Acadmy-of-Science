package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.AllStaView;
import com.model.AllStaView1;

/**
 * A data access object (DAO) providing persistence and search support for
 * AllStaView entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.AllStaView
 * @author MyEclipse Persistence Tools
 */

public class AllStaView1DAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AllStaView1DAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(AllStaView1 transientInstance) {
		log.debug("saving AllStaView1 instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AllStaView1 persistentInstance) {
		log.debug("deleting AllStaView1 instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AllStaView1 findById(com.model.AllStaViewId id) {
		log.debug("getting AllStaView1 instance with id: " + id);
		try {
			AllStaView1 instance = (AllStaView1) getHibernateTemplate().get(
					"com.model.AllStaView1", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AllStaView1 instance) {
		log.debug("finding AllStaView1 instance by example");
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
		log.debug("finding AllStaView1 instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AllStaView1 as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AllStaView1 instances");
		try {
			String queryString = "from AllStaView1";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AllStaView1 merge(AllStaView1 detachedInstance) {
		log.debug("merging AllStaView1 instance");
		try {
			AllStaView1 result = (AllStaView1) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AllStaView1 instance) {
		log.debug("attaching dirty AllStaView1 instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AllStaView1 instance) {
		log.debug("attaching clean AllStaView1 instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AllStaView1DAO getFromApplicationContext(ApplicationContext ctx) {
		return (AllStaView1DAO) ctx.getBean("AllStaView1DAO");
	}

	//�����ȡ����Ȳ��ҷ����¼�������ͳ��
	public List<AllStaView1> findByYearandQuar(String year, Integer quarter) {
		log.debug("finding AllStaView1 instances by year and quarter");
		try {
			String queryString = "from AllStaView1 as model where model.year= '"+year+"' and model.quarter= "+quarter+" ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//ȫ��
	@SuppressWarnings("unchecked")
	public List<AllStaView1> findByYear(String year) {
		log.debug("finding AllStaView1 instances by year and quarter");
		try {
			String queryString = "from AllStaView1 as model where model.year= '"+year+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//�����ȡ����Ȳ��ҷ����¼�������ͳ��_part�����ź�ְ�ܲ���
	@SuppressWarnings("unchecked")
	public List<AllStaView1> findByYQ_part(String year, Integer quarter,String dep) {
		log.debug("finding AllStaView1 instances by year and quarter");
		try {
			String queryString = "from AllStaView1 as model where model.year= '"+year+"' and model.quarter= "+quarter+" and model.reIndep='"+dep+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//ȫ��
	@SuppressWarnings("unchecked")
	public List<AllStaView1> findByYear_part(String year,String dep) {
		log.debug("finding AllStaView1 instances by year and quarter");
		try {
			String queryString = "from AllStaView1 as model where model.year= '"+year+"' and model.reIndep='"+dep+"' ORDER BY model.depQueue, model.reType, model.reRiskId, model.id.reId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}