package br.com.epfinal;

public class Registrador {
    private String nome;
    private byte[] valor;
    private int[] portaEntrada = new int[2];
    private int[] portaSaida = new int[2];
    private byte aberto;

    public Registrador(final String nome, int portaEntrada, int portaSaida) {
        int[] auxIn = {portaEntrada, -1};
        int[] auxOut = {portaSaida, -1};
        this.nome = nome;
        this.valor = new byte[16];
        this.portaEntrada = auxIn;
        this.portaSaida = auxOut;
        this.aberto = 1; //1 aberto, 0 fechado
    }

    public Registrador(final String nome, int[] portaEntrada, int[] portaSaida) {
        this.nome = nome;
        this.valor = new byte[16];
        this.portaEntrada = portaEntrada;
        this.portaSaida = portaSaida;
        this.aberto = 1; //1 aberto, 0 fechado
    }

    public byte[] getValor() {
        return valor;
    }

    public void setValor(byte[] valor) {
        this.valor = valor;
    }

    public byte getAberto() {
        return aberto;
    }

    public void setAberto(byte aberto) {
        this.aberto = aberto;
    }

    public String getNome() {
        return nome;
    }
}
