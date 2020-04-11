USE master
go
DECLARE @DBNAME VARCHAR(max)
DECLARE @DataPath AS NVARCHAR(max)
DECLARE @sql VARCHAR(max)
SET
  @DBNAME = N'spotify_db'
SET
  @DataPath = N'D:\university\databases\microsoft_ssis\Spotify_DB\'

SELECT @sql = ' DROP DATABASE IF EXISTS '
              + Quotename(@DBNAME)

EXEC (@sql)

SELECT @sql = ' CREATE DATABASE ' + Quotename(@DBNAME)
              + ' ON PRIMARY (
					NAME = ''' + @DBNAME
								+ ' _DB '',
					FILENAME = ''' + @DataPath + @DBNAME
								+'.mdf '',
					SIZE = 3136 KB,
					MAXSIZE = UNLIMITED,
					FILEGROWTH = 1024 KB
					)
				 LOG ON (
					NAME = '''
						+ @DBNAME + ' _Log '',
					FILENAME = '''
						+ @DataPath + @DBNAME
						+
				' _log.ldf '',
					SIZE = 832KB,
					MAXSIZE = 2048 GB,
					FILEGROWTH = 10 %
					) '

EXEC (@sql)

SELECT @sql = ' USE ' + Quotename(@DBNAME)

EXEC (@sql)
go