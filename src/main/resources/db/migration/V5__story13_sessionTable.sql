CREATE TABLE SESSION
(
    SESSION_ID    UUID PRIMARY KEY,
    FK_TOPIC_ID   UUID        NOT NULL,
    SESSION_DATE  DATE        NOT NULL,
    SESSION_TIME  TIME        NOT NULL,
    LOCATION      VARCHAR(20) NOT NULL,
    REMARKS       TEXT,
    STATUS        VARCHAR(20) NOT NULL,
    FK_COACH_ID   UUID        NOT NULL,
    FK_COACHEE_ID UUID        NOT NULL,

    FOREIGN KEY (FK_TOPIC_ID) REFERENCES TOPIC(TOPIC_ID),
    FOREIGN KEY (FK_COACH_ID) REFERENCES USERS(ID),
    FOREIGN KEY (FK_COACHEE_ID) REFERENCES USERS(ID)
)