package model;

public class Casa extends Imovel {
    private int numComodos;

    public Casa(String endereco, String cep, int area, int numComodos) {
        super(endereco, cep, area);
        setNumComodos(numComodos);
    }

    public void setNumComodos(int numComodos) {
        if (numComodos <= 0)
            throw new IllegalArgumentException("Número de cômodos deve ser maior que zero!");
        this.numComodos = numComodos;
    }

    public int getNumComodos() {
        return numComodos;
    }
}
