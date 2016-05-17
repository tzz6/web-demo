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
import com.tzz.util.UserSession;
import com.tzz.web.domain.User;
import com.tzz.web.service.UserService;

@Controller
public class LoginController extends BaseController {

	private final Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	/** 到达登录页面 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "url", required = false) String url, HttpServletRequest request,
			ModelMap model) {
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
		log.info("username:" + username + "password:" + password + "verifyCode:" + verifyCode + "savePassword:"
				+ savePassword + "autologin:" + autologin + "loginUrl:" + loginUrl);
		User user = userService.login(username, password);
		String sessVerifyCode = (String) request.getSession().getAttribute(Constants.SESSION_KEY.VERIFY_CODE);
		String ctx = request.getContextPath();
		if (loginUrl != null && !"".equals(loginUrl)) {
			loginUrl = ctx + DESUtil.decrypt(loginUrl, DESUtil.KEY);
		}else{
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
					}else{
						CookieUtil.removeCookie(response, Constants.COOKIE_KEY.AUTO_LOGIN);
					}
				}else{
					CookieUtil.removeCookie(response, Constants.COOKIE_KEY.SAVE_PASSWORD);
					CookieUtil.removeCookie(response, Constants.COOKIE_KEY.USERNAME);
					CookieUtil.removeCookie(response, Constants.COOKIE_KEY.PASSWORD);
					CookieUtil.removeCookie(response, Constants.COOKIE_KEY.AUTO_LOGIN);
				}
				writeJSON("success", loginUrl, response);
			}else{
				writeJSON("fail", loginUrl, response);
			}
		} else {
			writeJSON("fail", loginUrl, response);
		}
	}

}
