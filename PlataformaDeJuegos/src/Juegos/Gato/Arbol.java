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
public class Arbol {
    Nodo nodo00 = new Nodo('-', 0, 0);  //raiz
    Nodo nodo01 = new Nodo('-', 0, 1);
    Nodo nodo11 = new Nodo('-', 1, 1);
    Nodo nodo10 = new Nodo('-', 1, 0);
    Nodo nodo02 = new Nodo('-', 0, 2);
    Nodo nodo12 = new Nodo('-', 1, 2);
    Nodo nodo22 = new Nodo('-', 2, 2);
    Nodo nodo21 = new Nodo('-', 2, 1);
    Nodo nodo20 = new Nodo('-', 2, 0);
    
    public Arbol(){
        
        nodo00.addHijo(nodo01);
        nodo00.addHijo(nodo11);
        nodo00.addHijo(nodo10);
        
        nodo01.addHijo(nodo02);
        
        nodo11.addHijo(nodo12);
        nodo11.addHijo(nodo22);
        nodo11.addHijo(nodo11);
        
        nodo10.addHijo(nodo20);
    }
    
    public void destruir(){
        System.gc();
    } 
    
    public void setTiro(char tiro, int X, int Y){
        String coordenada = "" + X + Y;
        switch(coordenada){
            case "00": nodo00.setTiro(tiro); break;
            case "01": nodo01.setTiro(tiro); break;
            case "02": nodo00.setTiro(tiro); break;
            case "10": nodo00.setTiro(tiro); break;
            case "11": nodo00.setTiro(tiro); break;
            case "12": nodo00.setTiro(tiro); break;
            case "20": nodo00.setTiro(tiro); break;
            case "21": nodo00.setTiro(tiro); break;
            case "22": nodo00.setTiro(tiro); break;
        }
    }
    
    public void ImprimeArbol(){
        System.out.println(nodo00.buscaBSF('X'));
    }
    
    public static void main(String[] args){
        Arbol a = new Arbol();
        
        a.setTiro('x', 0, 0);
        a.setTiro('x', 1, 0);
        a.setTiro('x', 2, 1);
        
        a.ImprimeArbol();
    }
}
