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
public class clsMetodoPago {
    clsJDBC objConectar = new clsJDBC();
    ResultSet rs=null;
    String strSQL;
    
    public ResultSet listarMetodoPago()throws Exception{
        strSQL="Select * from metodo_pago";
        try {
            return rs=objConectar.consultarBD(strSQL);
        } catch (Exception e) {
        throw new Exception("Error al listar Metodo de pago--->"+e.getMessage());
        }
    }
    
    public Integer generarCodigo()throws Exception{
        strSQL="select Coalesce(Max(idMetodoPago),0)+1 as codigo from metodo_pago";
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
            } catch (Exception e) {
            throw new Exception("Error al generar el codigo para el metodo de pago--->"+e.getMessage());
        }
        return 0;
    }
    
    public ResultSet buscarMetodoPago(int cod)throws Exception{
        strSQL="select *from metodo_pago where idMetodoPago="+cod;
        try {
            return rs=objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al buscar el metodo de pago --->"+e.getMessage());
        }
    }
    
    public void registrarMetodo(int cod,String nombre)throws Exception{
        strSQL="insert into metodo_pago values("+cod+",'"+nombre+"')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar metodo de pago--->"+e.getMessage());
        }
    }
    public void eliminarMetodo_Pago(int cod) throws Exception{
        strSQL="delete from metodo_pago where idMetodoPago="+cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el metodo de pago"+e.getMessage());
        }   
    }
    
    public void modificarMetodo(int cod,String nom) throws Exception{
        strSQL="update metodo_pago set nombre='"+ nom +"' where idMetodoPago="+cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el metodo de pago--->"+e.getMessage());
        }
    }
    
    public void obtenerIdMetodoPago(String nom)throws Exception{
        strSQL="select idMetodoPago from metodo_pago where nombre='"+nom+"'";
        try {
            
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de metodo de pago-->"+e.getMessage());
        }
    }
    
    
    
}
