# getting started audit-log-service
Spring boot based audit log service

## prerequisites


http://localhost:8085/audits?pageSize=5

http://localhost:8085/audits?pageSize=5&pageNo=1

http://localhost:8085/audits?pageSize=5&pageNo=2

http://localhost:8085/audits?pageSize=5&pageNo=1&sortBy=userName

http://localhost:8085/audits?pageSize=5&pageNo=1&sortBy=userName&sortDir=asc


## rabbitmq commands

C:\Program Files\RabbitMQ Server\rabbitmq_server-3.11.5\sbin
    	> rabbitmq-plugins.bat enable rabbitmq_management
	> rabbitmq-service.bat stop
	> rabbitmq-service.bat start
default port - 15672

## TODO

	- Handling of log rotation with configurable window, archive kind of support or soft delete for some time
	- Pagination and format of request and response and filter support
	- Added Exception handling infra and for testing raised one exception but there are many points where exception can be raised and handled
	- Added logging at few points but doesn't added proper infra with proper logging level
	- Generated war via eclipse but packaging script can be written for generating war 
	- Design doc
	

Disclaimer: The high level structure of this spring boot project has been generated via `https://start.spring.io/`