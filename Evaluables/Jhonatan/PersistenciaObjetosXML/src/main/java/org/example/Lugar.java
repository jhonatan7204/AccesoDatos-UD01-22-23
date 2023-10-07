package org.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serial;
import java.io.Serializable;
@XStreamAlias("lugar")
public class Lugar implements Serializable {
    private String ciudad;
    private String pais;
    @Serial
    private static final long serialVersionUID = -5031101434865408464L;
    public Lugar() {
    }

    public Lugar(String ciudad, String pais) {
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
