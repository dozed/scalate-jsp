package com.fusesource.scalate.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.junit.Test;
import org.springframework.web.servlet.tags.UrlTag;


public class AppTest {

	@Test
	public void test() throws JspException {
		
		
		ScalateJspFactory factory = new ScalateJspFactory();
		PageContext pageContext = factory.getPageContext(null, null, null, "", false, 512, false);
		
		UrlTag tag = new UrlTag();
		tag.setPageContext(pageContext);
		tag.doStartTag();
		// process tag
	}
	
}
