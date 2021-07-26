package com.dao;

import java.sql.SQLException;
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

import com.model.EventFileView;
import com.model.FileManage;
import com.model.FlowFileManage;
import com.model.RiskReplyView;

/**
 * A data access object (DAO) providing persistence and search support for File
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.model.File
 * @author MyEclipse Persistence Tools
 */

public class FileManageDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(FileManageDAO.class);
	// property constants
	public static final String FILE_NAME = "fileName";

	protected void initDao() {
		// do nothing
	}

	public void save(FileManage transientInstance) {
		log.debug("saving FileManage instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FileManage persistentInstance) {
		log.debug("deleting FileManage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FileManage findById(java.lang.String id) {
		log.debug("getting FileManage instance with id: " + id);
		try {
			FileManage instance = (FileManage) getHibernateTemplate().get("com.model.FileManage",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FileManage instance) {
		log.debug("finding FileManage instance by example");
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
		log.debug("finding FileManage instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FileManage as model where model."
					+ propertyName + "= ? order by fileId asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}
	
	public List findAll() {
		log.debug("finding all FileManage instances");
		try {
			String queryString = "from FileManage order by fileId asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize) {
		//log.debug("finding all FileManage instances");
		try {
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find("from FileManage").size());
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback<String>(){

				@Override
				public String doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryString2 = "select count(*) from FileManage";
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					List result = session.createQuery("from FileManage order by fileId asc")
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List findAll(final int offset, final int pageSize,String fileNameString) {
		log.debug("finding all FileManage instances");
		try {
			final String queryString = "from FileManage as fm where fm.fileName like '%"+fileNameString+"%'  order by fileId asc";
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
			log.error("find all failed", re);getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}
	public FileManage merge(FileManage detachedInstance) {
		log.debug("merging FileManage instance");
		try {
			FileManage result = (FileManage) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FileManage instance) {
		log.debug("attaching dirty FileManage instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FileManage instance) {
		log.debug("attaching clean FileManage instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FileManageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FileManageDAO) ctx.getBean("FileManageDAO");
	}

	public List<FileManage> findAll(final int offset, final int pageSize,String fileNameString,String type) {
		//log.debug("finding all FileManage instances");
		try {
			String queryString = "from FileManage as fm where (fm.fileName like '%"+fileNameString+"%' or fm.fileId like'%" + fileNameString + "%') ";
			if(type != null && !type.equals("")) {
				queryString += " and fm.fileType='"+type+"'";
			}
			
			final String queryString1 = queryString;
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback<String>(){

				@Override
				public String doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryString2 = "select count(*) " + queryString1;
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					String queryString2 = queryString1 +" order by fileId asc";
					List result = session.createQuery(queryString2)
					              .setFirstResult(offset)
					              .setMaxResults(pageSize)
					              .list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}
	public List<FileManage> findAll(String fileNameString,String type)
	{
		try {
			String queryString = "";
			
			queryString += "from FileManage as fm where (fm.fileName like '%"+fileNameString+"%' or fm.fileId like'%" + fileNameString + "%') ";
			
			
			if(type != null && !type.equals("")) {
				queryString += " and fm.fileType='"+type+"'";
			}
			
			final String queryString1 = queryString;
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
			ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().execute(new HibernateCallback<String>(){

				@Override
				public String doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryString2 = "select count(*) " + queryString1;
					String result = session.createQuery(queryString2).uniqueResult().toString();
					session.close();
					return result;
				}
				
			}));
			List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					String queryString2 = queryString1 + " order by fileId asc";
					List result = session.createQuery(queryString2).list();
					session.close();
					return result;
				}
			});			
			return list;
		} catch (RuntimeException re) {
			//log.error("find all failed", re);
			getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}

	public List<FlowFileManage> findAllFlowFile(final int offset, final int pageSize,String fileNameString) {
		log.debug("finding all FlowFileManage instances");
		try {
			final String queryString = "from FlowFileManage as fm where fm.flowfileName like '%"+fileNameString+"%'";
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
			log.error("find all failed", re);getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}
	
	public List<FlowFileManage> findAllFlowFile(String fileNameString) {
		log.debug("finding all FlowFileManage instances");
		try {
			final String queryString = "from FlowFileManage as fm where fm.flowfileName like '%"+fileNameString+"%'";
			//ServletActionContext.getRequest().getSession().setAttribute("pagecount",getHibernateTemplate().find(queryString).size());
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
			log.error("find all failed", re);getHibernateTemplate().getSessionFactory().close();
			throw re;
		}
	}

	//通过Id查找flowfile
	public FlowFileManage findFlowById(String flowfilId) {
		log.debug("getting FlowFileManage instance with flowfileId: " + flowfilId);
		try {
			FlowFileManage instance = (FlowFileManage) getHibernateTemplate().get("com.model.FlowFileManage",
					flowfilId);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<EventFileView> findFile(String reId) {
		try {				
			String queryString = "from EventFileView as model where model.RE_Id='"+reId+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}