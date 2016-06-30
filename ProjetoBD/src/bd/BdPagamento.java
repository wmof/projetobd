/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import object.Pagamento;
import object.Carro;
import object.Cliente;

/**
 *
 * @author Leto
 */
public class BdPagamento {
    
    public static void insert(Pagamento pagamento) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "INSERT INTO pagamento (codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo)"
                    + " VALUES('"
                    + pagamento.getCodigo() + "','"
                    + pagamento.getData()+ "','"
                    + pagamento.getHora() + "','"
                    + pagamento.getValor() + "','"
                    + pagamento.getCarro().getCliente().getCodigo() + "','"
                    + pagamento.getCarro().getPlaca() + "','"
                    + pagamento.getPagamento().getCodigo() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void delete(Pagamento pagamento) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "DELETE pagamento WHERE codigo = '" + pagamento.getCodigo() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    
    public ArrayList<Pagamento> select() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Pagamento> retorno = new ArrayList<Pagamento>();
        String sql;
        sql = "SELECT codigo, data, hora, valor, cliente_codigo, carro_placa, valida_codigo FROM pagamento";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Pagamento p = new Pagamento();
            while (rs.next()) {
                p.setCodigo(rs.getString("codigo"));
                p.setData(rs.getString("data"));
                p.setHora(rs.getString("hora"));
                p.setValor(rs.getDouble("valor"));
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getString("cliente_codigo"));
                Carro car = new Carro();
                car.setPlaca(rs.getString("carro_placa"));
                car.setCliente(cli);
                Pagamento pag = new Pagamento();
                try {
                    pag.setCodigo(rs.getString("valida_codigo"));
                } catch (Exception e) {
                    pag.setCodigo("0000");
                }
                p.setPagamento(pag);
                retorno.add(p);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retorno;
    }
    
}
