package com.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.model.Department;
import com.model.Risk;
import com.model.RiskEvent;
import com.model.AllEventManageView;

public class AllEventManageViewDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory
	.getLogger(AllEventManageViewDAO.class);
	// property constants

	public static final String RE_CREATOR = "reCreator";
	public static final String RE_MODIFIER = "reModifier";
	
	protected void initDao() {
		// do nothing
	}
	
	public void save(AllEventManageView transientInstance) {
		log.debug("saving AllEventManageView instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			//throw re;
			re.printStackTrace();
		}
	}
	
	public void delete(AllEventManageView persistentInstance) {
		log.debug("deleting AllEventManageView instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			//throw re;
			re.printStackTrace();
		}
	}
	public AllEventManageView findById(java.lang.String id) {
		log.debug("getting AllEventManageView instance with id: " + id);
		try {
			AllEventManageView instance = (AllEventManageView) getHibernateTemplate().get(
					"com.model.AllEventManageView", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AllEventManageView instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AllEventManageView as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			//throw re;
			re.printStackTrace();
			return null;
		}
	}
	
	
	public List findByReCreator(Object reCreator) {
		return findByProperty(RE_CREATOR, reCreator);
	}

	public List findByReModifier(Object reModifier) {
		return findByProperty(RE_MODIFIER, reModifier);
	}
	
	 //事件处理中，显示所有部门的所有风险事件
	public List findAllEvent(final int offset, final int pageSize,final String orderbys) {
		
		try {
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback(){

				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String result = session.createQuery("select count(*) from AllEventManageView").uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub 
					List result = session.createQuery("from AllEventManageView as re "+(orderbys.equals("")?"order by re.reModifydate desc":"order by re."+orderbys))
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
			//throw re;
			re.printStackTrace();
			return null;
		}
	}

	
	//事件处理中，高级查询，查询条件:日期始dateFrom、日期终dateTo、风险编号riskId、部门名称reIndep
	@SuppressWarnings("unchecked")
	public List findEventByQueryCondition(String dateFrom,String dateTo,String reIndep,String riskDep,String reventStr, String stateId, final int offset, final int pageSize,final String orderbys) {
		try {
			/*if(dateFrom.equals("")) dateFrom="1900-01-01 00:00:00";
			if(dateTo.equals("")) {
				Date d=new Date();
				DateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateTo=f.format(d);
			}*/
			String queryState="";
			if ("0".equals(stateId)) 
		    {
			   queryState="";
			}
		    else if ("1".equals(stateId)) {
		    	queryState=" and re7.reState='*' ";  //已发布
		    }
		    else if ("2".equals(stateId)) {
		    	queryState=" and re7.reState='0' and re7.reAct='0' ";   //未修改
		    }
		    else if ("3".equals(stateId)) {
		    	queryState=" and re7.reState='0' and (re7.reAct IS NULL or re7.reAct!='0')  ";//未提交
		    }
		    else if ("4".equals(stateId)) {
		    	queryState=" and re7.reState!='*' and re7.reState!='0' and re7.reAct='0' ";  //未通过
		    }
		    else if ("5".equals(stateId)) {
		    	queryState=" and re7.reState='1' and re7.reAct!='0' ";    //已提交

		    }
		    else if ("6".equals(stateId)) {
		    	queryState=" and re7.reState!='*' and re7.reState!='0' and re7.reState!='1' and re7.reAct!='0' ";    //已通过

			}
			
			String queryString ="";
			
			if(reIndep.equals("none1")){
				queryString = "from AllEventManageView as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' "+queryState;
			}
			else{
				queryString = "from AllEventManageView as re7 where re7.reDate between '"+dateFrom+"' and '"+dateTo+"' and re7.reIndep = '"+reIndep+"' "+queryState;
			}
			
			if(reventStr != null && !reventStr.equals("")) {
				
				queryString += " and (re7.id.reId like '%" + reventStr + "%' or re7.reEventname like '%" + reventStr + "%') ";
				
			}
			
			if(!riskDep.equals("--请选择--")){
				
				queryString += " and (re7.riInchargedep = '"+riskDep+"' or (re7.riInchargedep='本部门' and re7.depName='"+riskDep+"')) ";
			}
			
			queryString += " " + (orderbys.equals("")?"order by re7.reDate desc":"order by re7."+orderbys);
			
			//System.out.println(queryString);
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
			final String queryString1 =queryString;
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
			//throw re;
			re.printStackTrace();
			return null;
		}	
	}

}
