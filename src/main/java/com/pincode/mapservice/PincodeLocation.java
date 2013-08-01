package com.pincode.mapservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
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
    public PincodeLocation()
    {

    }

    public JsonObject getPinCode(Double latitude, Double longitude)
    {
        String pincode = null;

        Map<String, String> parameters =new HashMap<String, String>();
        parameters.put("sensor", "false");
        String latlng = latitude.toString()+","+longitude.toString();
        parameters.put("latlng",latlng);

        JsonObject jsonObject = new ServerRequest().requestServer(parameters);

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

        //convert to JsonObject
        JsonObject jsonResponse = new JsonObject();
        JsonPrimitive jsonPrimitive = new JsonPrimitive(pincode);
        jsonResponse.add("pincode", jsonPrimitive);

        return jsonResponse;
    }

    public static void main(String args[]) throws Exception
    {
        new PincodeLocation().getPinCode(12.929489099192073, 77.63295650482178);
    }

}

