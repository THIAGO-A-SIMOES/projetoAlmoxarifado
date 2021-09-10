
package Classes;


public class Cidade {

  
    public int getSituacao() {
        return situacao;
    }

  
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    private int codCidade;
    private String descricao;
    private int codEstado;
    private int situacao;

    public Cidade() {
    }

    public Cidade(int codCidade, String descricao, int codEstado, int situacao) {
        this.codCidade = codCidade;
        this.descricao = descricao;
        this.codEstado = codEstado;
        this.situacao = situacao;
    }

  

    public int getCodCidade() {
        return codCidade;
    }

 
    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public String getDescricao() {
        return descricao;
    }

  
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
    public int getCodEstado() {
        return codEstado;
    }

   
    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    @Override
    public boolean equals(Object o) {
        
        return o.hashCode() == hashCode();
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.codCidade;
        return hash;
    }
    
    
    
    
}
