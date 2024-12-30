/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.ResultSet;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsEspecialidad {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarEspecialidades() throws Exception {
        strSQL = "select *from especialidad ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar especialidad---->" + e.getMessage());
        }
    }
    public ResultSet listarEspecialidadesMedico() throws Exception {
        strSQL = "select *from especialidad where estado =true ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar especialidad---->" + e.getMessage());
        }
    }

    public Integer generarCodigo() throws Exception {
        strSQL = "select COALESCE(Max(idEspecialidad),0)+1 as id from especialidad";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo para especialidad--->" + e.getMessage());
        }
        return 0;

    }

    public Integer obtenerIdsEspecialidadesSeleccionadas(String nombre) throws Exception {
        strSQL = "select idEspecialidad from especialidad where nombre='" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("idEspecialidad");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener id de la especialidad--->" + e.getMessage());
        }
        return 0;
    }

    public void registrarEspecialidad(int id, String nom, String des, boolean est) throws Exception {
        strSQL = "Insert into especialidad values (" + id + ",'" + nom + "','" + des + "'," + est + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar Especialidad--->" + e.getMessage());
        }
    }

    public ResultSet buscarEspecialidad(int id) throws Exception {
        strSQL = "Select * from especialidad where idEspecialidad=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar especialidad--->" + e.getMessage());
        }

    }

    public void eliminarEspecialidad(int id) throws Exception {
        if (especialidadAsignada(id)) {
            throw new Exception("No se puede eliminar la especialidad porque está asignada a uno o más médicos.");
        }
        strSQL = " delete from especialidad where idEspecialidad=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar especialidad--->" + e.getMessage());
        }
    }

    public void darBajaEspecialidad(int id) throws Exception {
        strSQL = " Update especialidad set estado= false where idEspecialidad=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja a la especialidad---> " + e.getMessage());
        }
    }

    public void modificarEspecialidad(int id, String nom, String des, boolean est) throws Exception {
        strSQL = "update especialidad set nombre='" + nom + "', descripcion='" + des + "', estado=" + est + "  where idEspecialidad=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la Especialidad---> " + e.getMessage());
        }
    }

    public Integer obtenerCodigoEspecialidad(String nom) throws Exception {
        strSQL = "select idEspecialidad from especialidad where nombre='" + nom + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idEspecialidad");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener codigo de la Especialidad--->" + e.getMessage());
        }
        return 0;
    }

    public boolean especialidadAsignada(int idEspecialidad) throws Exception {
        strSQL = "SELECT COUNT(*) AS total FROM MEDICO_ESPECIALIDAD WHERE idEspecialidad = " + idEspecialidad;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si la especialidad está asignada--->" + e.getMessage());
        }
        return false;
    }

    
}
