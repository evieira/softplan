package br.com.softplan.teste.sajadv.model.bean;

import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "processo")
public class ProcessoJB extends ObjetoJB {

    private String numeroProcessoUnificado;
    private String pastaFisicaCliente;
    private String descricao;
    private LocalDate dtDistribuicao;
    private boolean segredoDeJustica;
    private SituacaoEnum situacao;
    private ProcessoJB processoVinculado;
    private ProcessoJB processoFilho;
    private List<ResponsavelJB> listaResponsaveis;

    public ProcessoJB() {
    }

    public ProcessoJB(long id) {
        super(id);
    }

    public ProcessoJB(long id, String numeroProcessoUnificado, String pastaFisicaCliente, String descricao,
                      LocalDate dtDistribuicao, boolean segredoDeJustica, String situacao,
                      ProcessoJB processoVinculado, List<ResponsavelJB> listaResponsaveis) {
        super(id);
        this.numeroProcessoUnificado = numeroProcessoUnificado;
        this.pastaFisicaCliente = pastaFisicaCliente;
        this.descricao = descricao;
        this.dtDistribuicao = dtDistribuicao;
        this.segredoDeJustica = segredoDeJustica;
        this.situacao = SituacaoEnum.valueOf(situacao);
        this.processoVinculado = processoVinculado;
        this.listaResponsaveis = listaResponsaveis;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Column(length = 20, nullable = false, unique = true)
    public String getNumeroProcessoUnificado() {
        return numeroProcessoUnificado;
    }

    public void setNumeroProcessoUnificado(String numeroProcessoUnificado) {
        this.numeroProcessoUnificado = numeroProcessoUnificado;
    }

    @Column(length = 50)
    public String getPastaFisicaCliente() {
        return pastaFisicaCliente;
    }

    public void setPastaFisicaCliente(String pastaFisicaCliente) {
        this.pastaFisicaCliente = pastaFisicaCliente;
    }

    @Column(length = 1000)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    @OneToOne
    @JoinColumn(name = "idProcessoVinculado")
    public ProcessoJB getProcessoVinculado() {
        return processoVinculado;
    }

    public void setProcessoVinculado(ProcessoJB processoVinculado) {
        this.processoVinculado = processoVinculado;
    }

    @OrderBy("id ASC")
    @ManyToMany(targetEntity = ResponsavelJB.class, fetch = FetchType.EAGER)
    @JoinTable(name = "responsaveis_processo",
            joinColumns = {@JoinColumn(name = "idProcesso")},
            inverseJoinColumns = {@JoinColumn(name = "idResponsavel")})
    public List<ResponsavelJB> getListaResponsaveis() {
        return listaResponsaveis;
    }

    public void setListaResponsaveis(List<ResponsavelJB> listaResponsaveis) {
        this.listaResponsaveis = listaResponsaveis;
    }

    @OneToOne(mappedBy = "processoVinculado")
    public ProcessoJB getProcessoFilho() {
        return processoFilho;
    }

    public void setProcessoFilho(ProcessoJB processoFilho) {
        this.processoFilho = processoFilho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ProcessoJB that = (ProcessoJB) o;

        if (segredoDeJustica != that.segredoDeJustica) return false;
        if (numeroProcessoUnificado != null ? !numeroProcessoUnificado.equals(that.numeroProcessoUnificado) : that.numeroProcessoUnificado != null)
            return false;
        if (pastaFisicaCliente != null ? !pastaFisicaCliente.equals(that.pastaFisicaCliente) : that.pastaFisicaCliente != null)
            return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (dtDistribuicao != null ? !dtDistribuicao.equals(that.dtDistribuicao) : that.dtDistribuicao != null)
            return false;
        return situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (numeroProcessoUnificado != null ? numeroProcessoUnificado.hashCode() : 0);
        result = 31 * result + (pastaFisicaCliente != null ? pastaFisicaCliente.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (dtDistribuicao != null ? dtDistribuicao.hashCode() : 0);
        result = 31 * result + (segredoDeJustica ? 1 : 0);
        result = 31 * result + (situacao != null ? situacao.hashCode() : 0);
        return result;
    }
}
