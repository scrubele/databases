USE master
GO
DROP DATABASE  IF EXISTS spotify_db;
GO

CREATE DATABASE spotify_db
ON PRIMARY
( NAME = University, FILENAME = 'D:\university\databases\microsoft_ssis\Spotify_DB\spotify_db.mdf',
SIZE = 30MB, MAXSIZE = UNLIMITED, FILEGROWTH = 5MB )
LOG ON
( NAME = University_log, FILENAME = 'D:\university\databases\microsoft_ssis\Spotify_DB\spotify_db_log.ldf',
SIZE = 8MB, MAXSIZE = UNLIMITED, FILEGROWTH = 10% )

GO

use spotify_db;

IF DB_ID ('spotify_db') is not null
CREATE TABLE [basic_user]
(
  [basic_user_id] INT IDENTITY,
  [email] NVARCHAR(50) NULL,
  [password] NVARCHAR(50) NULL,
  [age] TINYINT NULL,
  [premium] TINYINT NULL,
  [country] NVARCHAR(50) NULL,

);

go

ALTER TABLE [basic_user]
  ADD CONSTRAINT pk_basic_users PRIMARY KEY NONCLUSTERED ([basic_user_id]);

go

CREATE TABLE [genre]
(
  [genre_id] INT IDENTITY,
  [genre] NVARCHAR(50) NULL
);

go

ALTER TABLE [genre]
  ADD CONSTRAINT pk_genres PRIMARY KEY NONCLUSTERED ([genre_id]);

go

CREATE TABLE [basic_user_genre]
(
  [basic_user_id] INT,
  [genre_id] INT,
  CONSTRAINT basic_user_genre_pk PRIMARY KEY ([basic_user_id], [genre_id]),
  CONSTRAINT fk_basic_user_genre_basic_user_id FOREIGN KEY ([basic_user_id]) REFERENCES [basic_user]
     ([basic_user_id]),
  CONSTRAINT fk_basic_user_genre_genre_id FOREIGN KEY ([genre_id]) REFERENCES
     [genre] ([genre_id])
);

CREATE TABLE [song]
(
  [song_id] INT IDENTITY,
  [name] NVARCHAR(50) NULL,
  [length] INT NULL,
  [rating] INT NULL,
);

ALTER TABLE [song]
  ADD CONSTRAINT pk_songs PRIMARY KEY NONCLUSTERED ([song_id]);

go

CREATE TABLE [session]
(
  [session_id] INT IDENTITY,
  [day] INT NULL,
  [month] INT NULL,
  [year] INT NULL,
  [duration] int NULL,
  [device] NVARCHAR(50) NULL,
);

ALTER TABLE [session]
  ADD CONSTRAINT pk_session PRIMARY KEY NONCLUSTERED ([session_id]);

  go

CREATE TABLE [feedback]
(
  [feedback_id] INT IDENTITY,
  [is_liked] INT,
  [is_disliked] INT,
  [is_skipped] INT,
);

ALTER TABLE [feedback]
  ADD CONSTRAINT pk_feedback PRIMARY KEY NONCLUSTERED ([feedback_id]);
  go

CREATE TABLE basic_user_song
(
  [basic_user_id] INT,
  [song_id] INT,
  [session_id] INT,
  [feedback_id] INT,
  CONSTRAINT basic_user_song_pk PRIMARY KEY ([basic_user_id], [song_id], [session_id]),
  CONSTRAINT fk_basic_user_song_basic_user_id FOREIGN KEY ([basic_user_id]) REFERENCES [basic_user] (
     [basic_user_id]),
  CONSTRAINT fk_basic_user_song_song_id FOREIGN KEY ([song_id]) REFERENCES song (
     [song_id]),
  CONSTRAINT fk_basic_user_song_session_id FOREIGN KEY ([session_id]) REFERENCES [session] (
     [session_id]),
  CONSTRAINT fk_basic_user_song_feedback_id FOREIGN KEY ([feedback_id]) REFERENCES [feedback] (
     [feedback_id])
);

CREATE TABLE [artist]
(
  [artist_id] INT IDENTITY,
  [genre_id] INT NULL,
);

ALTER TABLE [artist]
  ADD CONSTRAINT pk_artists PRIMARY KEY NONCLUSTERED ([artist_id]);
go

ALTER TABLE [artist]
  ADD CONSTRAINT fk_artist_basic_users FOREIGN KEY([artist_id]) REFERENCES [basic_user](
  [basic_user_id]);

go

CREATE TABLE [album]
(
  [album_id] INT IDENTITY,
  [name] NVARCHAR(50) NULL,
  [genre_id] INT NULL,
  [rating] INT NULL,
  listencount INT NULL,
  CONSTRAINT fk_genre_id FOREIGN KEY ([genre_id]) REFERENCES [genre] (
     [genre_id])
);

ALTER TABLE [album]
  ADD CONSTRAINT pk_albums PRIMARY KEY NONCLUSTERED ([album_id]);

go

CREATE TABLE album_song_artists
(
  [artist_id] INT,
  [album_id] INT,
  [song_id] INT,
  CONSTRAINT album_song_artist_pk PRIMARY KEY ([album_id], [song_id],
     [artist_id]),
  CONSTRAINT fk_album_song_artist_album_id FOREIGN KEY ([album_id])
     REFERENCES [album] ([album_id]),
  CONSTRAINT fk_album_song_artist_song_id FOREIGN KEY ([song_id]) REFERENCES
     [song] ([song_id]),
  CONSTRAINT fk_album_song_artist_artist_id FOREIGN KEY ([artist_id])
     REFERENCES [artist] ([artist_id]),
);

CREATE TABLE [advantage]
(
  [advantage_id] INT IDENTITY,
  [name] NVARCHAR(50) NULL,
  [length] INT NULL,
  [song_id] INT NULL,
  [pointreward] INT NULL,
  CONSTRAINT fk_advantage_song_id FOREIGN KEY ([song_id]) REFERENCES [song] (
     [song_id])
);