package com.tzz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tzz.crypto.des.DESUtil;
import com.tzz.util.Constants;
import com.tzz.util.CookieUtil;
import com.tzz.util.DateUtil;
import com.tzz.util.LogAppender;
import com.tzz.util.SystemUtil;
import com.tzz.util.UserSession;
import com.tzz.web.domain.User;
import com.tzz.web.service.UserService;

@Controller
public class LoginController extends BaseController {

	protected final transient Logger log = Logger.getLogger(LogAppender.USER);
	@Autowired
	private UserService userService;

	/** 到达登录页面 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "url", required = false) String url, HttpServletRequest request,
			ModelMap model) {
		log.info("用户登录页面----url:" + url);
		model.put("url", url);
		String savePassword = CookieUtil.getCookie(request, Constants.COOKIE_KEY.SAVE_PASSWORD);
		String autoLogin = CookieUtil.getCookie(request, Constants.COOKIE_KEY.AUTO_LOGIN);
		if (autoLogin != null && "1".equals(autoLogin)) {
			model.put("autoLogin", autoLogin);
		}
		if (savePassword != null && "1".equals(savePassword)) {
			String username = CookieUtil.getCookie(request, Constants.COOKIE_KEY.USERNAME);
			String password = CookieUtil.getCookie(request, Constants.COOKIE_KEY.PASSWORD);
			model.put("savePassword", savePassword);
			model.put("username", username);
			model.put("password", password);
		}
		return "/login";
	}

	/** 登录 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void verifyLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@RequestParam(value = "savePassword", required = false) String savePassword,
			@RequestParam(value = "autologin", required = false) String autologin,
			@RequestParam(value = "loginUrl", required = false) String loginUrl, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			log.info("username:" + username + "password:" + password + "verifyCode:" + verifyCode + "savePassword:"
					+ savePassword + "autologin:" + autologin + "loginUrl:" + loginUrl);
			User user = userService.login(username, password);
			String sessVerifyCode = (String) request.getSession().getAttribute(Constants.SESSION_KEY.VERIFY_CODE);
			String ctx = request.getContextPath();
			if (loginUrl != null && !"".equals(loginUrl)) {
				loginUrl = ctx + DESUtil.decrypt(loginUrl, DESUtil.KEY);
			} else {
				loginUrl = ctx + "/index";
			}
			if (user != null) {
				String cookSavePassword = CookieUtil.getCookie(request, Constants.COOKIE_KEY.SAVE_PASSWORD);
				if ((cookSavePassword != null && "1".equals(cookSavePassword))
						|| (verifyCode != null && sessVerifyCode.equals(verifyCode.toUpperCase()))) {
					UserSession.setUser(user);
					if (StringUtils.isNotBlank(savePassword)) {
						CookieUtil.addCookie(response, Constants.COOKIE_KEY.SAVE_PASSWORD, savePassword);
						CookieUtil.addCookie(response, Constants.COOKIE_KEY.USERNAME, username);
						CookieUtil.addCookie(response, Constants.COOKIE_KEY.PASSWORD, password);

						if (StringUtils.isNotBlank(autologin)) {
							CookieUtil.addCookie(response, Constants.COOKIE_KEY.AUTO_LOGIN, autologin);
						} else {
							CookieUtil.removeCookie(response, Constants.COOKIE_KEY.AUTO_LOGIN);
						}
					} else {
						CookieUtil.removeCookie(response, Constants.COOKIE_KEY.SAVE_PASSWORD);
						CookieUtil.removeCookie(response, Constants.COOKIE_KEY.USERNAME);
						CookieUtil.removeCookie(response, Constants.COOKIE_KEY.PASSWORD);
						CookieUtil.removeCookie(response, Constants.COOKIE_KEY.AUTO_LOGIN);
					}
					writeJSON("success", loginUrl, response);
					printLog(username, "success");

				} else {
					writeJSON("fail", loginUrl, response);
					printLog(username, "fail");
				}
			} else {
				writeJSON("fail", loginUrl, response);
				printLog(username, "fail");
			}

		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
	}

	private void printLog(String username, String status) {
		log.info("1\u00001\u0000" + DateUtil.getCurrDate("yyyy-MM-dd HH:mm:ss") + "\u0000" + username
				+ "\u0000WEB-DEMO\u0000WEB-DEMO\u0000127.0.0.1\u0000127.0.0.1" + "\u0000 "
				+ SystemUtil.getSystemUsername() + "\u000000" + SystemUtil.getSystemMAC() + "\u0000013\u0000" + status
				+ "\u0000\u0000\r");
	}

}
