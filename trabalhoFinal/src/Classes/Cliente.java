
package Classes;



public class Cliente{

   
    public int getSituacao() {
        return situacao;
    }

   
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    private int codCliente;
    private int codCidade;
    private String cnpj;
    private String nome;
    private String contato;
    private String endereco;
    private int situacao;

    public Cliente() {
    }

    public Cliente(int codCliente, int codCidade, String cnpj, String nome, String contato, String endereco, int situacao) {
        this.codCliente = codCliente;
        this.codCidade = codCidade;
        this.cnpj = cnpj;
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.situacao = situacao;
    }


  
    public int getCodCliente() {
        return codCliente;
    }

    
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

   
    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }


    public String getCnpj() {
        return cnpj;
    }

   
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

   
    public String getNome() {
        return nome;
    }

  
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String getContato() {
        return contato;
    }

  
    public void setContato(String contato) {
        this.contato = contato;
    }

  
    public String getEndereco() {
        return endereco;
    }

   
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        
        return o.hashCode() ==  hashCode();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codCliente;
        return hash;
    }
    
    
    
    
    
    
    
}
