# Studer

## What is it?
***
The purpose of the application is to provide support to foreign students.
****

## Studer in action
***

## Used technologies and tools
***
* Java 15
* React (Client part)
* Spring Boot
* PostgreSQL
* JSP
* Maven
* Docker

## How to run?
***
1. Download the repository to your machine. Download Client part as well that you can find 
   [there](https://github.com/emiliaszymanska/studerAppClient){:target="_blank" rel="noopener"}.
2. Open projects in your IDE. For Server part choose **userAndAdsRefactor** branch and for Client part - **profile** 
   (as we still work on this project).
2. In the Client part run command *npm install*.
2. In the Server part create an empty PostgreSQL database. In the *resources* folder you can find 
   the *app-properties-template* file. Fill in the missing data there and save it as 'application.properties'.
3. Fire up main method in the App class.
4. Fill in the database that you created with sample data from the *sql_data* folder. First *install_postgis*, then 
   *example_data_for_database*. (If you're using IntelliJ and you have connected a database to it, click right mouse
   button on database name, choose *Run SQL Script* and then choose mentioned files  from *sql_data* folder)
5. Launch 'npm start' in the Client part, and our app will open.
