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