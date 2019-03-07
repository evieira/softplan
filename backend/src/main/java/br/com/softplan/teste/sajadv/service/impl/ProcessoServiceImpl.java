package br.com.softplan.teste.sajadv.service.impl;

import br.com.softplan.teste.sajadv.exception.DataTooLongException;
import br.com.softplan.teste.sajadv.exception.DuplicatedException;
import br.com.softplan.teste.sajadv.exception.ListaResponsaveisException;
import br.com.softplan.teste.sajadv.exception.SoftplanException;
import br.com.softplan.teste.sajadv.model.bean.ProcessoJB;
import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;
import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;
import br.com.softplan.teste.sajadv.model.dao.ProcessoDAO;
import br.com.softplan.teste.sajadv.service.ProcessoService;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProcessoServiceImpl implements ProcessoService {

    @Inject
    private ProcessoDAO processoDAO;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void savar(ProcessoJB processo) throws SoftplanException {
        if(processo.getListaResponsaveis().isEmpty() || processo.getListaResponsaveis().size() > 3) {
            throw new ListaResponsaveisException("O processo deve ter entre 1 e 3 responsáveis");
        }

        if(processo.getNumeroProcessoUnificado() == null) {
            throw new SoftplanException("Número do processo unificado é obrigatrio");
        }

        if(contaProcessosNaHierarquia(getProcessoPorIdPai(processo.getId())) > 4) {
            throw new SoftplanException("Hierarquia deve ter no máximo 4 níveis");
        }

        if(verificaResponsavelDuplicado(processo)) {
            throw new SoftplanException("Não é possível vincular o mesmo responsável duas vezes no processo");
        }
        processo.setNumeroProcessoUnificado(processo.getNumeroProcessoUnificado()
                .replace("-", "").replace(".", ""));
        if(processo.getDtDistribuicao() != null && LocalDate.now().isBefore(processo.getDtDistribuicao())) {
            throw new SoftplanException("Data deve ser menor  ou igual a data atual");
        }
        if(processo.getNumeroProcessoUnificado() != null && processo.getNumeroProcessoUnificado().length() > 20) {
            throw new DataTooLongException("Campo número do processo unificado deve ter no máximo 20 caracteres");
        }
        if(processo.getPastaFisicaCliente() != null && processo.getPastaFisicaCliente().length() > 50) {
            throw new DataTooLongException("Campo pasta física cliente deve ter no máximo 50 caracteres");
        }
        if(processo.getDescricao() != null && processo.getDescricao().length() > 1000) {
            throw new DataTooLongException("Campo Descrição deve ter no máximo 1000 caracteres");
        }
        if(processo.isNew()) {
            try {
                processoDAO.salvar(processo);
            } catch (PersistenceException e) {
                Throwable throwable = ExceptionUtils.getRootCause(e);
                String message = throwable.getMessage();
                if(message.startsWith("ERROR: duplicate key")) {
                    throw new DuplicatedException("Número do processo unificado já cadastrado");
                }
            }
        } else {
            if(getSituacaoPorIdProcesso(processo.getId()).isFinalizado()) {
                throw new SoftplanException("Processo finalizado não pode ser alterado");
            }
            processoDAO.atualizar(processo);
        }
    }

    private Integer contaProcessosNaHierarquia(ProcessoJB processo) throws SoftplanException {
        if(processo == null) {
            return 0;
        }
        int processos = 1;
        List<ProcessoJB> listaProcessos = new ArrayList<>();
        while (processo.getProcessoFilho() != null) {
            processo = processo.getProcessoFilho();
        }
        while (processo.getProcessoVinculado() != null) {
            processos++;
            if(!listaProcessos.contains(processo)) {
                listaProcessos.add(processo);
            }
            processo = processo.getProcessoVinculado();
        }
        if(!listaProcessos.contains(processo)) {
            listaProcessos.add(processo);
        }
        if(listaProcessos.size() < processos) {
            throw new SoftplanException("Processo duplicado na hierarquia");
        }
        return processos;
    }

    private boolean verificaResponsavelDuplicado(ProcessoJB processo) {
        List<ResponsavelJB> lista = new ArrayList<>();
        processo.getListaResponsaveis().stream().forEach(responsavelJB -> {
            if(!lista.contains(responsavelJB)) {
                lista.add(responsavelJB);
            }
        });
        return lista.size() < processo.getListaResponsaveis().size();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void remover(Long id) throws SoftplanException {
        if(getSituacaoPorIdProcesso(id).isFinalizado()) {
            throw new SoftplanException("Processo finalizado não pode ser alterado");
        }
        if(processoDAO.temProcessoPaiPorId(id)) {
            throw new SoftplanException("Não é possível remover um processo vinculado à outro");
        }
        processoDAO.remover(getPorId(id));
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public ProcessoJB getPorId(Long id) {
        return processoDAO.getPorId(id);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProcessoJB> getLista() {
        return processoDAO.getLista();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProcessoJB> getListaPorIdResponsavel(long idResponsavel) {
        return processoDAO.getListaPorIdResponsavel(idResponsavel);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProcessoJB> getListaPorFiltros(String numeroProcesso, LocalDate dtInicio, LocalDate dtFim,
                                               SituacaoEnum situacao, boolean segredoJustica,
                                               String pastaFisicaCliente, String nomeResponsavel) {
        return processoDAO.getListaPorFiltros(numeroProcesso,
                dtInicio,
                dtFim,
                situacao,
                segredoJustica,
                pastaFisicaCliente,
                nomeResponsavel);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProcessoJB> getListaPorNumeroProcesso(String numeroProcesso) {
        return processoDAO.getListaPorNumeroProcesso(numeroProcesso);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public ProcessoJB getProcessoPorIdPai(long idProcesso) {
        return processoDAO.getProcessoPorIdPai(idProcesso);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public SituacaoEnum getSituacaoPorIdProcesso(long idProcesso) {
        return processoDAO.getSituacaoPorIdProcesso(idProcesso);
    }
}
