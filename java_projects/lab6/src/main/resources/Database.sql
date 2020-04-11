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
  REFERENCES organization (id)
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



INSERT INTO organizations(name ,address,phone_number) VALUES
                                                             ( 'Sniezka', 'Lvivska street', 0967760255),
                                                             ( 'Try Slony', 'Halytska street', 0967760255),
                                                             ( 'Mykolka', 'Bandery street', 0967760255);

INSERT INTO artists(surname, name, position, id_organization) VALUES
                                                                     ( 'Ivanov', 'Ivan', 'director', 1),
                                                                     ( 'Ivanov', 'Bohdan', 'director', 2),
                                                                     ( 'Smirnov', 'Ivan', 'director', 1);

INSERT INTO projects(name, date_start, date_end) VALUES
                                                        ( 'AnyArt', '12.05.18', '17.05.18');

INSERT INTO artist_projects(artist_id, projects_id) VALUES
                                                          ( 1,1),
                                                          (2,1)
