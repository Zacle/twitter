Video Link: https://www.youtube.com/watch?v=BBdDWn-kQNs

This is my project on using Twitter API to get tweets and using them within my application

This application allows the user to specify keywords of interest, and monitors tweets from a service that samples Twitter in
real time (or replays tweets that were previously recorded). It also plots the location of the user the originated the tweet
on a map of the world. To do this, the app makes use of an existing geographical mapping library and the Twitter API.x

Requirements
------------

Sign up as a Twitter developer

1) Sign up for a Twitter account if you don't already have one

2) Go to https://apps.twitter.com/ and click "Create New App".

3) Enter anything in the required fields, and create the application. You may use a placeholder for the website URL, such as ‘https://www.google.ca’.

4) On the application page, click the "Keys and Access Tokens" tab.

5) Copy the keys/tokens into the initializeTwitterStream() method in the twitter/LiveTwitterSource class. There should be four separate things to copy, including access tokens which may need to be generated lower down on the page.

6) Download, unzip, and add the following JARs to the project (Project Settings -> Modules -> Dependencies tab -> + JARS; use the + button in the bottom left of the modules pane):

a) http://twitter4j.org/archive/twitter4j-4.0.4.zip

lib -> twitter4j-core 

AND 

lib -> twitter4j-stream)

b) https://svn.openstreetmap.org/applications/viewer/jmapviewer/releases/2.3/JMapViewer-2.3.zip. 

JMapViewer.jar


Run the application
-------------------
Without making any changes to the code (other than the above steps), run the application (the main method is in ui/Application).
After a few seconds, you should see a control panel on the left containing some buttons and text boxes that you can interact
with to specify queries against the incoming tweets, and a map of the world on the right. The map should be zoomable
(use the mouse wheel or zoom controls in the upper left of the map) and scrollable (use the right mouse button).
Nothing should appear on the map, but if you enter search terms into the text box you should see each new query appearing
in a list below the control panel.

Troubleshooting: 

1) JUnit classes are not found. Position the cursor in the name of one of the classes with errors. Open the auto-resolver window (alt +  enter) and select “add JUnit 5 to path”.

2) When you select “Run”, a config panel comes up and the error says the module is not set: from the dropdown, make sure the module is set to “TwitterMapperStarter”.