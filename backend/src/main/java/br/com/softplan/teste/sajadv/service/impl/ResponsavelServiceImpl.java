package br.com.softplan.teste.sajadv.service.impl;

import br.com.softplan.teste.sajadv.exception.DataTooLongException;
import br.com.softplan.teste.sajadv.exception.DuplicatedException;
import br.com.softplan.teste.sajadv.exception.NotNullException;
import br.com.softplan.teste.sajadv.exception.SoftplanException;
import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;
import br.com.softplan.teste.sajadv.model.dao.ResponsavelDAO;
import br.com.softplan.teste.sajadv.service.ResponsavelService;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;


@Stateless
public class ResponsavelServiceImpl implements ResponsavelService {

    @Inject
    private ResponsavelDAO responsavelDAO;


    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void salvar(ResponsavelJB responsavel) throws SoftplanException {
        if(responsavel.isNew()) {
            try {

                if(responsavel.getNome().length() > 150) {
                    throw new DataTooLongException("Campo nome deve ter no máximo 150 caracteres.");
                }

                if(responsavel.getCpf().length() > 11) {
                    throw new DataTooLongException("Campo cpf deve ter no máximo 11 caracteres.");
                }

                responsavelDAO.salvar(responsavel);
            } catch (PersistenceException e) {
                Throwable throwable = ExceptionUtils.getRootCause(e);
                String message = throwable.getMessage();

                if(message.startsWith("ERROR: duplicate key")) {
                    throw new DuplicatedException("Cpf já cadastrado.");
                }
            }
        } else {
            responsavelDAO.atualizar(responsavel);
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void remover(Long id) throws NotNullException {
        if(responsavelDAO.responsavelVinculadoAProcessoPorIdResponsavel(id)) {
           throw new NotNullException("Responsavel vinculado à processo(s)");
        }

        responsavelDAO.remover(getPorId(id));
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public ResponsavelJB getPorId(Long id) {
        return responsavelDAO.getPorId(id);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ResponsavelJB> getLista() {
        return responsavelDAO.getLista();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ResponsavelJB> getListaPorNomeCpfNumeroProcesso(String nome, String cpf, String numeroProcesso) {
        return responsavelDAO.getListaPorNomeCpfNumeroProcesso(nome, cpf, numeroProcesso);
    }
}
