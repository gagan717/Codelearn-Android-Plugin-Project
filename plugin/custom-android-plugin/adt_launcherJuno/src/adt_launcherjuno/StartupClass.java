package adt_launcherjuno;

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
				
				Launcher.setPropertytofile("executedOnce","true");
				
				//fail all the tests for the first time except plugin test
				Launcher.launchProjectTests(null);
				
			}	
		}
		
		
		
			
		}


}
