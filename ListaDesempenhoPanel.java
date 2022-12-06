import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ListaDesempenhoPanel extends JPanel{

    private Janela janela;
    private JTable tabela;
    private JButton btnPesquisar;
    private JButton btnVoltar;
    private JTextField txtPesquisa;

    private CandidatoTableModel candidatoTableModel;
    private PesquisaTableModel pesquisaTableModel;

    List<Candidato> candidatos = new ArrayList<>();
    private CandidatoStorage candidatoStorage;
    JComboBox<String> candidatoJComboBox = new JComboBox<String>();

//    private String candidatoSelecionado = candidatoJComboBox.getSelectedItem().toString();

    public ListaDesempenhoPanel(Janela janela){
        this.janela = janela;
        setLayout(new BorderLayout());
        criarBtnComandos();
        gerarCandidatoTabelaPanel();
        gerarPesquisaTabelaPanel();
    }

    private void criarBtnComandos(){
        JPanel panel = new JPanel();
        panel.setBackground(janela.corSecundariaPink);
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        gerarTxtPesquisa();
        panel.add(txtPesquisa);

        gerarBtnVoltar();
        panel.add(btnVoltar);

        add(panel, BorderLayout.WEST);
    }

    private void gerarTxtPesquisa() {
        txtPesquisa = new JTextField();
        txtPesquisa.setPreferredSize(new Dimension(150, 40));
        txtPesquisa.setBackground(janela.corSecundariaPink);
        txtPesquisa.setForeground(janela.corSecundariaBlue);
        txtPesquisa.setFont(new Font("Arial", Font.ITALIC, 20));
        txtPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (txtPesquisa.getText() != null) {
                    pesquisaTableModel.carregar(PesquisaStorage.listarDesempenho(txtPesquisa.getText()));
                }
            }
        });

        txtPesquisa.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                if (txtPesquisa.getText().equals("")){
                    txtPesquisa.setText("Candidato");
                    txtPesquisa.setForeground(janela.corSecundariaBlue);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                if (txtPesquisa.getText().equals("Candidato")){
                    txtPesquisa.setText("");
                    txtPesquisa.setForeground(janela.corSecundariaBlue);
                }
            }
        });
    }

    private void gerarBtnVoltar(){
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(janela.corPrincipal);
        btnVoltar.setForeground(janela.corSecundariaBlue);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 15));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnVoltar.setBorder(line);

        btnVoltar.setPreferredSize(new Dimension(130, 35));

        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnVoltar.setForeground(janela.corSecundariaPink);
                btnVoltar.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnVoltar.setForeground(janela.corSecundariaBlue);
                btnVoltar.setBackground(janela.corPrincipal);
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarInicialPanel();
            }
        });

    }

//    private void gerarComboBox(){
//        candidatoJComboBox.setBackground(janela.corPrincipal);
//        candidatoJComboBox.setForeground(janela.corSecundariaBlue);
//        candidatoJComboBox.setFont(new Font("Arial", Font.BOLD, 20));
//        candidatoJComboBox.setPreferredSize(new Dimension(150, 40));
//        candidatoJComboBox.addItem("Selecione um candidato");
//        candidatoJComboBox.setSelectedIndex(0);
//        popularComboBox();
//
//        for (int i = 0; i < candidatos.size(); i++) {
//            candidatoJComboBox.addItem(candidatos.get(i).getNomeCandidato());
//        }
//
//        Border line = new LineBorder(janela.corPrincipal);
//        candidatoJComboBox.setBorder(line);
//
//        candidatoJComboBox.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (candidatoJComboBox.getSelectedItem() != null) {
//                    pesquisaTableModel.carregar(PesquisaStorage.listarDesempenho(candidatoJComboBox.getName()));
//                }
//            }
//        });
//    }

//    private void popularComboBox(){
//        candidatoStorage = new CandidatoStorage();
//        this.candidatos = candidatoStorage.listarNome();
//    }

    private void gerarCandidatoTabelaPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(janela.corPrincipal);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,220,20,20));
        panel.setSize(762, 300);
        panel.setOpaque(true);

        candidatoTableModel = new CandidatoTableModel(CandidatoStorage.listar());
        tabela = new JTable(candidatoTableModel);
//      Esse viewPort deixa eu pintar todo o fundo da tabela, e n só as linhas
        tabela.setFillsViewportHeight(true);
//      O Texto da tabela é o foreground
        tabela.setForeground(janela.corSecundariaBlue);
        tabela.setFont(new Font("Arial", Font.ITALIC, 15));
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

        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);

    }

    private void gerarPesquisaTabelaPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(janela.corPrincipal);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(300,20,20,20));
        panel.setSize(570, 360);
        panel.setOpaque(true);

        pesquisaTableModel = new PesquisaTableModel(PesquisaStorage.listar());
        tabela = new JTable(pesquisaTableModel);
//      Esse viewPort deixa eu pintar todo o fundo da tabela, e n só as linhas
        tabela.setFillsViewportHeight(true);
//      O Texto da tabela é o foreground
        tabela.setForeground(janela.corSecundariaBlue);
        tabela.setFont(new Font("Arial", Font.ITALIC, 15));
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

        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);

    }

    public void recarregar() {
        pesquisaTableModel.carregar(PesquisaStorage.listar());
        candidatoTableModel.carregar(CandidatoStorage.listar());
    }

}
