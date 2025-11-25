package model;

import exception.ValidacaoException;

public class Loja extends Imovel {

    private int numPortas;

    public Loja() {
        super();
    }

    public Loja(String endereco, String cep, int area, int numPortas) throws ValidacaoException {
        super(endereco, cep, area);
        setNumPortas(numPortas);
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) throws ValidacaoException {
        validarNumPortas(numPortas);
        this.numPortas = numPortas;
    }

    private void validarNumPortas(int numPortas) throws ValidacaoException {
        if (numPortas <= 0) {
            throw new ValidacaoException("Número de portas deve ser maior que zero!");
        }
    }

    @Override
    public String toString() {
        return "Loja [" + super.toString() + ", Nº Portas=" + numPortas + "]";
    }
}