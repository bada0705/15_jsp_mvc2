
CREATE DATABASE MVC2_LOGIN_EX;
USE MVC2_LOGIN_EX;
	
CREATE TABLE MEMBER(
    ID    VARCHAR(20),
    PW    VARCHAR(20),
    NAME  VARCHAR(20),
    TEL   VARCHAR(20),
    EMAIL VARCHAR(30),
    FIELD VARCHAR(20),        -- 지원분야
    SKILL VARCHAR(70),        -- 기술능력
    MAJOR VARCHAR(20)         -- 전공분야
);

SELECT * FROM MEMBER;