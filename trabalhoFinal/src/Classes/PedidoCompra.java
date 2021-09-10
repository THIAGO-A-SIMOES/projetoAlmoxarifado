package Classes;

public class PedidoCompra {

  
    public int getSituacao() {
        return situacao;
    }

    
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    private int codPedidoCompra;
    private float valTotal;
    private int situacao;

    public PedidoCompra() {
    }

    public PedidoCompra(int codPedidoCompra, float valTotal, int situacao) {
        this.codPedidoCompra = codPedidoCompra;
        this.valTotal = valTotal;
        this.situacao = situacao;
    }

   

    public int getCodPedidoCompra() {
        return codPedidoCompra;
    }

    public void setCodPedidoCompra(int CodPedidoCompra) {
        this.codPedidoCompra = codPedidoCompra;
    }

    public float getValTotal() {
        return valTotal;
    }

    public void setValTotal(float valTotal) {
        this.valTotal = valTotal;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codPedidoCompra;
        return hash;
    }
    
    
}