package br.com.ep.components;

import br.com.ep.components.cpu.Bus;
import br.com.ep.components.cpu.UC;
import br.com.ep.components.cpu.UnidadeLogicaAritimetica;
import br.com.ep.interfaces.Componente;
import br.com.ep.interfaces.Subject;

public class PortaX extends Porta {
    private UnidadeLogicaAritimetica ula;

    public PortaX(int id, Bus b, Componente c, UnidadeLogicaAritimetica ula, UC uc) {
        super(false, id, b, c, uc);
        this.ula = ula;
    }

    public void notify(Subject s) {
        if (s instanceof UC) {
            this.aberta = ((UC) s).getStatus(this.id);
            if (this.aberta && ((UC) s).podeAtualizar()) {
                this.ula.setWord(this.c.getWord(), this.id);
            }

            this.notifyObservers();
        }

    }
}

