package view;

import javax.swing.*;
import java.awt.*;

import controller.ImobiliariaController;

public class JanelaImobiliaria extends JFrame {

    private JTextField txtCnpj;
    private JTextField txtNome;

    private ImobiliariaController controller;

    public JanelaImobiliaria(ImobiliariaController controller) {
        super("Cadastro de Imobiliária");
        this.controller = controller;

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblCnpj = new JLabel("CNPJ:");
        JLabel lblNome = new JLabel("Nome:");

        txtCnpj = new JTextField(20);
        txtNome = new JTextField(20);

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> salvarImobiliaria());

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(lblCnpj);
        panel.add(txtCnpj);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(new JLabel());
        panel.add(btnSalvar);

        add(panel, BorderLayout.CENTER);
    }

    private void salvarImobiliaria() {
        try {
            String cnpj = txtCnpj.getText();
            String nome = txtNome.getText();

            controller.cadastrarImobiliaria(cnpj, nome);

            JOptionPane.showMessageDialog(this,
                    "Imobiliária cadastrada com sucesso!");
            txtCnpj.setText("");
            txtNome.setText("");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
