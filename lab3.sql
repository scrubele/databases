/* 1 query */
SELECT * 
FROM income_o
WHERE income_o.inc>=5000.0 and income_o.inc<=10000.0;
/* 2 query */
SELECT date, place
FROM pass_in_trip
WHERE place LIKE '1%';
/* 3 query */
SELECT product.model, product.maker
FROM product INNER JOIN pc ON product.model = pc.model
WHERE pc.price<600;
/* 4 query */
SELECT * 
FROM product
WHERE product.model = ANY( 
	SELECT pc.model
	FROM pc );
/* 5 query */
SELECT class
FROM ships
WHERE ships.class IN (
SELECT class
FROM classes
)
GROUP BY class;
/* 6 query */
SELECT concat('Row:',Left(place,1)) AS first,  concat('Place:',SUBSTRING(place,2,1)) AS second
FROM pass_in_trip;
/* 7 query */
SELECT TOP 2 maker
FROM product INNER JOIN printer ON product.model = printer.model
GROUP BY maker;
/* 7 query */
SELECT TOP 1 product.maker, MIN(printer.price)
FROM printer INNER JOIN product ON printer.model=product.model
GROUP BY product.maker;
/* 8 query */
SELECT product.maker, MAX(pc.price)
FROM product INNER JOIN pc ON product.model = pc.model
GROUP BY maker;
/* 9 query */
SELECT product.maker,  
	CASE 
		WHEN count(printer.type) >0 THEN concat(concat('YES(',count(printer.type)),')')
		ELSE 'NO'
	END as "printer"
FROM product LEFT JOIN printer ON product.model=printer.model
GROUP BY product.maker;

/* 10 query */

SELECT c.class1, c.summ FROM(
SELECT b.classn as class1, sum(countss) as summ
 FROM (
	SELECT classes.class as classn, count(ships.name) as countss
	FROM classes INNER JOIN ships ON classes.class=ships.class
	GROUP BY classes.class
	UNION
	SELECT classes.class, count(outcomes.result) as countss
	FROM outcomes INNER JOIN classes ON classes.class=outcomes.ship
	GROUP BY classes.class
	) as b 

  GROUP BY b.classn) as c
  WHERE  c.summ>=1 and c.summ<=2;
;
	
SELECT maker
FROM laptop INNER JOIN product ON laptop.model=product.model
WHERE maker=ANY(
	SELECT maker
	FROM pc INNER JOIN product ON pc.model=product.model)
Group BY maker;