USE QUEIMAS_BANCO;

CREATE TABLE CLIENTE (
    ID_CLIENTE INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(255) NOT NULL,
    CPF CHAR(11) NOT null UNIQUE,
    TELEFONE VARCHAR(15),
    CONSTRAINT CLIENTE_PK PRIMARY KEY (ID_CLIENTE)
);

CREATE TABLE PECA (
    ID_PECA INT NOT NULL AUTO_INCREMENT,
    TAMANHO VARCHAR(50) NOT NULL,
    TIPO_PECA VARCHAR(50) NOT NULL,
    ID_CLIENTE INT NOT NULL,
    ESTAGIO VARCHAR(50) NOT NULL,
    VALOR_TOTAL DECIMAL(5 , 2 ),
    CONSTRAINT PECA_PK PRIMARY KEY (ID_PECA),
    CONSTRAINT CLIENTE_PK FOREIGN KEY (ID_CLIENTE)
        REFERENCES CLIENTE (ID_CLIENTE)
);

CREATE TABLE QUEIMA (
    ID_QUEIMA INT NOT NULL AUTO_INCREMENT,
    DATA_QUEIMA DATE NOT NULL,
    TIPO_QUEIMA VARCHAR(50) NOT NULL,
    TEMPERATURA_ALCANCADA INT NOT NULL,
    ID_PECA INT NOT NULL,
    FORNO VARCHAR(50) NOT NULL,
    PRECO_QUEIMA DECIMAL(5 , 2 ) NOT NULL,
    CONSTRAINT QUEIMA_PK PRIMARY KEY (ID_QUEIMA),
    CONSTRAINT QUEIMA_PK FOREIGN KEY (ID_PECA)
        REFERENCES PECA (ID_PECA)
);

INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('João Silva', '12345678901', '(11) 91234-5678');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Maria Oliveira', '23456789012', '(21) 92345-6789');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Carlos Souza', '34567890123', '(31) 93456-7890');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Ana Costa', '45678901234', '(41) 94567-8901');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Paulo Mendes', '56789012345', '(51) 95678-9012');


select * from cliente


INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('M', 'Prato', 1, 'Biscoito', 50.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('G', 'Caneca', 2, 'Esmalte', 80.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('P', 'Prato', 3, 'Biscoito', 45.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('GG', 'Tigela', 4, 'Esmalte', 120.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('M', 'Caneca', 5, 'Esmalte', 55.00);

select * from peca

