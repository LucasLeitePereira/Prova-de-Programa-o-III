package model.dao;

import model.Aluno;

public class DaoAluno {
	//
	// CONSTANTES
	//
	final public static int NUM_MAX_ELEMENTOS = 10;
	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Aluno[] arrayDeElementos = new Aluno[NUM_MAX_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoAluno() {
		super();
	}
	
	/**
	 * Inclui um novo Aluno no array de elementos do Dao
	 */
	public boolean incluir(Aluno novo) {
		// Se o array de elementos já estiver completo, não podemos incluir ninguém
		if(DaoAluno.numElementos == DaoAluno.NUM_MAX_ELEMENTOS)
			return false;
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Incluindo o novo Aluno no array de elementos do Dao
		DaoAluno.arrayDeElementos[ DaoAluno.numElementos ] = novo;
		// Incrementamos numElementos
		DaoAluno.numElementos++;
		// retornamos true informando que incluímos o novo Aluno
		return true;		
	}
	/**
	 * Retorna o Aluno cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Aluno obterAlunoPelaMatricula(String matricula) {
		// Para cada Aluno presente dentro do array de elementos
		for(int i = 0; i < DaoAluno.numElementos; i++) {
			String matrDoAluno = DaoAluno.arrayDeElementos[i].getMatricula();
			if(matrDoAluno.equals(matricula))
				return DaoAluno.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Aluno cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Aluno obterAlunoPeloNome(String nome) {
		// Para cada Aluno presente dentro do array de elementos
		for(int i = 0; i < DaoAluno.numElementos; i++) {
			String nomeDoAluno = DaoAluno.arrayDeElementos[i].getNome();
			if(nomeDoAluno.equals(nome))
				return DaoAluno.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Aluno gerenciados pelo DAO
	 */
	public Aluno[] obterTodos() {
		return DaoAluno.arrayDeElementos;
	}
}
