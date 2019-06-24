package br.com.ep.components;

import br.com.ep.components.cpu.Bus;
import br.com.ep.components.cpu.UC;
import br.com.ep.interfaces.Componente;
import br.com.ep.interfaces.Observer;
import br.com.ep.interfaces.Subject;

import java.util.LinkedList;
import java.util.List;

public class Porta implements Observer, Subject {
    protected boolean aberta;
    protected boolean in;
    protected Bus barramento;
    protected Componente c;
    protected int id;
    private List<Observer> obs;

    public Porta(boolean in, int id, Bus b, Componente c, UC uc) {
        this.c = c;
        this.in = in;
        this.id = id;
        this.barramento = b;
        uc.addObserver(this);
        this.barramento.addObserver(this);
        this.obs = new LinkedList();
    }

    public void notify(Subject s) {
        if (s instanceof UC) {
            this.aberta = ((UC) s).getStatus(this.id);
            if (this.aberta && ((UC) s).podeAtualizar() && !this.in) {
                this.barramento.setWord(this.c.getWord());
            }

            this.notifyObservers();
        } else if (s instanceof Bus && this.aberta && this.in) {
            this.c.setWord(this.barramento.getWord(), this.id);
        }

    }

    public void addObserver(Observer o) {
        this.obs.add(o);
    }

    public void notifyObservers() {

        for (Observer o : this.obs) {
            o.notify(this);
        }

    }
}