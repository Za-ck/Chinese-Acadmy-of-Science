package com.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.model.RiskEventQueryView;
import com.model.RiskEventQueryViewId;

/**
 * A data access object (DAO) providing persistence and search support for
 * RiskEventQueryView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.RiskEventQueryView
 * @author MyEclipse Persistence Tools
 */

public class RiskEventQueryViewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RiskEventQueryViewDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(RiskEventQueryView transientInstance) {
		log.debug("saving RiskEventQueryView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RiskEventQueryView persistentInstance) {
		log.debug("deleting RiskEventQueryView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RiskEventQueryView findById(com.model.RiskEventQueryViewId id) {
		log.debug("getting RiskEventQueryView instance with id: " + id);
		try {
			RiskEventQueryView instance = (RiskEventQueryView) getHibernateTemplate()
					.get("com.model.RiskEventQueryView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RiskEventQueryView instance) {
		log.debug("finding RiskEventQueryView instance by example");
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
		log.debug("finding RiskEventQueryView instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RiskEventQueryView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RiskEventQueryView instances");
		try {
			String queryString = "from RiskEventQueryView";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//全部风险事件
	public List findAll(final int offset, final int pageSize,final String orderbys) {
		//log.debug("finding all RiskEventQueryView instances");
		try {
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from RiskEventQueryView").size());
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					 
					List result = session.createQuery("from RiskEventQueryView as reqv"+(orderbys.equals("")?" order by reqv.reDate desc":" order by reqv."+orderbys))
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
	//RiskEventQueryAction,条件查询，查询条件:日期始dateFrom、日期终dateTo、录入部门名称reIndep、管理责任部门名称riskDep
	@SuppressWarnings({ "unchecked", "unused" })
	public List reqQuery(String dateFrom,String dateTo,String reIndep,String choosedId,String riskDep,String reventStr, String stateId,final int offset, final int pageSize,final String orderbys) {
		try {	
			
			//String queryString ="from RiskEventQueryView as reqv where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			String queryString ="from RiskEventQueryView as reqv";
			
			if("0".equals(choosedId)){
				queryString+=" where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			else if("1".equals(choosedId)){
				queryString+=" where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			else if("2".equals(choosedId)){
				queryString+=" where (reqv.reModifydate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			
			
			if(!riskDep.equals("--请选择--")){
				
				queryString += " and (reqv.reinchargedep = '"+riskDep+"' or (reqv.reinchargedep='本部门' and reqv.depName='"+riskDep+"')) ";
			}
			if(!reIndep.equals("--请选择--")){
				queryString += " and reqv.depName = '"+reIndep+"' ";
			}
			if(reventStr != null && !reventStr.equals("")) {
				
				queryString += " and (reqv.id.reId like '%" + reventStr + "%' or reqv.reEventname like '%" + reventStr + "%') ";
				
			}
			
			String queryState="";
			if ("0".equals(stateId)) 
		    {
			   queryState="";
			}
		    else if ("1".equals(stateId)) {
		    	queryState=" and reqv.reState='*' ";  //已发布
		    }
		    else if ("2".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and reqv.reAct='0' ";   //未修改
		    }
		    else if ("3".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and (reqv.reAct IS NULL or reqv.reAct!='0')  ";//未提交
		    }
		    else if ("4".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reAct='0' ";  //未通过
		    }
		    else if ("5".equals(stateId)) {
		    	queryState=" and reqv.reState='1' and reqv.reAct!='0' ";    //已提交

		    }
		    else if ("6".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reState!='1' and reqv.reAct!='0' ";    //已通过

			}
			queryString += queryState;
			
			if(orderbys.equals("")&&"0".equals(choosedId)){
				queryString += " order by reqv.reDate desc";
			}
			else if(orderbys.equals("")&&"1".equals(choosedId)){
				queryString += " order by reqv.reDate desc";
			}
			else if(orderbys.equals("")&&"2".equals(choosedId)){
				queryString += " order by reqv.reModifydate desc";
			}
//			if(orderbys != null && !orderbys.equals("")) {
//				
//				queryString += " order by reqv."+orderbys;
//				
//			}
//			else {
//				
//				queryString += " order by reqv.reDate desc";
//				
//			}
			
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			final String queryString1 = queryString;
			final String queryString2 = "SELECT COUNT(*) " + queryString.substring(0, queryString.indexOf("order by"));
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
					List result = session.createQuery(queryString1)
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
	@SuppressWarnings({ "unchecked", "unused" })
	public List reqQuery1(String dateFrom,String dateTo,String reIndep,String choosedId,String riskDep,String reventStr, String stateId,final String orderbys) {
		try {	
			
			//String queryString ="from RiskEventQueryView as reqv where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			String queryString ="from RiskEventQueryView as reqv";
			
			if("0".equals(choosedId)){
				queryString+=" where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			else if("1".equals(choosedId)){
				queryString+=" where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			else if("2".equals(choosedId)){
				queryString+=" where (reqv.reModifydate between '"+dateFrom+"' and '"+dateTo+"') ";
			}
			
			
			if(!riskDep.equals("--请选择--")){
				
				queryString += " and (reqv.reinchargedep = '"+riskDep+"' or (reqv.reinchargedep='本部门' and reqv.depName='"+riskDep+"')) ";
			}
			if(!reIndep.equals("--请选择--")){
				queryString += " and reqv.depName = '"+reIndep+"' ";
			}
			if(reventStr != null && !reventStr.equals("")) {
				
				queryString += " and (reqv.id.reId like '%" + reventStr + "%' or reqv.reEventname like '%" + reventStr + "%') ";
				
			}
			
			String queryState="";
			if ("0".equals(stateId)) 
		    {
			   queryState="";
			}
		    else if ("1".equals(stateId)) {
		    	queryState=" and reqv.reState='*' ";  //已发布
		    }
		    else if ("2".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and reqv.reAct='0' ";   //未修改
		    }
		    else if ("3".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and (reqv.reAct IS NULL or reqv.reAct!='0')  ";//未提交
		    }
		    else if ("4".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reAct='0' ";  //未通过
		    }
		    else if ("5".equals(stateId)) {
		    	queryState=" and reqv.reState='1' and reqv.reAct!='0' ";    //已提交

		    }
		    else if ("6".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reState!='1' and reqv.reAct!='0' ";    //已通过

			}
			queryString += queryState;
			
			if(orderbys.equals("")&&"0".equals(choosedId)){
				queryString += " order by reqv.reDate desc";
			}
			else if(orderbys.equals("")&&"1".equals(choosedId)){
				queryString += " order by reqv.reDate desc";
			}
			else if(orderbys.equals("")&&"2".equals(choosedId)){
				queryString += " order by reqv.reModifydate desc";
			}
//			if(orderbys != null && !orderbys.equals("")) {
//				
//				queryString += " order by reqv."+orderbys;
//				
//			}
//			else {
//				
//				queryString += " order by reqv.reDate desc";
//				
//			}
			
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			final String queryString1 = queryString;
			final String queryString2 = "SELECT COUNT(*) " + queryString.substring(0, queryString.indexOf("order by"));
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
					List result = session.createQuery(queryString1)
					             
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
	////RiskEventQueryAction,条件查询，查询条件:日期始dateFrom、日期终dateTo、录入部门名称reIndep、管理责任部门名称riskDep-----不分页
	public List reqQuery(String dateFrom,String dateTo,String reIndep,String riskDep,String reventStr, String stateId) {
		try {
			/*if(dateFrom.equals("")) dateFrom="1900-01-01";
			if(dateTo.equals("")) {
				Date d=new Date();
				DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
				dateTo=f.format(d);
			}*/
			String queryString ="from RiskEventQueryView as reqv where (reqv.reDate between '"+dateFrom+"' and '"+dateTo+"') ";
			
			if(!riskDep.equals("--请选择--")){
				
				queryString += " and (reqv.reinchargedep = '"+riskDep+"' or (reqv.reinchargedep='本部门' and reqv.depName='"+riskDep+"')) ";
			}
			if(!reIndep.equals("--请选择--")){
				queryString += " and reqv.depName = '"+reIndep+"' ";
			}
			if(reventStr != null && !reventStr.equals("")) {
				
				queryString += " and (reqv.id.reId like '%" + reventStr + "%' or reqv.reEventname like '%" + reventStr + "%') ";
				
			}
			
			String queryState="";
			if ("0".equals(stateId)) 
		    {
			   queryState="";
			}
		    else if ("1".equals(stateId)) {
		    	queryState=" and reqv.reState='*' ";  //已发布
		    }
		    else if ("2".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and reqv.reAct='0' ";   //未修改
		    }
		    else if ("3".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and (reqv.reAct IS NULL or reqv.reAct!='0')  ";//未提交
		    }
		    else if ("4".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reAct='0' ";  //未通过
		    }
		    else if ("5".equals(stateId)) {
		    	queryState=" and reqv.reState='1' and reqv.reAct!='0' ";    //已提交

		    }
		    else if ("6".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reState!='1' and reqv.reAct!='0' ";    //已通过

			}
			queryString += queryState;
			
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			final String queryString1 = queryString;
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {			
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString1).list();
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
	
	//本年度风险事件
	@SuppressWarnings("unchecked")
	public List reqCurrentYear(String dateFrom,String dateTo,final int offset, final int pageSize) {
		try {			
			final String queryString = "from RiskEventQueryView as reqv where reqv.reDate between '"+dateFrom+"' and '"+dateTo+"' order by reqv.reDate desc";			
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
	
	
	public RiskEventQueryView merge(RiskEventQueryView detachedInstance) {
		log.debug("merging RiskEventQueryView instance");
		try {
			RiskEventQueryView result = (RiskEventQueryView) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RiskEventQueryView instance) {
		log.debug("attaching dirty RiskEventQueryView instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RiskEventQueryView instance) {
		log.debug("attaching clean RiskEventQueryView instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RiskEventQueryViewDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RiskEventQueryViewDAO) ctx.getBean("RiskEventQueryViewDAO");
	}
	

	
	//本部门录入和其归口的风险事件
	public List finddepre(String depid,String dep,final int offset, final int pageSize,final String orderbys) {
		try {
			final String queryString="from RiskEventQueryView as reqv  where reqv.indepid='"+depid+"' or reqv.reinchargedep='"+dep+"'"+(orderbys.equals("")?" order by reqv.reDate desc":" order by reqv."+orderbys);
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
	//本部门录入和其归口的风险事件--全部，不分页
	public List finddepre(String depid,String dep) {
		try {
			final String queryString="from RiskEventQueryView as reqv  where reqv.indepid='"+depid+"' or reqv.reinchargedep='"+dep+"'"+" order by reqv.reDate desc";
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
								public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString).list();
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
	
	
	//本部门录入和其归口的风险事件的高级查询
	@SuppressWarnings("unchecked")
	public List finddepadvanced(String depid,String dep,String dateFrom,String dateTo, String reventStr, String stateId, final int offset, final int pageSize,final String orderbys) {
		try {
			//final String queryString = "from RiskEventQueryView as reqv where reqv.reDate between '" + dateFrom+ "' and '" + dateTo+ "' and (reqv.indepid='"+depid+"' or reqv.reinchargedep='"+dep+"')"+(orderbys.equals("")?" order by reqv.reDate desc":" order by reqv."+orderbys);
			String queryString = "from RiskEventQueryView as reqv where reqv.reDate between '" + dateFrom+ "' and '" + dateTo+ "' and (reqv.indepid='"+depid+"' or reqv.reinchargedep='"+dep+"')";
			if(reventStr != null && !reventStr.equals("")) {
				queryString += " and (reqv.id.reId like '%" + reventStr +"%' or reqv.reEventname like '%"+ reventStr+"%') ";
			}
			String queryState="";
			if ("0".equals(stateId)) 
		    {
			   queryState="";
			}
		    else if ("1".equals(stateId)) {
		    	queryState=" and reqv.reState='*' ";  //已发布
		    }
		    else if ("2".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and reqv.reAct='0' ";   //未修改
		    }
		    else if ("3".equals(stateId)) {
		    	queryState=" and reqv.reState='0' and (reqv.reAct IS NULL or reqv.reAct!='0')  ";//未提交
		    }
		    else if ("4".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reAct='0' ";  //未通过
		    }
		    else if ("5".equals(stateId)) {
		    	queryState=" and reqv.reState='1' and reqv.reAct!='0' ";    //已提交

		    }
		    else if ("6".equals(stateId)) {
		    	queryState=" and reqv.reState!='*' and reqv.reState!='0' and reqv.reState!='1' and reqv.reAct!='0' ";    //已通过

			}
			queryString += queryState;
			queryString += (orderbys.equals("")?" order by reqv.reDate desc":" order by reqv."+orderbys);
			final String queryString1 = queryString;
			final String queryString2 = "SELECT COUNT(*) " + queryString.substring(0, queryString.indexOf("order by"));
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
					List result = session.createQuery(queryString1)
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
	//本部门录入和其归口的风险事件的高级查询--不分页
	public List finddepadvanced(String depid,String dep,String dateFrom,String dateTo) {
		try {
			final String queryString = "from RiskEventQueryView as reqv where reqv.reDate between '" + dateFrom+ "' and '" + dateTo+ "' and (reqv.indepid='"+depid+"' or reqv.reinchargedep='"+dep+"')"+" order by reqv.reDate desc";
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery(queryString).list();
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
}