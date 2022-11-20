import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class PesquisaStorage {


    public static boolean inserir(Pesquisa pesquisa){

        String query = "INSERT INTO pesquisa (uf, data, fonte) VALUES (?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, String.valueOf(pesquisa.getUf()));
            statement.setDate(2, new java.sql.Date(pesquisa.getData().getTime()));
            statement.setString(3, pesquisa.getFonte());
            statement.execute();

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                pesquisa.setIdPesquisa(resultSet.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean atualizar(Pesquisa pesquisa){

        String query = "UPDATE pesquisa SET uf = ?, data = ?, fonte WHERE idPesquisa = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, String.valueOf(pesquisa.getUf()));
            statement.setDate(2, new java.sql.Date(pesquisa.getData().getTime()));
            statement.setString(3, pesquisa.getFonte());
            statement.execute();

        } catch (SQLException e ) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean remover(Pesquisa pesquisa) {

        // Será q aqui não fica "WHERE idCandidato = ?"?
        String query = "DELETE FROM candidato WHERE idPesquisa = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, pesquisa.getIdPesquisa());
            statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * @return
     */
    public static List<Pesquisa> listar(){
        List<Pesquisa> pesquisas = new ArrayList<>();

        String query = "SELECT * FROM pesquisa ORDER BY data";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pesquisa pesquisa = new Pesquisa();
            
                pesquisa.setIdPesquisa(resultSet.getInt("idPesquisa"));
                pesquisa.setUf(String.valueOf(resultSet.getString()));
                pesquisa.setData(resultSet.getDate("data"));
                pesquisa.setFonte(resultSet.getString("fonte"));

                pesquisas.add(pesquisa);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pesquisas;
    }

}
