package br.com.ep.interfaces;

public interface Subject {
    void addObserver(Observer var1);

    void notifyObservers();
}