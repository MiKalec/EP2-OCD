package br.com.ep.components.cpu;

import br.com.ep.implementations.PalavraHorizontal;
import br.com.ep.interfaces.Observer;
import br.com.ep.interfaces.Subject;

import java.util.LinkedList;

public class Bus implements Subject {
    private PalavraHorizontal palavraAtual;
    private LinkedList observers = new LinkedList();

    public Bus() {
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (Object observer : this.observers) {
            Observer o = (Observer) observer;
            o.notify(this);
        }

    }

    public PalavraHorizontal getWord() {
        return this.palavraAtual;
    }

    public void setWord(PalavraHorizontal palavra) {
        this.palavraAtual = palavra;
        this.notifyObservers();
    }
}
