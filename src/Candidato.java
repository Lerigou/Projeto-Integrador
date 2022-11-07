import javax.swing.*;
import java.awt.*;

public class Candidato extends Janela{

    private String nome;
    private String vice;
    private String partido;
    private int numPartido;

    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private static final Insets FIELD_INSETS = new Insets(5, 10, 0, 0);

    public Candidato(String title){
        super(title);

        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();

        elementosCandidatos();
        btnCandidatos();
    }

    private void elementosCandidatos(){
        JLabel label;
        JSeparator separator;
        JScrollPane scrollPane;

        separator = new JSeparator();
        addComponent(separator, 16, 0, 0, 1);

        label = new JLabel("Table:");
        // Coloca esse label na coluna 0, a[i fica um do ladinho do outro
        addComponent(label, 18, 0, 1, 1);
        String[] columnNames = { "Nome", "Vice", "Numero" };
        String[][] data = {
                { "Lula", "Alckmin", "13" },
                { "Bolsonaro", "Mourao", "22" },
                { "War", "Gabriel", "11" },
                { "Thomas", "Dshock", "14" }
        };
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(
                new Dimension(table.getPreferredSize().width, table.getRowHeight() * 5));
        scrollPane = new JScrollPane(table);
        // Coloca o negócio na linha 18 na coluna 1
        addComponent(scrollPane, 18, 1, 3, 3);
    }

    private void btnCandidatos(){
        JButton btn;

        btn = new JButton("Cadastrar candidato");
        adicionarComponente(btn, 0,0,1,1);

        btn = new JButton("Excluir candidato");
        adicionarComponente(btn, 0,1,1,1);
    }

    private void adicionarComponente(JComponent componente, int linha, int coluna, int largura, int altura){
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        layout.setConstraints(componente, constraints);
        add(componente);
    }

    private void addComponent(JComponent c, int linha, int coluna, int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = FIELD_INSETS;
        constraints.anchor = GridBagConstraints.WEST;

        layout.setConstraints(c, constraints);
        add(c);
    }
}
