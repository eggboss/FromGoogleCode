<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="java.util.List"%>
<%@page import="kk.leech.db.vo.DISTRIBUTOR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List list = request.getAttribute("list")==null?null:(List)request.getAttribute("list");
	Integer pageNum = request.getAttribute("pageNum")==null?null:(Integer)request.getAttribute("pageNum");
	Integer totalPage = request.getAttribute("totalPage")==null?null:(Integer)request.getAttribute("totalPage");
	String message = request.getAttribute("message")==null?null:(String)request.getAttribute("message");
%>

<html>
<head>
<script>
function doInsert(){
	form1.method.value="insert";
	form1.submit();
}
function doUpdate(id){
	form1.method.value="update";
	form1.custId.value=id;
	form1.submit();
}
function doDelete(id){
	if(confirm('確認刪除？')){
		form1.method.value="delete";
		form1.custId.value=id;
		form1.submit();
	}
}
function goto(pageNum){
	form1.pageNum.value=pageNum;
	form1.method.value="search"
	form1.submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
<%
if(message!=null){
	out.println("<font color='red'>" + message + "</font>");
}
%>
<%if(list!=null){ %>
<form name="form1" action="test.do">
	<table border='1'>
		<th>CUST_ID</th><th>PASSWORD</th><th>CUST_NAME</th><th>PHONE</th><th>ADDRESS</th><th>FONCTION</th>
		<%
			for(int i=0; i<list.size(); i++){
				DISTRIBUTOR entity = (DISTRIBUTOR)list.get(i);
		%>
		<tr>
			<td><%=entity.getCust_id() %></td>
			<td><%=entity.getPassword() %></td>
			<td><%=entity.getCust_name() %></td>
			<td><%=entity.getPhone() %></td>
			<td><%=entity.getAddress() %></td>
			<td>
				<input type="button" name="update" value="修改" onclick="doUpdate('<%=entity.getCust_id() %>')">
				<input type="button" name="update" value="刪除" onclick="doDelete('<%=entity.getCust_id() %>')">
			</td>
		</tr>
		<%
			} 
		%>
	</table>
	<input type="hidden" name="pageNum" value="<%=pageNum %>"/>
	<input type="hidden" name="custId"/>
	<input type="hidden" name="method"/>
</form>
<hr>
<input type="button" name="update" value="新增" onclick="doInsert()">
<%
	int totalPageInt = totalPage.intValue();
	int currentPage = pageNum.intValue();
	for(int i=1; i<=totalPageInt; i++){
		if(i!=currentPage)
			out.print("<a href='javascript:goto("+i+")'>");
		out.print(i + "");
		if(i!=currentPage)
			out.print("</a>");
		out.print("　");
	}
%>
<%}else{%>

<a href="test.do">查詢</a>

<%}%>
</body>
</html>