package com.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.AllAnalysisView;
import com.model.RiskAssessSituationView;

public class RiskAssessSituationViewDAO extends HibernateDaoSupport{

public List findAll() {
		
		Calendar time = Calendar.getInstance();
		int currentYear;
		int currentQuarter = 0;
		int currentMonth;
		currentYear = time.get(Calendar.YEAR);
		currentMonth = time.get(Calendar.MONTH)+1;
		switch(currentMonth)
		{
		case 1:
		case 2:
		case 3:
			currentQuarter+=1;
			break;
		case 4:
        case 5:
        case 6:
        	currentQuarter+=2;
        	break ;
        case 7:
        case 8:
        case 9:
        	currentQuarter+=3;
        	break ;
        case 10:
        case 11:
        case 12:
        	currentQuarter+=4;
		}
		try {
			//String queryString = "from RiskAssessView ORDER BY raDepId,raYear,raQuarter";
			//默认查询为上一季度的考核分数，如果当前季度为第一季度，则查询上一年的最后一个季度
			//if(currentQuarter==1)
			//{
				//currentQuarter=4;
				//currentYear=currentYear-1;
			//}
			//else
				//currentQuarter=currentQuarter-1;
			
			String queryString = "from RiskAssessSituationView where raYear='"+currentYear+"'and raQuarter='"+currentQuarter+"' AND raDepId!='FB' and depAssess=1 ORDER BY raDepId";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
public List reqQuery(String Year,String YearTo,String DepId,String Quarter,String QuarterTo) {
	try {
		
		String queryString ="";
		
		 if(DepId.equals("none1")){
			 if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)<=0)
				 queryString = "from RiskAssessSituationView as model where model.raYear Between'"+Year+"' and '"+YearTo+"'and model.raQuarter between '"+Quarter+"' and '"+QuarterTo+"'";
			 else if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)>0)
				 queryString = "from RiskAssessSituationView as model where model.raYear Between'"+Year+"' and '"+YearTo+"'and model.raQuarter between '"+QuarterTo+"' and '"+Quarter+"'";
			 else if(Year.compareTo(YearTo)<0)
				 queryString = "from RiskAssessSituationView as model where (model.raYear ='"
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
				 queryString = "from RiskAssessSituationView as model where (model.raYear ='"
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
		 }
		 else if(!DepId.equals("none1")){
			 if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)<=0)
				 queryString = "from RiskAssessSituationView as model where model.raYear Between'"+Year+"' and '"+YearTo+"'and model.raQuarter between '"+Quarter+"' and '"+QuarterTo+"' and model.raDepId = '"+DepId+ "'";
			 else if(Year.equals(YearTo)&& Quarter.compareTo(QuarterTo)>0)
				 queryString = "from RiskAssessSituationView as model where model.raYear Between'"+Year+"' and '"+YearTo+"'and model.raQuarter between '"+QuarterTo+"' and '"+Quarter+"' and model.raDepId = '"+DepId+ "'";
			 else if(Year.compareTo(YearTo)<0)
				 queryString = "from RiskAssessSituationView as model where ((model.raYear ='"
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
						+ "'))" + " and model.raDepId = '"+DepId+ "'";
			 else
				 queryString = "from RiskAssessSituationView as model where ((model.raYear ='"
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
						+ "'))" + " and model.raDepId = '"+DepId+ "'";
		}
		queryString+=" AND model.raDepId!='FB' and depAssess=1 ORDER BY model.raDepId,raYear,raQuarter";
		final String queryString1 =queryString;
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
		
		throw re;
	}	
}

