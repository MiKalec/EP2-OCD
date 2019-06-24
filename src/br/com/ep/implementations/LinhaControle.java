package br.com.ep.implementations;

import java.util.Arrays;

public class LinhaControle {
    private static final int tamLinha = 39;
    private String funcao;
    private byte[] linha;

    public LinhaControle(byte[] l, String funcao) {
        if (l.length == 39) {
            this.linha = l;
        } else {
            this.linha = new byte[39];
        }

        this.funcao = funcao;
    }

    public String getDesc() {
        return this.funcao;
    }

    public byte[] getLinha() {
        return this.linha;
    }

    public byte[] getPortas() {
        return Arrays.copyOfRange(this.linha, 0, 32);
    }

    public byte getJmpCond() {
        return this.linha[32];
    }

    public byte getProx() {
        return this.linha[33];
    }

    public byte getULA() {
        return this.linha[34];
    }

    public byte[] getRWAV() {
        return Arrays.copyOfRange(this.linha, 35, 38);
    }

    public byte getDecode() {
        return this.linha[38];
    }
}
