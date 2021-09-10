package Classes;

public class PedidoCliente {


    public int getSituacao() {
        return situacao;
    }

  
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    private int codPedidoCliente;
    private int codTipoProduto;
    private int codCliente;
    private int quantidade;
    private int situacao;

    public PedidoCliente() {
    }

    public PedidoCliente(int codPedidoCliente, int codTipoProduto, int codCliente, int quantidade, int situacao) {
        this.codPedidoCliente = codPedidoCliente;
        this.codTipoProduto = codTipoProduto;
        this.codCliente = codCliente;
        this.quantidade = quantidade;
        this.situacao = situacao;
    }

    

    public int getCodPedidoCliente() {
        return codPedidoCliente;
    }

    public void setCodPedidoCliente(int codPedidoCliente) {
        this.codPedidoCliente = codPedidoCliente;
    }

    public int getCodTipoProduto() {
        return codTipoProduto;
    }

    public void setCodTipoProduto(int codTipoProduto) {
        this.codTipoProduto = codTipoProduto;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode()==hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.codPedidoCliente;
        return hash;
    }
    
    
    
    
    
}
