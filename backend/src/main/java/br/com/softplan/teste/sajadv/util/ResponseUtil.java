package br.com.softplan.teste.sajadv.util;

import javax.ws.rs.core.Response;

/**
 * Created by eduardo on 28/06/15.
 */
public class ResponseUtil {

    private static final String msg = "{\"mensagem\": \";\"}";

    public static Response sucesso() {
        return Response.status(Response.Status.OK).build();
    }

    public static Response sucesso(String mensagem) {
        return Response.status(Response.Status.OK).entity(msg.replace(";", mensagem)).build();
    }

    public static Response sucesso(Object object){
        return Response.status(Response.Status.OK).entity(object).build();
    }

    public static Response erroInterno(String mensagem) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg.replace(";", mensagem)).build();
    }

    public static Response naoEncontrado(String mensagem) {
        return Response.status(Response.Status.NOT_FOUND).entity(msg.replace(";", mensagem)).build();
    }

    public static Response invalido(String mensagem) {
        return Response.status(Response.Status.BAD_REQUEST).entity(msg.replace(";", mensagem)).build();
    }
}
