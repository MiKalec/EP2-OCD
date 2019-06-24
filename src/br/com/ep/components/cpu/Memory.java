package br.com.ep.components.cpu;

import br.com.ep.interfaces.Componente;
import br.com.ep.implementations.PalavraHorizontal;

import java.util.Map;
import java.util.TreeMap;

public class Memory implements Componente {
    boolean av;
    boolean w;
    boolean r;
    boolean endOk;
    Map<Long, PalavraHorizontal> palavras = new TreeMap();
    private long address;
    private PalavraHorizontal valor;
    private String cod;

    public Memory(String cod) {
        this.av = this.r = this.w = this.endOk = false;
        this.cod = cod;
    }

    public String getCodigo() {
        return this.cod;
    }

    public Map<Long, PalavraHorizontal> getMap() {
        return this.palavras;
    }

    public void setFlags(byte[] flags) {
        if (flags.length == 3) {
            this.r = flags[0] == 1;
            this.w = flags[1] == 1;
            this.av = flags[2] == 1;
        }

    }

    public void setWord(PalavraHorizontal palavra, int idPorta) {
        if (this.av && !this.endOk) {
            this.address = (long) palavra.getIntValue();
            this.endOk = true;
        } else if (this.w && this.av) {
            this.valor = palavra;
            if (this.address >= 0L) {
                this.palavras.put(this.address, this.valor);
            }

            this.endOk = false;
        }

    }

    public PalavraHorizontal getWord() {
        if (this.av && this.endOk && this.r) {
            this.valor = (PalavraHorizontal) this.palavras.get(this.address);
        }

        this.endOk = false;
        return this.valor == null && this.address >= 0L ? new PalavraHorizontal() : this.valor;
    }

    public void insere(long posicao, PalavraHorizontal val) {
        this.palavras.put(posicao, val);
    }

    public void reset() {
        this.av = this.r = this.w = false;
        this.palavras.clear();
    }
}
