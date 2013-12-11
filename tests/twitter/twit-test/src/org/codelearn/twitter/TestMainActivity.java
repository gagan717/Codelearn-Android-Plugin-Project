package org.codelearn.twitter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.codelearn.test.runner.SampleTestRunner;
import org.codelearn.twitter.R.id;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowTextView;

import android.content.Intent;
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
	TweetListActivity tweetActivity;
	RelativeLayout loginTopLayout,tweetListLayout;
	
	
	@Before
	public void testprep() throws Exception{
		assertNotNull("MainActivity.java not found. You are in deep shit. Contact devs@codelearn.org", (activity=Robolectric.buildActivity(MainActivity.class).create().visible().get()));
		assertNotNull("Layout name is not activity_main. You are in deep shit. Contact devs@codelearn.org",loginTopLayout=(RelativeLayout)LayoutInflater.from(activity).inflate(R.layout.activity_main,null));
	
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
	
	
	@Test
	public void testLesson5(){
		TextView view;
		assertNotNull("No TextView found in activity_main.xml.",view=(TextView)loginTopLayout.getChildAt(0));

		assertTrue("TextView android:layout_width is not fill_parent.", view.getLayoutParams().width==-1); //layout width: fill_parent
		
		//hello twitter string
		assertNotNull("hello_twitter is not found in strings.xml", activity.getResources().getString(R.string.hello_twitter));

		String helloFromActivity = activity.getResources().getString(R.string.hello_twitter);
		assertTrue("TextView text is different from hello_twitter in strings.xml. Change TextView's android:text  to @string/hello_twitter", view.getText().toString().equals(helloFromActivity) );

		
	}
	
	
	
	@Test
    public void testLesson6() throws Exception {
		int views=loginTopLayout.getChildCount();
		int linearview=0;
		int textview=0;
		
		String lbl_username=null,lbl_enter_username=null;
		
		for(int i=0;i<views;i++){
			View v=loginTopLayout.getChildAt(i);
			if(v instanceof LinearLayout){
				linearview++;
				/*
				 * placeholder for checking horizontal orientation of Linear Layout
				 */
				int internalViews=((LinearLayout) v).getChildCount();
				for(int j=0;j<internalViews;j++){
					View internalV=((LinearLayout) v).getChildAt(j);
					if(internalV instanceof TextView){
						lbl_username=((TextView) internalV).getText().toString();
					}else if(internalV instanceof EditText){
						lbl_enter_username=((EditText) internalV).getText().toString();
					}
				}
				
				
			}else if(v instanceof TextView){
				textview++;
			}
			
		}
		assertTrue("TextView tag not found in activity_main.xml. Maybe you have messed up things in moving from last lesson.", textview!=0);
		assertTrue("Linear Layout not found in activity_main.xml", linearview!=0);
		
		//check the new strings
		assertNotNull("lbl_username is not found in strings.xml", activity.getResources().getString(R.string.lbl_username));
		assertNotNull("lbl_enter_username is not found in strings.xml", activity.getResources().getString(R.string.lbl_enter_username));

		
		//null check is optional
		if(lbl_username!=null)
			assertTrue("TextView text is not same as lbl_username of strings.xml", activity.getResources().getString(R.string.lbl_username).equals(lbl_username));
		
		if(lbl_enter_username!=null)
			assertTrue("EditText text is not same as lbl_enter_username of strings.xml", activity.getResources().getString(R.string.lbl_enter_username).equals(lbl_enter_username));
                    
    }

	
	
		@Test
	    public void testLesson7() throws Exception {
			int views=loginTopLayout.getChildCount();
			int linearview=0;
			int Buttonview=0;
			
			int idLinearLayoutSecond = -1;
			
			for(int i=0;i<views;i++){
				View v=loginTopLayout.getChildAt(i);
				if(v instanceof LinearLayout){
					linearview++;
					if(linearview==2){
						idLinearLayoutSecond=i;
					}
				}else if(v instanceof Button){
					Buttonview++;
				}

			}
			
			assertTrue("Second Linear Layout cannot be found in activity_main.xml", linearview==2);
			
			//second linear layout
			
			String lbl_pwd=null,lbl_enter_pwd=null;
			LinearLayout llayout=(LinearLayout)loginTopLayout.getChildAt(idLinearLayoutSecond);
			for(int i=0;i<llayout.getChildCount();i++){
				View internalV=((LinearLayout) llayout).getChildAt(i);
				if(internalV instanceof TextView){
					lbl_pwd=((TextView) internalV).getText().toString();
				}else if(internalV instanceof EditText){
					lbl_enter_pwd=((EditText) internalV).getText().toString();
				}
			}
			
			//check the new strings
			assertNotNull("lbl_pwd is not found in strings.xml", activity.getResources().getString(R.string.lbl_pwd));
			assertNotNull("lbl_enter_pwd is not found in strings.xml", activity.getResources().getString(R.string.lbl_enter_pwd));

			
			//null check is optional
			if(lbl_pwd!=null)
				assertTrue("TextView text of second Linear Layout is not same as lbl_pwd of strings.xml", activity.getResources().getString(R.string.lbl_pwd).equals(lbl_pwd));
			
			if(lbl_enter_pwd!=null)
				assertTrue("EditText text second Linear Layout is not same as lbl_enter_pwd of strings.xml", activity.getResources().getString(R.string.lbl_enter_pwd).equals(lbl_enter_pwd));
	            
			//check for login button 
			assertNotNull("lbl_login is not found in strings.xml. It will be used as label of the button", activity.getResources().getString(R.string.lbl_login));
			assertTrue("Button tag is missing in activity_main.xml", Buttonview!=0);
			
			assertTrue("More than one Button found in activity_main.xml", Buttonview==1);


			
	    }
		
		@Test
		public void testLesson8() throws Exception{
			/*
			 * placeholder for lesson 8 tests: styling and formatting
			 */
		}
		
  	
	
		@Test
	    public void testLesson9() throws Exception {
			int views=loginTopLayout.getChildCount();
			String buttonText=null;
			
			for(int i=0;i<views;i++){
				View v=loginTopLayout.getChildAt(i);
				 if(v instanceof Button){
					 v.performClick();
					 buttonText=((Button) v).getText().toString();
				}

			}
			
			assertTrue("Click Listener not listening to the button. Button's text is still the same.", buttonText.equals(activity.getResources().getString(R.string.lbl_login)));
				
	    }
		
		@Test
		public void testLesson10() throws Exception{
			assertNotNull("TweetListActivity.java not found. Try the steps again.", (tweetActivity=Robolectric.buildActivity(TweetListActivity.class).create().visible().get()));
			assertNotNull("Layout name is not activity_tweet_list. Try the steps again.",tweetListLayout=(RelativeLayout)LayoutInflater.from(tweetActivity).inflate(R.layout.activity_tweet_list,null));

			//no tweet found string
			assertNotNull("no_tweet_found is not found in strings.xml. It will be used as text displayed in activity TweetList when the button is clicked", activity.getResources().getString(R.string.no_tweet_found));
			
			TextView view=null;
			assertNotNull("TextView cannot be found in activity_tweet_list.xml . You probably have messed up the xml. Try again.", view=(TextView) tweetListLayout.getChildAt(0));
			
			String tweetListText=view.getText().toString();
			assertTrue("TextView of activity_tweet_list.xml has different text than no_tweet_found of strings.xml.", tweetListText.equals(activity.getResources().getString(R.string.no_tweet_found)));


			//intent
			ShadowActivity activityS=Robolectric.shadowOf(activity);
			Intent startedIntent=activityS.getNextStartedActivity();
	        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);
	        assertThat("Intent is not working on the button. See, if the classes are (MainActivity.this, TweetListActivity.class) !",shadowIntent.getComponent().getClassName(), equalTo(TweetListActivity.class.getName()));


			
		}
		    
		    
		    
}
