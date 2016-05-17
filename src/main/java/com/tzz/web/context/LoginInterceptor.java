package com.tzz.web.context;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.tzz.crypto.des.DESUtil;
import com.tzz.util.UserSession;
import com.tzz.web.domain.User;

/**
 * 登录拦截器
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	protected final transient Logger log = Logger.getLogger(LoginInterceptor.class);

	private List<String> excludeUris;

	private List<String> excludeStartsUris;

	public List<String> getExcludeUris() {
		return excludeUris;
	}

	public void setExcludeUris(List<String> excludeUris) {
		this.excludeUris = excludeUris;
	}

	public List<String> getExcludeStartsUris() {
		return excludeStartsUris;
	}

	public void setExcludeStartsUris(List<String> excludeStartsUris) {
		this.excludeStartsUris = excludeStartsUris;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String url = request.getRequestURI();
		log.info("-----------loginInterceptor-----------url:" + url);

		String param = request.getQueryString();
		if (param == null) {
			param = "";
		} else {
			param = "?" + param;
		}
		String ctx = request.getContextPath();
		log.info("-----------ctx-----------:" + ctx);
		String path = request.getServletPath();
		log.info("http://" + request.getServerName() + url.replaceAll("\\s", ""));
		User user = UserSession.getUser();
		if (user != null) {
			return true;
		}
		if (excludeUris.contains(path)) {
			return true;
		}
		for (String startsUrl : excludeStartsUris) {
			if (url.contains(startsUrl)) {
				return true;
			}
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String data = "没有访问权限";
		OutputStream os = response.getOutputStream();
		os.write(data.getBytes("UTF-8"));
		log.info("return false ---" + url + " 被拦截，没有访问权限");
		String redirect_url = DESUtil.encrypt(path + param, DESUtil.KEY);
		String loginUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + ctx + "/login?url="
				+ redirect_url.replaceAll("\\s", "");
		response.sendRedirect(loginUrl);
		return false;
	}
}