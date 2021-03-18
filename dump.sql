;             
CREATE USER IF NOT EXISTS "SA" SALT '013e1b1cc5fbb14b' HASH '7ca382cb3c3f871677e17bc2e9c85461404dcf678fdbdc835bbeb2f51f35d18d' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6375BD2C_A44D_429D_9CB3_502FB2E35C3F" START WITH 3 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F00E81CA_0CE5_4E95_81CB_227A80D34E7D" START WITH 9 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_EBB808DE_4050_41F6_9731_E375B4FCE51F" START WITH 30 BELONGS_TO_TABLE;               
CREATE MEMORY TABLE "PUBLIC"."EMPLOYEE"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_EBB808DE_4050_41F6_9731_E375B4FCE51F" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_EBB808DE_4050_41F6_9731_E375B4FCE51F",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "UPDATED_AT" TIMESTAMP,
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255)
);      
ALTER TABLE "PUBLIC"."EMPLOYEE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");     
-- 29 +/- SELECT COUNT(*) FROM PUBLIC.EMPLOYEE;               
INSERT INTO "PUBLIC"."EMPLOYEE" VALUES
(1, TIMESTAMP '2021-03-19 04:45:42.081', TIMESTAMP '2021-03-19 04:45:42.081', 'Providenci', 'Kassulke'),
(2, TIMESTAMP '2021-03-19 04:45:42.763', TIMESTAMP '2021-03-19 04:45:42.763', 'Alene', 'Gutkowski'),
(3, TIMESTAMP '2021-03-19 04:45:43.293', TIMESTAMP '2021-03-19 04:45:43.293', 'Amari', 'Durgan'),
(4, TIMESTAMP '2021-03-19 04:45:43.737', TIMESTAMP '2021-03-19 04:45:43.737', 'Rosa', 'Mohr'),
(5, TIMESTAMP '2021-03-19 04:45:44.082', TIMESTAMP '2021-03-19 04:45:44.082', 'Aurore', 'Hamill'),
(6, TIMESTAMP '2021-03-19 04:45:45.444', TIMESTAMP '2021-03-19 04:45:45.444', 'Kory', 'Kutch'),
(7, TIMESTAMP '2021-03-19 04:45:46.216', TIMESTAMP '2021-03-19 04:45:46.216', 'Stefanie', 'Hilpert'),
(8, TIMESTAMP '2021-03-19 04:45:46.881', TIMESTAMP '2021-03-19 04:45:46.881', 'Cathrine', 'Brakus'),
(9, TIMESTAMP '2021-03-19 04:45:48.632', TIMESTAMP '2021-03-19 04:45:48.632', 'Quentin', 'Cole'),
(10, TIMESTAMP '2021-03-19 04:45:49.235', TIMESTAMP '2021-03-19 04:45:49.235', 'Elza', 'Kulas'),
(11, TIMESTAMP '2021-03-19 04:45:49.734', TIMESTAMP '2021-03-19 04:45:49.734', 'Claudia', 'Gutkowski'),
(12, TIMESTAMP '2021-03-19 04:45:50.189', TIMESTAMP '2021-03-19 04:45:50.189', 'Jerrell', 'Veum'),
(13, TIMESTAMP '2021-03-19 04:45:50.656', TIMESTAMP '2021-03-19 04:45:50.656', 'Gussie', 'Morissette'),
(14, TIMESTAMP '2021-03-19 04:45:51.087', TIMESTAMP '2021-03-19 04:45:51.087', 'Lennie', 'Reilly'),
(15, TIMESTAMP '2021-03-19 04:45:51.538', TIMESTAMP '2021-03-19 04:45:51.538', 'Michael', 'Ziemann'),
(16, TIMESTAMP '2021-03-19 04:45:51.965', TIMESTAMP '2021-03-19 04:45:51.965', 'Jordane', 'Swaniawski'),
(17, TIMESTAMP '2021-03-19 04:45:52.428', TIMESTAMP '2021-03-19 04:45:52.428', 'Lorine', 'Mante'),
(18, TIMESTAMP '2021-03-19 04:45:52.814', TIMESTAMP '2021-03-19 04:45:52.814', 'Vince', 'Crona'),
(19, TIMESTAMP '2021-03-19 04:45:53.332', TIMESTAMP '2021-03-19 04:45:53.332', 'Sherman', 'Walter'),
(20, TIMESTAMP '2021-03-19 04:45:53.735', TIMESTAMP '2021-03-19 04:45:53.735', 'Mohammed', 'Harris'),
(21, TIMESTAMP '2021-03-19 04:45:54.142', TIMESTAMP '2021-03-19 04:45:54.142', 'Dedrick', 'Davis'),
(22, TIMESTAMP '2021-03-19 04:45:54.562', TIMESTAMP '2021-03-19 04:45:54.562', 'Ramiro', 'Rosenbaum'),
(23, TIMESTAMP '2021-03-19 04:45:54.941', TIMESTAMP '2021-03-19 04:45:54.941', 'Lysanne', 'Rosenbaum'),
(24, TIMESTAMP '2021-03-19 04:45:55.315', TIMESTAMP '2021-03-19 04:45:55.315', 'Ova', 'Huels'),
(25, TIMESTAMP '2021-03-19 04:45:55.797', TIMESTAMP '2021-03-19 04:45:55.797', 'Dexter', 'Bartoletti'),
(26, TIMESTAMP '2021-03-19 04:45:56.112', TIMESTAMP '2021-03-19 04:45:56.112', 'Christelle', 'Bruen'),
(27, TIMESTAMP '2021-03-19 04:45:56.683', TIMESTAMP '2021-03-19 04:45:56.683', 'Jacey', 'Wehner'),
(28, TIMESTAMP '2021-03-19 04:45:57.193', TIMESTAMP '2021-03-19 04:45:57.193', 'Amie', 'Kozey'),
(29, TIMESTAMP '2021-03-19 04:45:57.674', TIMESTAMP '2021-03-19 04:45:57.674', 'Brad', 'Bogisich');           
CREATE MEMORY TABLE "PUBLIC"."SKILL"(
    "ID" BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_F00E81CA_0CE5_4E95_81CB_227A80D34E7D" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_F00E81CA_0CE5_4E95_81CB_227A80D34E7D",
    "CREATED_AT" TIMESTAMP NOT NULL,
    "UPDATED_AT" TIMESTAMP,
    "DESCRIPTION" VARCHAR(255),
    "DURATION" INTEGER NOT NULL,
    "LAST_USED" DATE,
    "EMPLOYEE_ID" BIGINT
);   
ALTER TABLE "PUBLIC"."SKILL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");        
-- 7 +/- SELECT COUNT(*) FROM PUBLIC.SKILL;   
INSERT INTO "PUBLIC"."SKILL" VALUES
(2, TIMESTAMP '2021-03-19 04:46:13.878', TIMESTAMP '2021-03-19 04:46:13.878', 'Functionality', 111, DATE '1992-01-12', 4),
(3, TIMESTAMP '2021-03-19 04:46:14.341', TIMESTAMP '2021-03-19 04:46:14.341', 'Brand', 342, DATE '1992-01-12', 4),
(4, TIMESTAMP '2021-03-19 04:46:14.834', TIMESTAMP '2021-03-19 04:46:14.834', 'Identity', 245, DATE '1992-01-12', 4),
(5, TIMESTAMP '2021-03-19 04:46:15.274', TIMESTAMP '2021-03-19 04:46:15.274', 'Response', 549, DATE '1992-01-12', 4),
(6, TIMESTAMP '2021-03-19 04:46:15.611', TIMESTAMP '2021-03-19 04:46:15.611', 'Security', 243, DATE '1992-01-12', 4),
(7, TIMESTAMP '2021-03-19 04:46:16.021', TIMESTAMP '2021-03-19 04:46:16.021', 'Communications', 170, DATE '1992-01-12', 4),
(8, TIMESTAMP '2021-03-19 04:46:16.38', TIMESTAMP '2021-03-19 04:46:16.38', 'Response', 423, DATE '1992-01-12', 4);    
CREATE MEMORY TABLE "PUBLIC"."USER"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_6375BD2C_A44D_429D_9CB3_502FB2E35C3F" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_6375BD2C_A44D_429D_9CB3_502FB2E35C3F",
    "PASSWORD" VARCHAR(255),
    "ROLE" VARCHAR(255),
    "USERNAME" VARCHAR(255)
);     
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
INSERT INTO "PUBLIC"."USER" VALUES
(1, '$2a$10$C29dLoUWXNMyy3s3wRxIyuUxUe5RvCXe9F2bqbwyZNHrSV82RfPvG', 'ADMIN', 'ADMIN'),
(2, '$2a$10$C29dLoUWXNMyy3s3wRxIyuUxUe5RvCXe9F2bqbwyZNHrSV82RfPvG', 'USER', 'Mike');              
ALTER TABLE "PUBLIC"."SKILL" ADD CONSTRAINT "PUBLIC"."FKEQKX730ICJS5OQ845I9B286FN" FOREIGN KEY("EMPLOYEE_ID") REFERENCES "PUBLIC"."EMPLOYEE"("ID") NOCHECK;   
