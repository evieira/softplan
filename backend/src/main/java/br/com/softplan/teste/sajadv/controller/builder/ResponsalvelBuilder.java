package br.com.softplan.teste.sajadv.controller.builder;

import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;
import br.com.softplan.teste.sajadv.model.dto.ResponsavelDTO;
import br.com.softplan.teste.sajadv.util.Util;

public class ResponsalvelBuilder {

    public static ResponsavelJB getResponsavelJB(ResponsavelDTO responsavelDTO) {
        return new ResponsavelJB(responsavelDTO.getId(), responsavelDTO.getNome(),
                responsavelDTO.getCpf().replace(".", "").replace("-", ""));
    }

    public static ResponsavelDTO getResponsavelDTO(ResponsavelJB responsavel) {
        return new ResponsavelDTO(responsavel.getId(), responsavel.getNome(), responsavel.getCpf());
    }

    public static ResponsavelDTO getResponsavelConsultaDTO(ResponsavelJB responsavel) {
        ResponsavelDTO responsavelConsulta = new ResponsavelDTO(responsavel.getId(),
                responsavel.getNome(), Util.formatarCPF(responsavel.getCpf()));
        StringBuffer processos = new StringBuffer();
        responsavel.getListaProcesso().stream().forEach(processo -> {
            processos.append(Util.formataNumeroProcessoUnificado(processo.getNumeroProcessoUnificado()));
            processos.append(" - ");
        });
        processos.append(";");
        responsavelConsulta.setProcessos(processos.toString().replace(" - ;", "")
                .replace(";", ""));
        return responsavelConsulta;
    }
}
