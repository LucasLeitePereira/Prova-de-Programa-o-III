package viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Curso;
import model.ModelException;
import model.dao.DaoCurso;

public class JanelaCurso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfCodigo;
    private JTextField tfNome;
    private JTextField tfCargaHoraria;

    /**
     * Create the frame.
     */
    public JanelaCurso() {
        setTitle("Curso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 252);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Código:");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNewLabel.setBounds(33, 40, 46, 14);
        contentPane.add(lblNewLabel);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(89, 35, 111, 20);
        contentPane.add(tfCodigo);
        tfCodigo.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(33, 80, 46, 14);
        contentPane.add(lblNewLabel_1);

        tfNome = new JTextField();
        tfNome.setBounds(89, 75, 311, 20);
        contentPane.add(tfNome);
        tfNome.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("CH:");
        lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(33, 124, 46, 14);
        contentPane.add(lblNewLabel_1_1);

        tfCargaHoraria = new JTextField();
        tfCargaHoraria.setColumns(10);
        tfCargaHoraria.setBounds(89, 119, 111, 20);
        contentPane.add(tfCargaHoraria);

        JButton btOk = new JButton("Ok");
        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String aux = tfCodigo.getText();
                int codigo;
                try {
                    codigo = Integer.parseInt(aux);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo código!");
                    return;
                }

                String nome = tfNome.getText();

                aux = tfCargaHoraria.getText();
                int cargaHoraria;
                try {
                    cargaHoraria = Integer.parseInt(aux);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo carga horária!");
                    return;
                }

                try {
                    Curso c1 = new Curso(codigo, nome, cargaHoraria);
                    DaoCurso dao = new DaoCurso();
                    dao.incluir(c1);
                    JOptionPane.showMessageDialog(btOk, "Curso criado e salvo!\n" + c1);
                } catch (ModelException e1) {
                    JOptionPane.showMessageDialog(btOk, e1.getMessage());
                }
            }
        });
        btOk.setBounds(40, 167, 89, 23);
        contentPane.add(btOk);

        JButton btRemover = new JButton("Remover");
        btRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerCurso();
            }
        });
        btRemover.setBounds(165, 167, 100, 23);
        contentPane.add(btRemover);

        JButton btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btCancelar, "Fechando a janela...");
                setVisible(false);
            }
        });
        btCancelar.setBounds(290, 167, 100, 23);
        contentPane.add(btCancelar);
    }


    private void removerCurso() {
        String aux = tfCodigo.getText();

        if (aux == null || aux.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o código do curso para remover.");
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(aux);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Código inválido! Digite apenas números.");
            return;
        }

        DaoCurso dao = new DaoCurso();
        Curso[] lista = dao.obterTodos();
        Curso encontrado = null;

        for (int i = 0; i < dao.getNumElementos(); i++) {
            if (lista[i].getCodigo() == codigo) {
                encontrado = lista[i];
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com este código.");
            return;
        }

        boolean ok = dao.remover(encontrado);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
            tfCodigo.setText("");
            tfNome.setText("");
            tfCargaHoraria.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover o curso.");
        }
    }
}
