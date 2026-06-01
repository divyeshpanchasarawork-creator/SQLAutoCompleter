# SQL Auto Completer

A backend REST API built with Java and Spring Boot that provides context-aware SQL autocompletion. It connects to a live database, extracts its schema at runtime, and uses a **Trie data structure** to serve prefix-based suggestions for SQL keywords, table names, and column names.

---

## How It Works

1. **Connect** — Send your database credentials via `POST /api/connect`. The API establishes a JDBC connection, introspects the schema using `DatabaseMetaData`, and builds a Trie pre-loaded with SQL keywords plus all extracted table and column names.
2. **Session** — A UUID session ID is returned and stored in a `ConcurrentHashMap`-backed `SessionManager`. The Trie is scoped to that session.
3. **Suggest** — Send any prefix via `GET /api/suggest`. The Trie performs a prefix traversal in O(k) time (where k = prefix length) and returns all matching completions.

---

## Features

- **Trie-based prefix search** — Custom `Trie` and `TrieNode` implementation with HashMap children. O(k) lookup regardless of dictionary size.
- **Live schema introspection** — Extracts table names and column names from any connected database at runtime using JDBC `DatabaseMetaData`.
- **Multi-database support** — Supports MySQL and PostgreSQL via dynamic driver loading.
- **Session management** — Each connection gets its own isolated Trie session, stored thread-safely in `ConcurrentHashMap`.
- **CORS configured** — Ready for frontend integration out of the box.
- **50+ SQL keywords** — Pre-loaded keywords covering DML, DDL, clauses, functions, operators, and data types.

---

## Tech Stack

- Java 21
- Spring Boot 3
- JDBC (MySQL + PostgreSQL)
- Maven

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/connect` | Connect to a database and get a session ID |
| `GET` | `/api/suggest?sessionId=&prefix=` | Get autocomplete suggestions for a prefix |

---

## Setup

### Prerequisites
- Java 21+
- Maven
- A running MySQL or PostgreSQL instance

### Run

```bash
mvn spring-boot:run
```

No `application.properties` database config needed — credentials are passed per request at runtime.

---

## Usage

**Connect to a database:**
```json
POST /api/connect
{
  "url": "jdbc:mysql://localhost:3306/your_db",
  "username": "root",
  "password": "yourpassword",
  "dbType": "MYSQL"
}
```
Returns: `"3f92a1b4-..."`  ← your session ID

**Get suggestions:**
```
GET /api/suggest?sessionId=3f92a1b4-...&prefix=SE
```
Returns:
```json
{
  "prefix": "SE",
  "words": ["SELECT"]
}
```

```
GET /api/suggest?sessionId=3f92a1b4-...&prefix=us
```
Returns:
```json
{
  "prefix": "us",
  "words": ["users", "user_id", "username"]
}
```

---

## Project Structure

```
src/main/java/com/divyesh/panchasara/SQLAutoCompleter/
├── beans/
│   ├── Trie.java                   # Trie with insert, search, prefix traversal
│   └── TrieNode.java               # HashMap-based node
├── config/
│   ├── CorsConfig.java             # CORS setup for cross-origin requests
│   └── SqlKeywordConfig.java       # 50+ SQL keywords as a Spring Bean
├── controller/
│   ├── AutoCompleteController.java # GET /api/suggest
│   └── ConnectionController.java   # POST /api/connect
├── model/
│   ├── ConnectionRequest.java
│   ├── ConnectionSession.java      # Holds session ID + Trie
│   └── SuggestionResponse.java
├── service/
│   ├── AutoCompleteService.java    # Looks up session, queries Trie
│   ├── ConnectionService.java      # JDBC connection via dynamic driver loading
│   ├── SchemaExtractorService.java # Pulls table + column names via DatabaseMetaData
│   └── TrieBuilderService.java     # Inserts schema tokens into Trie
└── sessions/
    └── SessionManager.java         # ConcurrentHashMap session store
```
