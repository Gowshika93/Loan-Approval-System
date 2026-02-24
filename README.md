Loan service handles 

1. Loan application
2. Approval
3. Status
4. Emi Schedule
5. Repayment Tracking


client -> Gateway -> Loan service -> Credit Check -> Save Loan -> Kafka -> Notification

Flow

1. Client

2. Api Gateway

3. Microservices layer
  User Service
  Loan service
  Payment Service
  Notification Service
  Credit check service
  
4. Infra

Eureka
Config server
Kafka
Zipkin
Prometheus
MySQL


Component Design - Responsibilities of each service

Api Gateway
 - Single entry point
 - JWT Validation
 - Routing
 - Rate Limiting
User Servive
 - Registration
 - Login
 - JWT issuance
 - Role Management
Loan Service
 - Create loan aplictaion
 - Approve/ reject
 - Maintain status
 -Generate Emi schedule
Payment service
 -Handle payments
 -Update loan balance
 -Payment validation
Notification Service
 -Email/SMS alerts
 -Loan approval notifications
Credit check service
 -Credit score validation
 -Risk assessment

Pending (admin review)
Approved (disbursement)
Active (All emi paid)
Closed

Loan Table
loan_id
user_id
amount
interest_rate
tenure_months
status
created_at

Repayment table
repayment_id
loan_id
emi_amount
due_date
paid_flag

Audit table
event
timestamp
actor

