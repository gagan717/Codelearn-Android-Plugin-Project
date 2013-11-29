Codelearn Android Project

Author: Pratik Anand 

Last updated: 29th Nov 2013

twitter.com/pratikone

email:pratik[DOT]preet[AT]gmail[DOT]com

=================================================================


This project has 3 folders respectively :

plugin : Codelearn Android plugin project and its generated JAR

project: Codelearns Android projects like twitter project and their generated ZIPs

tests: Codelearn Android tests for projects like twitter project and generated JARs

Note: For working on these projects, copy contents of dropin-dependencies to your ECLIPSE dropins folder. Import all three projects in Eclipse workspace. Make sure your ECLIPSE_HOME variable is set, otherwise set it or change Build path to include jars from dropins folder of eclipse.


Twit-test project has RunTest.java which can be called to test TESTS.

ALL JARS MUST BE SAVED TO RESPECTIVE JARS FOLDERS. JAR FILES FROM THESE FOLDERS ARE COPIED TO DROPIN-DEPENDENCIES FOLDER.

===================================================================

Steps to run this plugin:

1)Use Eclipse bundled with android SDK OR install ADT plugin for a vanilla eclipse

2)Copy contents of dropins-dependencies folder to dropins folder of Eclipse

3)Load a new twitter Codelearn project from File->New->Other->Codelearn Android Application-> Twitter App

4)Once the project is created, click on Run button. A Run as dialog box will come(for the first run only).

5)Select Codelearn Android Application from the Run as box and click on OK.

The android project will launch and parallely all tests by Codelearn will be executed.

6) If it is the first launch, a dialog box asking Codelearn.org username will come up. Enter details and click on OK. Then tests will run.

7) The Codelearn website will show a popup showing the status of tests.



Contents of drop in dependencies:

adt_launcherCodelearn (plugin)

hamcrest.jar

json-simple.jar

junit.jar

maps.jar

roboelectric-tests.jar

twit.zip (mock twitter client project)

twit-tests.jar (tests for twitter project)

==========================================================