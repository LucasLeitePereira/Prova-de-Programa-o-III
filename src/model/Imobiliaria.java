package model;

import exception.ValidacaoException;

public class Imobiliaria {

    private String cnpj;
    private String nome;

    public Imobiliaria() {
    }

    public Imobiliaria(String cnpj, String nome) throws ValidacaoException {
        setCnpj(cnpj);
        setNome(nome);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws ValidacaoException {
        validarCnpj(cnpj);
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValidacaoException {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarCnpj(String cnpj) throws ValidacaoException {
        if (cnpj == null || cnpj.length() != 18) {
            throw new ValidacaoException("CNPJ deve ter 18 caracteres!");
        }

        String regex = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";

        if (!cnpj.matches(regex)) {
            throw new ValidacaoException("CNPJ inválido! Formato esperado: 99.999.999/0009-99");
        }
    }

    private void validarNome(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio!");
        }

        if (nome.length() > 20) {
            throw new ValidacaoException("Nome deve ter no máximo 20 caracteres!");
        }


        if (!nome.matches("[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ\\s]+")) {
            throw new ValidacaoException("Nome deve conter apenas letras e espaços!");
        }
    }

    @Override
    public String toString() {
        return "Imobiliária [CNPJ=" + cnpj + ", Nome=" + nome + "]";
    }
}