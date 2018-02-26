To build this project, fist clone it to your computer and navigate to the tinyLMS directory.

From tinyLMS directory in terminal:

'gradle wrapper'

'./gradlew bootRun'


To start the front-end client:

From the tinyLMS directory:

'cd client'

'npm start'


Currently the application.properties file is set to create a new database every time the server starts. 

To save data, change the line "spring.jpa.hibernate.ddl-auto=create" to read "spring.jpa.hibernate.ddl-auto=none"


Navigate to http://localhost:4200 to start using tinyLMS.
