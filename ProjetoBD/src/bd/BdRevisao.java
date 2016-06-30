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
import object.Carro;
import object.Cliente;
import object.Pagamento;
import object.Revisao;

/**
 *
 * @author Leto
 */
public class BdRevisao {
    public static void insert(Revisao revisao) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "INSERT INTO revisao (codigo, data, hora, km, relatorio, autorizacao, carro_placa)"
                    + " VALUES('"
                    + revisao.getCodigo() + "','"
                    + revisao.getData()+ "','"
                    + revisao.getHora() + "','"
                    + revisao.getKm()+ "','"
                    + revisao.getRelatorio() + "','"
                    + revisao.getAutorizacao() + "','"
                    + revisao.getCarro().getPlaca() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void delete(Revisao revisao) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "DELETE revisao WHERE codigo = '" + revisao.getCodigo() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }    
    public ArrayList<Revisao> select() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Revisao> retorno = new ArrayList<Revisao>();
        String sql;
        sql = "SELECT codigo, data, hora, km, relatorio, autorizacao, carro_placa FROM pagamento";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Revisao r = new Revisao();
            while (rs.next()) {
                r.setCodigo(rs.getString("codigo"));
                r.setData(rs.getString("data"));
                r.setHora(rs.getString("hora"));
                r.setKm(rs.getString("km"));
                r.setAutorizacao(rs.getString("autorizacao"));
                r.setRelatorio(rs.getString("relatorio"));
                Carro car = new Carro();
                car.setPlaca(rs.getString("carro_placa"));
                r.setCarro(car);
                retorno.add(r);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retorno;
    }
    
}
