<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@page import="toolkie.UploadTool"%>
    <%
    //把request傳入UploadTool裡
    UploadTool upload = new  toolkie.UploadTool(request);
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%
//查詢是否錯誤
String msg = upload.checkUpload();
if(msg.length()>0)
{
  out.println(msg);
}
else
{
  //設定上傳路徑
  // upload.setUploadDir(this.getServletContext().getRealPath("." ));
  upload.setUploadDir("D:\\Temp\\fileupload\\");
  out.println("上傳到此路徑:"+this.getServletContext().getRealPath("." )+"<br/>");
  //取得文字檔
  out.println("文字欄位:"+upload.getParameter("textfield")+"<br/>");
  //開始上傳
  if(upload.isExtUpload("File1"))
    msg = upload.doUpload(upload.getUploadParameter("File1"),"File1");
  if(msg.length()==0 && upload.isExtUpload("File2"))
    msg = upload.doUpload(upload.getUploadParameter("File2"),"File2");

 
  if(msg.length()>0)
    out.println(msg);
  else
    out.println("上傳成功"+"<br/>");
 
}
%>

</body>
</html>