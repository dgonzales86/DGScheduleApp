
Universal Scheduling Application

Overview

This application utilizes JDK 17.0.5, JavaFX, and mySQL DB connector for the purposes of coordinating user, contact, and customer appointments.
Both appointments and customers can be added, modified, or deleted using the GUI; corresponding changes are made to the database.
This project also includes a reports section, whereupon a schedule can be generated for a selected contact. Additionally, the reports
screen can generate counts of available database users, and a count of appointments by type and month. The login screen feature
language translation to french, and appends login attempts to an external text file.

Author and build information

Donald Gonzales, dgon148@wgul.edu, version 1.0, 02/26/2023
IntelliJ IDEA 2022.1.1 (Community Edition)
Java JDK 17.0.5 / openjfx SDK 11.0.2
MySQL Connector driver version 8.0.29

Installation/ run instructions

To run this application, a compatible Java JDK, JavaFX libraries, and mySQL connector must be present on user's system.
mySQL connector and JavaFX libraries must be imported into the application. A path variable must be set to the location
of javaFX on the user's system. and VM options must include the following arguments:
--module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics
Ideally the user will have a database with a matching schema so that the application will run properly.

Additional features

An additional report of my choice was included in the application to show all existing users. This was achieved using mySQL count() function.

