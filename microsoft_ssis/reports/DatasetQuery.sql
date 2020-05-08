SELECT        fact_popularity.basic_user_id, fact_popularity.song_id, fact_popularity.session_id, fact_popularity.artist_id, fact_popularity.number_of_likes, dim_song.name, dim_song.length, dim_song.rating, dim_basic_user.age, 
                         dim_basic_user.premium, dim_session.day, dim_session.month, dim_session.year, dim_basic_user.country
FROM            fact_popularity INNER JOIN
                         dim_artist ON fact_popularity.artist_id = dim_artist.artist_id INNER JOIN
                         dim_basic_user ON fact_popularity.basic_user_id = dim_basic_user.basic_user_id INNER JOIN
                         dim_session ON fact_popularity.session_id = dim_session.session_id INNER JOIN
                         dim_song ON fact_popularity.song_id = dim_song.song_id INNER JOIN
                         fact_popularity AS fact_popularity_1 ON dim_artist.artist_id = fact_popularity_1.artist_id AND dim_basic_user.basic_user_id = fact_popularity_1.basic_user_id AND dim_session.session_id = fact_popularity_1.session_id AND 
                         dim_song.song_id = fact_popularity_1.song_id