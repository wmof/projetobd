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
import object.Reboque;
import object.Revisao;

/**
 *
 * @author Leto
 */
public class BdReboque {
    public static void insert(Reboque reboque) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "INSERT INTO reboque (codigo, data, hora, km, origem, destino, distancia, carro_placa)"
                    + " VALUES('"
                    + reboque.getCodigo() + "','"
                    + reboque.getData()+ "','"
                    + reboque.getHora() + "','"
                    + reboque.getKm()+ "','"
                    + reboque.getOrigem() + "','"
                    + reboque.getDestino() + "','"
                    + reboque.getDistancia() + "','"
                    + reboque.getCarro().getPlaca() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void delete(Reboque reboque) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "DELETE reboque WHERE codigo = '" + reboque.getCodigo() + "');";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }    
    public ArrayList<Reboque> select() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Reboque> retorno = new ArrayList<Reboque>();
        String sql;
        sql = "SELECT codigo, data, hora, km, origem, destino, distancia, carro_placa FROM pagamento";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Reboque r = new Reboque();
            while (rs.next()) {
                r.setCodigo(rs.getString("codigo"));
                r.setData(rs.getString("data"));
                r.setHora(rs.getString("hora"));
                r.setKm(rs.getString("km"));
                r.setDestino(rs.getString("destino"));                
                r.setDistancia(rs.getString("distancia"));
                r.setOrigem(rs.getString("origem"));
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
