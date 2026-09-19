MicroservicesGRPC/
├── docker-compose.yaml      # 1 postgres (2 DBs) + customer, billing, gateway
├── customer-service/        # own POM, JPA, DB=customer_db
├── billing-service/         # own POM, JPA, DB=billing_db, gRPC→customer
└── gateway/                 # own POM, REST-only edge, gRPC→services