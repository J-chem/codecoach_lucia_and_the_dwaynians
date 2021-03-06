CREATE TABLE COACH_INFO (
    COACH_INFO_ID UUID PRIMARY KEY,
    INTRODUCTION  TEXT,
    AVAILABILITY  TEXT
);

ALTER TABLE USERS
    ADD COACH_INFO_FK UUID;


ALTER TABLE USERS
    ADD CONSTRAINT COACH_INFO_FK
    FOREIGN KEY (COACH_INFO_FK) REFERENCES COACH_INFO(COACH_INFO_ID);