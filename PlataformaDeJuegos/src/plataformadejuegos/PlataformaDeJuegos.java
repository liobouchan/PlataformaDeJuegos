package plataformadejuegos;

import Vistas.Bienvenida;
import Vistas.Frame;
import java.awt.BorderLayout;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lio
 */
public class PlataformaDeJuegos {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Frame frame = new Frame();
        Bienvenida bienvenida = new Bienvenida();
        frame.setLayout(new BorderLayout());
        frame.add(bienvenida, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
