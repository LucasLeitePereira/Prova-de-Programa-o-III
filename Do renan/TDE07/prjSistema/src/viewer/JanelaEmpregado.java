package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Departamento;
import model.Empregado;
import model.ModelException;
import model.dao.DaoDepartamento;
import model.dao.DaoEmpregado;

public class JanelaEmpregado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfCpf;
    private JTextField tfNome;
    private JLabel lblNewLabel_2;
    private JTextField tfIdade;
    private JLabel lblNewLabel_3;
    private JComboBox cbDepto;
    private JLabel lblNewLabel_4;
    private JTextField tfMatrFunc;

    /**
     * Create the frame.
     */
    public JanelaEmpregado() {
        setTitle("Empregado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 332);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Cpf:");
        lblNewLabel.setBounds(26, 33, 46, 14);
        contentPane.add(lblNewLabel);

        tfCpf = new JTextField();
        tfCpf.setBounds(75, 30, 154, 20);
        contentPane.add(tfCpf);
        tfCpf.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setBounds(26, 77, 46, 14);
        contentPane.add(lblNewLabel_1);

        tfNome = new JTextField();
        tfNome.setBounds(75, 74, 246, 20);
        contentPane.add(tfNome);
        tfNome.setColumns(10);

        lblNewLabel_2 = new JLabel("Idade:");
        lblNewLabel_2.setBounds(26, 117, 46, 14);
        contentPane.add(lblNewLabel_2);

        tfIdade = new JTextField();
        tfIdade.setBounds(75, 114, 101, 20);
        contentPane.add(tfIdade);
        tfIdade.setColumns(10);

        lblNewLabel_3 = new JLabel("Departamento:");
        lblNewLabel_3.setBounds(26, 199, 90, 14);
        contentPane.add(lblNewLabel_3);

        JButton btOk = new JButton("Ok");
        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCpf.getText();
                String nome = tfNome.getText();
                String aux = tfIdade.getText();
                int idade;
                try {
                    idade = Integer.parseInt(aux);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Idade Inválida: " + aux);
                    return;
                }

                int matrFunc;
                aux = tfMatrFunc.getText();
                try {
                    matrFunc = Integer.parseInt(aux);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Matrícula Funcional Inválida: " + aux);
                    return;
                }


                Departamento depto = (Departamento) cbDepto.getSelectedItem();

                try {
                    Empregado d = new Empregado(cpf, nome, idade, matrFunc, depto);
                    DaoEmpregado dao = new DaoEmpregado();
                    dao.incluir(d);
                    JOptionPane.showMessageDialog(null, "Objeto Empregado Foi Instanciado!\n" + d);
                } catch (ModelException me) {
                    JOptionPane.showMessageDialog(null, me.getMessage());
                }
            }
        });
        btOk.setBounds(40, 243, 89, 23);
        contentPane.add(btOk);

        JButton btRemover = new JButton("Remover");
        btRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerEmpregado();
            }
        });
        btRemover.setBounds(165, 243, 100, 23);
        contentPane.add(btRemover);

        JButton btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btCancelar.setBounds(290, 243, 89, 23);
        contentPane.add(btCancelar);


        DaoDepartamento dao = new DaoDepartamento();
        Departamento[] conjDeptos = dao.obterTodos();
        cbDepto = new JComboBox(conjDeptos);
        cbDepto.setBounds(115, 195, 246, 22);
        contentPane.add(cbDepto);

        lblNewLabel_4 = new JLabel("Matr.Func:");
        lblNewLabel_4.setBounds(10, 160, 56, 14);
        contentPane.add(lblNewLabel_4);

        tfMatrFunc = new JTextField();
        tfMatrFunc.setBounds(75, 157, 154, 20);
        contentPane.add(tfMatrFunc);
        tfMatrFunc.setColumns(10);
    }

    private void removerEmpregado() {
        String aux = tfMatrFunc.getText();

        if (aux == null || aux.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a matrícula funcional do empregado a ser removido.");
            return;
        }

        int matrFunc;
        try {
            matrFunc = Integer.parseInt(aux);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Matrícula funcional inválida! Digite apenas números.");
            return;
        }

        DaoEmpregado dao = new DaoEmpregado();
        Empregado[] lista = dao.obterTodos();
        Empregado encontrado = null;

        for (int i = 0; i < dao.getNumElementos(); i++) {
            if (lista[i].getMatrFuncional() == matrFunc) {
                encontrado = lista[i];
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Empregado não encontrado para a matrícula informada.");
            return;
        }

        boolean ok = dao.remover(encontrado);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Empregado removido com sucesso!");

            tfCpf.setText("");
            tfNome.setText("");
            tfIdade.setText("");
            tfMatrFunc.setText("");
            if (cbDepto.getItemCount() > 0) {
                cbDepto.setSelectedIndex(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover o empregado.");
        }
    }
}
