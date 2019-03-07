package br.com.softplan.teste.sajadv.model.dao;

import java.util.List;

public interface DefaultDAO<T> {

    void salvar(T object);
    void atualizar(T object);
    void remover(T object);
    T getPorId(Long id);
    List<T> getLista();
}
