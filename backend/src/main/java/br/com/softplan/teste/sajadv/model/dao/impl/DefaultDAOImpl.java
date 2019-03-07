package br.com.softplan.teste.sajadv.model.dao.impl;



import br.com.softplan.teste.sajadv.model.dao.DefaultDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DefaultDAOImpl<T> implements DefaultDAO<T> {

    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public DefaultDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void salvar(T object) {
        getEntityManager().persist(object);
    }

    public void atualizar(T object) {
        getEntityManager().merge(object);
    }

    public void remover(T object) {
        getEntityManager().remove(object);
    }

    public T getPorId(Long id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    public List<T> getLista() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
