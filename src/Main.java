import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.Candidato();
    }

    public void Candidato(){
        JFrame frame = new Candidato("Lista de candidatos");
        frame.setVisible(true);
    }
}