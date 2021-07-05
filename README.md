# Studer

## What is it?

Studer is an application dedicated to international students. The purpose is to help them to settle in Poland.
Preparation of the full-stack project included: the planning phase, UI and UX design process (Figma), 
front-end development in React, back-end development in Spring, database design and implementation (Postgres), 
adopting simple DevOps practices with Docker.

The application was created in line with the "mobile first" approach. It's fully responsible 
and can be accessed by smartphones, tablets, laptops and desktop computers.

###Features

*The Map*<br/>
The project contains a map with important places (prepared with the Leaflet library). Moreover, 
the application can access the user's location and shows places within a given distance radius.

*Find a Buddy*<br/>
There is a Find a Buddy functionality that helps newcomers to meet new people. The feature is still developed.
## Studer in action

### *Main page*
The main part of our website is video with a girl.
<br/>![screenshot1](src/main/resources/img/main_video.png)
<br/>
We wanted to show her as the foreign student lost in the new habitat. 
<br/>Under the page header we set our application's assumptions.
<br/>![screenshot1](src/main/resources/img/fundamentals.png)
<br/>
It should support students, deliver a guidance and inspire.
<br/>As we go further we can see that girl is changing and finally meet her new friend.
### *Map*
Our first functionality is a map with places useful for foreign students.
<br/>![screenshot1](src/main/resources/img/map_header.png)
<br/>
We can choose many types of places that we want to be shown on the map.
<br/>![screenshot1](src/main/resources/img/map_points.png)
<br/>
Additionally, we came up with idea of showing user location. If we click on the green button, map will center on place
that we are in, and blue circle will suggest that we are in its range.
<br/>![screenshot1](src/main/resources/img/map_user_location.png)
<br/>
Of course, we can zoom in and out the map.
### *What next?*
For now, we are finishing the functionality that we called 'Find a buddy', and starting a user profile section.

## Used technologies and tools

* Java 15
* React (Client part)
* Spring Boot
* PostgreSQL
* JSP
* Maven
* Docker

## How to run?

1. Download the repository to your machine. Download Client part as well that you can find 
   [there](https://github.com/emiliaszymanska/studerAppClient).
2. Open projects in your IDE. For Server part choose **userAndAdsRefactor** branch and for Client part - **profile** 
   or **buddyFinder** (as we still work on this project).
3. In the Client part run command *npm install*.
4. In the Server part create an empty PostgreSQL database. In the *resources* folder you can find 
   the *app-properties-template* file. Fill in the missing data there and save it as 'application.properties'.
5. Fire up main method in the App class.
6. Fill in the database that you created with sample data from the *sql_data* folder. First *install_postgis*, then 
   *example_data_for_database*. (If you're using IntelliJ and you have connected a database to it, click right mouse
   button on database name, choose *Run SQL Script* and then choose mentioned files  from *sql_data* folder)
7. Launch 'npm start' in the Client part, and our app will open.
