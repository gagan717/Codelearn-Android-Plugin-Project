
package adt_launcherjuno.ui;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DialogExamples extends ApplicationWindow {
  public static String username;
  /**
   * @param parentShell
   */
  public DialogExamples(Shell parentShell) {
    super(parentShell);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NULL);

    composite.setLayout(new GridLayout());
    
    
    /* ------ InputDialog ------------- */
   
        InputDialog dialog = new InputDialog(getShell(), "Question", "Enter Codelearn Email", "", null);
        if(dialog.open() == Window.OK) {
          System.out.println("Your favorite Java UI framework is: " + dialog.getValue());
          username = dialog.getValue();
        }else{
        	new DialogExamples(null).open();
        }
      
    
  
    return super.createContents(parent);
  }
  
 

  public static void main(String[] args) {
	  Display.getDefault().syncExec( new Runnable() { 
	        public void run() {
	        	ApplicationWindow window = new DialogExamples(null);
	            //window.setBlockOnOpen(true);
	            window.open();
	        }
	    } );
  }
}

