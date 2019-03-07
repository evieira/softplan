package br.com.softplan.teste.sajadv.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponsavelDTO {

    private long id;
    private String nome;
    private String cpf;
    private String processos;

    public ResponsavelDTO() {
    }

    public ResponsavelDTO(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProcessos() {
        return processos;
    }

    public void setProcessos(String processos) {
        this.processos = processos;
    }
}
