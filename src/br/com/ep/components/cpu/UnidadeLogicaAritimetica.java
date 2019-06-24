package br.com.ep.components.cpu;

import br.com.ep.implementations.PalavraHorizontal;
import br.com.ep.interfaces.Componente;

public class UnidadeLogicaAritimetica implements Componente {
    public static final byte NDA = 0;
    public static final byte ADD = 1;
    public static final byte SUB = 2;
    public static final byte DIV = 3;
    public static final byte MUL = 4;
    public static final byte INC = 5;
    public static final byte DEC = 6;
    public static final byte MOD = 7;
    private byte[] flags;
    private byte operacao;
    private PalavraHorizontal num1;
    private PalavraHorizontal num2;
    private PalavraHorizontal resultado;
    private String codigo;
    private boolean updateFlags;

    public UnidadeLogicaAritimetica(String codigo) {
        this.codigo = codigo;
        this.num1 = new PalavraHorizontal();
        this.num2 = new PalavraHorizontal();
        this.resultado = new PalavraHorizontal();
        this.flags = new byte[2];
    }

    void setOperacao(byte b, boolean updateFlags) throws Exception {
        if (b >= 1 && b <= 7) {
            this.updateFlags = updateFlags;
            this.operacao = b;
            this.calc();
        }

    }

    public boolean flagZero() {
        return this.flags[0] == 1;
    }

    public boolean flagSignal() {
        return this.flags[1] == 0;
    }

    public void setFlags(byte[] f) {
        if (f.length == 2) {
            this.flags = f;
        }

    }

    private void calc() throws Exception {
        int a = this.num1.getIntValue();
        int b = this.num2.getIntValue();
        int resp = 0;
        switch (this.operacao) {
            case 0:
            default:
                break;
            case 1:
                resp = a + b;
                break;
            case 2:
                resp = b - a;
                break;
            case 3:
                if (a == 0) {
                    throw new Exception("Divisao por 0");
                }
                resp = b / a;
                break;
            case 4:
                resp = a * b;
                break;
            case 5:
                resp = a + 1;
                break;
            case 6:
                resp = a - 1;
                break;
            case 7:
                if (a == 0) {
                    throw new Exception("Divisao por 0");
                }

                resp = b % a;
        }

        if (this.updateFlags) {
            this.flags[0] = (byte) (resp == 0 ? 1 : 0);
            this.flags[1] = (byte) (resp >= 0 ? 0 : 1);
        }

        this.resultado = new PalavraHorizontal((long) resp);
    }

    public void setWord(PalavraHorizontal palavra, int idPorta) {
        if (idPorta == 15) {
            this.num2 = palavra;
        } else {
            this.num1 = palavra;
        }

    }

    public PalavraHorizontal getWord() {
        return this.resultado;
    }

    public PalavraHorizontal getNum1() {
        return this.num1;
    }

    public PalavraHorizontal getNum2() {
        return this.num2;
    }

    public void reset() {
        this.flags[0] = 0;
        this.flags[1] = 1;
        this.num1 = new PalavraHorizontal();
        this.num2 = new PalavraHorizontal();
        this.operacao = 0;
        this.resultado = new PalavraHorizontal();
    }
}

