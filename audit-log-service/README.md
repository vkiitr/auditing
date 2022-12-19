# getting started audit-log-service
Spring boot based audit log service

## prerequisites



## rabbitmq commands

C:\Program Files\RabbitMQ Server\rabbitmq_server-3.11.5\sbin
    	> rabbitmq-plugins.bat enable rabbitmq_management
	> rabbitmq-service.bat stop
	> rabbitmq-service.bat start
default port - 15672

## TODO

	- Handling of log rotation with configurable window, archive kind of support or soft delete for some time
	- pagination and format of request and response
	- logging with proper logging level
	- packaging script
	- 
	
    
Disclaimer: The high level structure of this spring boot project has been generated via `https://start.spring.io/`