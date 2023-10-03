package DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public boolean add(Usuario k) {
        String sql = "INSERT INTO users(nome_user, email_user, senha_user) VALUES (?,?,?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, k.getNome());
            stmt.setString(2, k.getEmail());
            stmt.setString(3, k.getSenha());
            stmt.execute();
            stmt.close();
            //con.close();
            System.out.println("itini san arigató");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean update(Usuario k) {
        String sql = "UPDATE users SET nome = ?, email_user = ?, senha_user = ? WHERE id = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, k.getNome());
            stmt.setString(2, k.getEmail());
            stmt.setString(3, k.getSenha());
            stmt.setLong(4, k.getId());
            stmt.execute();
            stmt.close();
            //con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(Usuario k) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, k.getId());
            stmt.close();
            //con.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Usuario> getList() {

        List<Usuario> us = new ArrayList();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            // next(): enquanto tiver 
            // um pega o próximo valor,
            // se não, para;

            while (rs.next()) {
                Usuario k = new Usuario();
                k.setId(rs.getLong("id_user"));
                k.setNome(rs.getString("nome_user"));
                k.setEmail(rs.getString("email_user"));
                k.setSenha(rs.getString("senha_user"));
                us.add(k);
            }

            stmt.close();
            rs.close();
            //con.close();

        } catch (SQLException ex) {
            // mostra o erro que pareceu;
            ex.printStackTrace();
            return null;
        }

        return us;
    }

    void voltar() {

    }

    public Usuario verificarUser(Usuario k) {

        try {
            String sql = "SELECT * FROM users WHERE nome_user = ? AND senha_user = ?;";
    // abrir
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, k.getNome());
            stmt.setString(2, k.getSenha());

            ResultSet rs = stmt.executeQuery();
            Usuario z = new Usuario();
            while (rs.next()) {
                z.setId(rs.getLong("id_user"));
                z.setNome(rs.getString("nome_user"));
                z.setEmail(rs.getString("email_user"));
                z.setSenha(rs.getString("senha_user"));
            }
// fechar
            stmt.close();
            rs.close();
            //con.close(); 
            return z;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
    }
    public Usuario verificarUserCad(Usuario k) {

        try {
            String sql = "SELECT * FROM users WHERE nome_user = ?;";
// abrir
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, k.getNome());

            ResultSet rs = stmt.executeQuery();
            Usuario z = new Usuario();
            while (rs.next()) {
                z.setId(rs.getLong("id_user"));
                z.setNome(rs.getString("nome_user"));
                z.setEmail(rs.getString("email_user"));
                z.setSenha(rs.getString("senha_user"));
            }
// fechar
            stmt.close();
            rs.close();
            //con.close(); 
            return z;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;

        }
    }
}
