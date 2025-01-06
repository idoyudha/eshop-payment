# Payment Service
Part of [eshop](https://github.com/idoyudha/eshop) Microservices Architecture.

## Overview
This service handles payment processing and management. Customer will upload the payment proof with image format. This service will save to Amazon S3. Then admin will validate it and send the event to order service to update the order status.

### Architecture
```
src/main/java/com/github/eshop/payment/
├── domain/                    # Business Rules
│   ├── entity/                # Business objects or models
│   ├── repository/            # Repository interfaces
│   └── service/               # Domain logic
│
├── application/               # Application Business Rules
│   ├── dto/                   # Data Transfer Objects
│   ├── service/               # Use cases
│   └── exception/             # Application specific exceptions
│
├── infrastructure/            # Frameworks & Drivers
│   ├── config/                # Framework configurations
│   ├── persistence/           # Database implementations
│   │   ├── entity/
│   │   ├── repository/
│   │   └── adapter/
│   ├── security/             # Security implementations
│   └── s3/                   # External service implementations
│
└── controller/               # Controller Adapters
    ├── rest/                 # REST controllers
    └── event/               # Event listeners
```

### Tech Stack
- Java
- Springboot
- MySQL
- AWS S3
- AWS Cognito
- Apache Kafka
- Maven
