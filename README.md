# Payment Service
Part of [eshop](https://github.com/idoyudha/eshop) Microservices Architecture.

## Overview
This service handles payment processing and management. Customer will upload the payment proof with image format. This service will save to Amazon S3. Then admin will validate it and send the event to order service to update the order status.

### Architecture
```
src/main/java/com/github/eshop/payment/
├── domain/             # business Rules
│   ├── entity/         # business objects or models
│   ├── repository/     # repository interfaces
│   └── service/        # domain logic
│
├── application/        # application Business Rules
│   ├── dto/            # data Transfer Objects
│   ├── service/        # use cases
│   └── exception/      # application specific exceptions
│
├── infrastructure/     # frameworks & Drivers
│   ├── config/         # framework configurations
│   ├── persistence/    # database implementations
│   │   ├── entity/     # entities of business logic (models) can be used in any layer
│   │   ├── repository/ # repository interface
│   │   └── adapter/    # jpa adapter repository
│   ├── security/       # security implementations
│   └── s3/             # external service implementations
│
└── controller/         # controller Adapters
    ├── rest/           # REST controllers
    └── event/          # event listeners
```

### Tech Stack
- Programming Language: Java
- CI/CD: Github Actions
- Framework: Springboot
- Database: MySQL and AWS S3
- Identity and Access Management: AWS Cognito
- Message Broker: Apache Kafka
- Container: Docker
