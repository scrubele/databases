 USE master

go

ALTER DATABASE spotifydb

SET single_user WITH

ROLLBACK immediate;

go

DROP DATABASE spotifydb

go

-- створюється нова база даних
CREATE DATABASE spotifydb

go

USE spotifydb;

IF Db_id ('SpotifyDB') IS NOT NULL
  CREATE TABLE [user]
    (
       [user_id]  INT IDENTITY,
       [email]    NVARCHAR(50) NULL,
       [password] NVARCHAR(50) NULL,
       [premium]  TINYINT NULL
    );

go

ALTER TABLE [user]
  ADD CONSTRAINT pk_users PRIMARY KEY NONCLUSTERED ([user_id]);

go

CREATE TABLE [genre]
  (
     [genre_id] INT IDENTITY,
     [genre]    NVARCHAR(50) NULL
  );

go

ALTER TABLE [genre]
  ADD CONSTRAINT pk_genres PRIMARY KEY NONCLUSTERED ([genre_id]);

go

CREATE TABLE [user_genre]
  (
     [user_id]  INT,
     [genre_id] INT,
     CONSTRAINT user_genre_pk PRIMARY KEY ([user_id], [genre_id]),
     CONSTRAINT fk_user_genre_user_id FOREIGN KEY ([user_id]) REFERENCES [user]
     ([user_id]),
     CONSTRAINT fk_user_genre_genre_id FOREIGN KEY ([genre_id]) REFERENCES
     [genre] ([genre_id])
  );

CREATE TABLE [song]
  (
     [song_id]     INT IDENTITY,
     [name]        NVARCHAR(50) NULL,
     [length]      INT NULL,
     [rating]      INT NULL,
     [listencount] INT NULL,
  );

ALTER TABLE [song]
  ADD CONSTRAINT pk_songs PRIMARY KEY NONCLUSTERED ([song_id]);

go

CREATE TABLE user_song
  (
     [user_id] INT,
     [song_id] INT,
     CONSTRAINT user_song_pk PRIMARY KEY ([user_id], [song_id]),
     CONSTRAINT fk_user_song_user_id FOREIGN KEY ([user_id]) REFERENCES [user] (
     [user_id]),
     CONSTRAINT fk_user_song_song_id FOREIGN KEY ([song_id]) REFERENCES song (
     [song_id])
  );

CREATE TABLE [artist]
  (
     [artist_id] INT UNIQUE NOT NULL,
     [genre_id]  INT NULL,
  );

ALTER TABLE [artist]
  ADD CONSTRAINT pk_artists PRIMARY KEY NONCLUSTERED ([artist_id]);

go

ALTER TABLE [artist]
  ADD CONSTRAINT fk_artist_users FOREIGN KEY([artist_id]) REFERENCES user(
  [user_id]);

go

CREATE TABLE [album]
  (
     [album_id]  INT IDENTITY,
     [name]      NVARCHAR(50) NULL,
     [genre_id]  INT NULL,
     [rating]    INT NULL,
     listencount INT NULL,
     CONSTRAINT fk_song_id FOREIGN KEY ([genre_id]) REFERENCES [genre] (
     [genre_id])
  );

ALTER TABLE [album]
  ADD CONSTRAINT pk_albums PRIMARY KEY NONCLUSTERED ([album_id]);

go

CREATE TABLE album_song_artists
  (
     [artist_id] INT,
     [album_id]  INT,
     [song_id]   INT,
     CONSTRAINT album_song_artist_pk PRIMARY KEY ([album_id], [song_id],
     [artist_id]),
     CONSTRAINT fk_album_song_artist_album_id FOREIGN KEY ([album_id])
     REFERENCES album ([album_id]),
     CONSTRAINT fk_album_song_artist_song_id FOREIGN KEY ([song_id]) REFERENCES
     [song] ([song_id]),
     CONSTRAINT fk_album_song_artist_artist_id FOREIGN KEY ([artist_id])
     REFERENCES [artist] ([artist_id]),
  );

CREATE TABLE [advantage]
  (
     [advantage_id] INT IDENTITY,
     [name]         NVARCHAR(50) NULL,
     [length]       INT NULL,
     [song_id]      INT NULL,
     [pointreward]  INT NULL,
     CONSTRAINT fk_advantage_song_id FOREIGN KEY ([song_id]) REFERENCES songs (
     [song_id])
  );  