import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class ListaCandidatoPanel extends JPanel {

    private Janela janela;
    private JTable tabela;
    private JButton btnAdd;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnVoltar;
    private CandidatoTableModel tableModel;

    public ListaCandidatoPanel(Janela janela){
        this.janela = janela;

        setLayout(new BorderLayout());
        criarBtnComandos();
        gerarTabelaPanel();

    }

    private void criarBtnComandos(){
        JPanel panel = new JPanel();
        panel.setBackground(janela.corSecundariaPink);
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        gerarBtnAdicionar();
        panel.add(btnAdd);

        gerarBtnEditar();
        panel.add(btnEditar);

        gerarBtnExcluir();
        panel.add(btnExcluir);

        gerarBtnVoltar();
        panel.add(btnVoltar);

        add(panel, BorderLayout.WEST);
        desabilitarBtns();

    }

    private void gerarBtnAdicionar(){
        btnAdd = new JButton("Adicionar");
        btnAdd.setBackground(janela.corPrincipal);
        btnAdd.setForeground(janela.corSecundariaBlue);
        btnAdd.setFont(new Font("Arial", Font.BOLD, 18));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnAdd.setBorder(line);

        btnAdd.setPreferredSize(new Dimension(150, 40));

        btnAdd.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnAdd.setForeground(janela.corSecundariaPink);
                btnAdd.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnAdd.setForeground(janela.corSecundariaBlue);
                btnAdd.setBackground(janela.corPrincipal);
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoForm(null);
            }
        });
    }

    private void gerarBtnEditar(){
        btnEditar = new JButton("Editar");
        btnEditar.setBackground(janela.corPrincipal);
        btnEditar.setForeground(janela.corSecundariaBlue);
        btnEditar.setPreferredSize(new Dimension(100, 30));
        btnEditar.setFont(new Font("Arial", Font.BOLD, 18));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnEditar.setBorder(line);

        btnEditar.setPreferredSize(new Dimension(150, 40));


        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoForm(tableModel.getCandidato(tabela.getSelectedRow()));
            }
        });
    }

    private void gerarBtnExcluir(){
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(janela.corPrincipal);
        btnExcluir.setForeground(janela.corSecundariaBlue);
        btnExcluir.setPreferredSize(new Dimension(100, 30));
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 18));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnExcluir.setBorder(line);

        btnExcluir.setPreferredSize(new Dimension(150, 40));


        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidato = tableModel.getCandidato(tabela.getSelectedRow());

                int resposta = JOptionPane.showConfirmDialog(ListaCandidatoPanel.this, "O candidato ser?? apagado. Tem certeza?", "Excluir candidato", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION) {
                    if (!CandidatoStorage.remover(candidato)) {
                        JOptionPane.showMessageDialog(ListaCandidatoPanel.this,
                                "Este candidato n??o pode ser exclu??do pois existem pesquisas cadastradas para ele! " + "\n" +
                                        "Exclua as pesquisas e volte aqui depois.",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ListaCandidatoPanel.this,
                                "Candidato exclu??do com sucesso!",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE);
                        recarregar();
                    }
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

    private void gerarTabelaPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(janela.corPrincipal);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setOpaque(true);

        tableModel = new CandidatoTableModel(CandidatoStorage.listar());
        tabela = new JTable(tableModel);
//      Esse viewPort deixa eu pintar todo o fundo da tabela, e n s?? as linhas
        tabela.setFillsViewportHeight(true);
//      O Texto da tabela ?? o foreground
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

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if (!e.getValueIsAdjusting()){
                    if (tabela.getSelectedRow() >= 0){
                        habilitarBtns();
                    } else {
                        desabilitarBtns();
                    }
                }
            }
        });

    JScrollPane scrollPane = new JScrollPane(tabela);
    panel.add(scrollPane);

    add(panel, BorderLayout.CENTER);

    }

    private void habilitarBtns(){
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);

        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnExcluir.setForeground(janela.corSecundariaPink);
                btnExcluir.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnExcluir.setForeground(janela.corSecundariaBlue);
                btnExcluir.setBackground(janela.corPrincipal);
            }
        });

        btnEditar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnEditar.setForeground(janela.corSecundariaPink);
                btnEditar.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnEditar.setForeground(janela.corSecundariaBlue);
                btnEditar.setBackground(janela.corPrincipal);
            }
        });
    }

    private void desabilitarBtns(){
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    public void recarregar(){
        tableModel.carregar(CandidatoStorage.listar());
    }


}
