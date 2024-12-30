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
public class clsUsuario {

    clsJDBC objConectar = new clsJDBC();
    String str;
    ResultSet rs = null;

    public Integer generarCodigo() throws Exception {
        str = "Select coalesce(max(idUsuario),0)+1 as codigo  from usuario";
        try {
            rs = objConectar.consultarBD(str);
            return rs.getInt("codigo");
        } catch (Exception e) {
            throw new Exception("Error al generar codigo para el nuevo ususario--->" + e.getMessage());
        }
    }

    public String login(String nomUser, String contrasena) throws Exception {
        // Consulta SQL actualizada para manejar roles y obtener nombres desde MEDICO o ADMINISTRADOR
        str = "SELECT u.idUsuario, u.rol, "
                + "CASE "
                + "  WHEN u.rol = 'medico' THEN (SELECT CONCAT(m.nombres, ' ', m.apellidoPaterno, ' ', m.apellidoMaterno) "
                + "                              FROM medico m WHERE m.idMedico = u.idMedico) "
                + "  WHEN u.rol = 'administrador' THEN (SELECT CONCAT(a.nombres, ' ', a.apellidoPaterno, ' ', a.apellidoMaterno) "
                + "                                    FROM administrador a WHERE a.idAdministrador = u.idAdministrador) "
                + "  ELSE '' END AS nombreCompleto "
                + "FROM usuario u "
                + "WHERE u.nombre_usuario = ? "
                + "AND u.contraseña = crypt(?, u.contraseña) "
                + "AND u.vigencia = true";

        String rol = "";
        int idUsuario = -1;
        String nombreCompleto = "";
        Connection micon = null;

        objConectar.conectar();
        micon = objConectar.getCon();

        try {
            PreparedStatement sp = micon.prepareStatement(str);
            sp.setString(1, nomUser);
            sp.setString(2, contrasena);

            rs = sp.executeQuery();

            if (rs.next()) {

                rol = rs.getString("rol");
                idUsuario = rs.getInt("idUsuario");
                nombreCompleto = rs.getString("nombreCompleto");

                clsFunciones.USUARSIO_INICIO_SESION = nombreCompleto;
                clsFunciones.ID_INICIO_SESION = idUsuario;

                return rol + ":" + idUsuario + ":" + nombreCompleto;
            } else {
                clsFunciones.USUARSIO_INICIO_SESION = "";
                clsFunciones.ID_INICIO_SESION = -1;

                return "Usuario o contraseña incorrectos";
            }
        } catch (Exception e) {
            throw new Exception("Error al iniciar sesión: " + e.getMessage());
        } finally {

            if (micon != null) {
                objConectar.desconectar();
            }
        }
    }

