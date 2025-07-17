# EASY-BANK

### Tools & Software Required: -
1. OpenJDK 17 0r above
2. MySQL8 or above
3. IntellJ
4. Docker
5. Maven
6. RabbitMq (Message Queue)

## Accounts - Service: -
***
Accounts Service Which is used to manage the accounts in **Easy-Bank**

## Loans - Service: -
***
Loans Service Which is used to manage the loans for accounts present **Easy-Bank**


### Spring Cloud Central Configuration: -
***
- Before doing spring cloud central configuration we have to set up & run RabbitMq locally.
- To run rabbitMq locally we need to execute the below docker command.
  **docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.1-management**
- To check whether RabbitMq is running or not after setting up locally.
- [Check RabbitMq Running or not](http://localhost:15672/)
- Once rabbitMq is running locally we have to add below spring cloud bus amqp dependency & dependency management in all the microservices including Config Service
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

<dependencyManagement>
  <dependencies>
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-dependencies</artifactId>
          <version>${spring-cloud.version}</version>
          <type>pom</type>
          <scope>import</scope>
      </dependency>
  </dependencies>
</dependencyManagement>
```
- Once add the above dependency in all microservice we have to add the related RabbitMq Properties in all microservices including config service.
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```
- Now properties that are need to add in only microservices (in this case **accounts** && **loans**).
```properties
spring.application.name=accounts/loans #Here we have to give application name 
spring.config.import="optional:configserver:http://localhost:9011/" #in place of configserver you have to your service name
spring.profiles.active=dev #Give profile Based on Env that you want to run it.
```
- Now properties that are need to add in config service
```properties
spring.profiles.active=git
spring.cloud.config.server.git.uri=https://github.com/PavanKalyanMajji/env-config-files.git
spring.cloud.config.server.git.defaultLabel=main #branch name
spring.cloud.config.server.git.timeout=5
spring.cloud.config.server.git.cloneOnStart=true #it will clone the changes while starting config server
spring.cloud.config.server.git.forcePull=true #it will pull the changes on forcefully
```
- Make sure **spring.application.name** of microservices should match with git filename.
Example
  ```spring.application.name = accounts```
  **accounts-dev.properties** or **accounts-dev.yaml**
- You can see above example of naming of file applicationName-evn.properties
- Enable annotation **@EnableConfigServer** in config service.
- At finally we have to hit busRefresh at-least from one microservice. so it will reflect the all other microservices.
[To Refresh Cloud Bus](http://localhost:8012/bus-refresh).

### Docker & Docker Compose: -
***
Before that we have to install the Docker Desktop locally and check the version by using **docker -v** command in command prompt.

1. #### Docker: -
- we can create the docker image in 3 ways
- 1. By using Docker file.
- 2. By using Build Packages.
- 3. By using Google Jib.

**By using Docker file** we have to create the Dockerfile in each individual microservice.
**NOTE: -** Make sure file naming convention file name should be **Dockerfile** no extension.
Example: -
```dockerfile
FROM openjdk17:latest

#Copy the jar from target folder to current namesapce
COPY /target/<jarName>.jar /<jarName>.jar

#We have to specify the command to run the jar in Entry point
ENTRYPOINT ["java", "-jar", "<jarName>.jar"]
```
After creating Dockerfile we have to build the image then run it in container. use below command to create image by using below command
```cmd
#Below command is used to build an image in Docker, at last . indicates that location of docker file if docker file is present in current directory give . or else path of docker file
docker build -t <ImageName>:<TagName> .

#bBelow command is to check image is created or not
docker images

#To run an image -d indicates detach mode -p indicates container port expose on 8081
docker run -d -p 8081:8080 <ImageName>:<TagName>

#To push an image to Docker Hub
docker image push docker.io/<UserName>/<Imagename>:<Tag>
```
**By using Build Packages** it was simple compare by using docker file creation. we have to add the maven build plugin along with configuration tag show in below.
**NOTE: -** No need Dockerfile by using Build Packages.
```xml
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
		<configuration>
			<image>
				<name>pavan/${project.artifactId}:v6</name>
			</image>
		</configuration>
</plugin>
```
Then we have to execute the below commands to create an image.
```cmd
#Below mvn command is used to clean and build the jar 
mvn clean pacakage install

#Below mvn command used to create an image in docker
mvn spring-boot:build-image 
```
