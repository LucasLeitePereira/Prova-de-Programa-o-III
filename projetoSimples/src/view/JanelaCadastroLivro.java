package view;

import javax.swing.*;
import java.awt.*;
import model.Livro;

public class JanelaCadastroLivro extends JFrame {
    private JLabel lblTituloLivro;
    private JLabel lblAutorLivro;
    private JLabel lblAnoLivro;

    private JTextField txtTituloLivro;
    private JTextField txtAutorLivro;
    private JTextField txtAnoLivro;

    private JButton btnCadastrarLivro;
    private JButton btnLimparLivro;

    public JanelaCadastroLivro() {
        criarComponentes();
        configurarJanela();
    }

    private void criarComponentes() {
        lblTituloLivro = new JLabel("Livro");
        lblAutorLivro = new JLabel("Autor");
        lblAnoLivro = new JLabel("Ano");

        txtTituloLivro = new JTextField(50);

        txtAutorLivro = new JTextField(50);
        txtAnoLivro = new JTextField(30);

        btnCadastrarLivro = new JButton("Cadastrar");
        btnLimparLivro = new JButton("Limpar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblTituloLivro, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtTituloLivro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblAutorLivro, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtAutorLivro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblAnoLivro, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtAnoLivro, gbc);

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnCadastrarLivro);
        panelBotoes.add(btnLimparLivro);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(panelBotoes, gbc);

        add(panel);

        configurarEventos();
    }

    private void configurarEventos() {
        btnCadastrarLivro.addActionListener(e -> salvar());
        btnLimparLivro.addActionListener(e -> limpar());
    }

    private void salvar() {
        try {
            String tituloLivro = txtTituloLivro.getText();
            String autorLivro = txtAutorLivro.getText();
            int anoLivro = Integer.parseInt(txtAnoLivro.getText());

            if (tituloLivro.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Digite o nome do livro",
                        "Erro de validação",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (autorLivro.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Digite o nome do autor",
                        "Erro de validação",
                        JOptionPane.ERROR_MESSAGE);
            }

            int ano;
            try {
                ano = Integer.parseInt(txtAnoLivro.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "O ano deve ser um número válido!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (ano < 1000 || ano > 2024) {
                JOptionPane.showMessageDialog(
                        this,
                        "O ano deve estar entre 1000 e 2024!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Livro livro = new Livro(tituloLivro, autorLivro, ano);

            JOptionPane.showMessageDialog(
                    this,
                    "Livro cadastrado com sucesso",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro inesperado",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpar() {
        txtTituloLivro.setText("");
        txtAutorLivro.setText("");
        txtAnoLivro.setText("");
        txtTituloLivro.requestFocus();
    }

    private void configurarJanela() {
        setTitle("Cadastro Livro");
        setSize(1400, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaCadastroLivro janela = new JanelaCadastroLivro();
            janela.setVisible(true);
        });
    }
}
