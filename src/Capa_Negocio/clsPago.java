/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class clsPago {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con = null;
    Statement sent;

    public Integer generarCodigoPago() throws Exception {
        strSQL = "select Coalesce(max(idPago),0)+1 as codigo from pago";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo de pago--->" + e.getMessage());
        }
        return 0;
    }

    public Integer generarCodigoDetallePago() throws Exception {
        strSQL = "select Coalesce(max(idDetallePago),0)+1 as codigo from detalle_pago";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar codigo de pago--->" + e.getMessage());
        }
        return 0;
    }

    public void registrarPago(int idPago, boolean estado, int idPaciente, int idMetodoPago, JTable tblCitas, JTable tblServicios) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);
            sent = con.createStatement();

            if ((tblCitas == null || tblCitas.getRowCount() == 0) && (tblServicios == null || tblServicios.getRowCount() == 0)) {
                throw new Exception("Debe seleccionar al menos una cita o un servicio para registrar el pago.");
            }

            strSQL = "INSERT INTO pago (idPago, fecha, estado, idPaciente, idMetodoPago) "
                    + "VALUES (" + idPago + ", current_date, " + estado + ", " + idPaciente + ", " + idMetodoPago + ")";
            sent.executeUpdate(strSQL);

            if (tblCitas != null && tblCitas.getRowCount() > 0) {
                for (int i = 0; i < tblCitas.getRowCount(); i++) {
                    int idCita = Integer.parseInt(tblCitas.getValueAt(i, 0).toString());
                    double precioCita = Double.parseDouble(tblCitas.getValueAt(i, 3).toString());

                    strSQL = "INSERT INTO detalle_pago (idPago, idCita, idServicio, subtotal, igv, monto_total, descripcion) "
                            + "VALUES (" + idPago + ", " + idCita + ", NULL, " + precioCita + ", " + (precioCita * 0.18) + ", " + (precioCita * 1.18) + ", 'Pago por cita médica')";
                    sent.executeUpdate(strSQL);
                }
            }

            if (tblServicios != null && tblServicios.getRowCount() > 0) {
                for (int i = 0; i < tblServicios.getRowCount(); i++) {
                    int idServicio = Integer.parseInt(tblServicios.getValueAt(i, 0).toString());
                    double precioServicio = Double.parseDouble(tblServicios.getValueAt(i, 2).toString());

                    strSQL = "INSERT INTO detalle_pago (idPago, idCita, idServicio, subtotal, igv, monto_total, descripcion) "
                            + "VALUES (" + idPago + ", NULL, " + idServicio + ", " + precioServicio + ", " + (precioServicio * 0.18) + ", " + (precioServicio * 1.18) + ", 'Pago por servicio')";
                    sent.executeUpdate(strSQL);
                }
            }
            strSQL = "UPDATE pago SET estado = true WHERE idPago = " + idPago;
            sent.executeUpdate(strSQL);

            con.commit();
            JOptionPane.showMessageDialog(null, "El pago se ha registrado y su estado se ha actualizado exitosamente.");
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw new Exception("Error al registrar el pago: " + e.getMessage());
        } finally {
            if (sent != null) {
                sent.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
