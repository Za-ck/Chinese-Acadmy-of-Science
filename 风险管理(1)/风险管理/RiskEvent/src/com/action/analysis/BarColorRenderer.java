package com.action.analysis;

import java.awt.Color;
import java.awt.Paint;

public class BarColorRenderer extends org.jfree.chart.renderer.category.BarRenderer3D { 
	   
    /** 
     *  
     */ 
    private static final long serialVersionUID = 784630226449158436L; 
    private Paint[] colors; 
    //初始化柱子颜色 
    private String[] colorValues = { "#4C7FB9", "#C1504C", "#9DBD58", "#8164A3", "#4AADC7"}; 
   
    public BarColorRenderer() { 
        colors = new Paint[colorValues.length]; 
        for (int i = 0; i < colorValues.length; i++) { 
            colors[i] = Color.decode(colorValues[i]); 
        } 
    } 
   
    //每根柱子以初始化的颜色不断轮循 
    public Paint getItemPaint(int i, int j) { 
        return colors[j % colors.length]; 
    } 
}