package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Imobiliaria;
import exception.ValidacaoException;

public class JanelaImobiliaria extends JFrame {

    private JTextField txtCnpj;
    private JTextField txtNome;
    private JButton btnSalvar;
    private JButton btnLimpar;

    public JanelaImobiliaria() {
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

        JLabel lblCnpj = new JLabel("CNPJ (99.999.999/0009-99):");
        JLabel lblNome = new JLabel("Nome (até 20 caracteres):");

        txtCnpj = new JTextField(20);
        txtNome = new JTextField(20);

        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");

        gbc.gridx = 0; gbc.gridy = 0;
        painelPrincipal.add(lblCnpj, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        painelPrincipal.add(txtCnpj, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painelPrincipal.add(lblNome, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        painelPrincipal.add(txtNome, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        painelPrincipal.add(painelBotoes, gbc);

        add(painelPrincipal);

        configurarEventos();
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarImobiliaria();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void salvarImobiliaria() {
        try {
            String cnpj = txtCnpj.getText();
            String nome = txtNome.getText();

            Imobiliaria imobiliaria = new Imobiliaria(cnpj, nome);

            JOptionPane.showMessageDialog(this,
                    "Imobiliária cadastrada com sucesso!\n" + imobiliaria.toString(),
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
        txtCnpj.setText("");
        txtNome.setText("");
        txtCnpj.requestFocus();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Imobiliária");
        setSize(450, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}