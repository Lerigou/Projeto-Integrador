import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {

    public static final String titulo = "CRUD";
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Principal principal;

    private ListaCandidatoPanel listaCandidatoPanel;
    private CandidatoFormPanel formCandidatoPanel;



    public Janela(){
        super(titulo);
//        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);
        criarCards();
    }

    public void mostrar() {
        pack();
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        mostrarInicialPanel();
    }

    public void mostrarInicialPanel(){
        cardLayout.show(cardPanel, Principal.class.getName());
    }

    public void mostrarCandidatoListPanel(){
        listaCandidatoPanel.recarregar();
        cardLayout.show(cardPanel, ListaCandidatoPanel.class.getName());
    }

    public void mostrarPesquisaListPanel(){
        listaCandidatoPanel.recarregar();
        cardLayout.show(cardPanel, ListaPesquisaPanel.class.getName());
    }


    public void mostrarCandidatoForm(Candidato candidato) {
        formCandidatoPanel.setCandidato(candidato);
        cardLayout.show(cardPanel, CandidatoFormPanel.class.getName());
    }

    public void mostrarTopCandidatosTable(){
        listaCandidatoPanel = new ListaCandidatoPanel(this);
        cardPanel.add(listaCandidatoPanel, TopCandidatosTable.class.getName());
    }

    private void criarCards(){
        principal = new Principal(this);
        cardPanel.add(principal, Principal.class.getName());

        listaCandidatoPanel = new ListaCandidatoPanel(this);
        cardPanel.add(listaCandidatoPanel, ListaCandidatoPanel.class.getName());

        formCandidatoPanel = new CandidatoFormPanel(this);
        cardPanel.add(formCandidatoPanel, CandidatoFormPanel.class.getName());

    }

}
