# LifeStream (Spring Boot)

A minimal but production-style backend for a blood donation platform.

## Run locally

Requirements: JDK 17+, Maven

```bash
mvn spring-boot:run
```

Open Swagger UI at: `http://localhost:8080/swagger-ui.html`

H2 console: `http://localhost:8080/h2` (JDBC URL: `jdbc:h2:mem:lifestream`, user: `sa`, password: empty)

## Auth (JWT)

- Login: `POST /api/auth/login` with `{ "username":"donor1", "password":"password" }`
- Use returned token as header: `Authorization: Bearer <token>`

Seed users:
- admin / admin123 (ROLE_ADMIN)
- donor1 / password (ROLE_DONOR)
- rec1 / password (ROLE_RECIPIENT)

## Donor Flow

1) Schedule a donation: `POST /api/donor/schedule`
```json
{ "when": "2030-12-31T10:00:00" }
```
2) Complete donation (admin or nurse flow can be modeled separately): `POST /api/donor/complete/{appointmentId}`
```json
{ "bloodType": "O_POS", "units": 1 }
```

## Recipient Flow

Request units: `POST /api/recipient/request`
```json
{ "bloodType": "O_POS", "units": 2 }
```

## Inventory

- Check availability: `GET /api/inventory/availability`
- Adjust (admin only): `POST /api/inventory/adjust?type=O_POS&delta=2`

## Concurrency / "real-time" inventory

- Stock updates are executed inside a transaction with a pessimistic row lock per blood type to prevent race conditions.
- For true realtime to clients, add WebSocket/SSE that pushes inventory updates after each adjustment (not included to keep code compact).

---

**Note**: Change `app.jwt.secret` in `application.yml` before deploying.
