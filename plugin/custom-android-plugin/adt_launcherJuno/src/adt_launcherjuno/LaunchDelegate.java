package adt_launcherjuno;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;


import com.android.ide.eclipse.adt.internal.launch.AndroidLaunch;
import com.android.ide.eclipse.adt.internal.launch.AndroidLaunchController;
import com.android.ide.eclipse.adt.internal.launch.LaunchConfigDelegate;

@SuppressWarnings({ "restriction", "unused" })
public class LaunchDelegate extends LaunchConfigurationDelegate{

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
            ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		IProject project = getProject(configuration);
		
		// get an existing or new launch configuration
	   
		 ILaunchConfiguration config = AndroidLaunchController.getLaunchConfig(project,
	                "BLAH.LaunchConfigType");
        if (config != null) {
           //android launch
      
        	launch=new AndroidLaunch(config, mode, null);
         new LaunchConfigDelegate().launch(config, mode,launch , monitor);
        }

		
        
        Launcher.launchProjectTests(project);
        
		
	}
	
	
	/**
     * Returns the IProject object matching the name found in the configuration
     * object under the name
     * <code>IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME</code>
     * @param configuration
     * @return The IProject object or null
     */
    private IProject getProject(ILaunchConfiguration configuration){
        // get the project name from the config
        String projectName;
        try {
            projectName = configuration.getAttribute(
                    IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
        } catch (CoreException e) {
            return null;
        }

        // get the current workspace
        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        // and return the project with the name from the config
        return workspace.getRoot().getProject(projectName);
    }

}
