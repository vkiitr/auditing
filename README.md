# audit-log-service
Spring boot based audit log service

# Getting started


## RabbitMQ commands

C:\Program Files\RabbitMQ Server\rabbitmq_server-3.11.5\sbin
    	> rabbitmq-plugins.bat enable rabbitmq_management
	> rabbitmq-service.bat stop
	> rabbitmq-service.bat start
default port - 15672

## TODO

	- convert it to listener of MQ
	- RBAC handling ADMIN vs NONADMIN
	- Handling of log rotation with configurable window, archive kind of support or soft delete for some time
	- input validation, Error/Exception handling
	- date and time handling
	- pagination and format of request and response
	
    
###Disclaimer: the high level of spring boot project has been generated via https://start.spring.io/