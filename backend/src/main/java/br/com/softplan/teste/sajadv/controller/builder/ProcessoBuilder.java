package br.com.softplan.teste.sajadv.controller.builder;

import br.com.softplan.teste.sajadv.model.bean.ProcessoJB;
import br.com.softplan.teste.sajadv.model.dto.ProcessoDTO;
import br.com.softplan.teste.sajadv.util.Util;

import java.util.ArrayList;

public class ProcessoBuilder {

    public static ProcessoJB getProcessoJB(ProcessoDTO processoDTO) {
        if(processoDTO == null) {
            return null;
        }
        ProcessoJB processo = new ProcessoJB(processoDTO.getId(), processoDTO.getNumeroProcessoUnificado(),
                processoDTO.getPastaFisicaCliente(), processoDTO.getDescricao(), processoDTO.getDtDistribuicao(),
                processoDTO.isSegredoDeJustica(), processoDTO.getSituacao(), processoDTO.getProcessoPai() == null ? null : new ProcessoJB(processoDTO.getProcessoPai().getId()), new ArrayList<>());

        processoDTO.getListaResponsavel().stream().forEach(responsavelDTO -> processo.getListaResponsaveis().add(ResponsalvelBuilder.getResponsavelJB(responsavelDTO)));

        return processo;
    }

    public static ProcessoDTO getProcessoDTO(ProcessoJB processo) {
        if(processo == null) {
            return null;
        }
        ProcessoDTO processoDTO = new ProcessoDTO(processo.getId(), Util.formataNumeroProcessoUnificado(processo.getNumeroProcessoUnificado()),
                processo.getPastaFisicaCliente(), processo.getDescricao(), processo.getDtDistribuicao(),
                processo.isSegredoDeJustica(), processo.getSituacao().toString(),
                getProcessoDTO(processo.getProcessoVinculado()), getProcessoFilhoDTO(processo.getProcessoFilho()));
        StringBuffer responsaveis = new StringBuffer();
        processo.getListaResponsaveis().stream().forEach(responsavelJB -> {
            processoDTO.getListaResponsavel().add(ResponsalvelBuilder.getResponsavelDTO(responsavelJB));
            responsaveis.append(responsavelJB.getNome());
            responsaveis.append(" - ");
        });
        responsaveis.append(";");
        processoDTO.setResponsaveis(responsaveis.toString().replace(" - ;", ""));
        return processoDTO;
    }

    public static ProcessoDTO getProcessoFilhoDTO(ProcessoJB processo) {
        if(processo == null) {
            return null;
        }
        ProcessoDTO processoDTO = new ProcessoDTO(processo.getId(), Util.formataNumeroProcessoUnificado(processo.getNumeroProcessoUnificado()),
                processo.getPastaFisicaCliente(), processo.getDescricao(), processo.getDtDistribuicao(),
                processo.isSegredoDeJustica(), processo.getSituacao().toString(),
                null, getProcessoFilhoDTO(processo.getProcessoFilho()));
        return processoDTO;
    }
}
