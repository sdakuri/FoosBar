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
    id bigint NOT NULL,
    playerone bigint NOT NULL,
    playertwo bigint NOT NULL,
    playeronescore integer,
    playertwoscore integer,
    creationdate date NOT NULL
);


ALTER TABLE public.game OWNER TO postgres;

CREATE TABLE leaders (
    player bigint NOT NULL,
    type character varying(12) NOT NULL,
    gameswon integer NOT NULL
);


ALTER TABLE public.leaders OWNER TO postgres;

CREATE TABLE player (
    id bigint NOT NULL,
    firstname character varying(20) NOT NULL,
    lastname character varying(30),
    numberofgameswon bigint
);

ALTER TABLE public.player OWNER TO postgres;

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

CREATE FUNCTION upsert_leaderboard(playerid INT) RETURNS VOID AS
$$
BEGIN
    LOOP
        UPDATE leaders SET gameswon = gameswon + 1 WHERE player = playerid;
        IF found THEN
            RETURN;
        END IF;
        BEGIN
            INSERT INTO leaders(player, type, gameswon) VALUES (playerid, 'MONTHLY',1);
            INSERT INTO leaders(player, type, gameswon) VALUES (playerid, 'QUARTERLY',1);
            INSERT INTO leaders(player, type, gameswon) VALUES (playerid, 'YEARLY',1);
            INSERT INTO leaders(player, type, gameswon) VALUES (playerid, 'ALLTIME',1);
            RETURN;
        EXCEPTION WHEN unique_violation THEN
        END;
    END LOOP;
END;
$$
LANGUAGE plpgsql;

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;



