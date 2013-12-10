package org.codelearn.twitter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.codelearn.test.runner.SampleTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowTextView;

import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


@RunWith(SampleTestRunner.class)
public class TestMainActivity{
	MainActivity activity;
	RelativeLayout loginTopLayout;
	
	
	@Before
	public void testprep() throws Exception{
		assertNotNull("MainActivity.java not found. You are in deep shit. Contact devs@codelearn.org", (activity=Robolectric.buildActivity(MainActivity.class).create().visible().get()));
		loginTopLayout=(RelativeLayout)LayoutInflater.from(activity).inflate(R.layout.activity_main,null);
	
	}
	
	//lesson 2
	@Test
    public void testLesson2() throws Exception {
        String app_name=activity.getResources().getString(R.string.app_name);
        assertTrue("Codelearn Twitter App project not found. Probably you are running the wrong project. Get some sleep. ", app_name.equalsIgnoreCase("Codelearn Twitter App"));
		
		/* 
		TextView view=(TextView)activity.findViewById(R.id.header);
		String helloFromActivity=(String) view.getText();
        
        assertTrue("Textbox string is not Hello world!", helloFromActivity.equalsIgnoreCase("Hello world!"));
        */
    }
	
	

	//lesson 3 this is sparta and lesson 5 hello twitter
	@Test
    public void testLesson3() throws Exception {
		TextView view;
		assertNotNull("No TextView found in activity_main.xml.",view=(TextView)loginTopLayout.getChildAt(0));
		
		assertNotNull("hello_world is not found in strings.xml", activity.getResources().getString(R.string.hello_world));

		String helloFromActivity = activity.getResources().getString(R.string.hello_world);
        
        assertTrue("String hello_world is empty!", helloFromActivity.length()>0 );

        assertTrue("TextView text is different from hello_world in strings.xml. Change TextView's android:text  to @string/hello_world", view.getText().toString().equals(helloFromActivity) );

        assertTrue("String hello_world is unchanged from Hello world!", helloFromActivity.equalsIgnoreCase("Hello world!")==false);
	
    }
	
	/*
	//lesson 5 &  6 username and layout
	@Test
    public void testLesson6() throws Exception {
		String helloTwitter=activity.getResources().getString(R.string.hello_twitter);
		assertTrue("hello_twitter is empty", helloTwitter.length()>0);
		
		//change in textbox and its id
		assertNotNull("Textview id is not @+id/header or missing. Check your layout xml", activity.findViewById(R.id.header));
		TextView view=(TextView)activity.findViewById(R.id.header);
		String helloFromActivity=(String) view.getText();
        assertTrue("Textbox string is not same as hello_twitter. Check your layout xml or strings.xml", helloFromActivity.equals(helloTwitter));

        
        
        int childcount = relative.getChildCount();
        
        int nTextView=0,nLinearLayout=0;
        
        for (int i=0; i < childcount; i++){
              View v = relative.getChildAt(i);
              if(v instanceof TextView || v instanceof LinearLayout){
            	  if(v instanceof TextView){
            		  nTextView++;
            		  ShadowTextView tview=(ShadowTextView) Robolectric.shadowOf(v);
            		  tview.
            		  //TextView temp=(TextView)v;
            		  
            	  }
              }
              
        }
        
        activity.findViewById(R.id.header);
        
        
        
		
        assertNotNull("Linear layout is missing or id is different", activity.findViewById(R.id.uname_block));
        
       // TextView view=(TextView) LayoutInflater.from(activity).inflate(R.layout.activity_main, null).findViewById(R.id.username);
        		
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
