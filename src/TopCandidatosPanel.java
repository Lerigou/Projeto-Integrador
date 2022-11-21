import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TopCandidatosPanel extends JPanel {

    private Janela janela;
    private JTable tabela;
    private TopCandidatosTable tableModel;

    public TopCandidatosPanel(Janela janela){
        this.janela = janela;
        setLayout(new BorderLayout());
        gerarTabelaTopPanel();
    }

    private void gerarTabelaTopPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(janela.corPrincipal);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setOpaque(true);

        tableModel = new TopCandidatosTable(CandidatoStorage.listar());
        tabela = new JTable(tableModel);
//      Esse viewPort deixa eu pintar todo o fundo da tabela, e n só as linhas
        tabela.setFillsViewportHeight(true);
//      O Texto da tabela é o foreground
        tabela.setForeground(janela.corContrasteBlue);
        tabela.setShowGrid(true);
        tabela.setRowHeight(20);
        tabela.setBackground(janela.corSecundariaPink);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(janela.corSecundariaBlue);
        headerRenderer.setForeground(janela.corPrincipal);

        for (int i = 0; i < tabela.getModel().getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
}
