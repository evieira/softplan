package br.com.softplan.teste.sajadv.model.dao.impl;

import br.com.softplan.teste.sajadv.model.bean.ResponsavelJB;
import br.com.softplan.teste.sajadv.model.dao.ResponsavelDAO;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
public class ResponsavelDAOImpl extends DefaultDAOImpl<ResponsavelJB> implements ResponsavelDAO {

    @Override
    public boolean responsavelVinculadoAProcessoPorIdResponsavel(Long idResponsavel) {
        String sql = "Select count(r.id) from ResponsavelJB r join r.listaProcesso processo where r.id = :idResponsavel";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("idResponsavel", idResponsavel);
        return ((Long) query.getSingleResult()) > 0;
    }

    @Override
    public List<ResponsavelJB> getListaPorNomeCpfNumeroProcesso(String nome, String cpf, String numeroProcesso) {
        StringBuffer sql = new StringBuffer("Select distinct r from ResponsavelJB r left join fetch r.listaProcesso processo where 1=1 ");
        if(nome != null && !nome.isEmpty()) {
            sql.append("and r.nome like :nome ");
        }
        if(cpf != null && !cpf.isEmpty()) {
            sql.append("and r.cpf = :cpf ");
        }
        if(numeroProcesso != null && !numeroProcesso.isEmpty()) {
            sql.append("and processo.numeroProcessoUnificado = :numeroProcesso ");
        }
        Query query = getEntityManager().createQuery(sql.toString());
        if(nome != null && !nome.isEmpty()) {
            query.setParameter("nome", "%" + nome + "%");
        }
        if(cpf != null && !cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }
        if(numeroProcesso != null && !numeroProcesso.isEmpty()) {
            query.setParameter("numeroProcesso", numeroProcesso);
        }
        return query.getResultList();
    }
}
