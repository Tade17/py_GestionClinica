/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.clsCita;
import Capa_Negocio.clsDetalleCita;
import Capa_Negocio.clsPaciente;
import Capa_Negocio.clsUsuario;
import java.awt.Color;
import java.awt.Frame;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class jdCita_Medico extends javax.swing.JDialog {

    clsCita objCita = new clsCita();
    clsDetalleCita objDetalleCita = new clsDetalleCita();
    clsPaciente objPaciente = new clsPaciente();
    clsUsuario objUsuario = new clsUsuario();

    public jdCita_Medico(java.awt.Frame parent, boolean modal) throws ParseException, Exception {
        super(parent, modal);
        initComponents();
        
        int idMedico = objUsuario.obtenerIdMedicoLogueado();
        listarCitasPendientes(idMedico);
        listarCitasAtendidas(idMedico);
        
        SpinnerDateModel modelHora = new SpinnerDateModel();
        spnHoraCita.setModel(modelHora);

        
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnHoraCita, "HH:mm:ss");
        spnHoraCita.setEditor(editor);

        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date defaultTime = sdf.parse("00:00:00");
        spnHoraCita.setValue(defaultTime);
        txtPaciente.setEditable(false);
        txtMotivo.setEditable(false);
        chkEstado.setEnabled(false);
        txtPaciente.setBackground(Color.WHITE);
        txtPaciente.setForeground(Color.BLACK);
        txtMotivo.setBackground(Color.WHITE);
        txtMotivo.setForeground(Color.BLACK);

    }

    private void limpiarControles() {
        txtIdCita.setText("");
        txtMotivo.setText("");
        txtDniPaciente.setText("");
        txtPaciente.setText("");
        jdFechaCita.setDate(null);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date defaultTime = sdf.parse("00:00:00");
            spnHoraCita.setValue(defaultTime);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al limpiar la hora: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtIdCita = new javax.swing.JTextField();
        btnBuscarCita = new javax.swing.JButton();
        chkEstado = new javax.swing.JCheckBox();
        jdFechaCita = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        spnHoraCita = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtDniPaciente = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGestionarCita = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCitasPendientes = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCitasAtendidas = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" CITA");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdCitaKeyTyped(evt);
            }
        });
        jPanel1.add(txtIdCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 94, -1));

        btnBuscarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1622836_checkmark_done_explore_find_magnifier_icon.png"))); // NOI18N
        btnBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCitaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        chkEstado.setText("Pendiente");
        chkEstado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadoStateChanged(evt);
            }
        });
        chkEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkEstadoMouseClicked(evt);
            }
        });
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));
        jPanel1.add(jdFechaCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 140, -1));

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtMotivo);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 410, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Fecha_Cita");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel14.setText("Estado");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setText("Hora_cita");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setText("Id_Cita");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        spnHoraCita.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spnHoraCitaFocusLost(evt);
            }
        });
        spnHoraCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnHoraCitaKeyTyped(evt);
            }
        });
        jPanel1.add(spnHoraCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 90, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel17.setText("Motivo");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("DNI");

        txtDniPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniPacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniPacienteKeyTyped(evt);
            }
        });

        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1435355619_search.png"))); // NOI18N
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("Paciente");

        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarPaciente)
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscarPaciente)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 530, -1));

        btnLimpiar.setBackground(new java.awt.Color(153, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929159_avatar_doctor_health_hospital_medical_icon.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 520, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGestionarCita.setBackground(new java.awt.Color(153, 255, 255));
        btnGestionarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929160_avatar_doctor_hospital_man_medical_icon.png"))); // NOI18N
        btnGestionarCita.setText("Gestionar Cita");
        btnGestionarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarCitaActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(153, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6673751_aid_emergency_first_health_healthcare_icon.png"))); // NOI18N
        btnEliminar.setText("Salir");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGestionarCita)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnGestionarCita)
                .addGap(52, 52, 52)
                .addComponent(btnEliminar)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCitasPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCitasPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasPendientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCitasPendientes);

        tblCitasAtendidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCitasAtendidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasAtendidasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCitasAtendidas);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/calendar_1827331.png"))); // NOI18N
        jLabel12.setText("CITAS ATENDIDAS");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/schedule_3652191.png"))); // NOI18N
        jLabel18.setText("CITAS POR ATENDER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(481, 481, 481)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void btnBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCitaActionPerformed
        // TODO add your handling code here:

        if (txtIdCita.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la cita que desea buscar.");
            return;
        }
        try {
            int idCita = Integer.parseInt(txtIdCita.getText());
            int idMedicoLogueado = objUsuario.obtenerIdMedicoLogueado();
            boolean pertenece = objCita.verificarCitaMedico(idCita, idMedicoLogueado);
            if (!pertenece) {
                JOptionPane.showMessageDialog(this, "La cita no pertenece al médico logueado.");
                return;
            }
            ResultSet rsCita = objCita.buscarCita(Integer.parseInt(txtIdCita.getText()));
            if (rsCita.next()) {
                jdFechaCita.setDate(rsCita.getDate("fecha"));
                spnHoraCita.setValue(rsCita.getTime("hora_inicio"));
                txtMotivo.setText(rsCita.getString("motivo"));
                txtDniPaciente.setText(rsCita.getString("dniPaciente"));
                txtPaciente.setText(rsCita.getString("nombrePaciente"));
                chkEstado.setSelected(rsCita.getBoolean("estado"));
                chkEstado.setText(rsCita.getBoolean("estado") ? "Atendida" : "Pendiente");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una cita con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de la cita debe ser un número entero.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la cita: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarCitaActionPerformed

    private void tblCitasPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasPendientesMouseClicked
        // TODO add your handling code here:
        txtIdCita.setText(String.valueOf(tblCitasPendientes.getValueAt(tblCitasPendientes.getSelectedRow(), 0)));
        btnBuscarCitaActionPerformed(null);
    }//GEN-LAST:event_tblCitasPendientesMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGestionarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarCitaActionPerformed
        int filaSeleccionada = tblCitasPendientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una cita pendiente para gestionar.");
            return;
        }

        int idCitaSeleccionada = Integer.parseInt(tblCitasPendientes.getValueAt(filaSeleccionada, 0).toString());
        String estadoCita = tblCitasPendientes.getValueAt(filaSeleccionada, 6).toString(); 

        if (estadoCita.equals("Atendida")) {
            JOptionPane.showMessageDialog(this, "La cita seleccionada ya ha sido atendida.");
            return;
        }

        jdDetalleCita detalleCita;
        try {
            detalleCita = new jdDetalleCita((Frame) SwingUtilities.getWindowAncestor(this), true, idCitaSeleccionada);
            detalleCita.setLocationRelativeTo(this); // Centrar el formulario
            detalleCita.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(jdCita_Medico.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int idMedico = objUsuario.obtenerIdMedicoLogueado();
            listarCitasPendientes(idMedico);
            listarCitasAtendidas(idMedico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las listas de citas: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGestionarCitaActionPerformed

    private void chkEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEstadoMouseClicked

    private void chkEstadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadoStateChanged
        // TODO add your handling code here:
        if (chkEstado.isSelected()) {
            chkEstado.setText("Atendida");
        } else {
            chkEstado.setText("Pendiente");
        }
    }//GEN-LAST:event_chkEstadoStateChanged

    private void spnHoraCitaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spnHoraCitaFocusLost

    }//GEN-LAST:event_spnHoraCitaFocusLost

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        // TODO add your handling code here:
        ResultSet rsPaciente;
        try {
            if (txtDniPaciente.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ingresa el dni del paciente que deseas buscar");
            } else {
                rsPaciente = objPaciente.buscarPacientePorDNI(txtDniPaciente.getText());
                if (rsPaciente.next()) {
                    txtPaciente.setText(rsPaciente.getString("nombre_completo"));
                } else {
                    JOptionPane.showMessageDialog(this, "El paciente no existe");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void txtDniPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPacienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniPacienteKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblCitasAtendidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasAtendidasMouseClicked
        // TODO add your handling code here:
        txtIdCita.setText(String.valueOf(tblCitasAtendidas.getValueAt(tblCitasAtendidas.getSelectedRow(), 0)));
        btnBuscarCitaActionPerformed(null);
    }//GEN-LAST:event_tblCitasAtendidasMouseClicked

    private void txtIdCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCitaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txtIdCita.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdCitaKeyTyped

    private void txtDniPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPacienteKeyTyped

        char key = evt.getKeyChar();

        if (!Character.isDigit(key)) {
            evt.consume(); 
        }

        if (txtDniPaciente.getText().length() >= 8) {
            evt.consume(); 
        }
    }//GEN-LAST:event_txtDniPacienteKeyTyped

    private void txtPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPacienteKeyTyped

    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotivoKeyTyped

    private void spnHoraCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnHoraCitaKeyTyped
        char key = evt.getKeyChar();
        boolean permitido = (key >= '0' && key <= '9') || key == ':';

        if (!permitido) {
            evt.consume();
        }
    }//GEN-LAST:event_spnHoraCitaKeyTyped
    /* private boolean validarHoraCita(String horaSeleccionada, String horaInicio, String horaFin) throws Exception {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        java.util.Date horaSel = formatoHora.parse(horaSeleccionada);
        java.util.Date horaIni = formatoHora.parse(horaInicio);
        java.util.Date horaFn = formatoHora.parse(horaFin);

        // Validar que la hora seleccionada esté en el rango
        return (horaSel.equals(horaIni) || horaSel.after(horaIni)) && (horaSel.before(horaFn) || horaSel.equals(horaFn));
    }*/

 /* private void limpiarTablaTurnos() {
        // Crear un modelo vacío con las mismas columnas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Turno");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");

        // Asignar el modelo vacío a la tabla
        tblTurnos.setModel(modelo);
    }*/

 /*private boolean validarCampos() {
        if (txtIdCita.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID de la cita no puede estar vacío.");
            return false;
        }
        if (jdFechaCita.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha.");
            return false;
        }
        if (spnHoraCita.getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una hora.");
            return false;
        }
        if (txtMotivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El motivo no puede estar vacío.");
            return false;
        }
        if (txtDniMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el DNI del médico.");
            return false;
        }
        if (txtDniPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el DNI del paciente.");
            return false;
        }
        return true;
    }*/
    private void listarCitasPendientes(int idMedico) {
        try {
            ResultSet rs = objCita.listarCitasPendientesPorMedico(idMedico);
            DefaultTableModel modeloPendientes = new DefaultTableModel();
            modeloPendientes.addColumn("ID Cita");
            modeloPendientes.addColumn("Fecha");
            modeloPendientes.addColumn("Hora Inicio");
            modeloPendientes.addColumn("Hora Fin");
            modeloPendientes.addColumn("Motivo");
            modeloPendientes.addColumn("Paciente");
            modeloPendientes.addColumn("Estado_cita");

            while (rs.next()) {
                modeloPendientes.addRow(new Object[]{
                    rs.getInt("idCita"),
                    rs.getDate("fecha"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getString("motivo"),
                    rs.getString("nombrePaciente"),
                    rs.getBoolean("estado") ? "Atendida" : "Pendiente"

                });
            }
            tblCitasPendientes.setModel(modeloPendientes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar citas pendientes: " + e.getMessage());
        }
    }

    private void listarCitasAtendidas(int idMedico) {
        try {
            ResultSet rs = objCita.listarCitasAtendidasPorMedico(idMedico);
            DefaultTableModel modeloAtendidas = new DefaultTableModel();
            modeloAtendidas.addColumn("ID Cita");
            modeloAtendidas.addColumn("Fecha");
            modeloAtendidas.addColumn("Hora Inicio");
            modeloAtendidas.addColumn("Hora Fin");
            modeloAtendidas.addColumn("Motivo");
            modeloAtendidas.addColumn("Paciente");
            modeloAtendidas.addColumn("Estado_cita");

            while (rs.next()) {
                modeloAtendidas.addRow(new Object[]{
                    rs.getInt("idCita"),
                    rs.getDate("fecha"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getString("motivo"),
                    rs.getString("nombrePaciente"),
                    rs.getBoolean("estado") ? "Atendida" : "Pendiente"
                });
            }
            tblCitasAtendidas.setModel(modeloAtendidas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar citas atendidas: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCita;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGestionarCita;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser jdFechaCita;
    private javax.swing.JSpinner spnHoraCita;
    private javax.swing.JTable tblCitasAtendidas;
    private javax.swing.JTable tblCitasPendientes;
    private javax.swing.JTextField txtDniPaciente;
    private javax.swing.JTextField txtIdCita;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
