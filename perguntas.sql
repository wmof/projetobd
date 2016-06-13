--server  : oracle11g.cin.ufpe.br
--instance: Instance01
--username: u_wmof
--password: shdnaahs
--string para conexao: oracle11g_Instance01

--1-Quais revisões foram feitas no dia 22/06/2016
SELECT data, hora, carro_placa
FROM revisao
WHERE data='04/06/2016'
--Saida Prevista
/*
DATA       HORA  CARRO_P
---------- ----- -------
04/06/2016 11:32 KIV0245
*/

--2-Quais reboques foram feitos do dia 19/06/2016
SELECT codigo, data, hora, km, distancia, origem, destino, carro_placa
FROM reboque
WHERE data='19/06/2016'

--3-Quais pagamentos foram feitos no dia 22/06/2016
SELECT codigo, data, hora, valor, veiculo_placa
FROM pagamento
WHERE data='22/06/2016'

--4-Quais os veículos do cliente de código: 0003 (DHG solutions)
SELECT placa, marca, modelo, cliente_codigo
FROM carro
WHERE cliente_codigo = 0003

--5-Quais as revisões não autorizadas
SELECT codigo, data, hora, km, relatorio, autorizacao, carro_placa
FROM revisao
WHERE autorizacao = '0'

--6-Quais os clientes com mais de um carro
SELECT c.nome
FROM cliente c, carro v
WHERE v.cliente_codigo = c.codigo

--7-Quais os veiculos da marca “BMW” e modelo “Next” (Para informar sobre um possível recall)
SELECT placa, marca, modelo, cliente_codigo
FROM carro
WHERE marca = 'BMW' AND modelo ='Next'

--8-Quais os reboques com a distancia maior de 100km 
SELECT codigo, data, hora, km, distancia, origem, destino, carro_placa
FROM reboque
WHERE distancia > 100

--9-Quais os reboque não foram destinados a oficina
SELECT codigo, data, hora, km, distancia, origem, destino, carro_placa
FROM reboque
WHERE destino<>oficina

--10-Quais revisões sem alinhamentos
SELECT data, hora, km, carro_placa
FROM revisao
WHERE alinhamento = Null
