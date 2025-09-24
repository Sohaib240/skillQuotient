# Skill Quotient Library Assignment

### Docker File Steps:
- mvn clean package -DskipTests
- docker build -t skillquotient .
- docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=qa -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/librarydb -e SPRING_DATASOURCE_USERNAME=libraryuser -e SPRING_DATASOURCE_PASSWORD=your_password skillquotient

### DB Setup For Docker
I am using postgreSQL, you need to run DB and create a database named 'librarydb', and simply update the username and password in the above docker run command.


### Local Setup
If you want it to run on localhost, you can use dev properties and can update the username and password to make this code work.


### Why Use PostgreSQL
I used PostgreSQL for this library assignment because the data has clear relationships between borrowers and books, which fits well with a relational database.
It ensures data integrity through unique constraints for borrower emails and ISBNs, and supports transactions for borrowing and returning books, maintaining consistency.
PostgreSQL also allows efficient queries for listing books and checking availability, is open-source, reliable, and integrates well with Spring Boot, making it a practical choice for this project.

### Documentation
For Basic API documentation i have used swagger with default implementation, if want to extend the documentation part, can customize the configuration for swagger


