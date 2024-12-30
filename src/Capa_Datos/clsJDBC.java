/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsJDBC {
    private String driver,url,user,password;
        private Connection con;
        private Statement sent=null;
        
        // constructor
        public clsJDBC(){
            this.driver="org.postgresql.Driver";
            this.url="jdbc:postgresql://localhost:5432/pry_Clinica";
            this.user="postgres";
            this.password="1234";
            this.con=null;
        }
                
        public void conectar() throws Exception 
        {
            try 
            {
                Class.forName(driver);
                con=DriverManager.getConnection(url,user,password);
            }
            catch(ClassNotFoundException | SQLException ex)
            {
                throw new Exception ("Error al conectar a la BD!");
            }
        }
        
        
        public void desconectar() throws Exception{
            try
                {
                con.close();    
                } 
            catch(SQLException ex)
                {
                throw new Exception("Error al desconectarse de la BD!");
                }
      
         }
        
        public void desconectar2() throws Exception {
        try {
            if (con != null && !con.isClosed()) { // Verifica si la conexión está abierta antes de cerrar
                con.close();
            }
        } catch (SQLException ex) {
            throw new Exception("Error al desconectarse de la BD: " + ex.getMessage());
        }
    }
        
        public ResultSet consultarBD (String sql)throws Exception{
            ResultSet rs=null;
            try
            {
            conectar();
            sent=con.createStatement();
            rs=sent.executeQuery(sql);
            return rs;
            }
            catch(Exception e)
            {
                throw new Exception("Error al ejecutar consulta "+e.getMessage());
            }
            finally{
                if (con!=null) {
                    desconectar();
                }
            }
        }
        
        public void ejecutarBD(String strSql)throws Exception{
            try{
                conectar();
                sent=con.createStatement();
                sent.executeUpdate(strSql);
            }catch(Exception e){
                throw new Exception("Error al ejecutar Update-->"+e.getMessage());
            }finally{
                if (con!=null) {
                    desconectar();
                }
            }
        }
        
        public void ejecutarBDTransacciones(String strSQL1,String strSQL2,String strSQL3) throws Exception{
            try {
                conectar();
                con.setAutoCommit(false);
                sent=con.createStatement();
                sent.executeUpdate(strSQL1);
                sent=con.createStatement();
                sent.executeUpdate(strSQL2);
                sent=con.createStatement();
                sent.executeUpdate(strSQL3);
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Error al ejecutar transaccion");
            }finally{
                if(con!=null){
                    desconectar();
                }
            }
        }
       public Connection getCon(){
           return con;
       }
       
        public ResultSet consultarBD2(String sql) throws Exception {
        try {
            Statement sent = con.createStatement(); // Usa la conexión ya abierta
            return sent.executeQuery(sql);
        } catch (SQLException e) {
            throw new Exception("Error al ejecutar consulta: " + e.getMessage());
        }
    }

    public void ejecutarBD2(String strSql) throws Exception {
        try {
            Statement sent = con.createStatement(); // Usa la conexión ya abierta
            sent.executeUpdate(strSql);
        } catch (SQLException e) {
            throw new Exception("Error al ejecutar Update: " + e.getMessage());
        }
    }

    
       
}
