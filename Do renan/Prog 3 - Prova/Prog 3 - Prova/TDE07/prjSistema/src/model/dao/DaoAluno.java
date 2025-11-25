package model.dao;

import model.Aluno;
import model.Empregado;

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
	
    public boolean incluir(Aluno novo) {
        if (novo == null)
            return false;

        if (DaoAluno.numElementos == DaoAluno.arrayDeElementos.length) {
            int novoTamanho = DaoAluno.arrayDeElementos.length * 2;
            Aluno[] novoArray = new Aluno[novoTamanho];
            for (int i = 0; i < DaoAluno.numElementos; i++) {
                novoArray[i] = DaoAluno.arrayDeElementos[i];
            }
            DaoAluno.arrayDeElementos = novoArray;
        }
        DaoAluno.arrayDeElementos[DaoAluno.numElementos] = novo;
        DaoAluno.numElementos++;
        return true;
    }

    public boolean remover(Aluno obj) {
        if (obj == null)
            return false;

        for (int i = 0; i < DaoAluno.numElementos; i++) {
            if (DaoAluno.arrayDeElementos[i] == obj) {
                for (int j = i; j < DaoAluno.numElementos - 1; j++) {
                    DaoAluno.arrayDeElementos[j] = DaoAluno.arrayDeElementos[j + 1];
                }
                DaoAluno.arrayDeElementos[DaoAluno.numElementos - 1] = null;
                DaoAluno.numElementos--;
                return true;
            }
        }
        return false;
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
	public static Aluno[] obterTodos() {
		return DaoAluno.arrayDeElementos;
	}

	/**
	 * Método que determina o novo arrayDeElementos a ser gerenciado
	 * pelo DAO. Observe que não há indicação de visibilidade (public,
	 * private ou protected). Isso em Java indica que a visibilidade é
	 * "package"; ou seja, somente as classes que pertencem ao mesmo 
	 * pacote sabem da existência deste método. Como somente a classe
	 * Serializador vai usar (e ela faz parte de model.dao), optamos
	 * por deixar a visibilidade package.
	 */
	static void recuperarTodos(Aluno[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoAluno.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}

}
