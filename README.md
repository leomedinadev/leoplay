# LeoPlay

API REST para gestionar un catálogo de películas, con recomendaciones generadas por IA (OpenAI vía LangChain4j).

Proyecto de práctica basado en un curso de Spring Boot, extendido con validaciones, manejo de errores y configuración por perfiles.

## Stack

- Java 21 · Spring Boot 3.5 (Web, Data JPA, Validation)
- PostgreSQL · Docker Compose
- LangChain4j + OpenAI (`gpt-4o-mini`)
- MapStruct · Lombok · springdoc-openapi (Swagger UI)
- Gradle

## Estructura

```
src/main/java/ec/com/leodev/leoplay
├── domain        # DTOs, enums, excepciones, puertos (IMovieRepository) y servicios
├── persistence   # Entidades JPA, mappers MapStruct y adaptador del repositorio
└── web           # Controladores REST y manejo global de errores
```

## Cómo ejecutar

**Requisitos:** Java 21 y Docker.

```bash
./gradlew bootRun
```

En el perfil `dev` (el predeterminado), `spring-boot-docker-compose` levanta PostgreSQL con `docker-compose.yaml` automáticamente.

- API: `http://localhost:8090/leo-play/api/movies`
- Swagger UI: `http://localhost:8090/leo-play/api/swagger-ui/index.html`

Para las recomendaciones con IA define tu clave; sin ella se usa la clave `demo` de LangChain4j:

```bash
export OPENAI_API_KEY=sk-...
```

### Producción

El perfil `prod` toma todas las credenciales de variables de entorno (ver `.env.example`):

| Variable | Descripción |
|---|---|
| `DB_URL` | URL JDBC de PostgreSQL |
| `DB_USERNAME` / `DB_PASSWORD` | Credenciales de la base |
| `OPENAI_API_KEY` | Clave de OpenAI |
| `PORT` | Puerto HTTP (por defecto 8080) |

```bash
SPRING_PROFILES_ACTIVE=prod ./gradlew bootRun
```

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/movies` | Lista todas las películas |
| `GET` | `/movies/{id}` | Obtiene una película (404 si no existe) |
| `POST` | `/movies` | Crea una película (400 si es inválida o el título ya existe) |
| `PUT` | `/movies/{id}` | Actualiza título, fecha y rating (404 si no existe) |
| `DELETE` | `/movies/{id}` | Elimina una película (204, o 404 si no existe) |
| `POST` | `/movies/suggest` | Recomienda hasta 3 películas según tus gustos |
| `GET` | `/hello` | Saludo de bienvenida generado por IA |

Ejemplo de creación:

```json
POST /leo-play/api/movies
{
  "title": "Interstellar",
  "duration": 169,
  "gender": "SCI_FI",
  "releaseDate": "2014-11-07",
  "rating": 4.8
}
```

Géneros válidos: `ACTION`, `COMEDY`, `DRAMA`, `ANIMATED`, `HORROR`, `SCI_FI`.

## Tests

```bash
./gradlew test
```
