package model;

import exception.ValidacaoException;

public class Casa extends Imovel {

    private int numComodos;

    public Casa() {
        super();
    }

    public Casa(String endereco, String cep, int area, int numComodos) throws ValidacaoException {
        super(endereco, cep, area);
        setNumComodos(numComodos);
    }

    public int getNumComodos() {
        return numComodos;
    }

    public void setNumComodos(int numComodos) throws ValidacaoException {
        validarNumComodos(numComodos);
        this.numComodos = numComodos;
    }

    private void validarNumComodos(int numComodos) throws ValidacaoException {
        if (numComodos <= 0) {
            throw new ValidacaoException("Número de cômodos deve ser maior que zero!");
        }
    }

    @Override
    public String toString() {
        return "Casa [" + super.toString() + ", Nº Cômodos=" + numComodos + "]";
    }
}