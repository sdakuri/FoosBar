CREATE DATABASE foosbar WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';

ALTER DATABASE foosbar OWNER TO postgres;

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE game (
    id integer NOT NULL,
    playerone bigint NOT NULL,
    playertwo bigint NOT NULL,
    playeronescore integer,
    playertwoscore integer,
    creationdate date NOT NULL
);


ALTER TABLE public.game OWNER TO postgres;

CREATE SEQUENCE game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.game_id_seq OWNER TO postgres;

ALTER SEQUENCE game_id_seq OWNED BY game.id;

CREATE TABLE leaders (
    player bigint NOT NULL,
    type character varying(12) NOT NULL,
    gameswon integer NOT NULL
);


ALTER TABLE public.leaders OWNER TO postgres;

CREATE TABLE player (
    id integer NOT NULL,
    firstname character varying(20) NOT NULL,
    lastname character varying(30),
    numberofgameswon bigint
);


ALTER TABLE public.player OWNER TO postgres;

CREATE SEQUENCE player_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_id_seq OWNER TO postgres;

ALTER SEQUENCE player_id_seq OWNED BY player.id;

ALTER TABLE game ALTER COLUMN id SET DEFAULT nextval('game_id_seq'::regclass);

ALTER TABLE player ALTER COLUMN id SET DEFAULT nextval('player_id_seq'::regclass);

ALTER TABLE ONLY game
    ADD CONSTRAINT game_id_pk PRIMARY KEY (id);

ALTER TABLE ONLY player
    ADD CONSTRAINT id_pk PRIMARY KEY (id);

ALTER TABLE ONLY leaders
    ADD CONSTRAINT leader_pk PRIMARY KEY (player, type);


ALTER TABLE ONLY leaders
    ADD CONSTRAINT player_fk FOREIGN KEY (player) REFERENCES player(id);

ALTER TABLE ONLY game
    ADD CONSTRAINT player_one_fk FOREIGN KEY (playerone) REFERENCES player(id);

ALTER TABLE ONLY game
    ADD CONSTRAINT player_two_fk FOREIGN KEY (playertwo) REFERENCES player(id);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;



