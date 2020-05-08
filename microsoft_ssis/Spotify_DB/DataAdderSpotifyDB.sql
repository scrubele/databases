USE master
GO
use spotify_db;

IF DB_ID ('spotify_db') is not null

INSERT INTO [basic_user]
    (email, password, age, premium)
VALUES
    ('ivanenko@gmail.com', '1111', 15, 0),
    ('petrenko@gmail.com', '1234', 20, 0),
    ('symonenko@gmail.com', '2345', 30, 0),
	('ivanenko@gmail.com', '1111', 15, 0),
    ('petrenko@gmail.com', '1234', 20, 0),
    ('symonenko@gmail.com', '2345', 30, 0)

go

INSERT INTO [genre]
    (genre)
VALUES
    ('rock'),
    ('jazz'),
    ('pop'),
    ('metal')
go

INSERT INTO [basic_user_genre]
    (basic_user_id, genre_id)
VALUES
    (1, 1),
    (2, 3),
    (4, 1),
    (6, 3)
go

INSERT INTO [song]
    ([name], [length], rating)
VALUES
    ('Sway', 123, 12),
    ('My way', 150, 14),
    ('Sway', 123, 12),
    ('My way', 150, 14)
go

INSERT INTO [session]
    ([day], [month], [year], duration, device)
VALUES
    (22, 4, 2020, 20, 'iPhone 7'),
    (21, 4, 2020, 40, 'xiaomi mi 7'),
    (23, 5, 2020, 120, 'iPhone 5'),
    (24, 7, 2020, 30, 'iPhone 3'),
	(23, 5, 2020, 120, 'iPhone 5'),
    (24, 7, 2020, 30, 'iPhone 3')
go

INSERT INTO [feedback]
    ([is_liked], [is_disliked], [is_skipped])
VALUES
	(1,0,0),
	(0,1,0)
go

INSERT INTO [basic_user_song]
    (basic_user_id, song_id, session_id, feedback_id)
VALUES
    (1, 1, 1, 1),
    (2, 2, 2, 2),
	(4, 1, 3, 1),
    (6, 2, 4, 1)
go

INSERT INTO [artist]
    (genre_id)
VALUES
    (1),
	(2),
	(3),
    (3)
go

INSERT INTO [album]
    ([name], genre_id,rating,listencount)
VALUES
    ('Nevermind', 1, 200, 1230),
	('Рун', 1, 200, 1230),
	    ('Nevermind', 1, 200, 1230),
	('Рун', 1, 200, 1230)
go

INSERT INTO [album_song_artists]
    (artist_id, album_id, song_id)
VALUES
    (1, 1, 1),
	(2, 2, 2),
	(3, 2, 3),
	(4, 3, 1)
go
