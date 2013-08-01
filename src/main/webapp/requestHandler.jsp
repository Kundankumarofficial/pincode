<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.pincode.mapservice.PincodeLocation" %>
<%--
  Created by IntelliJ IDEA.
  User: sruthi.nair
  Date: 8/1/13
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    double latitude = Double.parseDouble(request.getParameter("lat"));
    double longitude = Double.parseDouble(request.getParameter("lng"));
    PincodeLocation pincodeLocation = new PincodeLocation();
    String pincode = pincodeLocation.getPinCode(latitude, longitude);
    out.println(pincode);
%>