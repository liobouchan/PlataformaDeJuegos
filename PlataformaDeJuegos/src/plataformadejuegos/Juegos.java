/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plataformadejuegos;

/**
 *
 * @author lio
 */
public class Juegos {
    public String nombreJuego;
    
    public Juegos(String nombreJuego){
        this.nombreJuego = nombreJuego;
    }
    
    public Juegos(){
    
    }
    
    public String getNombreJuego(){
        return nombreJuego;
    }
    
    public void destruir(){
        this.nombreJuego = null;
        System.gc();
    }
    
}
