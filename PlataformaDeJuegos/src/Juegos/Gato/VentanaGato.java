/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.Gato;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lio
 */
public class VentanaGato extends JFrame{
    private JLabel resultado;
    private JPanel panel;
    private JButton restar;
    
    public VentanaGato(){
        super("Juego Gato");
        init();
        Propiedades();
        Acomoda();
        Eventos();
    }
    
    public final void init(){
        resultado = new JLabel("Barra estado");
        restar = new JButton("Reiniciar");
    }
    
    public final void Propiedades(){
        
    }
    
    public final void Acomoda(){
        
    }
    
    public final void Eventos(){
        restar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
    }
    
    public void setPanelCentro(final PanelCampo pc){
        this.add(restar, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        this.add(resultado, BorderLayout.SOUTH);
    }
    
    public void setMsjBarraEstado(String msj){
        resultado.setText(msj);
    }
    
    public JButton getRestar(){
        return this.restar;
    }    
}
