package ejercicio2y3;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("administrativo")
public class Administrativo extends Persona implements Serializable {
    private int tiempoContrato;
    public Administrativo() {
    }
    public Administrativo(String dni, String nombre, String apellido1, String apellido2, String fechaNacimiento, double sueldoBruto, int tiempoContrato) {
        super(dni, nombre, apellido1, apellido2, fechaNacimiento, sueldoBruto);
        this.tiempoContrato = tiempoContrato;
    }

    public int getTiempoContrato() {
        return tiempoContrato;
    }

    public void setTiempoContrato(int tiempoContrato) {
        this.tiempoContrato = tiempoContrato;
    }

    @Override
    public String toString() {
        return super.toString() +
                "tiempoContrato=" + tiempoContrato +
                '}';
    }
}
