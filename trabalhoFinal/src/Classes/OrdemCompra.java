package Classes;

public class OrdemCompra {


    public int getQuantestocada() {
        return quantestocada;
    }

    
    public void setQuantestocada(int quantestocada) {
        this.quantestocada = quantestocada;
    }

   
    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    private int codOrdemCompra;
    private float valUnitario;
    private int quantidade;
    private int codCliente;
    private int codFornecedor;
    private int codTipoProduto;
    private int situacao;
    private int quantestocada;

    public OrdemCompra(int codOrdemCompra, float valUnitario, int quantidade, int codCliente, int codFornecedor, int codTipoProduto, int situacao, int quantestocada) {
        this.codOrdemCompra = codOrdemCompra;
        this.valUnitario = valUnitario;
        this.quantidade = quantidade;
        this.codCliente = codCliente;
        this.codFornecedor = codFornecedor;
        this.codTipoProduto = codTipoProduto;
        this.situacao = situacao;
        this.quantestocada = quantestocada;
    }

   
    
    

    public OrdemCompra() {
    }

  
    
    public int getCodOrdemCompra() {
        return codOrdemCompra;
    }

    public void setCodOrdemCompra(int codOrdemCompra) {
        this.codOrdemCompra = codOrdemCompra;
    }

    public float getValUnitario() {
        return valUnitario;
    }

    public void setValUnitario(float valUnitario) {
        this.valUnitario = valUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public int getCodTipoProduto() {
        return codTipoProduto;
    }

    public void setCodTipoProduto(int codTipoProduto) {
        this.codTipoProduto = codTipoProduto;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
                
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.codOrdemCompra;
        return hash;
    }
    
    

}
