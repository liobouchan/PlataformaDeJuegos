/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.Gato;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author lio
 */
public class Nodo {
    private Character tiro;
    private List<Nodo> hijos;
    private int[] coordenada;
    
    public Nodo(char c) {
        this.tiro = c;
        this.coordenada = new int[2];
        hijos = new ArrayList<>();
    } 
    
    public Nodo(char c, int x, int y) {
        this.tiro = c;
        this.coordenada = new int[2];
        this.coordenada[0] = x;
        this.coordenada[1] = y;
        hijos = new ArrayList<>();
    } 
    
    public void Nodo(Nodo unNodo){
        this.coordenada = unNodo.coordenada;
    }

    public void destruir(){
        this.coordenada = null;
        System.gc();
    } 
    
    public void addHijo(Nodo h){
        hijos.add(h);
    }
    
    public List<Nodo> getHijos(){
        return this.hijos;
    }
    
    public char getChar(){
        return this.tiro;
    }
    
    public boolean isHoja(){
        return this.hijos.isEmpty();
    }
    
    public int getX(){
        return this.coordenada[0];
    }
    
    public int getY(){
        return this.coordenada[1];
    }

    public void setTiro(char tiro){
        this.tiro = tiro;
    }
    
    public boolean buscaBSF(char x){
        Queue<Nodo> cola = new LinkedList<>();
        int ent = 0;
        cola.add(this);
        while(!cola.isEmpty() && ent < 10){
            Nodo r = cola.poll();
            System.out.println(r.getChar() + " X=" + r.getX() + " Y=" + r.getY());
            if(r.getChar() == x){     
                return true;
            }else{
                for(Nodo h: r.getHijos()){
                    cola.add(h);
                }
            }
            ent ++;
        }
        return false;
    }
    
    public boolean buscaDSF(char c){
    
    Stack<Nodo> pila = new Stack<Nodo>();
        pila.push(this);
        while(pila.size()!=0){
            Nodo r=pila.pop();
            System.out.println(r.getChar());
            if(r.getChar()==c)
                return true;
        else
                for(int i = r.getHijos().size()-1; i>=0; i--)
                    pila.push(r.getHijos().get(i));
        }
    return false;
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }    
}
