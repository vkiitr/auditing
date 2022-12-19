# About audit-log-service

This is a spring boot based audit log service. It uses h2 in memory database. Schema for audit table can be found at `auditing\audit-log-service\src\main\resources\schema.sql`

Working function:
	- Exposes one GET API (/audits) that returns all records as per logged-in user's role. APi has pagination and sorting support.
	- Listen on locally installed rabbitmq and receive audit record message on port 5672 with guest user. all configuration can be modified from `auditing\audit-log-service\src\main\resources\application.properties`
	- Supportd memory user authentication with ADMIN and NONADMIN role. (user and role information can be found below in the file)
	
	
# Technical support instructions

## Packaging the war

- 	Clone the repo
- 	Install maven [Click here to download maven](https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip)

- 	run `mvn clean package` from `auditing\audit-log-service` to make war file.

-	war can be found `auditing\audit-log-service\target with name`  `audit-log-service-0.0.1.war`

## Deployment of war

-	Download Apache Tomcat server 9 and above (Should be compatible with Java 11)
-   copy the war to `{tomcat_dir}/webapps dir` and can rename it to `api.war`
-   Open tomcat's `{tomcat_dir}/conf/server.xml` and add following line inside <Host> tag in that file
	`<Context path="" docBase="api"></Context>`
- 	run `{tomcat_dir}/bin/catalina.bat` or `catalina.sh` start
-   Now service can be accessed via link `http://<host_name>:<port>/api/audits`

## Sample API once we have audit record in DB

```
http://localhost:8080/api/audits?pageSize=5

http://localhost:8080/api/audits?pageSize=5&pageNo=1

http://localhost:8080/api/audits?pageSize=5&pageNo=2

http://localhost:8080/api/audits?pageSize=5&pageNo=1&sortBy=userName

http://localhost:8080/api/audits?pageSize=5&pageNo=1&sortBy=userName&sortDir=asc
```

## User information that can be use to access records

Information of users can be find in the `user.json` file in source code at `auditing\audit-log-service\src\main\resources\static` and more users with their role can be added in json and service restart will be required to make the new user effective.

```
		[
		  {
		    "name": "admin",
		    "password": "password",
		    "role": "ADMIN"
		  },
		  {
		    "name": "vikas",
		    "password": "password",
		    "role": "ADMIN"
		  },
		  {
		    "name": "vk",
		    "password": "password",
		    "role": "NONADMIN"
		  },
		  {
		    "name": "pk",
		    "password": "password",
		    "role": "NONADMIN"
		  },
		  {
		    "name": "sk",
		    "password": "password",
		    "role": "NONADMIN"
		  }
		]
```

## DB access

H2 db instance can be accessed via url `http://<host_name>:<port>/api/h2`
DB name = jdbc:h2:mem:auditdb
user name = h2admin and password blank

## TODO

	## Requirement side implementation is pending for Handling of log rotation with configurable window. (Design thoughts are covered in design doc)
	## API implementation side:
		- HATEOAS and filter support
		- Added Exception handling infra and for testing raised one exception but there are many points where exception can be raised and handled.
		- Added logging at few points, but many more points are pending.


================================================================

# About audit-producer

This project can be used to publish the audit message via following REST API. AuditProducerApplication has main method and can be run as server.
 
```
	HTTP method type: POST 
	URI: http://{{host}}:{{publisher-port}}/audits/publish
	Request body:
	{
	    "userName": "vk",
	    "serviceName": "security-service",
	    "message": "Configuration has been modified successfully",
	    "category": "CONFIGURATION",
	    "operation": "MODIFY",
	    "auditTime": 1671354129,
	    "auditAttributes": [
	        {
	            "name": "interval",
	            "oldValue": "15",
	            "newValue": "10"
	        },
	        {
	            "name": "retention",
	            "oldValue": "1 years",
	            "newValue": "2 years"
	        },
	        {
	            "name": "hostName",
	            "oldValue": "abc.com",
	            "newValue": "xyz.com"
	        }
	    ]
	}
*** userName and service name can be fetch from request context.
```

Disclaimer: The high level structure of this spring boot project has been generated via `https://start.spring.io/`