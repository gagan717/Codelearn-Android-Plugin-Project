package adt_launcherjuno;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;









@SuppressWarnings("restriction")
public class TNewWizard  extends Wizard implements INewWizard {

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public boolean performFinish() {
		
		IProjectDescription description;
		try {
			/*
			 * 
			 * unzip a project into workspace
			 * 
			 */
			
			String dropinsFolder=Launcher.getEclipseHome()+"/dropins";
			extractFolder(dropinsFolder+"/twit.zip",true);
			
			
			/*
			 * import the unzipped project in workspace of eclipse
			 */
			
			description = ResourcesPlugin.getWorkspace().
					loadProjectDescription(  new Path(ResourcesPlugin.getWorkspace().getRoot().getLocation()+"/CodelearnTwitterApp/.project"));
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
			project.create(description, null);
			project.open(null);
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		return true;
	}
	
	//recursively unzip a zip file
	static public void extractFolder(String zipFile,boolean root) throws ZipException, IOException 
	{
	    //System.out.println(zipFile);
	    int BUFFER = 2048;
	    File file = new File(zipFile);

	    ZipFile zip = new ZipFile(file);
	    String newPath;
	    if(root==false){
	    	newPath = zipFile.substring(0, zipFile.length() - 4);
	    }else{
	    	IWorkspace workspace=ResourcesPlugin.getWorkspace();
	    	String workspacePath=workspace.getRoot().getLocation().toString();
	    	newPath=workspacePath;
	    }

	    //new File(newPath).mkdir();
	    Enumeration zipFileEntries = zip.entries();

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
	        /*
	        if (currentEntry.endsWith(".zip"))
	        {
	            // found a zip file, try to open
	            extractFolder(destFile.getAbsolutePath(),false);
	        }
	     		*/
	    }
	}

}
