/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Juegos.BatallaNaval;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author lio
 */
public class ObtenerImagenes {
    public Image cargar(String ruta){
        return Toolkit.getDefaultToolkit().createImage((getClass().getResource(ruta)));
    }
}
