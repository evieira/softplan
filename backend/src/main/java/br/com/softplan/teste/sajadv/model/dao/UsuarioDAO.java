package br.com.softplan.teste.sajadv.model.dao;


import br.com.softplan.teste.sajadv.model.bean.UsuarioJB;

public interface UsuarioDAO extends DefaultDAO<UsuarioJB> {

    UsuarioJB getPorLoginESenha(String usuario, String senha);
    boolean usuariosTemEntidadePorIdEntidade(long idEntidade);
}
