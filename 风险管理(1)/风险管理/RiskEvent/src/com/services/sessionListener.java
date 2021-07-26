package com.services;

import javax.servlet.*;    
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;    
import javax.servlet.http.HttpSession;

import java.io.IOException;    
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class sessionListener implements Filter {
	
	protected FilterConfig filterConfig = null;    
     private String redirectURL = null;    
     private List notCheckURLList = new ArrayList();    
     private String sessionKey = null;    
	
     public void destroy() {
		// TODO Auto-generated method stub
    	 this.filterConfig = null;
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletReponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletReponse;
		HttpSession session =request.getSession();
		//System.out.println("fliter..........");
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		 this.filterConfig = filterConfig;    
	     redirectURL = filterConfig.getInitParameter("redirectURL");    
	     sessionKey = filterConfig.getInitParameter("checkSessionKey");	    
	     String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");    	    
	     if(notCheckURLListStr != null)    
	     {    
	      StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");    
	      notCheckURLList.clear();    
	      while(st.hasMoreTokens())    
	      {    
	       notCheckURLList.add(st.nextToken());    
	      }    
	     }    
	}

}
