package controller;

import model.dao.Serializador;
import viewer.JanelaPrincipal;

public class CtrlPrograma {

	public static void main(String[] args) {
		// Recuperando os objetos da Serialização
		Serializador.recuperarObjetos();
		
		// Estamos declarando a variável 'janela' cujo tipo é 
		// ponteiro para JanelaPrincipal. Com o operador new
		// estamos criando um objeto JanelaPrincipal e, com a 
		// atribuição, a variável 'janela' passará a apontar para esse
		// novo objeto.
		JanelaPrincipal janela = new JanelaPrincipal();
		// Estamos mandando a mensagem 'setVisible(true)' para
		// o objeto apontado por 'janela'
		janela.setVisible(true);
		
	}

}
