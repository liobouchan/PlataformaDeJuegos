/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plataformadejuegos;

import javax.swing.JOptionPane;

/**
 *
 * @author lio
 */
public class Victorioso extends Resultado{
    public Victorioso( ){
        super();
    }
    public Victorioso(String resultado){
        super(resultado);
    }
    public void Imprimir(){
        JOptionPane.showMessageDialog(null, "Fuiste " + getResultado());
    }
    
}
