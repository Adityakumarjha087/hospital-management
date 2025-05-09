# Hospital Management System

A Spring Boot application for managing hospital operations including patient records, doctor information, and appointments.

## Features

- Patient Management
  - Add new patients
  - View patient list
  - Edit patient information
  - Delete patient records
- Doctor Management
  - Add new doctors
  - View doctor list
  - Edit doctor information
  - Delete doctor records
- Appointment Management
  - Schedule appointments
  - View appointment list
  - Update appointment status
  - Cancel appointments

## Technologies Used

- Java 11
- Spring Boot 2.7.0
- Spring Data JPA
- MySQL 8.0
- Thymeleaf
- Bootstrap 5
- Lombok
- Maven

## Prerequisites

- Java 11 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/hospital-management.git
   cd hospital-management
   ```

2. Configure MySQL:
   - Create a database named `hospitaldb`
   - Update `application.properties` with your MySQL credentials

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   - Open your browser and navigate to `http://localhost:8080`

## Configuration

The application uses the following default configuration:

- Server Port: 8080
- Database: MySQL
- Database Name: hospitaldb
- Database URL: jdbc:mysql://localhost:3306/hospitaldb

You can modify these settings in `src/main/resources/application.properties`.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 