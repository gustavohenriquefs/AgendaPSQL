package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory unicaInstancia = null;
    private Connection conn = null;

    private ConnectionFactory() {

        try {
            String dbNome = "db_agendaPSQL";
            String nomeUser = "postgres";
            String senha = "12345";
            String endereco = "localhost";

            Class.forName("org.postgresql.Driver");

            this.conn = DriverManager.getConnection("jdbc:postgresql://" + endereco + "/" + dbNome, nomeUser, senha);
        } catch (SQLException e) {
            System.out.println("Error____aa:");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException c) {
            System.out.println("Error____ab:");
            throw new RuntimeException(c);
        }
        
    }

    public static Connection getConnection() {
        
        if (unicaInstancia == null) {
            unicaInstancia = new ConnectionFactory();
            return unicaInstancia.getConn();
        } else {
            return unicaInstancia.getConn();
        }
        
    }

    private Connection getConn() {
        
        return this.conn;
        
    }

}
