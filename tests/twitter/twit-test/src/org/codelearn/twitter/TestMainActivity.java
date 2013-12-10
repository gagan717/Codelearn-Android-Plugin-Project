package org.codelearn.twitter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.codelearn.test.runner.SampleTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


@RunWith(SampleTestRunner.class)
public class TestMainActivity{
	MainActivity activity;
	
	@Before
	public void testpre() throws Exception{
		activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
	}
	
	//lesson 2
	@Test
    public void testBasicResourceValue() throws Exception {
        
		boolean notZero=false;
		
		
		TextView view=(TextView)activity.findViewById(R.id.textBox);
		//String helloFromActivity=activity.getResources().getString(R.string.hello_world);
		 String helloFromActivity=(String) view.getText();
        if(helloFromActivity.equalsIgnoreCase("Hello world!")){
        	notZero=true;
        }
        assertTrue("Textbox string is not Hello world!", notZero!=false);
        
    }
	
	/*	

	//lesson 3 this is sparta and lesson 5 hello twitter
	@Test
    public void testStringChange() throws Exception {
		boolean notZero=false;
		String helloFromActivity = activity.getResources().getString(R.string.hello_world);
        if(helloFromActivity.length()>0){
        	notZero=true;
        }
        
        assertTrue("String is empty", notZero!=false);
	
    }
	
	
	//lesson 6 username and layout
	@Test
    public void testLesson6() throws Exception {
		
		
        assertNotNull("Linear layout is missing or id is different", LayoutInflater.from(activity).inflate(R.layout.activity_main, null).findViewById(R.id.uname_block));
        
        TextView view=(TextView) LayoutInflater.from(activity).inflate(R.layout.activity_main, null).findViewById(R.id.username);
        		
        assertNotNull("Username label is missing or id is different", view);
        int username_label_length=view.getText().length();
        assertTrue("Username lenght should not be zero", username_label_length>0 );
        
        EditText edit_view=(EditText)LayoutInflater.from(activity).inflate(R.layout.activity_main, null).findViewById(R.id.fld_username);
        		
        assertNotNull("Username Textbox is missing or id is different", edit_view);
    }

	//lesson 7 password and button with layout
		@Test
	    public void testLesson7() throws Exception {
			
			
			
	        assertNotNull("Linear layout for password is missing or id is different", activity.findViewById(R.id.pwd_block));
	        
	        TextView view=(TextView) activity.findViewById(R.id.pwd);
	        assertNotNull("Password label is missing or id is different", view);
	        int username_label_length=view.getText().length();
	        assertTrue("Username lenght should not be zero", username_label_length>0 );
	        
	        EditText edit_view=(EditText) activity.findViewById(R.id.fld_pwd);
	        assertNotNull("Password textbox is missing or id is different", edit_view);
	        
	        Button button=(Button) activity.findViewById(R.id.btn_login);
	        assertNotNull("Button is missing or id is different", button);
	    }
	
	//lesson 8 click listener
			@Test
		    public void testLesson8() throws Exception {
			InstrumentationTestCase testCase=new InstrumentationTestCase();
			// register next activity that need to be monitored.
			  ActivityMonitor activityMonitor = testCase.getInstrumentation().addMonitor(org.codelearn.twitter.TweetList.class.getName(), null, false);

			  // open current activity.
			  Activity myActivity=new MainActivity();
			  
			  
			  final Button button = (Button) myActivity.findViewById(R.id.btn_login);
			  myActivity.runOnUiThread(new Runnable() {
			    @Override
			    public void run() {
			      // click button and open next activity.
			      button.performClick();
			    }
			  });

			  Activity nextActivity = testCase.getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
			  // next activity is opened and captured.
			  assertNotNull("Click Listener not working. Check addListener to button part again",nextActivity);
			  nextActivity .finish();	
			
				
				
		    }
		    
		    
		    */
}
