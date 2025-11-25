package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Casa;
import exception.ValidacaoException;

/**
 * Janela para cadastro de Casa
 * @author Seu Nome
 */
public class JanelaCasa extends JFrame {

    private JTextField txtEndereco;
    private JTextField txtCep;
    private JTextField txtArea;
    private JTextField txtNumComodos;
    private JButton btnSalvar;
    private JButton btnLimpar;

    public JanelaCasa() {
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels e campos
        JLabel lblEndereco = new JLabel("Endereço (até 30 caracteres):");
        JLabel lblCep = new JLabel("CEP (99999-999):");
        JLabel lblArea = new JLabel("Área (m²):");
        JLabel lblNumComodos = new JLabel("Número de Cômodos:");

        txtEndereco = new JTextField(25);
        txtCep = new JTextField(15);
        txtArea = new JTextField(10);
        txtNumComodos = new JTextField(10);

        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");

        // Posicionar componentes
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
        painelPrincipal.add(lblNumComodos, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        painelPrincipal.add(txtNumComodos, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelPrincipal.add(painelBotoes, gbc);

        add(painelPrincipal);

        // Configurar eventos
        configurarEventos();
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCasa();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void salvarCasa() {
        try {
            String endereco = txtEndereco.getText();
            String cep = txtCep.getText();

            int area;
            int numComodos;

            try {
                area = Integer.parseInt(txtArea.getText());
            } catch (NumberFormatException ex) {
                throw new ValidacaoException("Área deve ser um número inteiro!");
            }

            try {
                numComodos = Integer.parseInt(txtNumComodos.getText());
            } catch (NumberFormatException ex) {
                throw new ValidacaoException("Número de cômodos deve ser um número inteiro!");
            }

            Casa casa = new Casa(endereco, cep, area, numComodos);

            JOptionPane.showMessageDialog(this,
                    "Casa cadastrada com sucesso!\n" + casa.toString(),
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
        txtNumComodos.setText("");
        txtEndereco.requestFocus();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Casa");
        setSize(480, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}