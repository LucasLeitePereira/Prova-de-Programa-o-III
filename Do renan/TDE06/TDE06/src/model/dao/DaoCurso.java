package model.dao;

import model.Curso;

public class DaoCurso {
	//
	// CONSTANTES
	//
	final public static int NUM_MAX_ELEMENTOS = 10;
	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Curso[] arrayDeElementos = new Curso[NUM_MAX_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoCurso() {
		super();
	}
	
	/**
	 * Inclui um novo curso no array de elementos do Dao
	 */
	public boolean incluir(Curso novo) {
		// Se o array de elementos já estiver completo, não podemos incluir ninguém
		if(DaoCurso.numElementos == DaoCurso.NUM_MAX_ELEMENTOS)
			return false;
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		// Incluindo o novo curso no array de elementos do Dao
		DaoCurso.arrayDeElementos[ DaoCurso.numElementos ] = novo;
		// Incrementamos numElementos
		DaoCurso.numElementos++;
		// retornamos true informando que incluímos o novo curso
		return true;		
	}
	/**
	 * Retorna o curso cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Curso obterCursoPeloCodigo(int codigo) {
		// Para cada curso presente dentro do array de elementos
		for(int i = 0; i < DaoCurso.numElementos; i++) {
			int codigoDoCurso = DaoCurso.arrayDeElementos[i].getCodigo();
			if(codigoDoCurso == codigo)
				return DaoCurso.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o curso cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Curso obterCursoPeloNome(String nome) {
		// Para cada curso presente dentro do array de elementos
		for(int i = 0; i < DaoCurso.numElementos; i++) {
			String nomeDoCurso = DaoCurso.arrayDeElementos[i].getNome();
			if(nomeDoCurso.equals(nome))
				return DaoCurso.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Curso gerenciados pelo DAO
	 */
	public Curso[] obterTodos() {
		return DaoCurso.arrayDeElementos;
	}

}
