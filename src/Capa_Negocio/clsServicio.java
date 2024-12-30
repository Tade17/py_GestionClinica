/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.*;
/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsServicio {
    clsJDBC objConectar= new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    
    public ResultSet listarServicios()throws Exception{
        strSQL="select * from servicio";
        try{
            rs=objConectar.consultarBD(strSQL);
            return rs;
        }catch(Exception e ){
            throw new Exception("Error al listar servicios---->"+e.getMessage());
        }
    }
     public ResultSet listarServiciosParaPago()throws Exception{
        strSQL="select * from servicio where estado=true";
        try{
            rs=objConectar.consultarBD(strSQL);
            return rs;
        }catch(Exception e ){
            throw new Exception("Error al listar servicios---->"+e.getMessage());
        }
    }
    public Integer generarCodigoServicio() throws Exception{
         strSQL="select COALESCE(Max(idServicio),0)+1 as id from servicio";
         try{
             rs=objConectar.consultarBD(strSQL);
             while(rs.next()){
                 return rs.getInt("id");
             }
         }catch(Exception e){
             throw new Exception("Error al generar codigo para serivicio--->"+ e.getMessage());
         }
         return 0;
         
     }   
    
     public void registrarServicio(int id,String nom,String des,Double precio ,boolean est)throws Exception{
         strSQL="Insert into servicio  values ("+id+",'"+nom+"','"+des+"',"+precio+","+est+")";
         try{
              objConectar.ejecutarBD(strSQL);
         }catch(Exception e){
             throw new Exception("Error al registrar Servicio--->"+ e.getMessage());
         }
     }
     
     
     
     public ResultSet buscarServicio(int id)throws Exception{
         strSQL="Select * from servicio where idServicio="+id;
         try{
             rs=objConectar.consultarBD(strSQL);
             return rs;
         }catch(Exception e){
             throw new Exception("Error al buscar servicio--->"+e.getMessage());
     }
         
     }
     
     public void eliminarServicio(int id) throws Exception{
         strSQL=" delete from servicio where idServicio="+id;
         try{
             objConectar.ejecutarBD(strSQL);
         }catch(Exception e){
             throw new Exception("Error al eliminar servicio--->"+e.getMessage());
         }
     }
     
     public void darBajaServicio(int id) throws Exception{
         strSQL="update servicio set estado= false where idServicio="+id;
         try{
             objConectar.ejecutarBD(strSQL);
         }catch(Exception e){
             throw new Exception("Error al dar de baja a la servicio--->" + e.getMessage());
         }
     }
     
     public void modificarServicio(int id , String nom,String des,Double precio,boolean est)throws Exception{
         strSQL="update servicio set nombre='"+nom+"', descripcion='"+des+"',precio="+ precio+", estado="+est+"  where idServicio="+id;
         try{
             objConectar.ejecutarBD(strSQL);
         }catch(Exception e){
             throw new Exception("Error al modificar la servicio---> "+e.getMessage());
         }
     }
     
      public Integer obtenerCodigoServicio(String nom)throws Exception{
       strSQL="select idServicio from servicio where nombre='"+nom+"'";
       try{
       rs=objConectar.consultarBD(strSQL);
           if (rs.next()) return rs.getInt("idServicio");
       }catch(Exception e){
           throw new Exception("Error al obtener codigo de la servicio--->"+e.getMessage());
       }
       return 0;
    }
    
}
