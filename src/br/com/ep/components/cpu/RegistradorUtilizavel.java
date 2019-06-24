package br.com.ep.components.cpu;

import br.com.ep.implementations.RegCode;

public class RegistradorUtilizavel extends Registrador {
    private RegCode id;

    public RegistradorUtilizavel(String nome, String codigo, RegCode id) {
        super(nome, codigo);
        this.id = id;
    }

    public RegCode getID() {
        return this.id;
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.id.hashCode();
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
            RegistradorUtilizavel other = (RegistradorUtilizavel) obj;
            return this.id.equals(other.id);
        }
    }
}