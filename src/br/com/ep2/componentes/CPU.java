package br.com.ep2.componentes;

public class CPU {
    public final UnidadeControle unidadeControle = new UnidadeControle();
    public final UnidadeLogicaAritmetica unidadeLogicaAritmetica = new UnidadeLogicaAritmetica();
    public final Barramento barramento = new Barramento();

    public void start(){
        unidadeControle.executa();

    }


}
