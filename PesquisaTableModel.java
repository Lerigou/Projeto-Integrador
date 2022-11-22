import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PesquisaTableModel extends AbstractTableModel {

    // Cria um array chamado pesquisas
    private List<Pesquisa> pesquisas = new ArrayList<>();
    // Configura as colunas das tabelas
    private String[] colunas = new String[]{"id", "porcentagem", "Unidade de Federação", "Data", "Fonte"};

    public PesquisaTableModel(List<Pesquisa> pesquisas){
        this.pesquisas = pesquisas;
    }

    @Override
    public int getRowCount(){
        return pesquisas.size();
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

        Pesquisa pesquisa = pesquisas.get(rowId);

        switch (colId){

            case 0:
                value = Integer.toString(pesquisa.getIdPesquisa());
                break;

            case 1:
                value = Float.toString(pesquisa.getPorcentagem());
                break;

            case 2:
                value = String.valueOf(pesquisa.getUf());
                break;
            case 3:
                value = String.valueOf(pesquisa.getData());
                break;
            case 4:
                value = pesquisa.getFonte();
                break;
            default:
                System.err.printf("[ERRO] Indice de coluna invalido: %d%n", colId);
        }

        return value;
    }

    public void carregar(List<Pesquisa> pesquisas){
        this.pesquisas = pesquisas;
        fireTableDataChanged();
    }

    public Pesquisa getPesquisa(int rowId){
        Pesquisa pesquisa = null;

        pesquisa = pesquisas.get(rowId);
        return pesquisa;
    }

}