package br.com.ep.components.cpu;

import br.com.ep.interfaces.Componente;
import br.com.ep.implementations.PalavraHorizontal;

public class Registrador implements Componente {
    private String nome;
    private String codigo;
    private PalavraHorizontal mem;

    public Registrador(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.mem = new PalavraHorizontal();
    }

    public String getNome() {
        return this.nome;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String toString() {
        return this.nome;
    }

    public void setWord(PalavraHorizontal palavra, int idPorta) {
        this.mem = palavra;
    }

    public PalavraHorizontal getWord() {
        return this.mem;
    }

    public void reset() {
        this.mem = new PalavraHorizontal();
    }
}
