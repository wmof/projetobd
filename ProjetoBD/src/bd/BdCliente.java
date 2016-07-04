/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import object.Cliente;

/**
 *
 * @author Leto
 */
public class BdCliente {

    public static void insertFisica(Cliente cliente, String cpf) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "INSERT INTO cliente (codigo, nome, telefone, endereco) VALUES ("
                    + cliente.getCodigo() + ", '"
                    + cliente.getNome() + "', '"
                    + cliente.getEndereco() + "', '"
                    + cliente.getTelefone() + "')";
            stmt.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void insertJuridica(Cliente cliente, String cnpj) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "EXECUTE insertClienteJuridica('"
                    + cliente.getCodigo() + "','"
                    + cliente.getNome() + "','"
                    + cliente.getEndereco() + "','"
                    + cliente.getTelefone() + "','"
                    + cnpj + "')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void delete(Cliente cliente) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "DELETE cliente WHERE codigo = " + cliente.getCodigo();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public static void update(Cliente cliente) throws SQLException, IOException {
        try {
            Conexao conn = new Conexao();
            conn.conectar();
            Statement stmt = Conexao.con.createStatement();
            String sql;
            sql = "UPDATE cliente SET"
                    + " nome = '" + cliente.getNome() + "',"
                    + " telefone = '" + cliente.getTelefone() + "',"
                    + " endereco = '" + cliente.getEndereco() + "' "
                    + "WHERE" + " codigo = '" + cliente.getCodigo() + "'";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BDClass Erro:" + e.getMessage());
        }
    }

    public ArrayList<Cliente> select() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Cliente> retorno = new ArrayList<Cliente>();
        String sql;
        sql = "SELECT codigo, nome, telefone, endereco FROM cliente";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            Cliente c = new Cliente();
                c.setCodigo(rs.getString("codigo"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                retorno.add(c);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retorno;
    }
    public ArrayList<Cliente> selectDevedores() throws Exception {
        Conexao conn = new Conexao();
        conn.conectar();
        ArrayList<Cliente> retorno = new ArrayList<Cliente>();
        String sql;
        sql = "SELECT nome, telefone FROM pag_atrasado";
        try {
            Statement stmt = Conexao.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                retorno.add(c);

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return retorno;
    }
}
