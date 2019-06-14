# Pigeon

Pigeon, multi-module design e-commerce project. I purpose of development this project, deep-dive spring framework, queue,
cache mechanism and connect to each different infrastructure services. Planned tech stack, Java 8, Spring Boot and
Spring Framework Modules, MySQL, Cassandra, Redis, Guava, RabbitMQ and Consul. If needed high performance any services,
use Go and go-gin framework.

This project running on Apache Tomcat because every services deploying WAR and running on self path. So just a one tomcat
server handle all project request. If need a update any service, just uploaded relational war, other service run all the time.


### Modules

Project default and exact embedded modules core (code name, Fusion) and common. Why core and common embedded default.
Core modules, included base util, config, entity, repo and service. Common sub multi-module project. Needed any configured
different domain. In this way, not duplicated code and basic maintenance.

- Account
    - User
    - Customer
- Campaign
- Catalog
    - Product
    - Category
    - Tag
- Checkout
- CMS
- Event Service
- Invoice
- Order
- Payment
- Profiling
- Shipping
- View(Web)
- Backend Process(Workers)


### If you want to use this

Really, if you want to use this, fork project and develop your according to wishes.

Enjoy