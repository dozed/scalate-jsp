package com.fusesource.scalate.jsp;

import java.io.IOException;
import java.util.Enumeration;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class ScalateJspContext extends PageContext {

	@Override
	public void initialize(Servlet servlet, ServletRequest request, ServletResponse response, String errorPageURL,
			boolean needsSession, int bufferSize, boolean autoFlush) throws IOException, IllegalStateException,
			IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletRequest getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exception getException() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forward(String relativeUrlPath) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void include(String relativeUrlPath) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void include(String relativeUrlPath, boolean flush) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePageException(Exception e) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePageException(Throwable t) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String name, int scope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttribute(String name, int scope) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAttributesScope(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Enumeration<String> getAttributeNamesInScope(int scope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JspWriter getOut() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariableResolver getVariableResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ELContext getELContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
