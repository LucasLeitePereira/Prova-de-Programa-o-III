package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JanelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public JanelaPrincipal() {
        setTitle("Janela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);


        JButton btCurso = new JButton("Curso");
        btCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaCurso janela = new JanelaCurso();
                janela.setVisible(true);
            }
        });
        btCurso.setBounds(20, 30, 140, 30);
        contentPane.add(btCurso);

        JButton btDisciplina = new JButton("Disciplina");
        btDisciplina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaDisciplina janela = new JanelaDisciplina();
                janela.setVisible(true);
            }
        });
        btDisciplina.setBounds(180, 30, 140, 30);
        contentPane.add(btDisciplina);

        JButton btDepartamento = new JButton("Departamento");
        btDepartamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaDepartamento janela = new JanelaDepartamento();
                janela.setVisible(true);
            }
        });
        btDepartamento.setBounds(340, 30, 140, 30);
        contentPane.add(btDepartamento);

        JButton btConsultarCurso = new JButton("Consultar Curso");
        btConsultarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaConsultarCurso janela = new JanelaConsultarCurso();
                janela.setVisible(true);
            }
        });
        btConsultarCurso.setBounds(20, 80, 140, 30);
        contentPane.add(btConsultarCurso);

        JButton btAluno = new JButton("Aluno");
        btAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaAluno janela = new JanelaAluno();
                janela.setVisible(true);
            }
        });
        btAluno.setBounds(180, 80, 140, 30);
        contentPane.add(btAluno);

        JButton btEmpregado = new JButton("Empregado");
        btEmpregado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JanelaEmpregado().setVisible(true);
            }
        });
        btEmpregado.setBounds(340, 80, 140, 30);
        contentPane.add(btEmpregado);

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btSair.setBounds(180, 130, 140, 30);
        contentPane.add(btSair);
    }
}
