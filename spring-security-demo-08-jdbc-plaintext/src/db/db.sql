-- run in public schema


DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  enabled character varying(1) NOT NULL,
  PRIMARY KEY (username)
);

--
-- Inserting data for table users
--

INSERT INTO users 
VALUES 
('john','{noop}test123',1),
('mary','{noop}abc',1),
('susan','{noop}test123',1);


--
-- Table structure for table authorities
--

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
  username character varying(50) NOT NULL ,
  authority character varying(50) NOT NULL ,
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
) ;

--
-- Inserting data for table authorities
--

INSERT INTO authorities 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_ADMIN');

