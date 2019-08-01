CREATE TABLE pessoa
(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(5),
	complemento VARCHAR(30),
	bairro VARCHAR(20),
	cep VARCHAR(10),
	cidade VARCHAR(15),
	estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa VALUES(NULL,"Rafael",TRUE,"Rua Carijó","31","Sobrado","São Roque","26320-270","Queimados","RJ");
INSERT INTO pessoa VALUES(NULL,"Ana",TRUE,"Rua O","20","Esquina","Fanchem","25450-220","Queimados","RJ");
INSERT INTO pessoa VALUES(NULL,"Levi",FALSE,"Rua Teresinha Pinto","24","Casa gayzao","Centro","242424-240","Nova Iguaçu","RJ");