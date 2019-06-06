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


#How to build
run bellow commands
1) mvn clean 
2) mvn clean install

