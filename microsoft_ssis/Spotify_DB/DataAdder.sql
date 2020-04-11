USE master
GO
use spotify_db;

IF DB_ID ('spotify_db') is not null

INSERT INTO [basic_user]
    (email, password, premium)
VALUES
    ('ivanenko@gmail.com', '1111', 0),
    ('petrenko@gmail.com', '1234', 0),
    ('symonenko@gmail.com', '2345', 0)

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
    (2, 3)
go

INSERT INTO [song]
    (name, length, rating, listencount)
VALUES
    ('Sway', 123, 12, 4000),
    ('My way', 150, 14, 3000)
go

INSERT INTO [basic_user_song]
    (basic_user_id, song_id)
VALUES
    (1, 1),
    (2, 3)
go

INSERT INTO [artist]
    (artist_id, genre_id)
VALUES
    (3, 1)
go

INSERT INTO [album]
    (name, genre_id,rating,listencount)
VALUES
    ('Nevermind', 1, 200, 1230)
go

INSERT INTO [album_song_artists]
    (artist_id, album_id, song_id)
VALUES
    (3, 1, 1)
go
