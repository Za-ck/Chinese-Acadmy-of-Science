package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ProjectElement;
import com.model.RiskEvent;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProjectElement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ProjectElement
 * @author MyEclipse Persistence Tools
 */

public class ProjectElementDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ProjectElementDAO.class);
	// property constants
	public static final String PE_PROJECTID = "peProjectid";
	public static final String PE_EVALUATEID = "peEvaluateid";
	public static final String PE_PROBABLITY = "peProbablity";
	public static final String PE_PRODEGREE = "peProdegree";
	public static final String PE_IMPACTDEGREE = "peImpactdegree";
	public static final String PE_CATEIMPACT = "peCateimpact";
	public static final String PE_CATEGORY = "peCategory";
	public static final String PE_EVALUATENAME = "peEvaluatename";
	public static final String PE_CATEGORYNAME = "peCategoryname";

	protected void initDao() {
		// do nothing
	}

	public void save(ProjectElement transientInstance) {
		log.debug("saving ProjectElement instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProjectElement persistentInstance) {
		log.debug("deleting ProjectElement instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectElement findById(java.lang.Integer id) {
		log.debug("getting ProjectElement instance with id: " + id);
		try {
			ProjectElement instance = (ProjectElement) getHibernateTemplate()
					.get("com.model.ProjectElement", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByIdCategory(final String projectId,final String category) {
		try {
            List list = getHibernateTemplate().executeFind(new HibernateCallback() {	
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from ProjectElement where peProjectid='" + projectId+"' and peCategory='" + category+"'")
					.list();
					session.close();
					return result;
				}
			});	
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProjectElement instance) {
		log.debug("finding ProjectElement instance by example");
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
		log.debug("finding ProjectElement instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProjectElement as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPeProjectid(Object peProjectid) {
		return findByProperty(PE_PROJECTID, peProjectid);
	}

	public List findByPeEvaluateid(Object peEvaluateid) {
		return findByProperty(PE_EVALUATEID, peEvaluateid);
	}
	
	public List findByPeEvaluatename(Object peEvaluatename) {
		return findByProperty(PE_EVALUATENAME, peEvaluatename);
	}

	public List findByPeProbablity(Object peProbablity) {
		return findByProperty(PE_PROBABLITY, peProbablity);
	}

	public List findByPeProdegree(Object peProdegree) {
		return findByProperty(PE_PRODEGREE, peProdegree);
	}

	public List findByPeImpactdegree(Object peImpactdegree) {
		return findByProperty(PE_IMPACTDEGREE, peImpactdegree);
	}

	public List findByPeCateimpact(Object PeCateimpact) {
		return findByProperty(PE_CATEIMPACT, PeCateimpact);
	}
	public List findByPeCategory(Object PeCategory) {
		return findByProperty(PE_CATEGORY, PeCategory);
	}
	public List findByPeCategoryname(Object PeCategoryname) {
		return findByProperty(PE_CATEGORYNAME, PeCategoryname);
	}

	public List findAll() {
		log.debug("finding all ProjectElement instances");
		try {
			String queryString = "from ProjectElement";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProjectElement merge(ProjectElement detachedInstance) {
		log.debug("merging ProjectElement instance");
		try {
			ProjectElement result = (ProjectElement) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectElement instance) {
		log.debug("attaching dirty ProjectElement instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectElement instance) {
		log.debug("attaching clean ProjectElement instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProjectElementDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProjectElementDAO) ctx.getBean("ProjectElementDAO");
	}
	
	public List findByProjectId(String projectId) {
		log.debug("finding ProjectElement instance by projectId");
		try {
			String queryString = "from ProjectElement as model where model.peProjectid='"
					+ projectId + "' order by model.peCategoryname,model.peProdegree";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by projectId failed", re);
			throw re;
		}
	}
	
	   //点击保存修改后，修改项目因素
	   public void updateProjectElement(ProjectElement projectelement)
	   {
		   try{
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   String updateString="update ProjectElement set peCateimpact='"+projectelement.getPeCateimpact()+"',peProbablity='"+projectelement.getPeProbablity()+"',peProdegree='"+projectelement.getPeProdegree()+"',peImpactdegree='"+projectelement.getPeImpactdegree()+"' where peId="+projectelement.getPeId();
			   Query queryupdate=session.createQuery(updateString);
			   int ret=queryupdate.executeUpdate();
			   trans.commit();	   
			   session.close();
		   }
		   catch(RuntimeException re)
		   {
			 //throw re;
				re.printStackTrace();
		   }
	   }
}