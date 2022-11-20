//testando branch
import javax.swing.SwingUtilities;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mostrarGUI();
            }
        });
    }

    public static void mostrarGUI(){
        Janela frame = new Janela();
        frame.mostrar();
    }
}