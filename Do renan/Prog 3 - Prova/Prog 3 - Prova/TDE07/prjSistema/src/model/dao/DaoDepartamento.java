package model.dao;

import model.Departamento;
import model.Empregado;

public class DaoDepartamento {
	//
	// CONSTANTES
	//
	final public static int NUM_MAX_ELEMENTOS = 10;
	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Departamento[] arrayDeElementos = new Departamento[NUM_MAX_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoDepartamento() {
		super();
	}

    public int getNumElementos() {
        return DaoDepartamento.numElementos;
    }
	

	public boolean incluir(Departamento novo) {
        if (novo == null)
            return false;

        if (DaoDepartamento.numElementos == DaoDepartamento.arrayDeElementos.length) {
            int novoTamanho = DaoDepartamento.arrayDeElementos.length * 2;
            Departamento[] novoArray = new Departamento[novoTamanho];
            for (int i = 0; i < DaoDepartamento.numElementos; i++) {
                novoArray[i] = DaoDepartamento.arrayDeElementos[i];
            }
            DaoDepartamento.arrayDeElementos = novoArray;
        }
        DaoDepartamento.arrayDeElementos[DaoDepartamento.numElementos] = novo;
        DaoDepartamento.numElementos++;
        return true;
	}

    public boolean remover(Departamento obj) {
        if (obj == null)
            return false;

        for (int i = 0; i < DaoDepartamento.numElementos; i++) {
            if (DaoDepartamento.arrayDeElementos[i] == obj) {
                for (int j = i; j < DaoDepartamento.numElementos - 1; j++) {
                    DaoDepartamento.arrayDeElementos[j] = DaoDepartamento.arrayDeElementos[j + 1];
                }
                DaoDepartamento.arrayDeElementos[DaoDepartamento.numElementos - 1] = null;
                DaoDepartamento.numElementos--;
                return true;
            }
        }
        return false;
    }
	/**
	 * Retorna o Departamento cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Departamento obterDepartamentoPelaSigla(String sigla) {
		// Para cada Departamento presente dentro do array de elementos
		for(int i = 0; i < DaoDepartamento.numElementos; i++) {
			String siglaDoDepartamento = DaoDepartamento.arrayDeElementos[i].getSigla();
			if(siglaDoDepartamento.equals(sigla))
				return DaoDepartamento.arrayDeElementos[i];
		}
		return null;
	}
	/**
	 * Retorna o Departamento cujo nome foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Departamento obterDepartamentoPeloNome(String nome) {
		// Para cada Departamento presente dentro do array de elementos
		for(int i = 0; i < DaoDepartamento.numElementos; i++) {
			String nomeDoDepartamento = DaoDepartamento.arrayDeElementos[i].getNome();
			if(nomeDoDepartamento.equals(nome))
				return DaoDepartamento.arrayDeElementos[i];
		}
		return null;
	}
	
	/**
	 * Retorna todos os objetos Departamento gerenciados pelo DAO
	 */
	public static Departamento[] obterTodos() {
		return DaoDepartamento.arrayDeElementos;
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
	static void recuperarTodos(Departamento[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoDepartamento.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
