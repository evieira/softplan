package br.com.softplan.teste.sajadv.model.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "responsavel")
public class ResponsavelJB extends ObjetoJB {

    private String nome;
    private String cpf;
    private List<ProcessoJB> listaProcesso;

    public ResponsavelJB() {
    }

    public ResponsavelJB(long id, String nome, String cpf) {
        super(id);
        this.nome = nome;
        this.cpf = cpf;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Column(length = 150, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(length = 11, nullable = false, unique = true)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @ManyToMany(mappedBy = "listaResponsaveis", targetEntity = ProcessoJB.class)
    public List<ProcessoJB> getListaProcesso() {
        return listaProcesso;
    }

    public void setListaProcesso(List<ProcessoJB> listaProcesso) {
        this.listaProcesso = listaProcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResponsavelJB that = (ResponsavelJB) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        return cpf != null ? cpf.equals(that.cpf) : that.cpf == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        return result;
    }
}
