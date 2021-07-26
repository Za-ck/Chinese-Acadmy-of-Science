package com.dao;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.AllAnalysisView;
import com.model.KeyAnalysisView;
import com.model.ReportView;
import com.model.ReportViewId;


/**
 * A data access object (DAO) providing persistence and search support for
 * AllAnalysisView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.KeyAnalysisView
 * @author MyEclipse Persistence Tools
 */

public class KeyAnalysisViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(KeyAnalysisViewDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(KeyAnalysisView transientInstance) {
		log.debug("saving KeyAnalysisView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KeyAnalysisView persistentInstance) {
		log.debug("deleting KeyAnalysisView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KeyAnalysisView findById(String reId) {
		log.debug("getting KeyAnalysisView instance with id: " + reId);
		try {
			KeyAnalysisView instance = (KeyAnalysisView) getHibernateTemplate()
					.get("reId", reId);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KeyAnalysisView instance) {
		log.debug("finding KeyAnalysisView instance by example");
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
		log.debug("finding KeyAnalysisView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from KeyAnalysisView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all KeyAnalysisView instances");
		try {
			String queryString = "from KeyAnalysisView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KeyAnalysisView merge(KeyAnalysisView detachedInstance) {
		log.debug("merging KeyAnalysisView instance");
		try {
			KeyAnalysisView result = (KeyAnalysisView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KeyAnalysisView instance) {
		log.debug("attaching dirty KeyAnalysisView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KeyAnalysisView instance) {
		log.debug("attaching clean KeyAnalysisView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KeyAnalysisViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (KeyAnalysisViewDAO) ctx.getBean("KeyAnalysisViewDAO");
	}
	
	//查找有记录的部门
	public List findDep(String year) {
		
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisView as model  where model.year ='"+year+"' order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//根据部门查找该部门的风险事件
	public List findRiskEvent(final String year,final String dep) {
		log.debug("finding all KeyAnalysisView instances");
		
		
		try {
			 String queryString = "from KeyAnalysisView as model where model.reIndep = '"+dep+"' and model.year ='"+year+"'order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByDate(String dateFrom, String dateTo) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisView as model  where model.reDate between '"+dateFrom+"' and '"+dateTo+"' order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRisk(String year, String dep) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue from KeyAnalysisView as model where model.reIndep = '"+dep+"' and model.year ='"+year+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//找到二级风险的所有风险事件
	public List<KeyAnalysisView> findDetail(String year, String depId,
			String riskId) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			String queryString = "from KeyAnalysisView as model where model.year='"+year+"' and model.reIndep='"+depId+"' and  model.riskId='"+riskId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisView as model "+condition+" order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByDep(String depid) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue from KeyAnalysisView as model where model.reIndep='"+depid+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<KeyAnalysisView> findDetailByCondition(String depid,
			String riskName, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			if(condition.equals("")){condition=" where ";}
			else{condition+=" and ";}
			String queryString = "from KeyAnalysisView as model "+condition+" model.reIndep='"+depid+"' and  model.riskName='"+riskName+"' ORDER BY riAllvalue DESC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskNew(String year) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue,rtName from KeyAnalysisView as model where model.year ='"+year+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByRisk(String year, String riskId) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisView as model where model.riskId = '"+riskId+"' and model.year ='"+year+"' order by depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct riskName,rtName from KeyAnalysisView as model "+condition+" order by rtName,riskName";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByRiskName(String riskName) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from KeyAnalysisView as model where model.riskName = '"+riskName+"' order by depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findRiskByDepAndCondition(String depid, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			if(condition.equals("")){
				return findRiskByDep(depid);
			}else{
			 String queryString = "select distinct riskName from KeyAnalysisView as model "+condition+" and model.reIndep='"+depid+"' order by riskName";
					return getHibernateTemplate().find(queryString);
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMaxAndMinValue(String year){
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select MAX(riAllvalue),MIN(riAllvalue) from KeyAnalysisView as model where model.year='"+year+"'";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMaxAndMinDegree(String year){
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select MAX(riAlldegree),MIN(riAlldegree) from KeyAnalysisView as model where model.year='"+year+"'";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ReportView> findReportList(String condition){
		log.debug("finding all KeyAnalysisView instances");
		try {
			 String queryString = "select COUNT(*),riskName,riskQueue, FLOOR(AVG(riProdegree)), FLOOR((AVG(riAllvalue)-1)/35)+1 from KeyAnalysisView as model "+condition+" GROUP BY model.riskName, model.riskQueue ORDER BY model.riskQueue";
			 List reportListAll=getHibernateTemplate().find(queryString);
			 List<ReportView> reportViewList=new LinkedList<ReportView>();
			 if(!reportListAll.isEmpty()){
			 for (Object reportList : reportListAll) {
				Object[] report = (Object[]) reportList;
				ReportView reportView=new ReportView();
				reportView.setRiskNum(Integer.parseInt(report[0].toString()));
				ReportViewId reportViewId=new ReportViewId();
				reportViewId.setRiskName(report[1].toString());
				reportView.setId(reportViewId);
				reportView.setRiskQueue(Integer.parseInt(report[2].toString()));
//				reportView.setYear(report[3].toString());
				
//				System.out.println(Integer.parseInt(new DecimalFormat("0").format(Float.parseFloat(report[4].toString()))));
				
				reportView.setRiskPro(Integer.parseInt(new DecimalFormat("0").format(Float.parseFloat(report[3].toString()))));
				reportView.setRiskValuex(Integer.parseInt(new DecimalFormat("0").format(Float.parseFloat(report[4].toString()))));
				reportViewList.add(reportView);
			}
			 }
			return  reportViewList;
			 
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

		public List<KeyAnalysisView> findDetailByReId(String reId) {
		// TODO Auto-generated method stub
		log.debug("finding all KeyAnalysisView instances");
		try {
			String queryString = "from KeyAnalysisView as model where model.ReId='"+reId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
		
		public List findRiskAdminByConditon(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				 String queryString = "select distinct reCreator from KeyAnalysisView as model "+condition;
						return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List findAdminTimeByConditon(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				 String queryString = "select distinct SUBSTRING(reDate,0,10) from KeyAnalysisView as model "+condition;
						return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List findRiskVerifierByConditon(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				 String queryString = "select distinct inputLeader from KeyAnalysisView as model "+condition;
						return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List findVerifyTimeByConditon(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				 String queryString = "select distinct SUBSTRING(verifyTime,0,10) from KeyAnalysisView as model "+condition;
						return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List<KeyAnalysisView> findDetailOrderByDep(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				String queryString = "from KeyAnalysisView as model "+condition+" order by depName ASC,rtName ASC,riskName ASC,riAllvalue DESC";
				return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List<KeyAnalysisView> findDetailOrderByRisk(String condition) {
			// TODO Auto-generated method stub
			log.debug("finding all KeyAnalysisView instances");
			try {
				String queryString = "from KeyAnalysisView as model "+condition+" order by rtName ASC,riskName ASC,depName ASC,riAllvalue DESC";
				return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
		
		public List findRiskDepDisk(String condition){
			log.debug("finding all KeyAnalysisView instances");
			try {
				 String queryString = "select depName,SUM(riAllvalue*riProdegree) from KeyAnalysisView as model "+condition+" GROUP BY depName";
						return getHibernateTemplate().find(queryString);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
}