public List<RiskAssessSituationView> findRisk(String reIndep,String year,String yearto,String quarter,String quarterto) {

	try {
		String queryString = "";
		String datem="";
		String datemTo="";
		if("1".equals(quarter)){
			datem="-01-01 00:00:00";
		}
		else if("2".equals(quarter)){
			datem="-04-01 00:00:00";
		}
		else if("3".equals(quarter)){
			datem="-07-01 00:00:00";
		}
		else{
			datem="-10-01 00:00:00";
		}
		
		if("1".equals(quarterto)){
			datemTo="-03-31 23:59:59";
		}
		else if("2".equals(quarterto)){
			datemTo="-06-30 23:59:59";
		}
		else if("3".equals(quarter)){
			datemTo="-09-30 23:59:59";
		}
		else{
			datemTo="-12-31 23:59:59";
		}
		queryString = "from AllAnalysisView as model where  model.reIndep='"+reIndep+"' and (model.reDate between '"+year+datem+"' and '"+yearto+datemTo+"') ";//and order by depName ASC
		return getHibernateTemplate().find(queryString);
	} catch (RuntimeException re) {
		
		throw re;
	}
}	
public List<RiskAssessSituationView> findRisk1(String reIndep,String year,String yearto,String quarter,String quarterto) {

	try {
		if(Integer.parseInt(year)<2017){
			year="2017";
		}
		String yearDate="2017-01-01 00:00:00";
		String queryString = "";
		String datem="";
		String datemTo="";
		if("1".equals(quarter)){
			datem="-01-01 00:00:00";
		}
		else if("2".equals(quarter)){
			datem="-04-01 00:00:00";
		}
		else if("3".equals(quarter)){
			datem="-07-01 00:00:00";
		}
		else{
			datem="-10-01 00:00:00";
		}
		
		if("1".equals(quarterto)){
			datemTo="-03-31 23:59:59";
		}
		else if("2".equals(quarterto)){
			datemTo="-06-30 23:59:59";
		}
		else if("3".equals(quarter)){
			datemTo="-09-30 23:59:59";
		}
		else{
			datemTo="-12-31 23:59:59";
		}
		//len(model.ritaketime)>5
		//queryString = "from AllAnalysisView as model where model.ritaketime between '"+year+datem+"' and '"+yearto+datemTo+"' and model.reIndep='"+reIndep+"' and model.reDate>='"+yearDate+"'" ;//and order by depName ASC
		queryString = "from AllAnalysisView as model where model.reDate between '"+year+datem+"' and '"+yearto+datemTo+"' and model.reIndep='"+reIndep+"' and len(model.ritaketime)>5" ;//and order by depName ASC
		return getHibernateTemplate().find(queryString);
	} catch (RuntimeException re) {
		
		throw re;
	}
}	
public List<RiskAssessSituationView> findRisk2(String reIndep,String year,String yearto,String quarter,String quarterto) {

	try {
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
		String TimeString = time.format(new java.util.Date());
		//System.out.println(TimeString);
		if(Integer.parseInt(year)<2017){
			year="2017";
		}
		String yearDate="2017-01-01 00:00:00";
		String queryString = "";
		String datem="";
		String datemTo="";
		if("1".equals(quarter)){
			datem="-01-01 00:00:00";
		}
		else if("2".equals(quarter)){
			datem="-04-01 00:00:00";
		}
		else if("3".equals(quarter)){
			datem="-07-01 00:00:00";
		}
		else{
			datem="-10-01 00:00:00";
		}
		
		if("1".equals(quarterto)){
			datemTo="-03-31 23:59:59";
		}
		else if("2".equals(quarterto)){
			datemTo="-06-30 23:59:59";
		}
		else if("3".equals(quarter)){
			datemTo="-09-30 23:59:59";
		}
		else{
			datemTo="-12-31 23:59:59";
		}
		//len(model.ritaketime)>5
		//queryString = "from AllAnalysisView as model where model.ritaketime between '"+year+datem+"' and '"+yearto+datemTo+"' and model.reIndep='"+reIndep+"' and model.reDate>='"+yearDate+"'" ;//and order by depName ASC
		queryString = "from AllAnalysisView as model where model.reDate between '"+year+datem+"' and '"+yearto+datemTo+"' and model.reIndep='"+reIndep+"' and (model.ritaketime is NULL or len(model.ritaketime)<=5) and (model.riplandate<'"+TimeString+"')" ;//and order by depName ASC
		return getHibernateTemplate().find(queryString);
	} catch (RuntimeException re) {
		
		throw re;
	}
}	
	public List findAlert(String DepId,String Date,String DateTo){
		//ritaketime
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
		String TimeString = time.format(new java.util.Date());
		try{
		String queryString = "";
		if(Date.compareTo(DateTo)<=0) {
			//queryString = "from AllAnalysisView as model where model.riplandate <='"+DateTo+"' and model.reIndep = '"+DepId+ "'";
		    //修改后
			queryString = "from AllAnalysisView as model where (model.ritaketime is NULL or len(model.ritaketime)<=5) and  model.reDate between'"+Date+"' and '"+DateTo+"' and model.reIndep = '"+DepId+ "' and (model.riplandate<'"+TimeString+"')";
		}
			 
		 else {
			// queryString = "from AllAnalysisView as model where model.riplandate <='"+Date+"' and model.reIndep = '"+DepId+ "'";
			 queryString = "from AllAnalysisView as model where (model.ritaketime is NULL or len(model.ritaketime)<=5) and  model.reDate between'"+Date+"' and '"+DateTo+"' and model.reIndep = '"+DepId+ "' and (model.riplandate<'"+TimeString+"')";
		 }
			 
		//queryString+=" and model.riSign=0 ORDER BY model.riplandate";
		queryString+=" and model.riSign=0 ORDER BY model.ritaketime";
		final String queryString1 =queryString;
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
		
		throw re;
	}	
	}
	public List findAlert1(String DepId,String Date,String DateTo){
		//ritaketime
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
		String TimeString = time.format(new java.util.Date());
		System.out.println("????????????????????????????????"+Date);
		try{
		String queryString = "";
		if(Date.compareTo(DateTo)<=0) {
			//queryString = "from AllAnalysisView as model where model.riplandate <='"+DateTo+"' and model.reIndep = '"+DepId+ "'";
		    //修改后
			queryString = "from AllAnalysisView as model where (model.ritaketime is NULL or len(model.ritaketime)<=5) and  model.reDate between'"+Date+"' and '"+DateTo+"' and model.reIndep = '"+DepId+ "' and (model.riplandate>='"+TimeString+"')";
		}
			 
		 else {
			// queryString = "from AllAnalysisView as model where model.riplandate <='"+Date+"' and model.reIndep = '"+DepId+ "'";
			 queryString = "from AllAnalysisView as model where (model.ritaketime is NULL or len(model.ritaketime)<=5) and  model.reDate between'"+Date+"' and '"+DateTo+"' and model.reIndep = '"+DepId+ "' and (model.riplandate>='"+TimeString+"')";
		 }
			 
		//queryString+=" and model.riSign=0 ORDER BY model.riplandate";
		queryString+=" and model.riSign=0 ORDER BY model.ritaketime";
		final String queryString1 =queryString;
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
		
		throw re;
	}	
	}
	
}
