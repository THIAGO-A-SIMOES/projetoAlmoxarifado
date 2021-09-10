package Classes;

public class RelOrdemPedido {
    private int codOrdemCompra;
    private int codPedidoCompra;

    public RelOrdemPedido() {
    }

    public RelOrdemPedido(int codOrdemCompra, int codPedidoCompra) {
        this.codOrdemCompra = codOrdemCompra;
        this.codPedidoCompra = codPedidoCompra;
    }

    public int getOrdemCompra() {
        return codOrdemCompra;
    }

    public void setOrdemCompra(int codOrdemCompra) {
        this.codOrdemCompra = codOrdemCompra;
    }
    
    public int getCodPedidoCompra() {
        return codPedidoCompra;
    }

    public void setCodPedidoCompra(int codPedidoCompra) {
        this.codPedidoCompra = codPedidoCompra;
    }
}