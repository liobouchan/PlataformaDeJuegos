/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.Gato;

/**
 *
 * @author lio
 */
public class PanelCampoBit {
   private char[][] Matriz;

    public PanelCampoBit() {
        Matriz = new char[3][3];
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                Matriz[i][j] = '-';
            }
        }
    }
    
    public final void ImprimeMatriz(){
        System.out.println("----------");
        for(int i = 0; i < 3; i ++){
            System.out.println();
            for(int j = 0; j < 3; j ++){
                System.out.print(Matriz[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("----------");
    }
    
    public void restar(){
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j ++){
                Matriz[i][j] = '-';
            }
        }
    }
    
    public void setCaracter(char caracter, int i, int j){
        Matriz[i][j] = caracter;
    }
    
    public char getCaracter(int i, int j){
        return Matriz[i][j];
    }   
}
