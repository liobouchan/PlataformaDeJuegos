/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SQL;

import java.sql.*;

/**
 *
 * @author lio
 */
public class Conexion {
    private Connection conexion;
    
    public Conexion(){
    }
    
    public Connection init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/juego", "root", "");
            System.out.println("Conectado a la Base de Datos");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("No se pudo Conectar");
        }
        return conexion;
    }
    
    public Connection obtenerConexion(){
        return conexion;
    }
    
    public void cerrar(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
            }
        }
    }
    
    public void cerrar( java.sql.Statement statement ){
        if(statement != null){
            try{
                statement.close();
            }
            catch(Exception e){}
        }
    }
    
    public void destroy(){
        if(conexion != null){
            try{
                conexion.close();
                System.out.println("Conexión Cerrada");
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
}