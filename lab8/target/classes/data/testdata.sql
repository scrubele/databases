


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
