<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function goInsert(){
	document.dyForm.dispatch.value='goInsert';
	document.dyForm.submit();
}
</script>
</head>
<body>
<html:form method="post" action="testtoken.do">
	<input type="hidden" name="dispatch"/>
	<input type="button" value="goInsert" onclick="goInsert()"/>
</html:form>
</body>
</html>