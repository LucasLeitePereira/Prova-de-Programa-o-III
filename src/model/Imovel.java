package model;

import exception.ValidacaoException;

public abstract class Imovel {

    private String endereco;
    private String cep;
    private int area;

    public Imovel() {
    }

    public Imovel(String endereco, String cep, int area) throws ValidacaoException {
        setEndereco(endereco);
        setCep(cep);
        setArea(area);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) throws ValidacaoException {
        validarEndereco(endereco);
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) throws ValidacaoException {
        validarCep(cep);
        this.cep = cep;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) throws ValidacaoException {
        validarArea(area);
        this.area = area;
    }

    private void validarEndereco(String endereco) throws ValidacaoException {
        if (endereco == null || endereco.isEmpty()) {
            throw new ValidacaoException("Endereço não pode ser vazio!");
        }

        if (endereco.length() > 30) {
            throw new ValidacaoException("Endereço deve ter no máximo 30 caracteres!");
        }

        if (!endereco.matches("[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ0-9\\s,\\-]+")) {
            throw new ValidacaoException("Endereço deve conter apenas letras, números, vírgula e traço!");
        }
    }

    private void validarCep(String cep) throws ValidacaoException {
        if (cep == null || cep.length() != 9) {
            throw new ValidacaoException("CEP deve ter 9 caracteres!");
        }

        String regex = "\\d{5}-\\d{3}";

        if (!cep.matches(regex)) {
            throw new ValidacaoException("CEP inválido! Formato esperado: 99999-999");
        }
    }

    private void validarArea(int area) throws ValidacaoException {
        if (area <= 0) {
            throw new ValidacaoException("Área deve ser maior que zero!");
        }
    }

    @Override
    public String toString() {
        return "Endereço=" + endereco + ", CEP=" + cep + ", Área=" + area + "m²";
    }
}