/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.Gato;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lio
 */
public class Gato {
    VentanaGato v = new VentanaGato();
    PanelCampo pc = new PanelCampo();
    PanelCampoBit pcb = new PanelCampoBit();
    
    private int incrementa = 1;
    private boolean OX;

    public Gato(){
        ManejoCampo();
        
        v.setLayout(new BorderLayout());
        v.setPanelCentro(pc);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setSize(600, 600);
        v.setVisible(true);
        v.getRestar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                pc.restart();
                pcb.restar();
                ManejoCampo();
                if(incrementa%2 == 0){
                    v.setSize(v.getWidth() + 1, v.getHeight() + 1);
                }else{
                    v.setSize(v.getWidth() -1, v.getHeight() - 1);
                }
            }
        });
    }
    
    
    
    public final void ManejoCampo(){
        MouseAdapter ma = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                OX = incrementa%2 == 0;
                if(!OX){
                    v.setMsjBarraEstado("Tira O");
                    switch(me.getComponent().getName()){
                        case "00": pc.setTiro('X', 0, 0);
                                   pc.getCasilla(0, 0).removeMouseListener(this);
                                   pcb.setCaracter('X', 0, 0);
                                   break;
                        case "01": pc.setTiro('X', 0, 1);
                                   pc.getCasilla(0, 1).removeMouseListener(this);
                                   pcb.setCaracter('X', 0, 1);
                                   break;
                        case "02": pc.setTiro('X', 0, 2);
                                   pc.getCasilla(0, 2).removeMouseListener(this);
                                   pcb.setCaracter('X', 0, 2);
                                   break;
                        case "10": pc.setTiro('X', 1, 0);
                                   pc.getCasilla(1, 0).removeMouseListener(this);
                                   pcb.setCaracter('X', 1, 0);
                                   break;
                        case "11": pc.setTiro('X', 1, 1);
                                   pc.getCasilla(1, 1).removeMouseListener(this);
                                   pcb.setCaracter('X', 1, 1);
                                   break;
                        case "12": pc.setTiro('X', 1, 2);
                                   pc.getCasilla(1, 2).removeMouseListener(this);
                                   pcb.setCaracter('X', 1, 2);
                                   break;
                        case "20": pc.setTiro('X', 2, 0);
                                   pc.getCasilla(2, 0).removeMouseListener(this);
                                   pcb.setCaracter('X', 2, 0);
                                   break;
                        case "21": pc.setTiro('X', 2, 1);
                                   pc.getCasilla(2, 1).removeMouseListener(this);
                                   pcb.setCaracter('X', 2, 1);
                                   break;
                        case "22": pc.setTiro('X', 2, 2);
                                   pc.getCasilla(2, 2).removeMouseListener(this);
                                   pcb.setCaracter('X', 2, 2);
                                   break;
                    }
                    Ganador('X');
                }else{
                    v.setMsjBarraEstado("Tira X");
                    switch(me.getComponent().getName()){
                        case "00": pc.setTiro('O', 0, 0);
                                   pc.getCasilla(0, 0).removeMouseListener(this);
                                   pcb.setCaracter('O', 0, 0);
                                   break;
                        case "01": pc.setTiro('O', 0, 1);
                                   pc.getCasilla(0, 1).removeMouseListener(this);
                                   pcb.setCaracter('O', 0, 1);
                                   break;
                        case "02": pc.setTiro('O', 0, 2);
                                   pc.getCasilla(0, 2).removeMouseListener(this);
                                   pcb.setCaracter('O', 0, 2);
                                   break;
                        case "10": pc.setTiro('O', 1, 0);
                                   pc.getCasilla(1, 0).removeMouseListener(this);
                                   pcb.setCaracter('O', 1, 0);
                                   break;
                        case "11": pc.setTiro('O', 1, 1);
                                   pc.getCasilla(1, 1).removeMouseListener(this);
                                   pcb.setCaracter('O', 1, 1);
                                   break;
                        case "12": pc.setTiro('O', 1, 2);
                                   pc.getCasilla(1, 2).removeMouseListener(this);
                                   pcb.setCaracter('O', 1, 2);
                                   break;
                        case "20": pc.setTiro('O', 2, 0);
                                   pc.getCasilla(2, 0).removeMouseListener(this);
                                   pcb.setCaracter('O', 2, 0);
                                   break;
                        case "21": pc.setTiro('O', 2, 1);
                                   pc.getCasilla(2, 1).removeMouseListener(this);
                                   pcb.setCaracter('O', 2, 1);
                                   break;
                        case "22": pc.setTiro('O', 2, 2);
                                   pc.getCasilla(2, 2).removeMouseListener(this);
                                   pcb.setCaracter('O', 2, 2);
                                   break;
                    }
                    Ganador('O');
                }
                incrementa ++;
            }
        };
        
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                pc.getCasilla(i, j).addMouseListener(ma);
            }
        }
    }   
    
    public void CrandoArbol(char c, int X, int Y){
        if(incrementa == 0){
            
        }
    }
    
    
    public void Ganador(char tirador){
        int contador = 0;
        int i, j;
        
        //--Busca líena Horizontal
        for(i = 0; i < 3; i ++){
            for(j = 0; j < 3; j ++){
                if(pcb.getCaracter(i, j) == tirador){
                    contador ++;
                }
            }
            if(contador == 3){
                v.setMsjBarraEstado("Gana el tirador " + tirador);
                JOptionPane.showMessageDialog(null, "Ganador: " + tirador + " por linea horizontal");
                break;
            }else{
                contador = 0;
            }
        }
        contador = 0;
        //--Busca líena vertical
        for(i = 0; i < 3; i ++){
            for(j = 0; j < 3; j ++){
                if(pcb.getCaracter(j, i) == tirador){
                    contador ++;
                }
            }
            if(contador == 3){
                v.setMsjBarraEstado("Gana el tirador " + tirador );
                JOptionPane.showMessageDialog(null, "Ganador: " + tirador + " por linea vertical");
                break;
            }else{
                contador = 0;
            }
        }
        contador = 0;
        //--Busca línea diagonal
        for(i = 0; i < 3; i ++){
            for(j = 0; j < 3; j++){
                if(i == j && pcb.getCaracter(i, j) == tirador){
                    contador ++;
                }
            }
            if(contador == 3){
                v.setMsjBarraEstado("Gana el tirador " + tirador);
                JOptionPane.showMessageDialog(null, "Ganador: " + tirador + " por linea diagonal");
            }
        }
        contador = 0;
        //--Busca línea slash
        for(i = 0; i < 3; i ++){
            for(j = 0; j < 3; j++){
                if(j == 2-i && pcb.getCaracter(i, j) == tirador){
                    contador ++;
                }
            }
            if(contador == 3){
                v.setMsjBarraEstado("Gana el tirador " + tirador);
                JOptionPane.showMessageDialog(null, "Ganador: " + tirador + " por linea slash");
            }
        }
    }
    
    public static void main(String[] rgs){
        new Gato();
    }   
}
