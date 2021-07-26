package com.action.riskEvaluate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

//import com.dao.ProjectElementDAO;
import com.model.ProjectElement;

public class RiskEvaluate {
	private final int count=10000;
	private int[] distribute=new int[10];//0-1的分布
	private double expectedValue;//期望
	private double variance;//方差
	private Random random;
	
	public RiskEvaluate()
	{
		for(int i=0;i<10;i++)
			this.distribute[i]=0;
		this.expectedValue=0;
		this.variance=0;
		random=new Random();
	}
	
	public  double caculate(List<ProjectElement> list)
	{
		double weight=0;
		ProjectElement pro;
		double cateimpact=Double.parseDouble(list.get(0).getPeCateimpact());
		for(int i=0;i<list.size();i++)
		{
			 pro=list.get(i);
			 double probablity=0.0;
			 int prob=Integer.parseInt(pro.getPeProbablity());
			 switch (prob){
				 case 1:probablity=0.1;break;
				 case 2:probablity=0.3;break;
				 case 3:probablity=0.5;break;
				 case 4:probablity=0.7;break;
				 case 5:probablity=0.9;break;
			 }
			 double ran=random.nextDouble();
			 
			 
			 
			 if(new BigDecimal(ran).compareTo(new BigDecimal(probablity))<0)
			   {
				  weight=weight+Double.parseDouble(pro.getPeImpactdegree());
			   }
		}
		return cateimpact*weight;	
	}
	public String[] monteCarlo(List<ProjectElement>[] listArray)
	{
		
		double finalValue;
		for(int i=0;i<count;i++)
		{
			finalValue=0;
			for(int j=0;j<listArray.length;j++)
			if(listArray[j].size()!=0)
			{
				finalValue=finalValue+caculate(listArray[j]);	
			}
			finalValue=finalValue*10;
			int result=(int)finalValue;
			switch (result){
			case 0:	distribute[0]++;break;
			case 1:	distribute[1]++;break;
			case 2:	distribute[2]++;break;
			case 3:	distribute[3]++;break;
			case 4:	distribute[4]++;break;
			case 5:	distribute[5]++;break;
			case 6:	distribute[6]++;break;
			case 7:	distribute[7]++;break;
			case 8:	distribute[8]++;break;
			case 9: distribute[9]++;break;
			case 10: distribute[9]++;break;
			}
		}
		//计算期望
		for(int i=0;i<10;i++)
		{
			expectedValue=distribute[i]*(i+1)/10+expectedValue;
		}
		expectedValue=expectedValue/count;
		
		BigDecimal expectedValue_format = new BigDecimal(expectedValue);
		expectedValue = expectedValue_format.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();   //四舍五入保留3位小数
		//计算方差
		for(int i=0;i<10;i++)
		{
			variance=Math.pow(((i+1)/10-expectedValue),2)*distribute[i]+variance;
		}
		variance=variance/count;
		
		BigDecimal variance_format = new BigDecimal(variance);
		variance = variance_format.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		

		for(int i = 0;i<10;i++){
			//System.out.println("distribute"+i+"-----"+distribute[i]);
		}
		
		String[] finalResult = new String[12];
		finalResult[0] = Double.toString(expectedValue);
		finalResult[1] = Double.toString(variance);
		for(int i = 2;i<12;i++){
			int region = distribute[i-2];
			finalResult[i] = Integer.toString(region);
		}
		
		return finalResult;
	}
}
