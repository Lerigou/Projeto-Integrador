import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CandidatoFormPanel extends JPanel {

    private Janela janela;
    private Candidato candidato;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JTextField txtId;
    private JTextField txtNomeCandidato;
    private JTextField txtNomeVice;
    private JComboBox numPartido;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CandidatoFormPanel(Janela janela){
        this.janela = janela;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (candidato == null){
                    txtId.setText("");
                    txtNomeCandidato.setText("");
                    txtNomeVice.setText("");
                    numPartido.setSelectedItem("");
                } else {
                    txtId.setText(Integer.toString(candidato.getIdCandidato()));
                    txtNomeCandidato.setText(candidato.getNomeCandidato());
                    txtNomeVice.setText(candidato.getNomeVice());
                    numPartido.setSelectedItem(candidato.getPartido());
                }
            }
        });

        gerarForm();

    }

    public void setCandidato(Candidato candidato){
        this.candidato = candidato;
    }

    private void gerarForm(){
        JLabel label;

        label = new JLabel("id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId,0,1);

        label = new JLabel("Candidato");
        adicionarComponente(label, 1, 0);
        txtNomeCandidato = new JTextField(30);
        adicionarComponente(txtNomeCandidato, 1, 2);

        label = new JLabel("Vice");
        adicionarComponente(label, 1, 0);
        txtNomeVice = new JTextField(30);
        adicionarComponente(txtNomeVice, 1, 3);

        label = new JLabel("Partido");
        adicionarComponente(label, 1, 0);
        numPartido = new JComboBox();
        adicionarComponente(numPartido, 1, 4);

        gerarBtns();

    }

    private void gerarBtns() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        gerarBtnSalvar(btnPanel);
        gerarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void gerarBtnSalvar(JPanel btnPanel){
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CandidatoFormPanel.this.candidato == null){
                    Candidato novoCandidato = new Candidato();
                    novoCandidato.setNomeCandidato(txtNomeCandidato.getText());
                    novoCandidato.setNomeVice(txtNomeVice.getText());
//                    novoCandidato.setNumPartido(Integer.parseInt(txtNumPartido.getText()));
                    numPartido.setSelectedItem(candidato.getPartido());

                    CandidatoStorage.inserir(novoCandidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this,
                            "Candidato cadastrado com sucesso",
                            "Cadastro Candidato",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    candidato.setNomeCandidato(txtNomeCandidato.getText());
                    candidato.setNomeVice(txtNomeVice.getText());
//                    candidato.setNumPartido(Integer.parseInt(txtNumPartido.getText()));
                    numPartido.setSelectedItem(candidato.getPartido());

                    CandidatoStorage.atualizar(CandidatoFormPanel.this.candidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this,
                            "Candidato editado com sucesso",
                            "Cadastro candidato",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                janela.mostrarCandidatoListPanel();
            }
        });

        btnPanel.add(btnSalvar);
    }

    private void gerarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna,
                                     int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
}
