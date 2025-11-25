package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Loja;
import exception.ValidacaoException;

public class JanelaLoja extends JFrame {

    private JTextField txtEndereco;
    private JTextField txtCep;
    private JTextField txtArea;
    private JTextField txtNumPortas;
    private JButton btnSalvar;
    private JButton btnLimpar;

    public JanelaLoja() {
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEndereco = new JLabel("Endereço (até 30 caracteres):");
        JLabel lblCep = new JLabel("CEP (99999-999):");
        JLabel lblArea = new JLabel("Área (m²):");
        JLabel lblNumPortas = new JLabel("Número de Portas:");

        txtEndereco = new JTextField(25);
        txtCep = new JTextField(15);
        txtArea = new JTextField(10);
        txtNumPortas = new JTextField(10);

        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");

        gbc.gridx = 0; gbc.gridy = 0;
        painelPrincipal.add(lblEndereco, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        painelPrincipal.add(txtEndereco, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelPrincipal.add(lblCep, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        painelPrincipal.add(txtCep, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        painelPrincipal.add(lblArea, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        painelPrincipal.add(txtArea, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        painelPrincipal.add(lblNumPortas, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        painelPrincipal.add(txtNumPortas, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelPrincipal.add(painelBotoes, gbc);

        add(painelPrincipal);

        configurarEventos();
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarLoja();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void salvarLoja() {
        try {
            String endereco = txtEndereco.getText();
            String cep = txtCep.getText();

            int area;
            int numPortas;

            try {
                area = Integer.parseInt(txtArea.getText());
            } catch (NumberFormatException ex) {
                throw new ValidacaoException("Área deve ser um número inteiro!");
            }

            try {
                numPortas = Integer.parseInt(txtNumPortas.getText());
            } catch (NumberFormatException ex) {
                throw new ValidacaoException("Número de portas deve ser um número inteiro!");
            }

            Loja loja = new Loja(endereco, cep, area, numPortas);

            JOptionPane.showMessageDialog(this,
                    "Loja cadastrada com sucesso!\n" + loja.toString(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();

        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtEndereco.setText("");
        txtCep.setText("");
        txtArea.setText("");
        txtNumPortas.setText("");
        txtEndereco.requestFocus();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Loja");
        setSize(480, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}