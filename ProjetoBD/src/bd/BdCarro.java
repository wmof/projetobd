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

/**
 *
 * @author Leto
 */
public class BdCarro {
    
    public static void insert(Carro carro) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "INSERT INTO carro (placa, marca, modelo, cliente_codigo) VALUES ('"
                    + carro.getPlaca() + "','"
                    + carro.getMarca() + "','"
                    + carro.getModelo() + "','"
                    + carro.getCliente().getCodigo() + "')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void delete(Carro carro) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "DELETE carro WHERE placa = '" + carro.getPlaca() + "'";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void update(Carro carro) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "UPDATE carro SET"
                    + " marca = '" + carro.getMarca() + "', "
                    + " modelo = '" + carro.getModelo() + "' "
                    + "WHERE " + "placa = '" + carro.getPlaca() + "'";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public ArrayList<Carro> select() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Carro> retorno = new ArrayList<Carro>();
        String sql;
        sql = "SELECT placa, marca, modelo, cliente_codigo FROM carro";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Carro c = new Carro();
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getString("cliente_codigo"));
                c.setCliente(cli);
                retorno.add(c);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retorno;
    }
}
