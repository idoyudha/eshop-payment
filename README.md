# Payment Service
Payment service for E-Shop application. This service handles payment processing and management.

## Table of Contents
- Technology Stack
- Project Structure
- Getting Started
    - Prerequisites
    - Environment Variables
    - Running Application

### Tech Stack
- Java 21
- Springboot 3.2.1
- MySQL
- AWS S3
- AWS Cognito
- Apache Kafka
- Maven

### Project Structure
```
src/main/java/com/github/eshop/payment/
├── domain/                    # Business Rules
│   ├── entity/                # Business objects or models
│   │   ├── Payment.java
│   │   └── PaymentStatus.java
│   ├── repository/            # Repository interfaces
│   │   └── PaymentRepository.java
│   └── service/               # Domain logic
│       └── PaymentService.java
│
├── application/               # Application Business Rules
│   ├── dto/                   # Data Transfer Objects
│   │   ├── CreatePaymentRequest.java
│   │   └── PaymentResponse.java
│   ├── service/               # Use cases
│   │   └── PaymentApplicationService.java
│   └── exception/             # Application specific exceptions
│       └── PaymentException.java
│
├── infrastructure/            # Frameworks & Drivers
│   ├── config/                # Framework configurations
│   │   ├── S3Config.java
│   │   └── SecurityConfig.java
│   ├── persistence/           # Database implementations
│   │   ├── entity/
│   │   │   └── PaymentEntity.java
│   │   ├── repository/
│   │   │   └── JpaPaymentRepository.java
│   │   └── adapter/
│   │       └── PaymentRepositoryAdapter.java
│   ├── security/             # Security implementations
│   │   └── AuthenticationFilter.java
│   └── s3/                   # External service implementations
│       └── S3Service.java
│
└── controller/               # Controller Adapters
    ├── rest/                 # REST controllers
    │   ├── PaymentController.java
    │   └── GlobalExceptionHandler.java
    └── event/               # Event listeners
        └── PaymentKafkaListener.java
```

### Getting Started
#### Prerequisites
- Java 21
- Docker and Docker Compose
- AWS Account with S3 and Cognito Access
- MySQL

#### Environment Variables (value just for an example)
```
MYSQL_HOST=mysql-payment
MYSQL_PORT=3306
MYSQL_DATABASE=payments
MYSQL_USER=admin
MYSQL_PASSWORD=password
AWS_REGION=ap-southeast-x
AWS_ACCESS_KEY=AWSEXAMPLEACCESSKEY
AWS_SECRET_KEY=AwseXAMPLEsecRETk3Y
S3_BUCKET_NAME=awsexamples3bucketname
CLOUDFRONT_DOMAIN=https://cloudfrontexample.cloudfront.net

```
#### Running Application
Run with docker compose in root folder (same level with `\src`)
```
docker compose up -d
```
