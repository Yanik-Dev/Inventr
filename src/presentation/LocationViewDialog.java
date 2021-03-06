/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.MessageBox;
import entity.Location;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.LocationService;


/**
 *
 * @author Yanik
 */
public class LocationViewDialog extends javax.swing.JDialog {
    LocationService _dbService = null;
    List<Location> locations = new ArrayList<>();
    Location location = null;
    /**
     * Creates new form LocationView
     */
    public LocationViewDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    
    public void init(){
        ((DefaultTableModel) this.tableLocations.getModel()).setNumRows(0);
        this._dbService = new LocationService();
        DefaultTableModel model = (DefaultTableModel) this.tableLocations.getModel();
        model.setRowCount(0);
        this.locations = this._dbService.findAll(Location.class);
        for(Location location : locations){ 
            model.addRow(new Object[]{
                                       location.getLocationId(), 
                                       location.getName()
                                     });
        }
    }
    
    public void search(String search){
        this._dbService = new LocationService();
        ((DefaultTableModel) this.tableLocations.getModel()).setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) this.tableLocations.getModel();
        model.setRowCount(0);
        this.locations = this._dbService.search(search);
        for(Location location : locations){ 
            model.addRow(new Object[]{
                                       location.getLocationId(), 
                                       location.getName()
                                     });
        }
    }
    
    public void addLocation(){
        String value = JOptionPane.showInputDialog(this, "Location Name: ");
        if(value == null || value.trim().equals("")){
            MessageBox.errorBox(this, " - Invalid Entry", "Location name is required");
            return;
        }
        this.location = new Location();
        this.location.setName(value);
        boolean result = false;
        
        result = this._dbService.exist(location);
        if(result){
            MessageBox.infoBox(this, "- Result", "Location "+this.location.getName()+" already exist.");
            return;
        }
        
        result = this._dbService.create(this.location);
        if(result){
            MessageBox.infoBox(this, "- Result", "Location "+this.location.getName()+" was created successfully");
            this.init();
        }else{
            MessageBox.errorBox(this, "- Server Error", "Location "+this.location.getName()+" was unable to be created. Please contact admin.");
            //TODO : log error
        }
    }
    
    public void updateLocation(Location location){
        String value = JOptionPane.showInputDialog(this, "Location Name: ",location.getName());
        if(value == null || value.trim().equals("")){
            MessageBox.errorBox(this, " - Invalid Entry", "Location name is required");
            return;
        }
        this.location.setName(value);
        boolean result = false;
        result = this._dbService.update(this.location);
        if(result){
            MessageBox.infoBox(this, "- Result", "Location "+this.location.getName()+" was updated successfully");
            this.init();
        }else{
            MessageBox.errorBox(this, "- Server Error", "Location "+this.location.getName()+" was unable to be created. Please contact admin.");
            //TODO : log error
        }
    }
    
    public void deleteLocation(Location location){
        boolean result = false;
        result = this._dbService.delete(this.location);
        if(result){
            MessageBox.infoBox(this, "- Result", "Location "+this.location.getName()+" has been removed successfully");
            this.init();
        }else{
            MessageBox.errorBox(this, "- Server Error", "Location "+this.location.getName()+" was unable to be removed. Please contact admin.");
            //TODO : log error
        }
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
        jPanel2 = new javax.swing.JPanel();
        textBoxSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLocations = new javax.swing.JTable();
        buttonNew = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        buttonCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 241, 241));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        textBoxSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBoxSearchKeyPressed(evt);
            }
        });

        tableLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLocations);
        if (tableLocations.getColumnModel().getColumnCount() > 0) {
            tableLocations.getColumnModel().getColumn(0).setPreferredWidth(4);
        }

        buttonNew.setText("New");
        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(106, 106, 106));
        jLabel5.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEdit))
                    .addComponent(textBoxSearch)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCancel)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(textBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addContainerGap(390, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewActionPerformed
        this.addLocation();
    }//GEN-LAST:event_buttonNewActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        int row = this.tableLocations.getSelectedRow();
        System.out.println(row);
        Location locationToEdit = this.locations.get(row);
        if(locationToEdit != null){
            this.updateLocation(locationToEdit);
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void textBoxSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBoxSearchKeyPressed
        this.search(this.textBoxSearch.getText().trim());
    }//GEN-LAST:event_textBoxSearchKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonNew;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableLocations;
    private javax.swing.JTextField textBoxSearch;
    // End of variables declaration//GEN-END:variables
}
