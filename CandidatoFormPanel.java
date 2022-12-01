import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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

        setBackground(janela.corPrincipal);

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

        label = new JLabel("ID");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corSecundariaBlue);
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        txtId.setBackground(janela.corSecundariaBlueLight);
        txtId.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtId,0,2);

        label = new JLabel("Nome Candidato");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corSecundariaBlue);
        adicionarComponente(label, 1, 0);
        txtNomeCandidato = new JTextField(15);
        txtNomeCandidato.setBackground(janela.corSecundariaPink);
        txtNomeCandidato.setForeground(janela.corSecundariaBlue);
        txtNomeCandidato.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtNomeCandidato, 1, 2);

        label = new JLabel("Nome Vice");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corSecundariaBlue);
        adicionarComponente(label, 2, 0);
        txtNomeVice = new JTextField(15);
        txtNomeVice.setBackground(janela.corSecundariaPink);
        txtNomeVice.setForeground(janela.corSecundariaBlue);
        txtNomeVice.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtNomeVice, 2, 2);

        label = new JLabel("Sigla do Partido");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corSecundariaBlue);
        adicionarComponente(label, 3, 0);
        txtSiglaPartido = new JTextField(15);
        txtSiglaPartido.setBackground(janela.corSecundariaPink);
        txtSiglaPartido.setForeground(janela.corSecundariaBlue);
        txtSiglaPartido.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtSiglaPartido, 3, 2);

        label = new JLabel("Número do Partido");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corSecundariaBlue);
        adicionarComponente(label, 4, 0);
        numPartido = new JTextField(15);
        numPartido.setBackground(janela.corSecundariaPink);
        numPartido.setForeground(janela.corSecundariaBlue);
        numPartido.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(numPartido, 4, 2);

        Border line = new LineBorder(janela.corPrincipal);
        txtNomeCandidato.setBorder(line);
        txtNomeVice.setBorder(line);
        txtSiglaPartido.setBorder(line);
        numPartido.setBorder(line);

        gerarBtns();

    }

    private void gerarBtns() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        btnPanel.setBackground(janela.corPrincipal);

        gerarBtnSalvar(btnPanel);
        gerarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 0, 4, 1);
    }

    private void gerarBtnSalvar(JPanel btnPanel){
        btnSalvar = new JButton("Salvar");

        btnSalvar.setBackground(janela.corPrincipal);
        btnSalvar.setForeground(janela.corSecundariaBlue);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 15));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnSalvar.setBorder(line);

        btnSalvar.setPreferredSize(new Dimension(100, 30));

        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnSalvar.setForeground(janela.corSecundariaPink);
                btnSalvar.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnSalvar.setForeground(janela.corSecundariaBlue);
                btnSalvar.setBackground(janela.corPrincipal);
            }
        });
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

        btnCancelar.setBackground(janela.corPrincipal);
        btnCancelar.setForeground(janela.corSecundariaBlue);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));

        Border line = new LineBorder(janela.corSecundariaBlue);
        btnCancelar.setBorder(line);

        btnCancelar.setPreferredSize(new Dimension(100, 30));

        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                btnCancelar.setForeground(janela.corSecundariaPink);
                btnCancelar.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                btnCancelar.setForeground(janela.corSecundariaBlue);
                btnCancelar.setBackground(janela.corPrincipal);
            }
        });

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
