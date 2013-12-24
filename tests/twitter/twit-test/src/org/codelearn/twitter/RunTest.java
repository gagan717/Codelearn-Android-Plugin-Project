package org.codelearn.twitter;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	public List<String> failuresList;
	@SuppressWarnings("unchecked")
	public RunTest(String root) {
		
		
		
		System.out.println("Inside RunTest");
		
		PROJECT_ROOT=root;
		
		
		
		
		
		
		System.out.println("Root RunTest"+PROJECT_ROOT);
		JUnitCore junit = new JUnitCore();
    	Result result = junit.run(org.codelearn.twitter.TestMainActivity.class);
    	//System.out.println("completed " + result.getRunCount() + " tests");
    	//System.out.println("No. of failed test cases="+result.getFailures().size());
    	
       	

        failuresList = new ArrayList<String>();
        for (Failure failure : result.getFailures()){
        	System.out.println(failure.toString());
        	failuresList.add(failure.toString());
        }
        
       
        
        //output incl. plugin test
        System.out.println("Failed test cases: "+failuresList.size());	
        for(String str:failuresList){
        	System.out.println(str);
        }
        
       
       
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
		
		//new RunTest(parentparentparentparentDir.getAbsolutePath()+"/project/twitter/CodelearnTwitterApp");
		new RunTest("/Users/pratikone/Code/eclipse_workspace/tests_for_test/CodelearnTwitterApp");
		
	}
	
}