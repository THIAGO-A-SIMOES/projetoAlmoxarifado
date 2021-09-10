package Classes;

public class TipoProduto {
    private int codTipoProduto;
    private String descricao;
    private int qtdeMaxRecept;
    private String UPC;
    private int codCategoria;
    private int situacao;

    public TipoProduto() {
    }

    public TipoProduto(int codTipoProduto, String descricao, int qtdeMaxRecept, String UPC, int codCategoria, int situacao) {
        this.codTipoProduto = codTipoProduto;
        this.descricao = descricao;
        this.qtdeMaxRecept = qtdeMaxRecept;
        this.UPC = UPC;
        this.codCategoria = codCategoria;
        this.situacao = situacao;
    }

   
    public int getCodTipoProduto() {
        return codTipoProduto;
    }

    public void setCodTipoProduto(int codTipoProduto) {
        this.codTipoProduto = codTipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdeMaxRecept() {
        return qtdeMaxRecept;
    }

    public void setQtdeMaxRecept(int qtdeMaxRecept) {
        this.qtdeMaxRecept = qtdeMaxRecept;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codTipoProduto;
        return hash;
    }

    public String getUPC() {
        return UPC;
    }

  
    public void setUPC(String UPC) {
        this.UPC = UPC;
    }

  
    public int getCodCategoria() {
        return codCategoria;
    }

  
    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

 
    public int getSituacao() {
        return situacao;
    }

  
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    
    
}