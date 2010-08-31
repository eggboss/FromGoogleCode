<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="kirk.displaytag.bean.PersonBean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Display Tag Test Page</title>
		<%
			List<PersonBean> dataList = new ArrayList<PersonBean>();
			dataList.add(new PersonBean("spring","spring1",10,"0123456789","123@mail.com"));
			dataList.add(new PersonBean("hibernate","hibernate1",11,"0123456789","123@mail.com"));
			dataList.add(new PersonBean("iBatis","iBatis1",12,"0123456789","123@mail.com"));
			dataList.add(new PersonBean("struts","struts",13,"0123456789","123@mail.com"));
			dataList.add(new PersonBean("jpa","jpa1",14,"0123456789","123@mail.com"));
			request.setAttribute("dataList", dataList);
		%>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="../css/screen.css" type="text/css" media="screen" />
	</head>
	<body>
		<%--
		<display:table name="dataList" />
		--%>
		<%-- --%>
		<display:table name="dataList" id="bean" pagesize="10" export="true" class="list" cellspacing="0" cellpadding="0">
			<display:caption>This is the table caption</display:caption>
			<display:column title="name" headerClass="sortable" sortable="true">
				${bean.name }
			</display:column>
			<display:column title="nickname" property="nickname" headerClass="sortable" sortable="true"/>
			<display:column title="age" property="age" headerClass="sortable" sortable="true"/>
			<display:column title="mobile" property="mobile" headerClass="sortable" sortable="true"/>
			<display:column title="email" property="email" headerClass="sortable" sortable="true" autolink="true"/>
			
    		<display:setProperty name="export.rtf.filename" value="example.rtf"/>
    		<display:setProperty name="export.exctl.filename" value="example.xls"/>
    		<%--<display:setProperty name="export.csv.filename" value="example.csv"/>
    		--%><display:setProperty name="export.csv" value="false"/>
    		
			<%--<display:footer>
			    <tr>
					<td>Total Bill:</td>
					<td>${fn:length(dataList)}</td>
			    </tr>
			</display:footer>
			
		--%></display:table>
		
	</body>
</html>
