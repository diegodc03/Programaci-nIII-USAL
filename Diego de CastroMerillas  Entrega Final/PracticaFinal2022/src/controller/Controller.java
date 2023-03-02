/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import model.Model;
import model.Figura;
/**
 *
 * @author ddecm
 */
public class Controller {
    
    Model m = new Model();
  
    ///////////////////////////////////////////// METODOS PARA PASAR DEL MODELO A LA VISTA Y AL REVES /////////////////////////////////////////////////////////////////////
    
    public void comienzoPrograma(){
        m.comienzo();
    }
    
    public void salirPrograma(){
        m.finalPrograma();
    }
    
    
    public void importarArchivosCSV(){
        m.importarCSV();
    }
    
    public void exportarArchivoCSV(){
        m.exportarFiguarasCSV();
    }
  
    
    public void introducirNuevaPieza(String identif ,float alturaMetros , String material ,int cantidad ,int ano ,String foto ,String fabricante){
        m.introducirNuevaPiezas(identif ,  alturaMetros , material , cantidad , ano , foto , fabricante);
    }
    
    public void eliminarPieza(String pieza){
        m.eliminarPieza(pieza);
    }
    
    public List<Figura> consultarFigura(String identificador){
        return m.consultarFiguraId(identificador);
    }
    
    public void cambiarDatosFigura(String identificador, float alturaM, String material, int cantidad, int ano, String foto, String fabricante){
        m.cambiarDatos(identificador, alturaM, material, cantidad, ano, foto , fabricante);
    }


    public int piezaBucle(String identif){
        int dev = m.piezaBucle(identif);
        return dev;
    }
    
    public void exportarHTML(){
        m.exportarFigurasHTML();
    }
    
    public void listadoPorIdentificadorC(){
        m.listadoPorIdentificador();
    }
    
    public void listadoPorAnoEIdentificador(){
        m.listadoPorAnoEIdentificador();
    }
    
    public void listadoPorFabricanteYAno(){
        m.listadoPorFabricanteYAno();
    }
    
    public void print(List<Figura> f){
        m.printPantalla( f);
    }
    
    public String[][] imprimirConFormato(){
        return m.imprimirConFormato();
    }
    
    public String ponerPrimeraLetraMayuscula(String str){
        return m.ponerPrimeraLetraMayuscula(str);
    }

}