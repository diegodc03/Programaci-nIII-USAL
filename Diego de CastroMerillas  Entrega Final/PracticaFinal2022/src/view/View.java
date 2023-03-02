/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.Figura;
import controller.Controller;
import com.coti.tools.Esdia;
import com.coti.tools.DiaUtil;
import com.coti.tools.OpMat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego de Castro Merillas 
 * @author 2º Grado Ingenieria Informatica Universidad de Salamanca
 */
public class View {
    
    Controller c = new Controller();
    
    /////////////////////////////////////////////////////////////  GESTIÓN MENÚ  /////////////////////////////////////////////////////////////////////////////////
    //Metodo del menu principal
    public void runMenu(String menu){
        String opcion;
        boolean salir = false;
        int n=0;
        
        if(n==0){
        c.comienzoPrograma();
        n=1;
        }
        System.out.printf("%n%n%n");
        Esdia.underline2("Menu Principal");
        do{
            opcion = Esdia.readString(menu);
            
             switch(opcion){
                
                case "1" -> {
                    String menuArchivos = "%na.- Importar CSV%n"
                            + "b.- Exportar CSV%n"
                            + "c.- Exportar HTML%n"
                            + "d.- Volver al menu principal%n"
                            + "-> ";
                    this.archivosLeerMenu(menuArchivos);
                }
                     
                case "2" -> {
                    String menuGestionDeGaleria = "%na.- Añadir una figura al invetario%n"
                            + "b.- Consultar datos de una figura%n"
                            + "c.- Modificar datos de una figuta%n"
                            + "d.- Eliminar una figura del inventario%n"
                            + "e.- Volver al menu principal%n"
                            + "%n -> " ;
                    this.gestionDeLaGaleriaLeerMenu(menuGestionDeGaleria);
                }
                     
                case "3" -> {
                    String listadoMenu = "%na.- Listado por identificador%n"
                            + "b.- Listado por año e identificador%n"
                            + "c.- Listado por fabricante y año%n"
                            + "d.- Volver al menu principal%n"
                            + "-> ";
                    this.ListadosLeerMenu(listadoMenu);
                }
                 
                case "4" -> {
                    salir = Esdia.siOno("%n ¿Quiere salir del programa? s/n");
                    if(salir == true){
                        System.out.printf("%nHa salido del programa%n");
                        DiaUtil.showFinalTime();
                        c.salirPrograma();
                    }
                 }    
             }
        }while(salir == false);
        
    }
    //Metodo para el submenú de leerArchivos
    public void archivosLeerMenu(String menuArchivos){
        String opcion;
        System.out.println();
        int a=0;
        System.out.printf("%n%n%n%n%n");
        Esdia.underline2("Gestion Archivos");
        do{
            opcion = Esdia.readString(menuArchivos).toLowerCase();
            
            switch(opcion){
                case "a" :
                    c.importarArchivosCSV();
                    System.out.println();
                    break;
            
                case "b" :
                    c.exportarArchivoCSV();
                    System.out.println();
                    break;
            
                case "c" :
                    c.exportarHTML();
                    System.out.println();
                    break;
                case "d":
                    System.out.printf("Ha salido del menu leer archivos%n%n");
                    a=1;
                    break;
                default:
                    System.out.printf("Introduzca una opcion valida");
            }
        }while(a!=1);
    }
    // Metodo para el menu de la gestion de la galeria
    public void gestionDeLaGaleriaLeerMenu(String menuGestionDeLaGaleria){
        String opcion;
        int a=0;
        System.out.printf("%n%n%n%n");
        Esdia.underline2("Menu Gestion de la Galeria");
        do{
           opcion = Esdia.readString(menuGestionDeLaGaleria).toLowerCase();
            switch(opcion){
                case "a":
                    this.introducirNuevaPieza();
                    break;
                case "b" :
                    this.consultarDatosDeUnaFigura();
                    break;
                case "c" : 
                    this.cambiarDatosFigura();
                    break;
                case "d" :
                    this.eliminarDatosFigura();
                    break;
                case "e":
                    System.out.printf("%nHa salido del menu gestion de galeria");
                    a=1;
                    break;
                default: 
                    System.out.printf("%nNo ha introducido la opcion correcta%n");
            }
        }while(a == 0);
    }
    // Metodo del menu de listado de las figuras
    public void ListadosLeerMenu(String menuListados){
           String opcion;   
           System.out.printf("%n%n%n%n");
           Esdia.underline2("Listado de las figueras");
        do{
           opcion = Esdia.readString(menuListados).toLowerCase();
            switch(opcion){
                case "a" :
                    this.listadoPorIdentificador();
                    break;
                case "b" :
                    this.listadoPorAnoEIdentificador();
                    break;
                case "c" :
                    this.listadoPorFabricanteYAno();
                    break;
                case "d" :
                    System.out.printf("Ha salido de la opcion listados%n");
                default :
                    System.out.println("No ha introducido ninguna de las posibilidades");
                
            }
        }while(!"d".equals(opcion));
    }
    
    
    //////////////////////////// MENU GESTION DE GALERIA //////////////////////////////////////////////////////////////////////////
    //Metodo introducir nueva pieza --> aqui se preguntan los valores y se comprueba si se ponen bien
    public void introducirNuevaPieza(){     
        System.out.printf("%n%n%n%n%n");
        int dev;
        String identif;
        boolean comp = true;
        Esdia.underline2("Añadir una figura al inventario");
        do{
            identif = Esdia.readString_ne("%nIntroduzca un identificador para la pieza%n->");
            dev = c.piezaBucle(identif);
            if(dev == 1){
                System.out.println("Ha introducido un identificador ya existente, introduzca otro por favor%n->");
            }
        }while(dev == 1);
        
        float alturaMetros = Esdia.readFloat("%nIntroduce la altura en metros%n-> ",(float )0.001, (float) 1.5);             //minimo la figura tiene que tener una altura, ya que biblioteca.jar admide 0
                                                                                                                                  //como valor minimo, pongo un valor minimo para que la figura siempre tenga altura
        String material = Esdia.readString_ne("%nIntroduce el material con el que esta construido en su mayoria%n-> ");
        
        int cantidad;
        do{
            cantidad = Esdia.readInt("%nIntroduzca la cantidad de figuras de ese modelo %n-> ");
        }while(cantidad < 1);    //Como no tiene un maximo he hecho un bucle solo con el numero
        
        int ano = Esdia.readInt("%nIntroduzca el año de contruccion del modelo de la figura %n-> ",0,2100);
        String foto;
        do{
            comp = true;
            foto = Esdia.readString_ne("%nIntroduzca el nombre del archivo .PNG%n->  ");
            if(false == foto.endsWith(".png".toLowerCase())){
                comp = false;
                System.out.printf("Introduzca el nombre con .png al final%n");
            }
        }while(comp == false);
        String fabricante = Esdia.readString_ne("%nEscriba cual es el fabricante%n -> ");
        fabricante = c.ponerPrimeraLetraMayuscula(fabricante);
        //fabricante.toUpperCase().charAt(0) + fabricante.substring(1, fabricante.length()).toLowerCase();
        
        c.introducirNuevaPieza(identif, alturaMetros , material , cantidad , ano , foto , fabricante);
    }
    // Metodo Consultar Datos de una figura, se pregunta el identificador y se comprueba si existe y luego se imprime por pantalla
    public int consultarDatosDeUnaFigura(){
        System.out.printf("%n%n%n");
        Esdia.underline2("Consulta de datos de una figura");
        int ret;
        String identificador;
        List <Figura> fig = new ArrayList<>();
        
            //Hará el bucle hasta que haya un identificador valido
            identificador = Esdia.readString("Introduzca el identificador de la figura%n-> ");
            ret = c.piezaBucle(identificador);          //SEs un metodo en el que compruebo si el identificador es igual o no
            if(ret == 2){
                 System.out.println("Ha introducido un identificador que no existe, se vuelve al menu de gestion");
                 return 0;
            }
            else{
                fig = c.consultarFigura(identificador);             //tengo la figura que buscamos
                //Se imprime los datos de la figura
                for(Figura f : fig){
                    System.out.println("La figura buscada es:");
                    //System.out.println(devolverFormato());
                    System.out.printf("\tIdentificador %s%n"
                            + "\tAltura(M) %7f %n"
                            + "\tMaterial %7s %n"
                            + "\tCantidad %7d %n"
                            + "\tAno  %7d %n"
                            + "\tFoto %7s %n"
                            + "\tFabricante  %7s  %n",
                            f.getIdentificador(),f.getAlturaMetros(),
                            f.getMaterial(), f.getCantidad(), f.getAno(),f.getFoto(),f.getFabricante());}
            }
            return 1;
    }           
    //Metodo cambiar datos
    // Se pide el identificador de la figura y si existe se pide al usuario que elija que cambiar al usuario
    public int cambiarDatosFigura(){
        System.out.printf("%n%n%n%n%n");
        int ret;
        String opcion;
        boolean o = false;
        Esdia.underline2("Cambiar Datos de la figura");
        String identificador;
        float alturaM = 0.0f;
        String material = "xxx";
        int cantidad = 2;
        int ano = 0;
        String foto = "xxxx.png";
        String fabricante = "xxxxx";
        List <Figura> fig = new ArrayList<>();
            //Hará el bucle hasta que haya un identificador valido
            identificador = Esdia.readString("%nIntroduzca el identificador de la figura%n-> ");
            ret = c.piezaBucle(identificador);          //SEs un metodo en el que compruebo si el identificador es igual o no
            if(ret == 2){
                 System.out.println("Ha introducido un identificador que no existe, se vuelve al menu de gestion");
                 return 0;
            }
            else{
                fig = c.consultarFigura(identificador);             //tengo la figura que buscamos
                // Introduimos en variables los valores de la figura para asi poder cambiarlos luego
                for(Figura f : fig){
                    System.out.println("La figura buscada es:%n");
                    //System.out.println(devolverFormato());
                    System.out.printf("%s\t\t%f\t\t%s\t\t%d\t\t%d\t\t%s\t\t%s", f.getIdentificador(),f.getAlturaMetros(),
                     f.getMaterial(), f.getCantidad(), f.getAno(),f.getFoto(),f.getFabricante());
                alturaM = f.getAlturaMetros();
                material = f.getMaterial();
                cantidad = f.getCantidad();
                ano = f.getAno();
                foto = f.getFoto();
                fabricante = f.getFabricante();
                }  
                // Menu para elegir quedatos cambiar
                do{
                    opcion = Esdia.readString("%nIntroduzca que valor quiere cambiar menos el identificador:%n"
                        + "1- Altura%n"
                        + "2- Material%n"
                        + "3- Cantidad%n"
                        + "4- Ano%n"
                        + "5- Foto%n"
                        + "6- Fabricante%n"
                        + "s- Salir%n-> ");
                
                    switch(opcion){
                        case "1":
                            alturaM = Esdia.readFloat("Introduzca el nuevo valor de la altura%n-> ",(float)0.001 , (float) 1.5);
                            break;
                        case "2":
                            material = Esdia.readString("Introduzca el material nuevo%n-> ");
                            break;
                        case "3":
                            do{
                                cantidad = Esdia.readInt("Introduzca el cantidad de figuras del modelo%n->");
                                if(cantidad < 1){
                                    System.out.printf("%nIntroduzca una cantidad mayor que 0%n");}
                            }while(cantidad<1);
                            break;
                        case "4":
                            ano = Esdia.readInt("Introduzca el nuevo ano de construccion de la figura%n->",0,2100);
                            break;
                        case "5":
                            boolean comp=true;
                            do{
                                foto = Esdia.readString_ne("%nIntroduzca el nombre del archivo .PNG%n->  ");
                                if(!foto.endsWith(".png".toLowerCase())){
                                    comp = false;
                                    System.out.printf("Introduzca el nombre con .png al final%n");
                                }
                            }while(comp == false);
                            break;
                        case "6":
                            fabricante = Esdia.readString("Introduzca el nuevo fabricante%n->");
                            break;
                        case "s":
                            //return - true if y, false if n
                            o = Esdia.siOno("Quiere salir?");
                            c.cambiarDatosFigura(identificador, alturaM, material, cantidad, ano, foto , fabricante);
                            break;
                }
            }while(o == false);   
            }
        return 1;
    }
    //Metodo eliminar datos
    public int eliminarDatosFigura(){
        System.out.printf("%n%n%n%n%n");
        int opcion;
        
        int ret;
        List <Figura> fig = new ArrayList<>();
        Esdia.underline2("Eliminar Figura");
        String identificador = Esdia.readString("Introduzca el identificador de la pieza a eliminar%n->");
        ret = c.piezaBucle(identificador);          //SEs un metodo en el que compruebo si el identificador es igual o no
            if(ret == 2){
                 System.out.println("Ha introducido un identificador que no existe, se vuelve al menu de gestion");
                 return 0;
            }
            else{
                fig = c.consultarFigura(identificador);             //tengo la figura que buscamos
  
                for(Figura f : fig){
                    System.out.println("La figura buscada es:%n");
                    System.out.printf("%s\t\t%f\t\t%s\t\t%d\t\t%d\t\t%s\t\t%s%n", f.getIdentificador(),f.getAlturaMetros(),
                     f.getMaterial(), f.getCantidad(), f.getAno(),f.getFoto(),f.getFabricante());
                    //return - true if y, false if n
                    opcion = Esdia.readInt("Desea eliminar la pieza de verdad?%n1- Si%n 2- No%n-> ",1,2);
                    if(opcion == 1){
                        c.eliminarPieza(identificador);
                        System.out.printf("Se ha eliminado la figura %s",identificador);
                    } 
                    else{
                        System.out.printf("No se ha eliminado la figura, se vuelve al menu de gestion");
                    }
                }
        
                
            }
             return 0;   
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

////////////////////////////////////////////////////////////// MENÚ LISTADOS //////////////////////////////////////////////////////////////////////////////////
    
    public void listadoPorIdentificador(){
        System.out.printf("%n%n%n%n%n");
        //Se ordenan los valores
        c.listadoPorIdentificadorC();
        
        String[][] listado = c.imprimirConFormato();
        
        
        System.out.printf("El listado de las figuras ordenadas por identificador es%n");
        mostrarPorPantalla(listado);
        
        
    }
        
    public void listadoPorAnoEIdentificador(){
        System.out.printf("%n%n%n%n%n");
        //Ordenamos los valores por año e identificador si tienen el mismo año
        c.listadoPorAnoEIdentificador();
        
        String[][] listado = c.imprimirConFormato();
        
        System.out.printf("El listado de las figuras ordenadas por ano e identificador es%n");
        mostrarPorPantalla(listado);
        
    }
    
    public void listadoPorFabricanteYAno(){
        System.out.printf("%n%n%n%n%n");
        
        c.listadoPorFabricanteYAno();
        
        String[][] listado = c.imprimirConFormato();
        
        System.out.printf("El listado de las figuras ordenadas por fabricante y ano %n");
        mostrarPorPantalla(listado);  
    }
    
    public void mostrarPorPantalla(String[][] figuras){
        System.out.printf("|%1s|%s|%16s|%2s|%8s|%12s|%3s%n","Identif", "Altura(M)","Material","Nº", "Ano","Foto", "Fabricante");
        //Hacemos la fincion donde se comprueba printToScreen3 para asi reciclar codigo ya que tenemos que hacerlo tres veces
        try{
            OpMat.printToScreen3(figuras);
        }catch(Exception ex){
            System.out.printf("No fue posible mostrar los valores que quiere imprimir con el metodo printToStreen3");
        }
        
    }
    

}
    
    
    
    
    
    
    
    
    
    
    

