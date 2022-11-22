import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class PesquisaStorage {


    public static boolean inserir(Pesquisa pesquisa){

        String query = "INSERT INTO pesquisa (UF, data, fonte) VALUES (?, ?, ?)";

                // String query = "BEGIN;" +
                // "INSERT INTO pesquisa (UF, data,fonte)" +
                // "  VALUES('?', '?',''?);" +
                // "INSERT INTO candidato_pesquisa(candidato_idCandidato,porcentagem)" +
                // "  VALUES('?','?');" +
                // "COMMIT;";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //statement.setString(1,  Double.toString(pesquisa.getPorcentagem()));
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

        String query = "BEGIN;\n" +
                "UPDATE pesquisa SET uf = ?, data = ?, fonte = ? WHERE idPesquisa = ?\";\n" +
                "UPDATE candidato_pesquisa SET candidato_idCandidato = ?, pesquisa_idPesquisaa = ?, porcentagem = ? WHERE idPesquisa = ?;\n" +
                "COMMIT;";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, Double.toString(pesquisa.getPorcentagem()));
            statement.setString(2, String.valueOf(pesquisa.getUf()));
            statement.setDate(3, new java.sql.Date(pesquisa.getData().getTime()));
            statement.setString(4, pesquisa.getFonte());
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

        String query = "DELETE FROM pesquisa WHERE idpesquisa = ?";
        //String query = "DELETE FROM pesquisa JOIN candidato_pesquisa WHERE pesquisa.idcandidato = pesquisa_pesquisa.candidato_idcandidato and candidato.idcandidato = ?";

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

        //String query = "SELECT * FROM pesquisa ORDER BY data";
        // A utilização desse JOIN ta dando erro
        String query = "SELECT * FROM pesquisa p INNER JOIN candidato_pesquisa cp ON p.idpesquisa = cp.pesquisa_idpesquisa ORDER BY data";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pesquisa pesquisa = new Pesquisa();
                //pesquisa.setIdPesquisa(resultSet.getInt("idPesquisa"));
                //pesquisa.setPorcentagem(resultSet.getFloat("porcentagem"));
                pesquisa.setUf(resultSet.getString("UF").charAt(0));
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