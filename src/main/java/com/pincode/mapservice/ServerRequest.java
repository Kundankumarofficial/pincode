package com.pincode.mapservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: sruthi.nair
 * Date: 8/1/13
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerRequest {

    private final String URL = "http://maps.googleapis.com/maps/api/geocode/json?";

    public JsonObject requestServer(Map<String, String> params)
    {
        JsonObject response = null;

        //create URL
        String url = createURL(params);

        //make call
        HttpResponse httpResponse = makeRequest(url);

        //convert to jsonobject
        response= parseResponse(httpResponse);
        return response;
    }

    public JsonObject parseResponse(HttpResponse httpResponse)
    {
        JsonObject jsonResponse = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line = "";
            StringBuffer buf = new StringBuffer("");
            while((line = rd.readLine())!=null) {
                buf.append(line);
            }
            jsonResponse = new JsonParser().parse(buf.toString()).getAsJsonObject();
            return jsonResponse;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public HttpResponse makeRequest(String url)
    {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpGet request = new HttpGet(url);
            return httpClient.execute(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (HttpException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createURL(Map<String, String> parameters)
    {
        String url = URL;
        for(String key: parameters.keySet())
        {
            url+=key+"="+parameters.get(key)+"&";
        }
        return url;
    }
}
