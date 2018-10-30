USE master
GO
-- якщо база даних з таким іменем вже існує, тоді вона знищується

DROP DATABASE  IF EXISTS Devices;
GO

-- створюється нова база даних
CREATE DATABASE Devices
ON PRIMARY
( NAME = University, FILENAME = 'D:\University\database\lab5\Devices.mdf',
SIZE = 30MB, MAXSIZE = UNLIMITED, FILEGROWTH = 5MB )
LOG ON
( NAME = University_log, FILENAME = 'D:\University\database\Devices_log.ldf',
SIZE = 8MB, MAXSIZE = UNLIMITED, FILEGROWTH = 10% )

GO

use devices;

IF DB_ID ('devices') is not null

CREATE TABLE [producers]
(
[id] int IDENTITY ,
[name] nvarchar(50) NULL,
[address] nvarchar(50) NULL,
[phone_number] int NULL
);
go
ALTER TABLE [producers] 
	ADD 
	CONSTRAINT PK_producers PRIMARY KEY  NONCLUSTERED ([id]);

go
CREATE TABLE [coworkers]
(
[id] int IDENTITY ,
[surname] nvarchar(50) NULL,
[name] nvarchar(50) NULL,
[position] nvarchar(50) NULL,
[laboratory] int NULL
);
go
ALTER TABLE [coworkers] 
	ADD 
	CONSTRAINT PK_coworkers PRIMARY KEY  NONCLUSTERED ([id]);
go
CREATE TABLE [receiving_devices]
(
[id] int IDENTITY ,
[producer_id] int NOT NULL,
[date_of_receiving] date NULL,
CONSTRAINT FK_producers FOREIGN KEY (producer_id)
REFERENCES producers (id)
);
go
ALTER TABLE [receiving_devices]
	ADD 
	CONSTRAINT PK_receiving_devices PRIMARY KEY  NONCLUSTERED ([id]);
go
CREATE TABLE [devices]
(
[serial_id] int NOT NULL ,
[inventory_id] int  ,
[type] nvarchar(50) NULL,
[brand] nvarchar(50) NULL,
[characteristics] text NULL,
[image] varbinary(5000) NULL
CONSTRAINT FK_devices FOREIGN KEY (inventory_id)
REFERENCES receiving_devices (id),
);
go
ALTER TABLE [devices] 
	ADD 
	CONSTRAINT PK_devices PRIMARY KEY  NONCLUSTERED ([serial_id]);
go
CREATE TABLE [accounting]
(
[id] int IDENTITY ,
[devices_id] int NOT NULL,
[coworkers_id] int NOT NULL,
[date_of_issue] date NULL,
[date_of_admission] date NULL,
CONSTRAINT FK_receiving_devices FOREIGN KEY (devices_id)
REFERENCES devices (serial_id),
CONSTRAINT FK_coworkers FOREIGN KEY (coworkers_id)
REFERENCES coworkers (id)
);

go

ALTER TABLE [accounting]
	ADD 
	CONSTRAINT PK_accounting PRIMARY KEY  NONCLUSTERED ([id]),
	CHECK ([date_of_admission]>[date_of_issue]);
	/*	CONSTRAINT AK_Person_Surname_Name UNIQUE ( Surname, Name ) */
go
ALTER TABLE [devices] 
	ADD 
	CHECK ([serial_id] LIKE '[0-9][0-9][0-9][0-9]');
go
CREATE INDEX IX_producers_name
  ON [producers] ([name]);

CREATE INDEX IX_reader_name
  ON [devices] ([type]);
go
INSERT INTO producers(name ,address,phone_number) VALUES
( 'Sniezka', 'Lvivska street', 0967760255),
( 'Try Slony', 'Halytska street', 0967760255),
( 'Mykolka', 'Bandery street', 0967760255),
( 'Koparol', 'Yavorivska street', 0967760255),
( 'Barvinok', 'Zelena street', 0967760255),
( '562 B', 'B. Khmelnytskoho street', 0967760255),
( 'Farby-eco', 'Bandery street', 0967760255),
( 'Smile', 'Lukasha street', 0967760255),
( 'Ananas', 'Rozeva street', 0967760255),
( 'Loal', 'B. Kotsubynskoho street', 0967760255)


INSERT INTO coworkers(surname, name, position, laboratory) VALUES
( 'Ivanov', 'Ivan', 'chimik', 25),
( 'Mykhailenko', 'Vasyl', 'chimik', 22),
( 'Smirnov', 'Mykola', 'chimik', 17),
( 'Vyshnyvetska', 'Lesia', 'chimik', 25),
( 'Kobylianska', 'Olha', 'chimik', 22),
( 'Smirnov', 'Ivan', 'chimik', 17)

INSERT INTO receiving_devices(producer_id, date_of_receiving) VALUES
( 1, '2018/08/18'),
( 2, '2018/08/18'),
( 3, '2018/08/18'),
( 4, '2018/10/28'),
( 5, '2018/10/28'),
( 6, '2018/10/28'),
( 7, '2018/01/15'),
( 8, '2018/01/15')

INSERT INTO devices(serial_id, inventory_id, type, brand, characteristics, image ) VALUES
( 1234, 1, 'laptop', 'asus', 'white', 1010),
( 2345, 2, 'iphone', 'lenovo', 'smart', 1010),
( 1256, 3, 'smartphone', 'lenovo', 'black', 1010),
( 2223, 4, 'mi-band', 'acer', 'red', 1010),
( 1274, 5, 'printer', 'asus', 'white', 1010),
( 2375, 6, 'i-pad', 'lenovo', 'smart', 1010),
( 1276, 7, 'aplle-watch', 'lenovo', 'black', 1010),
( 2273, 8, 'notebook', 'acer', 'red', 1010)


INSERT INTO accounting(devices_id, coworkers_id, date_of_issue,date_of_admission ) VALUES
( 1234, 1, '2018/05/15','2018/08/18'),
( 1256, 2, '2018/05/17','2018/08/23'),
( 2223, 1, '2018/05/15','2018/08/28'),
( 2345, 4, '2018/05/05','2018/08/10'),
( 1274, 1, '2018/05/15','2018/08/18'),
( 1276, 2, '2018/05/17','2018/08/23'),
( 2273, 1, '2018/05/15','2018/08/28'),
( 2375, 4, '2018/05/05','2018/08/10')