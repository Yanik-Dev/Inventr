/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.FormHelper;
import common.MessageBox;
import constant.Status;
import entity.Rule;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import security.Security;
import services.DatabaseService;

/**
 *
 * @author Yanik
 */
public class UserFormDialog extends javax.swing.JDialog {

    List<Rule> _assignedRules = new ArrayList<>();
    List<Rule> _availableRules = new ArrayList<>();  
    User _user = null;
    DatabaseService _dbService = null;
    boolean isUpdate = false;
    DefaultListModel availableRulesList = new DefaultListModel();
    DefaultListModel assignedRulesList = new DefaultListModel();


    /**
     * Creates new form ItemDialog
     */
    public UserFormDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getRules();
    }
    
    /**
     * Creates new form ItemDialog
     */
    public UserFormDialog(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.getRules();
        this.setForm(user);
        this.isUpdate = true;
    }

    public void getRules(){
        this.availableRulesList.clear();
        this._dbService = new DatabaseService();
        this._availableRules = this._dbService.findAll(Rule.class);
        for(Rule category : this._availableRules){
            this.availableRulesList.addElement(category.getName());
        }
        this.listAvailableRules.setModel(this.availableRulesList);
    }
    
    public void assignRules(int index){
         Rule rule = this._availableRules.get(index);
         this._assignedRules.add(rule);
        ((DefaultListModel)this.listAssignedRules.getModel()).addElement(rule.getName());
        ((DefaultListModel)this.listAvailableRules.getModel()).remove(index);
        this._availableRules.remove(index);
        
    }
    
    public void removeRules(int index){
        Rule rule = this._assignedRules.get(index);
        this._availableRules.add(rule);
        ((DefaultListModel)this.listAvailableRules.getModel()).addElement(rule.getName());
        ((DefaultListModel)this.listAssignedRules.getModel()).remove(index);
        this._assignedRules.remove(index);
    }
    
    public void setForm(User user){
        this._user = user;
        this.textBoxFirstName.setText(user.getFirstname());
        this.textBoxUsername.setText(user.getLastname());
        this.textBoxUsername.setText(user.getUsername());

        for(Rule rule : user.getRules()){
            this._assignedRules.add(rule);
            this.assignedRulesList.addElement(rule.getName());
            
        }
        this.listAssignedRules.setModel(this.assignedRulesList);
    }
    
    public boolean validateForm(){
        String errorMessage = "";
        int counter = 0;
        
        if(this.textBoxFirstName.getText().trim().equals("")){
            counter++;
            errorMessage += counter+". First name is required\n";
        }
        if(this.textBoxLastName.getText().trim().equals("")){
            counter++;
            errorMessage += counter+". Last name is required\n";
        }
        
        if(this.textboxPassword.getText().trim().equals("") && this._user.getPassword().trim().equals("")){
            counter++;
            errorMessage += counter+". A Password is required\n";
        }else{
            if(!this.textboxPassword.getText().trim().equals(this.textboxConfirmPassword.getText().trim())){    
                counter++;
                errorMessage += counter+". Passwords are not equal\n";
            }
        }
        if(this.textBoxUsername.getText().trim().equals("")){
            counter++;
            errorMessage += counter+". Username is required\n";
        } 
        if(!this.checkBoxSuperUser.isSelected()){
            if(this.listAssignedRules.getModel().getSize() < 1){
                counter++;
                errorMessage += counter+". At lest one (1) rule should be assign to this user\n";
            }
        } 
        
        
  
        if(counter > 0){
            MessageBox.errorBox(this, "- Invalid Entries", errorMessage);
            return false;
        }
        return true;
    }
    
    public void clearForm(){
        FormHelper.clear(this.panelContent);
        ((DefaultListModel)this.listAssignedRules.getModel()).clear();
        this._assignedRules.clear();
        this.getRules();
    }
    
    public void setUser(){     
        if(this._user.getPassword().equals("")){
            String salt = Security.generateSalt();
            String password = this.textboxPassword.getText().trim();
            String hash = Security.get_SHA_512_SecurePassword(password, salt);
            this._user.setPassword(hash);
            this._user.setSalt(salt);
        }
        this._user.setFirstname(this.textBoxFirstName.getText().trim());
        this._user.setLastname(this.textBoxLastName.getText().trim());
        this._user.setUsername(this.textBoxUsername.getText().trim());
        this._user.setStatus(Status.ACTIVE);
        this._user.setRules(this._assignedRules);
    
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
        panelContent = new javax.swing.JPanel();
        textBoxFirstName = new javax.swing.JTextField();
        labelItemCode = new javax.swing.JLabel();
        textBoxUsername = new javax.swing.JTextField();
        labelItemName = new javax.swing.JLabel();
        textBoxLastName = new javax.swing.JTextField();
        labelItemCode1 = new javax.swing.JLabel();
        checkBoxSuperUser = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        listAvailableRules = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listAssignedRules = new javax.swing.JList<>();
        labelItemName2 = new javax.swing.JLabel();
        labelItemName3 = new javax.swing.JLabel();
        labelItemName4 = new javax.swing.JLabel();
        textboxPassword = new javax.swing.JPasswordField();
        textboxConfirmPassword = new javax.swing.JPasswordField();
        labelItemName5 = new javax.swing.JLabel();
        buttonAssign = new javax.swing.JButton();
        buttonUnassign = new javax.swing.JButton();
        buttonAction = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        buttonClear1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        textBoxFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode.setText("First Name");

        textBoxUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName.setText("Username");

        textBoxLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemCode1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode1.setText("Last Name");

        checkBoxSuperUser.setText("Super User");

        listAvailableRules.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listAvailableRules);

        listAssignedRules.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listAssignedRules);

        labelItemName2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName2.setText("Assigned Rules");

        labelItemName3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName3.setText("Available Rules");

        labelItemName4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName4.setText("Password");

        textboxPassword.setText("jPasswordField1");

        textboxConfirmPassword.setText("jPasswordField1");

        labelItemName5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName5.setText("Confirm Password");

        buttonAssign.setText("Assign");
        buttonAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAssignActionPerformed(evt);
            }
        });

        buttonUnassign.setText("Unassign");
        buttonUnassign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUnassignActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textBoxUsername)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkBoxSuperUser)
                                .addComponent(labelItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2)
                                        .addComponent(labelItemName3, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                    .addGap(13, 13, 13)
                                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buttonUnassign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonAssign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane3)
                                        .addComponent(labelItemName2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                                .addGroup(panelContentLayout.createSequentialGroup()
                                    .addComponent(textBoxFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textBoxLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelItemCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(labelItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(labelItemName4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(labelItemName5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(textboxPassword)
                        .addGap(18, 18, 18)
                        .addComponent(textboxConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelItemCode)
                    .addComponent(labelItemCode1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textBoxLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBoxUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelItemName4)
                    .addComponent(labelItemName5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textboxPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textboxConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkBoxSuperUser)
                .addGap(7, 7, 7)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelItemName2)
                    .addComponent(labelItemName3))
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(buttonAssign)
                        .addGap(18, 18, 18)
                        .addComponent(buttonUnassign)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        buttonAction.setText("Add");
        buttonAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionActionPerformed(evt);
            }
        });

        buttonClear.setText("Clear");
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        buttonClear1.setText("Cancel");
        buttonClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionActionPerformed
        if(!this.validateForm()){
            return;
        }

        this.setUser();
        boolean result = false;
        if(this.isUpdate){
            result = this._dbService.update(this._user);
            if(result){
                MessageBox.infoBox(this, "- Result", "User "+this._user.getUsername()+" updated successfully");
                this.setVisible(false);
                this.dispose();
            }else{
                MessageBox.errorBox(this, "- Server Error", "Item "+this._user.getUsername()+" was unable to be updated. Please contact admin.");
                //TODO : log error
            }
        }else{
            result = this._dbService.create(this._user);
            if(result){
                MessageBox.infoBox(this, "- Result", "User "+this._user.getUsername()+" created successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "User "+this._user.getUsername()+" was unable to be created. Please contact admin.");
                //TODO : log error
            }
        }
        if(result){ this.clearForm(); }
    }//GEN-LAST:event_buttonActionActionPerformed

    private void buttonClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClear1ActionPerformed
       this.setVisible(false);
       this.dispose();
    }//GEN-LAST:event_buttonClear1ActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
        this.clearForm();
    }//GEN-LAST:event_buttonClearActionPerformed

    private void buttonAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAssignActionPerformed
        int[] indices = this.listAvailableRules.getSelectedIndices();
        if(indices.length < 0) { return; }
        
        for(int index : indices){
            this.assignRules(index);
        }
    }//GEN-LAST:event_buttonAssignActionPerformed

    private void buttonUnassignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUnassignActionPerformed
       int[] indices = this.listAssignedRules.getSelectedIndices();
        if(indices.length < 0) { return; }
        
        for(int index : indices){
            this.removeRules(index);
        }
    }//GEN-LAST:event_buttonUnassignActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAction;
    private javax.swing.JButton buttonAssign;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonClear1;
    private javax.swing.JButton buttonUnassign;
    private javax.swing.JCheckBox checkBoxSuperUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelItemCode;
    private javax.swing.JLabel labelItemCode1;
    private javax.swing.JLabel labelItemName;
    private javax.swing.JLabel labelItemName2;
    private javax.swing.JLabel labelItemName3;
    private javax.swing.JLabel labelItemName4;
    private javax.swing.JLabel labelItemName5;
    private javax.swing.JList<String> listAssignedRules;
    private javax.swing.JList<String> listAvailableRules;
    private javax.swing.JPanel panelContent;
    private javax.swing.JTextField textBoxFirstName;
    private javax.swing.JTextField textBoxLastName;
    private javax.swing.JTextField textBoxUsername;
    private javax.swing.JPasswordField textboxConfirmPassword;
    private javax.swing.JPasswordField textboxPassword;
    // End of variables declaration//GEN-END:variables
}
