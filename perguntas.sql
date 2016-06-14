--1-Quais revisões foram feitas no dia 22/06/2016
SELECT data, hora, carro_placa
FROM revisao
WHERE data='04/06/2016';
--Saida Prevista
/*
DATA       HORA  CARRO_P
---------- ----- -------
04/06/2016 11:32 KIV0245
*/

--2-Quais reboques foram feitos do dia 19/06/2016
SELECT hora, distancia, carro_placa
FROM reboque
WHERE data='06/06/2016';
--Saida Prevista
/*
HORA   DISTANCIA CARRO_P
----- ---------- -------
16:34         13 KIV0245
*/

--3-Quais pagamentos foram feitos no dia 22/06/2016
SELECT hora, valor, carro_placa
FROM pagamento
WHERE data='02/06/2016';
--Saida Prevista
/*
HORA       VALOR CARRO_P
----- ---------- -------
14:51       79,9 KGK0012
14:51       79,9 KGK0012
14:52       79,9 KGK0012
14:52       79,9 KKK0001
14:52       79,9 KKK0001
14:52       79,9 KKK0001
*/

--4-Quais os veículos do cliente de código: 0003 (WMOF solutions)
SELECT placa, marca, modelo
FROM carro
WHERE cliente_codigo = 0003;
--Saida Prevista
/*
PLACA   MARCA                MODELO             
------- -------------------- --------------------
KKK0001 VW                   Gol                 
KGK0012 VW                   Gol
*/

--5-Quais as revisões não autorizadas
SELECT codigo, data, hora, carro_placa
FROM revisao
WHERE autorizacao = '0';
--Saida Prevista
/*
    CODIGO DATA       HORA  CARRO_P
---------- ---------- ----- -------
         2 04/06/2016 11:32 KIV0245
*/

--6-Quais os clientes com mais de um carro
SELECT c.nome
FROM cliente c
WHERE 1 < (SELECT COUNT(*)
FROM carro v
WHERE v.cliente_codigo = c.codigo);
--Saida Prevista
/*
NOME                                                                           
--------------------------------------------------------------------------------
WMOF SOLUTIONS
*/

--7-Quais os veiculos da marca “VW” e modelo “Gol” (Para informar sobre um possível recall por telefone)
SELECT c.nome, c.telefone
FROM cliente c
WHERE 0 < (SELECT COUNT(*)
FROM carro v
WHERE v.cliente_codigo = c.codigo AND v.marca = 'VW' AND v.modelo = 'Gol');
--Saida Prevista
/*
NOME
--------------------------------------------------------------------------------
TELEFONE
---------------
WMOF SOLUTIONS                                                                  
(81) 3010-3977 
*/

--8-Quais os reboques com a distancia maior de 100km 
SELECT data, hora, distancia, carro_placa
FROM reboque
WHERE distancia > 100;
--Saida Prevista
/*
DATA       HORA   DISTANCIA CARRO_P
---------- ----- ---------- -------
04/06/2016 10:31        129 KIV0245
*/

--9-Quais os reboque não foram destinados a oficina
SELECT data, hora, carro_placa
FROM reboque
WHERE destino != 'oficina';
--Saida Prevista
/*
DATA       HORA  CARRO_P
---------- ----- -------
06/06/2016 16:34 KIV0245
*/

--10-Quais revisões sem alinhamentos
SELECT r.data, r.hora, r.carro_placa
FROM revisao r
WHERE NOT EXISTS (SELECT a.codigo_revisao
FROM alinhamento a
WHERE a.CODIGO_REVISAO = r.CODIGO);
--Saida Prevista
/*
DATA       HORA  CARRO_P
---------- ----- -------
05/06/2016 17:32 KGK0012
*/


--Procedure Insert
CREATE OR REPLACE PROCEDURE insertClienteFisica(
	   cliente_codigo IN cliente.codigo%TYPE,
	   cliente_nome IN cliente.nome%TYPE,
	   cliente_endereco IN cliente.endereco%TYPE,
	   cliente_telefone IN cliente.telefone%TYPE,
       cliente_cpf IN cliente_fisica.cpf%TYPE)     
IS
BEGIN

  INSERT INTO cliente (codigo, nome, endereco, telefone) 
  VALUES (cliente_codigo, cliente_nome, cliente_endereco, cliente_telefone);
  INSERT INTO cliente_fisica (cliente_codigo, cpf)
  VALUES (cliente_codigo, cliente_cpf);

  COMMIT;

END;

--Procedure Delete
CREATE OR REPLACE PROCEDURE deleteCliente(
    c_codigo IN cliente.codigo%TYPE)
IS
BEGIN

    DELETE carro WHERE cliente_codigo = c_codigo;
    DELETE cliente_fisica WHERE cliente_codigo = c_codigo;
    DELETE cliente WHERE codigo = c_codigo;
    
    COMMIT;
END;

--Procedure UPDATE
CREATE OR REPLACE PROCEDURE updateClienteFisica(
       cliente_cod IN cliente.codigo%TYPE,
	   cliente_nome IN cliente.nome%TYPE,
	   cliente_endereco IN cliente.endereco%TYPE,
	   cliente_telefone IN cliente.telefone%TYPE,
       cliente_cpf IN cliente_fisica.cpf%TYPE)
IS
BEGIN
    UPDATE cliente
    SET
    nome = cliente_nome,
    endereco = cliente_endereco,
    telefone = cliente_telefone
    WHERE
    codigo = cliente_cod;
    
    UPDATE cliente_fisica
    SET
    cpf = cliente_cpf
    WHERE
    cliente_codigo = cliente_cod;
    
    COMMIT;
    
END;
    