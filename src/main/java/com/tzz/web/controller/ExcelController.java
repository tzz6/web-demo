package com.tzz.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tzz.util.FileUtil;
import com.tzz.web.domain.FileModel;
import com.tzz.web.service.FileModelService;

@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

	@Autowired
	private FileModelService fileModelService;

	/** 上传附件页 */
	@RequestMapping(value = "/toUpload", method = RequestMethod.GET)
	public String toUpload() {
		return "/file/uploadFile";
	}

	
	/** SpringMVC 上传附件 */
	@RequestMapping(value = "/saveFileSM", method = RequestMethod.POST)
	public String saveFileSM(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
		String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		try {
			for (int i = 0; i < files.length; i++) {
				FileModel fileModel = new FileModel();
				String filename = files[i].getOriginalFilename();
				System.out.println("上传的文件的名是：" + filename);
				if (filename == null || filename.trim().equals("")) {
					continue;
				}
				filename = filename.substring(filename.lastIndexOf("\\") + 1);
				// 获取item中的上传文件的输入流
				InputStream in = files[i].getInputStream();
				// 得到文件保存的名称
				String saveFilename = FileUtil.makeFileName(filename);
				// 得到文件的保存目录
				String realSavePath = FileUtil.makePath(saveFilename, savePath);
				// 创建一个文件输出流
				FileOutputStream out = new FileOutputStream(realSavePath + saveFilename);
				// 创建一个缓冲区
				byte buffer[] = new byte[1024];
				// 判断输入流中的数据是否已经读完的标识
				int len = 0;
				// 循环将输入流读入到缓冲区当中
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.close();
				in.close();
				fileModel.setName(filename);
				fileModel.setSavePath(realSavePath + saveFilename);
				fileModel.setCreateDate(new Date());
				fileModelService.saveEntity(fileModel);
				System.out.println("文件上传成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/file/index";
	}

}
