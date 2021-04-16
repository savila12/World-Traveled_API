# A World Well Traveled

This is the backend for a World Traveled tracking application. It allows users to create a new custom adventure as well as see past travels. We've created various endpoints that will allow users to view and manipulate the data stored in the database at their leisure. We use authentication to ensure that all adventures are only visible to each individual user once they are logged in. 

### Technologies Used
- Java
- Spring Boot
- Postgres
- Postman (for testing endpoints)

### Domain Models & ERD

**User**
- **PK** Long id
- **FK** Long userProfileId
- String userName
- String email
- String password *(encoded)*

**Adventure**
- **PK** Long id
- **FK** Long userId
- **FK** Long countryId
- String dateWent
- String adventureDescription
- String countryName

**Country**
- **PK** Long id
- String countryName

**UserProfile**
- **PK** Long id
- String firstName
- String lastName
- String profileDescription

![World Traveled ERD](docs/image.png)
