/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;


import Capa_Negocio.clsServicio;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author MendozaGastulo_Tadeou
 */
public class jdSeleccionServicio extends javax.swing.JDialog {

    clsServicio objServicio = new clsServicio();
    private int idServicioSeleccionado = 0;
    private String nombreServicioSeleccionado = "";
    private double precioServicioSeleccionado = 0.0;

    public jdSeleccionServicio(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        listarServicios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiciosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
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

    private void tblServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiciosMouseClicked
        int filaSeleccionada = tblServicios.getSelectedRow();
        if (filaSeleccionada != -1) {
            idServicioSeleccionado = Integer.parseInt(tblServicios.getValueAt(filaSeleccionada, 0).toString());
            nombreServicioSeleccionado = tblServicios.getValueAt(filaSeleccionada, 1).toString();
            precioServicioSeleccionado = Double.parseDouble(tblServicios.getValueAt(filaSeleccionada, 2).toString());

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Deseas añadir el servicio " + nombreServicioSeleccionado + " con precio: " + precioServicioSeleccionado + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); 
            }
        }
    }//GEN-LAST:event_tblServiciosMouseClicked

    private void listarServicios() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_SERVICIO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");

        try {
            ResultSet rs = objServicio.listarServiciosParaPago();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("idServicio"),
                    rs.getString("nombre"),
                    rs.getDouble("precio")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar servicios: " + e.getMessage());
        }
        tblServicios.setModel(modelo);
    }

    public int getIdServicioSeleccionado() {
        return idServicioSeleccionado;
    }

    public String getNombreServicioSeleccionado() {
        return nombreServicioSeleccionado;
    }

    public double getPrecioServicioSeleccionado() {
        return precioServicioSeleccionado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblServicios;
    // End of variables declaration//GEN-END:variables
}
