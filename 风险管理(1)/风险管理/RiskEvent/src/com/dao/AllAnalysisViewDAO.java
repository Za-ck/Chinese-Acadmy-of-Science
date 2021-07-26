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
 * @see com.model.AllAnalysisView
 * @author MyEclipse Persistence Tools
 */

public class AllAnalysisViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AllAnalysisViewDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(AllAnalysisView transientInstance) {
		log.debug("saving AllAnalysisView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AllAnalysisView persistentInstance) {
		log.debug("deleting AllAnalysisView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AllAnalysisView findById(String reId) {
		log.debug("getting AllAnalysisView instance with id: " + reId);
		try {
			AllAnalysisView instance = (AllAnalysisView) getHibernateTemplate()
					.get("reId", reId);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AllAnalysisView instance) {
		log.debug("finding AllAnalysisView instance by example");
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
		log.debug("finding AllAnalysisView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AllAnalysisView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "from AllAnalysisView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AllAnalysisView merge(AllAnalysisView detachedInstance) {
		log.debug("merging AllAnalysisView instance");
		try {
			AllAnalysisView result = (AllAnalysisView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AllAnalysisView instance) {
		log.debug("attaching dirty AllAnalysisView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AllAnalysisView instance) {
		log.debug("attaching clean AllAnalysisView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AllAnalysisViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AllAnalysisViewDAO) ctx.getBean("AllAnalysisViewDAO");
	}
	
	//查找有记录的部门
	public List findDep(String year) {
		
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model  where model.year ='"+year+"' order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//根据部门查找该部门的风险事件
	public List findRiskEvent(final String year,final String dep) {
		log.debug("finding all AllAnalysisView instances");
		
		
		try {
			 String queryString = "from AllAnalysisView as model where model.reIndep = '"+dep+"' and model.year ='"+year+"'order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByDate(String dateFrom, String dateTo) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model  where model.reDate between '"+dateFrom+"' and '"+dateTo+"' order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRisk(String year, String dep) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where model.reIndep = '"+dep+"' and model.year ='"+year+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//找到二级风险的所有风险事件
	public List<AllAnalysisView> findDetail(String year, String depId,
			String riskId) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "from AllAnalysisView as model where model.year='"+year+"' and model.reIndep='"+depId+"' and  model.riskId='"+riskId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model "+condition+" order by model.depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findDepByConditon1(String state) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			if("未应对".equals(state)){
				queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where (len(model.ritaketime)<=5) order by model.depQueue";					
			}else{
				queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where (len(model.ritaketime)>5) order by model.depQueue";
			}
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByDep(String depid) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where model.reIndep='"+depid+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<AllAnalysisView> findDetailByCondition(String depid,
			String riskName, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			if(condition.equals("")){condition=" where ";}
			else{condition+=" and ";}
			String queryString = "from AllAnalysisView as model "+condition+" model.reIndep='"+depid+"' and  model.riskName='"+riskName+"' ORDER BY riAllvalue DESC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<AllAnalysisView> findDetailByCondition1(String depname,String riskId, String state) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			if("未应对".equals(state)){
				queryString = "from AllAnalysisView as model where (len(model.ritaketime)<=5) and model.depName='"+depname+"' and  model.riskId='"+riskId+"'";
			}else{
				queryString = "from AllAnalysisView as model where (len(model.ritaketime)>5) and model.depName='"+depname+"' and  model.riskId='"+riskId+"'";
			}
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findRiskNew(String year) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct riskId,riskName,riskQueue,rtName from AllAnalysisView as model where model.year ='"+year+"' order by riskQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByRisk(String year, String riskId) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where model.riskId = '"+riskId+"' and model.year ='"+year+"' order by depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct riskName,rtName from AllAnalysisView as model "+condition+" order by rtName,riskName";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findRiskTypeByConditon(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct rtName from AllAnalysisView as model "+condition;
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDepByRiskId(String riskId) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where model.riskId = '"+riskId+"' order by depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findDepByRiskName(String riskName) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where model.riskName= '"+riskName+"' order by depQueue";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByDepAndCondition(String depid, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			if(condition.equals("")){
				return findRiskByDep(depid);
			}else{
			 String queryString = "select distinct riskName from AllAnalysisView as model "+condition+" and model.reIndep='"+depid+"' order by riskName";
					return getHibernateTemplate().find(queryString);
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findRiskByDepAndCondition1(String depname, String state) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			if("未应对".equals(state)){
				queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where  (len(model.ritaketime)<=5) and model.depName='"+depname+"' order by riskQueue";
			}else{
				queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where  (len(model.ritaketime)>5)  and model.depName='"+depname+"' order by riskQueue";
			}
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMaxAndMinValue(String year){
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select MAX(riAllvalue),MIN(riAllvalue) from AllAnalysisView as model where model.year='"+year+"'";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMaxAndMinDegree(String year){
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select MAX(riAlldegree),MIN(riAlldegree) from AllAnalysisView as model where model.year='"+year+"'";
					return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ReportView> findReportList(String condition){
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select COUNT(*),riskName,riskQueue, FLOOR(AVG(riProdegree)), FLOOR((AVG(riAllvalue)-1)/35)+1 from AllAnalysisView as model "+condition+" GROUP BY model.riskName, model.riskQueue ORDER BY model.riskQueue";
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
	
	public List<AllAnalysisView> findDetailByReId(String reId) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "from AllAnalysisView as model where model.ReId='"+reId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRisk_manage(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where  "+condition;
			//System.out.println("执行的sql语句为："+queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<AllAnalysisView> findDetail_manage(String riskId,String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "from AllAnalysisView as model where model.riskId='"+riskId+"' and "+condition+" order by riAllvalue desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByrtName(String rtName,String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select distinct riskName from AllAnalysisView as model "+condition+" and rtName = '"+rtName+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public List<AllAnalysisView> findDetailByRiskName(String riskName, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			if(condition.equals("")){condition=" where ";}
			else{condition+=" and ";}
			String queryString = "from AllAnalysisView as model "+condition+" model.riskName='"+riskName+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findFile(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			 String queryString = "select fileName,count(*) from AllAnalysisView as model " +
			 condition+" and fileName != ''"+" GROUP BY fileName";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findDep_manage(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "select distinct reIndep,depName,depQueue from AllAnalysisView as model where "+condition;					
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findRiskByDepAndCondition2(String depname, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "select distinct riskId,riskName,riskQueue from AllAnalysisView as model where model.depName='"+depname+"' and "+condition+" order by riskQueue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<AllAnalysisView> findDetailByCondition2(String depname,
			String riskId, String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "from AllAnalysisView as model where model.depName='"+depname+"' and  model.riskId='"+riskId+"' and "+condition+" order by riAllvalue desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AllAnalysisView> findRisk135_new(String userName,
			String condition) {
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "from AllAnalysisView as model where model.reCreator='"+userName+"' and "+condition+" order by depName ASC,riskName ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AllAnalysisView> findRisk_new(String condition) {
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "";
			queryString = "from AllAnalysisView as model where "+condition+" order by depName ASC,riskName ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public List<AllAnalysisView> findDetailOrderByDep(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "from AllAnalysisView as model "+condition+" order by depName ASC,rtName ASC,riskName ASC,riAllvalue DESC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<AllAnalysisView> findDetailOrderByRisk(String condition) {
		// TODO Auto-generated method stub
		log.debug("finding all AllAnalysisView instances");
		try {
			String queryString = "from AllAnalysisView as model "+condition+" order by rtName ASC,riskName ASC,depName ASC,riAllvalue DESC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}