package br.senac.tads.pi3a.agenda;


//Importação data - pacote java util
import java.security.Timestamp;
import java.util.Date;



/**
 *
 * @author glebson.lsilva1
 */
public class Pessoa {
    
    //Declaro minhas variáveis para minha classe base
    private Long id;
    private String nome;
    private Date data_nascimento;
    private String telefone;
    private String email;
    private Timestamp data_cadastro;
    
    //Getters e Setters - Inserção - visualização e alteração
    
    
    public Long getId(){
    
        return id;    
    
    }
    public void setId(Long id){
    
        this.id = id;
    
    }
    public String getNome(){
    
        return nome;
    
    }
    public void setNome(String nome){
    
        this.nome = nome;
    
    }
    public Date getData_nascimento(){
    
        return data_nascimento;
        
    }
    public void setData_nascimento(Date data){
    
    
        this.data_nascimento = data;
    
    }
    public String getTelefone(){
    
        return telefone;
    
    }
    public void setTelefone(String tel){
    
    
        this.telefone = tel;
    
    }
    public String getEmail(){
    
        return email;
    
    }
    public void setEmail(String e){
    
        this.email = e;
    
    }
    public Timestamp getData_cadastro(){
    
        return data_cadastro;
    
    }
    public void setData_cadastro(Timestamp dt){
    
    
        this.data_cadastro = dt;
    }
}
