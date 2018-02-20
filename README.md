# ABCBankingProblem
ABC Bank has many branches and customers. Each branch has limited number of teller counters. ABC Bank would like to improve their customer experience at each branch by implementing a token based system where customers will be issued a token and token display board will display current token serving and tokens are queued up at respective teller counters based on several parametes such as Account Type, Service Opted and so on.

Tech Stack:
1. Spring Boot - 1.5.4
2. MySQL - 5.7.21-0ubuntu0.16.04.1
3. Java -1.8

# Swagger Integration for RESTFul API's Documentation

http://localhost:8080/swagger-ui.html

# ER Diagram

![dbmodel-1](https://user-images.githubusercontent.com/20350389/36408614-7ffb8852-162c-11e8-9f16-46265dd68fe6.png)

# Class Diagram

# RESTFul API's
 will update soon making some minor changes to API's

# Assumptions
1. For new customer, customer details will be saved into database and then token will be issued and assigned to counter.
2. For Existing Customer token will be generated and assigned to counter to operate based on services opted and type of account.
3. At any given point of time tokens will be queued up at counters based on counter ranking (no of tokens assigned to counter).
4. Dedicated counters to Regular and Priority Customers.

# Scope of Extension
1. In Current Implementation its using a queue to operate tokens for each counter. This can be enhanced by adding any distributed Queue approach(RabbitMQ). Not extended it to this case as at any given point of time when token is issued it should be assigned to a counter.
2. Change counter operator to operate on both Regular and Priority Tokens by adding priority queue's.
3. For multi counter services once a service is completed can prioritoize his next service than adding it to end of queue for processing next service to enhance customer experience.
