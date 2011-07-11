package com.fusesource.scalate.jsp;

import javax.servlet.jsp.JspEngineInfo;

public class ScalateJspEngineInfo extends JspEngineInfo {

	@Override
	public String getSpecificationVersion() {
		return "scalate-jsp";
	}

}
