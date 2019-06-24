package br.com.ep.implementations;

import java.util.Arrays;

public class OpCode {
    private byte[] code;

    public OpCode(PalavraHorizontal mem) {
        this.code = Arrays.copyOfRange(mem.getBits(), 0, 5);
    }

    public OpCode(byte[] b) {
        if (b.length == 5) {
            this.code = b;
        }

    }

    public byte[] getCode() {
        return this.code;
    }

    public String toString() {
        return Arrays.toString(this.code);
    }

    public int hashCode() {
        boolean prime = true;
        int result = 1;
        result = 31 * result + Arrays.hashCode(this.code);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            OpCode other = (OpCode) obj;
            return Arrays.equals(this.code, other.code);
        }
    }
}
