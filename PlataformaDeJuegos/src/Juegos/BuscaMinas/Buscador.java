/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.BuscaMinas;

/**
 *
 * @author lio
 */
public class Buscador extends plataformadejuegos.Juegos{
    //String nombre = "Buscaminas";
    public Buscador(String nombre){
        super(nombre);
    }
    
    public void Buscador(){
    
    }
    
    public void Buscador(Buscador unBuscador){
 
    }
    
    public void destruir(){
        System.gc();
    }
    
    public void Imprimir(){
        System.out.println("Estamos Implementando Herencia " + getNombreJuego());
    }
    
}
