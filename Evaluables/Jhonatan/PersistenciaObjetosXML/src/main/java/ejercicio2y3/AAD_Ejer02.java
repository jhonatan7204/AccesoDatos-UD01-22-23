package ejercicio2y3;

import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AAD_Ejer02 {
    public static void main(String[] args) {
        String fichero = "instituto.xml";
        String ruta = "instituto.dat";
        readXML(fichero, ruta);
    }
    public static void readXML(String fichero, String ruta){
        XStream xStream =  new XStream();

        xStream.processAnnotations(Persona.class);
        xStream.processAnnotations(Profesor.class);
        xStream.processAnnotations(Administrativo.class);
        xStream.processAnnotations(Falta.class);
        xStream.processAnnotations(Historial.class);
        xStream.processAnnotations(Instituto.class);
        xStream.processAnnotations(ListaInstitutos.class);

        xStream.addImplicitCollection(ListaInstitutos.class, "instituto");
        xStream.addImplicitCollection(Instituto.class, "persona");
        xStream.addImplicitCollection(Profesor.class, "historial");
        xStream.addImplicitCollection(Profesor.class, "faltas");

        xStream.allowTypes(new Class[]{
                Persona.class,
                Profesor.class,
                Administrativo.class,
                Falta.class,
                Historial.class,
                Instituto.class,
                ListaInstitutos.class
        });

        try (FileOutputStream fos = new FileOutputStream(ruta);
             ObjectOutputStream salida = new ObjectOutputStream(fos)){

            ListaInstitutos lista = (ListaInstitutos) xStream.fromXML(new FileInputStream(fichero));

            for(Instituto instituto: lista.getInstitutos()){
                System.out.println(instituto);
                salida.writeObject(instituto);
            }

            System.out.println("Fichero DAT creado con exito");

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
