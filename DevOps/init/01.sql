

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

CREATE TABLE public.user_table (
    id bigint NOT NULL,
    birth_date date NOT NULL,
    email character varying(255) NOT NULL,
    first_surname character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    registration_date date NOT NULL,
    second_surname character varying(255)
);
ALTER TABLE public.user_table OWNER TO postgres;

INSERT INTO public.user_table
(id, birth_date, email, first_surname, name, password, phone_number, registration_date, second_surname)
VALUES(1, '1980-01-02', 'john@gmail.com', 'cena', 'john', 'john1', '123456789', '2023-03-15', null);

INSERT INTO public.user_table
(id, birth_date, email, first_surname, name, password, phone_number, registration_date, second_surname)
VALUES(2, '2005-08-29', 'john2@gmail.com', 'cena2', 'john2', 'john2', '123456789', '2023-03-15', null);

INSERT INTO public.user_table
(id, birth_date, email, first_surname, name, password, phone_number, registration_date, second_surname)
VALUES(3, '1963-08-29', 'john3@gmail.com', 'cena', 'john', 'john3', '123456789', '2023-03-15', null);

INSERT INTO public.user_table
(id, birth_date, email, first_surname, name, password, phone_number, registration_date, second_surname)
VALUES(4, '1963-08-30', 'john4@gmail.com', 'cena', 'john', 'john4', '123456789', '2023-03-15', null);

INSERT INTO public.user_table
(id, birth_date, email, first_surname, name, password, phone_number, registration_date, second_surname)
VALUES(5, '2005-08-30', 'john5@gmail.com', 'cena', 'john', 'john5', '123456789', '2023-03-15', null);
