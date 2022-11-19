import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JPanel {

    private Janela frame;
    private BorderLayout layout;

    private JButton candidatoBtn;
    private JButton pesquisaBtn;

    public Principal(Janela janela){
        this.frame = janela;
        layout = new BorderLayout();
        setLayout(layout);
        criarTopCandidatosTable();
        criarBtns();

    }

    public void criarTopCandidatosTable(){
        frame.mostrarTopCandidatosTable();
    }

    public void criarBtns(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 100));
        panel.setBackground(Color.white);
        criarBtnListaCandidato();
        criarBtnListaPesquisa();
        panel.add(candidatoBtn);
        panel.add(pesquisaBtn);
        add(panel, BorderLayout.CENTER);
    }

    public void criarBtnListaCandidato(){
        candidatoBtn = new JButton("Lista de candidatos");
        candidatoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarCandidatoListPanel();
            }
        });
    }

    public void criarBtnListaPesquisa(){
        pesquisaBtn = new JButton("Lista de pesquisas");
        pesquisaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPesquisaListPanel();
            }
        });
    }


}
