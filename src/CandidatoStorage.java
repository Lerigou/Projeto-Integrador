import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoStorage {


    public static boolean inserir(Candidato candidato){

        String query = "INSERT INTO candidato (nomeCandidato, nomeVice, partido, numPartido) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, candidato.getNomeCandidato());
            statement.setString(2, candidato.getNomeVice());
            statement.setInt(3, candidato.getPartido());
            statement.setInt(4, candidato.getIdCandidato());

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                candidato.setIdCandidato(resultSet.getInt(1));
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

    public static boolean atualizar(Candidato candidato){

        String query = "UPDATE candidato SET nomeCandidato = ?, nomeVice = ?, partido = ? WHERE idCandidato = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, candidato.getIdCandidato());
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

    public static boolean remover(Candidato candidato) {

        String query = "DELETE FROM candidato WHERE id = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, candidato.getIdCandidato());
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

    public static List<Candidato> listar(){
        List<Candidato> candidatos = new ArrayList<>();

        String query = "SEELCT * FROM canddiato ORDER BY idCandiato";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Candidato candidato = new Candidato();
                candidato.setIdCandidato(resultSet.getInt("id"));
                candidato.setNomeCandidato(resultSet.getString("nome"));
                candidato.setNomeVice(resultSet.getString("vice"));
                candidato.setPartido(resultSet.getInt("Partido"));

                candidatos.add(candidato);
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

        return candidatos;
    }

}
