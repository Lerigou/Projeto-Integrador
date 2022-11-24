import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class PesquisaFormPanel extends JPanel {

    private Janela janela;
    private Pesquisa pesquisa;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JTextField txtId;
    private JTextField txtUF;
    private JTextField txtData;
    private JTextField txtFonte;
    private JTextField txtPorcentagem;;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public PesquisaFormPanel(Janela janela){
        this.janela = janela;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setBackground(janela.corPrincipal);

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (pesquisa == null){
                    txtId.setText("");
                    txtUF.setText("");
                    txtData.setText("");
                    txtFonte.setText("");
                } else {
                    txtId.setText(Integer.toString(pesquisa.getIdPesquisa()));
                    txtUF.setText(String.valueOf(pesquisa.getUf()));
//                    Transformando date em string
                    txtData.setText(String.valueOf(pesquisa.getData()));
                    txtFonte.setText(pesquisa.getFonte());
                }
            }
        });

        gerarForm();

    }

    public void setPesquisa(Pesquisa pesquisa){
        this.pesquisa = pesquisa;
    }

    private void gerarForm(){
        JLabel label;

        label = new JLabel("ID");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corContrasteBlue);
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        txtId.setBackground(janela.corSecundariaBlueLight);
        txtId.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtId,0,2);

        label = new JLabel("Porcentagem");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corContrasteBlue);
        adicionarComponente(label, 1, 0);
        txtPorcentagem = new JTextField(5);
        txtPorcentagem.setEditable(false);
        txtPorcentagem.setBackground(janela.corSecundariaBlueLight);
        txtPorcentagem.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtPorcentagem,1,2);

        label = new JLabel("Unidade de Federação");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corContrasteBlue);
        adicionarComponente(label, 2, 0);
        txtUF = new JTextField(15);
        txtUF.setBackground(janela.corSecundariaPink);
        txtUF.setForeground(janela.corContrasteBlue);
        txtUF.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtUF, 2, 2);

        label = new JLabel("Data");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corContrasteBlue);
        adicionarComponente(label, 3, 0);
        txtData = new JTextField(15);
        txtData.setBackground(janela.corSecundariaPink);
        txtData.setForeground(janela.corContrasteBlue);
        txtData.setFont(new Font("Arial", Font.BOLD, 15));

        txtData.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                if (txtData.getText().equals("")){
                    txtData.setText("XXXX-XX-XX");
                    txtData.setForeground(janela.corContrasteBlue);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                if (txtData.getText().equals("XXXX-XX-XX")){
                    txtData.setText("");
                    txtData.setForeground(janela.corSecundariaBlueLight);
                }
            }
        });

        adicionarComponente(txtData, 3, 2);

        label = new JLabel("Fonte");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(janela.corContrasteBlue);
        adicionarComponente(label, 4, 0);
        txtFonte = new JTextField(15);
        txtFonte.setBackground(janela.corSecundariaPink);
        txtFonte.setForeground(janela.corContrasteBlue);
        txtFonte.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarComponente(txtFonte, 4, 2);

        Border line = new LineBorder(janela.corPrincipal);
        txtUF.setBorder(line);
        txtData.setBorder(line);
        txtFonte.setBorder(line);

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
                if (PesquisaFormPanel.this.pesquisa == null){
                    Pesquisa novaPesquisa = new Pesquisa();
//                   charAt(0) pra pegar o primeiro char da String
                    novaPesquisa.setUf((txtUF.getText()).charAt(0));
//                    transformando string em date
                    novaPesquisa.setData(Date.valueOf(txtData.getText()));
                    novaPesquisa.setFonte(txtFonte.getText());
                   // novaPesquisa.setPorcentagem(txtPorcentagem.getText());
                    PesquisaStorage.inserir(novaPesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this,
                            "Pesquisa cadastrado com sucesso",
                            "Cadastro Pesquisa",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pesquisa.setUf((txtUF.getText()).charAt(0));
                    pesquisa.setData(Date.valueOf(txtData.getText()));
                    pesquisa.setFonte(txtFonte.getText());

                    PesquisaStorage.atualizar(PesquisaFormPanel.this.pesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this,
                            "Pesquisa editado com sucesso",
                            "Cadastro Pesquisa",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                janela.mostrarPesquisaListPanel();
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
                janela.mostrarPesquisaListPanel();
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
