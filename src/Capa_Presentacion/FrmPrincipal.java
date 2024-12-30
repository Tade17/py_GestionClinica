/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Capa_Presentacion;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    private String tipoUsuario;

    public FrmPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(getIconImage());
        actualizarFecha();
        actualizarHora();
        txtFecha.setEditable(false);
        txtHora.setEditable(false);

    }

    private void configurarVistaSegunUsuario() {
        if (tipoUsuario.equalsIgnoreCase("administrador")) {

            btnCerrar.setVisible(true);
            btnCambiar.setVisible(true);
            btnMisCitas.setVisible(false);
            btnAgendarCita.setVisible(true);
            btnPaciente.setVisible(true);
            btnMedico.setVisible(true);
            btnPagarServicio.setVisible(true);

            mnuMantenimiento.setVisible(true);
            mnuReportes.setVisible(true);
            mnuProcedimientos.setVisible(true);
            mnuRegistrar.setVisible(true);

        } else if (tipoUsuario.equalsIgnoreCase("medico")) {
            // Configuración para el médico

            btnCambiar.setVisible(true);
            btnCerrar.setVisible(true);
            btnMisCitas.setVisible(true);
            btnPaciente.setVisible(false);
            btnMedico.setVisible(false);
            btnAgendarCita.setVisible(false);
            btnPagarServicio.setVisible(false);

            mnuMantenimiento.setVisible(false);
            mnuReportes.setVisible(false);
            mnuProcedimientos.setVisible(false);
            mnuRegistrar.setVisible(false);

        } else if (tipoUsuario.equalsIgnoreCase("paciente")) {
            // Configuración para el paciente
            btnCerrar.setVisible(true);
            btnCambiar.setVisible(true);
            btnMisCitas.setVisible(false);
            btnAgendarCita.setVisible(false);
            btnPaciente.setVisible(false);
            btnMedico.setVisible(false);
            btnPagarServicio.setVisible(false);

            mnuMantenimiento.setVisible(false);
            mnuReportes.setVisible(false);
            mnuProcedimientos.setVisible(false);

        }
    }

    private void actualizarFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = ahora.format(formatoFecha);
        txtFecha.setText(fechaFormateada);
    }

    private void actualizarHora() {
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaFormateada = ahora.format(formatoHora);
                txtHora.setText(horaFormateada);
            }
        };
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    }

    public void setUsuarioLogueado(String nombreCompleto) {
        lblNombreCompleto.setText(nombreCompleto);
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        configurarVistaSegunUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        btnCerrar = new javax.swing.JButton();
        btnCambiar = new javax.swing.JButton();
        btnAgendarCita = new javax.swing.JButton();
        btnMisCitas = new javax.swing.JButton();
        btnPaciente = new javax.swing.JButton();
        btnMedico = new javax.swing.JButton();
        btnPagarServicio = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        lblNombreCompleto = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuRegistrar = new javax.swing.JMenu();
        mnuRegistrarAdministrador = new javax.swing.JMenuItem();
        mnuRegistrarUsuario = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mnuManEspecialidad = new javax.swing.JMenuItem();
        mnuManServicio = new javax.swing.JMenuItem();
        mnuManTurno = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnuMantEnfermedades = new javax.swing.JMenuItem();
        mnuReportes = new javax.swing.JMenu();
        mnuListadoMedicoEspecialidad = new javax.swing.JMenuItem();
        mnuListadoCitasMedico = new javax.swing.JMenuItem();
        mnuPacientesActivos = new javax.swing.JMenuItem();
        mnuAdministrativoSolicitud = new javax.swing.JMenuItem();
        mnuProcedimientos = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenuItem2.setText("jMenuItem2");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENÚ PRINCIPAL");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(0, 204, 204));
        jToolBar1.setRollover(true);

        btnCerrar.setBackground(new java.awt.Color(153, 255, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/2305619_door_exit_logout_open_out_icon.png"))); // NOI18N
        btnCerrar.setText("Salir");
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCerrar);

        btnCambiar.setBackground(new java.awt.Color(153, 255, 255));
        btnCambiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/8962571_users_group_team_people_man_icon.png"))); // NOI18N
        btnCambiar.setText("Cambiar");
        btnCambiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCambiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCambiar);

        btnAgendarCita.setBackground(new java.awt.Color(153, 255, 255));
        btnAgendarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5736343_appointment_business_calendar_date_event_icon.png"))); // NOI18N
        btnAgendarCita.setText("Agendar Cita");
        btnAgendarCita.setFocusable(false);
        btnAgendarCita.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgendarCita.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarCitaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAgendarCita);

        btnMisCitas.setBackground(new java.awt.Color(153, 255, 255));
        btnMisCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1622836_checkmark_done_explore_find_magnifier_icon.png"))); // NOI18N
        btnMisCitas.setText("Mis Citas");
        btnMisCitas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMisCitas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMisCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisCitasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnMisCitas);

        btnPaciente.setBackground(new java.awt.Color(153, 255, 255));
        btnPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929144_avatar_man_mask_sick_corona virus_icon.png"))); // NOI18N
        btnPaciente.setText("Paciente");
        btnPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPaciente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPaciente);

        btnMedico.setBackground(new java.awt.Color(153, 255, 255));
        btnMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929159_avatar_doctor_health_hospital_medical_icon.png"))); // NOI18N
        btnMedico.setText("Médico");
        btnMedico.setFocusable(false);
        btnMedico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMedico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnMedico);

        btnPagarServicio.setBackground(new java.awt.Color(153, 255, 255));
        btnPagarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/4288566_banking_bill_card_credit_expenditure_icon.png"))); // NOI18N
        btnPagarServicio.setText("Pagar Servicio");
        btnPagarServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPagarServicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPagarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarServicioActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPagarServicio);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/46808_idea_user_icon.png"))); // NOI18N
        jLabel1.setText("Usuario:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5736343_appointment_business_calendar_date_event_icon.png"))); // NOI18N
        jLabel2.setText("Fecha:");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/3859125_app_hour_interface_time_timer_icon.png"))); // NOI18N
        jLabel3.setText("Hora:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        mnuRegistrar.setText("Registrar");

        mnuRegistrarAdministrador.setText("Registrar administrador");
        mnuRegistrarAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegistrarAdministradorActionPerformed(evt);
            }
        });
        mnuRegistrar.add(mnuRegistrarAdministrador);

        mnuRegistrarUsuario.setText("Registrar Nuevo Usuario");
        mnuRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegistrarUsuarioActionPerformed(evt);
            }
        });
        mnuRegistrar.add(mnuRegistrarUsuario);

        jMenuBar1.add(mnuRegistrar);

        mnuMantenimiento.setText("Mantenimiento");

        mnuManEspecialidad.setText("Especialidad");
        mnuManEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuManEspecialidadActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuManEspecialidad);

        mnuManServicio.setText("Servicio");
        mnuManServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuManServicioActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuManServicio);

        mnuManTurno.setText("Turno Médico");
        mnuManTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuManTurnoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuManTurno);

        jMenuItem3.setText("Metodo de pago");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(jMenuItem3);

        mnuMantEnfermedades.setText("Enfermedades");
        mnuMantEnfermedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantEnfermedadesActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantEnfermedades);

        jMenuBar1.add(mnuMantenimiento);

        mnuReportes.setText("Reportes");

        mnuListadoMedicoEspecialidad.setText("Listado de médicos por especialidad");
        mnuListadoMedicoEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuListadoMedicoEspecialidadActionPerformed(evt);
            }
        });
        mnuReportes.add(mnuListadoMedicoEspecialidad);

        mnuListadoCitasMedico.setText("Listado de citas por cada médico");
        mnuListadoCitasMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuListadoCitasMedicoActionPerformed(evt);
            }
        });
        mnuReportes.add(mnuListadoCitasMedico);

        mnuPacientesActivos.setText("Listado de pacientes activos");
        mnuPacientesActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPacientesActivosActionPerformed(evt);
            }
        });
        mnuReportes.add(mnuPacientesActivos);

        mnuAdministrativoSolicitud.setText("Listado administrativo de solicitudes e ingresos");
        mnuAdministrativoSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAdministrativoSolicitudActionPerformed(evt);
            }
        });
        mnuReportes.add(mnuAdministrativoSolicitud);

        jMenuBar1.add(mnuReportes);

        mnuProcedimientos.setText("Procedimientos");
        jMenuBar1.add(mnuProcedimientos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Recursos/hospital.png"));
        return retValue;
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jdInicioSesion objLogin = new jdInicioSesion(this, true);
        objLogin.setLocationRelativeTo(this);
        objLogin.setVisible(true);

    }//GEN-LAST:event_formWindowOpened

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacienteActionPerformed
        // TODO add your handling code here:
        jdMantenimientoPaciente objForm = new jdMantenimientoPaciente(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_btnPacienteActionPerformed

    private void btnMisCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisCitasActionPerformed
        // TODO add your handling code here:
        jdCita_Medico objForm;
        try {
            objForm = new jdCita_Medico(this, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnMisCitasActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        // TODO add your handling code here:
        jdInicioSesion objLogin = new jdInicioSesion(this, true);
        objLogin.setLocationRelativeTo(this);
        objLogin.setVisible(true);
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void btnPagarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarServicioActionPerformed
        // TODO add your handling code here:
        jdPagoServicio frm;
        try {
            frm = new jdPagoServicio(this, true);
            frm.setLocationRelativeTo(this);
            frm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnPagarServicioActionPerformed

    private void btnMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicoActionPerformed
        // TODO add your handling code here:
        jdMantenimientoMedico frm = new jdMantenimientoMedico(this, true);
        frm.setLocationRelativeTo(this);
        frm.setVisible(true);
    }//GEN-LAST:event_btnMedicoActionPerformed

    private void btnAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarCitaActionPerformed
        jdCita objForm;
        try {
            objForm = new jdCita(this, true);
            objForm.setLocationRelativeTo(this);
            objForm.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgendarCitaActionPerformed

    private void mnuMantEnfermedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantEnfermedadesActionPerformed
        // TODO add your handling code here:
        jdMantenimientoEnfermedades frm;
        try {
            frm = new jdMantenimientoEnfermedades(this, true);
            frm.setLocationRelativeTo(this);
            frm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuMantEnfermedadesActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

        try {
            jdMantenimientoMetodoPago frm = new jdMantenimientoMetodoPago(this, true);
            frm.setLocationRelativeTo(this);
            frm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuManTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuManTurnoActionPerformed
        // TODO add your handling code here:
        jdMantenimientoTurnoMedico objForm = new jdMantenimientoTurnoMedico(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuManTurnoActionPerformed

    private void mnuManServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuManServicioActionPerformed
        // TODO add your handling code here:
        jdMantenimientoServicio objForm = new jdMantenimientoServicio(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuManServicioActionPerformed

    private void mnuManEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuManEspecialidadActionPerformed
        // TODO add your handling code here:
        jdMantenimientoEspecialidad objForm = new jdMantenimientoEspecialidad(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuManEspecialidadActionPerformed

    private void mnuListadoMedicoEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuListadoMedicoEspecialidadActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuListadoMedicoEspecialidadActionPerformed

    private void mnuListadoCitasMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuListadoCitasMedicoActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_mnuListadoCitasMedicoActionPerformed

    private void mnuPacientesActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPacientesActivosActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuPacientesActivosActionPerformed

    private void mnuAdministrativoSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAdministrativoSolicitudActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_mnuAdministrativoSolicitudActionPerformed

    private void mnuRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegistrarUsuarioActionPerformed
        jdMantenimientoUsuarios objForm = new jdMantenimientoUsuarios(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuRegistrarUsuarioActionPerformed

    private void mnuRegistrarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegistrarAdministradorActionPerformed
        // TODO add your handling code here:
        jdMantenimientoAdministrador objForm = new jdMantenimientoAdministrador(this, true);
        objForm.setLocationRelativeTo(this);
        objForm.setVisible(true);
    }//GEN-LAST:event_mnuRegistrarAdministradorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendarCita;
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMedico;
    private javax.swing.JButton btnMisCitas;
    private javax.swing.JButton btnPaciente;
    private javax.swing.JButton btnPagarServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblNombreCompleto;
    private javax.swing.JMenuItem mnuAdministrativoSolicitud;
    private javax.swing.JMenuItem mnuListadoCitasMedico;
    private javax.swing.JMenuItem mnuListadoMedicoEspecialidad;
    private javax.swing.JMenuItem mnuManEspecialidad;
    private javax.swing.JMenuItem mnuManServicio;
    private javax.swing.JMenuItem mnuManTurno;
    private javax.swing.JMenuItem mnuMantEnfermedades;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenuItem mnuPacientesActivos;
    private javax.swing.JMenu mnuProcedimientos;
    private javax.swing.JMenu mnuRegistrar;
    private javax.swing.JMenuItem mnuRegistrarAdministrador;
    private javax.swing.JMenuItem mnuRegistrarUsuario;
    private javax.swing.JMenu mnuReportes;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
