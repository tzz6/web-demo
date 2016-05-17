package com.tzz.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义标签
 *
 */
public class ImgTags extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private String imageUrl;

	@Override
	public int doStartTag() throws JspException {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			stringBuffer.append("<div style='text-align: center;'>");
			stringBuffer.append("<img src='" + getImageUrl() + "'/>");
			stringBuffer.append("</div>");
			JspWriter out = pageContext.getOut();
			out.write(stringBuffer.toString());
			return EVAL_BODY_INCLUDE;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
