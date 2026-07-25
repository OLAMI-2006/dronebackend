# 
Smart Drone Healthcare Logistics Platform (Backend)

The robust backend service powering the Smart Drone Healthcare Logistics Platform. Built with Java and Spring Boot, this API manages medical drone fleets, live telemetry data streams, medication payloads, and dispatch routes utilizing a digital twin architecture.



## Core Features

* **Drone Fleet Management:** Register, update, and monitor drone states, battery capacities, and operational limits.
* **Medication & Payload Tracking:** Handle medical delivery requests, payload types, and item restrictions.
* **Real-Time Telemetry:** Stream live flight telemetry data using WebSockets.
* **RESTful APIs:** Clean endpoints to control mission operations, check drone availability, and fetch delivery logs.



## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Data Persistence:** Jakarta Persistence (JPA) / Hibernate
* **Boilerplate Reduction:** Project Lombok
* **Real-Time Communication:** WebSockets


## Getting Started

Follow these steps to run the backend service locally.

### Prerequisites
* **Java Development Kit (JDK 17 or higher)** installed.
* **Maven** installed (or use the included `mvnw` wrapper).

### Installation & Execution

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/OLAMI-2006/dronebackend.git](https://github.com/OLAMI-2006/dronebackend.git)
