package model;

public class Loja extends Imovel {
    private int numPortas;

    public Loja(String endereco, String cep, int area, int numPortas) {
        super(endereco, cep, area);
        setNumPortas(numPortas);
    }

    public void setNumPortas(int numPortas) {
        if (numPortas <= 0)
            throw new IllegalArgumentException("Número de portas deve ser maior que zero!");
        this.numPortas = numPortas;
    }

    public int getNumPortas() {
        return numPortas;
    }
}
