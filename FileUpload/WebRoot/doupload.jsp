<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@page import="toolkie.UploadTool"%>
    <%
    //��request�ǤJUploadTool��
    UploadTool upload = new  toolkie.UploadTool(request);
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%
//�d�߬O�_���~
String msg = upload.checkUpload();
if(msg.length()>0)
{
  out.println(msg);
}
else
{
  //�]�w�W�Ǹ��|
  // upload.setUploadDir(this.getServletContext().getRealPath("." ));
  upload.setUploadDir("D:\\Temp\\fileupload\\");
  out.println("�W�Ǩ즹���|:"+this.getServletContext().getRealPath("." )+"<br/>");
  //���o��r��
  out.println("��r���:"+upload.getParameter("textfield")+"<br/>");
  //�}�l�W��
  if(upload.isExtUpload("File1"))
    msg = upload.doUpload(upload.getUploadParameter("File1"),"File1");
  if(msg.length()==0 && upload.isExtUpload("File2"))
    msg = upload.doUpload(upload.getUploadParameter("File2"),"File2");

 
  if(msg.length()>0)
    out.println(msg);
  else
    out.println("�W�Ǧ��\"+"<br/>");
 
}
%>

</body>
</html>