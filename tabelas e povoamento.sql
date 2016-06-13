--Apagando arquivos das possiveis tabelas


DELETE alinhamento WHERE 1=1;
DELETE reboque WHERE 1=1;
DELETE revisao WHERE 1=1;
DELETE pagamento WHERE 1=1;
DELETE carro WHERE 1=1;
DELETE cliente_fisica WHERE 1=1;
DELETE cliente_juridica WHERE 1=1;
DELETE cliente WHERE 1=1;


--Apagando possiveis tabelas


DROP TABLE reboque;
DROP TABLE alinhamento;
DROP TABLE revisao;
DROP TABLE cliente_fisica;
DROP TABLE cliente_juridica;
DROP TABLE pagamento;
DROP TABLE carro;
DROP TABLE cliente;


--Criando Tabelas


CREATE TABLE CLIENTE(
codigo INT PRIMARY KEY,
nome VARCHAR(100),
endereco VARCHAR(100),
telefone VARCHAR(15) );
commit;

CREATE TABLE cliente_fisica (
cliente_codigo INT,
cpf VARCHAR(14),
CONSTRAINT FK_cliente_codigo_fisica FOREIGN KEY (cliente_codigo) REFERENCES CLIENTE (codigo) );
commit;

CREATE TABLE cliente_juridica(
cliente_codigo INT,
cnpj VARCHAR(18),
CONSTRAINT FK_cliente_codigo_juridica FOREIGN KEY (cliente_codigo) REFERENCES cliente (codigo) );
commit;

CREATE TABLE carro(
placa VARCHAR(7) PRIMARY KEY,
marca VARCHAR(20),
modelo VARCHAR(20),
cliente_codigo INT,
CONSTRAINT FK_cliente_codigo_carro FOREIGN KEY (cliente_codigo) REFERENCES cliente (codigo) );
commit;

CREATE TABLE pagamento(
codigo INT PRIMARY KEY,
data VARCHAR(10),
valor NUMBER,
hora VARCHAR(5),
carro_placa VARCHAR(7),
cliente_codigo INT,
valida_codigo INT,
CONSTRAINT FK_cliente_codigo_pagamento FOREIGN KEY (cliente_codigo) REFERENCES cliente (codigo),
CONSTRAINT FK_carro_placa FOREIGN KEY (carro_placa) REFERENCES carro(placa),
CONSTRAINT FK_valida_codigo FOREIGN KEY (valida_codigo) REFERENCES pagamento(codigo) );
commit;

CREATE TABLE revisao(
codigo INT PRIMARY KEY,
hora VARCHAR(5) NOT NULL,
data VARCHAR(10) NOT NULL,
km INT,
relatorio VARCHAR(255),
autorizacao INT,
carro_placa VARCHAR(7),
CONSTRAINT FK_carro_placa_revisao FOREIGN KEY (carro_placa) REFERENCES carro(placa) );
commit;

CREATE TABLE alinhamento(
observacoes VARCHAR(255),
codigo_revisao INT,
CONSTRAINT FK_codigo_revisao FOREIGN KEY (codigo_revisao) REFERENCES revisao(codigo));
commit;

CREATE TABLE reboque(
codigo INT PRIMARY KEY,
hora VARCHAR(5) NOT NULL,
data VARCHAR(10) NOT NULL,
km INT,
destino VARCHAR(255),
origem VARCHAR(255),
distancia INT,
carro_placa VARCHAR(7),
CONSTRAINT FK_carro_placa_reboque FOREIGN KEY (carro_placa) REFERENCES carro(placa) );
commit;


--Povoando tabelas


INSERT INTO cliente (codigo, nome, endereco, telefone)
VALUES ('0001','Wellington', 'Rua: garanhuns, 45', '(81) 99376-0544');
INSERT INTO cliente_fisica (cliente_codigo, cpf)
VALUES ('0001','00000000007');

INSERT INTO cliente (codigo, nome, endereco, telefone)
VALUES ('0002','Ricardo', 'Rua: Ernesto Nazareth, 102', '(81) 98489-0988');
INSERT INTO cliente_fisica (cliente_codigo, cpf)
VALUES ('0002','12345678900');

INSERT INTO cliente (codigo, nome, endereco, telefone)
VALUES ('0003','WMOF SOLUTIONS', 'Rua: dos lirios, 09', '(81) 3010-3977');
INSERT INTO cliente_juridica (cliente_codigo, cnpj)
VALUES ('0003','’0000000000000007’');

