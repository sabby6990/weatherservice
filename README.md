# weatherservice
This is a weather service application

# Type 
It is a spring boot application which can run as an independent web service. on port 8080 by default

# change default port number 
if you wish to change the default port number then simply add the bellow properties in the application.properties file
* server.port=888

#logging level
in order to change the logging level simple change the log level in the application.properties , default is set to DEBUG

#How to run 
* using eclipse simply run the project as spring boot application
* using command line simply run the jar present in the target folder after building the application using maven:

java -jar target/OpenWeatherApp-0.0.1-SNAPSHOT.jar

# how to test
use curl command to hit the webservice at localhost:8080/uploadcities with post request
with no basic authentication so far (for now no username password need to implement springsecuritycontext for Oauth authentication for services due to limited time.)

or you can use postman service with http://localhost:8080/uploadCities as port request url and select form-data --> key = file value = choose file.

#How to build
run bellow commands
1) mvn clean
2) mvn clean install

