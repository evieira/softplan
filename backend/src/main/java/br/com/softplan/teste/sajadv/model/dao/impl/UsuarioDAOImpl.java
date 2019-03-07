package br.com.softplan.teste.sajadv.model.dao.impl;

import br.com.softplan.teste.sajadv.model.bean.UsuarioJB;
import br.com.softplan.teste.sajadv.model.dao.UsuarioDAO;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.logging.Logger;

@Named
public class UsuarioDAOImpl extends DefaultDAOImpl<UsuarioJB> implements UsuarioDAO {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public UsuarioJB getPorLoginESenha(String usuario, String senha) {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT u from UsuarioJB u where u.email = :usuario and u.senha = :senha");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("usuario", usuario);
            query.setParameter("senha", senha);

            return (UsuarioJB) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean usuariosTemEntidadePorIdEntidade(long idEntidade) {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(u.id) from UsuarioJB u join u.entidade e where e.id = :idEntidade");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("idEntidade", idEntidade);
            Long contagem = (Long) query.getSingleResult();
            return contagem > 0;
        } catch (NoResultException e) {
            logger.info(e.getMessage());
            return true;
        }
    }
}
