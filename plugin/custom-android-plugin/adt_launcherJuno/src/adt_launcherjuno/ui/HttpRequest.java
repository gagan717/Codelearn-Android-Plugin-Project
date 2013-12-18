package adt_launcherjuno.ui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {
	public static void main(String message) {
		
		//String message = "my message";
    try {
        //URL url = new URL("http://androidtutorial.herokuapp.com/reports/incoming");
    	URL url = new URL("http://www.codelearn.org/android-tutorial/reports/incoming");
    	
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
 
        connection.setRequestProperty("Accept", "application/json");        

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(message);
        writer.close();
        
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // OK
        	System.out.println("received ok");
        } else {
            // Server returned HTTP error code.
        	System.out.println("received not ok");
        }
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}