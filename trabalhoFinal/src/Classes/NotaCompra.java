
package Classes;





public class NotaCompra {

    public int getCodOrdemCompra() {
        return codOrdemCompra;
    }

    
    public void setCodOrdemCompra(int codOrdemCompra) {
        this.codOrdemCompra = codOrdemCompra;
    }

    
    private int codFornecedor;
    private int codTipoProduto;
    private int codOrdemCompra;
    private int codNotaCompra;

    public NotaCompra() {
    }

    public NotaCompra(int codFornecedor, int codTipoProduto, int codOrdemCompra, int codNotaCompra) {
        this.codFornecedor = codFornecedor;
        this.codTipoProduto = codTipoProduto;
        this.codOrdemCompra = codOrdemCompra;
        this.codNotaCompra = codNotaCompra;
    }

    
 
    
    

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.getCodNotaCompra();
        return hash;
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

   
   

    
    public int getCodNotaCompra() {
        return codNotaCompra;
    }

  
    public void setCodNotaCompra(int codNotaCompra) {
        this.codNotaCompra = codNotaCompra;
    }
    
    
    
            
    
  
}
