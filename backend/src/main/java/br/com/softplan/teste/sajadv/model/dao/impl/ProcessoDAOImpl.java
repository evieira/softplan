package br.com.softplan.teste.sajadv.model.dao.impl;

import br.com.softplan.teste.sajadv.model.bean.ProcessoJB;
import br.com.softplan.teste.sajadv.model.bean.enumaration.SituacaoEnum;
import br.com.softplan.teste.sajadv.model.dao.ProcessoDAO;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Named
public class ProcessoDAOImpl extends DefaultDAOImpl<ProcessoJB> implements ProcessoDAO {

    @Override
    public boolean temProcessoPaiPorId(Long id) {
        String sql = "Select count(p.id) from ProcessoJB p where p.processoVinculado.id = :id";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("id", id);

        return ((Long) query.getSingleResult()) > 0;
    }

    @Override
    public List<ProcessoJB> getListaPorIdResponsavel(long idResponsavel) {
        String sql = "Select distinct p from ProcessoJB p join fetch p.listaResponsaveis where p.id in " +
                "(Select p1.id from ProcessoJB p1 join p1.listaResponsaveis r where r.id = :idResponsavel)";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("idResponsavel", idResponsavel);
        return query.getResultList();
    }

    @Override
    public List<ProcessoJB> getListaPorFiltros(String numeroProcesso, LocalDate dtInicio, LocalDate dtFim, SituacaoEnum situacao, boolean segredoJustica, String pastaFisicaCliente, String nomeResponsavel) {
        String sql = "Select p1 from ProcessoJB  p1 where p1.id in (Select distinct p.id from ProcessoJB p join p.listaResponsaveis r" +
                " where p.segredoDeJustica = :segredoJustica and p.situacao = :situacao ";
        if(numeroProcesso != null) {
            sql += " and p.numeroProcessoUnificado = :numeroProcesso ";
        }
        if(pastaFisicaCliente != null) {
            sql += " and p.pastaFisicaCliente like :pastaFisicaCliente ";
        }
        if(nomeResponsavel != null) {
            sql += " and r.nome like :nomeResponsavel";
        }
        if(dtInicio != null && dtFim != null) {
            sql += " and p.dtDistribuicao between :dtInicio and :dtFim";
        } else if(dtInicio != null) {
            sql += " and p.dtDistribuicao >= :dtInicio";
        }else if(dtFim != null) {
            sql += " and p.dtDistribuicao <= :dtFim";
        }
        sql += ")";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("segredoJustica",segredoJustica);
        query.setParameter("situacao",situacao);

        if(numeroProcesso != null) {
            query.setParameter("numeroProcesso",numeroProcesso);
        }
        if(pastaFisicaCliente != null) {
            query.setParameter("pastaFisicaCliente", "%" + pastaFisicaCliente + "%");
        }
        if(nomeResponsavel != null) {
            query.setParameter("nomeResponsavel", "%" + nomeResponsavel + "%");
        }
        if(dtInicio != null) {
            query.setParameter("dtInicio",dtInicio);
        }
        if(dtFim != null) {
            query.setParameter("dtFim",dtFim);
        }
        return query.getResultList();
    }

    @Override
    public List<ProcessoJB> getListaPorNumeroProcesso(String numeroProcesso) {
        String sql = "Select distinct p from ProcessoJB p where p.numeroProcessoUnificado like :numeroProcesso";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("numeroProcesso", numeroProcesso + "%");
        return query.getResultList();
    }

    @Override
    public ProcessoJB getProcessoPorIdPai(long idProcesso) {
        try {
            String sql = "Select p from ProcessoJB p where p.processoVinculado.id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", idProcesso);
            return (ProcessoJB) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public SituacaoEnum getSituacaoPorIdProcesso(long idProcesso) {
        try {
            String sql = "Select p.situacao from ProcessoJB p where p.id = :id";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("id", idProcesso);
            return (SituacaoEnum) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
