/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.BuscaMinas;

import static java.lang.Thread.sleep;
import javax.swing.JFrame;

/**
 *
 * @author lio
 */
public class Tiempo extends Thread{
    Buscaminas buscaMinas;
    boolean Exit= false;
    int segundos = 0;

    Tiempo (JFrame j){
        System.out.println("\n Comienza el tiempo...");
        buscaMinas = (Buscaminas)j;
    }
    
    public void Tiempo(){
        
    }
    
    public void Tiempo(Tiempo unTiempo){
        this.segundos = segundos;
    }
    
    public void destruir(){
        this.segundos = 0;
        System.gc();
    }

    @Override
    public void run(){
        while(!Exit){
        try{
            sleep(1000);
            segundos++;
            buscaMinas.txtTime.setText(Integer.toString(segundos));
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        }
    }

    public void parar(boolean b){
        if(b)Exit = true;
        segundos = 0;
    }   
}
