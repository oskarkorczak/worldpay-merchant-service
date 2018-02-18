Running the application:
------------------------
Run sbt in shell using below command: 

`sbt`

Run application server locally, using below command, while being in sbt shell: 

`run`

Application can be accessed at `http://localhost:9000/` address.

Assumptions:
-----------
1. Bootstrap lib used to achieve neat visual effects
2. Form validation added were necessary 
3. Notions of hexagonal architecture (aka ports and adapters) were used (cf. core/infra). 
 Interfaces and core domain objects (eg. Offer) are located in `core` package.
 Everything else using any external libs/infrastructure etc is located in `infra` package. 
4. Persistence is done using `cache`, which is a mutable map in this case for simplicity reasons.
5. Tests are written with v. high coverage for `core` domain concepts. 
There should be more tests written around `controllers`, which was not done due to time limitations.
6. There are two notions of `offer` concept.
Basic one is a core domain concept and therefore is located in `core` package
Another one (`OfferModel`) is the transfer object between form and controller and it is located in `infra` package.
7. CSRF was switched off for POST requests for simplicity and time related reasons.  
8. Dependency injection implemented via MacWire
9. Cancel funtion should have additional approval before taking action for user safety
10. Search is done only for offer description
11. When offer auto-expires then browser refresh should take place in order to visually see it on the screen

Task:
-----
Background

Per Wikipedia, "an offer is a proposal to sell a specific product or service under specific conditions". As a merchant I offer goods for sale. I want to create an offer so that I can share it with my customers.
All my offers have shopper friendly descriptions. I price all my offers up front in a defined currency.
An offer is time-bounded, with the length of time an offer is valid for defined as part of the offer, and should expire automatically. Offers may also be explicitly cancelled before they expire.

Assignment

You are required to create a simple RESTful software service that will
allow a merchant to create a new simple offer. Offers, once created, may be queried. After the period of time defined on the offer it should expire and further requests to query the offer should reflect that somehow. Before an offer has expired users may cancel it.


Guidelines
- The solution should be written in Java or Scala
- The merchant should be able to interact with the service over HTTP
- No restrictions on external libraries
- You can ignore authentication and authorization concerns
- As a simplification offers may be persisted to a file, embedded database or held in memory
- We are looking for a simple solution representative of an enterprise deliverable
- Use TDD
- Submit as a git repository (link to GitHub, BitBucket, etc)
- Please pay attention to OO design; clean code, adherence to SOLID principles
- Feel free to make any assumptions and document in a README markdown file, or otherwise, with the submission