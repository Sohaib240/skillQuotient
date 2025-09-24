Docker File Steps:
- mvn clean package -DskipTests
- docker build -t skillquotient .
- docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=qa -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/librarydb -e SPRING_DATASOURCE_USERNAME=libraryuser -e SPRING_DATASOURCE_PASSWORD=your_password skillquotient


I am using postgreSQL, you need to run DB and create a database named 'librarydb', and simply update the username and password in the above docker run command.

If you want it to run on localhost, you can use dev properties and can update the username and password to make this code work.
