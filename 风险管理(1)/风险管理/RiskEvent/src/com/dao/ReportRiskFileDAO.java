package com.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.ReportRiskFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportRiskFile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportRiskFile
 * @author MyEclipse Persistence Tools
 */

public class ReportRiskFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportRiskFileDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportRiskFile transientInstance) {
		log.debug("saving ReportRiskFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public ReportRiskFile findById(String id) {
		log.debug("getting ReportRiskFile instance with id: " + id);
		try {
			ReportRiskFile instance = (ReportRiskFile) getHibernateTemplate()
					.get("com.model.ReportRiskFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportRiskFile instance) {
		log.debug("finding ReportRiskFile instance by example");
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
		log.debug("finding ReportRiskFile instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportRiskFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportRiskFile instances");
		try {
			String queryString = "from ReportRiskFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportRiskFile merge(ReportRiskFile detachedInstance) {
		log.debug("merging ReportRiskFile instance");
		try {
			ReportRiskFile result = (ReportRiskFile) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportRiskFile instance) {
		log.debug("attaching dirty ReportRiskFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportRiskFile instance) {
		log.debug("attaching clean ReportRiskFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static ReportRiskFileDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportRiskFileDAO) ctx.getBean("ReportRiskFileDAO");
	}

	/*-------------------------------- 以下是自己写的方法 --------------------------------------*/
	
	// 根据报告编号删除文件
	@SuppressWarnings("unchecked")
	public void deleteByReportId(String reportId) {
		
		List<ReportRiskFile> files = findByProperty("fileReportId", reportId);
		
//		final String queryString = "DELETE FROM ReportRiskFile WHERE fileReportId = '" + reportId + "'";
//		getHibernateTemplate().execute(new HibernateCallback<Object>() {
//
//			@Override
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				int result = session.createQuery(queryString).executeUpdate();
//				session.close();
//				return result;
//			}
//
//		});
		
		for(ReportRiskFile file:files) {
			delete(file);
		}
		
	}

	// 根据文件Id删除文件
	public void deleteByFileId(String fileId) {
		final String queryString = "DELETE FROM ReportRiskFile WHERE fileId = '" + fileId + "'";
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}

		});
	}
	
	//删除文件
	public void delete(ReportRiskFile persistentInstance) {
		
		try {
			String filePath = persistentInstance.getFileFilepath();
			deleteByFileId(persistentInstance.getFileId());
			File file = new File(filePath);
			if(file.exists()){
			    file.delete();
			}
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
	
	// 批量新增文件
	public void addFiles(final List<ReportRiskFile> files) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for(ReportRiskFile file : files) {
					session.save(file);
				}
				session.close();
				return null;
			}

		});
	}

	// 批量删除文件
	public void deleteFilesByIds(String[] deletefiles) {

		String hql = "";

		for (int i = 0; i < deletefiles.length; i++) {
			if (i == 0) {
				hql = "fileId=" + deletefiles[i];
			} else {
				hql = hql + " or fileId=" + deletefiles[i];
			}
		}
		final String queryString = "DELETE FROM ReportRiskFile WHERE " + hql;
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}

		});

	}
}