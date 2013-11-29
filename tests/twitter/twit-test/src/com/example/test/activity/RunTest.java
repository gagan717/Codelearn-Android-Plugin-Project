package com.example.test.activity;

import java.io.File;

import org.json.simple.JSONArray;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTest {
	public static String PROJECT_ROOT=null;
	public JSONArray failuresList;
	public String failures_json;
	@SuppressWarnings("unchecked")
	public RunTest(String root) {
		System.out.println("Inside RunTest");
		PROJECT_ROOT=root;
		System.out.println("Root RunTest"+PROJECT_ROOT);
		JUnitCore junit = new JUnitCore();
    	Result result = junit.run(com.example.test.activity.SampleTest.class);
    	System.out.println("completed " + result.getRunCount() + " tests");
    	System.out.println("No. of failed test cases="+result.getFailures().size());
    	
        for (Failure failure : result.getFailures())
            System.out.println("Failure message " + failure.toString()); 
        	

        failuresList = new JSONArray();
        for (Failure failure : result.getFailures()){
        	failuresList.add(failure.toString());
        }
        //to be accessed by reflection 
        failures_json=failuresList.toString();
        	
       
}
	
	
	public static void main(String[] args) {
		//all this effort to get path of  root of Codelearn Android Project
		
		File currentDir = new File(".");
		File parentDir = currentDir.getAbsoluteFile().getParentFile();
		File parentparentDir=parentDir.getAbsoluteFile().getParentFile();
		File parentparentparentDir=parentparentDir.getAbsoluteFile().getParentFile();
		File parentparentparentparentDir=parentparentparentDir.getAbsoluteFile().getParentFile();
		
		new RunTest(parentparentparentparentDir.getAbsolutePath()+"/project/twitter/twit");
	}
	
}