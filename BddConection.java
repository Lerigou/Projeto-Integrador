import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConection {

    private static final  String URL = "jdbc:mysql://localhost:3306/pi";
    private static final String USER = "root";
    private static final String SENHA = "root";
//    private static final String SENHA = "1234";

    private static Connection conexao;
    private BddConection() {}

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(URL, USER, SENHA);
        }
        return conexao;
    }

} // fim da classe BddConection
