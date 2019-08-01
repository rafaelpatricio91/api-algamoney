CREATE TABLE categoria
(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria VALUES(NULL, "Lazer");
INSERT INTO categoria VALUES(NULL, "Alimentação");
INSERT INTO categoria VALUES(NULL, "Transporte");
INSERT INTO categoria VALUES(NULL, "Supermercado");
INSERT INTO categoria VALUES(NULL, "Farmácia");
INSERT INTO categoria VALUES(NULL, "Outros");