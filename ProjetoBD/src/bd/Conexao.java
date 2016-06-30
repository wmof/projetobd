/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.net.PasswordAuthentication;
import java.sql.*;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Leto
 */
public class Conexao {

    // declaracao de variaveis
    public static Connection con;
    public static String url;
    public static String user;
    public static String password;

    /**
     * @param args the command line arguments
     */
    public void conectar() {
        // TODO code application logic here

        url = "jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:Instance01"; //string de conexÃ£o para o ORACLE
        user = "u_wmof"; //nome do usuário
        password = "shdnaahs"; //senha
        try {
            OracleDataSource ds = new OracleDataSource(); //nova fonte de dados
            ds.setURL(url); //passa a URL para a conexao
            ds.getConnection(user, password);//recebe usuario e senha
            con = ds.getConnection(user, password);//conexao recebe os parametros de usuario e senha
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexao Erro"+e.getMessage());
        }

    }
}
