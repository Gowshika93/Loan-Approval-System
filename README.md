Bank Loan Approval System
Capstone Project Presentation


5
Microservices	12
REST Endpoints	4
Databases	5
Kafka Topics
 
 System Architecture
The application follows a microservices architecture pattern where each business capability is owned by an independent service with its own database, deployment lifecycle, and technology stack.

Service	Technology	Responsibility
API Gateway	Spring Cloud Gateway + WebFlux	Single entry point — JWT validation, routing, circuit breaking
Auth Service	Spring Boot + Spring Security	User management, JWT signing with RSA-256
Customer Service	Spring Boot + JPA	KYC profiles, credit score management
Loan Service	Spring Boot + Feign + Kafka	Loan lifecycle: apply, approve, disburse
Doc & Notif Service	Spring Boot + Kafka	Document upload/verification, notifications

Infrastructure Components:
•	MySQL 8: 4 independent databases — one per service (database-per-service pattern)
•	Apache Kafka: Async event streaming for loan approval and document verification notifications
•	Eureka Server: Service discovery registry — services register and discover each other by name
•	Docker: Kafka and Zookeeper run in Docker containers for easy local setup
Role-Based Access Control
Role	Token obtained by	Can access
USER	Any registered user via /api/auth/login	Apply loan, check status, upload documents, view own profile
ADMIN	admin@bank.com / admin123 via /api/auth/login	Approve/reject loans, disburse funds, verify documents, view credit scores
 
 Complete Loan Lifecycle
Stage	Endpoint	Role	Status Change
Application	POST /api/loans/apply	USER	→ APPLIED
Review & Approve	POST /api/loans/approval/submit	ADMIN	→ APPROVED
Rejection	POST /api/loans/approval/submit (approved:false)	ADMIN	→ REJECTED
Disbursement	POST /api/loans/payment/disburse	ADMIN	→ DISBURSED
Complete API Reference

Method	Endpoint	Auth	Description
POST	/api/auth/register	None	Register new user account
POST	/api/auth/login	None	Login — returns JWT accessToken
POST	/api/customers/register	None	Register KYC profile
GET	/api/customers/{id}	USER	Get customer by ID
GET	/api/customers/{id}/creditscore	ADMIN	Fetch and refresh credit score
POST	/api/loans/apply	USER	Submit loan application
GET	/api/loans/status/{id}	USER	Check loan status
POST	/api/loans/approval/submit	ADMIN	Approve or reject loan
POST	/api/loans/payment/disburse	ADMIN	Disburse approved loan
POST	/api/documents/upload *	USER	Upload KYC document (port 8090 direct)
POST	/api/documents/verify	ADMIN	Trigger document verification
POST	/api/notifications/send	USER	Send manual notification

* Document upload calls port 8090 directly. All other endpoints go through the gateway on port 8085.
 
Flow

#	Action	Endpoint	What to show
1	Register user	POST /api/auth/register	201 response, user created
2	Login as user	POST /api/auth/login	JWT token returned — show token in jwt.io decoder
3	Register customer KYC	POST /api/customers/register	Customer created with id — note the id
4	Try protected endpoint without token	GET /api/customers/1	401 Unauthorized — security working
5	Get customer with token	GET /api/customers/1	200 — customer profile returned
6	Apply for loan	POST /api/loans/apply	Loan created with status APPLIED
7	Try approval with user token	POST /api/loans/approval/submit	403 Forbidden — RBAC working
8	Login as admin	POST /api/auth/login (admin)	Admin JWT — show role:ADMIN in decoder
9	Approve loan	POST /api/loans/approval/submit	Status changes to APPROVED, Kafka event fired
10	Check loan status	GET /api/loans/status/1	Show APPROVED status
11	Upload document	POST :8090/api/documents/upload	Document uploaded, id returned
12	Verify document	POST /api/documents/verify	Status VERIFIED, Kafka event fired
13	Disburse loan	POST /api/loans/payment/disburse	Status DISBURSED — complete lifecycle

