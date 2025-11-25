package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
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

    private JTextField tfNome;
    private JTextField tfCpf;
    private JTextField tfMatrFuncional;
    private JTextField tfIdade;
    private JComboBox<Departamento> cbDepartamentos; // <- agora guarda Departamento

    public JanelaEmpregado() {
        setTitle("Empregado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 420, 300); // aumentei um pouco a altura
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 15, 60, 14);
        contentPane.add(lblNome);

        tfNome = new JTextField();
        tfNome.setBounds(120, 12, 260, 20);
        contentPane.add(tfNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 46, 60, 14);
        contentPane.add(lblCpf);

        tfCpf = new JTextField();
        tfCpf.setBounds(120, 43, 260, 20);
        contentPane.add(tfCpf);

        JLabel lblMatr = new JLabel("Matr. Func.:");
        lblMatr.setBounds(10, 77, 100, 14);
        contentPane.add(lblMatr);

        tfMatrFuncional = new JTextField();
        tfMatrFuncional.setBounds(120, 74, 120, 20);
        contentPane.add(tfMatrFuncional);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(10, 108, 100, 14);
        contentPane.add(lblIdade);

        tfIdade = new JTextField();
        tfIdade.setBounds(120, 105, 80, 20);
        contentPane.add(tfIdade);

        JLabel lblDepto = new JLabel("Departamento:");
        lblDepto.setBounds(10, 139, 100, 14); // desci o depto para não sobrepor idade
        contentPane.add(lblDepto);

        cbDepartamentos = new JComboBox<Departamento>();
        cbDepartamentos.setBounds(120, 136, 260, 22);
        contentPane.add(cbDepartamentos);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarEmpregado();
            }
        });
        btSalvar.setBounds(120, 200, 100, 25);
        contentPane.add(btSalvar);

        JButton btFechar = new JButton("Fechar");
        btFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btFechar.setBounds(230, 200, 100, 25);
        contentPane.add(btFechar);

        carregarDepartamentos();
    }

    private void carregarDepartamentos() {
        // Troque esta fonte caso você já tenha uma lista centralizada em CtrlPrograma/Outro DAO
        DaoDepartamento dao = new DaoDepartamento();
        int n = dao.getNumElementos();
        Departamento[] todos = dao.getTodos();

        DefaultComboBoxModel<Departamento> model = new DefaultComboBoxModel<>();
        for (int i = 0; i < n; i++) {
            model.addElement(todos[i]); // aparecerá "SIGLA-Nome" via toString()
        }
        cbDepartamentos.setModel(model);
    }

    private void salvarEmpregado() {
        String nome = tfNome.getText();
        String cpf = tfCpf.getText();
        String idadeStr = tfIdade.getText();
        String matrStr = tfMatrFuncional.getText();

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome.");
            return;
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o CPF.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número inteiro.");
            return;
        }

        int matr;
        try {
            matr = Integer.parseInt(matrStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Matrícula funcional deve ser um número inteiro.");
            return;
        }

        Departamento depto = (Departamento) cbDepartamentos.getSelectedItem();
        if (depto == null) {
            JOptionPane.showMessageDialog(this, "Selecione um Departamento.");
            return;
        }

        try {
            // Ordem dos parâmetros: cpf, nome, idade, matrFuncional, departamento
            Empregado emp = new Empregado(cpf, nome, idade, matr, depto);

            DaoEmpregado daoEmp = new DaoEmpregado();
            boolean ok = daoEmp.incluir(emp);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Capacidade máxima de empregados atingida!");
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Empregado salvo com sucesso:\n" + emp.getMatrFuncional() + " - " + nome);

        } catch (ModelException me) {
            JOptionPane.showMessageDialog(this, me.getMessage());
        }
    }
}
