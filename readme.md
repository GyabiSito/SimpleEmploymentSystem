# 💼 Employment System

Una aplicación web moderna hecha con **Spring Boot**, **Thymeleaf** y **Bootstrap 5** para ayudar a los responsables de RR.HH. a gestionar empleados y sus compensaciones de forma simple, rápida y profesional.

---

## 🚀 Funcionalidades

✅ Alta, edición y visualización de empleados
✅ Búsqueda avanzada con filtros por nombre o puesto
✅ Registro mensual de compensaciones: salario, bono, comisión, viáticos y ajustes
✅ Reportes de compensación mensual y desglose por tipo
✅ Validaciones inteligentes y manejo claro de errores
✅ Interfaz clara, responsiva y mobile-friendly con Bootstrap

---

## 🧠 Tecnologías utilizadas

* **Java 17**
* **Spring Boot 3** (MVC, Data JPA)
* **Hibernate**
* **Thymeleaf** para plantillas dinámicas
* **MySQL** como base de datos relacional
* **Bootstrap 5** para el diseño

---

## 🛠️ Puesta en marcha

### Requisitos

* Java 17
* MySQL 8+
* Maven

### Clonar y ejecutar

```bash
git clone https://github.com/your-repo/employment-system.git
cd employment-system

# Configurar base de datos
mvn spring-boot:run
```

### Configuración de la base de datos

```sql
CREATE DATABASE employee;
```

Y en tu `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 📸 Capturas de pantalla
---

### Empleados

<img width="1375" height="365" alt="image" src="https://github.com/user-attachments/assets/efefa657-b8f8-457f-a9a4-990367be45e2" />
<img width="1348" height="544" alt="image" src="https://github.com/user-attachments/assets/c3aecb6b-10df-4f0c-8558-fab2e1bc0246" />
<img width="1375" height="472" alt="image" src="https://github.com/user-attachments/assets/ec312fe1-5848-4891-85b1-ab7a498b71d9" />
<img width="1356" height="622" alt="image" src="https://github.com/user-attachments/assets/a2b8e760-f43b-4405-992d-7f9feb01d048" />

### Compensaciones

<img width="1359" height="448" alt="image" src="https://github.com/user-attachments/assets/a5ccd756-d28c-4874-bf96-58eaf8dad522" />
<img width="1383" height="356" alt="image" src="https://github.com/user-attachments/assets/bd844e87-715c-4099-8dba-2b65e077b80e" />
<img width="1335" height="275" alt="image" src="https://github.com/user-attachments/assets/549274e8-3e1b-41ab-98c5-9127137a8d3c" />
<img width="1347" height="443" alt="image" src="https://github.com/user-attachments/assets/63cd130e-3c90-4341-aa77-45c82ea6aec0" />

---

## 📂 Estructura del proyecto

```
.
├── pom.xml
├── mvnw / mvnw.cmd
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.gyabisito.employmentsystem
│   │   │       ├── annotations
│   │   │       ├── controllers
│   │   │       ├── entities
│   │   │       ├── enums
│   │   │       ├── repositories
│   │   │       ├── services
│   │   │       ├── utils
│   │   │       └── validators
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       │   ├── compensation_*.html
│   │       │   ├── employee_*.html
│   │       │   └── home.html
│   │       └── application.properties
│   └── test
│       └── java
│           └── com.gyabisito.employmentsystem
│               └── EmploymentSystemApplicationTests.java
└── target
```


