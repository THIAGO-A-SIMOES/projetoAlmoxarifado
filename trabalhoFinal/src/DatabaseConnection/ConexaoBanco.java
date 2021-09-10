package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
   
       private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost";
    private static final String PORT = "3306";
    private static final String USUARIO = "teste";
    private static final String SENHA = "teste";
    private static final String BANCO = "almoxarifado";
    private static final String TIMEZONE = "userTimezone=true&serverTimezone=UTC";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String connection = URL + ":" + PORT + "/" + BANCO + "?" + TIMEZONE;
        Class.forName(DRIVER);
        Connection conexao = DriverManager.getConnection(connection, USUARIO, SENHA);
        return conexao;

    }

}
   

