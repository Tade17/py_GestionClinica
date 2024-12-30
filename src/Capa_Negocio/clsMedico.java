/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author grupo_02
 */
public class clsMedico {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarMedicos() throws Exception {
        strSQL = "Select * from medico order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar medicos--->" + e.getMessage());
        }
    }

    public ResultSet listarCitasMedicoDia() throws Exception {
        strSQL = "Select * from cita order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar medicos--->" + e.getMessage());
        }
    }

    public Integer obtenerIdEspecialidad(String nom) throws Exception {
        strSQL = "Select idEspecialidad from Especialidad where nombre ='" + nom + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idEspecialidad");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al buscar id de la especialidad --->" + e.getMessage());
        }
        return 0;
    }

    public Integer generarIdDoctor() throws Exception {
        strSQL = "Select coalesce(max(idMedico),0)+1 as id from medico";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al generar codigo del Médico--->" + e.getMessage());
        }
        return 0;
    }

    public int obtenerIdMedicoPorNombre(String nombreCompleto) throws Exception {
        strSQL = "SELECT idMedico FROM MEDICO WHERE CONCAT('Dr. ', nombres, ' ', apellidoPaterno) = '" + nombreCompleto + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idMedico");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al obtener Id Medico--->" + e.getMessage());
        }

        return -1;
    }

    public Integer obtenerIdMedicoXDNI(String dni) throws Exception {
        strSQL = "select idMedico from Medico where dni='" + dni + "'";
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

    public void registrarMedico(Integer id, String dni, String nombr, String apPatre, String apMater, Date nac, String correo, Boolean vig) throws Exception {
        strSQL = "insert into medico values (" + id + ", '" + dni + "', '" + nombr + "', '" + apPatre + "', '" + apMater + "','" + nac + "', '" + correo + "'," + vig + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al insertar un nuevo médico--->" + e.getMessage());
        }
    }

    public ResultSet buscarMedico(Integer cod) throws Exception {
        strSQL = "Select * from medico where idMedico=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Medico-->" + e.getMessage());
        }
    }

    public void registrarEspecialidadesMedico(int idMedico, List<Integer> especialidades) throws Exception {
        try {
            objConectar.conectar();
            for (Integer idEspecialidad : especialidades) {
                strSQL = "INSERT INTO MEDICO_ESPECIALIDAD (idMedico, idEspecialidad) VALUES (" + idMedico + ", " + idEspecialidad + ")";
                objConectar.ejecutarBD(strSQL);
            }
        } catch (Exception e) {
            throw new Exception("Error al registrar especialidades al medico --> " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public void eliminarMedico(Integer id) throws Exception {
        if (tieneCitasAsignadas(id)) {
            throw new Exception("no se puede eliminar al médico , ya que tiene citas asignadas");
        }
        if (tieneTurnosAsignado(id)) {
            throw new Exception("no se puede eliminar al médico , ya que tiene turnos asignados");
        }
        eliminarEspecialidadesMedico(id);
        strSQL = "delete from medico where idMedico= " + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error interno al eliminar medico--->" + e.getMessage());
        }
    }

    public void modificarMedico(Integer id, String dni, String nombr, String apPatre, String apMater, Date nac, String correo, Boolean vig) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha adecuado para SQL
        String fechaNacimiento = dateFormat.format(nac);
        strSQL = "update medico set dni='" + dni + "',nombres='" + nombr + "', apellidoPaterno='" + apPatre + "',apellidoMaterno='" + apMater + "', fecha_nacimiento='" + fechaNacimiento
                + "', correo='" + correo + "', vigencia=" + vig + " where idMedico=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error interno al modificar un medico--->" + e.getMessage());
        }
    }

    public void darBaja(Integer id) throws Exception {
        if (tieneCitasAsignadas(id)) {
            throw new Exception("Error al dar de baja medico ---> tiene citas asignadas");
        }
        if (tieneTurnosAsignado(id)) {
            throw new Exception("Error al dar de baja medico ---> tiene turnos asignados");
        }
        strSQL = "update medico set vigencia=false where idMedico=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error interno al dar de baja a un medico--->" + e.getMessage());
        }
    }

    public void eliminarEspecialidadesMedico(int idMedico) throws Exception {
        strSQL = "DELETE FROM medico_especialidad WHERE idMedico = " + idMedico;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar especialidades asociadas al médico -->" + e.getMessage());
        }
    }

    public ResultSet especialidades() throws Exception {
        strSQL = "select nombre from especialidad";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error interno al dar listar especialidades--->" + e.getMessage());

        }
    }

    // verificando que no tenga citas asignadas
    public boolean tieneCitasAsignadas(int idMedico) throws Exception {
        strSQL = "SELECT COUNT(*) AS cuenta FROM CITA WHERE idMedico = " + idMedico;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("cuenta") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si el médico tiene citas asignadas --> " + e.getMessage());
        }
        return false;
    }

    //verificando que no tenga asignado turnos medicos
    public boolean tieneTurnosAsignado(int id) throws Exception {
        strSQL = "Select count(*) as cuenta from turno_medico where idMedico=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("cuenta") > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar turnos del medico" + e.getMessage());
        }
        return false;
    }

    public ResultSet listarEspecialidadesxMédico(int idMedico) throws Exception {
        strSQL = "SELECT e.idEspecialidad,e.nombre "
                + "FROM especialidad e "
                + "INNER JOIN medico_especialidad me ON e.idEspecialidad = me.idEspecialidad "
                + "WHERE me.idMedico = " + idMedico;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar las especialidades del médico: " + e.getMessage());
        }
    }

    public ResultSet buscarMedicoPorDNI(String dni) throws Exception {
        strSQL = "SELECT m.idMedico, m.dni, "
                + "m.nombres || ' ' || m.apellidoPaterno || ' ' || m.apellidoMaterno AS nombre_completo, "
                + "string_agg(e.nombre, ', ') AS especialidades "
                + "FROM medico m "
                + "INNER JOIN medico_especialidad me ON m.idMedico = me.idMedico "
                + "INNER JOIN especialidad e ON me.idEspecialidad = e.idEspecialidad "
                + "WHERE m.dni = '" + dni + "' "
                + "GROUP BY m.idMedico, m.dni, m.nombres, m.apellidoPaterno, m.apellidoMaterno;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar médico por DNI --> " + e.getMessage());
        }
    }

    public ResultSet filtrarMedicosPorEspecialidadYFecha(String especialidad, java.sql.Date fecha) throws Exception {
        strSQL = "SELECT m.dni, "
                + "CONCAT(m.nombres, ' ', m.apellidoPaterno, ' ', m.apellidoMaterno) AS nombreCompleto, "
                + "tm.fecha, "
                + "tm.hora_inicio, "
                + "tm.hora_fin, "
                + "tm.cupos "
                + "FROM medico m "
                + "INNER JOIN medico_especialidad me ON m.idMedico = me.idMedico "
                + "INNER JOIN especialidad e ON me.idEspecialidad = e.idEspecialidad "
                + "INNER JOIN turno_medico tm ON m.idMedico = tm.idMedico "
                + "WHERE e.nombre = '" + especialidad + "' AND tm.fecha = '" + fecha + "' "
                + "ORDER BY tm.fecha ASC";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al filtrar médicos por especialidad y fecha: " + e.getMessage());
        }
    }

    public ResultSet filtrarMedicosPorEspecialidad(String especialidad) throws Exception {
        strSQL = "SELECT "
                + "m.idMedico,m.dni ,"
                + "CONCAT(m.nombres, ' ', m.apellidoPaterno, ' ', m.apellidoMaterno) AS nombreCompleto, "
                + "m.fecha_nacimiento , m.vigencia  "
                + "FROM medico m "
                + "INNER JOIN medico_especialidad me ON m.idMedico = me.idMedico "
                + "INNER JOIN especialidad e ON me.idEspecialidad = e.idEspecialidad "
                + "WHERE e.nombre = '" + especialidad + "' "
                + "ORDER BY m.idMedico";
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al filtrar médicos por especialidad: " + e.getMessage());
        }
    }

}
