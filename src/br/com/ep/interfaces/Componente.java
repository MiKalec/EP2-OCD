package br.com.ep.interfaces;

import br.com.ep.implementations.PalavraHorizontal;

public interface Componente {
    void setWord(PalavraHorizontal var1, int var2);

    PalavraHorizontal getWord();

    void reset();
}
