package br.com.softplan.teste.sajadv.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtil {

	/**
	 * Criar uma action a partir de uma string em forma de EL
	 * @param action 
	 * 			EL usada para gerar a action v�lida
	 * @return Retorna um MethodExpression que ser� populada no menu
	 */
	public static MethodExpression getActionExpression(String action) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		return expressionFactory.createMethodExpression(elContext, action, String.class, new Class[0]);
	}

	/**
	 * Retorna um objeto da sess�o
	 * @param nome
	 * 			Nome do objeto que ser� pesquisado
	 * @return Retorna o objeto da sess�o
	 */
	public static Object getAtributoDeSessao(String nome) {
		Object obj = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			ExternalContext ex = context.getExternalContext();
			obj = ((HttpSession) ex.getSession(true)).getAttribute(nome);
		}
		return obj;
	}

	public static void removeAtributoDeSessao(String nome) {
		Object obj = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			ExternalContext ex = context.getExternalContext();
			((HttpSession) ex.getSession(true)).removeAttribute(nome);
		}
	}

	/**
	 * Adiciona um objeto na sess�o
	 * @param nome
	 * 			Nome do objeto que ser� colocado na sess�o
	 * @param objeto
	 * 			Objeto que ser� colocado na sess�o
	 */
	public static void setAtributoDeSessao(String nome, Object objeto) {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(nome, objeto);
	}

	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse(){
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	public static void removeAtributoDaSessionMap(String nome){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(nome);
	}
	
	public static void invalidaSessao(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

    public static void setErrorMessage(String mensagem){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem);
        facesContext.addMessage(null, facesMessage);
    }

    public static void setSuccessMessage(String mensagem) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem);
        facesContext.addMessage(null, facesMessage);
    }
    public static void setAvisoMessage(String mensagem) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "", mensagem);
        facesContext.addMessage(null, facesMessage);
    }

    public static String facesRedirect(String pagina) {
    	StringBuilder retorno = new StringBuilder(pagina);
    	retorno.append("?faces-redirect=true");
    	return retorno.toString();
	}

}
