# FSWEB-S19-Challenge

## Twitter API

### Goal:
The purpose of this project is to practice all the topics we have learned with Spring Boot by designing a backend project. Our goal is to implement a Twitter-like application. How would we write this project? What would we pay attention to in design and implementation? How would we test it?

## Functional Requirements

- The project will be designed using Spring Boot technology. PostgreSQL will be used as the database.
- Endpoints:

### EASY
- `POST http://localhost:3000/tweet` => Create a tweet and save it to the database. The tweet must be associated with a specific user. Anonymous tweets are not allowed.
- `GET http://localhost:3000/tweet/findByUserId` => Retrieve all tweets of a specific user.
- `GET http://localhost:3000/tweet/findById` => Retrieve the details of a specific tweet.
- `PUT http://localhost:3000/tweet/id` => Update specific fields of an existing tweet.
- `DELETE http://localhost:3000/tweet/id` => Delete a tweet by ID. (Only the tweet owner can delete it.)

### MEDIUM
- `POST http://localhost:3000/comment` => Add a comment to a tweet.
- `PUT http://localhost:3000/comment/id` => Edit a comment made by a user.
- `DELETE http://localhost:3000/comment/id` => Delete a comment made by a user. (Only the comment owner or tweet owner can delete it.)
- `POST http://localhost:3000/like` => Like a tweet.
- `POST http://localhost:3000/dislike` => Remove a like from a tweet.

### HARD
- `POST http://localhost:3000/retweet` => Retweet a tweet. (Do not implement Twitter's built-in retweet functionality; create a custom implementation.)
- `DELETE http://localhost:3000/retweet/id` => Remove a retweet.

## Architectural Requirements

- Before implementing the API, design the database schema to support features like tweet, user, comment, like, retweet, etc.
- Implement a multi-layered architecture (Controller/Service/Repository/Entity).
- Implement Global Exception Handling for the system.
- Apply field validation for the Entity classes stored in the database.
- Follow Dependency Injection principles.
- Besides `/login` and `/register`, the system should include at least two additional endpoints protected by Spring Security.
- Write Unit Tests covering at least 30% of the functions in the project.

## FullStack Developer Muscles:

- Create a simple frontend for the Twitter API using React. The UI does not need to be complex; for example, a simple page listing all tweets of a user is sufficient.
- Experience handling CORS issues in real-world scenarios. Your React app runs on port 3200, while your Spring Boot backend runs separately.
- One of the endpoints you will test in a React component is `GET http://localhost:3000/tweet/findByUserId`.
- Can you resolve any encountered CORS issues?
