package com.tzz.test.util;

import org.junit.Test;

import com.tzz.util.pdf.PDFUtil;

public class PDFUtilTest {

	/**htmlPath*/
	@Test
	public void testGeneratePDF() {
		String savePath = PDFUtil.generatePDF("D:/pdf");
		System.out.println(savePath);
	}

	/**HTML转PDF*/
	@Test
	public void testHtmlToPdf() {
		String htmlPath = "E:/eclipse/eclipse/workspace/web-demo/src/main/webapp/WEB-INF/pdf/pdf.html";
		String imagePath = "file:///E:/eclipse/eclipse/workspace/web-demo/src/main/webapp/image/pdf/";
		String path = "D:/pdf/html/";
		PDFUtil.htmlToPDF(htmlPath, imagePath, path);
	}

}
