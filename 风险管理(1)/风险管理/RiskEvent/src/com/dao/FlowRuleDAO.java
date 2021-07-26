package com.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.FlowRule;

/**
 * A data access object (DAO) providing persistence and search support for
 * FlowRule entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.FlowRule
 * @author MyEclipse Persistence Tools
 */

public class FlowRuleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(FlowRuleDAO.class);
	// property constants
	public static final String FR_PRESTATUS = "frPrestatus";
	public static final String FR_SUCCNEXT = "frSuccnext";
	public static final String FR_FAILNEXT = "frFailnext";
	public static final String FR_DEPART = "frDepart";
	public static final String FR_ROLE = "frRole";
	public static final String FR_REMARK1 = "frRemark1";
	public static final String FR_REMARK2 = "frRemark2";

	protected void initDao() {
		// do nothing
	}

	public void save(FlowRule transientInstance) {
		log.debug("saving FlowRule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(final String value) {
		log.debug("deleting FlowRule instance");
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryString = "delete from FlowRule where riskId = '"
							+ value + "'";
					Query query = session.createQuery(queryString);
					query.executeUpdate();
					session.close();
					return true;
				}
			});
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(FlowRule persistentInstance) {
		log.debug("deleting FlowRule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FlowRule findById(java.lang.Integer id) {
		log.debug("getting FlowRule instance with id: " + id);
		try {
			FlowRule instance = (FlowRule) getHibernateTemplate().get(
					"com.model.FlowRule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FlowRule instance) {
		log.debug("finding FlowRule instance by example");
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

	public List FindBy3Property(String propertyName1, String value1, String propertyName2, String value2, 
			String propertyName3, String value3)
	{
		try {
			String queryString1 = "from FlowRule as model where model."  + propertyName1 + "='" + value1 + "' and model." + propertyName2 + "='" + value2 + "' and model." + propertyName3 + "='" + value3 + "'";
			return getHibernateTemplate().find(queryString1);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding FlowRule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FlowRule as model where model."
					+ propertyName + "= ? order by model.frStatus";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFlow(String propertyName, Object value) {
		log.debug("finding FlowRule instance with property: " + propertyName
				+ ", value: " + value
				+ " where frStatus!='1' or frNextstatus!='*'");
		try {
			String queryString = "from FlowRule as model where model."
					+ propertyName
					+ "= ? and model.frStatus!='1' and  model.frNextstatus!='*'  order by model.frStatus";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFrPrestatus(Object frPrestatus) {
		return findByProperty(FR_PRESTATUS, frPrestatus);
	}

	public List findByFrSuccnext(Object frSuccnext) {
		return findByProperty(FR_SUCCNEXT, frSuccnext);
	}

	public List findByFrFailnext(Object frFailnext) {
		return findByProperty(FR_FAILNEXT, frFailnext);
	}

	public List findByFrDepart(Object frDepart) {
		return findByProperty(FR_DEPART, frDepart);
	}

	public List findByFrRole(Object frRole) {
		return findByProperty(FR_ROLE, frRole);
	}

	public List findByFrRemark1(Object frRemark1) {
		return findByProperty(FR_REMARK1, frRemark1);
	}

	public List findByFrRemark2(Object frRemark2) {
		return findByProperty(FR_REMARK2, frRemark2);
	}

	// 通过riskId查询flowRule
	public List findByRiskId(String riskId) {
		try {

			String queryString = "from FlowRule as model where model.riskId = '"
					+ riskId + "' ";
			List<FlowRule> list = new LinkedList<FlowRule>();
			list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskFlowAll(final int offset, final int pageSize) {
		log.debug("finding all FlowRule instances");
		final String queryString = "select distinct riskId  from FlowRule order by riskId";
		try {
			ServletActionContext.getRequest().getSession().setAttribute(
					"pagecount",
					getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString)
									.setFirstResult(offset).setMaxResults(
											pageSize).list();
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

	// 限制只取当年
	public List findRiskFlowAllNew(final int offset, final int pageSize) {
		log.debug("finding all FlowRule instances");
		String riskCurrent = "1";

		final String queryString = "select risk.riskId,risk.riskName,risk.riskQueue from Risk as risk,FlowRule as fr where risk.riskId = fr.riskId and fr.frId in (select min(frId) from FlowRule fr group by riskId) and  risk.riskCurrent = '"
				+ riskCurrent + "' order by risk.riskName";

		try {
			ServletActionContext.getRequest().getSession().setAttribute(
					"pagecount",
					getHibernateTemplate().find(queryString).size());
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {

							List result = session.createQuery(queryString)
									.setFirstResult(offset).setMaxResults(
											pageSize).list();
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

	// 多条件查询权限信息
	public List celuQuery(String celu, final int offset, final int pageSize) {
		
		try {
			String queryString = "";
			if (celu.equals("--请选择--"))
				queryString = "select distinct riskId  from FlowRule order by riskId";
			else
				queryString = "select distinct riskId  from FlowRule where riskId='"
						+ celu + "'";
			
			int size = getHibernateTemplate().find(queryString).size();
			ServletActionContext.getRequest().getSession().setAttribute(
					"pagecount", size);
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString1)
									.setFirstResult(offset).setMaxResults(
											pageSize).list();
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

	public List yearQuery(String year, final int offset, final int pageSize) {
		
		try {
			String queryString = "";

			queryString = "select distinct riskId from FlowRule where riskId like'%"+ year + "%'";
			
			int size = getHibernateTemplate().find(queryString).size();
			ServletActionContext.getRequest().getSession().setAttribute(
					"pagecount", size);
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							// TODO Auto-generated method stub
							List result = session.createQuery(queryString1)
									.setFirstResult(offset).setMaxResults(
											pageSize).list();
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

	// 获得查询年份的流转策略list，并针对“2012”年特殊处理 加上“风险名称”查询条件
	public List yearQueryNew(String year, String riskTypeId, final int offset,
			final int pageSize) {

		try {
			String queryString = "";
			String riskCurrent="1";

			

			if (year.equals("2012")) {
				
				queryString = "select risk.riskId,risk.riskName,risk.riskQueue from Risk as risk,FlowRule as fr where risk.riskId = fr.riskId and fr.frId in (select min(frId) from FlowRule group by riskId) and  length(risk.riskId)<12 and risk.riskCurrent='"+riskCurrent+"' and risk.riskName like '%"
						+ riskTypeId + "%' order by risk.riskName";
			} else {
				
				queryString = "select risk.riskId,risk.riskName,risk.riskQueue from Risk as risk,FlowRule as fr where risk.riskId = fr.riskId and fr.frId in (select min(frId) from FlowRule group by riskId) and risk.riskCurrent='"+riskCurrent+"' and  risk.riskId like '%"
						+ year
						+ "%' and risk.riskName like '%"
						+ riskTypeId
						+ "%' order by risk.riskName";
			}

			
			int size = getHibernateTemplate().find(queryString).size();
			ServletActionContext.getRequest().getSession().setAttribute(
					"pagecount", size);
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString1)
									.setFirstResult(offset).setMaxResults(
											pageSize).list();
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

	public List findAll() {
		log.debug("finding all FlowRule instances");
		try {
			String queryString = "from FlowRule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findnextstatus(String risk, String status) {
		try {

			String queryString = "from FlowRule where riskId='" + risk
					+ "' and frStatus='" + status + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find nextstatus failed", re);
			throw re;
		}
	}

	public FlowRule merge(FlowRule detachedInstance) {
		log.debug("merging FlowRule instance");
		try {
			FlowRule result = (FlowRule) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FlowRule instance) {
		log.debug("attaching dirty FlowRule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FlowRule instance) {
		log.debug("attaching clean FlowRule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FlowRuleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FlowRuleDAO) ctx.getBean("FlowRuleDAO");
	}
}