/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.Gato;

import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lio
 */
public class PanelCampo extends JPanel{
   private final ImageIcon iconoX;
    private final ImageIcon iconoO;
    private final ImageIcon negro;
    private JLabel[][] campoGato;
    
    public PanelCampo() {
        setLayout(new GridLayout(3, 3, 3, 3));
        negro = new ImageIcon(getClass().getResource("Negro.jpg"));
        iconoO = new ImageIcon(getClass().getResource("iconoO.jpg"));
        iconoX = new ImageIcon(getClass().getResource("iconoX.jpg"));
        campoGato = new JLabel[3][3];
        
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                campoGato[i][j] = new JLabel(negro);
                campoGato[i][j].setName(""+i+""+j);
                this.add(campoGato[i][j]);
            }
        }
    }
    
    public void restart(){
        this.removeAll();
        this.setLayout(new GridLayout(3, 3, 3, 3));
        Icon ico = new ImageIcon(
                            getClass().getResource(
                                "Negro.jpg")
                        );
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                campoGato[i][j] = null;
                campoGato[i][j] = new JLabel(ico);
                campoGato[i][j].setName(""+i+""+j);
                this.add(campoGato[i][j]);
            }
        }
        this.repaint();
    }
    
    public void setTiro(char tiro, int i, int j){
        if(tiro == 'X'){
            campoGato[i][j].setIcon(iconoX);
        }else if(tiro == 'O'){
            campoGato[i][j].setIcon(iconoO);
        }else{
            campoGato[i][j].setIcon(negro);
        }
    }
    
    public JLabel getCasilla(int i, int j){
        return campoGato[i][j];
    }   
}