INSERT INTO cliente (codigo, nome, endereco, telefone)
VALUES ('0004','Lairson', 'Av: Paulista, 11', '(81) 98988-0988');
INSERT INTO cliente_fisica (cliente_codigo, cpf)
VALUES ('0004','12345678901');

INSERT INTO carro (placa, marca, modelo, cliente_codigo)
VALUES ('KKL1519','Citroen','C4 pallas',0001);

INSERT INTO carro (placa, marca, modelo, cliente_codigo)
VALUES ('KIV0245','GM','Vectra',0002);

INSERT INTO carro (placa, marca, modelo, cliente_codigo)
VALUES ('KKK0001','VW','Gol',0003);

INSERT INTO carro (placa, marca, modelo, cliente_codigo)
VALUES ('KGK0012','VW','Gol',0003);

INSERT INTO carro (placa, marca, modelo, cliente_codigo)
VALUES ('KKW2723','Toyota','Corolla',0004);

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0001', '02/04/2016', '14:10', '79,90', '0001', 'KKL1519', '');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0002', '03/05/2016', '12:19', '79,90', '0001', 'KKL1519', '0001');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0003', '01/06/2016', '09:00', '79,90', '0002', 'KIV0245', '');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0004', '01/06/2016', '11:45', '79,90', '0001', 'KKL1519', '0002');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0005', '03/06/2016', '08:10', '79,90', '0004', 'KKW2723', '');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0006', '02/06/2016', '14:51', '79,90', '0003', 'KGK0012', '');
INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0007', '02/06/2016', '14:51', '79,90', '0003', 'KGK0012', '0006');
INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0008', '02/06/2016', '14:52', '79,90', '0003', 'KGK0012', '0007');

INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0009', '02/06/2016', '14:52', '79,90', '0003', 'KKK0001', '');
INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0010', '02/06/2016', '14:52', '79,90', '0003', 'KKK0001', '0009');
INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0011', '02/06/2016', '14:52', '79,90', '0003', 'KKK0001', '0010');

INSERT INTO revisao (codigo, data, hora, km, relatorio, autorizacao, carro_placa)
VALUES (0001, '12/04/2016', '15:30', '23000', 'Foi feito troca dos itens de 20mil km', '1', 'KKL1519');

INSERT INTO revisao (codigo, data, hora, km, relatorio, autorizacao, carro_placa)
VALUES (0002, '04/06/2016', '11:32', '80789', 'Foi feito orçamento dos itens de 80mil km, mas o cliente não aprovou', '0', 'KIV0245');

INSERT INTO revisao (codigo, data, hora, km, relatorio, autorizacao, carro_placa)
VALUES (0003, '05/06/2016', '16:50', '32067', 'Foi feito troca dos itens de 20mil km', '1', 'KKK0001');
INSERT INTO revisao (codigo, data, hora, km, relatorio, autorizacao, carro_placa)
VALUES (0004, '05/06/2016', '17:32', '29456', 'Foi feito troca dos itens de 30mil km + bateria', '1', 'KGK0012');

INSERT INTO alinhamento (codigo_revisao, observacoes)
VALUES (0001,'Alinhemento FEITO, pouca diferença');

INSERT INTO alinhamento (codigo_revisao, observacoes)
VALUES (0002,'Alinhemento FEITO, mas suspensão ruim');

INSERT INTO alinhamento (codigo_revisao, observacoes)
VALUES (0003,'Alinhemento FEITO, bastante diferença');

INSERT INTO alinhamento (codigo_revisao, observacoes)
VALUES (0004,'');



INSERT INTO reboque(codigo, data, hora, km, distancia, origem, destino, carro_placa)
VALUES (0001, '04/06/2016', '10:31', '80789', 128.6, 'CARUARU-PE', 'oficina', 'KIV0245');

INSERT INTO reboque(codigo, data, hora, km, distancia, origem, destino, carro_placa)
VALUES (0002, '06/06/2016', '16:34', '80789', 12.6, 'oficina', 'casa do proprietario', 'KIV0245');

INSERT INTO reboque(codigo, data, hora, km, distancia, origem, destino, carro_placa)
VALUES (0003, '13/06/2016', '08:34', '132452', 17, 'casa do proprietario', 'oficina', 'KKW2723');