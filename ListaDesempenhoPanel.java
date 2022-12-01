import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaDesempenhoPanel extends JPanel{

    private Janela janela;
    private JTable tabela;
    private JButton btnPesquisar;
    private JButton btnVoltar;

    private CandidatoTableModel candidatoTableModel;
    private PesquisaTableModel pesquisaTableModel;
//    private List<Candidato> candidatos = new ArrayList<Candidato>();
    private CandidatoStorage candidatoStorage;

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

        gerarBtnPesquisar();
        panel.add(btnPesquisar);

        gerarBtnVoltar();
        panel.add(btnVoltar);

        gerarComboBox();

        add(panel, BorderLayout.WEST);
    }

    private void gerarBtnPesquisar(){
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBackground(janela.corPrincipal);
        btnPesquisar.setForeground(janela.corSecundariaBlue);
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 18));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnPesquisar.setBorder(line);

        btnPesquisar.setPreferredSize(new Dimension(150, 40));

        btnPesquisar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnPesquisar.setForeground(janela.corSecundariaPink);
                btnPesquisar.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnPesquisar.setForeground(janela.corSecundariaBlue);
                btnPesquisar.setBackground(janela.corPrincipal);
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

    private void gerarComboBox(){
        JComboBox<String> candidatoJComboBox = new JComboBox<String>();
        candidatoJComboBox.setBackground(janela.corSecundariaPink);
        candidatoJComboBox.setForeground(janela.corContrasteBlue);
        candidatoJComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        candidatoJComboBox.setPreferredSize(new Dimension(150, 40));
        candidatoJComboBox.addItem("Selecione um candidato");
        candidatoJComboBox.setSelectedIndex(0);
//        popularComboBox();
//
//        for (int i = 0; i < candidatos.size(); i++) {
//            candidatoJComboBox.addItem(candidatos.get(i).getNomeCandidato());
//        }

        Border line = new LineBorder(janela.corPrincipal);
        candidatoJComboBox.setBorder(line);
    }

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

}
