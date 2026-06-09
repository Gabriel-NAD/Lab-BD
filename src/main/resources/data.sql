INSERT INTO ARTISTA (nome, nacionalidade) VALUES
('Queen', 'Britânica'),
('Led Zeppelin', 'Britânica'),
('AC/DC', 'Australiana'),
('Banda X (Pop)', 'Brasileira');

INSERT INTO USUARIO (username, email) VALUES
('Pablo', 'pablo@aluno.com'),
('Josue', 'josue@aluno.com'),
('Alexandre', 'alexandre@aluno.com');

INSERT INTO MUSICA (titulo, duracao_segundos, artista_id) VALUES
('Bohemian Rhapsody', 354, 1),
('Stairway to Heaven', 482, 2),
('Back In Black', 255, 3),
('We Will Rock You', 160, 1),
('Musica Pop Brasileira', 180, 4),
('Thunderstruck', 292, 3);

INSERT INTO PLAYLIST (playlist_id, usuario_id, nome) VALUES
(1, 1, 'Rock do Pablo'),
(2, 2, 'Baladas do Josue'),
(3, 1, 'Heavy Riffs');

INSERT INTO MUSICA_PLAYLIST (musica_id, playlist_id, usuario_id, ordem_na_playlist) VALUES
(1, 1, 1, 1), (3, 1, 1, 2), (4, 1, 1, 3),
(2, 2, 2, 1),
(3, 3, 1, 1), (6, 3, 1, 2);

-- Reseta as sequences para evitar conflito de PK ao criar novos registros
SELECT setval('artista_id_seq',  (SELECT MAX(id)          FROM artista));
SELECT setval('usuario_id_seq',  (SELECT MAX(id)          FROM usuario));
SELECT setval('musica_id_seq',   (SELECT MAX(id)          FROM musica));
SELECT setval('playlist_playlist_id_seq', (SELECT MAX(playlist_id) FROM playlist));