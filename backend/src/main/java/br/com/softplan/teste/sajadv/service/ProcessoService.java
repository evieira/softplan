package br.com.softplan.teste.sajadv.service;

import br.com.softplan.teste.sajadv.exception.SoftplanException;
import br.com.softplan.teste.sajadv.model.bean.ProcessoJB;
import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;

import javax.ejb.Local;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Local
public interface ProcessoService {

    void savar(ProcessoJB processo) throws SoftplanException;
    void remover(Long id) throws SoftplanException;
    ProcessoJB getPorId(Long id);
    List<ProcessoJB> getLista();
    List<ProcessoJB> getListaPorIdResponsavel(long idResponsavel);
    List<ProcessoJB> getListaPorFiltros(String numeroProcesso, LocalDate dtInicio, LocalDate dtFim, SituacaoEnum situacao, boolean segredoJustica, String pastaFisicaCliente, String nomeResponsavel);
    List<ProcessoJB> getListaPorNumeroProcesso(String numeroProcesso);
    ProcessoJB getProcessoPorIdPai(long idProcesso);
    SituacaoEnum getSituacaoPorIdProcesso(long idProcesso);
}
