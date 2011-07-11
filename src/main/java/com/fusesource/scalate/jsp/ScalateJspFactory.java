package com.fusesource.scalate.jsp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

public class ScalateJspFactory extends JspFactory {

	private Map<ServletContext, JspApplicationContext> contextMap = new HashMap<ServletContext, JspApplicationContext>();
	
	@Override
	public PageContext getPageContext(Servlet servlet, ServletRequest request, ServletResponse response,
			String errorPageURL, boolean needsSession, int buffer, boolean autoflush) {
		
		// TODO check constructor arguments
		ScalatePageContext ctx = new ScalatePageContext(null, (HttpServletRequest)request, (HttpServletResponse)response);
		
		return ctx;
	}

	@Override
	public void releasePageContext(PageContext pc) {
		// TODO nop?
	}

	@Override
	public JspEngineInfo getEngineInfo() {
		return new ScalateJspEngineInfo();
	}

	@Override
	public JspApplicationContext getJspApplicationContext(ServletContext context) {
		// TODO where is the context set?
		return contextMap.get(context);
	}

}
