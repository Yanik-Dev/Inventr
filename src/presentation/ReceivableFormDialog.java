/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.FormHelper;
import common.MessageBox;
import common.Validator;
import entity.Item;
import entity.Receivable;
import entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import services.DatabaseService;

/**
 *
 * @author Yanik
 */
public class ReceivableFormDialog extends javax.swing.JDialog {
    List<Item> _items = new ArrayList<>();
    List<Supplier> _suppliers = new ArrayList<>();   
    Receivable _receivable = new Receivable();
    DatabaseService _dbService = null;
    boolean isUpdate = false;
    
    /**
     * Creates new form ItemDialog
     */
    public ReceivableFormDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getSuppliers();
        this.getItems();
    }
    
    /**
     * Creates new form ItemDialog
     */
    public ReceivableFormDialog(java.awt.Frame parent, boolean modal, Receivable receivable) {
        super(parent, modal);
        initComponents();
        this.getSuppliers();
        this.getItems();
        this.setForm(receivable);
    }
    
    public void getSuppliers(){
        ((DefaultComboBoxModel) this.comboBoxSupplier.getModel()).removeAllElements();
        this.comboBoxSupplier.removeAllItems();
        this._dbService = new DatabaseService();
        this._suppliers = this._dbService.findAll(Supplier.class);
        for(Supplier supplier : this._suppliers){
            this.comboBoxSupplier.addItem(supplier.getName());
        }
    }
    
    public void getItems(){
        ((DefaultComboBoxModel) this.comboxItem.getModel()).removeAllElements();
        this.comboxItem.removeAllItems();
        this._dbService = new DatabaseService();
        this._items = this._dbService.findAll(Item.class);
        for(Item item : this._items){
            this.comboBoxSupplier.addItem(item.getItemName());
        }
    }

    
    public void setForm(Receivable receivable){
        this._receivable = receivable;
        this.textBoxCost.setText(Double.toString(_receivable.getCost()));
        this.maskBoxDate.setText(receivable.getDateReceived());
        this.textBoxQuantity.setText(Double.toString(_receivable.getAmount()));
        if(receivable.getSupplier() != null){
           this.comboBoxSupplier.setSelectedItem(receivable.getSupplier().getName());
        }
        if(receivable.getItem() != null){
           this.comboxItem.setSelectedItem(receivable.getItem().getItemName());
        }
    }
    
    public boolean validateForm(){
        String errorMessage = "";
        int counter = 0;
        
        //TODO: validate date
        if(this.comboBoxSupplier.getSelectedIndex() < 0){
            counter++;
            errorMessage += counter+". A supplier is required\n";
        }
        
        if(this.comboxItem.getSelectedIndex() < 0){
            counter++;
            errorMessage += counter+". An item is required\n";
        }
        try{
           Double d = Double.parseDouble(this.textBoxCost.getText().trim());
        }
        catch(NumberFormatException ex){
           counter++;
           errorMessage += counter+". Cost must be a number\n";
           this.textBoxCost.setText("0.00");
        }
        try{
           Double d = Double.parseDouble(this.textBoxQuantity.getText().trim());
           if(d < 1){
            counter++;
            errorMessage += counter+". Quantity must be over 0\n";
           }
        }
        catch(NumberFormatException ex){
           counter++;
           errorMessage += counter+". Quantity must be a number\n";
           this.textBoxQuantity.setText("0");
        }
        
        if(!Validator.isDateValid(this.maskBoxDate.getText().trim(), "yyyy-MM-dd")){
            counter++;
           errorMessage += counter+". Invalid date format\n";
           this.textBoxQuantity.setText("0");
        }
        if(counter > 0){
            MessageBox.errorBox(this, "- Invalid Entries", errorMessage);
            return false;
        }
        return true;
    }
    
    public void clearForm(){
        FormHelper.clear(this.jPanel2);
        this.textBoxQuantity.setText("0");
        this.textBoxCost.setText("0.00");
    }
    
    public void setReceivable(){
      
        Supplier supplier = this._suppliers.get(this.comboBoxSupplier.getSelectedIndex());
        Item item = this._items.get(this.comboxItem.getSelectedIndex());
        this._receivable.setDateReceived(this.maskBoxDate.getText());
        this._receivable.setAmount(Double.parseDouble(this.textBoxQuantity.getText().trim()));
        this._receivable.setCost(Double.parseDouble(this.textBoxCost.getText().trim()));
        this._receivable.setSupplier(supplier);  
        this._receivable.setItem(item);    
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
        panelItemForm = new javax.swing.JPanel();
        labelItemCode = new javax.swing.JLabel();
        textBoxQuantity = new javax.swing.JTextField();
        labelQuantity = new javax.swing.JLabel();
        textBoxCost = new javax.swing.JTextField();
        labelUnitCost = new javax.swing.JLabel();
        comboBoxSupplier = new javax.swing.JComboBox<>();
        maskBoxDate = new javax.swing.JFormattedTextField();
        labelQuantity1 = new javax.swing.JLabel();
        labelQuantity2 = new javax.swing.JLabel();
        comboxItem = new javax.swing.JComboBox<>();
        buttonAction = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        buttonClear1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelItemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode.setText("Item Name");

        textBoxQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBoxQuantity.setText("0");

        labelQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelQuantity.setText("Quantity");

        textBoxCost.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBoxCost.setText("0.00");

        labelUnitCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelUnitCost.setText("Cost");

        maskBoxDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));

        labelQuantity1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelQuantity1.setText("Date Received (yyyy-mm-dd)");

        labelQuantity2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelQuantity2.setText("Supplier");

        javax.swing.GroupLayout panelItemFormLayout = new javax.swing.GroupLayout(panelItemForm);
        panelItemForm.setLayout(panelItemFormLayout);
        panelItemFormLayout.setHorizontalGroup(
            panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelItemFormLayout.createSequentialGroup()
                        .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemFormLayout.createSequentialGroup()
                                    .addComponent(labelItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(186, 186, 186))
                                .addComponent(textBoxQuantity, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textBoxCost)
                                .addComponent(labelUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelQuantity2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maskBoxDate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboxItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelItemFormLayout.setVerticalGroup(
            panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelItemCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboxItem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(labelQuantity2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQuantity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBoxQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUnitCost)
                .addGap(5, 5, 5)
                .addComponent(textBoxCost, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQuantity1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maskBoxDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
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
                    .addComponent(panelItemForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelItemForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionActionPerformed
        if(!this.validateForm()){
            return;
        }

        this.setReceivable();
        boolean result = false;
        if(this._receivable.getId() > 0){
            result = this._dbService.update(this._receivable);
            if(result){
                MessageBox.infoBox(this, "- Result", "Item "+this._receivable.getItem().getItemName()+" quantity was updated successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "Item "+this._receivable.getItem().getItemName()+" quantity was unable to be updated. Please contact admin.");
                //TODO : log error
            }
        }else{
            result = this._dbService.create(this._receivable);
            if(result){
                MessageBox.infoBox(this, "- Result", "Item "+this._receivable.getItem().getItemName()+" quantity was updated create successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "Item "+this._receivable.getItem().getItemName()+" quantity was unable to be created. Please contact admin.");
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

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAction;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonClear1;
    private javax.swing.JComboBox<String> comboBoxSupplier;
    private javax.swing.JComboBox<String> comboxItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelItemCode;
    private javax.swing.JLabel labelQuantity;
    private javax.swing.JLabel labelQuantity1;
    private javax.swing.JLabel labelQuantity2;
    private javax.swing.JLabel labelUnitCost;
    private javax.swing.JFormattedTextField maskBoxDate;
    private javax.swing.JPanel panelItemForm;
    private javax.swing.JTextField textBoxCost;
    private javax.swing.JTextField textBoxQuantity;
    // End of variables declaration//GEN-END:variables
}
