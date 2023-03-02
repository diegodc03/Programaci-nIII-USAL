/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ddecm
 */
public class Figura implements Serializable{

    //Atributos de la clase Figura
    private String identificador;     //identificador de la figura --> no hay otro identificador igual
    private float alturaMetros;         // Dimensiones de la figura en metros
    private String material;          // Material de contruccion mayoritario de la figura
    private int cantidad;               // el numero de figuras de un modelo que tiene la galeria
    private int ano;                    // Año en el que se compra la figura
    private String foto;              // Nombre del archivo grafico de tipo png
    private String fabricante;        // nombre del creador de la figura
    
    //Contructor sin paso de parametros
    public Figura(){
        this.identificador = "i00000";
        this.alturaMetros = (float) 0.01;
        this.material = "xxxxx";
        this.cantidad = 1;
        this.ano = 000;
        this.foto = "arch.png";
        this.fabricante = "xxxxxxx";
    }
    
    /**
     *
     * @param identif
     * @param alturaMet
     * @param material
     * @param cantidad
     * @param ano
     * @param foto
     * @param fabricante
     */
    // Contructor con paso de parametros que se le asigna unos valores a la figura
    public Figura(String identif ,  float alturaMet , String material , int cantidad , int ano , String foto , String fabricante ){
        this.identificador = identif;
        this.alturaMetros = alturaMet;
        this.material = material;
        this.cantidad = cantidad;
        this.ano = ano;
        this.foto = foto;
        this.fabricante = fabricante;
    }
    
    // Formato que tendra la tabla de HTML
    public String asHTML(){
        return String.format("<TR>"
                            + "<td>%s</td>"
                            +     "<td>%f</td>"
                            +     "<td>%s</td>"
                            +     "<td>%d</td>"
                            +     "<td>%d</td>"
                            +     "<td>%s</td>"
                            +     "<td>%s</td>"
                            +     "<TR>",
                this.identificador, this.alturaMetros, this.material, this.cantidad, this.ano,this.foto, this.fabricante);
    }
   
    
    // Metodo para tratar la exportacion de ficheros, cada celda de la fila tiene un tipo atomico que hay que ver si la informacion del 
    // archivo se puede convertir al que es, es decir tiene el mismo tipo atomico.
    //Se importa en formato String
    public static Figura factory(String[] tmp){
        float tmp1;
        int tmp3,tmp4;
        for(String s : tmp){
            if(s.isBlank()){
                return null;
                //retorna null si alguna de las opciones de la figura esta a NULL, ya que no debe haber ninguna
            }
        }
        
        //Hemos importado un array de String, eso significa que tendremos que pasar todos los valores a los tipos atomicos que necesitemos
        if(tmp[0] == null){
            return null;
        }
        //Hacemos comprobacion si la celda 5 que es foto, contiene .png al final ya que es necesario
        
        if(!tmp[5].endsWith(".png")){
            return null;
        }

        //Cambiamos a float o int dependiendo la variable 
        try {
            tmp1 = Float.parseFloat(tmp[1]);
            tmp3 = Integer.parseInt(tmp[3]);
            tmp4 = Integer.parseInt(tmp[4]);
        } catch(NumberFormatException e) {
            //Si el valor de la altura se retorna null significa que en la posicion de la altura no estaba un valor correcto
            return null;
        }
              
        // Creamos la figura llamando al constructor y la retornamos al metodo que habia llamado a este.
        return new Figura(tmp[0], tmp1, tmp[2],tmp3, tmp4, tmp[5], tmp[6]);
    }
    
    //Metodo que devuelve en formato String la informacion sobre una figura
    public String escribeElFicheroConDELIMITER(String DELIMITER){
        return String.format("%s%s%f%s%s%s%d%s%d%s%s%s%s",
                this.identificador,DELIMITER,
                this.alturaMetros, DELIMITER, 
                this.material, DELIMITER,
                this.cantidad, DELIMITER,
                this.ano, DELIMITER,
                this.foto, DELIMITER,
                this.fabricante);
    
   }
    
   
    public String[] valoresFigura(){
       String[] tmp = new String[7];
       tmp[0] = this.identificador;
       tmp[1] = String.valueOf(alturaMetros);
       tmp[2] = this.material;
       tmp[3] = String.valueOf(cantidad);
       tmp[4] = String.valueOf(ano);
       tmp[5] = this.foto;
       tmp[6] = this.fabricante;
       
       return tmp;
   }
   
    ///////////////////////////////////////////////// GETTERS Y SETTERS //////////////////////////////////////////////////////////////////////////
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public float getAlturaMetros() {
        return alturaMetros;
    }
    public void setAlturaMetros(float alturaMetros) {
        this.alturaMetros = alturaMetros;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

   
   
    
    
    
    
    
    
}
