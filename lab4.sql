use labor_sql;
/* 1 query */
SELECT *
FROM printer
WHERE printer.type<>'Matrix' and printer.price<300
ORDER BY printer.type DESC;

/* 2 query */
SELECT ships.name, ships.launched
FROM ships
WHERE ships.name not LIKE "%a";

/* 3 query */
SELECT maker, type, speed, hd
FROM product INNER JOIN pc ON product.model=pc.model
WHERE pc.hd<8;
/* 4 query */
SELECT maker
FROM pc INNER JOIN product ON pc.model=product.model
WHERE maker in (
SELECT maker
FROM laptop INNER JOIN product ON laptop.model=product.model)
GROUP BY maker;
/* 5 query */
SELECT class
FROM classes 
WHERE class in (
    SELECT classes.class
    FROM ships INNER JOIN classes ON ships.class=classes.class) 
  OR class in (
    SELECT classes.class
    FROM ships, classes
        WHERE ships.class=classes.class);
/* 6 query */
SELECT date(date) as date
FROM battles;

/* 7 query */
SELECT product.maker, printer.model, MIN(price)
FROM printer INNER JOIN product ON printer.model=product.model;
/* 8 query */
SELECT product.maker, AVG (hd)
FROM pc INNER JOIN product ON pc.model=product.model
WHERE product.maker in (
  SELECT maker
  FROM product INNER JOIN printer ON product.model= printer.model) ;

/* 9 query */
select name, numGuns, bore, displacement, type, country, launched, ships.class
from ships join classes on ships.class = classes.class
where
  case when numGuns=8 then 1 else 0 end +
  case when bore=15 then 1 else 0 end +
  case when displacement=32000 then 1 else 0 end +
  case when type='bb' then 1 else 0 end +
  case when country='USA' then 1 else 0 end +
  case when launched=1915 then 1 else 0 end +
  case when ships.class='Kongo' then 1 else 0 end >= 4;

/* 10 query */
SELECT name FROM ships as sh WHERE launched < 1942
  UNION SELECT ou.ship FROM outcomes as ou 
    INNER JOIN battles as ba ON ba.name = ou.battle WHERE year(ba.date) <= 1942;


SELECT product FROM product WHERE maker = SOME(s);



