
package Classes;

public class Categoria {
    
    private int codCategoria;
    private String descricao;
    private int situacao;

    public Categoria() {
    }

    public Categoria(int codCategoria, String descricao, int situacao) {
        this.codCategoria = codCategoria;
        this.descricao = descricao;
        this.situacao = situacao;
    }

  
    public int getCodCategoria() {
        return codCategoria;
    }

   
    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

   
    public String getDescricao() {
        return descricao;
    }

  
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
    public int getSituacao() {
        return situacao;
    }

   
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.codCategoria;
        return hash;
    }
    
    
    
    
}
