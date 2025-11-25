package model.dao;

import model.Empregado;

public class DaoEmpregado {
	//
	// CONSTANTES
	//
	final public static int NUM_MAX_ELEMENTOS = 10;
	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Empregado[] arrayDeElementos = new Empregado[NUM_MAX_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoEmpregado() {
		super();
	}
    public int getNumElementos() {
        return DaoEmpregado.numElementos;
    }

	public boolean incluir(Empregado novo) {
        if (novo == null)
            return false;

        if (DaoEmpregado.numElementos == DaoEmpregado.arrayDeElementos.length) {
            int novoTamanho = DaoEmpregado.arrayDeElementos.length * 2;
            Empregado[] novoArray = new Empregado[novoTamanho];
            for (int i = 0; i < DaoEmpregado.numElementos; i++) {
                novoArray[i] = DaoEmpregado.arrayDeElementos[i];
            }
            DaoEmpregado.arrayDeElementos = novoArray;
        }
        DaoEmpregado.arrayDeElementos[DaoEmpregado.numElementos] = novo;
        DaoEmpregado.numElementos++;
        return true;
	}

    public boolean remover(Empregado obj) {
        if (obj == null)
            return false;

        for (int i = 0; i < DaoEmpregado.numElementos; i++) {
            if (DaoEmpregado.arrayDeElementos[i] == obj) {
                for (int j = i; j < DaoEmpregado.numElementos - 1; j++) {
                    DaoEmpregado.arrayDeElementos[j] = DaoEmpregado.arrayDeElementos[j + 1];
                }
                DaoEmpregado.arrayDeElementos[DaoEmpregado.numElementos - 1] = null;
                DaoEmpregado.numElementos--;
                return true;
            }
        }
        return false;
    }
	/**
	 * Retorna o Empregado cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Empregado obterEmpregadoPelaMatrFunc(int matrFunc) {
		// Para cada Empregado presente dentro do array de elementos
		for(int i = 0; i < DaoEmpregado.numElementos; i++) {
			int matrFuncDoEmpregado = DaoEmpregado.arrayDeElementos[i].getMatrFuncional();
			if(matrFuncDoEmpregado == matrFunc)
				return DaoEmpregado.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Empregado cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Empregado obterEmpregadoPeloNome(String nome) {
		// Para cada Empregado presente dentro do array de elementos
		for(int i = 0; i < DaoEmpregado.numElementos; i++) {
			String nomeDoEmpregado = DaoEmpregado.arrayDeElementos[i].getNome();
			if(nomeDoEmpregado.equals(nome))
				return DaoEmpregado.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Empregado gerenciados pelo DAO
	 */
	public static Empregado[] obterTodos() {
		return DaoEmpregado.arrayDeElementos;
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
	static void recuperarTodos(Empregado[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoEmpregado.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
