USE master
GO
DROP DATABASE  IF EXISTS spotify_star;
GO

CREATE DATABASE spotify_star
ON PRIMARY
( NAME = spotify_star, FILENAME = 'D:\university\databases\microsoft_ssis\star_db\spotify_star.mdf',
SIZE = 30MB, MAXSIZE = UNLIMITED, FILEGROWTH = 5MB )
LOG ON
( NAME = spotify_star_log, FILENAME = 'D:\university\databases\microsoft_ssis\star_db\spotify_star_log.ldf',
SIZE = 8MB, MAXSIZE = UNLIMITED, FILEGROWTH = 10% )

GO

use spotify_star;

IF DB_ID ('spotify_star') is not null
CREATE TABLE [dim_basic_user]
(
  [basic_user_id] INT IDENTITY,
  [age] TINYINT NULL,
  [country] NVARCHAR(50) NULL,
  [premium] TINYINT NULL,
);

go

ALTER TABLE [dim_basic_user]
  ADD CONSTRAINT pk_basic_user PRIMARY KEY NONCLUSTERED ([basic_user_id]);

go

CREATE TABLE [dim_song]
(
  [song_id] INT IDENTITY,
  [name] NVARCHAR(50) NULL,
  [length] INT NULL,
  [rating] INT NULL
);

ALTER TABLE [dim_song]
  ADD CONSTRAINT pk_song PRIMARY KEY NONCLUSTERED ([song_id]);

go

CREATE TABLE [dim_session]
(
  [session_id] INT IDENTITY,
  [day] INT NULL,
  [month] INT NULL,
  [year] INT NULL,
  [duration] int NULL,
  [device] NVARCHAR(50) NULL
);

ALTER TABLE [dim_session]
  ADD CONSTRAINT pk_session PRIMARY KEY NONCLUSTERED ([session_id]);

go

CREATE TABLE [dim_artist]
(
  [artist_id] INT IDENTITY,
  [genre_id] NVARCHAR(50) NULL
);

go

ALTER TABLE [dim_artist]
  ADD CONSTRAINT pk_artist PRIMARY KEY NONCLUSTERED ([artist_id]);

go
CREATE TABLE [fact_popularity]
(
  [basic_user_id] INT,
  [song_id] INT,
  [session_id] INT,
  [artist_id] INT,
  [number_of_likes] INT,
  CONSTRAINT fact_listenings_basic_user_fk FOREIGN KEY ([basic_user_id]) REFERENCES [dim_basic_user] ([basic_user_id]),
  CONSTRAINT fact_listenings_song_fk FOREIGN KEY ([song_id]) REFERENCES [dim_song] ([song_id]),
  CONSTRAINT fact_listenings_session_fk FOREIGN KEY ([session_id]) REFERENCES  [dim_session]([session_id]),
  CONSTRAINT fact_listenings_artist_fk FOREIGN KEY ([artist_id]) REFERENCES [dim_artist] ([artist_id]),
);

go

/*
INSERT INTO [dim_session]
    ([date])
VALUES
    (convert(datetime,'18-06-12 10:34:09 PM',5))
go*/

