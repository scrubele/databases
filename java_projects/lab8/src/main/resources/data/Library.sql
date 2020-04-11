DROP DATABASE  IF EXISTS Artists;
create database artists;
use artists;
CREATE TABLE Organizations
(
  id int NOT NULL AUTO_INCREMENT ,
  name nvarchar(50) NULL,
  address nvarchar(50) NULL,
  phone_number int NULL,
  Primary key (id)
);


CREATE TABLE artists
(
  id int NOT NULL AUTO_INCREMENT ,
  surname nvarchar(50) NULL,
  name nvarchar(50) NULL,
  position nvarchar(50) NULL,
  id_organization int NULL,
  Primary key (id),
  CONSTRAINT FK_projects_organizations FOREIGN KEY (id_organization)
  REFERENCES organizations (id)
);


CREATE TABLE projects
(
  id int NOT NULL AUTO_INCREMENT ,
  name nvarchar(50) NOT NULL,
  date_start date NULL,
  date_end date NULL,
  Primary key (id)

);


CREATE TABLE artist_projects
(
  id int NOT NULL AUTO_INCREMENT ,
  artist_id int NOT NULL,
  projects_id int NOT NULL,
  CONSTRAINT FK_artists FOREIGN KEY (artist_id)
  REFERENCES artists (id),
  CONSTRAINT FK_projects FOREIGN KEY (projects_id)
  REFERENCES projects (id),
  Primary key (id)
);
