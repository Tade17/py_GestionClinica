/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.clsCita;
import Capa_Negocio.clsMedico;
import Capa_Negocio.clsPaciente;
import Capa_Negocio.clsTurnoMedico;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class jdCita extends javax.swing.JDialog {

    clsCita objCita = new clsCita();
    clsPaciente objPaciente = new clsPaciente();
    clsMedico objMedico = new clsMedico();
    clsTurnoMedico objTurnoMedico = new clsTurnoMedico();

    public jdCita(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        txtDniPaciente.requestFocus();
        txtPaciente.setEditable(false);
        txtMedico.setEditable(false);
        txtEspecialidad.setEditable(false);
        SpinnerDateModel modelHora = new SpinnerDateModel();
        spnHoraCita.setModel(modelHora);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnHoraCita, "HH:mm:ss");
        spnHoraCita.setEditor(editor);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date defaultTime = sdf.parse("00:00:00");
        spnHoraCita.setValue(defaultTime);
        listarCitas();
        listarTurnosMedico();
        
    }

    private void listarTurnosMedico() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Turno");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");

        try {
            ResultSet rsTurnos = objTurnoMedico.listarTurnosPorMedico(objCita.obtenerIdMedicoxDni(txtDniMedico.getText()));
            Object datos[][] = new Object[1][4];
            while (rsTurnos.next()) {

                datos[0][0] = rsTurnos.getInt("idTurno");
                datos[0][1] = rsTurnos.getDate("fecha");
                datos[0][2] = rsTurnos.getString("hora_inicio");
                datos[0][3] = rsTurnos.getString("hora_fin");
                modelo.addRow(datos[0]);
            }
            tblTurnos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar turnos: " + e.getMessage());
        }
    }

    private void listarTurnoDeCita(int idCita) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Turno");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");

        try {
            ResultSet rsTurno = objTurnoMedico.obtenerTurnoDeCita(idCita);
            if (rsTurno.next()) {
                Object[] datos = {
                    rsTurno.getInt("idTurno"),
                    rsTurno.getDate("fecha"),
                    rsTurno.getString("hora_inicio"),
                    rsTurno.getString("hora_fin")
                };
                modelo.addRow(datos);
            }
            tblTurnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el turno de la cita: " + e.getMessage());
        }
    }

    private void listarCitas() {
        ResultSet rsCitas = null;
        String estado = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id_Cita");
        modelo.addColumn("Fecha Cita");
        modelo.addColumn("Hora Cita");
        modelo.addColumn("Estado");
        modelo.addColumn("Motivo");
        modelo.addColumn("Medico asignado");
        modelo.addColumn("Paciente");
        try {
            rsCitas = objCita.listarCitasPendientes();
            Object datos[][] = new Object[1][7];
            while (rsCitas.next()) {
                if (rsCitas.getString("estado").equals("t")) {
                    estado = "Atendida";
                } else {
                    estado = "Pendiente";
                }
                datos[0][0] = rsCitas.getInt("idCita");
                datos[0][1] = rsCitas.getDate("fecha");
                datos[0][2] = rsCitas.getString("hora_inicio");
                datos[0][3] = estado;
                datos[0][4] = rsCitas.getString("motivo");
                datos[0][5] = rsCitas.getString("nombre_medico");
                datos[0][6] = rsCitas.getString("nombre_paciente");
                modelo.addRow(datos[0]);
            }
            tblCita.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar las citas -->" + e.getMessage());
        }

    }

    private void limpiarControles() {
        txtIdCita.setText("");
        jdFechaCita.setDate(null);

        try {
            // Reiniciar el valor del spinner a "00:00:00"
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date defaultTime = sdf.parse("00:00:00");
            spnHoraCita.setValue(defaultTime);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al limpiar la hora: " + e.getMessage());
        }

        txtMotivo.setText("");
        txtDniPaciente.setText("");
        txtPaciente.setText("");
        txtDniMedico.setText("");
        txtMedico.setText("");
        txtEspecialidad.setText("");
        txtDniPaciente.requestFocus();
        limpiarTablaTurnos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtIdCita = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jdFechaCita = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        spnHoraCita = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtDniPaciente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        btnVer = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDniMedico = new javax.swing.JTextField();
        txtMedico = new javax.swing.JTextField();
        btnBusquedaAvanzadaMédico = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTurnos = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        btnAgregarTurno = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCita = new javax.swing.JTable();

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

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1622836_checkmark_done_explore_find_magnifier_icon.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));
        jPanel1.add(jdFechaCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 720, 140, -1));

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtMotivo);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 410, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Fecha_Cita");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setText("Hora_cita");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 720, -1, -1));

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
        jPanel1.add(spnHoraCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 720, 90, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel17.setText("Motivo");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("Paciente");

        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteKeyTyped(evt);
            }
        });

        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/buscar2.png"))); // NOI18N
        btnVer.setText("VER");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
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
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDniPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 540, -1));

        jPanel6.setBackground(new java.awt.Color(51, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("Médico");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("DNI");

        txtDniMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniMedicoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniMedicoKeyTyped(evt);
            }
        });

        txtMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedicoKeyTyped(evt);
            }
        });

        btnBusquedaAvanzadaMédico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/buscar2.png"))); // NOI18N
        btnBusquedaAvanzadaMédico.setText("BUSQUEDA AVANZADA");
        btnBusquedaAvanzadaMédico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaAvanzadaMédicoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("Especialidad (es)");

        txtEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspecialidadKeyTyped(evt);
            }
        });

        tblTurnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTurnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTurnosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTurnos);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel18.setText("Turnos Disponibles");

        btnAgregarTurno.setText("Agregar Nuevo");
        btnAgregarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDniMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(btnBusquedaAvanzadaMédico, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel18)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(btnAgregarTurno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDniMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBusquedaAvanzadaMédico, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarTurno)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 530, 450));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNuevo.setBackground(new java.awt.Color(153, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6673745_health_healthcare_hospital_medic_medical_icon.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(153, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6673751_aid_emergency_first_health_healthcare_icon.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(153, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929159_avatar_doctor_health_hospital_medical_icon.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(153, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6673740_ambulance_emergency_health_healthcare_hospital_icon.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnLimpiar))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnEliminar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCita);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarControles();
        listarCitas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:

        try {
            if (btnNuevo.getText().equals("Nuevo")) {
                btnNuevo.setText("Guardar");
                JOptionPane.showMessageDialog(this, "Complete los campos para agendar la cita");
                limpiarControles();
                txtIdCita.setText(objCita.generarCodigoCita().toString());
                txtMotivo.requestFocus();
            } else { // Guardar
                int idTurno = Integer.parseInt(tblTurnos.getValueAt(tblTurnos.getSelectedRow(), 0).toString());
                int idMedico = objCita.obtenerIdMedicoxDni(txtDniMedico.getText());
                int idPaciente = objCita.obtenerIdPacienteXDNI(txtDniPaciente.getText());
                int idCita = Integer.parseInt(txtIdCita.getText());
                String fecha = new SimpleDateFormat("yyyy-MM-dd").format(jdFechaCita.getDate());
                String motivo = txtMotivo.getText();
                double precio = 100.00;
                if (!objTurnoMedico.validarTurnoDisponible(idTurno)) {
                    JOptionPane.showMessageDialog(this, "El turno seleccionado no está disponible. ");
                    return;
                }

                btnNuevo.setText("Nuevo");
                objCita.registrarCita(
                        Integer.parseInt(txtIdCita.getText()),
                        txtMotivo.getText(),
                        idMedico,
                        idPaciente,
                        idTurno
                );

                /*limpiarControles();*/
                listarCitas();
                JOptionPane.showMessageDialog(this, "Cita registrada con éxito");

                jdPagoCita objPago = new jdPagoCita((Frame) SwingUtilities.getWindowAncestor(this), true,
                        objCita.obtenerIdPacienteXDNI(txtDniPaciente.getText()), txtDniPaciente.getText(), idCita,
                        fecha, motivo, precio
                );
                objPago.setLocationRelativeTo(this);
                objPago.setVisible(true);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar una cita -->" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if (txtIdCita.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el código de la cita.");
                return;
            }

            ResultSet rsCita = objCita.buscarCita(Integer.parseInt(txtIdCita.getText()));

            if (rsCita.next()) {
                txtMotivo.setText(rsCita.getString("motivo"));

                txtDniMedico.setText(rsCita.getString("dniMedico"));
                txtMedico.setText(rsCita.getString("nombreMedico"));

                txtDniPaciente.setText(rsCita.getString("dniPaciente"));
                txtPaciente.setText(rsCita.getString("nombrePaciente"));
                txtEspecialidad.setText(rsCita.getString("especialidades"));

                jdFechaCita.setDate(rsCita.getDate("fecha"));
                spnHoraCita.setValue(rsCita.getTime("hora_inicio"));

                listarTurnoDeCita(Integer.parseInt(txtIdCita.getText()));
            } else {
                JOptionPane.showMessageDialog(this, "El Código de cita no existe.");
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la cita --> " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitaMouseClicked
        // TODO add your handling code here:
        txtIdCita.setText(String.valueOf(tblCita.getValueAt(tblCita.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
    }//GEN-LAST:event_tblCitaMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtIdCita.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a eliminar");
            } else {
                int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea eliminar esta cita? id: " + txtIdCita.getText() + "?", "Pregunta",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");

                if (opcion == 0) {
                    objCita.eliminarCita(Integer.valueOf(txtIdCita.getText()));
                    limpiarControles();
                    listarCitas();
                    JOptionPane.showMessageDialog(this, " Cita eliminada ");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar cita --> " + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtDniMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniMedicoKeyReleased
        try {
            String dni = txtDniMedico.getText().trim();
            if (!dni.isEmpty()) {
                ResultSet rsMedico = objMedico.buscarMedicoPorDNI(dni);

                if (rsMedico.next()) {
                    txtMedico.setText(rsMedico.getString("nombre_completo"));
                    txtEspecialidad.setText(rsMedico.getString("especialidades"));
                    listarTurnosMedico();
                } else {
                    txtMedico.setText("");
                    txtEspecialidad.setText("");
                    limpiarTablaTurnos();
                }
            } else {
                txtMedico.setText("");
                txtEspecialidad.setText("");
                limpiarTablaTurnos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_txtDniMedicoKeyReleased

    private void tblTurnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTurnosMouseClicked
        // TODO add your handling code here:
        int fila = tblTurnos.getSelectedRow();
        if (fila >= 0) {
            try {
                String fecha = tblTurnos.getValueAt(fila, 1).toString();
                String horaInicio = tblTurnos.getValueAt(fila, 2).toString();
                String horaFin = tblTurnos.getValueAt(fila, 3).toString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaTurno = formatoFecha.parse(fecha);
                jdFechaCita.setDate(fechaTurno);
                // Convertir la hora a java.util.Date
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                java.util.Date horaInicioDate = formatoHora.parse(horaInicio);

                // Setear el valor en los spinners
                spnHoraCita.setValue(horaInicioDate);
                JOptionPane.showMessageDialog(this, "Horario de cita registrado, la atención comienza desde " + horaInicio + " hasta " + horaFin);
            } catch (HeadlessException | ParseException e) {
                JOptionPane.showMessageDialog(this, "Error al setear la fecha: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un turno disponible.");
        }
    }//GEN-LAST:event_tblTurnosMouseClicked

    private void txtDniPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPacienteKeyReleased
        try {
            String dni = txtDniPaciente.getText().trim();
            if (!dni.isEmpty()) {
                ResultSet rsPaciente = objPaciente.buscarPacientePorDNI2(dni);
                if (rsPaciente.next()) {
                    txtPaciente.setText(rsPaciente.getString("nombre_completo"));
                } else {
                    txtPaciente.setText("");
                }
            } else {
                txtPaciente.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_txtDniPacienteKeyReleased

    private void txtIdCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCitaKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
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
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPacienteKeyTyped

    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMotivoKeyTyped

    private void txtMedicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicoKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMedicoKeyTyped

    private void txtEspecialidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecialidadKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEspecialidadKeyTyped

    private void txtDniMedicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniMedicoKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;
        boolean punto = key == 46;
        String textoActual = ((javax.swing.JTextField) evt.getSource()).getText();
        if (punto && textoActual.contains(".")) {
            evt.consume();
            return;
        }
        if (!(numeros || punto)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDniMedicoKeyTyped

    private void spnHoraCitaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnHoraCitaKeyTyped

        char key = evt.getKeyChar();
        boolean permitido = (key >= '0' && key <= '9') || key == ':';

        if (!permitido) {
            evt.consume();
        }
    }//GEN-LAST:event_spnHoraCitaKeyTyped

    private void spnHoraCitaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spnHoraCitaFocusLost
        // Obtener la hora seleccionada del spinner
        String horaSeleccionada = spnHoraCita.getValue().toString();

        int filaSeleccionada = tblTurnos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un turno de la tabla.");
            return;
        }
        String horaInicio = tblTurnos.getValueAt(filaSeleccionada, 2).toString();
        String horaFin = tblTurnos.getValueAt(filaSeleccionada, 3).toString();

        try {
            if (!validarHoraCita(horaSeleccionada, horaInicio, horaFin)) {
                JOptionPane.showMessageDialog(this, "La hora ingresada no está dentro del rango permitido.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al validar la hora: " + e.getMessage());
        }
    }//GEN-LAST:event_spnHoraCitaFocusLost

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
            jdListaPacientes obj = new jdListaPacientes((Frame) SwingUtilities.getWindowAncestor(this), true);
            obj.setLocationRelativeTo(this);
            obj.setVisible(true);
            String dniSeleccionado = obj.getDniDelPaciente();
            if (!dniSeleccionado.isEmpty()) {
                txtDniPaciente.setText(dniSeleccionado);
                txtDniPacienteKeyReleased(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnBusquedaAvanzadaMédicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaMédicoActionPerformed
        // TODO add your handling code here:
        try {
            jdBusqAvanzMedicosCita obj = new jdBusqAvanzMedicosCita((Frame) SwingUtilities.getWindowAncestor(this), true);
            obj.setLocationRelativeTo(this);
            obj.setVisible(true);
            String dniSeleccionado = obj.getDniMedico();
            txtDniMedico.setText(dniSeleccionado);
            if (!dniSeleccionado.isEmpty()) {
                txtDniMedico.setText(dniSeleccionado);
                txtDniMedicoKeyReleased(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnBusquedaAvanzadaMédicoActionPerformed

    private void btnAgregarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTurnoActionPerformed
        jdMantenimientoTurnoMedico obj = new jdMantenimientoTurnoMedico((Frame) SwingUtilities.getWindowAncestor(this), true);
        obj.setLocationRelativeTo(this);
        obj.setVisible(true);
    }//GEN-LAST:event_btnAgregarTurnoActionPerformed
    private boolean validarHoraCita(String horaSeleccionada, String horaInicio, String horaFin) throws Exception {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        java.util.Date horaSel = formatoHora.parse(horaSeleccionada);
        java.util.Date horaIni = formatoHora.parse(horaInicio);
        java.util.Date horaFn = formatoHora.parse(horaFin);

        return (horaSel.equals(horaIni) || horaSel.after(horaIni)) && (horaSel.before(horaFn) || horaSel.equals(horaFn));
    }

    private void limpiarTablaTurnos() {
        // Crear un modelo vacío con las mismas columnas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Turno");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");

        tblTurnos.setModel(modelo);
    }

    private boolean validarCampos() {
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTurno;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBusquedaAvanzadaMédico;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser jdFechaCita;
    private javax.swing.JSpinner spnHoraCita;
    private javax.swing.JTable tblCita;
    private javax.swing.JTable tblTurnos;
    private javax.swing.JTextField txtDniMedico;
    private javax.swing.JTextField txtDniPaciente;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtIdCita;
    private javax.swing.JTextField txtMedico;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
