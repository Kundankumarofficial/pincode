package com.test.pincode.mapservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pincode.mapservice.PincodeLocation;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: venkata.lakshmi
 * Date: 8/1/13
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PincodeLocationTest  {

    PincodeLocation pincodeLocation;

    @Before
    public void setUp() throws Exception
    {
        pincodeLocation = new PincodeLocation();
    }

    @Test
    public void testCreateURL() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sensor", "true");
        params.put("latlng", "40.714224,-73.961452");

        String url = pincodeLocation.createURL(params);
        assertEquals("http://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=true", url);
    }

    @Test
    public void testGetPinCode() throws Exception{
        double latitude = 40.71422400;
        double longitude = -73.961452;
        /*String pincode = pincodeLocation.getPinCode(latitude, longitude);
        System.out.println(pincode);
        assertEquals("11211",pincode);
        */
        JsonObject jsonObject = pincodeLocation.getPinCode(latitude, longitude);
        System.out.println(jsonObject);
    }

    @Test
    public void testMakeRequest() throws Exception
    {
        String url = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&latlng=40.71422400,-73.961452&";
        HttpResponse response = pincodeLocation.makeRequest(url);
        System.out.println(response.toString());
        assertNotNull(response);
    }

    @Test
    public void testParseResponse() throws Exception
    {
        String url = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&latlng=40.71422400,-73.961452&";
        HttpResponse response = pincodeLocation.makeRequest(url);
        JsonObject jsonObject = pincodeLocation.parseResponse(response);
        System.out.println(jsonObject);
        assertNotNull(jsonObject);
    }

}
