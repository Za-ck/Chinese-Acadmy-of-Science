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

import com.model.ReportDepFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportDepFile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.model.ReportDepFile
 * @author MyEclipse Persistence Tools
 */

public class ReportDepFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportDepFileDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ReportDepFile transientInstance) {
		log.debug("saving ReportDepFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public ReportDepFile findById(java.lang.String id) {
		log.debug("getting ReportDepFile instance with id: " + id);
		try {
			ReportDepFile instance = (ReportDepFile) getHibernateTemplate()
					.get("com.model.ReportDepFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportDepFile instance) {
		log.debug("finding ReportDepFile instance by example");
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
		log.debug("finding ReportDepFile instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportDepFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ReportDepFile instances");
		try {
			String queryString = "from ReportDepFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportDepFile merge(ReportDepFile detachedInstance) {
		log.debug("merging ReportDepFile instance");
		try {
			ReportDepFile result = (ReportDepFile) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportDepFile instance) {
		log.debug("attaching dirty ReportDepFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportDepFile instance) {
		log.debug("attaching clean ReportDepFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportDepFileDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportDepFileDAO) ctx.getBean("ReportDepFileDAO");
	}
	
	/*-------------------------------- 以下是自己写的方法 --------------------------------------*/
	
	// 根据报告编号删除文件
	@SuppressWarnings("unchecked")
	public void deleteByReportId(String reportId) {
		
		List<ReportDepFile> files = findByProperty("fileReportId", reportId);
		
		for(ReportDepFile file:files) {
			delete(file);
		}
		
	}

	// 根据文件Id删除文件
	public void deleteByFileId(String fileId) {
		final String queryString = "DELETE FROM ReportDepFile WHERE fileId = '" + fileId + "'";
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
	public void delete(ReportDepFile persistentInstance) {
		
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
	public void addFiles(final List<ReportDepFile> files) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for(ReportDepFile file : files) {
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
		final String queryString = "DELETE FROM ReportDepFile WHERE " + hql;
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
	
	//根据reportId和uploaderdep查找附件（因为存在多个部门编写相同的报告)
	public List<ReportDepFile> getspecificFile(String reportId, String uploaderdep) {
		
		final String queryString = "FROM ReportDepFile WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + uploaderdep + "'";
		
		List<ReportDepFile> list = getHibernateTemplate().execute( new HibernateCallback<List<ReportDepFile>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportDepFile> doInHibernate(Session session) throws HibernateException, SQLException {
				List<ReportDepFile> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
	
	//根据reportId和depId，查询附件最大的版本号
	public int queryMaxVersion(String reportId, String depId){
		final String queryMaxValueString = "SELECT MAX(fileVersion) FROM ReportDepFile WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "'";
		int maxVersion = getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				int maxValue = (Integer)session.createQuery(queryMaxValueString).uniqueResult();
				session.close();
				return maxValue;
			}
			
		});
		return maxVersion;
	}
	
	//根据reportId和depId更新附件中的标志，附加条件是附件的版本号为0
	public void updateFileVersion(String reportId, String depId){
		//查询当前数据库中版本号最大的数值
		final String queryMaxValueString = "SELECT MAX(fileVersion) FROM ReportDepFile WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "'";
		int maxVersion = getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				int maxValue = (Integer)session.createQuery(queryMaxValueString).uniqueResult();
				session.close();
				return maxValue;
			}
			
		});
		maxVersion = maxVersion + 1;
		//将附件版本号为0的附件的版本号设置为前面获取到的数值加上一
		final String updateString = "UPDATE ReportDepFile SET fileVersion = '"+ maxVersion +"' WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "' and fileVersion=0";
		getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Integer result = session.createQuery(updateString).executeUpdate();
				session.close();
				return result;
			}
			
		});
	}
	
	
	//根据reportId和depId更新附件的标志
	public void updateFileFlag(String reportId, String depId, List<String>fileIdlist) {
		
		final String queryString = "UPDATE ReportDepFile SET fileSendflag = 1 WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "'";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		
		String fileIds = "";
		for(String fileId : fileIdlist) {
			fileIds += "'" + fileId + "',";
		}
		// 用于员工在上传文件之后发现错误,以便于及时撤回修改
		if(!fileIds.equals("")) {
			fileIds = fileIds.substring(0, fileIds.length()-1);
			final String queryString1 = "UPDATE ReportDepFile SET filenewflag = 0 WHERE fileId NOT IN (" + fileIds +") AND fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "'";
			getHibernateTemplate().execute(new HibernateCallback<Integer>(){
				@Override
				public Integer doInHibernate(Session session)
						throws HibernateException, SQLException {
					Integer result = session.createQuery(queryString1).executeUpdate();
					session.close();
					return result;
				}
			});
		}
	}
	
	//根据reportId和depId更新附件的新旧标志
	public void updateNewFlag(String reportId, String depId) {
		
		final String queryString = "UPDATE ReportDepFile SET filenewflag = 0 WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "' and filenewflag=1";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		
	}
	
	// 更新最新上传的文件的发送标志
	public void updateSendFlagInRevocation(String reportId, String depId) {
		
		final String queryString = "UPDATE ReportDepFile SET fileSendflag = 0 WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "' and filenewflag=1";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		  
	}
	//更新撤回后的文件版本
	public void updateFileVersionInRevocation(String reportId, String depId){
		final String queryString = "UPDATE ReportDepFile SET fileVersion = 0 WHERE fileReportId='" + reportId + "' and fileUploaderdep='" + depId + "' and filenewflag=1";
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
	}
	
	public List<ReportDepFile> getReportFile(String reportId) {
		final String queryString = "FROM ReportDepFile WHERE fileReportId='" + reportId + "'";
		List<ReportDepFile> list = getHibernateTemplate().execute( new HibernateCallback<List<ReportDepFile>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportDepFile> doInHibernate(Session session) throws HibernateException, SQLException {
				List<ReportDepFile> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
}