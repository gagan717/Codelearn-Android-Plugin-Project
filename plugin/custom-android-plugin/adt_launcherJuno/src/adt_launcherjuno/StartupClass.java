package adt_launcherjuno;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;

public class StartupClass implements IStartup{
	
	@Override
	public void earlyStartup() {
		
		
		String executedOnce=Launcher.getPropertyfromfile("executedOnce");
		if(executedOnce!=null){
			if(executedOnce.equalsIgnoreCase("false")){
				
				Display.getDefault().asyncExec( new Runnable() { 
			        public void run() {
			        	String username=Launcher.getPropertyfromfile("username");
			        	MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Codelearn Plugin", "Welcome to Codelearn.org \nYou are logged in with "+username);
			        }
			    } );
				
				//copy roboelectric 2.0 maven dependencies to user.home/.m2 folder
				try {
					extractToUSERHOME();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Launcher.setPropertytofile("executedOnce","true");
				
				//fail all the tests for the first time except plugin test
				Launcher.launchProjectTests(null);
				
			}	
		}
		
		
		}
	
	
	
	void extractToUSERHOME() throws IOException{
		String userHome=System.getProperty("user.home");
		 //System.out.println(zipFile);
	    int BUFFER = 2048;
	    File file = new File(Launcher.getEclipseHome()+"/dropins/m2.zip");

	    ZipFile zip = new ZipFile(file);
	    String newPath;
   
    	
    	newPath=userHome;
   

	    //new File(newPath).mkdir();
	    Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

	    // Process each entry
	    while (zipFileEntries.hasMoreElements())
	    {
	        // grab a zip file entry
	        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	        String currentEntry = entry.getName();
	        File destFile = new File(newPath, currentEntry);
	        //destFile = new File(newPath, destFile.getName());
	        File destinationParent = destFile.getParentFile();

	        // create the parent directory structure if needed
	        destinationParent.mkdirs();

	        if (!entry.isDirectory())
	        {
	            BufferedInputStream is = new BufferedInputStream(zip
	            .getInputStream(entry));
	            int currentByte;
	            // establish buffer for writing file
	            byte data[] = new byte[BUFFER];

	            // write the current file to disk
	            FileOutputStream fos = new FileOutputStream(destFile);
	            BufferedOutputStream dest = new BufferedOutputStream(fos,
	            BUFFER);

	            // read and write until last byte is encountered
	            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
	                dest.write(data, 0, currentByte);
	            }
	            dest.flush();
	            dest.close();
	            is.close();
	        }
	       
	    }
	  }
		
	}



