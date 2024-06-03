INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('João Silva', '12345678901', '(11) 91234-5678');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Maria Oliveira', '23456789012', '(21) 92345-6789');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Carlos Souza', '34567890123', '(31) 93456-7890');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Ana Costa', '45678901234', '(41) 94567-8901');
INSERT INTO CLIENTE (NOME, CPF, TELEFONE) VALUES ('Paulo Mendes', '56789012345', '(51) 95678-9012');
 
 
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('M', 'Prato', 1, 'Biscoito', 50.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('G', 'Caneca', 2, 'Esmalte', 80.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('P', 'Prato', 3, 'Biscoito', 45.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('GG', 'Tigela', 4, 'Esmalte', 120.00);
INSERT INTO PECA (TAMANHO, TIPO_PECA, ID_CLIENTE, ESTAGIO, VALOR_TOTAL) VALUES ('M', 'Caneca', 5, 'Esmalte', 55.00);
 
 
INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA)
VALUES ('2024-05-01', 'Esmalte', 950, 1, 'Forno A', 30.00);
 
INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA)
VALUES ('2024-05-02', 'Biscoito', 1050, 2, 'Forno B', 40.00);
 
INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA)
VALUES ('2024-05-03', 'Esmalte', 950, 3, 'Forno C', 35.00);
 
INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA)
VALUES ('2024-05-04', 'Biscoito', 1050, 4, 'Forno A', 45.00);
 
INSERT INTO QUEIMA (DATA_QUEIMA, TIPO_QUEIMA, TEMPERATURA_ALCANCADA, ID_PECA, FORNO, PRECO_QUEIMA)
VALUES ('2024-05-05', 'Esmalte', 950, 5, 'Forno B', 50.00);