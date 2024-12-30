/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsAdministradores {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarAdministradores() throws Exception {
        strSQL = "select * from administrador order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los administradores -->" + e.getMessage());
        }
    }

    public Integer generarCodigoAdministrador() throws Exception {
        strSQL = "select coalesce(max(idAdministrador),0)+1 as codigo from administrador";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de administrador -->" + e.getMessage());
        }
        return 0;
    }

    public void insertarAdministrador(int id, String nombres, String apePa, String apeMa, Date fecha_nac, String telefono, String dni, boolean vig) throws Exception {
        strSQL = "insert into administrador (idAdministrador, nombres, apellidoPaterno, apellidoMaterno, fecha_nacimiento, telefono, dni,vigencia) values"
                + " (" + id + ",'" + nombres + "', '" + apePa + "','" + apeMa + "','" + fecha_nac + "',' " + telefono + "','" + dni + "', " + vig + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar administrador -->" + e.getMessage());
        }
    }

    public void modificarAdmnistrador(int id, String nombres, String apePa, String apeMa, Date fecha_nac, String telefono, String dni, boolean vig) throws Exception {
        strSQL = "update administrador set nombres='" + nombres + "',  apellidoPaterno='" + apePa + "',  apellidoMaterno='" + apeMa + "',  fecha_nacimiento='" + fecha_nac
                + "',  telefono='" + telefono + "',  vigencia=" + vig + " where idAdministrador=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar administrador -->" + e.getMessage());
        }
    }

    public void eliminarAdministrador(int id) throws Exception {
        strSQL = "delete from administrador where idAdministrador=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar administrador -->" + e.getMessage());
        }
    }

    public void darBajaAdministrador(int id) throws Exception {
        strSQL = "update administrador set vigencia=false where idAdministrador=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al administrador -->" + e.getMessage());
        }

    }

    public ResultSet buscarAdministrador(int id) throws Exception {
        strSQL = "select idAdministrador,nombres,apellidoPaterno,apellidoMaterno,fecha_nacimiento,telefono,dni,vigencia"
                + " from administrador "
                + "where idAdministrador=" + id;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al buscar al administrador -->" + e.getMessage());
        }
    }
}
