/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.clsEnfermedades;
import Capa_Negocio.clsPaciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author MendozaGastulo_Tadeo
 */
public class jdMantenimientoPaciente extends javax.swing.JDialog {

    clsPaciente objPaciente = new clsPaciente();
    clsEnfermedades objEnfermedades = new clsEnfermedades();

    public jdMantenimientoPaciente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listEnfermedades.setModel(new DefaultListModel<>());
        listEnfermedadesSeleccionadas.setModel(new DefaultListModel<>());
        listarPacientes();
        cargarListaEnfermedades();
        listEnfermedades.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    private void listarPacientes() {
        ResultSet rsPaciente = null;
        Integer cont = 0;
        String estado = "";
        DefaultTableModel modeloC = new DefaultTableModel();
        modeloC.addColumn("Código");
        modeloC.addColumn("Nombres");
        modeloC.addColumn("Apellido Paterno");
        modeloC.addColumn("Apellido Materno");
        modeloC.addColumn("DNI");
        modeloC.addColumn("Peso");
        modeloC.addColumn("Altura");
        modeloC.addColumn("Fecha de nacimiento");
        modeloC.addColumn("Teléfono");
        modeloC.addColumn("Tipo de sangre");
        modeloC.addColumn("Vigencia");
        tblPaciente.setModel(modeloC);

        try {
            rsPaciente = objPaciente.listarPacientes();
            while (rsPaciente.next()) {
                if (rsPaciente.getString("estado").equals("t")) {
                    estado = "Vigente";
                } else {
                    estado = "No Vigente";
                }
                modeloC.addRow(new Object[]{rsPaciente.getInt("idPaciente"),
                    rsPaciente.getString("nombres"), rsPaciente.getString("apellidoPaterno"),
                    rsPaciente.getString("apellidoMaterno"), rsPaciente.getString("dni"), rsPaciente.getDouble("peso") + " Kg",
                    rsPaciente.getDouble("altura") + " Mts", rsPaciente.getString("fecha_nacimiento"), rsPaciente.getString("telefono"),
                    rsPaciente.getString("tipoSangre"), estado});
                cont += 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al consultar pacientes -->" + e.getMessage());
        }
    }

    private void limpiarControles() {
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApePa.setText("");
        txtApeMa.setText("");
        txtDNI.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        jdFechaNac.setDate(null);
        txtTelefono.setText("");
        cboTipoSangre.setSelectedIndex(0);
        chkEstado.setSelected(false);
        txtCodigo.requestFocus();
        ((DefaultListModel<String>) listEnfermedadesSeleccionadas.getModel()).clear();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        txtTelefono = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtApePa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtApeMa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdFechaNac = new com.toedter.calendar.JDateChooser();
        cboTipoSangre = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaciente = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listEnfermedadesSeleccionadas = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listEnfermedades = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        btnNuevaEnfermedad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MANTENIMIENTO PACIENTE");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Código:");

        jLabel2.setText("DNI:");

        jLabel3.setText("Nombres:");

        jLabel4.setText("Teléfono:");

        jLabel6.setText("Vigencia");

        chkEstado.setText("No vigente");
        chkEstado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadoStateChanged(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/1622836_checkmark_done_explore_find_magnifier_icon.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel11.setText("Apellido Paterno:");

        txtApePa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApePaKeyTyped(evt);
            }
        });

        jLabel12.setText("Apellido Materno:");

        txtApeMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeMaKeyTyped(evt);
            }
        });

        jLabel8.setText("Peso:");

        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        jLabel13.setText("Altura:");

        txtAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaKeyTyped(evt);
            }
        });

        jLabel14.setText("Fecha Nacimiento:");

        jLabel7.setText("Tipo de sangre:");

        jLabel5.setText("Kg");

        jLabel9.setText("Mts");

        cboTipoSangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombres)
                    .addComponent(txtApePa)
                    .addComponent(txtApeMa)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAltura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)))
                    .addComponent(chkEstado)
                    .addComponent(jdFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDNI)
                    .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtApePa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtApeMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jdFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cboTipoSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(chkEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btnModificar.setBackground(new java.awt.Color(153, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6673743_health_hospital_injection_medical_medicine_icon.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnDarBaja.setBackground(new java.awt.Color(153, 255, 255));
        btnDarBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/5929160_avatar_doctor_hospital_man_medical_icon.png"))); // NOI18N
        btnDarBaja.setText("Dar Baja ");
        btnDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBajaActionPerformed(evt);
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
        btnLimpiar.setText("LIMPIAR");
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
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDarBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnDarBaja)
                .addGap(27, 27, 27)
                .addComponent(btnEliminar)
                .addGap(28, 28, 28)
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPaciente);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));

        listEnfermedadesSeleccionadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listEnfermedadesSeleccionadasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listEnfermedadesSeleccionadas);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6141444_contagious_coronavirus_covid19_disease_sneeze_icon.png"))); // NOI18N
        jLabel15.setText("ENFERMEDADES DEL PACIENTE");

        listEnfermedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listEnfermedadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listEnfermedades);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/6022670_checking_coronavirus_covid_dirty_disease_icon.png"))); // NOI18N
        jLabel16.setText("ENFERMEDADES ");

        btnNuevaEnfermedad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/8725908_file_edit_alt_icon (2).png"))); // NOI18N
        btnNuevaEnfermedad.setText("NUEVA");
        btnNuevaEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaEnfermedadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btnNuevaEnfermedad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevaEnfermedad)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //listarTipoClientes();
        listarPacientes();
        cargarListaEnfermedades();
    }//GEN-LAST:event_formWindowActivated

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            if (btnNuevo.getText().equals("Nuevo")) {
                btnNuevo.setText("Guardar");
                JOptionPane.showMessageDialog(this, "Complete los campos para registrar al paciente.");
                limpiarControles();
                txtCodigo.setEditable(false);
                txtCodigo.setText(objPaciente.generarCodigoPaciente().toString());
                txtNombres.requestFocus();
                cargarListaEnfermedades();
            } else {
                if (validarCamposPaciente()) {
                    btnNuevo.setText("Nuevo");
                    registrarPaciente();
                    limpiarControles();
                    listarPacientes();
                    txtCodigo.setEditable(true);
                    JOptionPane.showMessageDialog(this, "Paciente registrado con éxito.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void registrarPaciente() throws Exception {
        java.sql.Date fechaNacimientoSQL = new java.sql.Date(jdFechaNac.getDate().getTime());

        objPaciente.registrar(Integer.valueOf(txtCodigo.getText()),
                txtNombres.getText(),
                txtApePa.getText(),
                txtApeMa.getText(),
                txtDNI.getText(),
                Float.parseFloat(txtPeso.getText()),
                Float.parseFloat(txtAltura.getText()),
                fechaNacimientoSQL,
                txtTelefono.getText(),
                cboTipoSangre.getSelectedItem().toString(),
                chkEstado.isSelected()
        );
        DefaultListModel<String> modelSeleccionadas = (DefaultListModel<String>) listEnfermedadesSeleccionadas.getModel();
        List<Integer> enfermedadesSeleccionadas = new ArrayList<>();

        for (int i = 0; i < modelSeleccionadas.size(); i++) {
            String enfermedadNombre = modelSeleccionadas.getElementAt(i).trim();
            int idEnfermedad = objEnfermedades.obtenerIdsEnfermedadesSeleccionadas(enfermedadNombre); // Usamos el método proporcionado
            if (idEnfermedad == 0) {
                throw new Exception("La enfermedad '" + enfermedadNombre + "' no existe en la base de datos.");
            }
            enfermedadesSeleccionadas.add(idEnfermedad);
        }

        objPaciente.registrarEnfermedadesPaciente(Integer.parseInt(txtCodigo.getText()), enfermedadesSeleccionadas);
    }

    private boolean validarCamposPaciente() {
        if (txtNombres.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar el nombre del paciente");
            return false;
        }
        if (txtApePa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar el apellido paterno del paciente");
            return false;
        }
        if (txtApeMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar el apellido materno del paciente");
            return false;
        }
        if (txtDNI.getText().isEmpty() || txtDNI.getText().length() != 8) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un DNI válido (8 dígitos).");
            return false;
        }
        if (txtPeso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el peso del paciente");
            return false;
        }
        if (txtAltura.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la altura del paciente");
            return false;
        }
        double peso = Double.parseDouble(txtPeso.getText());
        double altura = Double.parseDouble(txtAltura.getText());

        if (peso <= 0 || altura <= 0) {
            JOptionPane.showMessageDialog(this, "El peso y la altura deben ser valores positivos.");
            return false;
        }

        if (peso < 20 || peso > 300) { 
            JOptionPane.showMessageDialog(this, "El peso ingresado está fuera de rango.");
            return false;
        }
        if (altura < 1.20 || altura > 2.50) { 
            JOptionPane.showMessageDialog(this, "La altura ingresada está fuera de rango.");
            return false;
        }
        
        if (jdFechaNac.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de nacimiento.");
            return false;
        }
        Date fechaActual = new Date();

        Date fechaNacimiento = jdFechaNac.getDate();

        if (fechaNacimiento != null && fechaNacimiento.after(fechaActual)) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser posterior a la fecha actual.");
            return false;
        }
        if (txtTelefono.getText().isEmpty() || txtTelefono.getText().length() < 9) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un número de teléfono válido.");
            return false;
        }

        return true;
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ResultSet rs = null;
        try {
            if (txtCodigo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código para buscar");
            } else {
                rs = objPaciente.buscarPaciente(Integer.valueOf(txtCodigo.getText()));
                if (rs.next()) {
                    txtCodigo.setText(rs.getString("idPaciente"));
                    txtNombres.setText(rs.getString("nombres"));
                    txtApePa.setText(rs.getString("apellidoPaterno"));
                    txtApeMa.setText(rs.getString("apellidoMaterno"));
                    txtDNI.setText(rs.getString("dni"));
                    txtPeso.setText(rs.getString("peso"));
                    txtAltura.setText(rs.getString("altura"));
                    jdFechaNac.setDate(rs.getDate("fecha_nacimiento"));
                    txtTelefono.setText(rs.getString("telefono"));
                    cboTipoSangre.setSelectedItem(rs.getString("tipoSangre"));
                    chkEstado.setSelected(rs.getBoolean("estado"));
                    cargarEnfermedadesPaciente(Integer.parseInt(txtCodigo.getText()));
                    rs.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Código de paciente no existe");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacienteMouseClicked
        // TODO add your handling code here:
        txtCodigo.setText(String.valueOf(tblPaciente.getValueAt(tblPaciente.getSelectedRow(), 0)));
        btnBuscarActionPerformed(null);
    }//GEN-LAST:event_tblPacienteMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            if (txtCodigo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a eliminar");
            } else {
                int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea eliminar este paciente : " + txtNombres.getText() + "?", "Pregunta",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");

                if (opcion == 0) {
                    objPaciente.eliminarPaciente(Integer.valueOf(txtCodigo.getText()));
                    limpiarControles();
                    listarPacientes();
                    JOptionPane.showMessageDialog(this, "Paciente con Id: " + txtCodigo.getText() + " eliminado");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBajaActionPerformed
        // TODO add your handling code here:
        try {
            if (txtCodigo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código a eliminar");
            } else {
                int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea dar de baja este paciente: " + txtNombres.getText() + "?", "Pregunta",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");

                if (opcion == 0) {
                    objPaciente.darBajaPaciente(Integer.valueOf(txtCodigo.getText()));
                    limpiarControles();
                    listarPacientes();
                    JOptionPane.showMessageDialog(this, "Paciente con Id: " + txtCodigo.getText() + " dado de baja");
                }
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnDarBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un ID para modificar.");
        } else {
            try {
                int opcion = JOptionPane.showOptionDialog(null,
                        "¿Está seguro de que desea modificar este paciente: " + txtNombres.getText() + "?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Sí", "No"},
                        "Sí");

                if (opcion == JOptionPane.YES_OPTION) {
                    java.sql.Date fechaNacimientoSQL = new java.sql.Date(jdFechaNac.getDate().getTime());

                    DefaultListModel<String> modelSeleccionadas
                            = (DefaultListModel<String>) listEnfermedadesSeleccionadas.getModel();
                    List<Integer> enfermedadesSeleccionadas = new ArrayList<>();

                    for (int i = 0; i < modelSeleccionadas.size(); i++) {
                        String enfermedadNombre = modelSeleccionadas.getElementAt(i).trim();
                        int idEnfermedad = objEnfermedades.obtenerIdsEnfermedadesSeleccionadas(enfermedadNombre); // Usamos el método
                        if (idEnfermedad == 0) {
                            throw new Exception("La enfermedad '" + enfermedadNombre + "' no existe en la base de datos.");
                        }
                        enfermedadesSeleccionadas.add(idEnfermedad);
                    }

                    objPaciente.modificar(
                            Integer.valueOf(txtCodigo.getText()),
                            txtNombres.getText().trim(),
                            txtApePa.getText().trim(),
                            txtApeMa.getText().trim(),
                            txtDNI.getText().trim(),
                            Float.parseFloat(txtPeso.getText()),
                            Float.parseFloat(txtAltura.getText()),
                            fechaNacimientoSQL,
                            txtTelefono.getText().trim(),
                            cboTipoSangre.getSelectedItem().toString(),
                            chkEstado.isSelected(),
                            enfermedadesSeleccionadas
                    );

                    JOptionPane.showMessageDialog(this,
                            "Paciente con Id: " + txtCodigo.getText() + " modificado correctamente.");
                    limpiarControles();
                    listarPacientes();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error al modificar el paciente: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        listarPacientes();
    }//GEN-LAST:event_formWindowOpened

    private void chkEstadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadoStateChanged
        // TODO add your handling code here:
        if (chkEstado.isSelected()) {
            chkEstado.setText("Vigente");
        } else {
            chkEstado.setText("No vigente");
        }
    }//GEN-LAST:event_chkEstadoStateChanged

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarControles();
        cargarListaEnfermedades();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnNuevaEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaEnfermedadActionPerformed
        // TODO add your handling code here:
        jdMantenimientoEnfermedades obj;
        try {
            obj = new jdMantenimientoEnfermedades(null, true);
            obj.setLocationRelativeTo(this);
            obj.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(jdMantenimientoPaciente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnNuevaEnfermedadActionPerformed

    private void listEnfermedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEnfermedadesMouseClicked
        String enfermedad = listEnfermedades.getSelectedValue();

        if (enfermedad != null) {
            DefaultListModel<String> modelDisponibles = (DefaultListModel<String>) listEnfermedades.getModel();
            DefaultListModel<String> modelSeleccionadas = (DefaultListModel<String>) listEnfermedadesSeleccionadas.getModel();

            if (modelSeleccionadas.contains(enfermedad)) {
                JOptionPane.showMessageDialog(this, "El paciente ya tiene registrada esta enfermedad.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                modelDisponibles.removeElement(enfermedad);
                modelSeleccionadas.addElement(enfermedad);
            }
        }
    }//GEN-LAST:event_listEnfermedadesMouseClicked

    private void listEnfermedadesSeleccionadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEnfermedadesSeleccionadasMouseClicked

        String enfermedad = listEnfermedadesSeleccionadas.getSelectedValue();

        if (enfermedad != null) {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea quitar esta enfermedad?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                DefaultListModel<String> modelDisponibles = (DefaultListModel<String>) listEnfermedades.getModel();
                DefaultListModel<String> modelSeleccionadas = (DefaultListModel<String>) listEnfermedadesSeleccionadas.getModel();

                modelSeleccionadas.removeElement(enfermedad);
                if (!modelDisponibles.contains(enfermedad)) {
                    modelDisponibles.addElement(enfermedad);
                }
            }
        }
    }//GEN-LAST:event_listEnfermedadesSeleccionadasMouseClicked

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txtCodigo.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char key = evt.getKeyChar();
        if (!Character.isDigit(key) && key != '+') {
            evt.consume();
        }
        if (key == '+' && txtTelefono.getText().contains("+")) {
            evt.consume();
        }
        if (txtTelefono.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        char key = evt.getKeyChar();

        if (!Character.isDigit(key)) {
            evt.consume();
        }

        if (txtDNI.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtApePaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApePaKeyTyped

    private void txtApeMaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApeMaKeyTyped

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
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
        if (txtPeso.getText().length() >= 7) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        // TODO add your handling code here:
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
        if (txtAltura.getText().length() >= 4) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAlturaKeyTyped

    private void cargarListaEnfermedades() {
        ResultSet rs = null;
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            rs = objEnfermedades.listarEnfermedades();
            while (rs.next()) {
                String enfermedad = rs.getString("nombre");
                model.addElement(enfermedad);
            }
            listEnfermedades.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar enfermedades: " + e.getMessage());
        }
    }

    private void cargarEnfermedadesPaciente(int idPaciente) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listEnfermedadesSeleccionadas.setModel(modelo);

        try {
            ResultSet rs = objPaciente.listaEnfermedadesxPaciente(idPaciente);
            while (rs.next()) {
                String enfermedad = rs.getString("nombre");
                modelo.addElement(enfermedad);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar enfermedades del paciente: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevaEnfermedad;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboTipoSangre;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdFechaNac;
    private javax.swing.JList<String> listEnfermedades;
    private javax.swing.JList<String> listEnfermedadesSeleccionadas;
    private javax.swing.JTable tblPaciente;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtApeMa;
    private javax.swing.JTextField txtApePa;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
