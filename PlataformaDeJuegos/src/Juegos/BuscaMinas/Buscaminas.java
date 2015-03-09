/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.BuscaMinas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import plataformadejuegos.Victorioso;

/**
 *
 * @author lio
 */
public class Buscaminas extends JFrame implements ActionListener{
    JButton[][] botones;
    int [][] minas;
    JTextField txtTime = new JTextField(3);
    JTextField txtMinas = new JTextField(3);
    JLabel time = new JLabel("Tiempo: ");
    JLabel minasRestantes = new JLabel("Minas restantes: ");
    
    int numeroBotones;
    int numeroMinas;
    int total = numeroBotones*numeroBotones-numeroMinas;
    
    Tiempo t;

    public Buscaminas(int numeroBotones , int numeroMinas){
        super("Buscaminas");
        this.numeroBotones = numeroBotones;
        this.numeroMinas = numeroMinas;
        botones = new JButton[numeroBotones][numeroBotones];
        minas = new int[numeroBotones][numeroBotones];
        
        Buscador busca = new Buscador("buscaminas");
        busca.Imprimir();
        busca.destruir();
        JPanel panelNorth = new JPanel();
        panelNorth.add(minasRestantes);
        panelNorth.add(txtMinas);
        panelNorth.add(time);
        panelNorth.add(txtTime);
        add(panelNorth,"North");
        txtMinas.setEditable(false);
        txtTime.setEditable(false);
        txtMinas.setText(Integer.toString(total));
        JPanel panelCenter = new JPanel(new GridLayout(numeroBotones,numeroBotones));
        
        for(int i = 0; i<numeroBotones ; i++)
           for(int j = 0; j<numeroBotones ; j++)
           {
                /*Creamos un botón*/
                botones [i][j] = new JButton();
                /*Agregamos a nuestro panel central*/
                panelCenter.add(botones[i][j]);
                /*ActionListener*/
                botones[i][j].addActionListener(this);
           }
                this.add(panelCenter,"Center");
                /*Metodo que coloca el número de minas que ingresa el usuario*/
                colocar(numeroMinas);

                /*Arranca el tiempo*/
                t = new Tiempo(this);
                t.start();


                 /*Características de la ventana*/
                 setDefaultCloseOperation(HIDE_ON_CLOSE);
                 setTitle("Buscaminas");
                 setResizable(true);
                 setSize(600,600);
                 setVisible(true);
    }
    
    public void Buscaminas(){
    
    }

    public void Buscaminas(Buscaminas unBuscaminas){
        this.botones = unBuscaminas.botones;
    }
    
    public void destruir(){
        this.botones = null;
        System.gc();
    }
    
    void colocar(int numMinas){
        System.out.println("Colocando Minas... \n");
        for(int i = 0; i<numMinas ;i++){
            int x,y = 0;
            double w,z = 0;
            do{
                w = Math.random()*numeroBotones;
                z = Math.random()*numeroBotones;
                x = (int)w;
                y = (int)z;
            }
            while(minas[x][y] != 0);
                minas[x][y] = 1;
         }
         for(int i = 0; i<numeroBotones ; i++){
            System.out.println();
            for(int j = 0; j<numeroBotones ; j++)
                System.out.print(" " + minas[i][j]);
         }
    } 
    
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i<numeroBotones ;i++)
            for(int j = 0; j<numeroBotones ;j++){
                if(e.getSource() == botones[i][j] && botones[i][j].getBackground()!= Color.WHITE){ 
                    botones[i][j].setBackground(Color.WHITE);
                    if(minas[i][j] == 1){
                        mina();
                    }
                    else{
                        casillaSinMina(i,j);
                    }
                }
            }
    }
    
    void casillaSinMina(int i, int j){
        total--;
        txtMinas.setText(Integer.toString(total));
        botones[i][j].setText(Integer.toString(minasCerca(i,j))); //Cuantas Minas cerca
        if(total == 0)
           ganaste();
    }
    
    void ganaste(){
        t.stop();
        JOptionPane.showMessageDialog(this,"Has ganado. \nTu tiempo fue de: " + txtTime.getText() + " segundos");
        nuevoJuego();
    }
    
    void mina(){
        t.stop(); 
        for(int i = 0 ; i<numeroBotones ; i++)
            for(int j=0 ; j<numeroBotones ; j++){
                if(minas[i][j] == 1){
                    botones[i][j].setBackground(Color.RED);
                }
            }
            String resultado = "Perdedor";
            Victorioso herencia = new Victorioso(resultado);
            herencia.getResultado();
            herencia.Imprimir();
            herencia.destruir();
//JOptionPane.showMessageDialog(this,"Lo siento... Has perdido!");
        nuevoJuego();
    }
    int minasCerca(int x,int y){
        int minCerca=0;
        for(int i = y-1 ; i<= y+1; i++){
            if(i>-1 && i<numeroBotones){
                if(minas[x][i] == 1){
                   minCerca++;
                }
            }
        }
        for(int j= x-1 ; j<=x+1; j++){
            if(j>-1 && j<numeroBotones)
                if(minas[j][y] == 1){
                    minCerca++;
                }
        }
        int w = x;
        int z = y;
        w--;
        z--;
        for(int i=w; i<w+3 ; i++){
           if(i>-1 && i<numeroBotones && z>-1 && z<numeroBotones)
               if(minas[i][z] == 1){
                   minCerca++;
               }
               z++;
        }
        w = x;
        z = y;
        w--;
        z++;
        for(int i = w ; i<w+3 ; i++){
            if(i>-1 && i<numeroBotones && z>-1 && z<numeroBotones)
                if(minas[i][z] == 1){
                    minCerca++;
                }
                z--;
        }
        return minCerca;
    }
    
    void nuevoJuego(){
        for(int i = 0; i<numeroBotones ; i++)
            for(int j=0 ; j<numeroBotones ; j++){
                minas[i][j] = 0;
                botones[i][j].setText("");
                botones[i][j].setBackground(null);
            }
            colocar(numeroMinas);
            total = numeroBotones*numeroBotones-numeroMinas;
            txtMinas.setText(Integer.toString(total));
            t = new Tiempo(this);
            t.start();
    }
    
}


