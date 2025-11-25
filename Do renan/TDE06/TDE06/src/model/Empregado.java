package model;

public class Empregado extends Pessoa {
    private int matrFuncional;
    private Departamento departamentoTrabalho;



    public Empregado(String nome, String cpf,int idade, int matrFuncional, Departamento departamentoTrabalho) throws ModelException {
        super(nome, cpf, idade);
        setMatrFuncional(matrFuncional);
        setDepartamentoTrabalho(departamentoTrabalho);
    }

    public int getMatrFuncional() {
        return matrFuncional;
    }

    public void setMatrFuncional(int matrFuncional) throws ModelException {
        if (matrFuncional <= 0 || matrFuncional >= 9999) {
            throw new ModelException("Matrícula funcional deve ser > 0 e < 9999.");
        }
        this.matrFuncional = matrFuncional;
    }

    public Departamento getDepartamentoTrabalho() {
        return departamentoTrabalho;
    }

    public void setDepartamentoTrabalho(Departamento departamentoTrabalho) throws ModelException {
        if (departamentoTrabalho == null) {
            throw new ModelException("Departamento de trabalho é obrigatório.");
        }
        this.departamentoTrabalho = departamentoTrabalho;
    }
}
