import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatoStorage {


    public static boolean inserir(Candidato candidato){

        String query = "INSERT INTO candidato (nomeCandidato, nomeVice, siglapartido, numeroPartido) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, candidato.getNomeCandidato());
            statement.setString(2, candidato.getNomeVice());
            statement.setString(3, candidato.getSiglaPartido());
            statement.setInt(4, candidato.getNumeroPartido());
            statement.execute();

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

        String query = "UPDATE candidato SET nomeCandidato = ?, nomeVice = ?, siglaPartido = ?, numeroPartido = ? WHERE idCandidato = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, candidato.getNomeCandidato());
            statement.setString(2, candidato.getNomeVice());
            statement.setString(3, candidato.getSiglaPartido());
            statement.setInt(4, candidato.getNumeroPartido());
            statement.setInt(5, candidato.getIdCandidato());
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

        // Será q aqui não fica "WHERE idCandidato = ?"?
        String query = "DELETE FROM candidato WHERE idCandidato = ?";

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

        String query = "SELECT * FROM candidato ORDER BY idCandidato";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Candidato candidato = new Candidato();
                candidato.setIdCandidato(resultSet.getInt("idCandidato"));
                candidato.setNomeCandidato(resultSet.getString("nomeCandidato"));
                candidato.setNomeVice(resultSet.getString("nomeVice"));
                candidato.setSiglaPartido(resultSet.getString("siglaPartido"));
                candidato.setNumeroPartido(resultSet.getInt("numeroPartido"));

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

    public static List<Candidato> listarNome(){
        List<Candidato> candidatos = new ArrayList<>();

        String query = "SELECT * FROM candidato ORDER BY idCandidato";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = BddConection.getConexao();

            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Candidato candidato = new Candidato();
                candidato.setNomeCandidato(resultSet.getString("nomeCandidato"));

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
