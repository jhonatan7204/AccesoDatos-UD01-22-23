package org.example;

import com.thoughtworks.xstream.XStream;
import java.io.*;

public class AAD_Ejer01 {
    public static void main(String[] args) {
        String ruta = "viajes.dat";
        String xml = "viajes.xml";
        crearFicheroXML(ruta, xml);
    }
    public static void crearFicheroXML(String fichero, String xml){
        ListaViajes viajes = new ListaViajes();
        try(FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream entrada = new ObjectInputStream(fis)){

            while (true){
                Viaje viaje = (Viaje) entrada.readObject();
                viajes.add((viaje));
            }

        }catch (EOFException end){
            System.out.print("");
        }catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }

        XStream xStream = new XStream();

        xStream.processAnnotations(Etapa.class);
        xStream.processAnnotations(Hotel.class);
        xStream.processAnnotations(Lugar.class);
        xStream.processAnnotations(Viaje.class);
        xStream.processAnnotations(ListaViajes.class);

        xStream.addImplicitCollection(ListaViajes.class, "lista");

        try {

            xStream.toXML(viajes, new FileOutputStream(xml));
            System.out.println("Fichero XML creado con exito.");

        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
