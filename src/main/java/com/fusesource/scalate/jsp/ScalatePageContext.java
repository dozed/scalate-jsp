package com.fusesource.scalate.jsp;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.util.Assert;

public class ScalatePageContext extends PageContext {

	private final ServletContext servletContext;

	private final HttpServletRequest request;

	private final HttpServletResponse response;

	private final ServletConfig servletConfig;

	private final Map<String, Object> attributes = new LinkedHashMap<String, Object>();

	private JspWriter out;


	/**
	 * Create new ScalatePageContext with a default {@link MockServletContext},
	 * {@link MockHttpServletRequest}, {@link MockHttpServletResponse},
	 * {@link MockServletConfig}.
	 */
	public ScalatePageContext() {
		this(null, null, null, null);
	}

	/**
	 * Create new ScalatePageContext with a default {@link MockHttpServletRequest},
	 * {@link MockHttpServletResponse}, {@link MockServletConfig}.
	 * @param servletContext the ServletContext that the JSP page runs in
	 * (only necessary when actually accessing the ServletContext)
	 */
	public ScalatePageContext(ServletContext servletContext) {
		this(servletContext, null, null, null);
	}

	/**
	 * Create new ScalatePageContext with a MockHttpServletResponse,
	 * MockServletConfig.
	 * @param servletContext the ServletContext that the JSP page runs in
	 * @param request the current HttpServletRequest
	 * (only necessary when actually accessing the request)
	 */
	public ScalatePageContext(ServletContext servletContext, HttpServletRequest request) {
		this(servletContext, request, null, null);
	}

	/**
	 * Create new ScalatePageContext with a MockServletConfig.
	 * @param servletContext the ServletContext that the JSP page runs in
	 * @param request the current HttpServletRequest
	 * @param response the current HttpServletResponse
	 * (only necessary when actually writing to the response)
	 */
	public ScalatePageContext(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		this(servletContext, request, response, null);
	}

	/**
	 * Create new MockServletConfig.
	 * @param servletContext the ServletContext that the JSP page runs in
	 * @param request the current HttpServletRequest
	 * @param response the current HttpServletResponse
	 * @param servletConfig the ServletConfig (hardly ever accessed from within a tag)
	 */
	public ScalatePageContext(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response, ServletConfig servletConfig) {

		this.servletContext = (servletContext != null ? servletContext : new MockServletContext());
		this.request = (request != null ? request : new MockHttpServletRequest(servletContext));
		this.response = (response != null ? response : new MockHttpServletResponse());
		this.servletConfig = (servletConfig != null ? servletConfig : new MockServletConfig(servletContext));

//		this.servletContext = null;
//		this.request = null;
//		this.response = null;
//		this.servletConfig = null;
	}


	public void initialize(
			Servlet servlet, ServletRequest request, ServletResponse response,
			String errorPageURL, boolean needsSession, int bufferSize, boolean autoFlush) {

		throw new UnsupportedOperationException("Use appropriate constructor");
	}

	public void release() {
	}

	public void setAttribute(String name, Object value) {
		Assert.notNull(name, "Attribute name must not be null");
		if (value != null) {
			this.attributes.put(name, value);
		}
		else {
			this.attributes.remove(name);
		}
	}

