package br.com.softplan.teste.sajadv.model.dto;

import br.com.softplan.teste.sajadv.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoDTO {

    private long id;
    private String numeroProcessoUnificado;
    private String pastaFisicaCliente;
    private String descricao;
    private LocalDate dtDistribuicao;
    private boolean segredoDeJustica;
    private String situacao;
    private String responsaveis;
    private ProcessoDTO processoPai;
    private ProcessoDTO processoFilho;
    private List<ResponsavelDTO> listaResponsavel;

    public ProcessoDTO() {
        this.setListaResponsavel(new ArrayList<>());
    }

    public ProcessoDTO(long id, String numeroProcessoUnificado, String pastaFisicaCliente, String descricao,
                       LocalDate dtDistribuicao, boolean segredoDeJustica, String situacao, ProcessoDTO processoPai,
                       ProcessoDTO processoFilho) {
        this.id = id;
        this.numeroProcessoUnificado = numeroProcessoUnificado;
        this.pastaFisicaCliente = pastaFisicaCliente;
        this.descricao = descricao;
        this.dtDistribuicao = dtDistribuicao;
        this.segredoDeJustica = segredoDeJustica;
        this.situacao = situacao;
        this.processoPai = processoPai;
        this.processoFilho = processoFilho;
        this.setListaResponsavel(new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroProcessoUnificado() {
        return numeroProcessoUnificado;
    }

    public void setNumeroProcessoUnificado(String numeroProcessoUnificado) {
        this.numeroProcessoUnificado = numeroProcessoUnificado;
    }

    public String getPastaFisicaCliente() {
        return pastaFisicaCliente;
    }

    public void setPastaFisicaCliente(String pastaFisicaCliente) {
        this.pastaFisicaCliente = pastaFisicaCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDtDistribuicao() {
        return dtDistribuicao;
    }

    public void setDtDistribuicao(LocalDate dtDistribuicao) {
        this.dtDistribuicao = dtDistribuicao;
    }

    public boolean isSegredoDeJustica() {
        return segredoDeJustica;
    }

    public void setSegredoDeJustica(boolean segredoDeJustica) {
        this.segredoDeJustica = segredoDeJustica;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(String responsaveis) {
        this.responsaveis = responsaveis;
    }

    public ProcessoDTO getProcessoPai() {
        return processoPai;
    }

    public ProcessoDTO getProcessoFilho() {
        return processoFilho;
    }

    public void setProcessoFilho(ProcessoDTO processoFilho) {
        this.processoFilho = processoFilho;
    }

    public void setProcessoPai(ProcessoDTO processoPai) {
        this.processoPai = processoPai;
    }

    public List<ResponsavelDTO> getListaResponsavel() {
        return listaResponsavel;
    }

    public void setListaResponsavel(List<ResponsavelDTO> listaResponsavel) {
        this.listaResponsavel = listaResponsavel;
    }
}
