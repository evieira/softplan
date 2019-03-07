package br.com.softplan.teste.sajadv.model.dao;

import br.com.softplan.teste.sajadv.model.bean.ProcessoJB;
import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

public interface ProcessoDAO extends DefaultDAO<ProcessoJB> {

    boolean temProcessoPaiPorId(Long id);
    List<ProcessoJB> getListaPorIdResponsavel(long idResponsavel);
    List<ProcessoJB> getListaPorFiltros(String numeroProcesso, LocalDate dtInicio, LocalDate dtFim, SituacaoEnum situacao, boolean segredoJustica, String pastaFisicaCliente, String nomeResponsavel);
    List<ProcessoJB> getListaPorNumeroProcesso(String numeroProcesso);
    ProcessoJB getProcessoPorIdPai(long idProcesso);
    SituacaoEnum getSituacaoPorIdProcesso(long idProcesso);
}
