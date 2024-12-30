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
public class clsCita {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;//establecer conexion
    Statement sent;

    public ResultSet listarCitas() throws Exception {
        strSQL = "SELECT C.idCita, TM.fecha, TM.hora_inicio, C.estado, C.motivo, "
                + "M.nombres || ' ' || M.apellidoPaterno || ' ' || M.apellidoMaterno AS nombre_medico, "
                + "P.nombres || ' ' || P.apellidoPaterno || ' ' || P.apellidoMaterno AS nombre_paciente "
                + "FROM cita C "
                + "INNER JOIN turno_medico TM ON C.idTurno = TM.idTurno "
                + "INNER JOIN medico M ON C.idMedico = M.idMedico "
                + "INNER JOIN paciente P ON C.idPaciente = P.idPaciente "
                + "ORDER BY C.idCita";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas --> " + e.getMessage());
        }
    }
    public ResultSet listarCitasPendientes() throws Exception {
        strSQL = "SELECT C.idCita, TM.fecha, TM.hora_inicio, C.estado, C.motivo, "
                + "M.nombres || ' ' || M.apellidoPaterno || ' ' || M.apellidoMaterno AS nombre_medico, "
                + "P.nombres || ' ' || P.apellidoPaterno || ' ' || P.apellidoMaterno AS nombre_paciente "
                + "FROM cita C "
                + "INNER JOIN turno_medico TM ON C.idTurno = TM.idTurno "
                + "INNER JOIN medico M ON C.idMedico = M.idMedico "
                + "INNER JOIN paciente P ON C.idPaciente = P.idPaciente "
                + "where c.estado=false and  TM.fecha=current_date "
                + "ORDER BY C.idCita ";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las citas --> " + e.getMessage());
        }
    }

    public Integer generarCodigoCita() throws Exception {
        strSQL = "select COALESCE(max(idCita),0)+ 1 as codigo from Cita";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el código para la cita -->" + e.getMessage());
        }
        return 0;
    }

    public void registrarCita(int idCita,  String motivo, int idMedico, int idPaciente, int idTurno) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            strSQL = "INSERT INTO cita (idCita, estado, motivo, idMedico, idPaciente, idTurno) "
                    + "VALUES (" + idCita + ",false, '" + motivo + "', " + idMedico + ", " + idPaciente + ", " + idTurno + ");";
            sent = con.createStatement();
            sent.executeUpdate(strSQL);

            con.commit();
        } catch (Exception ex) {
            con.rollback(); 
            throw new Exception("Error al registrar la cita --> " + ex.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public ResultSet buscarCitaa(Integer cod) throws Exception {
        strSQL = "select c.idCita as idCita,c.fecha as fecha, c.hora as hora,c.estado as estado , c.motivo as motivo, m.nombres as nombreMedico,p.nombres as nombrePaciente  from cita c "
                + " inner join paciente p on p.idPaciente=c.idPaciente inner join Medico m on m.idMedico=c.idMedico where idCita=" + cod;

        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el código para la cita -->" + e.getMessage());
        }
    }

    public void darBajaCita(Integer cod) throws Exception {
        strSQL = "update cita set estado=false where idCita=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja una cita" + e.getMessage());
        }
    }

    public void validarEstadoCita(Integer cod) throws Exception {
        strSQL = "SELECT estado FROM CITA WHERE idCita = " + cod + ";";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                boolean estadoActual = rs.getBoolean("estado");
                if (estadoActual) {
                    throw new Exception("No se puede modificar una cita que ya ha sido atendida.");
                }
            } else {
                throw new Exception("La cita con ID " + cod + " no existe.");
            }
        } catch (Exception e) {
            throw new Exception("Error al validar el estado de la cita --> " + e.getMessage());
        }
    }

    public void modificarCita(Integer cod, Integer idTurno, Boolean est, String motivo, Integer id_m, Integer id_pa) throws Exception {
        try {
            strSQL = "UPDATE CITA SET idTurno = " + idTurno + ", estado = " + est
                    + ", motivo = '" + motivo + "', idMedico = " + id_m + ", idPaciente = " + id_pa
                    + " WHERE idCita = " + cod + ";";
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la cita --> " + e.getMessage());
        }
    }

    public Integer obtenerIdMedicoxDni(String dni) throws Exception {
        strSQL = "Select idMedico from medico where dni ='" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idMedico");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al obtener el id del médico --->" + e.getMessage());
        }
        return 0;
    }

    public ResultSet paciente() throws Exception {
        strSQL = "select nombres from paciente";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error interno al dar listar pacientes--->" + e.getMessage());

        }
    }

    public Integer obtenerIdPacienteXDNI(String dni) throws Exception {
        strSQL = "Select idPaciente from paciente where dni ='" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idPaciente");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al obtener el id del paciente--->" + e.getMessage());
        }
        return 0;
    }

    public boolean esCitaAtendida(int idCita) throws Exception {
        strSQL = "select estado from cita where idCita=" + idCita;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getBoolean("estado"); 
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar el estado de la cita --> " + e.getMessage());
        }
        return false; 
    }

    public boolean tienePagoAsociado(int idCita) throws Exception {
        strSQL = "select estado  from pago p inner join detalle_pago dp on dp.idPago=p.idPago where idCita = " + idCita;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si la cita tiene pagos asociados --> " + e.getMessage());
        }
        return false;
    }

    public void eliminarCita(Integer cod) throws Exception {
        if (esCitaAtendida(cod)) {
            throw new Exception("Ya ha sido atendida");
        }
        if (tienePagoAsociado(cod)) {
            throw new Exception("Esta relacionada a un pago");
        }
        strSQL = "delete from Cita where idCita=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la cita -->" + e.getMessage());

        }
    }

    public ResultSet listarCitasPorMedicoYFecha(Integer idMedico, String fechaInicio, String fechaFin) throws Exception {
        strSQL = "SELECT * FROM cita WHERE idMedico=" + idMedico
                + " AND fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar citas por médico y fechas --> " + e.getMessage());
        }
    }

    public boolean verificarDisponibilidadMedico(Integer idMedico, String fecha, String horaInicio, String horaFin) throws Exception {
        strSQL = "SELECT COUNT(*) as total FROM cita WHERE idMedico=" + idMedico
                + " AND fecha='" + fecha + "' AND hora BETWEEN '" + horaInicio + "' AND '" + horaFin + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") == 0; 
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar disponibilidad del médico --> " + e.getMessage());
        }
        return false;
    }

    public void actualizarEstadoCita(Integer idCita, Boolean estado) throws Exception {
        strSQL = "UPDATE cita SET estado=" + estado + " WHERE idCita=" + idCita;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el estado de la cita --> " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesPorMedico(Integer idMedico) throws Exception {
        strSQL = "SELECT * FROM cita WHERE idMedico=" + idMedico + " AND estado=false";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar citas pendientes por médico --> " + e.getMessage());
        }
    }

    public boolean existeCita(Integer idMedico, String fecha, String hora) throws Exception {
        strSQL = "SELECT COUNT(*) as total FROM cita WHERE idMedico=" + idMedico
                + " AND fecha='" + fecha + "' AND hora='" + hora + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0; 
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si ya existe la cita --> " + e.getMessage());
        }
        return false;
    }

    public void cancelarCita(Integer idCita) throws Exception {
        strSQL = "UPDATE cita SET estado=false WHERE idCita=" + idCita;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al cancelar la cita --> " + e.getMessage());
        }
    }

    public ResultSet buscarCita(int idCita) throws Exception {
        strSQL = "SELECT c.idCita, tm.fecha, tm.hora_inicio, tm.hora_fin, c.motivo, "
                + "p.dni AS dniPaciente, "
                + "p.nombres || ' ' || p.apellidoPaterno || ' ' || p.apellidoMaterno AS nombrePaciente, "
                + "m.dni AS dniMedico, "
                + "m.nombres || ' ' || m.apellidoPaterno || ' ' || m.apellidoMaterno AS nombreMedico, "
                + "STRING_AGG(e.nombre, ', ') AS especialidades, " 
                + "c.estado, tm.idTurno "
                + "FROM cita c "
                + "INNER JOIN turno_medico tm ON c.idTurno = tm.idTurno "
                + "INNER JOIN paciente p ON c.idPaciente = p.idPaciente "
                + "INNER JOIN medico m ON tm.idMedico = m.idMedico "
                + "INNER JOIN medico_especialidad me ON m.idMedico = me.idMedico "
                + "INNER JOIN especialidad e ON me.idEspecialidad = e.idEspecialidad "
                + "WHERE c.idCita = " + idCita + " and c.estado=false  "
                + "GROUP BY c.idCita, tm.fecha, tm.hora_inicio, tm.hora_fin, c.motivo, "
                + "p.dni, p.nombres, p.apellidoPaterno, p.apellidoMaterno, "
                + "m.dni, m.nombres, m.apellidoPaterno, m.apellidoMaterno, "
                + "c.estado, tm.idTurno";

        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al buscar la cita --> " + e.getMessage());
        }
    }

    public ResultSet listarCitasMedico(int idMedico) throws Exception {
        strSQL = "SELECT C.idCita, C.estado, C.motivo, T.fecha, T.hora_inicio, T.hora_fin, "
                + "P.nombres || ' ' || P.apellidoPaterno || ' ' || P.apellidoMaterno AS nombrePaciente"
                + "FROM CITA C "
                + "INNER JOIN PACIENTE P ON C.idPaciente = P.idPaciente "
                + "INNER JOIN TURNO_MEDICO T ON C.idTurno = T.idTurno "
                + "WHERE C.idMedico = " + idMedico + " "
                + "ORDER BY C.estado, T.fecha, T.hora_inicio;";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar las citas del médico --> " + e.getMessage());
        }
    }

    public ResultSet listarCitasPendientesPorMedico(int idMedico) throws Exception {
        strSQL = "SELECT c.idCita, tm.fecha, tm.hora_inicio, tm.hora_fin, c.motivo, "
                + "p.nombres || ' ' || p.apellidoPaterno || ' ' || p.apellidoMaterno AS nombrePaciente, c.estado "
                + "FROM cita c "
                + "INNER JOIN turno_medico tm ON c.idTurno = tm.idTurno "
                + "INNER JOIN paciente p ON c.idPaciente = p.idPaciente "
                + "WHERE c.idMedico =" + idMedico + " AND c.estado = false and  tm.fecha=current_date "
                + "ORDER BY tm.fecha, tm.hora_inicio";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar citas pendientes --> " + e.getMessage());
        }
    }

    public ResultSet listarCitasAtendidasPorMedico(int idMedico) throws Exception {
        strSQL = "SELECT c.idCita, tm.fecha, tm.hora_inicio, tm.hora_fin, c.motivo, "
                + "p.nombres || ' ' || p.apellidoPaterno || ' ' || p.apellidoMaterno AS nombrePaciente, c.estado "
                + "FROM cita c "
                + "INNER JOIN turno_medico tm ON c.idTurno = tm.idTurno "
                + "INNER JOIN paciente p ON c.idPaciente = p.idPaciente "
                + "WHERE c.idMedico =" + idMedico + " AND c.estado = true "
                + // Solo atendidas
                "ORDER BY tm.fecha, tm.hora_inicio";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar citas atendidas --> " + e.getMessage());
        }
    }

    public boolean isCitaAtendida(int idCita) throws Exception {
        boolean atendida = false;
        strSQL = "SELECT estado FROM cita WHERE idCita = " + idCita;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                atendida = rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar el estado de la cita: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            objConectar.desconectar();
        }
        return atendida;
    }

    public boolean verificarCitaMedico(int idCita, int idMedicoLogueado) throws Exception {
        strSQL = "SELECT idMedico FROM cita WHERE idCita = " + idCita;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int idMedicoAsignado = rs.getInt("idMedico");
                return idMedicoAsignado == idMedicoLogueado;
            } else {
                throw new Exception("Cita no encontrada.");
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la cita: " + e.getMessage());
        }
    }

    public ResultSet listarCitasPoridPaciente(int idPaciente) throws Exception {
            strSQL = "SELECT c.idcita, tm.fecha, c.motivo"
                + " FROM cita c "
                + "INNER JOIN Turno_Medico tm ON c.idTurno= tm.idTurno "
                + "WHERE c.idPaciente = " + idPaciente + " and c.estado=false";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar citas pendientes --> " + e.getMessage());
        }
    }

}
