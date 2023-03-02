/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicafinal2022;
import view.View;
import com.coti.tools.DiaUtil;
/**
 *
 * @author ddecm
 */
//flatlaf dark
public class PracticaFinal2022 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View v = new View();
        DiaUtil.startTimerMS();
        //Menu principal del programa
        
        v.runMenu("%n 1.- Archivos"
                + "%n 2.- Gestion de la Galeria"
                + "%n 3.- Listados"
                + "%n 4.- Salir%n"
                + "-> ");
        
        
        
        
        
    }
    
}



