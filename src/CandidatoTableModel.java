import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CandidatoTableModel extends AbstractTableModel {

    // Cria um array chamado candidatos
    private List<Candidato> candidatos = new ArrayList<>();
    // Configura as colunas das tabelas
    private String[] colunas = new String[]{"id", "nome", "vice", "partido"};

    public CandidatoTableModel(List<Candidato> candidatos){
        this.candidatos = candidatos;
    }

    @Override
    public int getRowCount(){
        return candidatos.size();
    }

    @Override
    public int getColumnCount(){
        return colunas.length;
    }

    @Override
    public String getColumnName(int colId){
        String colName = null;
        colName = colunas[colId];
        return colName;
    }

    @Override
    public Object getValueAt(int rowId, int colId){
        String value = null;

        Candidato candidato = candidatos.get(rowId);

        switch (colId){

            case 0:
                value = Integer.toString(candidato.getIdCandidato());
                break;

            case 1:
                value = candidato.getNomeCandidato();
                break;
            case 2:
                value = candidato.getNomeVice();
                break;
            case 3:
                value = Integer.toString(candidato.getPartido());
                break;
            default:
                System.err.printf("[ERRO] Indice de coluna invalido: %d%n", colId);
        }

        return value;
    }

    public void carregar(List<Candidato> candidatos){
        this.candidatos = candidatos;
        fireTableDataChanged();
    }

    public Candidato getCandidato(int rowId){
        Candidato candidato = null;

        candidato = candidatos.get(rowId);
        return candidato;
    }

}
