-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2-beta1
-- PostgreSQL version: 11.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- object: reasoneat_dba | type: ROLE --
-- DROP ROLE IF EXISTS reasoneat_dba;
-- CREATE ROLE reasoneat_dba WITH ;
-- ddl-end --


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: reasoneat | type: DATABASE --
-- -- DROP DATABASE IF EXISTS reasoneat;
-- CREATE DATABASE reasoneat;
-- -- ddl-end --
-- 

-- object: public.category | type: TABLE --
-- DROP TABLE IF EXISTS public.category CASCADE;
CREATE TABLE public.category (
	category_id uuid NOT NULL,
	name varchar(50) NOT NULL,
	header_text text,
	footer_text text,
	image varchar(255),
	CONSTRAINT category_pk PRIMARY KEY (category_id)

);
-- ddl-end --
ALTER TABLE public.category OWNER TO reasoneat_dba;
-- ddl-end --

-- object: public.product | type: TABLE --
-- DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product (
	product_id uuid NOT NULL,
	name varchar(50) NOT NULL,
	header_text text,
	footer_text text,
	image varchar(255) NOT NULL,
	category_id uuid NOT NULL,
	CONSTRAINT product_pk PRIMARY KEY (product_id)

);
-- ddl-end --
ALTER TABLE public.product OWNER TO reasoneat_dba;
-- ddl-end --

-- object: public.season | type: TABLE --
-- DROP TABLE IF EXISTS public.season CASCADE;
CREATE TABLE public.season (
	season_id uuid NOT NULL,
	name varchar(50) NOT NULL,
	header_text text,
	footer_text text,
	image varchar(255),
	CONSTRAINT season_pk PRIMARY KEY (season_id)

);
-- ddl-end --
ALTER TABLE public.season OWNER TO reasoneat_dba;
-- ddl-end --

-- object: public.month | type: TABLE --
-- DROP TABLE IF EXISTS public.month CASCADE;
CREATE TABLE public.month (
	month_id uuid NOT NULL,
	name varchar(50) NOT NULL,
	header_text text,
	footer_text text,
	image varchar(255),
	season_id uuid NOT NULL,
	CONSTRAINT month_pk PRIMARY KEY (month_id)

);
-- ddl-end --
ALTER TABLE public.month OWNER TO reasoneat_dba;
-- ddl-end --

-- object: public.product_month | type: TABLE --
-- DROP TABLE IF EXISTS public.product_month CASCADE;
CREATE TABLE public.product_month (
	product_id uuid NOT NULL,
	month_id uuid NOT NULL,
	CONSTRAINT product_month_pk PRIMARY KEY (product_id,month_id)

);
-- ddl-end --
ALTER TABLE public.product_month OWNER TO reasoneat_dba;
-- ddl-end --

-- object: fk_product_category | type: CONSTRAINT --
-- ALTER TABLE public.product DROP CONSTRAINT IF EXISTS fk_product_category CASCADE;
ALTER TABLE public.product ADD CONSTRAINT fk_product_category FOREIGN KEY (category_id)
REFERENCES public.category (category_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_month_season | type: CONSTRAINT --
-- ALTER TABLE public.month DROP CONSTRAINT IF EXISTS fk_month_season CASCADE;
ALTER TABLE public.month ADD CONSTRAINT fk_month_season FOREIGN KEY (season_id)
REFERENCES public.season (season_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_product_many_month | type: CONSTRAINT --
-- ALTER TABLE public.product_month DROP CONSTRAINT IF EXISTS fk_product_many_month CASCADE;
ALTER TABLE public.product_month ADD CONSTRAINT fk_product_many_month FOREIGN KEY (product_id)
REFERENCES public.product (product_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_month_many_product | type: CONSTRAINT --
-- ALTER TABLE public.product_month DROP CONSTRAINT IF EXISTS fk_month_many_product CASCADE;
ALTER TABLE public.product_month ADD CONSTRAINT fk_month_many_product FOREIGN KEY (month_id)
REFERENCES public.month (month_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


