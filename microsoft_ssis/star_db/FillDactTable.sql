Insert into [spotify_star].[dbo].[fact_popularity](
	  [basic_user_id]
      ,[song_id]
      ,[artist_id]
      ,[session_id]
	  ,[number_of_likes]
      ,[avg_time_of_listenings])
select *
from
(
 SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
	   , sum([feedback].[is_liked]) as number_of_likes
  FROM [spotify_db].[dbo].[basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id]
  Inner join [spotify_db].[dbo].[feedback] on  [feedback].[feedback_id] = [basic_user_song].[feedback_id]
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id]
  Group by [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]) as user_song_artist_all, 
  (select avg([duration]) as [avg_time_of_listenings]
  from [spotify_db].[dbo].[session] inner join(
    SELECT [basic_user_song].[basic_user_id]
       , [basic_user_song].[song_id]
	   , [album_song_artists].[artist_id]
	   , [basic_user_song].[session_id]
  FROM [spotify_db].[dbo].[basic_user_song] 
  Inner Join [spotify_db].[dbo].[basic_user] on [basic_user].[basic_user_id] = [basic_user_song].[basic_user_id] 
  Inner join [spotify_db].[dbo].[album_song_artists] on [album_song_artists].[song_id] = [basic_user_song].[song_id]) as user_song_artist
  on [session].[session_id] = [user_song_artist].[session_id]) as duration