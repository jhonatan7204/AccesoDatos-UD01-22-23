package ejercicio2y3;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;
@XStreamAlias("profesor")
public class Profesor extends Persona implements Serializable {
    private String fechaIncorporacion;
    private List<Historial> historial;
    private List<Falta> faltas;

    public Profesor() {
    }
    public Profesor(String dni, String nombre, String apellido1, String apellido2, String fechaNacimiento, double sueldoBruto, String fechaIncorporacion, List<Historial> historial, List<Falta> faltas) {
        super(dni, nombre, apellido1, apellido2, fechaNacimiento, sueldoBruto);
        this.fechaIncorporacion = fechaIncorporacion;
        this.historial = historial;
        this.faltas = faltas;
    }

    public String getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(String fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }

    public List<Falta> getFaltas() {
        return faltas;
    }

    public void setFaltas(List<Falta> faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return super.toString() +
                "fechaIncorporacion='" + fechaIncorporacion + '\'' +
                ", historial=" + historial +
                ", faltas=" + faltas +
                '}';
    }
}
