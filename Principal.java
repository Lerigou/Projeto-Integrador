import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JPanel {

    private Janela janela;
    private BorderLayout layout;
    private JButton candidatoBtn;
    private JButton pesquisaBtn;

    public Principal(Janela janela){
        this.janela = janela;
        layout = new BorderLayout();
        setLayout(layout);
        criarBtns();
    }

    public void criarBtns(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 100));
        panel.setBackground(janela.corPrincipal);
        criarBtnListaCandidato();
        criarBtnListaPesquisa();
        panel.add(candidatoBtn);
        panel.add(pesquisaBtn);
        add(panel, BorderLayout.CENTER);
    }

    public void criarBtnListaCandidato(){
        candidatoBtn = new JButton("Lista de candidatos");
        candidatoBtn.setBackground(janela.corPrincipal);
        candidatoBtn.setForeground(janela.corSecundariaBlue);

        Border line = new LineBorder(janela.corSecundariaBlue);
        candidatoBtn.setBorder(line);

        candidatoBtn.setPreferredSize(new Dimension(200, 30));

        candidatoBtn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                candidatoBtn.setForeground(janela.corSecundariaPink);
                candidatoBtn.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                candidatoBtn.setForeground(janela.corSecundariaBlue);
                candidatoBtn.setBackground(janela.corPrincipal);
            }
        });
        candidatoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarCandidatoListPanel();
            }
        });
    }

    public void criarBtnListaPesquisa(){
        pesquisaBtn = new JButton("Lista de pesquisas");
        pesquisaBtn.setBackground(janela.corPrincipal);
        pesquisaBtn.setForeground(janela.corSecundariaBlue);

        Border line = new LineBorder(janela.corSecundariaBlue);
        pesquisaBtn.setBorder(line);

        pesquisaBtn.setPreferredSize(new Dimension(200, 30));

        pesquisaBtn.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                pesquisaBtn.setForeground(janela.corSecundariaPink);
                pesquisaBtn.setBackground(janela.corSecundariaBlue);
            }

            public void mouseExited(java.awt.event.MouseEvent e){
                pesquisaBtn.setForeground(janela.corSecundariaBlue);
                pesquisaBtn.setBackground(janela.corPrincipal);
            }
        });
        pesquisaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.mostrarPesquisaListPanel();
            }
        });
    }


}
