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
public class clsDetalleCita {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs;
    Connection con = null;
    Statement sent;

    public ResultSet listarDetalleCita(int idDetalleCita) throws Exception {
        strSQL = "SELECT * FROM DETALLE_CITA where idDetalleCita=" + idDetalleCita;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar el detalle guardado --> " + e.getMessage());
        }
    }

    public ResultSet listarTodosDetallesCitas() throws Exception {
        strSQL = "SELECT DC.idDetalleCita, DC.descripcion, DC.diagnostico, DC.medicamento, DC.dosis, "
                + "C.idCita, C.motivo, C.estado "
                + "FROM DETALLE_CITA DC "
                + "INNER JOIN CITA C ON DC.idCita = C.idCita;";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar detalles de las citas --> " + e.getMessage());
        }
    }

    public Integer generarCodigoDetalleCita() throws Exception {
        strSQL = "Select coalesce(max(idDetalleCita),0)+1 as codigo from detalle_cita ";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo para el detalle de cita---->" + e.getMessage());
        }
        return 0;
    }

    public Integer obtenerIdDetalleCita(int idCita) throws Exception {
        strSQL = "select idDetalleCita from detalle_cita where idCita=" + idCita;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idDetalleCita");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id del detalle de la cita---->" + e.getMessage());
        }
        return 0;
    }

    public Integer obtenerIdCita(int id) throws Exception {
        strSQL = "Select idCita from cita where idCita =" + id + "";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idCita");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de la cita --->" + e.getMessage());
        }
        return 0;
    }

    public ResultSet buscarDetalleCita(int idCita) throws Exception {
        strSQL = "SELECT * FROM DETALLE_CITA WHERE idCita = " + idCita + ";";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al buscar el detalle de la cita --> " + e.getMessage());
        }
    }
    public void modificarDetalleCita(int idDetalleCita, String descripcion, String diagnostico, String medicamento, String dosis) throws Exception {
        strSQL = "UPDATE DETALLE_CITA SET descripcion = '" + descripcion + "', diagnostico = '" + diagnostico
                + "', medicamento = '" + medicamento + "', dosis = '" + dosis
                + "' WHERE idDetalleCita = " + idDetalleCita + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el detalle de la cita --> " + e.getMessage());
        }
    }

    public void eliminarDetalleCita(int idDetalleCita) throws Exception {
        strSQL = "DELETE FROM DETALLE_CITA WHERE idDetalleCita = " + idDetalleCita + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el detalle de la cita --> " + e.getMessage());
        }
    }

    public void registrarDetalleCita(int idDetalleCita, String descripcion, String diagnostico, String medicamento, String dosis, int idCita) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

           
            strSQL = "INSERT INTO detalle_cita (idDetalleCita, descripcion, diagnostico, medicamento, dosis, idCita) "
                    + "VALUES (" + idDetalleCita + ", '" + descripcion + "', '" + diagnostico + "', '" + medicamento + "', '" + dosis + "', " + idCita + ");";
            sent = con.createStatement();
            sent.executeUpdate(strSQL);

           
            strSQL = "UPDATE cita SET estado = true WHERE idCita = " + idCita + ";";
            sent.executeUpdate(strSQL);

            con.commit(); 
        } catch (Exception ex) {
            con.rollback();
            throw new Exception("Error al registrar detalles de la cita --> " + ex.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public boolean existeDetalleParaCita(int idCita) throws Exception {
        strSQL = "SELECT COUNT(*) AS total FROM DETALLE_CITA WHERE idCita = " + idCita + ";";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (Exception e) {
            throw new Exception("Error al verificar existencia de detalle para la cita --> " + e.getMessage());
        }
    }

    public void actualizarEstadoCita(int idCita, boolean estado) throws Exception {
        strSQL = "UPDATE CITA SET estado = " + estado + " WHERE idCita = " + idCita + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al actualizar estado de cita --> " + e.getMessage());
        }
    }

    public ResultSet obtenerDetallePorIdCita(int idCita) throws Exception {
         strSQL = "SELECT descripcion, diagnostico, medicamento, dosis FROM DETALLE_CITA WHERE idCita = " + idCita;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al obtener detalle de cita --> " + e.getMessage());
        }
    }

}
