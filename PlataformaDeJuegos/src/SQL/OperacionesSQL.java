/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lio
 */
public class OperacionesSQL {
    public Connection Registrar(String usuario , String password){
        Conexion conexion = new Conexion();
        Connection registro = conexion.init();
        String sql;
        int n;
        sql = "INSERT INTO jugador (usuario,password)VALUES (?,?)";
        try{
            PreparedStatement pst = registro.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password);
            n = pst.executeUpdate();
            if( n > 0 ){
                JOptionPane.showMessageDialog(null,"registrado con exito");
            }
                conexion.destroy();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null;
        
    }
}