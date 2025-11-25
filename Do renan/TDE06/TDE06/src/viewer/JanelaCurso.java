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

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfCodigo.getText();
				int codigo;
				try {
					// Convertendo a string lida para inteiro
					codigo = Integer.parseInt(aux);
				} catch(NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo código!");
					return;
				}
				// Mando para 'tfNome' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String nome = tfNome.getText();
				
				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				aux = tfCargaHoraria.getText();
				int cargaHoraria;
				try {
					// Convertendo a string lida para inteiro
					cargaHoraria = Integer.parseInt(aux);
				} catch(NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo carga horária!");
					return;
				}
				//
				// Após ter recuperado o que o usuário preencheu, vamos 
				// instanciar o curso
				//
				try {
					// Instanciando o curso
					Curso c1 = new Curso(codigo, nome, cargaHoraria);
					// Solicitando a um DaoCurso para guardar o objeto
					DaoCurso dao = new DaoCurso();
					dao.incluir(c1);
					// Informo ao usuário que o objeto foi guardado
					JOptionPane.showMessageDialog(btOk, "Objeto Curso Criado: " + c1);
				} catch (ModelException e1) {
					// Exibe uma 'Dialog' posicionada próximo ao botão
					// btOk com a mensagem de erro da exceção
					JOptionPane.showMessageDialog(btOk, e1.getMessage());
					// Imprime na console o traçado de execução da Stack
					e1.printStackTrace();
				}

			}
		});
		btOk.setBounds(103, 167, 89, 23);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Exibindo a Dialog
				JOptionPane.showMessageDialog(btCancelar, "Fechando a janela...");
				// Tornando a janela invisível
				setVisible(false);
			}
		});
		btCancelar.setBounds(237, 167, 89, 23);
		contentPane.add(btCancelar);

		JLabel lblNewLabel_1_1 = new JLabel("CH:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(33, 124, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		tfCargaHoraria = new JTextField();
		tfCargaHoraria.setColumns(10);
		tfCargaHoraria.setBounds(89, 119, 111, 20);
		contentPane.add(tfCargaHoraria);
	}
}
