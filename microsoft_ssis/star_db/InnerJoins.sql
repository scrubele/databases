USE master
GO
use spotify_db;

IF DB_ID ('spotify_db') is not null

SELECT [basic_user_id]
	  , AVG([age]) as [avg_age]
  FROM [spotify_db].[dbo].[basic_user]
  GROUP BY [basic_user_id]

  SELECT [basic_user_id]
      ,[age]
      ,[premium]
  FROM [spotify_db].[dbo].[basic_user]

GO
SELECT [song_id]
      ,[name]
      ,[length]
      ,[rating]
  FROM [song]

Go  

SELECT [basic_user_id]
      ,[song_id]
  FROM [spotify_db].[dbo].[basic_user_song]

Go

SELECT [basic_user_song].[basic_user_id]
      ,[basic_user_song].[song_id]
  FROM [basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id] ;


  SELECT [artist_id]
      ,[album_id]
      ,[song_id]
  FROM [spotify_db].[dbo].[album_song_artists]

  go

  SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
  FROM [basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id] 
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id] 
  go

  select avg([duration]) as [avg_time_of_listenings]
  from [session] inner join(
    SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
  FROM [basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id] 
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id]) as user_song_artist
  on [session].[session_id] = [user_song_artist].[session_id]


select *
from
(
 SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
	   , sum([feedback].[is_liked]) as number_of_likes
  FROM [basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id]
  Inner join [spotify_db].[dbo].[feedback] on [basic_user_song].feedback_id = [feedback].feedback_id
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id]
  Group by [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]) as user_song_artist_all, 
  (select avg([duration]) as [avg_time_of_listenings]
  from [session] inner join(
    SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
  FROM [basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id] 
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id]) as user_song_artist
  on [session].[session_id] = [user_song_artist].[session_id]) as duration