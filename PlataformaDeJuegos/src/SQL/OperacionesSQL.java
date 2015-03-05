/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lio
 */
public class OperacionesSQL {
   Conexion conexion = new Conexion();
   Connection comodin = conexion.init();
   String sql;
   ResultSet resultSet;
   Statement statement;
   
    public void Registrar(String usuario , String password){
        int n;
        try{

            if(ValidarUsuario(usuario) != 1){
                sql = "INSERT INTO jugador (usuario,password)VALUES (?,?)";
                PreparedStatement pst = comodin.prepareStatement(sql);
                pst.setString(1, usuario);
                pst.setString(2, password);
                n = pst.executeUpdate();
                if( n > 0 ){
                    JOptionPane.showMessageDialog(null,"Resgistrado con éxito");
                }
            }else{
                JOptionPane.showMessageDialog(null,"El usuario ya existe");
            }
        }
        catch(SQLException e){
            conexion.destroy();
            System.out.println(e);
        }
        conexion.destroy();
    }      
    
    public int ValidarUsuario(String usuario){
        sql = "SELECT usuario from jugador";
        
        try{
            statement = comodin.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                if(resultSet.getString("usuario").equals(usuario))
                        return 1;
            }
        }
        catch(SQLException e){
            System.out.println(e);
            System.out.println("Error en validar usuario");
        }
        return 0;
    }
    
    public int InicioDeSesion(String usuario , String password){
        sql = "SELECT * FROM jugador WHERE usuario = '" + usuario + "' AND password = '" + password + "'";;
        int i = 0; 
        try{
            PreparedStatement pstm = comodin.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("usuario") + resultSet.getString("password"));
                i++;
                conexion.destroy();
                return i;
            }
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println(e);
            conexion.destroy();
        }
        conexion.destroy();
        return 0;
    }
}