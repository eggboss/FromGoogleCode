<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
try {
	String pmonth = request.getParameter("pmonth");
	if(pmonth==null || "".equals(pmonth)) pmonth="201309";
	String urlString = "http://yamstock.megatime.com.tw/asp/tfe/tfe_option.asp?routeindex=140000&pmonth=" + pmonth;
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