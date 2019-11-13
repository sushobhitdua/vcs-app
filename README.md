# VCS Tool for Text Files

##### VCS app is responsible for operations like :

	-Upload new txt file (max 5MB)
	-Rename, delete, update the content of any txt file uploaded to the service
	-View history of changes for the particular file
	-Revert file back to some revision
	
##### Tools and Technologies used
    -Spring boot 2.2.1
    -MongoDB
    -Eclipse IDE
    -Java1.8

#### Steps to Build and Start App
`mainClassName` : com.graebert.vcs.VcsApplication

###### Gradle  Build
```
gradle build
```
###### Run Command
To Run App, you can simply run `VcsApplication` class as java application using Eclipse IDE or
```sh
$ ./gradlew bootRun
```
The tomcat server will start at http://localhost:9090

##### Application Started
```
23:59:08.464 [main] DEBUG com.graebert.vcs.VcsApplication - Running with Spring Boot v2.2.1.RELEASE, Spring v5.2.1.RELEASE
23:59:08.466 [main] INFO  com.graebert.vcs.VcsApplication - No active profile set, falling back to default profiles: default
00:00:43.956 [main] INFO  com.graebert.vcs.VcsApplication - Started VcsApplication in 28.303 seconds (JVM running for 84.96)
```

##### Application Logs
In current app folder it will file with path `logs/vcs-app.log`
```sh
$ tail -f  logs/vcs-app.log
```
### Swagger UI and Resource Endpoints

	http://localhost:9090/swagger-ui.html
	http://localhost:9090/v2/api-docs

