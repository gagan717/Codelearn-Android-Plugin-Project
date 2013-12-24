package adt_launcherjuno;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.osgi.framework.Bundle;

import adt_launcherjuno.ui.HttpRequest;

import com.android.ide.eclipse.adt.internal.launch.AndroidLaunchController;


@SuppressWarnings("restriction")
public class Launcher implements ILaunchShortcut {
    public static String executedOnce="true";
	
	@Override
	public void launch(ISelection selection, String mode) {
		// TODO Auto-generated method stub
		 if (selection instanceof IStructuredSelection) {

	            // get the object and the project from it
	            IStructuredSelection structSelect = (IStructuredSelection)selection;
	            Object o = structSelect.getFirstElement();

	            // get the first (and normally only) element
	            if (o instanceof IAdaptable) {
	                IResource r = (IResource)((IAdaptable)o).getAdapter(IResource.class);

	                // get the project from the resource
	                if (r != null) {
	                    IProject project = r.getProject();
	                    
	                    if (project != null)  {
	                       
	                    	  launch(project, mode); //calls Launch Delegate
	                    	  
	                    	   
	                       
	                    }
	                }
	            }
	        }
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub
		
	}
	
	 private void launch(IProject project, String mode) {
	        // get an existing or new launch configuration
		    /*ILaunchConfiguration config = AndroidLaunchController.getLaunchConfig(project,
	                LaunchConfigDelegate.ANDROID_LAUNCH_TYPE_ID);*/
		 ILaunchConfiguration config = AndroidLaunchController.getLaunchConfig(project,
	                "BLAH.LaunchConfigType");
	        if (config != null) {
	            // and launch!
	            DebugUITools.launch(config, mode);
	         
	        }
	    }
	 
	 @SuppressWarnings("unchecked")
	public static void launchProjectTests(IProject project){
		
			System.out.println("Running launchProjectTests");
			
			
			String uid=authenticateWithServer(); //authenticate with Codelearn website
			//classpath for tests jar file
			List<String> classpath=getFiles();
			String projectRoot=null;
			
			try {	
			if(project!=null){
				 project.open(null /* IProgressMonitor */);
				
				 IJavaProject javaProject = JavaCore.create(project);
				 
				 
				projectRoot=new File(project.getLocationURI()).getAbsolutePath();
				
				 
				
				
				//add project classpath to classpath list
				String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
				
				for(int i=0;i<classPathEntries.length;i++){
					classpath.add(classPathEntries[i]);
				}
			
			} //if(project!=null)
			
			List<URL> urlList = new ArrayList<URL>();
			for (int i = 0; i < classpath.size(); i++) {
			 String entry = classpath.get(i);
			 IPath path = new Path(entry);
			 URL url =  path.toFile().toURI().toURL();
			 urlList.add(url);
			}
			
			ClassLoader parentClassLoader=null;
			if(project!=null){
				parentClassLoader = project.getClass().getClassLoader();
			}//if(project!=null) second check
			
			URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
			URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
				 
			//use of java reflection 
			Class<?> clazz = classLoader.loadClass("org.codelearn.twitter.RunTest");  //load class
				 
		    Constructor<?> ct = clazz.getConstructor(String.class);  //define constructor
			Object parameter=projectRoot; //create object to be passed in reflection class
			Object instance =ct.newInstance(parameter);	//create a new instance of class
			Field field=instance.getClass().getDeclaredField("failuresList");
			List<String> failuresList=(List<String>) field.get(instance);
			
			System.out.println("back to launchProjectTests");
			
			JSONArray json_array=new JSONArray();
			
			for(String str:failuresList){
		        	json_array.add(str);
		        }
			
			JSONObject obj = new JSONObject();
			
	        obj.put("uid",uid);
	        obj.put("failures", json_array);
	    	
	    	System.out.println(obj.toString());
	    	
	    	HttpRequest.main(obj.toString());
			
			
		  
		 } catch (ClassNotFoundException e) {
		 } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 
	 public static List<String> getFiles() {
		List<String> nameList=new ArrayList<String>();
		String eclipse_home=null;
		eclipse_home = getEclipseHome();
		
		
		//plugins
		File[] flist=listFilesMatching(new File(eclipse_home+"/plugins"),"org.eclipse.*.jar");
		for(File f:flist){
			nameList.add(f.getAbsolutePath());
		}

		//dropins
		flist=listFilesMatching(new File(eclipse_home+"/dropins"),".*");
		for(File f:flist){
			nameList.add(f.getAbsolutePath());
		}
		
		//apache
		flist=listFilesMatching(new File(eclipse_home+"/plugins"),"org.apache.commons.logging*.jar");
		for(File f:flist){
			nameList.add(f.getAbsolutePath());
		}
		
		
		return nameList;
	}
	
	public static File[] listFilesMatching(File root, String regex) {
	    if(!root.isDirectory()) {
	        throw new IllegalArgumentException(root+" is no directory.");
	    }
	    final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
	    return root.listFiles(new FileFilter(){
	        @Override
	        public boolean accept(File file) {
	            return p.matcher(file.getName()).matches();
	        }
	    });
	}

	public static String getEclipseHome(){
		String retStr=null;
		try {
			retStr=new File(new URL(System.getProperty("eclipse.home.location")).toURI()).getAbsolutePath();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return retStr;
		
	}
	
	public static String authenticateWithServer(){
		//Get username password from strings.xml
		String uid="blank";
		uid=getPropertyfromfile("uid");
    	
    	
    	return uid;
	}
	
	//return value from config.properties in dropins folder
	public static String getPropertyfromfile(String key){
		String value=null;
		String filePath=Launcher.getEclipseHome()+"/dropins/config.properties";
    	Properties prop = new Properties();
    	File file=new File(filePath);
    	if(file.exists()){
    	
	    	try {
	    		prop.load(new FileInputStream(filePath));
	    		value=prop.getProperty(key);
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
    	}
    	else{
    		Display.getDefault().asyncExec( new Runnable() { 
    	        public void run() {
    	        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", "config.properties not found in dropins folder");
    	        }
    	    } );
    		
    	}
    	return value;
	}
	
	public static String setPropertytofile(String key,String value){
		String filePath=Launcher.getEclipseHome()+"/dropins/config.properties";
    	Properties prop = new Properties();
    	File file=new File(filePath);
    	if(file.exists()){
    	
	    	try {
	    		prop.load(new FileInputStream(filePath));
	    		prop.setProperty(key, value);
	    		prop.store(new FileOutputStream(filePath), null);
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
    	}
    	else{
    		Display.getDefault().asyncExec( new Runnable() { 
    	        public void run() {
    	        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", "config.properties not found in dropins folder");
    	        }
    	    } );
    		
    	}
    	return value;
		
		
		

	}
	
}
