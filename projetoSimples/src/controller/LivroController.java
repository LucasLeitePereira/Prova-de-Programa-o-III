package controller;

import view.JanelaCadastroLivro;
import javax.swing.SwingUtilities;


public class LivroController {

    public static void main(String[] args) {
        // Inicia a interface gráfica na thread correta
        SwingUtilities.invokeLater(() -> {
            JanelaCadastroLivro janela = new JanelaCadastroLivro();
            janela.setVisible(true);
        });
    }
}