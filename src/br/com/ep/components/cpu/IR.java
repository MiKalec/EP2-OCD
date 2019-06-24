package br.com.ep.components.cpu;

import br.com.ep.implementations.OpCode;
import br.com.ep.implementations.PalavraHorizontal;

public class IR extends Registrador {
    private OpCode opcode;
    private Registrador p1;
    private Registrador p2;

    public IR(String nome, String codigo, Registrador p1, Registrador p2) {
        super(nome, codigo);
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setWord(PalavraHorizontal mem, int idPorta) {
        super.setWord(mem, idPorta);
        this.opcode = new OpCode(mem);

        try {
            switch (UC.getQtdRegs(this.opcode)) {
                case 0:
                    this.p1.setWord(new PalavraHorizontal((long) ((int) Long.parseLong(this.parse(mem.getBits(5, 31)), 2))), -1);
                    break;
                case 1:
                    this.p1.setWord(new PalavraHorizontal(mem, 5, 7), -1);
                    this.p2.setWord(new PalavraHorizontal((long) ((int) Long.parseLong(this.parse(mem.getBits(8, 31)), 2))), -1);
                    break;
                case 2:
                    this.p2.setWord(new PalavraHorizontal(mem, 5, 7), -1);
                    this.p1.setWord(new PalavraHorizontal((long) ((int) Long.parseLong(this.parse(mem.getBits(8, 31)), 2))), -1);
                    break;
                case 3:
                    this.p1.setWord(new PalavraHorizontal(mem, 5, 7), -1);
                    this.p2.setWord(new PalavraHorizontal(mem, 8, 10), -1);
                    break;
                case 4:
                    int fim = 17;
                    this.p1.setWord(new PalavraHorizontal((long) ((int) Long.parseLong(this.parse(mem.getBits(5, fim)), 2))), -1);
                    this.p2.setWord(new PalavraHorizontal((long) ((int) Long.parseLong(this.parse(mem.getBits(fim + 1, 31)), 2))), -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String parse(byte[] bits) {
        StringBuilder resp = new StringBuilder();
        if (bits.length > 0) {
            int size = 32;

            for (int i = bits.length - 1; i >= 0; --i) {
                resp.insert(0, bits[i]);
                --size;
            }

            while (size > 0) {
                resp.insert(0, (bits[0] == 1 ? "1" : "0"));
                --size;
            }
        }

        return resp.toString();
    }

    public PalavraHorizontal getP1() {
        return this.p1.getWord();
    }

    public PalavraHorizontal getP2() {
        return this.p2.getWord();
    }

    public OpCode getOpCode() {
        return this.opcode;
    }
}