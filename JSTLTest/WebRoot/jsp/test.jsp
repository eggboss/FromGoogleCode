<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
// init
request.setAttribute("username","kirk");
//request.setAttribute("nulldata",null);
request.setAttribute("specialChar","<o>");
request.setAttribute("num1","12");
request.setAttribute("num2","2");
List<String> list = new ArrayList<String>();
list.add("list_data1");
list.add("list_data2");
list.add("list_data3");
request.setAttribute("list",list);

Map<String,String> map = new HashMap<String,String>();
map.put("mapkey1", "mapdate1");
map.put("mapkey2", "mapdate2");
map.put("mapkey3", "mapdate3");
request.setAttribute("map",map);
%>
<html>
  <head>
    <title>JSTL Test page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  
  	<c:out value="Welcome to JSTL!"/><br/>
  	<c:out value="Hi! ${username}"/><br/>
  	<!-- 傳入值為Null時，會使用default的值 -->
  	<c:out value="${nulldata}" default="is null"/><br/>
  	<!-- 測試特殊字元 ，在原始檔中<o>會被轉成&lt;o&gt;-->
  	<c:out value="${specialChar}"/><br/>
  	<!-- 取得request scope裡的username -->
  	<c:out value="${requestScope.username}"/><br/>
  	<!-- 計算，字串也可以計算 -->
  	<c:out value="${num1 / num2}"/><br/>
  	<!-- List -->
  	<c:out value="${requestScope.list[1]}"/><br/>
  	<!-- Map -->
  	<c:out value="${requestScope.map.mapkey2}"/>
  	
  	<br/>
  	<input type="radio" name="ra" value="11" onclick="radioOnChange(this);">11
  	<input type="radio" name="ra" value="12" onclick="radioOnChange(this);">12
  	
  	<br>
  	<br>
  	<input type="text" name="addString" value="${num1},${num2}"/>
  </body>
  	<script type="text/javascript">
	function getRadioValue(name){
		var radioObjs = document.getElementsByName(name);
		for(var i=0; i<radioObjs.length; i++){
			if(radioObjs[i].checked)
				return radioObjs[i].value;
		}
	}
	function radioOnChange(obj){
		alert(getRadioValue(obj.name));
	}
	getRadioValue('ra');
	</script>
</html>
