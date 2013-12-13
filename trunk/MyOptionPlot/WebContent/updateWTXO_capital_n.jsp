<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
try {
	String Sname = request.getParameter("Sname");
	String urlString = "http://www.capitalfutures.com.tw/option/default.asp?Sname=TXO"+Sname+"%3B%A5x%BF%EF"+Sname+"&xy=1";
	//String urlString = "http://www.capitalfutures.com.tw/option/default.asp";
	System.out.println("Url = " + urlString);
	URL url = new URL(urlString);
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String line;
    
    while ((line = reader.readLine()) != null) {
        out.println(line);
        //out.println(new String(line.getBytes("BIG5"), "UTF-8"));
    }
    reader.close();
} catch (MalformedURLException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}

%>