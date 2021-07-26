package com.action.riskEvaluate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.RiskEvaluateInternationDAO;
import com.model.RiskEvaluateInternation;


public class RiskEvaluateContractInputAction {
	private List<RiskEvaluateInternation> primaryRisk;

	
	private Map<List<String>,List<RiskEvaluateInternation>> contractMap = null;
	private List<String> elementCount = null;

	private RiskEvaluateInternationDAO riskEvaluateInternationDao;

	private String primary = "s101";
		
	public Map<List<String>, List<RiskEvaluateInternation>> getContractMap() {
		return contractMap;
	}

	public void setContractMap(
			Map<List<String>, List<RiskEvaluateInternation>> contractMap) {
		this.contractMap = contractMap;
	}



	public List<String> getElementCount() {
		return elementCount;
	}

	public void setElementCount(List<String> elementCount) {
		this.elementCount = elementCount;
	}

	public List<RiskEvaluateInternation> getPrimaryRisk() {
		return primaryRisk;
	}

	public void setPrimaryRisk(List<RiskEvaluateInternation> primaryRisk) {
		this.primaryRisk = primaryRisk;
	}


	public RiskEvaluateInternationDAO getRiskEvaluateInternationDao() {
		return riskEvaluateInternationDao;
	}
	
	public void setRiskEvaluateInternationDao(
			RiskEvaluateInternationDAO riskEvaluateInternationDao) {
		this.riskEvaluateInternationDao = riskEvaluateInternationDao;
	}

	
	
	public String getRiskFactor(){		
		try {
			// 二级风险
			
			HttpServletRequest request = ServletActionContext.getRequest(); 
			this.primaryRisk  =  new LinkedList<RiskEvaluateInternation>();
			primaryRisk = this.getRiskEvaluateInternationDao().findByReiCategory(primary);
			int primaryCount = this.getRiskEvaluateInternationDao().findByReiCategory(primary).size();
			//System.out.println("风险分类个数"+primaryCount);
			
			elementCount =  new LinkedList<String>();
			contractMap = new LinkedHashMap<List<String>, List<RiskEvaluateInternation>>();
			for(int i = 200;i<200+primaryCount;i++){
				List<RiskEvaluateInternation> elementList = new LinkedList<RiskEvaluateInternation>();
				List<String> keyList = new LinkedList<String>();
				elementList = this.getRiskEvaluateInternationDao().findByReiCategory(i+"");
				String cateName = this.getRiskEvaluateInternationDao().findById(Integer.toString(i)).getReiCatename();   //大类的名称
				String category = Integer.toString(i);   //大类的remark
				keyList.add(cateName);  //父类名称
				keyList.add(category);  //父类编号
				
				elementCount.add(this.getRiskEvaluateInternationDao().findByReiCategory(i+"").size()+"");
				contractMap.put(keyList,elementList);
			}
			//保存每一个以及风险的二级风险个数
			request.setAttribute("elementCount", elementCount);

			//打印Map,可删除
			 for (Map.Entry<List<String>, List<RiskEvaluateInternation>> entry : contractMap.entrySet()) {
				   List<String> keyList = entry.getKey();
				   List<RiskEvaluateInternation> value = entry.getValue();
				   //System.out.println("key[0]=" + keyList.get(0)+ "key[1]=" + keyList.get(1)+" value=" + value.size());
				  }

			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

}
