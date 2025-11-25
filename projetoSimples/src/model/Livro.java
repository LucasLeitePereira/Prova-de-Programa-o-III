package model;

public class Livro {
    public String titulo;
    public String autor;
    public int ano;

    public Livro() {
    }
    public Livro(String titulo, String autor, int ano) {
        this.titulo = setTitulo(titulo);
        this.autor = setAutor(autor);
        this.ano = setAno(ano);
    }

    public String setTitulo(String titulo) {
        return titulo;
    }

    public String setAutor(String autor) {
        return autor;
    }

    public Integer setAno(int ano) {
        return ano;
    }

}


