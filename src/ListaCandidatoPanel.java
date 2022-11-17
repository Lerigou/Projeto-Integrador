import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    private CandidatoTableModel tableModel;

    public ListaCandidatoPanel(Janela janela){
        this.janela = janela;

        setLayout(new BorderLayout());


        criarBtnComandos();
        gerarTabelaPanel();
    }

    private void criarBtnComandos(){
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);

        gerarBtnAdicionar();
        panel.add(btnAdd);

        gerarBtnEditar();
        panel.add(btnEditar);

        gerarBtnExcluir();
        panel.add(btnExcluir);

        add(panel, BorderLayout.NORTH);
        desabilitarBtns();

    }

    private void gerarBtnAdicionar(){
        btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoForm(null);
            }
        });
    }

    private void gerarBtnEditar(){
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoForm(tableModel.getCandidato(tabela.getSelectedRow()));
            }
        });
    }

    private void gerarBtnExcluir(){
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidato = tableModel.getCandidato(tabela.getSelectedRow());
                int resposta = JOptionPane.showConfirmDialog(ListaCandidatoPanel.this, "O candidato será apagado. Tem certeza?", "Excluir candidato", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION){
                    CandidatoStorage.remover(candidato);
                    recarregar();
                }
            }
        });
    }

    private void gerarTabelaPanel() {
        JPanel panel = new JPanel();

        tableModel = new CandidatoTableModel(CandidatoStorage.listar());
        tabela = new JTable(tableModel);
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
    }

    private void desabilitarBtns(){
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    public void recarregar(){
        tableModel.carregar(CandidatoStorage.listar());
    }


}
