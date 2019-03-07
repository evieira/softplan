package br.com.softplan.teste.sajadv.controller.rest;

import br.com.softplan.teste.sajadv.controller.builder.ProcessoBuilder;
import br.com.softplan.teste.sajadv.exception.*;
import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;
import br.com.softplan.teste.sajadv.model.dto.ProcessoDTO;
import br.com.softplan.teste.sajadv.model.dto.SituacaoDTO;
import br.com.softplan.teste.sajadv.service.ProcessoService;
import br.com.softplan.teste.sajadv.util.ResponseUtil;
import br.com.softplan.teste.sajadv.util.Util;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/processos")
public class ProcessoController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    private ProcessoService processoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response salvar(ProcessoDTO processoDTO) {
        try {
            processoService.savar(ProcessoBuilder.getProcessoJB(processoDTO));
            return ResponseUtil.sucesso("Processo salvo com sucesso");
        } catch (DataTooLongException e) {
            return ResponseUtil.invalido(e.getMessage());
        } catch (ListaResponsaveisException e) {
            return ResponseUtil.invalido(e.getMessage());
        } catch (DuplicatedException e) {
            return ResponseUtil.invalido(e.getMessage());
        } catch (SoftplanException e) {
            return ResponseUtil.invalido(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseUtil.erroInterno("Erro ao salvar o processo");
        }
    }

    @DELETE
    @Path("/id/{id}")
    public Response remover(@PathParam("id") long id) {
        try {
            processoService.remover(id);
            return ResponseUtil.sucesso("Processo removido com sucesso");
        } catch (NotNullException e) {
            logger.info(e.getMessage());
            return ResponseUtil.invalido(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseUtil.erroInterno("Erro ao remover o processo");
        }
    }

    @GET
    @Path("/porIdResponsavel/idResponsavel/{idResponsavel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaPorIdResponsavel(@PathParam("idResponsavel") long idResponsavel) {
        List<ProcessoDTO> listaProcesso = new ArrayList<>();
        processoService.getListaPorIdResponsavel(idResponsavel).stream()
                .forEach(processoJB -> listaProcesso.add(ProcessoBuilder.getProcessoDTO(processoJB)));
        return ResponseUtil.sucesso(listaProcesso);
    }

    @GET
    @Path("/listaSituacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaSituacao() {
        List<SituacaoDTO> listaSituacao = new ArrayList<>();
        listaSituacao.add(new SituacaoDTO(SituacaoEnum.ARQUIVADO.toString(), "ARQUIVADO"));
        listaSituacao.add(new SituacaoDTO(SituacaoEnum.DESMEMBRADO.toString(), "DESMEMBRADO"));
        listaSituacao.add(new SituacaoDTO(SituacaoEnum.EM_ANDAMENTO.toString(), "EM_ANDAMENTO"));
        listaSituacao.add(new SituacaoDTO(SituacaoEnum.EM_RECURSO.toString(), "EM_RECURSO"));
        listaSituacao.add(new SituacaoDTO(SituacaoEnum.FINALIZADO.toString(), "FINALIZADO"));
        return ResponseUtil.sucesso(listaSituacao);
    }

    @GET
    @Path("/numeroProcesso/{numeroProcesso}/dtInicioDistribuicao/{dtInicioDistribuicao}/dtFimDistribuicao/{dtFimDistribuicao}/situacao/{situacao}/segredoJustica/{segredoJustica}/pastaFisicaCliente/{pastaFisicaCliente}/responsavel/{responsavel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaPorFiltros(@PathParam("numeroProcesso") String numeroProcesso,
                                       @PathParam("dtInicioDistribuicao") String dtInicioDistribuicao,
                                       @PathParam("dtFimDistribuicao") String dtFimDistribuicao,
                                       @PathParam("situacao") SituacaoEnum situacao,
                                       @PathParam("segredoJustica") boolean segredoJustica,
                                       @PathParam("pastaFisicaCliente") String pastaFisicaCliente,
                                       @PathParam("responsavel") String responsavel) {

        numeroProcesso = numeroProcesso.equals("undefined") ? null : numeroProcesso;
        numeroProcesso = numeroProcesso == null ? numeroProcesso : numeroProcesso.replace("-", "").replace(".", "");
        pastaFisicaCliente = pastaFisicaCliente.equals("undefined") ? null : pastaFisicaCliente;
        responsavel = responsavel.equals("undefined") ? null : responsavel;
        dtInicioDistribuicao = dtInicioDistribuicao.equals("undefined") ? null : dtInicioDistribuicao;
        dtFimDistribuicao = dtFimDistribuicao.equals("undefined") ? null : dtFimDistribuicao;
        List<ProcessoDTO> listaProcesso = new ArrayList<>();

        processoService.getListaPorFiltros(numeroProcesso,
                Util.stringToDate(dtInicioDistribuicao),
                Util.stringToDate(dtFimDistribuicao),
                situacao,
                segredoJustica,
                pastaFisicaCliente,
                responsavel).forEach(processoJB -> listaProcesso.add(ProcessoBuilder.getProcessoDTO(processoJB)));

        return ResponseUtil.sucesso(listaProcesso);
    }

    @GET
    @Path("/numeroProcesso/{numeroProcesso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaPorNumeroProcesso(@PathParam("numeroProcesso") String numeroProcesso) {
        List<ProcessoDTO> listaProcesso = new ArrayList<>();
        processoService.getListaPorNumeroProcesso(numeroProcesso)
                .forEach(processoJB -> listaProcesso.add(ProcessoBuilder.getProcessoDTO(processoJB)));
        return ResponseUtil.sucesso(listaProcesso);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessoPorId(@PathParam("id") long id) {
        return ResponseUtil.sucesso(ProcessoBuilder.getProcessoDTO(processoService.getPorId(id)));
    }

}
