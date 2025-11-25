package viewer;

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
import model.dao.DaoCurso;

public class JanelaConsultarCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfResultado;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarCurso() {
		setTitle("Consultar Curso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 30, 42, 27);
		contentPane.add(lblNewLabel);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(70, 33, 109, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfResultado = new JTextField();
		tfResultado.setBounds(10, 95, 400, 20);
		contentPane.add(tfResultado);
		tfResultado.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Resultado:");
		lblNewLabel_1.setBounds(10, 69, 200, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btConsultar = new JButton("Consultar");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperando o que o usuário preencheu
				String aux = tfCodigo.getText();
				int codigo;
				try {
					codigo = Integer.parseInt(aux);
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(btConsultar, "Código inválido!");
					return;
				}
				// Solicitando ao Dao para procurar o curso com o código informado
				DaoCurso dao = new DaoCurso();
				Curso c = dao.obterCursoPeloCodigo(codigo);
				if(c != null)
					tfResultado.setText(c.toString());
				else
					tfResultado.setText("Curso não encontrado!");
			}
		});
		btConsultar.setBounds(197, 32, 138, 23);
		contentPane.add(btConsultar);
		
		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btFechar.setBounds(166, 154, 89, 23);
		contentPane.add(btFechar);
	}
}
