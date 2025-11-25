package model.dao;

import model.Curso;
import model.Empregado;

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
    public int getNumElementos() {
        return DaoCurso.numElementos;
    }


	public boolean incluir(Curso novo) {
        if (novo == null)
            return false;

        if (DaoCurso.numElementos == DaoCurso.arrayDeElementos.length) {
            int novoTamanho = DaoCurso.arrayDeElementos.length * 2;
            Curso[] novoArray = new Curso[novoTamanho];
            for (int i = 0; i < DaoCurso.numElementos; i++) {
                novoArray[i] = DaoCurso.arrayDeElementos[i];
            }
            DaoCurso.arrayDeElementos = novoArray;
        }
        DaoCurso.arrayDeElementos[DaoCurso.numElementos] = novo;
        DaoCurso.numElementos++;
        return true;
	}

    public boolean remover(Curso obj) {
        if (obj == null)
            return false;

        for (int i = 0; i < DaoCurso.numElementos; i++) {
            if (DaoCurso.arrayDeElementos[i] == obj) {
                for (int j = i; j < DaoCurso.numElementos - 1; j++) {
                    DaoCurso.arrayDeElementos[j] = DaoCurso.arrayDeElementos[j + 1];
                }
                DaoCurso.arrayDeElementos[DaoCurso.numElementos - 1] = null;
                DaoCurso.numElementos--;
                return true;
            }
        }
        return false;
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
	public static Curso[] obterTodos() {
		return DaoCurso.arrayDeElementos;
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
	static void recuperarTodos(Curso[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoCurso.arrayDeElementos = array;
		// Contando quantos objetos Empregado estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
