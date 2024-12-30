/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.*;
import java.util.List;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsPaciente {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarPacientes() throws Exception {
        strSQL = "select * from Paciente order by 1";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los pacientes -->" + e.getMessage());
        }
    }
     public ResultSet obtenerPacientePorDNI(String dni) throws Exception {
        strSQL = "select  nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno AS nombre_completo , telefono "
                + "from Paciente "
                + "where dni='"+dni+"'";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener paciente -->" + e.getMessage());
        }
    }

    public Integer obtenerCodigoPaciente(String nom) throws Exception {
        strSQL = "select idPaciente from Paciente where nombres='" + nom + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idPaciente");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el codigo del paciente-->" + e.getMessage());
        }
        return 0;
    }

    public Integer obtenerCodigoPacienteXDNI(String dni) throws Exception {
        strSQL = "select idPaciente from Paciente where dni='" + dni + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idPaciente");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el codigo del paciente-->" + e.getMessage());
        }
        return 0;
    }
    public String obtenerDNIPacientePorId(int id) throws Exception {
        strSQL = "select dni from Paciente where idPaciente=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("dni");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el dni por id del paciente-->" + e.getMessage());
        }
        return "";
    }
     public String obtenerNombrePacientePorId(int id) throws Exception {
        strSQL = "select nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno AS nombre_completo from Paciente where idPaciente=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("nombre_completo");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el dni por nombre-->" + e.getMessage());
        }
        return "";
    }
     
    

    public Integer generarCodigoPaciente() throws Exception {
        strSQL = "select coalesce(max(idPaciente),0)+1 as codigo from Paciente";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de paciente -->" + e.getMessage());
        }
        return 0;
    }

    public Integer generarCodigoUsuario() throws Exception {
        strSQL = "select coalesce(max(idUsuario),0)+1 as codigo from usuario";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de usuario para paciente -->" + e.getMessage());
        }
        return 0;
    }

    public void registrar(Integer cod, String nom, String ape_pa, String ape_ma, String dni, float peso, float altura,
            Date fe_nac, String tel, String tip_sang, Boolean est) throws Exception {
        strSQL = "INSERT INTO Paciente (idPaciente, nombres, apellidoPaterno, apellidoMaterno, dni, peso, altura, fecha_nacimiento, telefono, tipoSangre, estado) "
                + "VALUES (" + cod + ", '" + nom + "', '" + ape_pa + "', '" + ape_ma + "', '" + dni + "', " + peso + ", " + altura + ", '" + fe_nac + "', '" + tel + "', '"
                + tip_sang + "', " + est + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar Paciente -->" + e.getMessage());
        }
    }

    public void registrarEnfermedadesPaciente(int idPaciente, List<Integer> enfermedades) throws Exception {
        try {
            objConectar.conectar(); 
            for (Integer idEnfermedad : enfermedades) {
                strSQL = "INSERT INTO PACIENTE_ENFERMEDAD (idPaciente, idEnfermedad) VALUES (" + idPaciente + ", " + idEnfermedad + ")";
                objConectar.ejecutarBD(strSQL);
            }
        } catch (Exception e) {
            throw new Exception("Error al registrar enfermedades del paciente: " + e.getMessage());
        } finally {
            objConectar.desconectar(); 
        }
    }

    public ResultSet buscarPaciente(Integer cod) throws Exception {
        strSQL = "select * from Paciente where idPaciente=" + cod;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar Paciente -->" + e.getMessage());
        }
    }

    public void darBajaPaciente(Integer cod) throws Exception {
        strSQL = "update paciente set estado= false where idPaciente=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja al Paciente -->" + e.getMessage());
        }
    }

    public void modificar(Integer cod, String nom, String ape_pa, String ape_ma, String dni, float peso, float altura,
            java.sql.Date fe_nac, String tel, String tip_sang, Boolean est, List<Integer> enfermedadesSeleccionadas) throws Exception {

        strSQL = "UPDATE Paciente SET nombres='" + nom + "', apellidoPaterno='" + ape_pa + "', apellidoMaterno='" + ape_ma
                + "', dni='" + dni + "', peso=" + peso + ", altura=" + altura + ", fecha_nacimiento='" + fe_nac + "', telefono='" + tel
                + "', tipoSangre='" + tip_sang + "', estado=" + est + " WHERE idPaciente=" + cod;

        try {
            objConectar.ejecutarBD(strSQL);
            eliminarEnfermedadesPaciente(cod);
            registrarEnfermedadesPaciente(cod, enfermedadesSeleccionadas);

        } catch (Exception e) {
            throw new Exception("Error al modificar Paciente -->" + e.getMessage());
        }
    }

    public boolean tienePagosAsociados(int idPaciente) throws Exception {
        strSQL = "SELECT COUNT(*) AS total FROM PAGO WHERE idPaciente = " + idPaciente + " and estado=true";
        rs = objConectar.consultarBD(strSQL);
        if (rs.next() && rs.getInt("total") > 0) {
            return true;
        }
        return false;
    }

    public boolean tieneCitasAsociadas(int idPaciente) throws Exception {
        strSQL = "SELECT COUNT(*) AS total FROM CITA WHERE idPaciente = " + idPaciente;
        rs = objConectar.consultarBD(strSQL);
        if (rs.next() && rs.getInt("total") > 0) {
            return true;
        }
        return false;
    }

    public void eliminarEnfermedadesPaciente(int idPaciente) throws Exception {
        strSQL = "DELETE FROM PACIENTE_ENFERMEDAD WHERE idPaciente = " + idPaciente;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar enfermedades asociadas del paciente -->" + e.getMessage());
        }
    }

    public void eliminarPaciente(Integer cod) throws Exception {
        if (tienePagosAsociados(cod)) {
            throw new Exception("No se puede eliminar al paciente, debido a que tiene pagos asociados ");
        }
        if (tieneCitasAsociadas(cod)) {
            throw new Exception("No se puede eliminar al paciente, debido a que tiene citas asociados");
        }
        eliminarEnfermedadesPaciente(cod);

        strSQL = "DELETE FROM Paciente WHERE idPaciente = " + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar Paciente -->" + e.getMessage());
        }
    }

    public ResultSet listaEnfermedadesxPaciente(int id) throws Exception {
        strSQL = "select e.idEnfermedad,e.nombre"
                + " from enfermedad e "
                + "inner join paciente_enfermedad pe on pe.idEnfermedad=e.idEnfermedad "
                + "where idPaciente =" + id;
        try {
            return objConectar.consultarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al listar las enfermedades del paciente" + e.getMessage());
        }
    }

    public ResultSet buscarPacientePorDNI(String dni) throws Exception {
        strSQL = "SELECT idPaciente, nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno AS nombre_completo "
                + "FROM paciente "
                + "WHERE dni = '" + dni + "';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar paciente por DNI --> " + e.getMessage());
        }
    }

    public ResultSet buscarPacientePorDNI2(String dni) throws Exception {
        strSQL = "SELECT dni, nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno AS nombre_completo "
                + "FROM paciente "
                + "WHERE dni = '" + dni + "';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar paciente por DNI --> " + e.getMessage());
        }
    }

    public ResultSet buscarPacientePorDNIPago(String dni) throws Exception {
        strSQL = "SELECT nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno AS nombre_completo, telefono "
                + "FROM paciente "
                + "WHERE dni = '" + dni + "';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar paciente por DNI --> " + e.getMessage());
        }
    }
}
