package view;

import javax.swing.*;
import java.awt.*;

import controller.CasaController;

public class JanelaCasa extends JFrame {

    private JTextField txtEndereco;
    private JTextField txtCep;
    private JTextField txtArea;
    private JTextField txtNumComodos;

    private CasaController controller;

    public JanelaCasa(CasaController controller) {
        super("Cadastro de Casa");
        this.controller = controller;

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblEndereco    = new JLabel("Endereço:");
        JLabel lblCep         = new JLabel("CEP (99999-999):");
        JLabel lblArea        = new JLabel("Área (m²):");
        JLabel lblNumComodos  = new JLabel("Nº de cômodos:");

        txtEndereco   = new JTextField(25);
        txtCep        = new JTextField(10);
        txtArea       = new JTextField(5);
        txtNumComodos = new JTextField(5);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> salvarCasa());

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(lblCep);
        panel.add(txtCep);
        panel.add(lblArea);
        panel.add(txtArea);
        panel.add(lblNumComodos);
        panel.add(txtNumComodos);
        panel.add(new JLabel());
        panel.add(btnSalvar);

        add(panel, BorderLayout.CENTER);
    }

    private void salvarCasa() {
        try {
            String endereco = txtEndereco.getText();
            String cep      = txtCep.getText();
            int area        = Integer.parseInt(txtArea.getText());
            int numComodos  = Integer.parseInt(txtNumComodos.getText());

            controller.cadastrarCasa(endereco, cep, area, numComodos);

            JOptionPane.showMessageDialog(this,
                    "Casa cadastrada com sucesso!");

            txtEndereco.setText("");
            txtCep.setText("");
            txtArea.setText("");
            txtNumComodos.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Área e número de cômodos devem ser inteiros.",
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
