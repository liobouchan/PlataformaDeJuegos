/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.BatallaNaval;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author lio
 */
public class BatallaNaval extends javax.swing.JFrame {

    /**
     * Creates new form BatallaNaval
     */
    Image tablero;
    int nEstado = 0;
    int tableroMio[][]=new int[8][8];
    boolean bTableroMio[][]=new boolean[8][8];
    int tableroSuyo[][]=new int[8][8];
    boolean bTableroSuyo[][]=new boolean[8][8];
    int pFila=0;
    int pCol=0;
    int pTam=5;
    int pHor=0;
    
    public BatallaNaval() {
        ObtenerImagenes i =new ObtenerImagenes();
        tablero=i.cargar("tablero.jpeg");
        initComponents();
        setBounds(0,0,800,600);
        addMouseListener(
            new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK && nEstado==1){
                        pHor=1-pHor;
                        rectificarBarcoPoner();
                        repaint();
                        return;
                    }
                    if (nEstado==0){
                        nEstado=1;
                        iniciarPartida();
                        repaint();
                    }else if (nEstado==1){
                        if (puedePonerBarco()){
                            int pDF=0;
                            int pDC=0;
                            if (pHor==1){
                                pDF=1;
                            }else{
                                pDC=1;
                            }
                            for (int m=pFila;m<=pFila+(pTam-1)*pDF;m++){
                                for (int n=pCol;n<=pCol+(pTam-1)*pDC;n++){
                                    tableroMio[m][n]=pTam;
                                }
                            }
                            pTam--;
                            if (pTam==0){
                                nEstado=2;
                                repaint();
                            }
                        }
                    }else if (nEstado==2){
                        int f=(e.getY()-200)/30;
                        int c=(e.getX()-450)/30;
                        if (f!=pFila || c!=pCol){
                            pFila=f;
                            pCol=c;
                            if (celdaEstaEnTablero(f, c)){
                                if (bTableroSuyo[f][c]==false){
                                    bTableroSuyo[f][c]=true;
                                    repaint();
                                    if (victoria(tableroSuyo, bTableroSuyo)){
                                        JOptionPane.showMessageDialog(null, "Has ganado");
                                        nEstado=0;
                                    }
                                    dispararEl();
                                    repaint();
                                    if (victoria(tableroMio, bTableroMio)){
                                        JOptionPane.showMessageDialog(null, "Has perdido");
                                        nEstado=0;
                                    }
                                    repaint();
                                }
                            }
                        }
                    }
                }
            }
        );
        addMouseMotionListener(
            new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int x=e.getX();
                    int y=e.getY();
                    if (nEstado==1 && x>=100 && y>=200 && x<100+30*8 && y<200+30*8){
                        int f=(y-200)/30;
                        int c=(x-100)/30;
                        if (f!=pFila || c!=pCol){
                            pFila=f;
                            pCol=c;
                            rectificarBarcoPoner();
                            repaint();
                        }
                    }
                }
            }
        );
    }
    
    public void iniciarPartida(){
        for ( int n=0; n<8 ; n++ ){
            for ( int m=0; m<8 ; m++ ){
                tableroMio[n][m]=0;
                tableroSuyo[n][m]=0;
                bTableroMio[n][m]=false;
                bTableroSuyo[n][m]=false;
            }
        }
        for ( int tam=5; tam>=1; tam-- ){
            ponerBarco(tableroSuyo, tam);
        }
        pTam = 5;
    }
    
    public void pintarTablero(Graphics g, int tab[][], int x, int y, boolean bVisible[][]){
        for ( int n=0; n<8; n++ ){
            for ( int m=0; m<8; m++ ){
                if (tab[n][m]>0 && bVisible[n][m]){
                    g.setColor(Color.yellow);
                    if (noHayInvisible(tab, tab[n][m], bVisible)){
                        g.setColor(Color.red);
                    }
                    g.fillRect(x+m*30, y+n*30, 30, 30);
                }
                if (tab[n][m]==0 && bVisible[n][m]){
                    g.setColor(Color.cyan);
                    g.fillRect(x+m*30, y+n*30, 30, 30);
                }
                if (tab[n][m]>0 && tab==tableroMio && !bVisible[n][m]){
                    g.setColor(Color.gray);
                    g.fillRect(x+m*30, y+n*30, 30, 30);
                }
                g.setColor(Color.black);
                g.drawRect(x+m*30, y+n*30, 30, 30);
                if (nEstado==1 && tab==tableroMio){
                    int pDF=0;
                    int pDC=0;
                    if (pHor==1){
                        pDF=1;
                    }else{
                        pDC=1;
                    }
                    if (n>=pFila && m>=pCol && n<=pFila+(pTam-1)*pDF && m<=pCol+(pTam-1)*pDC){
                        g.setColor(Color.green);
                        g.fillRect(x+m*30, y+n*30, 30, 30);
                    }
                }
            }
        }
    }

    public void paint(Graphics g){
        g.drawImage(tablero, 0,0,this);
        pintarTablero(g, tableroMio, 100, 200, bTableroMio);
        pintarTablero(g, tableroSuyo, 450, 200, bTableroSuyo);
    }
    
    public boolean celdaEstaEnTablero(int f, int c){
        if (f<0) return false;
        if (c<0) return false;
        if (f>=8) return false;
        if (c>=8) return false;
        return true;
    }
    
    public void ponerBarco(int tab[][], int tam){

        int f,c,hor;
        do{
            f=(int)(Math.random()*8);
            c=(int)(Math.random()*8);
            hor=(int)(Math.random()*2);
        }while(!puedePonerBarco(tab, tam, f, c, hor));
        int df=0,dc=0;
        if (hor==1) df=1;
        else dc=1;
        for (int f2=f;f2<=f+(tam-1)*df;f2++){
            for (int c2=c;c2<=c+(tam-1)*dc;c2++){
                tab[f2][c2]=tam;
            }
        }
    }
        
    public boolean puedePonerBarco(int tab[][], int tam, int f, int c, int hor){
        int df=0,dc=0;
        if (hor==1) df=1;
        else dc=1;
        for (int c2=c;c2<=c+tam*dc;c2++){
            for (int f2=f;f2<=f+tam*df;f2++){
                if (!celdaEstaEnTablero(f2, c2)){
                    return false;
                }
            }
        }
        for (int f2=f-1;f2<=f+1+df*tam;f2++){
            for (int c2=c-1;c2<=c+1+dc*tam;c2++){
                if (celdaEstaEnTablero(f2,c2)){
                    if (tab[f2][c2]!=0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void rectificarBarcoPoner(){
        int pDF=0;
        int pDC=0;
        if (pHor==1){
            pDF=1;
        }else{
            pDC=1;
        }
        if (pFila+pTam*pDF>=8){
            pFila=7-pTam*pDF;
        }
        if (pCol+pTam*pDC>=8){
            pCol=7-pTam*pDC;
        }
    }
    
    public boolean puedePonerBarco(){
        return puedePonerBarco(tableroMio, pTam, pFila, pCol, pHor);
    }
    public boolean noHayInvisible(int tab[][], int valor, boolean bVisible[][]){
        for (int n=0;n<8;n++){
            for (int m=0;m<8;m++){
                if (bVisible[n][m]==false){
                    if (tab[n][m]==valor){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
        public void dispararEl(){
        int f,c;
        do{
            f=(int)(Math.random()*8);
            c=(int)(Math.random()*8);
        }while(bTableroMio[f][c]==true);
        bTableroMio[f][c]=true;
    }
        
     public boolean victoria(int tab[][], boolean bTab[][]){
        for (int n=0;n<8;n++){
            for (int m=0;m<8;m++){
                if (bTab[n][m]==false && tab[n][m]!=0){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BatallaNaval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BatallaNaval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BatallaNaval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BatallaNaval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BatallaNaval().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
