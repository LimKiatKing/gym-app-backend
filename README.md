# gym-app-backend
This is a software development project to reinforce my coding
fundamentals. The key goal is to be able to build a full stack 
project.

This project consist of 2 repo, gym-app-backend and gym-app-frontend.

# Background
The inspiration for this app stems from a rock climber's frustration
with the existing, in some cases non-existent, climbing gym 
management app.

# Scope
The main function of this app is to be able to manage gym booking 
and share multi-pass (gym credits) with friends. 

Users will be able to:
- View gym schedule
- Book a gym slot
- Add/remove other users
- Utilize friend's multi-pass for gym booking

The following features is out of scope and will not be covered 
in this project: login authentication & payment system.

# Getting Started
The backend is run by Java and the database by MySQL, the initial 
data SQL script is provided in the backend repo.

Re-configure datasource in application.properties: 
```
## Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/gym_app_db
spring.datasource.username=root
spring.datasource.password=
```

Run 'GymAppApplication' class before starting the frontend.