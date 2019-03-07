package br.com.softplan.teste.sajadv.model.bean.enumaration;

public enum  SituacaoEnum {

    EM_ANDAMENTO(false),
    DESMEMBRADO(false),
    EM_RECURSO(false),
    FINALIZADO(true),
    ARQUIVADO(true);

    private boolean finalizado;

    private SituacaoEnum(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        switch (this) {
            case EM_ANDAMENTO: return "Em andamento";
            case DESMEMBRADO: return "Desmembrado";
            case EM_RECURSO: return "Em recurso";
            case FINALIZADO: return "Finalizado";
            case ARQUIVADO: return "Arquivado";
        }
        return null;
    }
}
