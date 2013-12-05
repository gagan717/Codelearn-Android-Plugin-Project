package com.example.test.activity;

import java.io.File;

import org.json.simple.JSONArray;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

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
    	Result result = junit.run(com.example.test.activity.SampleTest.class);
    	//System.out.println("completed " + result.getRunCount() + " tests");
    	//System.out.println("No. of failed test cases="+result.getFailures().size());
    	
       	

        failuresList = new JSONArray();
        for (Failure failure : result.getFailures()){
        	failuresList.add(failure.toString());
        }
        
        //plugin test
        if(PLUGIN_READY.equals("true")==false){
        	failuresList.add("testCodelearnPlugin(com.example.test.activity.SampleTest):\n Expected: \"true\" \n got: \""+PLUGIN_READY+"\"");
        }
        
        //to be accessed by reflection 
        failures_json=failuresList.toString();
        
        //output incl. plugin test
        System.out.println("Failed test cases: "+failuresList.size());	
        System.out.println(failures_json.toString());
       
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