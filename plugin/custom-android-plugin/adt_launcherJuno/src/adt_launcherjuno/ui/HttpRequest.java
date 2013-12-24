package adt_launcherjuno.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import adt_launcherjuno.Launcher;

public class HttpRequest {
	public static void main(String message) {
		
		//String message = "my message";
    try {
    	URL url = new URL("http://www.codelearn.org/android-tutorial/reports/incoming");
    	//URL url=new URL("http://192.168.1.128:3000/reports/incoming");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
 
        connection.setRequestProperty("Accept", "application/json");        
        connection.setConnectTimeout(30000);

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(message);
        writer.close();
        
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // OK
        	System.out.println("received ok");
           InputStreamReader reader=new InputStreamReader(connection.getInputStream());
           BufferedReader buff=new BufferedReader(reader);
        	final String receivedMsg=buff.readLine();
        	System.out.println("Received from server :"+receivedMsg);
        	Display.getDefault().asyncExec( new Runnable() { 
		        public void run() {
		        	String message=receivedMsg;
		        	if(Launcher.executedOnce.equalsIgnoreCase("false")){
		        		message="Welcome to Codelearn.org\nYou are logged in with "+Launcher.getPropertyfromfile("username")+"\n"+message;
		        		Launcher.executedOnce="true";
		        	}
		        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", message);
		        }
		    } );
        	
        } else {
        	System.out.println("received not ok");

        	Display.getDefault().asyncExec( new Runnable() { 
		        public void run() {
		        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", "Could not connect to www.codelearn.org. Test your internet connection.");
		        }
		    } );
        	
            // Server returned HTTP error code.
        }
    }catch(SocketTimeoutException e){
    	Display.getDefault().asyncExec( new Runnable() { 
	        public void run() {
	        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", "Could not connect to www.codelearn.org. Test your internet connection.");
	        }
	    } );
    	
    }catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}