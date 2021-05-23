CREATE DATABASE MYE030;
USE MYE030;
CREATE TABLE countries(country_ID INT,
					   country_NAME VARCHAR(30),
                       PRIMARY KEY(country_ID));
CREATE TABLE years(year_ID INT,
				   PRIMARY KEY(year_ID));

SELECT * FROM countries;