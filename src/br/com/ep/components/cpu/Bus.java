package br.com.ep.components.cpu;

import br.com.ep.interfaces.Observer;
import br.com.ep.implementations.PalavraHorizontal;
import br.com.ep.interfaces.Subject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Bus implements Subject {
    private PalavraHorizontal palavraAtual;
    private List<Observer> observers = new LinkedList();

    public Bus() {
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers() {
        Iterator var2 = this.observers.iterator();

        while (var2.hasNext()) {
            Observer o = (Observer) var2.next();
            o.notify(this);
        }

    }

    public PalavraHorizontal getWord() {
        return this.palavraAtual;
    }

    public void setWord(PalavraHorizontal p) {
        this.palavraAtual = p;
        this.notifyObservers();
    }
}
