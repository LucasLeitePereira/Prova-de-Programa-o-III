package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame {

    private JButton btnImobiliaria;
    private JButton btnCasa;
    private JButton btnLoja;

    public JanelaPrincipal() {
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridLayout(4, 1, 10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Sistema de Imobiliária", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        btnImobiliaria = new JButton("Incluir Imobiliária");
        btnCasa = new JButton("Incluir Casa");
        btnLoja = new JButton("Incluir Loja");

        Font fonteBotao = new Font("Arial", Font.PLAIN, 14);
        btnImobiliaria.setFont(fonteBotao);
        btnCasa.setFont(fonteBotao);
        btnLoja.setFont(fonteBotao);

        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(btnImobiliaria);
        painelPrincipal.add(btnCasa);
        painelPrincipal.add(btnLoja);

        add(painelPrincipal);

        configurarEventos();
    }

    private void configurarEventos() {
        btnImobiliaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaImobiliaria janela = new JanelaImobiliaria();
                janela.setVisible(true);
            }
        });

        btnCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaCasa janela = new JanelaCasa();
                janela.setVisible(true);
            }
        });

        btnLoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaLoja janela = new JanelaLoja();
                janela.setVisible(true);
            }
        });
    }

    private void configurarJanela() {
        setTitle("Sistema Imobiliária - Menu Principal");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}