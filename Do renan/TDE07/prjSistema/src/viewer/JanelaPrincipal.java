package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.dao.Serializador;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btAluno;

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btDepartamento = new JButton("Departamento");
		btDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Estamos declarando a variável 'janela' cujo tipo é 
				// ponteiro para JanelaDepartamento. Com o operador new
				// estamos criando um objeto JanelaDepartamento e, com a 
				// atribuição, a variável 'janela' passará a apontar para esse
				// novo objeto.
				JanelaDepartamento janela = new JanelaDepartamento();
				// Estamos mandando a mensagem 'setVisible(true)' para
				// o objeto apontado por 'janela'
				janela.setVisible(true);
			}
		});
		btDepartamento.setBounds(278, 37, 119, 23);
		contentPane.add(btDepartamento);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Solicitando o salvamento dos objetos
				Serializador.salvarObjetos();
				// Para encerrar o programa
				System.exit(0);
			}
		});
		btSair.setBounds(149, 131, 119, 23);
		contentPane.add(btSair);
		
		JButton btCurso = new JButton("Curso");
		btCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCurso janela = new JanelaCurso();
				janela.setVisible(true);
			}
		});
		btCurso.setBounds(20, 37, 119, 23);
		contentPane.add(btCurso);
		
		JButton btDisciplina = new JButton("Disciplina");
		btDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaDisciplina janela = new JanelaDisciplina();
				janela.setVisible(true);
			}
		});
		btDisciplina.setBounds(149, 37, 119, 23);
		contentPane.add(btDisciplina);
		
		JButton btConsultarCurso = new JButton("Consultar Curso");
		btConsultarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaConsultarCurso janela = new JanelaConsultarCurso();
				janela.setVisible(true);
			}
		});
		btConsultarCurso.setBounds(20, 84, 119, 23);
		contentPane.add(btConsultarCurso);
		
		JButton btIncluirEmpregado = new JButton("Empregado");
		btIncluirEmpregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JanelaEmpregado janela = new JanelaEmpregado();
				janela.setVisible(true);
			}
		});
		btIncluirEmpregado.setBounds(149, 84, 119, 23);
		contentPane.add(btIncluirEmpregado);
		
		btAluno = new JButton("Aluno");
		btAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaAluno janela = new JanelaAluno();
				janela.setVisible(true);
			}
		});
		btAluno.setBounds(279, 83, 119, 23);
		contentPane.add(btAluno);
	}
}
