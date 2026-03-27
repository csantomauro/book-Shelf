# BookShelf API

A RESTful backend for managing a personal book library, with JWT-based authentication and full CRUD operations.

**Live demo:** https://bookshelf-frontend-ten.vercel.app  
**Backend:** https://book-shelf-2pql.onrender.com

## Tech stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.5 |
| Security | Spring Security, JWT (jjwt) |
| Database | PostgreSQL (hosted on Supabase) |
| API Docs | SpringDoc OpenAPI / Swagger UI |
| Deployment | Render (backend), Vercel (frontend) |

## Features

- JWT authentication — stateless, token-based login
- Full CRUD for books via REST endpoints
- Author management with one-to-many relationship to books
- Role-based users (USER / ADMIN) with BCrypt password hashing
- Swagger UI available at `/swagger-ui.html`
- CORS configured for frontend integration
- Database auto-provisioned via Hibernate DDL on first run
- Seed data loaded on first startup only

## Project structure
```
src/main/java/com/rtfs/BookShelf/
├── domain/
│   ├── AppUser.java              # User entity
│   ├── AppUserRepository.java    # User repository (not REST-exported)
│   ├── Author.java               # Author entity (one-to-many with Book)
│   ├── AuthorRepository.java     # Author repository
│   ├── Book.java                 # Book entity
│   ├── BookRepository.java       # Book repository with search by title/genre
│   └── AccountCredentials.java   # Login request record
├── service/
│   ├── JwtService.java           # JWT generation and validation
│   └── UserDetailsServiceImpl.java # Spring Security user loading
├── web/
│   ├── BookController.java       # GET /books, GET /health
│   └── LoginController.java      # POST /login
├── AuthenticationFilter.java     # JWT validation filter
├── AuthEntryPoint.java           # 401 error handler
├── SecurityConfig.java           # Security and CORS configuration
└── BookShelfApplication.java     # Entry point + seed data
```

## API endpoints

| Method | Path | Auth | Description |
|---|---|---|---|
| POST | `/login` | No | Returns JWT in Authorization header |
| GET | `/books` | Yes | List all books |
| GET | `/health` | No | Health check |
| GET/POST/PUT/DELETE | `/api/books` | Yes | Spring Data REST CRUD |
| GET | `/swagger-ui.html` | No | API documentation |

## Running locally

**Prerequisites**
- Java 17
- Gradle
- A PostgreSQL database (local or Supabase)

**1. Clone the repo**
```bash
git clone https://github.com/csantomauro/book-Shelf.git
cd book-Shelf
```

**2. Create a local properties file**

Create `src/main/resources/application-local.properties`:
```properties
DB_URL=jdbc:postgresql://your-host:5432/postgres?sslmode=require&prepareThreshold=0
DB_USER=your_user
DB_PASSWORD=your_password
JWT_SECRET=your_base64_encoded_secret
```

**3. Run with the local profile**
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

The API will be available at `http://localhost:8080`.

**4. Test the login endpoint**
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"user"}'
```

Copy the `Authorization` header from the response and use it as a Bearer token for subsequent requests.

## Environment variables

| Variable | Description |
|---|---|
| `DB_URL` | JDBC connection string |
| `DB_USER` | Database username |
| `DB_PASSWORD` | Database password |
| `JWT_SECRET` | Base64-encoded HS256 secret key |

## Deployment

The backend is containerised with Docker and deployed on Render's free tier. The database is hosted on Supabase (free tier, PostgreSQL). Environment variables are configured in Render's dashboard — no secrets are stored in the repository.

Frontend repository: [book-shelf-frontend](https://github.com/csantomauro/bookshelf-frontend)
