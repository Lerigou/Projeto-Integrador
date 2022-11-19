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
    private JTextField txtSiglaPartido;
    private JTextField numPartido;
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
                    txtSiglaPartido.setText("");
                    numPartido.setText("");
                } else {
                    txtId.setText(Integer.toString(candidato.getIdCandidato()));
                    txtNomeCandidato.setText(candidato.getNomeCandidato());
                    txtNomeVice.setText(candidato.getNomeVice());
                    txtSiglaPartido.setText(candidato.getSiglaPartido());
                    numPartido.setText(Integer.toString(candidato.getNumeroPartido()));
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
        txtNomeCandidato = new JTextField(15);
        adicionarComponente(txtNomeCandidato, 1, 1);

        label = new JLabel("Vice");
        adicionarComponente(label, 2, 0);
        txtNomeVice = new JTextField(15);
        adicionarComponente(txtNomeVice, 2, 1);

        label = new JLabel("Sigla do Partido");
        adicionarComponente(label, 3, 0);
        txtSiglaPartido = new JTextField(15);
        adicionarComponente(txtSiglaPartido, 3, 1);

        label = new JLabel("Número do Partido");
        adicionarComponente(label, 4, 0);
        numPartido = new JTextField(15);
        adicionarComponente(numPartido, 4, 1);

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
                    novoCandidato.setSiglaPartido(txtSiglaPartido.getText());
                    novoCandidato.setNumeroPartido(Integer.parseInt(numPartido.getText()));
//                    txtSiglaPartido.setText(candidato.getSiglaPartido());
//                    novoCandidato.setNumPartido(Integer.parseInt(txtNumPartido.getText()));
//                    numPartido.setText(Integer.toString(candidato.getNumeroPartido()));

                    CandidatoStorage.inserir(novoCandidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this,
                            "Candidato cadastrado com sucesso",
                            "Cadastro Candidato",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    candidato.setNomeCandidato(txtNomeCandidato.getText());
                    candidato.setNomeVice(txtNomeVice.getText());
                    candidato.setSiglaPartido(txtSiglaPartido.getText());
                    candidato.setNumeroPartido(candidato.getNumeroPartido());
//                    txtSiglaPartido.setText(candidato.getSiglaPartido());
//                    candidato.setNumPartido(Integer.parseInt(txtNumPartido.getText()));
//                    numPartido.setText(Integer.toString(candidato.getNumeroPartido()));

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
