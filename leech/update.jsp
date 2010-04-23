<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="kk.leech.db.vo.DISTRIBUTOR"%>
<%
	DISTRIBUTOR entity = request.getAttribute("entity")==null?null:(DISTRIBUTOR)request.getAttribute("entity");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		<th><%=entity.getCust_id() %></th>
		<th><input type="text" name="password" value="<%=entity.getPassword() %>"></th>
		<th><input type="text" name="cust_name" value="<%=entity.getCust_name() %>"></th>
		<th><input type="text" name="phone" value="<%=entity.getPhone() %>"></th>
		<th><input type="text" name="address" value="<%=entity.getAddress() %>"></th>
	</tr>
</table>
<input type="hidden" name="cust_id" value="<%=entity.getCust_id() %>">
<input type="hidden" name="method" value="updateAfter">
<input type="submit" name="save" value="存檔"/>
</form>
</body>
</html>