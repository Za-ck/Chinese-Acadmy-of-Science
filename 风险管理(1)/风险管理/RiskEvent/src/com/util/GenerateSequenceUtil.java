package com.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateSequenceUtil {

    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");

    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;

    private static final int MAX = 9999;

    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized String generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        return sb.toString();
    }
    
    public static String generateFileId() {
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String fileId =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 文件Id
		return fileId;
		
	}
    
    //新的附件id的生成策略
    public static String newGenerateFileId() throws InterruptedException{
    	long now = System.currentTimeMillis();//一个13位的时间戳
    	int r1 = (int)(Math.random()*9+1);
    	int r2 = (int)(Math.random()*9+1);
    	String fileId = String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);
    	//休眠1毫秒
    	Thread.sleep(1);
    	return fileId;
    }
    
    
    public static String generateTaskId() {
			
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		int r3=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String taskId = String.valueOf(r1)+String.valueOf(r2)+String.valueOf(r3)+String.valueOf(now);// 文件Id
		return taskId;
		
	}
    
    public static String generateInvestProjectNameId() {
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String investProjectNameId = String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 评估项目名称Id
		return investProjectNameId;
		
	}
    
}