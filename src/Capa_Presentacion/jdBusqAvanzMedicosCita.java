/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.clsEspecialidad;
import Capa_Negocio.clsMedico;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class jdBusqAvanzMedicosCita extends javax.swing.JDialog {

    clsMedico objMed = new clsMedico();
    clsEspecialidad objEspecialidad = new clsEspecialidad();
    private String dniMedico = "";
    private String nombreMedico = "";

    public jdBusqAvanzMedicosCita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listarEspecialidades();
        jdcFecha.setDate(new Date());
        configurarListeners();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        cboEspecialidades = new javax.swing.JComboBox<>();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Elegir el turno médico");

        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLista);

        cboEspecialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEspecialidadesActionPerformed(evt);
            }
        });
        cboEspecialidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboEspecialidadesKeyReleased(evt);
            }
        });

        jdcFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jdcFechaKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/8960624_stethoscopes_stethoscope_doctor_health_healthcare_icon.png"))); // NOI18N
        jLabel1.setText("Especialidad");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("FECHA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboEspecialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEspecialidadesActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboEspecialidadesActionPerformed

    private void tblListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tblLista.getSelectedRow();
        if (filaSeleccionada != -1) {
            dniMedico = tblLista.getValueAt(filaSeleccionada, 0).toString();
            nombreMedico = tblLista.getValueAt(filaSeleccionada, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Deseas escoger al  Dr." + nombreMedico + " con dni: " + dniMedico + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }//GEN-LAST:event_tblListaMouseClicked

    private void cboEspecialidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboEspecialidadesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEspecialidadesKeyReleased

    private void jdcFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcFechaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jdcFechaKeyReleased

    private void listarMedicos() {
        ResultSet rs = null;
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("nombreCompleto");
        modelo.addColumn("especialidad");
        modelo.addColumn("fecha");

        try {
            rs = objMed.listarMedicos();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("idMedico"),
                    rs.getString("nombreCompleto"),
                    rs.getString("especialidad"),
                    rs.getDate("fecha")
                });
            }
            tblLista.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar los médicos: " + e.getMessage());
        }
    }

    private void actualizarTabla() {
        String especialidadSeleccionada = cboEspecialidades.getSelectedItem().toString();
        Date fechaSeleccionada = jdcFecha.getDate();

        // Si no hay especialidad seleccionada o fecha, mostrar todos los médicos
        if (especialidadSeleccionada.isEmpty() || fechaSeleccionada == null) {
            listarMedicos();
        } else {
            listarFiltradoPorEspecialidadYFecha();
        }
    }

    private void listarEspecialidades() {
        ResultSet rs = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cboEspecialidades.setModel(modelo);
        try {
            rs = objEspecialidad.listarEspecialidades();
            while (rs.next()) {
                modelo.addElement(rs.getString("nombre"));
            }
        } catch (Exception e) {
        }
    }

    private void listarFiltradoPorEspecialidadYFecha() {
        String especialidad = cboEspecialidades.getSelectedItem().toString();
        java.sql.Date fecha = null;
        try {
            if (jdcFecha.getDate() != null) {
                fecha = new java.sql.Date(jdcFecha.getDate().getTime());
            }

            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modelo.addColumn("Dni");
            modelo.addColumn("nombreCompleto");
            modelo.addColumn("Fecha");
            modelo.addColumn("Hora Inicio");
            modelo.addColumn("Hora Fin");

            ResultSet rs = objMed.filtrarMedicosPorEspecialidadYFecha(especialidad, fecha);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("dni"),
                    rs.getString("nombreCompleto"),
                    rs.getDate("fecha"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getInt("cupos")
                });
            }
            tblLista.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar médicos: " + e.getMessage());
        }
    }

    private void configurarListeners() {
        // Listener para el JDateChooser
        jdcFecha.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    actualizarTabla();
                }
            }
        });

        // Listener para el ComboBox
        cboEspecialidades.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    actualizarTabla();
                }
            }
        });
    }

    public String getDniMedico() {
        return dniMedico;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboEspecialidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable tblLista;
    // End of variables declaration//GEN-END:variables
}
