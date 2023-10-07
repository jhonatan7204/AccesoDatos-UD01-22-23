package ejercicio2y3;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("falta")
public class Falta implements Serializable {
    private String fecha;
    private String razon;

    public Falta() {
    }

    public Falta(String fecha, String razon) {
        this.fecha = fecha;
        this.razon = razon;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    @Override
    public String toString() {
        return "Falta{" +
                "fecha='" + fecha + '\'' +
                ", razon='" + razon + '\'' +
                '}';
    }
}
