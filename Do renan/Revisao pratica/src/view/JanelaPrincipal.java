package view;

import javax.swing.*;
import java.awt.*;

import controller.ImobiliariaController;
import controller.CasaController;
import controller.LojaController;

public class JanelaPrincipal extends JFrame {

    private ImobiliariaController imobiliariaController;
    private CasaController casaController;
    private LojaController lojaController;

    public JanelaPrincipal() {
        super("Sistema de Imóveis");

        imobiliariaController = new ImobiliariaController();
        casaController = new CasaController();
        lojaController = new LojaController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JButton btnImobiliaria = new JButton("Cadastrar Imobiliária");
        JButton btnCasa        = new JButton("Cadastrar Casa");
        JButton btnLoja        = new JButton("Cadastrar Loja");

        btnImobiliaria.addActionListener(e ->
                new JanelaImobiliaria(imobiliariaController).setVisible(true));

        btnCasa.addActionListener(e ->
                new JanelaCasa(casaController).setVisible(true));

        btnLoja.addActionListener(e ->
                new JanelaLoja(lojaController).setVisible(true));

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnImobiliaria);
        add(btnCasa);
        add(btnLoja);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal jp = new JanelaPrincipal();
            jp.setVisible(true);
        });
    }
}
