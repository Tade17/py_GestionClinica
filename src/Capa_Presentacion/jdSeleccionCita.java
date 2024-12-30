/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.clsCita;
import Capa_Negocio.clsPaciente;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class jdSeleccionCita extends javax.swing.JDialog {

    private clsCita objCita = new clsCita();
    private int idCitaSeleccionada = 0; 
    private double precioCita = 100.00; 
    private String fechaCitaSeleccionada = "";
    private String motivoCitaSeleccionada = "";
    clsPaciente objPaciente = new clsPaciente();

    public jdSeleccionCita(java.awt.Frame parent, boolean modal, String dniPaciente) throws Exception {
        super(parent, modal);
        initComponents();
        listarCitasPorPaciente(dniPaciente);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCitasPaciente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblCitasPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCitasPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCitasPaciente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCitasPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasPacienteMouseClicked
        int filaSeleccionada = tblCitasPaciente.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tblCitasPaciente.getModel();
            idCitaSeleccionada = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
            fechaCitaSeleccionada = modelo.getValueAt(filaSeleccionada, 1).toString(); 
            motivoCitaSeleccionada = modelo.getValueAt(filaSeleccionada, 2).toString();

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Deseas añadir la cita con ID " + idCitaSeleccionada + " y motivo: " + motivoCitaSeleccionada + " al detalle de pago?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); 
            }
        }
    }//GEN-LAST:event_tblCitasPacienteMouseClicked

    private void listarCitasPorPaciente(String dniPaciente) {
        ResultSet rs = null;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_CITA");
        modelo.addColumn("FECHA");
        modelo.addColumn("MOTVO");
        modelo.addColumn("PRECIO");
        try {
            rs = objCita.listarCitasPoridPaciente(objPaciente.obtenerCodigoPacienteXDNI(dniPaciente));
            Object datos[][] = new Object[1][7];
            while (rs.next()) {
                datos[0][0] = rs.getInt("idcita");
                datos[0][1] = rs.getString("fecha");
                datos[0][2] = rs.getString("motivo");
                datos[0][3] = precioCita;
                modelo.addRow(datos[0]);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e.getMessage());
        }
        tblCitasPaciente.setModel(modelo);
    }
    

    public int getIdCitaSeleccionada() {
        return idCitaSeleccionada;
    }

    public double getPrecioCita() {
        return precioCita;
    }

    public String getFechaCitaSeleccionada() {
        return fechaCitaSeleccionada;
    }

    public String getMotivoCitaSeleccionada() {
        return motivoCitaSeleccionada;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCitasPaciente;
    // End of variables declaration//GEN-END:variables
}
