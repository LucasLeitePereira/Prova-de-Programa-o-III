package view;

import javax.swing.*;
import java.awt.*;

import controller.LojaController;

public class JanelaLoja extends JFrame {

    private JTextField txtEndereco;
    private JTextField txtCep;
    private JTextField txtArea;
    private JTextField txtNumPortas;

    private LojaController controller;

    public JanelaLoja(LojaController controller) {
        super("Cadastro de Loja");
        this.controller = controller;

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblEndereco    = new JLabel("Endereço:");
        JLabel lblCep         = new JLabel("CEP (99999-999):");
        JLabel lblArea        = new JLabel("Área (m²):");
        JLabel lblNumPortas   = new JLabel("Nº de portas:");

        txtEndereco   = new JTextField(25);
        txtCep        = new JTextField(10);
        txtArea       = new JTextField(5);
        txtNumPortas  = new JTextField(5);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> salvarLoja());

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(lblCep);
        panel.add(txtCep);
        panel.add(lblArea);
        panel.add(txtArea);
        panel.add(lblNumPortas);
        panel.add(txtNumPortas);
        panel.add(new JLabel());
        panel.add(btnSalvar);

        add(panel, BorderLayout.CENTER);
    }

    private void salvarLoja() {
        try {
            String endereco = txtEndereco.getText();
            String cep      = txtCep.getText();
            int area        = Integer.parseInt(txtArea.getText());
            int numPortas   = Integer.parseInt(txtNumPortas.getText());

            controller.cadastrarLoja(endereco, cep, area, numPortas);

            JOptionPane.showMessageDialog(this,
                    "Loja cadastrada com sucesso!");

            txtEndereco.setText("");
            txtCep.setText("");
            txtArea.setText("");
            txtNumPortas.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Área e número de portas devem ser inteiros.",
                    "Erro de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
