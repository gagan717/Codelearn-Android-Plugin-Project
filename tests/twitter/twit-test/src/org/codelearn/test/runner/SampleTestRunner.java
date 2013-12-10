package org.codelearn.test.runner;

import java.io.File;

import org.codelearn.twitter.RunTest;
import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;


public class SampleTestRunner extends RobolectricTestRunner {

	 
	public SampleTestRunner(Class testClass) throws InitializationError {
		super(testClass);
	}
	
	
	
	@Override
    protected AndroidManifest createAppManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
		manifestFile=Fs.newFile(new File(RunTest.PROJECT_ROOT+"/AndroidManifest.xml"));
		resDir=Fs.newFile(new File(RunTest.PROJECT_ROOT+"/res"));
		assetsDir=Fs.newFile(new File(RunTest.PROJECT_ROOT+"/assets"));
		
		if (!manifestFile.exists()) {
		      System.out.print("WARNING: No manifest file found at " + manifestFile.getPath() + ".");
		      System.out.println("Falling back to the Android OS resources only.");
		      System.out.println("To remove this warning, annotate your test class with @Config(manifest=Config.NONE).");
		      return null;
		    }
		    AndroidManifest manifest = new AndroidManifest(manifestFile, resDir, assetsDir);
		    
		    String packageName = System.getProperty("android.package");
		    //manifest.setPackageName(packageName);
		    return manifest;

	}
	
}
