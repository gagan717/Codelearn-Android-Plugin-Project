package com.example.test.runner;

import java.io.File;

import org.junit.runners.model.InitializationError;

import com.example.test.activity.RunTest;
import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;

public class SampleTestRunner extends RobolectricTestRunner {

	/**
	 * Call this constructor to specify the location of resources and AndroidManifest.xml.
	 * 
	 * @param testClass
	 * @throws InitializationError
	 */	
	public SampleTestRunner(@SuppressWarnings("rawtypes") Class testClass) throws InitializationError {
		super(testClass, new RobolectricConfig(new File(RunTest.PROJECT_ROOT+"/AndroidManifest.xml"), 
				new File(RunTest.PROJECT_ROOT+"/res")));
	}
}