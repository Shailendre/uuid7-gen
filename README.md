# UUID7 Project

The UUID7 Project is a Java-based implementation of UUID Version 7 as specified in [RFC 9562, Section 5.7](https://www.rfc-editor.org/rfc/rfc9562.html#section-5.7). UUIDv7 combines a Unix timestamp with random bits to generate unique identifiers that are time-ordered.

---

## Features

- **UUIDv7 Generation**: Generates unique, time-ordered UUIDs based on the current Unix timestamp in milliseconds.
- **RESTful API**: Provides endpoints for UUID generation and retrieval.
- **Web Interface**: Includes a user-friendly frontend to interact with the UUID service.

---

## Technology Stack

| Component   | Technology       |
|-------------|------------------|
| **Service** | JVM, Java 21     |
| **Frontend**| FreeMarker       |
| **Framework**| Spring Boot MVC |


---

## Installation

Follow these steps to set up the project locally:

1. **Clone the repository**:
   ```bash
	git clone https://github.com/Shailendre/uuid7-gen.git
   ```
2. **Navigate to the project directory and build**:
  ```bash
	cd uuid7-gen
	mvn clean install
	mvn spring-boot:run
  ```

---

## Accessing the Application

```bash
http://localhost:8080/
```


## API Access

```bash
curl localhost:8080/uuid-gen/v1/uuid7
```

## Response 
```json
{
"uuid" : "qwe3454-86uind-789",
"_64bits" : 1234,
"_128bits" : 1234567
}
```

---

## References

- [RFC 9562: Universally Unique Identifiers (UUID)](https://www.rfc-editor.org/rfc/rfc9562.html)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [FreeMarker Documentation](https://freemarker.apache.org/docs/)


---

## Licence 

This project is licensed under the MIT. See the [LICENSE](https://opensource.org/licenses/MIT) file for more details.
  

