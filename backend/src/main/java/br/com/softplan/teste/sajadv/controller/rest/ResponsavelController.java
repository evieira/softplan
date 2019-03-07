package br.com.softplan.teste.sajadv.controller.rest;

import br.com.softplan.teste.sajadv.controller.builder.ResponsalvelBuilder;
import br.com.softplan.teste.sajadv.exception.DataTooLongException;
import br.com.softplan.teste.sajadv.exception.DuplicatedException;
import br.com.softplan.teste.sajadv.exception.NotNullException;
import br.com.softplan.teste.sajadv.model.dto.ResponsavelDTO;
import br.com.softplan.teste.sajadv.service.ResponsavelService;
import br.com.softplan.teste.sajadv.util.ResponseUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/responsaveis")
public class ResponsavelController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    private ResponsavelService responsavelService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response salvar(ResponsavelDTO responsavelDTO) {
        try {
            responsavelService.salvar(ResponsalvelBuilder.getResponsavelJB(responsavelDTO));
            return ResponseUtil.sucesso("Responsável salvo com sucesso");
        } catch (DataTooLongException e) {
            return ResponseUtil.invalido(e.getMessage());
        } catch (DuplicatedException e) {
            return ResponseUtil.erroInterno(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseUtil.erroInterno("Erro ao salvar o responsável");
        }
    }

    @DELETE
    @Path("/id/{id}")
    public Response remover(@PathParam("id") long id) {
        try {
            responsavelService.remover(id);
            return ResponseUtil.sucesso("Responsavel removido com sucesso");
        } catch (NotNullException e) {
            logger.info(e.getMessage());
            return ResponseUtil.invalido(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseUtil.erroInterno("Erro ao remover o responsável");
        }
    }

    @GET
    @Path("/nome/{nome}/cpf/{cpf}/numeroProcesso/{numeroProcesso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponsaveisPorNomeCpfNumeroProcesso(@PathParam("nome") String nome,
                                                            @PathParam("cpf") String cpf,
                                                            @PathParam("numeroProcesso") String numeroProcesso) {

        nome = nome.equals("undefined") ? null : nome;
        cpf = cpf.equals("undefined") ? null : cpf.replace(".", "").replace("-", "");
        numeroProcesso = numeroProcesso.equals("undefined") ? null : numeroProcesso.replace("-", "")
                .replace(".", "");

        List<ResponsavelDTO> listaResponsaveisDTO = new ArrayList<>();
        responsavelService.getListaPorNomeCpfNumeroProcesso(nome, cpf, numeroProcesso).stream()
                .forEach(responsavel -> listaResponsaveisDTO.add(ResponsalvelBuilder.getResponsavelConsultaDTO(responsavel)));
        return ResponseUtil.sucesso(listaResponsaveisDTO);
    }

    @GET
    @Path("/nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponsaveisPorNome(@PathParam("nome") String nome) {
        List<ResponsavelDTO> listaResponsaveisDTO = new ArrayList<>();
        responsavelService.getListaPorNomeCpfNumeroProcesso(nome, null, null).stream()
                .forEach(responsavel -> listaResponsaveisDTO.add(ResponsalvelBuilder.getResponsavelConsultaDTO(responsavel)));
        return ResponseUtil.sucesso(listaResponsaveisDTO);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponsavelPorId(@PathParam("id") long id) {
        return ResponseUtil.sucesso(ResponsalvelBuilder.getResponsavelDTO(responsavelService.getPorId(id)));
    }

}
