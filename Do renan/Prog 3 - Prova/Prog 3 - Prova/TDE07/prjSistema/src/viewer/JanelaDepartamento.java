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

import model.Departamento;
import model.ModelException;
import model.dao.DaoDepartamento;

public class JanelaDepartamento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfSigla;
    private JTextField tfNome;

    /**
     * Create the frame.
     */
    public JanelaDepartamento() {
        setTitle("Departamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 223);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Sigla:");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNewLabel.setBounds(33, 40, 46, 14);
        contentPane.add(lblNewLabel);

        tfSigla = new JTextField();
        tfSigla.setBounds(89, 35, 111, 20);
        contentPane.add(tfSigla);
        tfSigla.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(33, 80, 46, 14);
        contentPane.add(lblNewLabel_1);

        tfNome = new JTextField();
        tfNome.setBounds(89, 75, 311, 20);
        contentPane.add(tfNome);
        tfNome.setColumns(10);

        JButton btOk = new JButton("Ok");
        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sigla = tfSigla.getText();
                String nome = tfNome.getText();

                try {
                    Departamento d1 = new Departamento(sigla, nome);
                    DaoDepartamento dao = new DaoDepartamento();
                    dao.incluir(d1);
                    JOptionPane.showMessageDialog(btOk, "Objeto Departamento Criado: " + d1);
                } catch (ModelException e1) {
                    JOptionPane.showMessageDialog(btOk, e1.getMessage());
                    e1.printStackTrace();
                }
            }
        });
        btOk.setBounds(60, 132, 89, 23);
        contentPane.add(btOk);

        JButton btRemover = new JButton("Remover");
        btRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerDepartamento();
            }
        });
        btRemover.setBounds(175, 132, 100, 23);
        contentPane.add(btRemover);


        JButton btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btCancelar, "Fechando a janela...");
                setVisible(false);
            }
        });
        btCancelar.setBounds(290, 132, 100, 23);
        contentPane.add(btCancelar);
    }


    private void removerDepartamento() {
        String sigla = tfSigla.getText();

        if (sigla == null || sigla.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a sigla do departamento a ser removido.");
            return;
        }

        DaoDepartamento dao = new DaoDepartamento();
        Departamento[] lista = dao.obterTodos();
        Departamento encontrado = null;

        for (int i = 0; i < dao.getNumElementos(); i++) {
            if (lista[i].getSigla().equals(sigla)) {
                encontrado = lista[i];
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Departamento não encontrado para a sigla informada.");
            return;
        }

        boolean ok = dao.remover(encontrado);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Departamento removido com sucesso!");

            tfSigla.setText("");
            tfNome.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover o departamento.");
        }
    }
}
