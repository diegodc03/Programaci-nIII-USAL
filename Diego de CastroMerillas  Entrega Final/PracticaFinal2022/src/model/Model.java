/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import static java.lang.System.out;
import java.util.ArrayList;
import com.coti.tools.OpMat;
import com.coti.tools.Rutas;
import com.coti.tools.Esdia;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author ddecm
 */
public class Model {
    
    private final String NAME_FILE_CSV = "figuras.csv";        //Nombre del fichero .csv
    private final String NAME_FOLDER = "datos_figuras";    //Nombre de la carpeta donde se encuenta los ficheros .bin .csv y el HTML
    private final String NAME_HTML = "figuras.html";       //Nombre de la pagina HTML
    private final String NAME_FILE_BIN = "figuras.bin";     //Nombre del fichero .bin
    private List<Figura> generalList = new ArrayList<>();
    private final int CAMPO = 7;                            //Numero de campos de la figura | Identificacion | Altura | Material | Cantidad | Año | Foto | Fabricante
    
    
    //////////////////////////////////////////////////// COMIENZO Y FINAL /////////////////////////////////////////////////////////////////////////////
    public void comienzo(){
        
        // Guardamos la direccion donde esta el fichero
        Path ficheroBin = Rutas.pathToFileInFolderOnDesktop(NAME_FOLDER, NAME_FILE_BIN);
        
        //Si existe el fichero entonces importamos la informacion de comienzo del fichero binario
        if(ficheroBin.toFile().exists()){
            
            FileInputStream fis;
            BufferedInputStream bis;
            ObjectInputStream ois;
            
            try{
                //Declaro estos metodos para asi importar el fichero
                fis = new FileInputStream(ficheroBin.toFile());
                bis = new BufferedInputStream(fis);
                ois = new ObjectInputStream(bis);
                
                //Guardamos en generaList la informacion del fichero binario
                generalList = (List<Figura>) ois.readObject();
                System.out.printf("%n%nSe ha leido el archivo binario correctamente%n%n%n");
                ois.close();
            }catch (IOException | ClassNotFoundException ex){
                System.out.printf("No se ha podido leer el archivo%n");
                System.out.printf("Asegurese que la carpeta datos_figuras esta en el escritorio y el archivo figuras.bin esta dentro%n%n%n%n");
            }
        //Si no existe o no se puede importar el fichero binario entocnes como segunda opcion impotamos un fichero CSV que ayuda a empezar
        }else{
            //System.out.printf("No existe el fichero%n");
            System.out.printf("%n%nNo se ha leido el fichero binario por lo que podemos leer el CSV del menu Archivos%n");
            

        }
    }  
    // Metodo que se llama al final del programa para así exportar la informacion actualizada de la sesion en un fichero binario
    public void finalPrograma(){
        Path ficheroBin = Rutas.pathToFileInFolderOnDesktop(NAME_FOLDER,NAME_FILE_BIN);
        try{
            FileOutputStream fos = new FileOutputStream(ficheroBin.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
           
            //Escribe la informaion de la lista generalList en el fichero CSV
            oos.writeObject(generalList);
            oos.close();
        }catch(IOException ex){
            System.out.printf("No fue posible guardar el archivo binario con las figuras");
        }
        
        
        
    }
    
    /////////////////////////////////////////////////// MENÚ ARCHIVOS //////////////////////////////////////////////////////////////////////////////////
    public int importarCSV(){
        
        final String DELIMITADOR = "\t";
        int n = 0, a=0;
        Esdia.underline2("%n%nMétodo: importarCSV()");
        
        //Cogemos la ruta donde est el fichero CSV
        File archivoCSV = Rutas.pathToFileInFolderOnDesktop(NAME_FOLDER, NAME_FILE_CSV).toFile();
        System.out.println("La ruta donde estan los ficheros es " + archivoCSV);
        
        //Si el archivo existe intentamos iportar la informacion
        if (archivoCSV.exists()) {
            //tmp guarda la informacion del fichero csv
            String[][] tmp;
            try {
                tmp = OpMat.importFromDisk(archivoCSV, DELIMITADOR);
            } catch (Exception ex) {
                out.printf("%n%nNo fue posible importar el archivo");
                return 0;
            }
            //Iteramos cada fila de tmp para ver si podemos pasarlo a ArrayList y los datos estan en un formato bueno
            generalList = new ArrayList<>();
            for (String[] campos : tmp) {           //for(int i=0; i<tmp[i].length; i++)
                Figura f;                               
                f = Figura.factory(campos);     
                if (null != f) {
                    generalList.add(f);
                    a = a + 1;
                }else {
                    System.out.printf("Se ha descartado la linea %d %n", a+n+1);
                    n = n + 1;
                } 
            }    
        }
        else{
            System.out.printf("No existe el archivo");
        }
        //Informacion de los errores que ha dado el fichero
        if(generalList != null){
            System.out.printf("%nSe ha leido correctamente el fichero%n");
            int total = n+a;
            float porcent = (total*n)/a;
            System.out.printf("El numero de registos descartados es "+ n +" de un total de " + total + " y su porcentaje de errores es de %.2f%% %n",porcent );
        }
        return 1;
    }
    
    public void exportarFiguarasCSV(){
        //ruta del fichero dnde se guardará la informacion
        final String DELIMITER = "\t";
        File exportarFiguras = Rutas.pathToFileInFolderOnDesktop​(NAME_FOLDER,"figuras.csv").toFile();
        
        if(null == generalList){
            System.out.printf("La lista de figuras esta vacia, no puede exportar ningun elemento");
        }
        
        List <String> tmp = new ArrayList<>();
        for(Figura f : generalList ){
            tmp.add(f.escribeElFicheroConDELIMITER(DELIMITER));
        }
        try{
            //Escribe en el fichero en formato csv de la informacion contenia en la lista tmp
            Files.write(exportarFiguras.toPath(),tmp, Charset.defaultCharset(),StandardOpenOption.CREATE);
        }catch(IOException ex){
            System.out.printf("No se pudo crear el archivo exportado");
        }
        System.out.printf("El archivo se ha exportado corractamente");
    }
    
    // Metodo que exporta a HTML la lista con las figuras
    public void exportarFigurasHTML(){
        File exportarHTML = Rutas.pathToFileInFolderOnDesktop​(NAME_FOLDER,NAME_HTML).toFile();
        if(exportarHTML.exists()){
            exportarHTML.delete();
        }
        try{
            //Creamos un printWiiter para escribir en el fichero
            PrintWriter pw = new PrintWriter(exportarHTML);
            //Formato de como va a ser el encabezado de la tabla
            pw.printf("<DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset=\"UTD-8\">%n"
                    + "</HEAD>%n"
                    + "<BODY>");
            pw.printf("<TABLE BORDER=1>%n");
            pw.printf("<caption>GALERIA DE ARTE</caption>");                    //Título de la tabla
            pw.printf("<TR><tr style=\"background-color: rgb(102, 255, 153);\">"
                        + "<TD>IDENTIFICADOR</TD>" //IDENTIFICACION                     Titulo de cada columna 
                        + "<TD>ALTURA (M)</TD>" //ALTURA M
                        + "<TD>MATERIAL</TD>" //MATERIAL
                        + "<TD>CANTIDAD</TD>" //CANTIDAD
                        + "<TD>AÑO</TD>" // AÑO
                        + "<TD>FOTO</TD>" //FOTOS
                        + "<TD>FABRICANTE</TD>" //FABRICANTES
                        + "</TR>");
            // Con este bucle imprimimos todos los valores en el fichero HTML
            for(Figura f : generalList){
               pw.printf("%s%n", f.asHTML());
            }
            pw.printf("</>%n</BODY>%n</HTML>%n");
            pw.close();
        }catch(FileNotFoundException ex){
            System.out.println("ERROR AL EXPORTAR A HTML");
        } 
        System.out.printf("Se ha exportado el fichero HTML correctamente");
    }
       
    
   ///////////////////////////////////////////////// MENU GESTION DE GALERIA /////////////////////////////////////////////////////////////////////////
    // Método para ver si esta la figura en el 
    public int piezaBucle(String identificadorNew){
        String identif;
        String newId = identificadorNew;
        for(Figura f : generalList){
            identif = f.getIdentificador();
            if(newId.equalsIgnoreCase(identif)){
                //Signfica que hay un identificador igual
                
                return 1;
            } 
        }
        /*
        if(true == generalList.contains(identificadorNew)){
            return 1;
        }*/
        return 2;
    }
    //Metodo introducir nueva pieza en ArrayList
    public void introducirNuevaPiezas(String identif ,float alturaMetros , String material ,int cantidad ,int ano ,String foto ,String fabricante){
        
        Figura f = new Figura(identif,  alturaMetros , material , cantidad , ano , foto , fabricante);
        generalList.add(f);
        System.out.printf("Se ha añadidio la figura al inventario%n%n");
    }
    //Metodo consultarFigura por Identificador
    public List<Figura> consultarFiguraId(String identificador){
        String valor;  
        List <Figura> figura = new ArrayList<>(); 
        for(Figura f : generalList){
            valor = f.getIdentificador();
            if(valor.equalsIgnoreCase(identificador)){
                figura.add(f);
            }
        }
        //ListIterator<Figura> fi = generalList.listIterator();
        return figura; 
    }
    //Metodo cambiar datos, aqui solo se introducen dentro del ArrayList
    //Tengo dos formas de hacerlo, uno comentado y otro no
    public void cambiarDatos(String identificador, float alturaM, String material, int cantidad, int ano, String foto, String fabricante){
        //String valor;   
        int n = 0, a=0;
        /*
        for(Figura f : generalList){
            valor = f.getIdentificador();
            if(valor.equalsIgnoreCase(identificador)){
                //ha encontrado una figura la cual tiene el mismo identificador
                f.setIdentificador(identificador);
                f.setAlturaMetros(alturaM);
                f.setMaterial(material);
                f.setCantidad(cantidad);
                f.setAno(ano);
                f.setFoto(foto);
                f.setFabricante(fabricante);
            }
            n+=1;
        }
        */
        for(Figura tmp : generalList){
            //Iteramos para ver en que indice se encuantra el identificador buscado
            if(identificador.equalsIgnoreCase(tmp.getIdentificador())){
                a=n;
            }
            n=n+1;  
        }
        Figura f = new Figura(identificador, alturaM, material,cantidad,ano,foto,fabricante);   // Crea una figura nueva
        generalList.set(a, f);          // Introduce los valores en el indice a para cambiarlos
    }
    // Metodo eliminar pieza pasandole el identificador
    public void eliminarPieza(String pieza){
        List<Figura> arrayRemove = new ArrayList<>();
        for(Figura f : generalList){
            if(pieza.equalsIgnoreCase(f.getIdentificador())){
                //Significa que el identificador que se ha puesto es bueno y se elimina una pieza
                arrayRemove.add(f);                     //Si se encuentra el identificador se mete en ArrayRemove 
            }
        }  
        //Se elimina el valor introducido en arrayRemove
        generalList.removeAll(arrayRemove);
        //Se podria haber hecho mas eficiente pero queria probar el metodo removeAll
    }
    
////////////////////////////////////////////////////// MENÚ LISTADOS //////////////////////////////////////////////////////////////////////

    // Metodo en el que se ordena por identificador
    public void listadoPorIdentificador(){
        Comparator<Figura> cId = Comparator.comparing(Figura::getIdentificador);
        Collections.sort(generalList, cId);
    }
    //Metodo que se ordena por año y liego si tienen las figuras mismos años se ordenan por indetificador
    public void listadoPorAnoEIdentificador(){
        Comparator<Figura> cAno = Comparator.comparing(Figura::getAno).reversed();
        Comparator<Figura> cId = Comparator.comparing(Figura::getIdentificador);
        Comparator<Figura> total = cAno.thenComparing(cId);
        Collections.sort(generalList, total);
    }
    //Metodo que ordena por fabricate y si hay un mismo fabricante se ordena por año
    public void listadoPorFabricanteYAno(){
        Comparator<Figura> cFabric = Comparator.comparing(Figura::getFabricante);
        Comparator<Figura> cAno = Comparator.comparing(Figura::getAno).reversed();
        Comparator<Figura> total = cFabric.thenComparing(cAno);
        Collections.sort(generalList, total);
    }
    
    public String printPantalla(List<Figura> f){
        for(Figura a : f){
        return String.format("%s\t%f\t%s\t%d\t%d\t%s\t%s",a.getIdentificador(),a.getAlturaMetros(),
                a.getMaterial(),a.getCantidad(),a.getAno(),a.getFoto(),a.getFabricante());
        }
        return null;
    }
    //Metodo para imprimir pon pantalla con printToScreen
    public String[][] imprimirConFormato(){
        String[][] matriz = new String[generalList.size()][CAMPO];
        int n=0;    //Numero que itera el primer indice ya que con el forEach no lo podemos saber
        for(Figura f : generalList){
            matriz[n] = f.valoresFigura();
            n++;
        }
        return matriz;
    }
    //Metodo para poner la primera letra en mayuscula, ya que lo necesito para el comparador
    public String ponerPrimeraLetraMayuscula(String str){
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }
    
  
}

    
    
