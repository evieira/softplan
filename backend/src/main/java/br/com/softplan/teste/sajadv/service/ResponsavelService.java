package br.com.softplan.teste.sajadv.service;

import br.com.softplan.teste.sajadv.exception.DuplicatedException;
import br.com.softplan.teste.sajadv.exception.NotNullException;
import br.com.softplan.teste.sajadv.exception.SoftplanException;
import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ResponsavelService {

    void salvar(ResponsavelJB responsavel) throws SoftplanException;
    void remover(Long id) throws SoftplanException;
    ResponsavelJB getPorId(Long id);
    List<ResponsavelJB> getLista();
    List<ResponsavelJB> getListaPorNomeCpfNumeroProcesso(String nome, String cpf, String numeroProcesso);
}
