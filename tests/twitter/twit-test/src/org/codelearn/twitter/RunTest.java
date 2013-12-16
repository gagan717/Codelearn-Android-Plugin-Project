package org.codelearn.twitter;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codelearn.twitter.MainActivity;
import org.json.simple.JSONArray;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;

public class RunTest {
	public static String PROJECT_ROOT=null;
	public static String PLUGIN_READY=null;
	public JSONArray failuresList;
	public String failures_json;
	@SuppressWarnings("unchecked")
	public RunTest(String root,String pluginInstalled) {
		
		
		
		System.out.println("Inside RunTest");
		
		PROJECT_ROOT=root;
		PLUGIN_READY=pluginInstalled;
		
		
		
		
		
		System.out.println("Root RunTest"+PROJECT_ROOT);
		JUnitCore junit = new JUnitCore();
    	Result result = junit.run(org.codelearn.twitter.TestMainActivity.class);
    	//System.out.println("completed " + result.getRunCount() + " tests");
    	//System.out.println("No. of failed test cases="+result.getFailures().size());
    	
       	

        failuresList = new JSONArray();
        for (Failure failure : result.getFailures()){
        	System.out.println(failure.toString());
        	failuresList.add(failure.toString());
        }
        
        //plugin test
        if(PLUGIN_READY.equals("true")==false){
        	failuresList.add("testCodelearnPlugin(org.codelearn.twitter.TestMainActivity):\n Expected: \"true\" \n got: \""+PLUGIN_READY+"\"");
        }
        
        //to be accessed by reflection 
        failures_json=failuresList.toString();
        
        //output incl. plugin test
        System.out.println("Failed test cases: "+failuresList.size());	
        System.out.println(failures_json.toString());
        
       
       
}
	
	
	final  static String getProjectRoot(){
		
		return PROJECT_ROOT;
		
	}
	
	
	public static void main(String[] args) {
		//all this effort to get path of  root of Codelearn Android Project
		
		File currentDir = new File(".");
		File parentDir = currentDir.getAbsoluteFile().getParentFile();
		File parentparentDir=parentDir.getAbsoluteFile().getParentFile();
		File parentparentparentDir=parentparentDir.getAbsoluteFile().getParentFile();
		File parentparentparentparentDir=parentparentparentDir.getAbsoluteFile().getParentFile();
		
		new RunTest(parentparentparentparentDir.getAbsolutePath()+"/project/twitter/CodelearnTwitterApp","true");
		
	}
	
}