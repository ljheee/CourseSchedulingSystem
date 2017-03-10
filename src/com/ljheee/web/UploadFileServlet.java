package com.ljheee.web;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.ljheee.read.ReadXls;


/**
 * 文件上传数据接收类
 */
public class UploadFileServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	/** 统一的编码格式*/
	private static final String encode = "UTF-8";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String tempDirectoryPath = this.getServletContext().getRealPath("/") + "/temps";
			File tempDirectory = new File(tempDirectoryPath);
			
			
			//上传时产生的临时文件的默认保存目录
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//DiskFileItemFactory中DEFAULT_SIZE_THRESHOLD=10240表示如果上传文件大于10K则会产生上传临时文件
			//上传临时文件的默认目录为java.io.tmpdir中保存的路径，根据操作系统的不同会有区别
			
			if(!tempDirectory.exists()) {
				tempDirectory.mkdir();
			}
			
			//重新设置临时文件保存目录
			factory.setRepository(tempDirectory);

			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置文件上传的头编码，如果需要正确接收中文文件路径或者文件名
			// 这里需要设置对应的字符编码，为了通用这里设置为UTF-8
			upload.setHeaderEncoding(encode);

			//解析请求数据包
			List<FileItem> fileItems = upload.parseRequest(request);
			if(null != fileItems){
				FileUtils.copyInputStreamToFile(fileItems.get(0).getInputStream(), 
						new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "1.xls"));
				FileUtils.copyInputStreamToFile(fileItems.get(1).getInputStream(), 
						new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "2.xls"));
			}
			//读取教研室计划
			ReadXls readXls = ReadXls.getInstance();
			readXls.setXlsFile(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "2.xls"));
			List<?> tList = readXls.getTeacherList();//获取教师列表
			request.setAttribute("tList", tList);
			
			request.getRequestDispatcher("course_schedule.jsp").forward(request, response);
			responseMessage(request, response, "上传完成");
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

	
	/**
	 * 文件上传完（服务器接受文件，并保存到指定目录）
	 * 返回结果函数---执行脚本
	 * @param response
	 * @param state
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void responseMessage(HttpServletRequest request,HttpServletResponse response, String str) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Writer writer = null;
		
		try {
			writer = response.getWriter();
			writer.write("<script>");
			writer.write("alert("+str+")");
			writer.write("</script>");
			writer.flush();
			writer.close();
		} catch(Exception e) {
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	

}
