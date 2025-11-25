package controller;

import java.util.ArrayList;
import java.util.List;

import model.Imobiliaria;

public class ImobiliariaController {

    private List<Imobiliaria> imobiliarias;

    public ImobiliariaController() {
        this.imobiliarias = new ArrayList<>();
    }

    public void cadastrarImobiliaria(String cnpj, String nome) {
        Imobiliaria im = new Imobiliaria(cnpj, nome);
        imobiliarias.add(im);
    }

    public List<Imobiliaria> listarImobiliarias() {
        return imobiliarias;
    }

    public Imobiliaria buscarPorCnpj(String cnpj) {
        for (Imobiliaria im : imobiliarias) {
            if (im.getCnpj().equals(cnpj)) {
                return im;
            }
        }
        return null;
    }
}
