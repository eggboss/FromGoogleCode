<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Page</title>
<script>
function doInsert(){
	//document.dyForm.action='testtoken.do';
	document.dyForm.dispatch.value='doInsert';
	document.dyForm.submit();
}

</script>
</head>
<body>
<html:form method="post" action="testtoken.do">
	<input type="hidden" name="dispatch"/>
	<input type="button" value="doInsert" onclick="doInsert()">
</html:form>
</body>
</html>