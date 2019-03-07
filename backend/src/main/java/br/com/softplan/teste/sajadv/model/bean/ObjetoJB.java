package br.com.softplan.teste.sajadv.model.bean;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ObjetoJB implements Serializable {

    private static final long serialVersionUID = -1300242530987595852L;

    protected long id;

    public ObjetoJB() {
    }
    public ObjetoJB(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public boolean isNew(){
        if(id == 0) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjetoJB objetoJB = (ObjetoJB) o;

        return id == objetoJB.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ObjetoJB{" +
                "id=" + id +
                '}';
    }
}
