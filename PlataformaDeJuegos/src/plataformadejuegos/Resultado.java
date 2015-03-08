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
public class Resultado {
    public String resultado;
    
    public Resultado(String resultado){
        this.resultado = resultado;
    }
    
    public Resultado(){
    }
    
    public String getResultado(){
        return resultado;
    }
    
    public void destruir(){
        this.resultado = null;
        System.gc();
    }
}
