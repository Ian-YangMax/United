-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id integer NOT NULL DEFAULT nextval('"Books_id_seq"'::regclass),
    isbn text COLLATE pg_catalog."default",
    title text COLLATE pg_catalog."default",
    author text COLLATE pg_catalog."default",
    price numeric(6,2),
    CONSTRAINT "Books_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;