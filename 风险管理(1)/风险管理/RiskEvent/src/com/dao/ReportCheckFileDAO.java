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

import com.model.ReportCheckFile;


public class ReportCheckFileDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReportCheckFileDAO.class);
	// property constants
	public static final String FILE_REPORT_ID = "fileReportId";
	public static final String FILE_FILENAME = "fileFilename";
	public static final String FILE_FILEPATH = "fileFilepath";
	public static final String FILE_DATE = "fileDate";
	public static final String FILE_UPLOADERNAME = "fileUploadername";
	public static final String FILE_UPLOADER = "fileUploader";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportCheckFile transientInstance) {
		log.debug("saving ReportCheckFile instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public ReportCheckFile findById(java.lang.String id) {
		log.debug("getting ReportCheckFile instance with id: " + id);
		try {
			ReportCheckFile instance = (ReportCheckFile) getHibernateTemplate()
					.get("com.model.ReportCheckFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportCheckFile instance) {
		log.debug("finding ReportCheckFile instance by example");
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
		log.debug("finding ReportCheckFile instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReportCheckFile as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFileReportId(Object fileReportId) {
		return findByProperty(FILE_REPORT_ID, fileReportId);
	}

	public List findByFileFilename(Object fileFilename) {
		return findByProperty(FILE_FILENAME, fileFilename);
	}

	public List findByFileFilepath(Object fileFilepath) {
		return findByProperty(FILE_FILEPATH, fileFilepath);
	}

	public List findByFileDate(Object fileDate) {
		return findByProperty(FILE_DATE, fileDate);
	}

	public List findByFileUploadername(Object fileUploadername) {
		return findByProperty(FILE_UPLOADERNAME, fileUploadername);
	}

	public List findByFileUploader(Object fileUploader) {
		return findByProperty(FILE_UPLOADER, fileUploader);
	}

	public List findAll() {
		log.debug("finding all ReportCheckFile instances");
		try {
			String queryString = "from ReportCheckFile";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportCheckFile merge(ReportCheckFile detachedInstance) {
		log.debug("merging ReportCheckFile instance");
		try {
			ReportCheckFile result = (ReportCheckFile) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportCheckFile instance) {
		log.debug("attaching dirty ReportCheckFile instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportCheckFile instance) {
		log.debug("attaching clean ReportCheckFile instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportCheckFileDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportCheckFileDAO) ctx.getBean("ReportCheckFileDAO");
	}
	
/*-------------------------------- 以下是自己写的方法 --------------------------------------*/
	
	// 根据报告编号删除文件
	@SuppressWarnings("unchecked")
	public void deleteByReportId(String reportId) {
		
		List<ReportCheckFile> files = findByProperty("fileReportId", reportId);
		
		for(ReportCheckFile file:files) {
			delete(file);
		}
		
	}

	// 根据文件Id删除文件
	public void deleteByFileId(String fileId) {
		final String queryString = "DELETE FROM ReportCheckFile WHERE fileId = '" + fileId + "'";
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
	public void delete(ReportCheckFile persistentInstance) {
		
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
	public void addFiles(final List<ReportCheckFile> files) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for(ReportCheckFile file : files) {
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
		final String queryString = "DELETE FROM ReportCheckFile WHERE " + hql;
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
	
	//根据reportId查找附件
	public List<ReportCheckFile> getspecificFile(String reportId) {
		
		final String queryString = "FROM ReportCheckFile WHERE fileReportId='" + reportId + "'";
		
		List<ReportCheckFile> list = getHibernateTemplate().execute( new HibernateCallback<List<ReportCheckFile>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ReportCheckFile> doInHibernate(Session session) throws HibernateException, SQLException {
				List<ReportCheckFile> list = session.createQuery(queryString).list();
				session.close();
				return list;
			}
		});
		return list;
	}
	
	//根据reportId和depId更新附件的新旧标志
	public void updateNewFlag(String reportId) {
		
		final String queryString = "UPDATE ReportCheckFile SET filenewflag = 0 WHERE fileReportId='" + reportId + "' and filenewflag=1";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		
	}
	
	//根据reportId更新附件的标志,附加条件是附件的版本号为0
	public void updateFileVersion(String reportId){
		//查询当前数据库中版本号最大的数值
		final String queryMaxValueString = "SELECT MAX(fileVersion) FROM ReportCheckFile WHERE fileReportId='" + reportId + "'";
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
		final String updateString = "UPDATE ReportCheckFile SET fileVersion = '"+ maxVersion +"' WHERE fileReportId='" + reportId + "' and fileVersion=0";
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
	
	//根据reportId，查询附件最大的版本号
	public int queryMaxVersion(String reportId){
		final String queryMaxValueString = "SELECT MAX(fileVersion) FROM ReportCheckFile WHERE fileReportId='" + reportId + "'";
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
	//根据reportId更新附件的标志
	public void updateFileFlag(String reportId, List<String>fileIdlist) {
		
		final String queryString = "UPDATE ReportCheckFile SET fileSendflag = 1 WHERE fileReportId='" + reportId + "'";
		
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
		if(!fileIds.equals("")) {
			fileIds = fileIds.substring(0, fileIds.length()-1);
			final String queryString1 = "UPDATE ReportCheckFile SET filenewflag = 0 WHERE fileId NOT IN (" + fileIds +") AND fileReportId='" + reportId + "'";
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
	
	// 更新最新上传的文件的发送标志
	public void updateSendFlagInRevocation(final String reportId) {
		
		final String queryString = "UPDATE ReportCheckFile SET fileSendflag = 0 WHERE fileReportId='" + reportId + "' and filenewflag=1";
		
		getHibernateTemplate().execute( new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				
				Integer result = session.createQuery(queryString).executeUpdate();
				session.close();
				return result;
			}
		});
		
	}
}