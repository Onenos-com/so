use requestdb;

ALTER TABLE infra_active_requests 
ADD COLUMN REQUEST_URL varchar(500) 
AFTER OPERATIONAL_ENV_NAME;

ALTER TABLE archived_infra_requests 
ADD COLUMN REQUEST_URL varchar(500) 
AFTER OPERATIONAL_ENV_NAME;