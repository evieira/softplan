package br.com.softplan.teste.sajadv.model.dao;

import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;

import java.util.List;

public interface ResponsavelDAO extends DefaultDAO<ResponsavelJB> {

    boolean responsavelVinculadoAProcessoPorIdResponsavel(Long idResponsavel);

    List<ResponsavelJB> getListaPorNomeCpfNumeroProcesso(String nome, String cpf, String numeroProcesso);
}
