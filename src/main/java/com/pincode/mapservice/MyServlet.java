package com.pincode.mapservice;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: sruthi.nair
 * Date: 8/1/13
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double latitude = Double.parseDouble(request.getParameter("lat"));
        double longitude = Double.parseDouble(request.getParameter("lng"));
        PincodeLocation pincodeLocation = new PincodeLocation();
        String pincode = pincodeLocation.getPinCode(latitude, longitude);
        PrintWriter out = response.getWriter();
        out.print(pincode);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
