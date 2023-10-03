
import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Compromisso;

public class testDao {

    private Connection con;

    public testDao() {
        this.con = ConnectionFactory.getConnection();
    }

    public ArrayList<Compromisso> getList(Long id) {
        try {
            ArrayList<Compromisso> us = new ArrayList();
            String sql = "SELECT * FROM compromissos WHERE id_user = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setLong(1, id);
            
            ResultSet rs;
            rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Compromisso k = new Compromisso();
                        k.setId(rs.getLong("id_comp"));
                        k.setFk(rs.getLong("id_user"));
                        k.setTitulo(rs.getString("titulo_comp"));
                        k.setDescricao(rs.getString("desc_comp"));
                        k.setData(rs.getString("data_comp"));
                        us.add(k);
                        System.out.println(rs.getLong("id_comp"));
                    }
                    
                    stmt.close();
                    rs.close();
                    //con.close();
                    
                    for (int i = 0; i < us.size(); i++) {
                        System.out.println(us.get(i).getTitulo());
                    }
                    return us;
        } catch (SQLException ex) {
            Logger.getLogger(testDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
