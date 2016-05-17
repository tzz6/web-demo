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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tzz.util.FileUtil;
import com.tzz.web.domain.FileModel;
import com.tzz.web.service.FileModelService;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

	@Autowired
	private FileModelService fileModelService;

	/** 列表页 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<FileModel> fileModels = fileModelService.findAll();
		model.put("fileModels", fileModels);
		return "/file/fileList";
	}

	/** 上传附件页 */
	@RequestMapping(value = "/toUpload", method = RequestMethod.GET)
	public String toUpload() {
		return "/file/uploadFile";
	}

	/** 上传附件 需要去掉spring-servlet中 支持上传文件的配置 */
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	public String saveFile(HttpServletRequest request) {
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		// 上传时生成的临时文件保存目录
		String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {// 创建临时目录
			tmpFile.mkdir();
		}

		try {
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); // 解决上传文件名的中文乱码
			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(1024 * 1024);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(1024 * 1024 * 10);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
				}
			});

			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
			}

			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				FileModel fileModel = new FileModel();
				if (item.isFormField()) {// 普通输入项的数据
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
				} else {// 附件数据
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					System.out.println("上传的文件的扩展名是：" + fileExtName);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
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
					// 删除处理文件上传时生成的临时文件
					item.delete();
					in.close();
					fileModel.setName(filename);
					fileModel.setSavePath(realSavePath + saveFilename);
					fileModel.setCreateDate(new Date());
					fileModelService.saveEntity(fileModel);
					System.out.println("文件上传成功！");
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			System.out.println("单个文件超出最大值！！！");
			e.printStackTrace();
		} catch (FileUploadBase.SizeLimitExceededException e) {
			System.out.println("上传文件的总的大小超出限制的最大值！！！");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("文件上传失败！");
			e.printStackTrace();
		}

		return "redirect:/file/index";
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

	/** 下载 */
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void download(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		FileModel fileModel = fileModelService.getEntity(id);
		String fileName = fileModel.getName();
		String path = fileModel.getSavePath();
		FileUtil.downloadFile(response, fileName, path);
	}

}