	public void setAttribute(String name, Object value, int scope) {
		Assert.notNull(name, "Attribute name must not be null");
		switch (scope) {
			case PAGE_SCOPE:
				setAttribute(name, value);
				break;
			case REQUEST_SCOPE:
				this.request.setAttribute(name, value);
				break;
			case SESSION_SCOPE:
				this.request.getSession().setAttribute(name, value);
				break;
			case APPLICATION_SCOPE:
				this.servletContext.setAttribute(name, value);
				break;
			default:
				throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	public Object getAttribute(String name) {
		Assert.notNull(name, "Attribute name must not be null");
		return this.attributes.get(name);
	}

	public Object getAttribute(String name, int scope) {
		Assert.notNull(name, "Attribute name must not be null");
		switch (scope) {
			case PAGE_SCOPE:
				return getAttribute(name);
			case REQUEST_SCOPE:
				return this.request.getAttribute(name);
			case SESSION_SCOPE:
				HttpSession session = this.request.getSession(false);
				return (session != null ? session.getAttribute(name) : null);
			case APPLICATION_SCOPE:
				return this.servletContext.getAttribute(name);
			default:
				throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	public Object findAttribute(String name) {
		Object value = getAttribute(name);
		if (value == null) {
			value = getAttribute(name, REQUEST_SCOPE);
			if (value == null) {
				value = getAttribute(name, SESSION_SCOPE);
				if (value == null) {
					value = getAttribute(name, APPLICATION_SCOPE);
				}
			}
		}
		return value;
	}

	public void removeAttribute(String name) {
		Assert.notNull(name, "Attribute name must not be null");
		this.removeAttribute(name, PageContext.PAGE_SCOPE);
		this.removeAttribute(name, PageContext.REQUEST_SCOPE);
		this.removeAttribute(name, PageContext.SESSION_SCOPE);
		this.removeAttribute(name, PageContext.APPLICATION_SCOPE);
	}

	public void removeAttribute(String name, int scope) {
		Assert.notNull(name, "Attribute name must not be null");
		switch (scope) {
			case PAGE_SCOPE:
				this.attributes.remove(name);
				break;
			case REQUEST_SCOPE:
				this.request.removeAttribute(name);
				break;
			case SESSION_SCOPE:
				this.request.getSession().removeAttribute(name);
				break;
			case APPLICATION_SCOPE:
				this.servletContext.removeAttribute(name);
				break;
			default:
				throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	public int getAttributesScope(String name) {
		if (getAttribute(name) != null) {
			return PAGE_SCOPE;
		}
		else if (getAttribute(name, REQUEST_SCOPE) != null) {
			return REQUEST_SCOPE;
		}
		else if (getAttribute(name, SESSION_SCOPE) != null) {
			return SESSION_SCOPE;
		}
		else if (getAttribute(name, APPLICATION_SCOPE) != null) {
			return APPLICATION_SCOPE;
		}
		else {
			return 0;
		}
	}

	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttributeNamesInScope(int scope) {
		switch (scope) {
			case PAGE_SCOPE:
				return getAttributeNames();
			case REQUEST_SCOPE:
				return this.request.getAttributeNames();
			case SESSION_SCOPE:
				HttpSession session = this.request.getSession(false);
				return (session != null ? session.getAttributeNames() : null);
			case APPLICATION_SCOPE:
				return this.servletContext.getAttributeNames();
			default:
				throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	public JspWriter getOut() {
		if (this.out == null) {
			//this.out = new MockJspWriter(this.response);
		}
		return this.out;
	}

	public ExpressionEvaluator getExpressionEvaluator() {
		//return new MockExpressionEvaluator(this);
		return null;
	}

	public ELContext getELContext() {
		return null;
	}

	public VariableResolver getVariableResolver() {
		return null;
	}

	public HttpSession getSession() {
		return this.request.getSession();
	}

	public Object getPage() {
		return this;
	}

	public ServletRequest getRequest() {
		return this.request;
	}

	public ServletResponse getResponse() {
		return this.response;
	}

	public Exception getException() {
		return null;
	}

	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public void forward(String url) throws ServletException, IOException {
		throw new UnsupportedOperationException("forward");
	}

	public void include(String url) throws ServletException, IOException {
		throw new UnsupportedOperationException("include");
	}

	public void include(String url, boolean flush) throws ServletException, IOException {
		throw new UnsupportedOperationException("include");
	}

	public void handlePageException(Exception ex) throws ServletException, IOException {
		throw new UnsupportedOperationException("handlePageException");
	}

	public void handlePageException(Throwable ex) throws ServletException, IOException {
		throw new UnsupportedOperationException("handlePageException");
	}


}
