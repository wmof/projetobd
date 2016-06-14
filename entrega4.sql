--Deletando as views possivelmente existentes
DROP VIEW tel_carro;
DROP VIEW pag_atrasado;

--View 1 Para visualizar as placas dos veiculos e telefones dos seus respectivos donos
CREATE VIEW tel_carro
AS
SELECT DISTINCT c.telefone, v.placa
FROM
Cliente c, Carro v
WHERE c.codigo = v.cliente_codigo;
--Visualizaando VIEW 1
SELECT * 
FROM tel_carro;
--Saida Prevista
/*
TELEFONE        PLACA 
--------------- -------
(81) 99376-0544 KKL1519
(81) 3010-3977  KKK0001
(81) 98988-0988 KKW2723
(81) 3010-3977  KGK0012
(81) 98489-0988 KIV0245
*/

--View 2 Para visualizar os clientes que estão com seus pagamentos atrasados
CREATE VIEW pag_atrasado
AS
SELECT c.nome, c.telefone 
FROM
Cliente c
WHERE NOT EXISTS(SELECT DISTINCT c.codigo
FROM
CARRO v, pagamento p
WHERE c.codigo = v.CLIENTE_CODIGO AND v.placa = p.carro_placa AND SUBSTR(p.data,-7,7) = (select to_char(sysdate, 'MM/YYYY') from dual));

SELECT * 
FROM pag_atrasado;
--Saida Prevista
/*
NOME
--------------------------------------------------------------------------------
TELEFONE
---------------
Wellington                                                                      
(81) 99376-0544

Vamos inserir o pagamento para "Wellington" se tornar adiplente
*/
INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)
VALUES ('0012', to_char(sysdate, 'DD/MM/YYYY'), to_char(sysdate, 'HH24:MM'), '79,90', '0001', 'KKL1519', '0004');
--Agora vamos conferir se "Wellington" está adiplente
SELECT * 
FROM pag_atrasado;
--Saida Prevista
/*
NOME
--------------------------------------------------------------------------------
TELEFONE
---------------
Ricardo                                                                         
(81) 98489-0988
*/