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

> Próximamente 😉

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

---

## 🤝 Contribuciones

¡Pull requests bienvenidos! Si querés proponer cambios grandes, abrí un issue primero.

---

## 📄 Licencia

MIT – Usalo libremente, mejoralo y compartilo 💡
