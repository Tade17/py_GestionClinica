/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.*;
import java.util.Calendar;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsTurnoMedico {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarTurnosMedicos() throws Exception {
        strSQL = "select *  from turno_medico t inner join medico m on m.idMedico=t.idMedico where t.estado=true";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar turnos medicos---->" + e.getMessage());
        }
    }

    public Integer obtenerIdMedico(String nom) throws Exception {
        strSQL = "Select idMedico from medico where nombres='" + nom + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idMedico");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al buscar la medico --->" + e.getMessage());
        }
        return 0;
    }

    public Integer generarCodigo() throws Exception {
        strSQL = "select COALESCE(Max(idTurno),0)+1 as id from turno_medico";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo para el turno medico --->" + e.getMessage());
        }
        return 0;

    }

    public void registrarTurnoMedico(int id, Date fecha, java.sql.Time horaIn, java.sql.Time horaFin, boolean esta, int cupos, Integer idMedico) throws Exception {

        if (!validarFechaNoPasada(fecha)) {
            throw new Exception("La fecha del turno no puede ser pasada.");
        }

        if (horaIn.after(horaFin)) {
            throw new Exception("La hora de inicio no puede ser posterior a la hora de fin.");
        }

        strSQL = "INSERT INTO turno_medico (idTurno, fecha, hora_inicio, hora_fin, estado, cupos, idMedico) "
                + "VALUES (" + id + ", '" + fecha + "', '" + horaIn + "', '" + horaFin + "', " + esta + ", " + cupos + ", " + idMedico + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el turno médico --> " + e.getMessage());
        }
    }

    public boolean verificarDisponibilidadTurno(int idTurno) throws Exception {
        strSQL = "SELECT COUNT(*) AS citas_asignadas, tm.cupos "
                + "FROM cita c "
                + "JOIN turno_medico tm ON c.idTurno = tm.idTurno "
                + "WHERE tm.idTurno = " + idTurno + " "
                + "GROUP BY tm.cupos";

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int citasAsignadas = rs.getInt("citas_asignadas");
                int cupos = rs.getInt("cupos");
                return citasAsignadas < cupos; // Retorna true si hay espacio
            }
            return true; // Si no hay citas asignadas, el turno está disponible
        } catch (Exception e) {
            throw new Exception("Error al verificar disponibilidad del turno --> " + e.getMessage());
        }
    }

    public ResultSet buscarTurnoMedico(int id) throws Exception {
        strSQL = "Select tr.fecha, tr.hora_inicio, tr.hora_fin, tr.estado,tr.cupos,m.dni, m.idMedico,m.nombres,m.apellidoPaterno,m.apellidoMaterno  "
                + "from turno_medico tr "
                + "inner join medico m on m.idMedico=tr.idMedico "
                + "where idTurno=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar turno del medico --->" + e.getMessage());
        }

    }

    public void darBajaTurno(int id) throws Exception {
        if (tieneCitasAsociadas(id)) {
            throw new Exception("El turno tiene citas asociadas y no se puede dar de baja.");
        }
        strSQL = "update turno_medico set estado= false where idTurno=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al turno médico--->" + e.getMessage());
        }
    }

    public boolean existeTurno(int id) throws Exception {
        strSQL = "SELECT COUNT(*) AS total FROM turno_medico WHERE idTurno = " + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (Exception e) {
            throw new Exception("Error al verificar existencia del turno --> " + e.getMessage());
        }
    }

    public void eliminarTurnoMedico(int id) throws Exception {

        if (!existeTurno(id)) {
            throw new Exception("El turno no existe y no se puede eliminar.");
        }

        if (tieneCitasAsociadas(id)) {
            throw new Exception("El turno tiene citas asociadas y no se puede eliminar.");
        }

        strSQL = "DELETE FROM turno_medico WHERE idTurno = " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar turno médico --> " + e.getMessage());
        }
    }

    public void modificarTurnoMedico(int id, Date fecha, java.sql.Time horaIn, java.sql.Time horaFin, boolean esta, int cupos, Integer idMedico) throws Exception {
        if (!validarFechaNoPasada(fecha)) {
            throw new Exception("La fecha del turno no puede ser pasada.");
        }
        if (tieneCitasAsociadas(id)) {
            throw new Exception("El turno tiene citas asociadas y no se puede modificar.");
        }
        if (cupos <= 0) {
            throw new Exception("El número de cupos debe ser mayor a cero.");
        }
        strSQL = "update turno_medico "
                + "set fecha='" + fecha + "', hora_inicio='" + horaIn + "',hora_fin='" + horaFin + "', estado=" + esta + ", cupos= " + cupos
                + ", idMedico=" + idMedico + " where idTurno=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el turno médico --> " + e.getMessage());
        }
    }

    public ResultSet medicosDisponibles() throws Exception {
        strSQL = "select nombres,apellidoPaterno from medico";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error interno al dar listar medicos Disponibles--->" + e.getMessage());

        }
    }

    public ResultSet listarEspecialidadesxMédico(int idMedico) throws Exception {
        strSQL = "SELECT e.nombre AS especialidad "
                + "FROM especialidad e "
                + "INNER JOIN medico_especialidad me ON e.idEspecialidad = me.idEspecialidad "
                + "WHERE me.idMedico = " + idMedico;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar las especialidades del médico: " + e.getMessage());
        }
    }

    public ResultSet listarTurnosPorMedico(int idMedico) throws Exception {
        strSQL = "SELECT t.idTurno, t.fecha, t.hora_inicio, t.hora_fin "
                + "FROM turno_medico t "
                + "WHERE t.idMedico = " + idMedico + " "
                + "AND t.fecha >= CURRENT_DATE " 
                + "ORDER BY t.fecha, t.hora_inicio;";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar turnos del médico --> " + e.getMessage());
        }
    }

    public ResultSet listarTurnosPorMedicoDesdeHoy(int idMedico) throws Exception {
        strSQL = "SELECT t.idTurno, t.fecha, t.hora_inicio, t.hora_fin "
                + "FROM turno_medico t "
                + "WHERE t.idMedico = " + idMedico + " "
                + "AND t.fecha >= CURRENT_DATE " // Solo turnos de hoy en adelante
                + "ORDER BY t.fecha, t.hora_inicio;";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar turnos del médico desde hoy --> " + e.getMessage());
        }
    }

    public ResultSet obtenerTurnoDeCita(int idCita) throws Exception {
        strSQL = "SELECT t.idTurno, t.fecha, t.hora_inicio, t.hora_fin "
                + "FROM turno_medico t "
                + "INNER JOIN cita c ON t.idTurno = c.idTurno "
                + "WHERE c.idCita = " + idCita + ";";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al obtener el turno de la cita --> " + e.getMessage());
        }
    }

    public boolean validarTurnoDisponible(int idTurno) throws Exception {
        strSQL = "SELECT estado FROM TURNO_MEDICO WHERE idTurno = " + idTurno + ";";
        try {
            rs = objConectar.consultarBD(strSQL);

            if (rs.next()) {
                return rs.getBoolean("estado");
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error al validar turno disponible --> " + e.getMessage());
        }
    }

    public ResultSet buscarTurnoPorId(int idTurno) throws Exception {
        strSQL = "SELECT idTurno, fecha, hora_inicio, hora_fin, estado "
                + "FROM TURNO_MEDICO WHERE idTurno = " + idTurno + ";";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al buscar el turno --> " + e.getMessage());
        }
    }

    public Integer obtenerIdMedicoXDNI(String dni) throws Exception {
        strSQL = "SELECT idMedico, nombres, apellidoPaterno, apellidoMaterno FROM medico WHERE dni = '" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idMedico");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el codigo del medico-->" + e.getMessage());
        }
        return 0;
    }

    public ResultSet obtenerIdMedicoDNI(String dni) throws Exception {
        strSQL = "SELECT idMedico, nombres, apellidoPaterno, apellidoMaterno "
                + "FROM medico "
                + "WHERE dni = '" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener el codigo del medico-->" + e.getMessage());
        }
    }

    public boolean verificarCruceTurnos(int idMedico, java.sql.Date fecha, java.sql.Time horaInicio, java.sql.Time horaFin) throws Exception {
        strSQL = "SELECT COUNT(*) AS total "
                + "FROM turno_medico "
                + "WHERE idMedico = " + idMedico + " "
                + "AND fecha = '" + fecha + "' "
                + "AND (('" + horaInicio + "' BETWEEN hora_inicio AND hora_fin) "
                + "OR ('" + horaFin + "' BETWEEN hora_inicio AND hora_fin) "
                + "OR (hora_inicio BETWEEN '" + horaInicio + "' AND '" + horaFin + "'))";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar solapamiento de turnos --> " + e.getMessage());
        }
        return false;
    }

    public boolean tieneCitasAsociadas(int idTurno) throws Exception {
        strSQL = "SELECT COUNT(*) AS total "
                + "FROM cita "
                + "WHERE idTurno = " + idTurno + ";";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si el turno tiene citas asociadas --> " + e.getMessage());
        }
        return false;
    }

    public boolean validarFechaNoPasada(Date fecha) {
        try {
            Calendar calHoy = Calendar.getInstance();
            calHoy.set(Calendar.HOUR_OF_DAY, 0);
            calHoy.set(Calendar.MINUTE, 0);
            calHoy.set(Calendar.SECOND, 0);
            calHoy.set(Calendar.MILLISECOND, 0);
            java.util.Date hoy = calHoy.getTime();

            return !fecha.before(hoy);
        } catch (Exception e) {
            return false;
        }
    }

}
