
package Classes;


public class Alojamento {
    
    
    private int codAlojamento;
    private int codCliente;

    public Alojamento() {
    }

    public Alojamento(int codAlojamento, int codCliente) {
        this.codAlojamento = codAlojamento;
        this.codCliente = codCliente;
    }

    
    public int getCodAlojamento() {
        return codAlojamento;
    }

    
    public void setCodAlojamento(int codAlojamento) {
        this.codAlojamento = codAlojamento;
    }

    
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode()  == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codAlojamento;
        return hash;
    }
    
    
    
    
    
}
