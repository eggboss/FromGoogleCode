<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert</title>
</head>
<body>
<form name="form1" action="test.do">
<table>
	<tr>
		<th>CUST_ID</th>
		<th>PASSWORD</th>
		<th>CUST_NAME</th>
		<th>PHONE</th>
		<th>ADDRESS</th>
	</tr>
	<tr>
		<th><input type="text" name="cust_id"></th>
		<th><input type="text" name="password"></th>
		<th><input type="text" name="cust_name"></th>
		<th><input type="text" name="phone"></th>
		<th><input type="text" name="address"></th>
	</tr>
</table>
<input type="hidden" name="method" value="insertAfter"/>
<input type="submit" name="save" value="存檔"/>
</form>
</body>
</html>