package DAO;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Compromisso;

public class CompromissoDAO {

    private Connection con;

    public CompromissoDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    public boolean add(Compromisso c) {
        String sql = "INSERT INTO compromissos(id_user, titulo_comp, desc_comp, data_comp) VALUES( ?, ?, ?, ?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, c.getFk());
            stmt.setString(2, c.getTitulo());
            stmt.setString(3, c.getDescricao());
            stmt.setString(4, c.getData());
            stmt.execute();
            stmt.close();
            //con.close();
            System.out.println("Compromisso adicionado");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // listar os compromissos agendados;
    public ArrayList<Compromisso> getList(Long id) {

        ArrayList<Compromisso> us = new ArrayList();
        String sql = "SELECT * FROM compromissos WHERE id_user = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            try ( ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Compromisso k = new Compromisso();
                    k.setId(rs.getLong("id_comp"));
                    k.setFk(rs.getLong("id_user"));
                    k.setTitulo(rs.getString("titulo_comp"));
                    k.setDescricao(rs.getString("desc_comp"));
                    k.setData(rs.getString("data_comp"));
                    us.add(k);
                }

                stmt.close();
                rs.close();
                //con.close();

            } catch (SQLException ex) {
// mostra o erro que pareceu;
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompromissoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < us.size(); i++) {
            System.out.println(us.get(i).getTitulo());
        }
        return us;
    }

    public boolean deletarCompromisso(Long id_user, Long id_comp) {

        String sql = "DELETE FROM compromissos WHERE id_user = ? AND id_comp = ?;";
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id_user);
            stmt.setLong(2, id_comp);
            stmt.execute();
            stmt.close();
            //con.close();
            System.out.println("deletado");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
