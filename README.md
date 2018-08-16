# portal
Nakshatra Academy Portal

To Build 

./mvnw clean install


Now, build the full application by executing ./mvnw clean install command inside the myapp directory. 
This will build the Java executable Jar in the backend/target directory. You can run the jar as shown below.


$ java -jar services/target/nia-portal.jar
You can view the application by firing up your favourite browser http://localhost:8080. 
You will see Angular application rendered as shown below.

Run UI module in Local
cd ui
mvn install

../mvnw ui:install-node-and-yarn ui:yarn

https://github.com/mfriedenhagen/dummy-lifecycle-mapping-plugin

===========
clean eclise .classpath
mvn eclipse:clean

reinstall
mvn eclipse:eclipse

java -jar -Dspring.profiles.active=PROD nia-portal.jar