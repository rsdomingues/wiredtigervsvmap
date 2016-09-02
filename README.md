# Wired Tiger vs MMAP
PoC to compare Wired Tiger and MMAP Storage Engines in mongodb

To run the dependencies (mongodb), use:
`cd docker-images`
`docker-compose up`

To Run the application use: 
`cd app`
`mvn clean spring-boot:run`

To Run the load testing use:
`cd app`
`mvn clean gatling:execute`

At the end Gatling will generate an report to you.