
package Classes;




public class Receptaculo {
    
    private int codReceptaculo;
    private int codCorredor;
    private int codTipoProduto;
    private int quantidade;

    public Receptaculo() {
    }

    public Receptaculo(int codReceptaculo, int codCorredor, int codTipoProduto, int quantidade) {
        this.codReceptaculo = codReceptaculo;
        this.codCorredor = codCorredor;
        this.codTipoProduto = codTipoProduto;
        this.quantidade = quantidade;
    }

    /**
     * @return the codReceptaculo
     */
    public int getCodReceptaculo() {
        return codReceptaculo;
    }

    /**
     * @param codReceptaculo the codReceptaculo to set
     */
    public void setCodReceptaculo(int codReceptaculo) {
        this.codReceptaculo = codReceptaculo;
    }

    /**
     * @return the codCorredor
     */
    public int getCodCorredor() {
        return codCorredor;
    }

    /**
     * @param codCorredor the codCorredor to set
     */
    public void setCodCorredor(int codCorredor) {
        this.codCorredor = codCorredor;
    }

    /**
     * @return the codTipoProduto
     */
    public int getCodTipoProduto() {
        return codTipoProduto;
    }

    /**
     * @param codTipoProduto the codTipoProduto to set
     */
    public void setCodTipoProduto(int codTipoProduto) {
        this.codTipoProduto = codTipoProduto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
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
        hash = 59 * hash + this.codReceptaculo;
        return hash;
    }
    
    
    
    
    
    
    
      
}
