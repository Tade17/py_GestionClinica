/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.*;

/**
 *
 * @author grupo_02
 */
public class clsEnfermedades {

    clsJDBC objConectar = new clsJDBC();
    String str;
    ResultSet rs = null;

    public ResultSet listarEnfermedades() throws Exception {
        str = "select * from enfermedad";
        try {
            return objConectar.consultarBD(str);
        } catch (Exception e) {
            throw new Exception("Error al listar enfermedades--->" + e.getMessage());
        }
    }

    public Integer generarCodigo() throws Exception {
        str = "select coalesce(max(idEnfermedad),0)+1 as codigo from enfermedad";
        try {
            rs = objConectar.consultarBD(str);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo para la enfermedad--->" + e.getMessage());
        }
        return 0;
    }

    public Integer obtenerIdsEnfermedadesSeleccionadas(String nombre) throws Exception {
        str = "select idEnfermedad from enfermedad where nombre='"+nombre+"'";
        try {
            rs = objConectar.consultarBD(str);
            while (rs.next()) {
                return rs.getInt("idEnfermedad");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener id de la enfermedad--->" + e.getMessage());
        }
        return 0;
    }

    public ResultSet buscarEnfermedad(int id) throws Exception {
        str = "Select * from enfermedad where idEnfermedad=" + id;
        try {
            rs = objConectar.consultarBD(str);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar enfermedad--->" + e.getMessage());
        }
    }

    public Integer obtenerIdEnfermedadXNombre(String nom) throws Exception {
        str = "select idEnfermedad from enfermedad where nombre='" + nom + "'";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return rs.getInt("idEnfermedad");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de la enfermedad-->" + e.getMessage());
        }
        return 0;
    }

    public void registrarEnfermedad(int id, String nombre, String descripcion) throws Exception {
        str = "insert into enfermedad values(" + id + ",'" + nombre + "','" + descripcion + "')";
        try {
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error al insertar la enfermedad--->" + e.getMessage());
        }
    }

    public void modificarEnfermedad(int id, String nombre, String descripcion) throws Exception {
        str = "update enfermedad set nombre='" + nombre + "', descripcion='" + descripcion + "' where idEnfermedad=" + id;
        try {
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la enfermedad--->" + e.getMessage());
        }
    }

    public boolean tienePacientesAsociados(int idEnfermedad) throws Exception {
        str = "SELECT COUNT(*) AS total FROM paciente_enfermedad WHERE idEnfermedad = " + idEnfermedad;
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next() && rs.getInt("total") > 0) {
                return true; // 
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar asociaciones de la enfermedad--->" + e.getMessage());
        }
        return false;
    }

    public void eliminarEnfermedad(int idEnfermedad) throws Exception {
        if (tienePacientesAsociados(idEnfermedad)) {
            throw new Exception(" No se puede eliminar la enfermedad porque está asociada a uno o más pacientes.");
        }

        str = "DELETE FROM enfermedad WHERE idEnfermedad = " + idEnfermedad;
        try {
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la enfermedad--->" + e.getMessage());
        }
    }
}
