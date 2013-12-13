<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
try {
	String opym = request.getParameter("opym");
	String urlString = "http://tw.screener.finance.yahoo.net/future/aa03?opmr=optionfull&opcm=WTXO&opym=" + opym;
	System.out.println("Url = " + urlString);
	URL url = new URL(urlString);
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    String line;
    
    while ((line = reader.readLine()) != null) {
        out.println(line);
    }
    reader.close();
} catch (MalformedURLException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}

%>