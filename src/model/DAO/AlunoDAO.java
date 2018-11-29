/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.BEAN.Aluno;

/**
 *
 * @author Programador-03
 */
public class AlunoDAO {

    Conexao conexao = new Conexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public void create(Aluno a) {
        if (!conexao.obterConexao()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }

        try {
            stmt = conexao.con.prepareStatement("INSERT INTO aluno (tb_nome,tb_sexo,tb_idade,tb_email) VALUES (?,?,?,?)");

            stmt.setString(1, a.getNome());
            stmt.setString(2, String.valueOf(a.getSexo()));
            stmt.setInt(3, a.getIdade());
            stmt.setString(4, a.getEmail());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar no  Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        
        conexao.closeConnection(conexao.con, stmt);

    }

    public void update(Aluno a) {
        if (!conexao.obterConexao()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }

        try {
            stmt = conexao.con.prepareStatement("UPDATE aluno SET tb_nome=?, tb_idade=?, tb_sexo=?, tb_email=?, tb_telefone=? where idAluno=?");

            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getIdade());
            stmt.setString(3, String.valueOf(a.getSexo()));
            stmt.setString(4, a.getEmail());
            stmt.setInt(5, a.getTelefone());
            stmt.setInt(6, a.getIdAluno());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar no Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        conexao.closeConnection(conexao.con, stmt);
    }

    public void delete(Aluno a) {
        
        if (!conexao.obterConexao()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }

        try {
            stmt = conexao.con.prepareStatement("DELETE FROM aluno where idAluno=?");
            stmt.setInt(1, a.getIdAluno());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir no  Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        conexao.closeConnection(conexao.con, stmt);
    }
    
    //Lista de Produtos para exibir na grid jTProdutos
    public List<Aluno> lerAlunosBanco() {

        List<Aluno> alunosList = new ArrayList<>();
        if (!conexao.obterConexao()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar com o Banco de Dados!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }
        try {
            stmt = conexao.con.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("idAluno"));
                aluno.setNome(rs.getString("tb_nome"));
                aluno.setEmail(rs.getString("tb_email"));
                aluno.setTelefone(rs.getInt("tb_telefone"));
                aluno.setIdade(rs.getInt("tb_idade"));

                alunosList.add(aluno);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.closeConnection(conexao.con, stmt, rs);
        }
        return alunosList;
    }

}
