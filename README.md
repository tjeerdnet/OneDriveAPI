# OneDrive API tutorial

This document explains how to use the Java/Jersey based OneDrive API to talk with the free OneDrive RESTful services
and is part of the tutorial series (http://www.tjeerd.net/2014/08/23/onedrive-api-restful-programming-in-java/ and
http://www.tjeerd.net/2014/09/14/onedrive-api-restful-programming-in-java-part-2-making-calls-with-java).

## 1. Registering your client/app
Before you can talk to the OneDrive RESTful services you need to register your client/app via the OneDrive Development
Dashboard. After you have registered your client/app you need to get an authorization code which you need to get so
called refresh and access tokens. Fore more information go to http://www.tjeerd.net/2014/08/23/onedrive-api-restful-programming-in-java/

## 2. Get your refresh token
In net.tjeerd.onedrive you will find the program Step1InitToken. This program expects you
to replace the values of the constants (CLIENT_ID, CLIENT_SECRET and AUTHORIZATION_CODE) with the information you
have prepared in step 1. When you run the program it will print out the refresh token.

## 3. Using the OneDrive API
Look for the OneDriveTest unit test to see how you can talk with the OneDrive RESTful API. Be sure that the values in
the test resources folder (onedrive.properties) are replaced with the client-id, client secret, authorization code
and refresh token you have prepared in step 1 and 2.

