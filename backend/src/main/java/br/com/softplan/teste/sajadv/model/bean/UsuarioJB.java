package br.com.softplan.teste.sajadv.model.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioJB extends ObjetoJB {

    private String nome;
    private String email;
    private String senha;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Column(nullable = false, length = 80)
    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    @Column(nullable = false, length = 80)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, length = 50)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

}
