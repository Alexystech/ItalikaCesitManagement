/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itsx.slasher.italikacesitmanagement.view;

import com.itsx.slasher.italikacesitmanagement.model.Vehicle;
import com.itsx.slasher.italikacesitmanagement.service.VehicleService;

import java.awt.*;

/**
 *
 * @author defin
 */
public class VehicleDialog extends javax.swing.JDialog {

    private VehicleService vehicleService;

    /**
     * Creates new form VehicleDialog
     */
    public VehicleDialog(VehicleService vehicleService, Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Vehiculo");
        setResizable(true);
        setLocationRelativeTo(null);

        this.vehicleService = vehicleService;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        plaqueVehicleDialogField = new javax.swing.JTextField();
        brandVehicleDialogField = new javax.swing.JTextField();
        modelVehicleDialogField = new javax.swing.JTextField();
        updateVehicleDialogButton = new javax.swing.JButton();
        deleteVehicleDialogButton = new javax.swing.JButton();
        brandVehicleDialogValidation = new javax.swing.JLabel();
        modelVehicleDialogValidation = new javax.swing.JLabel();
        yearVehicleDialogValidation = new javax.swing.JLabel();
        yearVehicleDialogCBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVehicleDialog = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Vehiculo");

        jLabel2.setText("placa");

        jLabel3.setText("Marca");

        jLabel4.setText("Modelo");

        jLabel5.setText("A??o");

        plaqueVehicleDialogField.setEnabled(false);

        updateVehicleDialogButton.setText("Actualizar");

        deleteVehicleDialogButton.setText("Eliminar");
        deleteVehicleDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVehicleDialogButtonActionPerformed(evt);
            }
        });

        yearVehicleDialogCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(updateVehicleDialogButton, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(deleteVehicleDialogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(modelVehicleDialogField)
                                .addComponent(plaqueVehicleDialogField)
                                .addComponent(modelVehicleDialogValidation)
                                .addComponent(yearVehicleDialogValidation)
                                .addComponent(brandVehicleDialogField)
                                .addComponent(brandVehicleDialogValidation)
                                .addComponent(yearVehicleDialogCBox, 0, 191, Short.MAX_VALUE)))))
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plaqueVehicleDialogField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandVehicleDialogField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandVehicleDialogValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelVehicleDialogField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modelVehicleDialogValidation)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(yearVehicleDialogCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearVehicleDialogValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateVehicleDialogButton)
                    .addComponent(deleteVehicleDialogButton))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        tableVehicleDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Marca", "Modelo", "A??o"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVehicleDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVehicleDialogMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableVehicleDialog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteVehicleDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVehicleDialogButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteVehicleDialogButtonActionPerformed

    private void tableVehicleDialogMouseClicked(java.awt.event.MouseEvent evt) {

        int row = tableVehicleDialog.getSelectedRow();
        String plaqueSelected = tableVehicleDialog.getValueAt(row, 0).toString();

        Vehicle selectedVehicle = vehicleService.getVehicleByPlaque(plaqueSelected);

        plaqueVehicleDialogField.setText(selectedVehicle.getPlaque());
        brandVehicleDialogField.setText(selectedVehicle.getBrand());
        modelVehicleDialogField.setText(selectedVehicle.getModel());
        yearVehicleDialogCBox.setSelectedIndex(selectedIndexOfYearCBox(selectedVehicle.getYear()));

    }

    private int selectedIndexOfYearCBox(int year) {

        int selectedIndex = 0;

        for (int index = 0; index < yearVehicleDialogCBox.getItemCount(); index++) {
            if ( year == Integer.parseInt(yearVehicleDialogCBox.getItemAt(index) ) ) {
                selectedIndex = index;
                break;
            }
        }

        return selectedIndex;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VehicleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VehicleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VehicleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VehicleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VehicleDialog().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField brandVehicleDialogField;
    public javax.swing.JLabel brandVehicleDialogValidation;
    public javax.swing.JButton deleteVehicleDialogButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField modelVehicleDialogField;
    public javax.swing.JLabel modelVehicleDialogValidation;
    public javax.swing.JTextField plaqueVehicleDialogField;
    public javax.swing.JTable tableVehicleDialog;
    public javax.swing.JButton updateVehicleDialogButton;
    public javax.swing.JComboBox<String> yearVehicleDialogCBox;
    public javax.swing.JLabel yearVehicleDialogValidation;
    // End of variables declaration//GEN-END:variables
}
