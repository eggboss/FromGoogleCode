<%--
See:http://www.hscripts.com/tutorials/jsp/jstltags/function-tag.php
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="kirk.displaytag.bean.PersonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<PersonBean> dataList = new ArrayList<PersonBean>();
	dataList.add(new PersonBean("spring","spring1",10,"0123456789","123@mail.com"));
	dataList.add(new PersonBean("hibernate","hibernate1",11,"0123456789","123@mail.com"));
	dataList.add(new PersonBean("iBatis","iBatis1",12,"0123456789","123@mail.com"));
	dataList.add(new PersonBean("struts","struts",13,"0123456789","123@mail.com"));
	dataList.add(new PersonBean("jpa","jpa1",14,"0123456789","123@mail.com"));
	request.setAttribute("dataList", dataList);
%>
<html>
	<head>
		<title>JSTL function tag sample page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<fieldset>
			<legend>fn:contains(string,substring)</legend>
			字串中是否包含子字串<br/>
			${fn:contains(kirk,k)}<br/>
			${fn:contains('kirk','k')}<br/>
			${fn:contains('sandy','k')}<br/>
		</fieldset>
	
	
	</body>
</html>