    public boolean validarVigencia(String usu) throws Exception {
        str = "SELECT vigencia FROM usuario WHERE nombre_usuario = ?";
        Connection micon = null;

        try {
            objConectar.conectar();
            micon = objConectar.getCon();
            PreparedStatement sp = micon.prepareStatement(str);
            sp.setString(1, usu);
            rs = sp.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("vigencia");
            }
        } catch (Exception e) {
            throw new Exception("Error al validar usuario: " + e.getMessage());
        } finally {

            if (micon != null) {
                objConectar.desconectar();
            }
        }
        return false;
    }

    public int obtenerIdMedicoLogueado() throws Exception {
        int idUsuario = clsFunciones.ID_INICIO_SESION;

        if (idUsuario == -1) {
            throw new Exception("No hay un usuario logueado.");
        }

        str = "SELECT idMedico FROM USUARIO WHERE idUsuario = " + idUsuario + " AND rol = 'medico';";
        try {
            objConectar.conectar();
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                int idMedico = rs.getInt("idMedico");
                if (idMedico == 0) {
                    throw new Exception("El usuario logueado no es un médico.");
                }
                return idMedico;
            } else {
                throw new Exception("El usuario logueado no existe o no es un médico.");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el ID del médico logueado --> " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public boolean existencia(Integer id) throws Exception {
        str = "select * from usuario where idusuario=" + id + ";";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Error interno al encontrar usuario" + e.getMessage());

        }
    }

    public ResultSet listarUsuario() throws Exception {
        str = " select us.idUsuario,us.nombre_usuario,us.correo,us.vigencia,us.rol "
                + "from usuario us "
                + " order by 1;";
        try {
            return rs = objConectar.consultarBD(str);
        } catch (Exception e) {
            throw new Exception("Error interno al generar el id del nuevo usuario" + e.getMessage());

        }
    }

    public String mostrarMedico(String dni) throws Exception {
        str = "select apellidoPaterno||' '||apellidoMaterno||', '|| nombres  as nombreCompleto from medico where dni='" + dni + "'";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return rs.getString("nombreCompleto");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al encontrar el medico-->" + e.getMessage());
        }
        return "";
    }

    public String mostrarAdministrador(String dni) throws Exception {
        str = "select apellidoPaterno||' '||apellidoMaterno||', '|| nombres  as nombreCompleto from administrador where dni='" + dni + "'";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return rs.getString("nombreCompleto");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al encontrar el administrador-->" + e.getMessage());
        }
        return "";
    }

    public Integer generarIdUser() throws Exception {
        str = "select coalesce(max(idusuario),0)+1 as id from usuario;";
        try {
            rs = objConectar.consultarBD(str);
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new Exception("Error interno al generar el id del nuevo usuario" + e.getMessage());
        }
        return 0;
    }

    public ResultSet buscarUser(Integer id) throws Exception {
        str = "select us.idUsuario,us.nombre_usuario,us.correo,us.vigencia,us.rol,us.contraseña "
                + " from usuario us "
                + " where idusuario=" + id + ";";
        try {
            return rs = objConectar.consultarBD(str);

        } catch (Exception e) {
            throw new Exception("Error interno al buscar el id del nuevo usuario" + e.getMessage());

        }
    }

    public Integer obtenerIdMed(String dni) throws Exception {
        str = "select idMedico as codigo from medico where dni='" + dni + "';";
        try {
            rs = objConectar.consultarBD(str);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Error interno al encontrar el id del medico con el dni-->" + e.getMessage());
        }
    }

    public Integer obtenerIdAdm(String dni) throws Exception {
        str = "select idAdministrador as codigo from Administrador where dni='" + dni + "';";
        try {
            rs = objConectar.consultarBD(str);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Error interno al encontrar el id del administrador con el dni-->" + e.getMessage());
        }
    }

    public void insertarNuevoUsuario(Integer idUser, String nombre_usuario,
            String contraseña, String correo, boolean vigencia, String rol,
            Integer idMedico, Integer idAdministrador) throws Exception {
        try {
            if (rol.equalsIgnoreCase("medico")) {
                str = "insert into usuario values (" + idUser + ", '"
                        + nombre_usuario + "', crypt('" + contraseña
                        + "', gen_salt('bf')), '" + correo + "', " + vigencia
                        + ", 'medico', " + (idMedico != null ? idMedico : "null")
                        + ",null)";
            } else if (rol.equalsIgnoreCase("administrador")) {
                str = "insert into usuario values (" + idUser + ", '"
                        + nombre_usuario + "', crypt('" + contraseña
                        + "', gen_salt('bf')), '" + correo + "', " + vigencia
                        + ", 'administrador', null,"
                        + (idAdministrador != null ? idAdministrador : "null") + ")";
            }
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error interno al insertar nuevo usuario: "
                    + e.getMessage());
        }
    }

    public Integer obtenerIdAdministrador(String correo) throws Exception {
        str = "select idAdministrador as id from administrador where correo ='" + correo + "'";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Error interno al obtener Id  del administrador con el correo" + e.getMessage());
        }
    }

    public Integer obtenerIdMedico(String correo) throws Exception {
        str = "select IdMedico as id from medico where correo ='" + correo + "'";
        try {
            rs = objConectar.consultarBD(str);
            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Error interno al obtener Id  del administrador con el correo" + e.getMessage());
        }
    }

    public void eliminarUsuario(Integer id) throws Exception {
        str = "delete from usuario where idUsuario=" + id + ";";

        try {
            objConectar.ejecutarBD(str);;
        } catch (Exception e) {
            throw new Exception("Error interno al eliminar al usuario -->" + e.getMessage());
        }
    }

    public void darDeBajaUsuario(Integer id) throws Exception {
        str = "update usuario set vigencia=false where idUsuario=" + id + ";";
        try {
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error interno al dar de baja al usuario -->" + e.getMessage());

        }
    }

    public void modificarUusario(Integer idUser, String nombre_usuario, String contraseña, String correo, boolean vigencia) throws Exception {
        str = "update usuario set nombre_usuario='" + nombre_usuario + "', contraseña='" + contraseña + "', correo='" + correo + "', vigencia=" + vigencia + ", "
                + " where idUsuario=" + idUser + ";";
        try {
            objConectar.ejecutarBD(str);
        } catch (Exception e) {
            throw new Exception("Error interno al modificar datos del al usuario -->" + e.getMessage());
        }
    }

    public String obtenerDniPorIdUsuario(Integer idUsuario) throws Exception {
        try {
            String str = "select me.dni as dni_medico,ad.dni as dni_administrador  "
                    + "FROM usuario u "
                    + "left join medico me on u.idmedico = me.idmedico "
                    + "left join administrador ad on u.idadministrador = ad.idadministrador "
                    + "WHERE u.idusuario = " + idUsuario;

            rs = objConectar.consultarBD(str);

            if (rs.next()) {
                String dniMedico = rs.getString("dni_medico");
                String dniAdmin = rs.getString("dni_administrador");

                return dniMedico != null ? dniMedico : dniAdmin;
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Error al obtener DNI: " + e.getMessage());
        }
    }

}
