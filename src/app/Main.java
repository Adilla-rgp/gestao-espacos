package app;
import controller.FluxoUsuarioController;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new FluxoUsuarioController();
        });
    }
}