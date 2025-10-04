INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Shrek', 90, 'ANIMADA', NULL, '2024-06-01', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Inception', 148, 'CIENCIA_FICCION', NULL, '2010-07-16', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Titanic', 195, 'DRAMA', 4.6, '1997-12-19', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('John Wick', 101, 'ACCION', NULL, '2014-10-24', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('El Conjuro', 112, 'TERROR', 3.0, '2013-07-19', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Coco', 105, 'ANIMADA', 4.7, '2017-10-27', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Interstellar', 169, 'CIENCIA_FICCION', 5.0, '2014-11-07', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Joker', 122, 'DRAMA', NULL, '2019-10-04', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Toy Story', 81, 'ANIMADA', 4.5, '1995-11-22', 'D') ON CONFLICT (title) DO NOTHING;

INSERT INTO movie (title, duration, gender, rating, releaseDate, status) VALUES ('Avengers: Endgame', 181, 'ACCION', 3.9, '2019-04-26', 'D') ON CONFLICT (title) DO NOTHING;