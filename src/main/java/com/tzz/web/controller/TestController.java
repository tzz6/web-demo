package com.tzz.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "/send")
	@ResponseBody
	public Map<String, String> sendPost(HttpServletRequest request) {
		Map<String, String> maps = new HashMap<String, String>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		maps.put("username", username);
		maps.put("password", password);
		return maps;
	}

	@RequestMapping(value = "/send/file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sendPostFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		Map<String, String> maps = new HashMap<String, String>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		maps.put("username", username);
		maps.put("password", password);

		try {
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				fileName = new String(fileName.getBytes(), "UTF-8");
				InputStream is = file.getInputStream();
				if (fileName != null && !("".equals(fileName))) {
					File directory = new File("D://test//httpclient//file");
					if (!directory.exists()) {
						directory.mkdirs();
					}
					String filePath = ("D://test//httpclient//file") + File.separator + fileName;
					FileOutputStream fos = new FileOutputStream(filePath);
					byte[] buffer = new byte[1024];
					while (is.read(buffer) > 0) {
						fos.write(buffer, 0, buffer.length);
					}
					fos.flush();
					fos.close();
					maps.put("file--" + fileName, "uploadSuccess");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maps;
	}
	
	@RequestMapping(value = "/send/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendPostJson(HttpServletRequest request) {
		try {
			StringBuffer str = new StringBuffer();
			BufferedInputStream in = new BufferedInputStream(request.getInputStream());
			int i;
			char c;
			while ((i = in.read()) != -1) {
				c = (char) i;
				str.append(c);
			}
			System.out.println(str.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> userMap = new HashMap<String, Object>();
		// System.out.println(agentChoose.toString());
		// System.out.println(name);
		userMap.put("serviceName", "676123");
		userMap.put("serviceFullName", "00001");
		userMap.put("errorCode", "00001x");
		userMap.put("errorMessage", "000012");
		return userMap;
	}
}
