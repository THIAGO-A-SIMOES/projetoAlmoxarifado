
package Classes;


public class Estado {
    
    private int codEstado;
    private String descricao;
    private String sigla;

    public Estado() {
    }

    public Estado(int codEstado, String descricao, String sigla) {
        this.codEstado = codEstado;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    
    public int getCodEstado() {
        return codEstado;
    }


    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    
    public String getDescricao() {
        return descricao;
    }

  
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
    public String getSigla() {
        return sigla;
    }

  
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode()== hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codEstado;
        return hash;
    }
    
    
    
    
}
