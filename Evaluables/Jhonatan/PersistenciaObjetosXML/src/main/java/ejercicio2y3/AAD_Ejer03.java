package ejercicio2y3;

import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.util.Scanner;

public class AAD_Ejer03 {
    public static void main(String[] args) {
        String ruta = "src/main/resources/";
        int opcion;
        do{
            opcion = menu();
            switch (opcion){
                case 1: //instituto.xml
                    XMLtoDAT(ruta +
                            leerString("Introduce el nombre del fichero XML: "));
                    break;
                case 2: //insti.dat
                    DATtoXML(ruta +
                            leerString("Introduce el nombre del fichero DAT: "));
                    break;
                case 3:
                    mostrarXML(ruta +
                            leerString("Introduce el nombre del fichero XML: "));
                    break;
                case 4:
                    mostrarDAT(ruta +
                            leerString("Introduce el nombre del fichero DAT: "));
                    break;
                case 5:
            }

        }while(opcion != 5);
    }
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("--- Gestión de ficheros ---");
        System.out.println("1. Convertir XML a DAT");
        System.out.println("2. Convertir DAT a XML");
        System.out.println("3. Mostrar XML");
        System.out.println("4. Mostrar DAT");
        System.out.println("5. Salir");

        do {
            System.out.print("Opcion: ");
            while (!sc.hasNextInt()) {
                System.out.println("Valor no válido");
                sc.next();
                System.out.print("Opcion: ");
            }
            opcion = sc.nextInt();
        }while(opcion < 1 || opcion > 5);

        return opcion;
    }
    public static void XMLtoDAT(String nombreXML){
        String nombreDAT = nombreXML.replace(".xml", ".dat");
        boolean existe = new File(nombreDAT).exists();
        ListaInstitutos listaAnterior = null;
        ListaInstitutos lista;

        if(!new File(nombreXML).exists()){
            System.out.println("El fichero no existe\n");
            return;
        }

        if(existe) {
            listaAnterior = leerFicheroDat(nombreDAT);
        }

        try (FileOutputStream fos = new FileOutputStream(nombreDAT);
             ObjectOutputStream salida = new ObjectOutputStream(fos)){

            lista = leerFicheroXml(nombreXML);

            if(existe){
                for(Instituto instituto: listaAnterior.getInstitutos()){
                    salida.writeObject(instituto);
                }
            }

            for(Instituto instituto: lista.getInstitutos()){
                salida.writeObject(instituto);
            }

            System.out.println("\nFichero creado exitosamente\n");

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    public static void DATtoXML(String nombreDAT){
        String nombreXML = nombreDAT.replace(".dat", ".xml");
        boolean existe = new File(nombreXML).exists();
        ListaInstitutos listaAnterior = null;
        XStream xStream = new XStream();
        ListaInstitutos lista;

        if(!new File(nombreDAT).exists()){
            System.out.println("El fichero no existe\n");
            return;
        }

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

        lista = leerFicheroDat(nombreDAT);

        if(existe){
            listaAnterior = leerFicheroXml(nombreXML);
            for(Instituto instituto: lista.getInstitutos()){
                listaAnterior.add(instituto);
            }
        }
        try {
            if(existe) {
                xStream.toXML(listaAnterior, new FileOutputStream(nombreXML));
            }else{
                xStream.toXML(lista, new FileOutputStream(nombreXML));
            }
            System.out.println("\nFichero creado exitosamente\n");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void mostrarXML(String nombreXML){
        ListaInstitutos institutos = null;
        if(!new File(nombreXML).exists()){
            System.out.println("El fichero no existe\n");
            return;
        }

        institutos = leerFicheroXml(nombreXML);

        System.out.println();
        for(Instituto instituto: institutos.getInstitutos()){
            System.out.println(instituto);
        }
        System.out.println();
    }
    public static void mostrarDAT(String nombreDAT){
        ListaInstitutos institutos = null;
        if(!new File(nombreDAT).exists()){
            System.out.println("El fichero no existe\n");
            return;
        }

        institutos = leerFicheroDat(nombreDAT);

        System.out.println();
        for(Instituto instituto: institutos.getInstitutos()){
            System.out.println(instituto);
        }
        System.out.println();
    }

    /* ↓ Métodos Útiles ↓*/
    public static ListaInstitutos leerFicheroDat(String nombreDAT){
        ListaInstitutos lista = new ListaInstitutos();

        try (FileInputStream fis = new FileInputStream(nombreDAT);
             ObjectInputStream entrada = new ObjectInputStream(fis)) {

            while (true) {
                lista.add((Instituto) entrada.readObject());
            }

        } catch (EOFException eof){
            System.out.print("");
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public static ListaInstitutos leerFicheroXml(String nombreXML){
        ListaInstitutos lista = new ListaInstitutos();
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

        try {
            lista = (ListaInstitutos) xStream.fromXML(new FileInputStream(nombreXML));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public static String leerString(String mensaje){
        Scanner sc = new Scanner(System.in);
        String cadena;
        System.out.print(mensaje);
        cadena = sc.nextLine();
        return cadena;
    }
}
