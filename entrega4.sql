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

--Procedure Select

/*FALTAAAAA*/

--Fnciton para saber se o veiculo tem mais reboques ou revisões
CREATE OR REPLACE FUNCTION reb_rev(placa carro.placa%TYPE) RETURN
VARCHAR2 IS
reb VARCHAR2(2);
rev VARCHAR2(2);
retorno VARCHAR2(7);
BEGIN
SELECT COUNT(*) into reb from reboque WHERE carro_placa = placa;
SELECT COUNT(*) into rev from revisao WHERE carro_placa = placa;
IF rev > reb THEN retorno := 'Revisão';
ELSIF rev < reb THEN
retorno := 'Reboque';
ELSE retorno := 'Igual';
END IF;
RETURN retorno;
END reb_rev;

--Funcition para saber o custo de cada reboque
/*
Os reboque ate 50km de distancia custam R$50
A cada KM a mais de 50 é cobrado R$1.1
*/
CREATE OR REPLACE FUNCTION valor_reboque(cod reboque.codigo%TYPE) RETURN
NUMBER IS
retorno NUMBER;
reb NUMBER;
BEGIN
SELECT distancia into reb from reboque WHERE codigo = cod;
IF reb <= 50 THEN retorno := 50;
ELSE
retorno := 50 + (reb-50)*1.1;
END IF;
RETURN retorno;
END valor_reboque;

--TRIGGER para proibir a inserção de mais de 3 reboques por ANO
