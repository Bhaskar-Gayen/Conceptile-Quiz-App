# Conceptile-Quiz-App


This is a simple Spring Boot application for managing a quiz system. Users can:

- Start a new quiz session.
- Fetch a random multiple-choice question from the database.
- Submit their answers.
- View a summary of their quiz, including the number of questions answered and their accuracy.

## Assumptions

1. A single user is pre-seeded into the system for demo purposes.
2. Questions are preloaded into an H2 database for simplicity.
3. Only one active session per user is supported at any time.
4. Question options are simple strings, and the correct answer is validated against the database.
5. This application is designed as a demo and is not optimized for production use.

## Technologies Used

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven (build tool)
- Lombok (for reducing boilerplate code)

## How to Run the Application

### Prerequisites

1. Install Java 21 and ensure it is configured in your system's PATH.
2. Install Maven.

### Steps to Run

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd quiz-application
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. The application will start on `http://localhost:8080`.

5. Access the H2 Console for database inspection:

    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (leave blank)

## API Endpoints

### Base URL

`http://localhost:8080/api/quiz`

### 1. Start a New Quiz Session

**Endpoint:**

```http
POST /start
```

**Request Body:**

```json
{
  "userId": 1
}
```

**Response:**

```json
{
  "sessionId": 1001
}
```

### 2. Fetch a Random Question

**Endpoint:**

```http
GET /question
```

**Query Parameters:**

- `sessionId`: ID of the active quiz session

**Response:**

```json
{
  "questionId": 101,
  "questionText": "What is the capital of France?",
  "options": ["Paris", "Berlin", "Rome", "Madrid"]
}
```

### 3. Submit an Answer

**Endpoint:**

```http
POST /answer
```

**Request Body:**

```json
{
  "sessionId": 1001,
  "questionId": 101,
  "selectedAnswer": "Paris"
}
```

**Response:**

```json
{
  "isCorrect": true,
  "correctAnswer": "Paris"
}
```

### 4. Get Quiz Summary

**Endpoint:**

```http
GET /summary
```

**Query Parameters:**

- `sessionId`: ID of the active quiz session

**Response:**

```json
{
  "totalAnswered": 5,
  "correctAnswers": 4,
  "incorrectAnswers": 1
}
```

## Sample Data

The application seeds the following sample questions into the database:

| Question ID | Question Text                            | Options                               | Correct Answer |
| ----------- | ---------------------------------------- | ------------------------------------- | -------------- |
| 101         | What is the capital of France?           | ["Paris", "Berlin", "Rome", "Madrid"] | Paris          |
| 102         | What is 2 + 2?                           | ["3", "4", "5", "6"]                  | 4              |
| 103         | Which planet is known as the Red Planet? | ["Earth", "Mars", "Jupiter", "Venus"] | Mars           |

## Notes

- This application is intended for demo purposes and has limited features.
- For production-level applications, consider using a relational database like MySQL or PostgreSQL and implement authentication mechanisms.


