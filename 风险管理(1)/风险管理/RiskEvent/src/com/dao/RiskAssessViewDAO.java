package com.dao;

import java.sql.SQLException;
import java.util.Calendar;
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

import com.model.Department;
import com.model.RiskAssessAccount;
import com.model.RiskAssessView;
import com.model.RiskEvent;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskAssessView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskAssessView
 * @author MyEclipse Persistence Tools
 */

public class RiskAssessViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskAssessViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public RiskAssessView findById(java.lang.Integer id) {
		log.debug("getting RiskAssessView instance with id: " + id);
		try {
			RiskAssessView instance = (RiskAssessView) getHibernateTemplate()
					.get("com.model.RiskAssessView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskAssessView instance) {
		log.debug("finding RiskAssessView instance by example");
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
		log.debug("finding RiskAssessView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskAssessView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public RiskAssessView merge(RiskAssessView detachedInstance) {
		log.debug("merging RiskAssessView instance");
		try {
			RiskAssessView result = (RiskAssessView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskAssessView instance) {
		log.debug("attaching dirty RiskAssessView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskAssessView instance) {
		log.debug("attaching clean RiskAssessView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskAssessViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskAssessViewDAO) ctx.getBean("RiskAssessViewDAO");
	}

	/*----------------------------------自己写的------------------------------*/
//	public void updateeventinput(RiskEvent riskevent) {
//		try {
//			Session session = getHibernateTemplate().getSessionFactory()
//					.openSession();
//			Transaction trans = session.beginTransaction();
//			String updateString = "update RiskEvent set RE_detail='"
//					+ riskevent.getReDetail() + "',RE_eventname='"
//					+ riskevent.getReEventname() + "',RE_modifydate='"
//					+ riskevent.getReModifydate() + "',RE_modifier='"
//					+ riskevent.getReModifier() + "' where RE_Id='"
//					+ riskevent.getReId() + "'";
//			Query queryupdate = session.createQuery(updateString);
//			int ret = queryupdate.executeUpdate();
//			trans.commit();
//			session.close();
//		} catch (RuntimeException re) {
//			// throw re;
//			re.printStackTrace();
//		}
//	}

	// 查询全部--第一维度
	public List findAll() {

		Calendar time = Calendar.getInstance();
		int currentYear;
		int currentQuarter = 0;
		int currentMonth;
		currentYear = time.get(Calendar.YEAR);
		currentMonth = time.get(Calendar.MONTH) + 1;
		switch (currentMonth) {
		case 1:
		case 2:
		case 3:
			currentQuarter += 1;
			break;
		case 4:
		case 5:
		case 6:
			currentQuarter += 2;
			break;
		case 7:
		case 8:
		case 9:
			currentQuarter += 3;
			break;
		case 10:
		case 11:
		case 12:
			currentQuarter += 4;
		}
		try {
			// String queryString =
			// "from RiskAssessView ORDER BY raDepId,raYear,raQuarter";
			// 默认查询为上一季度的考核分数，如果当前季度为第一季度，则查询上一年的最后一个季度
			//if (currentQuarter == 1) {
				//currentQuarter = 4;
				//currentYear = currentYear - 1;
			//} else
				//currentQuarter = currentQuarter - 1;

			String queryString = "from RiskAssessView where raYear='"
					+ currentYear + "'and raQuarter='" + currentQuarter
					+ "' and raDepId!='FB' and depAssess=1 "
					+ " ORDER BY raDepId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// 条件查询，按录入年份，录入部门，录入季度查询
	
	public List reqQuery(String Year, String YearTo, String DepId,
			String Quarter, String QuarterTo) {
		try {

			String queryString = "";

			if (DepId.equals("none1")) {
				if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0)
					queryString = "from RiskAssessView as model where (model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ Quarter
							+ "' and '" + QuarterTo + "'";
				else if (Year.equals(YearTo)
						&& Quarter.compareTo(QuarterTo) > 0)
					queryString = "from RiskAssessView as model where (model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ QuarterTo
							+ "' and '" + Quarter + "'";
				else if (Year.compareTo(YearTo) < 0)
					queryString = "from RiskAssessView as model where ((model.raYear ='"
							+ Year
							+ "' and model.raQuarter >='"
							+ Quarter
							+ "') or (model.raYear ='"
							+ YearTo
							+ "' and model.raQuarter <= '"
							+ QuarterTo
							+ "') or ('"
							+ Year
							+ "' < "
							+ "model.raYear and model.raYear"
							+ " < '"
							+ YearTo
							+ "')";
				else
					queryString = "from RiskAssessView as model where ((model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter >='"
						+ QuarterTo
						+ "') or (model.raYear ='"
						+ Year
						+ "' and model.raQuarter <= '"
						+ Quarter
						+ "') or ('"
						+ YearTo
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ Year
						+ "')";
			} else if (!DepId.equals("none1")) {
				if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0)
					queryString = "from RiskAssessView as model where (model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ Quarter
							+ "' and '"
							+ QuarterTo
							+ "' and model.raDepId = '"
							+ DepId + "'";
				else if (Year.equals(YearTo)
						&& Quarter.compareTo(QuarterTo) > 0)
					queryString = "from RiskAssessView as model where (model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ QuarterTo
							+ "' and '"
							+ Quarter
							+ "' and model.raDepId = '"
							+ DepId + "'";
				else if (Year.compareTo(YearTo) < 0)
					queryString = "from RiskAssessView as model where (((model.raYear ='"
						+ Year
						+ "' and model.raQuarter >='"
						+ Quarter
						+ "') or (model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter <= '"
						+ QuarterTo
						+ "') or ('"
						+ Year
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ YearTo
						+ "'))"+ " and model.raDepId = '"
						+ DepId + "'";
				else
					queryString = "from RiskAssessView as model where (((model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter >='"
						+ QuarterTo
						+ "') or (model.raYear ='"
						+ Year
						+ "' and model.raQuarter <= '"
						+ Quarter
						+ "') or ('"
						+ YearTo
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ Year
						+ "'))"+ " and model.raDepId = '"
						+ DepId + "'";
			}
			queryString += " and raDepId != 'FB') and depAssess=1 ORDER BY model.raDepId ASC,model.raYear ASC,model.raQuarter ASC";
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString1).list();
							session.close();
							return result;
						}
					});
			return list;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	public List reqQuery1(String Year, String YearTo, String DepId,
			String Quarter, String QuarterTo) {
		try {

			String queryString = "";

			if (DepId.equals("none1")) {
				if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0)
					queryString = "from RiskAssessView as model where model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ Quarter
							+ "' and '" + QuarterTo + "'";
				else if (Year.equals(YearTo)
						&& Quarter.compareTo(QuarterTo) > 0)
					queryString = "from RiskAssessView as model where model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ QuarterTo
							+ "' and '" + Quarter + "'";
				else if (Year.compareTo(YearTo) < 0)
					queryString = "from RiskAssessView as model where (model.raYear ='"
							+ Year
							+ "' and model.raQuarter >='"
							+ Quarter
							+ "') or (model.raYear ='"
							+ YearTo
							+ "' and model.raQuarter <= '"
							+ QuarterTo
							+ "') or ('"
							+ Year
							+ "' < "
							+ "model.raYear and model.raYear"
							+ " < '"
							+ YearTo
							+ "')";
				else
					queryString = "from RiskAssessView as model where (model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter >='"
						+ QuarterTo
						+ "') or (model.raYear ='"
						+ Year
						+ "' and model.raQuarter <= '"
						+ Quarter
						+ "') or ('"
						+ YearTo
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ Year
						+ "')";
			} else if (!DepId.equals("none1")) {
				if (Year.equals(YearTo) && Quarter.compareTo(QuarterTo) <= 0)
					queryString = "from RiskAssessView as model where model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ Quarter
							+ "' and '"
							+ QuarterTo
							+ "' and model.raDepId = '"
							+ DepId + "'";
				else if (Year.equals(YearTo)
						&& Quarter.compareTo(QuarterTo) > 0)
					queryString = "from RiskAssessView as model where model.raYear Between'"
							+ Year
							+ "' and '"
							+ YearTo
							+ "' and model.raQuarter between '"
							+ QuarterTo
							+ "' and '"
							+ Quarter
							+ "' and model.raDepId = '"
							+ DepId + "'";
				else if (Year.compareTo(YearTo) < 0)
					queryString = "from RiskAssessView as model where ((model.raYear ='"
						+ Year
						+ "' and model.raQuarter >='"
						+ Quarter
						+ "') or (model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter <= '"
						+ QuarterTo
						+ "') or ('"
						+ Year
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ YearTo
						+ "'))"+ " and model.raDepId = '"
						+ DepId + "'";
				else
					queryString = "from RiskAssessView as model where ((model.raYear ='"
						+ YearTo
						+ "' and model.raQuarter >='"
						+ QuarterTo
						+ "') or (model.raYear ='"
						+ Year
						+ "' and model.raQuarter <= '"
						+ Quarter
						+ "') or ('"
						+ YearTo
						+ "' < "
						+ "model.raYear and model.raYear"
						+ " < '"
						+ Year
						+ "'))"+ " and model.raDepId = '"
						+ DepId + "'";
			}
			queryString += " and raDepId != 'FB'  ORDER BY model.raDepId ASC,model.raYear ASC,model.raQuarter ASC";
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							List result = session.createQuery(queryString1).list();
							session.close();
							return result;
						}
					});
			return list;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	public List<RiskAssessAccount> reqQuerys(String depId, String year, String month) {
		try {
			String queryString = "from RiskAssessAccount where raYear='"+year+"' and raMonth='" + month + "' and raDepId='" + depId + "'";
			List<RiskAssessAccount> list = getHibernateTemplate().find(queryString);
			return list;
		} catch (Exception re) {
			
			throw new RuntimeException(re);
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
	
	public void updateRiskAccountByDelete(String depId, String year, String month) {
		
		try{
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   String updateString="update RiskAssessAccount set raNumberInput=raNumberInput-1 where raYear='"+year+"' and raMonth='" + month + "' and raDepId='" + depId + "'";
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

	public void insertRiskAccount(Department d, String assessYear, String assessMonth) {

		try{
			   int month = Integer.valueOf(assessMonth);
			   String quarter = (month-1)/3+1+"";
			   Session session = getHibernateTemplate().getSessionFactory().openSession();		
			   Transaction trans=session.beginTransaction();
			   String updateString="insert into RiskAssessAccount (raDepId,raDepName,raYear,raQuarter,raMonth,raNumberInput,raDepProperty) values ('"+d.getDepId()+"','"+d.getDepName()+"','"+assessYear+"','"+quarter+"','"+assessMonth+"',1,'"+d.getDepSort()+"')";
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
}