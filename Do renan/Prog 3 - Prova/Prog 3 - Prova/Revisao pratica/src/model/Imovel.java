package model;

public abstract class Imovel {
    private String endereco;
    private String cep;
    private int area;

    public Imovel(String endereco, String cep, int area) {
        setEndereco(endereco);
        setCep(cep);
        setArea(area);
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty() || endereco.length() > 30)
            throw new IllegalArgumentException("Endereço inválido!");
        this.endereco = endereco;
    }

    public void setCep(String cep) {
        if (cep == null || !cep.matches("\\d{5}-\\d{3}"))
            throw new IllegalArgumentException("CEP inválido! Formato esperado: 99999-999");
        this.cep = cep;
    }

    public void setArea(int area) {
        if (area <= 0)
            throw new IllegalArgumentException("Área deve ser maior que zero!");
        this.area = area;
    }

    public String getEndereco() { return endereco; }
    public String getCep() { return cep; }
    public int getArea() { return area; }
}
