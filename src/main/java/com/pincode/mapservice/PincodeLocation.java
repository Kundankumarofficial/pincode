package com.pincode.mapservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jdi.connect.Connector;
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
 * User: venkata.lakshmi
 * Date: 8/1/13
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class PincodeLocation {
    private final String URL = "http://maps.googleapis.com/maps/api/geocode/json?";
    public PincodeLocation()
    {

    }

    public String getPinCode(Double latitude, Double longitude)
    {
        String pincode = null;
        Map<String, String> parameters =new HashMap<String, String>();
        parameters.put("sensor", "false");
        String latlng = latitude.toString()+","+longitude.toString();
        parameters.put("latlng",latlng);

        //create URL
        String url = createURL(parameters);

        //make call
        HttpResponse httpResponse = makeRequest(url);

        //convert to jsonobject
        JsonObject jsonObject = parseResponse(httpResponse);

        //parse Json Response
        JsonArray results = jsonObject.get("results").getAsJsonArray();
        JsonObject result = results.get(0).getAsJsonObject();
        JsonArray addressComponents = result.get("address_components").getAsJsonArray();
        for(int i=0; i<addressComponents.size(); i++)
        {
            JsonObject component = addressComponents.get(i).getAsJsonObject();
            JsonArray types = component.get("types").getAsJsonArray();
            for(int j =0; j<types.size(); j++)
            {
                if(types.get(j).getAsString().equals("postal_code"))
                {
                    pincode = component.get("long_name").getAsString();
                    break;
                }
            }
        }
        System.out.println(pincode);
        return pincode;
    }

    public static void main(String args[]) throws Exception
    {
        new PincodeLocation().getPinCode(12.929489099192073, 77.63295650482178);
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

