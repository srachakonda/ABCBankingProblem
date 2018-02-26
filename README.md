# ABCBankingProblem
ABC Bank has many branches and customers. Each branch has limited number of teller counters. ABC Bank would like to improve their customer experience at each branch by implementing a token based system where customers will be issued a token and token display board will display current token serving and tokens are queued up at respective teller counters based on several parametes such as Account Type, Service Opted and so on.

Tech Stack:
1. Spring Boot - 1.5.4
2. MySQL - 5.7.21-0ubuntu0.16.04.1
3. Java -1.8

# Swagger Integration for RESTful API's Documentation

http://localhost:8080/swagger-ui.html

# ER Diagram

![dbmodel-1](https://user-images.githubusercontent.com/20350389/36408614-7ffb8852-162c-11e8-9f16-46265dd68fe6.png)

# Class Diagram

![abcbankingproblem2](https://user-images.githubusercontent.com/20350389/36655851-32156a4a-1aeb-11e8-8875-6391fa110c3d.png)
# RESTful API's

**/api**

1. **/issuetoken (Method: POST)**

- To create token for each customer as per his account type(priority/regular). 
- If customer is new user then customer details will be persisted first and generates token.
- If customer is existing user then token will be generated.
- If customer opts for multiple services then once one service gets completed token will be reassigned to another counter to serve next services.

Request Body:

{ 

	"name":"Sampath Imaginea",
    "phoneNumber":9154869675,
    "accountType":"PRIORITY",
    "address":"Banjara Hills",
    "servicesOpted":["WITHDRAW","DEPOSIT"]
} 

**/api/counter**

1. URL: **/counters (Method: GET)**

- Lists down all counters and respective tokens assigned.

2. URL: **/operate/{id} (Method: GET)**

- Used by counter operator to operate services for each customer on token basis based on priority.

3. URL: **/addCounter (Method: POST)**

- To add counter to serve tokens.

Request Body:

{

	"servicesOffered":[ "WITHDRAW"],
	"accountType":"PRIORITY",
	"branch":1,
	"operator":1
}
  
4. URL: **/addOperator (Method: POST)**

- To add operator to operate on token generated.

Request Body:

{

	"role":"MANAGER",
	"branch":1
}

5. URL: **/operators (Method: GET)**

- Lists down available operators to serve customers.

6. URL: **/addBranch (Method: POST)**

- To add bank branch to serve customer tokens.

Request Body:

{

	"name":"Jubilee Hills"
}

7. URL: **/branches (Method: GET)**

- Lists down current available branches to serve customers.

# Assumptions
1. For new customer, customer details will be saved into database and then token will be issued and assigned to counter.
2. For Existing Customer token will be generated and assigned to counter to operate based on services opted and type of account.
3. At any given point of time tokens will be queued up at counters based on counter ranking (no of tokens assigned to counter).
4. Dedicated counters to Regular and Priority Customers.

# Scope of Extension
1. In Current Implementation its using a queue to operate tokens for each counter. This can be enhanced by adding any distributed Queue approach(RabbitMQ). Not extended it to this case as at any given point of time when token is issued it should be assigned to a counter.
2. Change counter operator to operate on both Regular and Priority Tokens by adding priority queue's.
3. For multi counter services once a service is completed can prioritoize his next service than adding it to end of queue for processing next service to enhance customer experience.
4. Remove JPARepository and add CustomDAOImpl to allow DB Change without much code changes.
