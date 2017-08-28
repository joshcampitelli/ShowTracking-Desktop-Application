Show Tracking Desktop Application
Current Version: 1.2

Author: Josh Campitelli
joshcampitelli@cmail.carleton.ca

General Notes on Progress/ Ideas to Implement:

Purpose of the Application is for myself, logging show history as I watch episodes. Already done by Netflix although majority of shows aren't on Netflix.
Version 1.0: All data stored locally, each time running the program the data within will reset. Although not very useful will create the framework for the 
	     later versions.

Version 1.1: All data stored in MySQL database using data Querying to insert, delete, and updata user info.

Version 1.2: Implement the user accounts and authentication using MD5 Hashing.

Version 1.3: Implement an interface for storing all the data (insert, delete, modify methods being abstract) then extend for two different implementations
	     one being MySQL Database for use on Desktop PC until remote access is created, other for use on laptop for the time being.

Version 1.4: Implement the GUI using JavaFX to build a simple window for data input. May require more research into show titles, thumbnails, seasons; 
	     look into API's for this information.

Hypothetical Future Versions:
-Provide viewing links for each of the logged shows, this would require extensive internet searching/logging to create a data farm.
-Late implementation could include a Chrome Plugin which automatically logs TV shows and Movies.
-Email Notifications for new release dates for your logged shows.

Technical Information:

-Compare names to already registered to make a database of tv shows, upon entering name of show
 check if someone is already tracking this show and verify if match.