package br.com.ep.implementations;

import java.util.Arrays;

public class RegCode {
    private byte[] code;

    public RegCode(PalavraHorizontal mem) {
        this.code = Arrays.copyOfRange(mem.getBits(), 29, 31);
    }

    public RegCode(byte[] b) {
        if (b.length == 3) {
            this.code = b;
        }

    }

    byte[] getCode() {
        return this.code;
    }

    public String toString() {
        return Arrays.toString(this.code);
    }

    public int hashCode() {
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
            RegCode other = (RegCode) obj;
            return Arrays.equals(this.code, other.code);
        }
    }
}
