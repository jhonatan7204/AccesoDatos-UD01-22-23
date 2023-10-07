package ejercicio2y3;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@XStreamAlias("institutos")
public class ListaInstitutos implements Serializable {

    private List<Instituto> instituto;

    public ListaInstitutos() {
        instituto = new ArrayList<>();
    }
    public void add(Instituto instituto){
        this.instituto.add(instituto);
    }
    public List<Instituto> getInstitutos() {
        return instituto;
    }
    public void setInstituto(List<Instituto> instituto) {
        this.instituto = instituto;
    }
}
