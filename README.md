# SportFlow 🏟️

SportFlow è un'applicazione enterprise basata su microservizi per la prenotazione di campi sportivi (padel, calcetto, basket) e l'organizzazione di partite pubbliche o private.

---

## 🏛️ Architettura del Sistema
Il progetto è strutturato come un **Mono-repo Maven Multi-modulo** composto dai seguenti microservizi:
* **`booking-service`**: Gestione dei centri sportivi, dei campi e delle prenotazioni orarie.
* **`auth-service`**: Servizio di identità, autenticazione e sicurezza basato su JWT.
* **`match-service`**: Gestione dell'organizzazione delle partite e accoppiamento giocatori.
* **`notification-service`**: Invio asincrono di notifiche tramite eventi.

---

## 🛠️ Stack Tecnologico
* **Java 21 (LTS)**
* **Spring Boot 3.3.x**
* **Maven** (Gestione multi-modulo)
* **PostgreSQL** (Database primario per ogni servizio)
* **Flyway** (Gestione migrazioni del database)
* **Docker & Docker Compose** (Infrastruttura e containerizzazione)

---

## 🚀 Requisiti Minimi
Per avviare il progetto in locale assicurati di avere installato:
* **Java SDK 21**
* **Docker & Docker Compose**
* **Maven 3.9+** (o il Maven Wrapper incluso)

---

## 📖 Sviluppo & Contributo
Il progetto segue una strategia Git Flow semplificata:
* Il codice stabile si trova su `main`.
* Lo sviluppo corrente avviene su `develop`.
* I singoli task vengono sviluppati su branch temporanei `feature/nome-task`.