
<%@page import="java.net.URLEncoder"%><%@ page
	language="java"
	contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.Properties,java.io.*"
%><%
	// 需要在session放入 downloadFile=完整檔案路徑
	//String fileName = (String)ActionContext.getContext().getValueStack().findValue("downloadFile", String.class);
	//String fn = fileName.substring(fileName.lastIndexOf("/") + 1);
	
	//String dailyPath = (String)ActionContext.getContext().getValueStack().findValue("dailyPath", String.class);
	//Properties prop = PropUtil.getProperties("path.properties");
	//String pre_path = prop.getProperty("resultFilePath") + dailyPath + "/";
	
	request.setCharacterEncoding("utf-8");
	//String fn = request.getParameter("fn");
	
	//System.out.println("filename=" + fn);
	
	//String src_fname = pre_path + fn;
	
	//String dst_fname = URLEncoder.encode(fn, "utf-8"); // 這裡得加上"utf-8"
	//System.out.println("filename=" + dst_fname);
	
	String src_fname = "test.txt";
	String dst_fname = "test.txt";
	
	// the charset must be iso-8859-1
	response.setContentType("application/octet-stream; charset=iso-8859-1");
	response.setHeader("Content-disposition", "attachment; filename=\"" + dst_fname + "\"");

	FileInputStream fis = null;
	int byteRead;

	try {
		fis = new FileInputStream(src_fname);
		while ((byteRead = fis.read()) != -1) {
			out.write(byteRead);
		}
		out.flush();
	}
	catch (Exception e) {
		out.clearBuffer();
		response.setContentType("text/html; charset=big5");
		response.setHeader("Content-disposition", "inline");
		out.println("<HTML><BODY><P>");
		out.println(e.toString());
		out.println("</P></BODY></HTML>");
	}
	if (fis != null) {
		fis.close();
	}
	return; // 避免下面多按了 Enter 鍵而輸出多餘的換行字元.
%>