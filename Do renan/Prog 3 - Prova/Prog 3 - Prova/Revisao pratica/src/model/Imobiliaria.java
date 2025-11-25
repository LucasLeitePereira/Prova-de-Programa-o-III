package model;

public class Imobiliaria {
    private String cnpj;
    private String nome;

    public Imobiliaria(String cnpj, String nome) {
        setCnpj(cnpj);
        setNome(nome);
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null ||
                !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/0009-\\d{2}")) {
            throw new IllegalArgumentException("CNPJ inválido! Formato correto: 99.999.999/0009-99");
        }
        this.cnpj = cnpj;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty() || nome.length() > 20)
            throw new IllegalArgumentException("Nome inválido!");
        this.nome = nome;
    }

    public String getCnpj() { return cnpj; }
    public String getNome() { return nome; }
}
