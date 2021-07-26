package com.dao;
// default package

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
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

import com.model.RiskAssessAccount;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskAssess entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .RiskAssess
 * @author MyEclipse Persistence Tools
 */

public class RiskAssessDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskAssessDAO.class);
	// property constants
	public static final String RA_DEP_ID = "raDepId";
	public static final String RA_DEP_NAME = "raDepName";
	public static final String RA_DepProperty = "raDepProperty";
	public static final String RA_Year = "raYear";
	public static final String RA_Quarter = "raQuarter";
	public static final String RA_NumberInput = "raNumberInput";
	public static final String RA_NumberDeal = "raNumberDeal";
	public static final String RA_REMARK1 = "raRemark1";
	public static final String RA_REMARK2 = "raRemark2";
	public static final String RA_DimID = "raDimId";
	public static final String RA_DimName = "raDimName";

	protected void initDao() {
		// do nothing
	}

	public void save(RiskAssessAccount transientInstance) {
		log.debug("saving RiskAssess instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskAssessAccount persistentInstance) {
		log.debug("deleting RiskAssess instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskAssessAccount findById(java.lang.Integer id) {
		log.debug("getting RiskAssess instance with id: " + id);
		try {
			RiskAssessAccount instance = (RiskAssessAccount) getHibernateTemplate().get(
					"RiskAssess", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskAssessAccount instance) {
		log.debug("finding RiskAssess instance by example");
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
		log.debug("finding RiskAssess instance with property: " + propertyName
				+ ", value: " + value);
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

	public List findbyRaQuarter(Object raQuarter){
		return findByProperty(RA_Quarter,raQuarter);
	}
	public List findByRaYear(Object raYear) {
		return findByProperty(RA_Year, raYear);
	}

	public List findByRaRemark1(Object raRemark1) {
		return findByProperty(RA_REMARK1, raRemark1);
	}

	public List findByRaRemark2(Object raRemark2) {
		return findByProperty(RA_REMARK2, raRemark2);
	}

	public List findAll() {
		log.debug("finding all RiskAssess instances");
		try {
			String queryString = "from RiskAssessAccout";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//ȫ�����ſ��˳ɼ�
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize,final String orderbys) {
		try {
			final String queryString = "SELECT COUNT(*) from RiskAssessAccount";
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString).uniqueResult().toString();
					session.close();
					return result;
				}
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from RiskAssessAccount as model "+(orderbys.equals("")?" order by model.raId":" order by model."+orderbys))
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List raQuery(final String raDepId,final String Year,final String AssessResultFrom,final String AssessResultTo,final String Quarter,final int offset, final int pageSize,final String orderbys) {
		try {
			final String AssessResultFrom_hql=AssessResultFrom.equals("")?"":" and model.raAssessResult>='"+AssessResultFrom+"'";
			final String AssessResultTo_hql=AssessResultTo.equals("")?"":" and model.raAssessResult<='"+AssessResultTo+"'";
			final String raDepId_hql=raDepId.equals("none1")?"":" and model.raDepId='"+raDepId+"'";
			final String Quarter_hql=Quarter.equals("")?"":" and model.raQuarter='"+Quarter+"'";
			final String queryString = "SELECT COUNT(*) from RiskAssessAccount as model where model.raYear='"+Year+"'"+AssessResultFrom_hql+AssessResultTo_hql+raDepId_hql+Quarter;
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString).uniqueResult().toString();
					session.close();
					return result;
				}
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from RiskAssessAccount as model where model.raYear='"+Year+"'"+AssessResultFrom_hql+AssessResultTo_hql+raDepId_hql+Quarter_hql+(orderbys.equals("")?" order by model.raId":" order by model."+orderbys))
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			re.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RiskAssessAccount> reqMonthlyResult(String raDepId,String raYear){
		List<RiskAssessAccount> list=null;
		String queryString ="from RiskAssessAccount where raDepId='"+raDepId+"' and raYear='"+raYear+"'";
		Session session=getHibernateTemplate().getSessionFactory().openSession();
		list=session.createQuery(queryString).list();
		session.close();
		return list;
	}
	
	public RiskAssessAccount merge(RiskAssessAccount detachedInstance) {
		log.debug("merging RiskAssess instance");
		try {
			RiskAssessAccount result = (RiskAssessAccount) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskAssessAccount instance) {
		log.debug("attaching dirty RiskAssess instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskAssessAccount instance) {
		log.debug("attaching clean RiskAssess instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskAssessDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskAssessDAO) ctx.getBean("RiskAssessDAO");
	}
	
	public List CheckInput(String year, String month, String depId) {
		try {
			final String queryString = "FROM RiskAssessAccount WHERE raDepId='"
					+ depId + "' AND raMonth='" + month + "' AND raYear='"
					+ year + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			//log.error("attach failed", re);
			//throw re;
			
			List<RiskAssessAccount> checkinput=new LinkedList<RiskAssessAccount>();
			return checkinput;
			//re.printStackTrace();
		}
	}

	public void updateRiskAccount(String depId, String year, String month) {
		
		try{
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   String updateString="update RiskAssessAccount set raNumberInput=raNumberInput+1 where raYear='"+year+"' and raMonth='" + month + "' and raDepId='" + depId + "'";
			   Query queryupdate=session.createQuery(updateString);
			   queryupdate.executeUpdate();
			   trans.commit();	   
			   session.close();
		   }
		   catch(RuntimeException re)
		   {
			   //throw re;
				re.printStackTrace();
		   }
		
	}


	public List<RiskAssessAccount> findList(String depId, String year, String month) {
		try {
			String queryString = "from RiskAssessAccount where raYear='"+year+"' and raMonth='" + month + "' and raDepId='" + depId + "'";
			List<RiskAssessAccount> list = getHibernateTemplate().find(queryString);
			return list;
		} catch (Exception re) {
			
			throw new RuntimeException(re);
		}
	}

	
	@SuppressWarnings("unchecked")
	public List CheckInput_Old(String year, String month, String depId) {
		
		final String queryString = "FROM RiskAssessAccount WHERE raDepId='" + depId + "' AND raMonth='"+ month + "' AND raYear='"+ year +"'";
		//System.out.println(queryString);
		
		List<RiskAssessAccount> list = getHibernateTemplate().executeFind( new HibernateCallback<List<RiskAssessAccount>>() {
			
			@Override
			public List<RiskAssessAccount> doInHibernate(Session session) throws HibernateException, SQLException {
				List<RiskAssessAccount> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
}