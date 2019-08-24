DROP TABLE IF EXISTS public.product;

CREATE TABLE public.product
(
  id serial,
  name character varying(100),
  category character varying(100),
  description character varying(2000),
  country character varying(50),
  image character varying(250),
  CONSTRAINT unique_product_1 PRIMARY KEY (id)
)  




