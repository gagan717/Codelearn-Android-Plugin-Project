package adt_launcherjuno.ui;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextDialog extends TitleAreaDialog{
	  private Text txtFirstName;
	  private String id;
	  private String message;
	  
	public TextDialog(Shell parentShell, String message) {
		super(parentShell);
		this.message=message;
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("Codelearn.org");
	    setMessage(this.message, IMessageProvider.INFORMATION);
	  }
	  
	  
	  @Override
	  protected Control createDialogArea(Composite parent) {
	    Composite area = (Composite) super.createDialogArea(parent);
	    Composite container = new Composite(area, SWT.NONE);
	    container.setLayoutData(new GridData(GridData.FILL_BOTH));
	    GridLayout layout = new GridLayout(1, false);
	    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    container.setLayout(layout);

	    createID(container);
	    
	    return area;
	  }


	  
	  private void createID(Composite container) {
	    Label lbtFirstName = new Label(container, SWT.NONE);
	    lbtFirstName.setText("Enter ID shown in site here and Run again");

	    GridData dataFirstName = new GridData();
	    dataFirstName.grabExcessHorizontalSpace = true;
	    dataFirstName.horizontalAlignment = GridData.FILL;

	    txtFirstName = new Text(container, SWT.BORDER);
	    txtFirstName.setLayoutData(dataFirstName);
	  }

	  @Override
	  protected boolean isResizable() {
	    return true;
	  }

	  // save content of the Text fields because they get disposed
	  // as soon as the Dialog closes
	  private void saveInput() {
	    id = txtFirstName.getText();
	   

	  }

	  @Override
	  protected void okPressed() {
	    saveInput();
	    super.okPressed();
	  }

	  public String getId() {
	    return id;
	  }

	  

}
