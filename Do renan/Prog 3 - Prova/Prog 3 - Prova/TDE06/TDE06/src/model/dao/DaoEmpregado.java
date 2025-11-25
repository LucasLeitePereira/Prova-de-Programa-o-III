package model.dao;

import model.Empregado;

public class DaoEmpregado {

    public static final int NUM_MAX_ELEMENTOS = 20;

    private static int numElementos = 0;
    private static Empregado[] arrayDeElementos = new Empregado[NUM_MAX_ELEMENTOS];

    public DaoEmpregado() {
        super();
    }

    public boolean incluir(Empregado novo) {
        if (numElementos == NUM_MAX_ELEMENTOS) return false;
        if (novo == null) return false;
        arrayDeElementos[numElementos] = novo;
        numElementos++;
        return true;
    }

    public Empregado obterEmpregadoPelaMatrFuncional(int matrFuncional) {
        for (int i = 0; i < numElementos; i++) {
            if (arrayDeElementos[i].getMatrFuncional() == matrFuncional) {
                return arrayDeElementos[i];
            }
        }
        return null;
    }

    public Empregado obterEmpregadoPeloNome(String nome) {
        if (nome == null) return null;
        for (int i = 0; i < numElementos; i++) {
            try {
                if (nome.equals(arrayDeElementos[i].getNome())) {
                    return arrayDeElementos[i];
                }
            } catch (Throwable t) {

            }
        }
        return null;
    }

    public Empregado[] obterTodos() {
        return arrayDeElementos;
    }

    public int getNumElementos() {
        return numElementos;
    }
}
