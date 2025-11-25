package model.dao;

import model.Departamento;

public class DaoDepartamento {
    public static final int NUM_MAX_ELEMENTOS = 20;

    private static int numElementos = 0;
    private static Departamento[] arrayDeElementos = new Departamento[NUM_MAX_ELEMENTOS];

    public boolean inserir(Departamento d) {
        if (d == null) return false;
        if (numElementos >= NUM_MAX_ELEMENTOS) return false;
        arrayDeElementos[numElementos++] = d;
        return true;
    }

    public Departamento obterDepartamentoPeloCodigo(String codigo) {
        if (codigo == null) return null;
        for (int i = 0; i < numElementos; i++) {
            if (codigo.equals(arrayDeElementos[i].getNome())) {
                return arrayDeElementos[i];
            }
        }
        return null;
    }

    public int getNumElementos() { return numElementos; }
    public Departamento[] getTodos() { return arrayDeElementos; }
}
