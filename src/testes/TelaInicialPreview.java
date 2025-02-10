package testes;
import view.TelaInicialView;
import javax.swing.*;
import java.awt.*;

public class TelaInicialPreview {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria uma instância da TelaInicialView
            TelaInicialView tela = new TelaInicialView("Tela Inicial",false);

            // Configura a visualização
            tela.setLocationRelativeTo(null);  // Centraliza na tela
            tela.setVisible(true);

            // Imprime informações úteis para debug
            System.out.println("=== Informações da Tela ===");
            System.out.println("Tamanho: " + tela.getSize());
            System.out.println("Posição: " + tela.getLocation());
            imprimirHierarquiaComponentes(tela, 0);
        });
    }

    // Método auxiliar para visualizar a hierarquia de componentes
    private static void imprimirHierarquiaComponentes(Component componente, int nivel) {
        String indentacao = "  ".repeat(nivel);

        System.out.println(indentacao + componente.getClass().getSimpleName() +
                " [" + componente.getWidth() + "x" + componente.getHeight() + "]");

        if (componente instanceof Container) {
            Container container = (Container) componente;
            for (Component filho : container.getComponents()) {
                imprimirHierarquiaComponentes(filho, nivel + 1);
            }
        }
    }
}