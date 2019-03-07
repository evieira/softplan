package br.com.softplan.teste.sajadv.model.dto;

public class SituacaoDTO {

    private String descricao;
    private String valor;

    public SituacaoDTO(String descricao, String valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
