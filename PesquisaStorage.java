import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisaStorage {


    public static boolean inserir(Pesquisa pesquisa){

        String query = "INSERT INTO pesquisa (porcentagem, UF, data, fonte, candidato_idCandidato) VALUES(?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setFloat(1, pesquisa.getPorcentagem());
            statement.setString(2, String.valueOf(pesquisa.getUf()));
            statement.setDate(3, new java.sql.Date(pesquisa.getData().getTime()));
            statement.setString(4, pesquisa.getFonte());
            
            
            statement.setInt(5, pesquisa.getIdCandidato()); //erro da marcia: Erro no parametro 5

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

        //String query = "UPDATE pesquisa SET porcentagem = ?, UF = ?, data = ?, fonte = ?, Candidato_idCandidato = ? WHERE idPesquisa = ?;";
        String query = "UPDATE pesquisa SET porcentagem = ?, UF = ?, data = ?, fonte = ?, Candidato_idCandidato = ? WHERE idPesquisa = ?;";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, Double.toString(pesquisa.getPorcentagem()));
            statement.setString(2, String.valueOf(pesquisa.getUf()));
            statement.setDate(3, new java.sql.Date(pesquisa.getData().getTime()));
            statement.setString(4, pesquisa.getFonte());
            statement.setInt(5, pesquisa.getIdCandidato());
            statement.setInt(6, pesquisa.getIdPesquisa());
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
        String query = "SELECT p.idPesquisa, p.porcentagem, p.UF, p.data, p.fonte, c.nomeCandidato FROM pesquisa p JOIN candidato c ON p.Candidato_idCandidato = c.idCandidato ORDER BY p.data DESC";

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
                pesquisa.setPorcentagem(resultSet.getFloat("Porcentagem"));

                pesquisa.setUf(resultSet.getString("UF"));
                pesquisa.setData(resultSet.getDate("Data"));
                pesquisa.setFonte(resultSet.getString("Fonte"));
                pesquisa.setNomeCandidato(resultSet.getString("nomeCandidato"));

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

    public static List<Pesquisa> listarDesempenho(){
        List<Pesquisa> pesquisas = new ArrayList<>();
        String query = "SELECT p.idPesquisa, p.porcentagem, p.UF, p.data, p.fonte, c.nomeCandidato FROM pesquisa p JOIN candidato c ON p.Candidato_idCandidato = c.idCandidato ORDER BY p.data DESC";

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
                pesquisa.setPorcentagem(resultSet.getFloat("Porcentagem"));

                pesquisa.setUf(resultSet.getString("UF"));
                pesquisa.setData(resultSet.getDate("Data"));
                pesquisa.setFonte(resultSet.getString("Fonte"));
                pesquisa.setNomeCandidato(resultSet.getString("nomeCandidato"));

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

    public static List<Pesquisa> listarDesempenho(String nome){
        List<Pesquisa> pesquisas = new ArrayList<>();
        String query = "SELECT p.idPesquisa, p.porcentagem, p.UF, p.data, p.fonte, c.nomeCandidato FROM pesquisa p JOIN candidato c ON p.Candidato_idCandidato = c.idCandidato WHERE c.nomeCandidato like '" + nome + "%' ORDER BY p.data DESC";

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
                pesquisa.setPorcentagem(resultSet.getFloat("Porcentagem"));

                pesquisa.setUf(resultSet.getString("UF"));
                pesquisa.setData(resultSet.getDate("Data"));
                pesquisa.setFonte(resultSet.getString("Fonte"));
                pesquisa.setNomeCandidato(resultSet.getString("nomeCandidato"));

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
