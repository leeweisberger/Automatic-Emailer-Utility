

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class testglassdoor {
    
    public static String job = "engineer";

    public static void main(String[] args) throws MalformedURLException, IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("http://api.glassdoor.com/api/api.htm?t.p=21079&t.k=iiqhBV7VuEE&userip=0.0.0.0&useragent=&format=json&v=1&action=jobs-prog&countryId=1&jobTitle="+job).openConnection();
    
        conn.setDoOutput(true);
        conn.setDoInput(true);
        int responseCode = conn.getResponseCode();
        
        System.out.println("Response Code : " + responseCode);
     
        BufferedReader in = 
                 new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
     
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        JsonElement jelement = new JsonParser().parse(response.toString());
        JsonObject  jobject = jelement.getAsJsonObject();
       System.out.println(jobject.get("response").getAsJsonObject().getAsJsonArray("results").get(0).getAsJsonObject().get("medianSalary")); 
    
    }
}
