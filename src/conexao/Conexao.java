/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Programador-03
 */
public class Conexao {

    public Connection con = null;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BANCO = "cad_alunos";
    private static final String USER = "root";
    private static final String PASS = "root";

    public boolean obterConexao() {
        try {
            Class.forName(DRIVER);
            try {
                this.con = DriverManager.getConnection(URL + BANCO, USER, PASS);
//                JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!");
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "OCORREU O SEGUINTE ERRO:\n" + ex, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "OCORREU O SEGUINTE ERRO:\n" + ex, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "OCORREU O SEGUINTE ERRO:\n" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
