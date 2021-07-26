package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.Risk;

/**
 * A data access object (DAO) providing persistence and search support for Risk
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.Risk
 * @author MyEclipse Persistence Tools
 */

public class RiskDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(RiskDAO.class);
	// property constants
	public static final String RISK_NAME = "riskName";
	public static final String RISK_DEP = "riskDep";
	public static final String RISK_REMARK = "riskRemark";

	protected void initDao() {
		// do nothing
	}

	public void save(Risk transientInstance) {
		log.debug("saving Risk instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Risk persistentInstance) {
		log.debug("deleting Risk instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Risk findById(java.lang.String id) {
		log.debug("getting Risk instance with id: " + id);
		try {
			Risk instance = (Risk) getHibernateTemplate().get("com.model.Risk",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Risk instance) {
		log.debug("finding Risk instance by example");
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
		log.debug("finding Risk instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Risk as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRiskName(Object riskName) {
		return findByProperty(RISK_NAME, riskName);
	}

	public List findByRiskDep(Object riskDep) {
		return findByProperty(RISK_DEP, riskDep);
	}

	public List findByRiskRemark(Object riskRemark) {
		return findByProperty(RISK_REMARK, riskRemark);
	}

	public List findAll() {
		
		try {
			String queryString = "from Risk";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	public List findAllbyName() {
		
		try {
			String queryString = "from Risk as risk order by risk.riskName";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getRiskMaxQueue() {
		final String queryString2 = "SELECT MAX(riskQueue) from Risk";
		return getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				String result = session.createQuery(queryString2).uniqueResult().toString();
				session.close();
				return Integer.parseInt(result);
			}
			
		});
	}
	
	public List findAllCurrent() {
		
		try {
			String queryString = "from Risk as risk where risk.riskCurrent = '1' order by risk.riskName";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize) {
		
		try {
			final String queryString = "from Risk order by riskName";
			//findAll();
			final String queryString2 = "SELECT COUNT(*) " + queryString;
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	public Risk merge(Risk detachedInstance) {
		log.debug("merging Risk instance");
		try {
			Risk result = (Risk) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Risk instance) {
		log.debug("attaching dirty Risk instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Risk instance) {
		log.debug("attaching clean Risk instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RiskDAO) ctx.getBean("RiskDAO");
	}
	
	//根据风险类型信息查询风险
	public List search(String riskTypeId) {
		log.debug("finding all Risk instances");
		try {
			String queryString1 = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%'";
			String queryString2 = "from Risk as risk where risk.riskType.rtName like '%"+riskTypeId+"%'";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			if(!list.iterator().hasNext())
				list=getHibernateTemplate().find(queryString2);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List search(String riskTypeId,final int offset, final int pageSize) {
		log.debug("finding all Department instances");
		try {
			final String queryString = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%' or risk.riskType.rtName like '%"+riskTypeId+"%'";		
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString)
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
			throw re;
		}
	}
	
	
	//根据年份查询风险
	public List findByYear(String year) {
		log.debug("finding all Risk instances");
		try {
			
			String queryString ="";
			if(year.equals("2012"))
			{
				//queryString = "from Risk as risk where length(risk.riskId)<12  order by risk.riskQueue";
				//按中文首字母排序
				queryString = "from Risk as risk where length(risk.riskId)<12  order by risk.riskName";
			}
			else
			{
				//queryString = "from Risk as risk where risk.riskId like '%"+year+"%' order by risk.riskQueue";
				//按中文首字母排序
				queryString = "from Risk as risk where risk.riskId like '%"+year+"%' order by risk.riskName";
			}
			
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//根据风险类型信息查询风险
	//导出Excel查询
	public List searchNew(String year,String riskTypeId) {
		
		log.debug("finding all Risk instances");
		try {
			//String queryString = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%' or risk.riskType.rtName like '%"+riskTypeId+"%'";
			String queryString ="";
			if(year.equals("2012"))
			{
				//queryString = "from Risk as risk where length(risk.riskId)<12 and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
				queryString = "from Risk as risk where length(risk.riskId)<12 and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
			}
			else
			{
				//queryString = "from Risk as risk where risk.riskId like '%"+year+"%' and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
				queryString = "from Risk as risk where risk.riskId like '%"+year+"%' and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
			}
			
			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//分页查询
	public List searchNew(String year,String riskTypeId,final int offset, final int pageSize) {
		
		log.debug("finding all Department instances");
		try {
			
			//final String queryString = "from Risk as risk where risk.riskType.rtId like '%"+riskTypeId+"%' or risk.riskType.rtName like '%"+riskTypeId+"%'";		
			String queryString1 ="";
			if(year.equals("2012"))
			{
				queryString1 = "from Risk as risk where length(risk.riskId)<12 and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
			}
			else
			{
				queryString1 = "from Risk as risk where risk.riskId like '%"+year+"%' and (risk.riskId like '%"+riskTypeId+"%' or risk.riskName like '%"+riskTypeId+"%') order by risk.riskQueue";
			}
			
			final String queryString =queryString1;
			
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery(queryString)
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
			throw re;
		}
	}
	
	
	
	
	//根据风险类型信息查询风险
	public List findbyInputType(String riskTypeId) {
		
		try {
			//String riskCurrent="1";
			//String queryString1 = "from Risk as risk where risk.riskType = '"+riskTypeId+"' and risk.riskCurrent = '"+riskCurrent+"' ";
			String queryString1 = "from Risk as risk where risk.riskType = '"+riskTypeId+"' order by risk.riskName";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//在风险录入中根据风险类型信息查询风险
	public List findbyInputTypeInput(String riskTypeId) {
		
		try {
			String riskCurrent="1";
			String queryString1 = "from Risk as risk where risk.riskType = '"+riskTypeId+"' and risk.riskCurrent = '"+riskCurrent+"' order by risk.riskName";
			//String queryString1 = "from Risk as risk where risk.riskType = '"+riskTypeId+"'";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByInputTypeDESC(String riskTypeId)
	{
		try {
			
			String riskCurrent="1";
			String queryString1 = "from Risk as risk where risk.riskType = '"+riskTypeId+"' and risk.riskCurrent = '"+riskCurrent+"' order by risk.riskId desc";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	public List findByTypeAndYearDESC(String riskTypeId,String year)
	{
		try {
			
			String queryString1 = "from Risk as model where model.riskType = '"+riskTypeId+"' and model.riskId like '%"+year+"%'  order by model.riskId desc";
			List<Risk> list=new LinkedList<Risk>();
			list=getHibernateTemplate().find(queryString1);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	//当年所有风险
	public List findCurrentYearAll() {
		log.debug("finding all Risk instances");
		try {
			
			String riskCurrent="1";
			final String queryString = "from Risk as model where model.riskCurrent = '"+riskCurrent+"' order by model.riskQueue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//当年所有风险
	public List findCurrentYearAll(final int offset, final int pageSize) {
		log.debug("finding all Department instances");
		try {
			String riskCurrent="1";
			final String queryString = "from Risk as model where model.riskCurrent = '"+riskCurrent+"' order by model.riskQueue";
			//findAll();
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					List result = session.createQuery(queryString)
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
			throw re;
		}
	}
	//删除老的风险,设置RiskCurrent为1
	public void delOldRisk(String year) {
		
		String queryString ="";
		if(year.equals("2012"))
		{
			queryString = "UPDATE Risk SET riskCurrent = '0' WHERE length(risk.riskId)<12";
		}
		else
		{
			queryString = "UPDATE Risk SET riskCurrent = '0' WHERE riskId like '%"+year+"%'";
		}
		final String queryString1 = queryString;
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString1).executeUpdate();
				session.close();
				return result;
			}
		});
	}
	
}