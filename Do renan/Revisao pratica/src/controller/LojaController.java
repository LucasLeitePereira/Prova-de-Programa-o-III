package controller;

import java.util.ArrayList;
import java.util.List;

import model.Loja;

public class LojaController {

    private List<Loja> lojas;

    public LojaController() {
        this.lojas = new ArrayList<>();
    }

    public void cadastrarLoja(String endereco, String cep, int area, int numPortas) {
        Loja loja = new Loja(endereco, cep, area, numPortas);
        lojas.add(loja);
    }

    public List<Loja> listarLojas() {
        return lojas;
    }
}
