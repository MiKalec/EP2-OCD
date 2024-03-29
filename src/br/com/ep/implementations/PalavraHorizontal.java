package br.com.ep.implementations;

import java.util.Arrays;

public class PalavraHorizontal {
    public static final int qtdBitsPalavra = 32;
    private byte[] bits;

    public PalavraHorizontal() {
        this.bits = new byte[32];
    }

    public PalavraHorizontal(long resp) {
        this.bits = new byte[32];
        String s = Long.toBinaryString(resp);
        int cont = 31;

        for (int i = s.length() - 1; i >= 0 && cont >= 0; --i) {
            this.bits[cont--] = (byte) (s.charAt(i) == '1' ? 1 : 0);
        }

    }

    public PalavraHorizontal(PalavraHorizontal a, int from, int to) {
        this.bits = new byte[32];
        if (from >= 0 && from < 32 && from <= to && to <= 32) {
            int b = 31;

            for (int i = to; i >= from; --i) {
                this.bits[b] = a.bits[i];
                --b;
            }
        }

    }

    public PalavraHorizontal(byte[] p) {
        if (p.length == 32) {
            this.bits = p;
        }

    }

    public void setValue(byte[] b) {
        if (b.length >= 32) {
            for (int i = 0; i < 32; ++i) {
                this.bits[i] = b[i];
            }
        }

    }

    byte[] getBits() {
        return this.bits;
    }

    public byte[] getBits(int from, int to) {
        return Arrays.copyOfRange(this.bits, from, to + 1);
    }

    private String bitString(byte[] bits) {
        StringBuilder r = new StringBuilder();

        for (byte b : bits) {
            r.append(b);
        }

        return r.toString();
    }

    public String toString() {
        return Arrays.toString(this.bits);
    }

    public int getIntValue() {
        long l = Long.parseLong(this.bitString(this.bits), 2);
        return (int) l;
    }
}

