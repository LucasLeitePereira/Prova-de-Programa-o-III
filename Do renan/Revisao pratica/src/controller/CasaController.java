package controller;

import java.util.ArrayList;
import java.util.List;

import model.Casa;

public class CasaController {

    private List<Casa> casas;

    public CasaController() {
        this.casas = new ArrayList<>();
    }

    public void cadastrarCasa(String endereco, String cep, int area, int numComodos) {
        Casa casa = new Casa(endereco, cep, area, numComodos);
        casas.add(casa);
    }

    public List<Casa> listarCasas() {
        return casas;
    }
}
