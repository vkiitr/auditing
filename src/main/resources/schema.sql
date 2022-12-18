DROP TABLE IF EXISTS USERS;
  
CREATE TABLE USERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  role VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS AUDIT_RECORD;
CREATE TABLE AUDIT_RECORD
(
   RecordId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   UserName varchar (512) NOT NULL,
   ServiceName varchar (512) NOT NULL,
   Message varchar (1024) NOT NULL,
   Category int NOT NULL DEFAULT 0,
   Operation int NOT NULL DEFAULT 0,
   Attributes varchar (2048) NOT NULL DEFAULT '',
   AuditTimestamp bigint NOT NULL DEFAULT 0,
   CreatedDateTime bigint NOT NULL DEFAULT 0,
   LastUpdatedDateTime bigint NOT NULL DEFAULT 0
);
create index Index_category on AUDIT_RECORD (Category);
create index Index_username on AUDIT_RECORD (UserName);
create index Index_servicename on AUDIT_RECORD (ServiceName);
create index Index_audittimestamp on AUDIT_RECORD (AuditTimestamp);