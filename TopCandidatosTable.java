import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TopCandidatosTable extends AbstractTableModel {

    private List<Candidato> candidatos = new ArrayList<>();
    private String[] colunas = new String[]{"id", "Nome candidato", "Nome vice", "Nome Partido", "Numero do Partido"};

    public TopCandidatosTable(List<Candidato> candidatos){
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
    public Object getValueAt(int rowIdx, int colIdx){
        String value = null;

        Candidato candidato = candidatos.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(candidato.getIdCandidato());
                break;
            case 1:
                value = candidato.getNomeCandidato();
                break;
            case 2:
                value = candidato.getNomeVice();
                break;
            default:
                System.err.printf("[ERRO] indice de coluna invalido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Candidato> candidatos) {
        this.candidatos = candidatos;
        fireTableDataChanged();
    }

    public Candidato getCandidato(int rowIdx) {
        Candidato candidato = null;

        candidato = candidatos.get(rowIdx);

        return candidato;
    }
}
