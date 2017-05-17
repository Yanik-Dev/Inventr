/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.FormHelper;
import common.MessageBox;
import constant.Status;
import entity.Category;
import entity.Country;
import entity.Item;
import entity.Location;
import entity.Supplier;
import entity.Unit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import services.DatabaseService;

/**
 *
 * @author Yanik
 */
public class ItemDialog extends javax.swing.JDialog {
    List<Unit> units = new ArrayList<>();
    List<Supplier> suppliers = new ArrayList<>();    
    List<Category> categories = new ArrayList<>();
    List<Location> locations = new ArrayList<>();
    Item item = null;
    DatabaseService _dbService = null;
    boolean isUpdate = false;
    
    DefaultListModel supplierListModel = new DefaultListModel();
    List<Supplier> supplierList = new ArrayList<>();
    /**
     * Creates new form ItemDialog
     */
    public ItemDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getSuppliers();
        this.getCategories();
        this.getLocations();
        this.getUnits();
    }
    
    /**
     * Creates new form ItemDialog
     */
    public ItemDialog(java.awt.Frame parent, boolean modal, Item item) {
        super(parent, modal);
        initComponents();
        this.getSuppliers();
        this.getCategories();
        this.getLocations();
        this.getUnits();
        this.setForm(item);
        this.isUpdate = true;
    }
    
    public void getSuppliers(){
        this.comboBoxSupplier.removeAllItems();
        this._dbService = new DatabaseService();
        this.suppliers = this._dbService.findAll(Supplier.class);
        for(Supplier supplier : this.suppliers){
            this.comboBoxSupplier.addItem(supplier.getName());
        }
    }
    
    public void getCategories(){
        this.comboBoxCategory.removeAllItems();
        this._dbService = new DatabaseService();
        this.categories = this._dbService.findAll(Category.class);
        for(Category category : this.categories){
            this.comboBoxSupplier.addItem(category.getName());
        }
    }
    
    public void getUnits(){
        this.comboBoxUnit.removeAllItems();
        this._dbService = new DatabaseService();
        this.units = this._dbService.findAll(Unit.class);
        for(Unit unit : this.units){
            this.comboBoxUnit.addItem(unit.getName());
        }
    }
    
    public void getLocations(){
        this.comboBoxLocation.removeAllItems();
        this._dbService = new DatabaseService();
        this.locations = this._dbService.findAll(Location.class);
        for(Location location : this.locations){
            this.comboBoxLocation.addItem(location.getName());
        }
    }
    
    public void addSuppliersToList(){
        int index = this.comboBoxSupplier.getSelectedIndex();
        if(index < 0) { return; }
        
        Supplier supplier = this.suppliers.get(index);
        boolean found = this.supplierList.contains(this);
        if(found) { MessageBox.warningBox(this, "", "Supplier already exist in list"); return; }
        this.supplierListModel.addElement(supplier.getName());
        this.ListSupplier.setModel(this.supplierListModel);
        this.supplierList.add(supplier);
    }
    
    public void removeSupplierFromList(int index){
        if(index < 0){
            MessageBox.warningBox(this, "", "No Supplier selected");
            return;
        }
        ((DefaultListModel) this.ListSupplier.getModel()).remove(index);
        this.supplierList.remove(index);
    }
    
    public void setForm(Item item){
        this.item = item;
        this.textBoxItemCode.setText(item.getItemCode());
        this.textBoxItemName.setText(item.getItemName());
        this.textAreaDescription.setText(item.getDescription());
        this.textBoxUnitCost.setText(Double.toString(item.getUnitCost()));
        this.textBoxReorderLevel.setText(Double.toString(item.getReorderLevel()));
        this.textBoxQuantity.setText(Double.toString(item.getQuantity()));
        if(item.getCategory() != null){
           this.comboBoxCategory.setSelectedItem(item.getCategory().getName());
        }
        if(item.getLocation() != null){
           this.comboBoxLocation.setSelectedItem(item.getLocation().getName());
        }
        if(item.getUnit() != null){
          this.comboBoxUnit.setSelectedItem(item.getUnit().getName());  
        }
        for(Supplier supplier : item.getSuppliers()){
            this.supplierList.add(supplier);
            this.supplierListModel.addElement(supplier.getName());
        }
        this.ListSupplier.setModel(this.supplierListModel);
    }
    
    public boolean validateForm(){
        String errorMessage = "";
        int counter = 0;
        
        if(this.textBoxItemCode.getText().trim().equals("")){
            counter++;
            errorMessage += counter+". An item code is required\n";
        }
        if(this.textBoxItemName.getText().trim().equals("")){
            counter++;
            errorMessage += counter+". An item name is required\n";
        } 
        if(this.comboBoxCategory.getSelectedIndex() < 0){
            counter++;
            errorMessage += counter+". A category is required\n";
        }       
        if(this.comboBoxLocation.getSelectedIndex() < 0){
            counter++;
            errorMessage += counter+". A storage location is required\n";
        }
        if(this.comboBoxUnit.getSelectedIndex() < 0){
            counter++;
            errorMessage += counter+". A unit is required\n";
        }
        if(counter > 0){
            MessageBox.errorBox(this, "- Invalid Entries", errorMessage);
            return false;
        }
        return true;
    }
    
    public void clearForm(){
        FormHelper.clear(this.jPanel2);
        ((DefaultListModel)this.ListSupplier.getModel()).clear();
        this.supplierList.clear();
        this.textBoxQuantity.setText("0");
        this.textBoxUnitCost.setText("0.00");
        this.textBoxReorderLevel.setText("0");
    }
    
    public void setItem(){
        item = new Item();      
        
        Unit unit = this.units.get(this.comboBoxUnit.getSelectedIndex());        
        Supplier supplier = this.suppliers.get(this.comboBoxSupplier.getSelectedIndex());
        Location location = this.locations.get(this.comboBoxLocation.getSelectedIndex());
        Category category = this.categories.get(this.comboBoxCategory.getSelectedIndex());

        item.setUnit(unit);
        item.setSuppliers(this.supplierList);
        item.setLocation(location);
        item.setCategory(category);
        item.setItemCode(this.textBoxItemCode.getText().trim()); 
        item.setItemName(this.textBoxItemName.getText().trim());
        item.setDescription(this.textAreaDescription.getText().trim());
        item.setQuantity(Double.parseDouble(this.textBoxQuantity.getText()));
        item.setReorderLevel(Double.parseDouble(this.textBoxReorderLevel.getText())); 
        item.setUnitCost(Double.parseDouble(this.textBoxUnitCost.getText()));
        item.setStatus(Status.ACTIVE);
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuOption = new javax.swing.JPopupMenu();
        menuItemRemove = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelItemForm = new javax.swing.JPanel();
        textBoxItemCode = new javax.swing.JTextField();
        labelCategory = new javax.swing.JLabel();
        comboBoxCategory = new javax.swing.JComboBox<>();
        labelItemCode = new javax.swing.JLabel();
        textBoxItemName = new javax.swing.JTextField();
        labelItemName = new javax.swing.JLabel();
        comboBoxLocation = new javax.swing.JComboBox<>();
        labelLocation = new javax.swing.JLabel();
        scrollPaneItemTable = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        labelDescription = new javax.swing.JLabel();
        addStorageLocation = new javax.swing.JButton();
        buttonAddCategory = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListSupplier = new javax.swing.JList<>();
        comboBoxSupplier = new javax.swing.JComboBox<>();
        buttonSupplierAdd = new javax.swing.JButton();
        buttonAddSupplier = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        labelReorderLevek = new javax.swing.JLabel();
        labelQuantity = new javax.swing.JLabel();
        labelUnitCost = new javax.swing.JLabel();
        comboBoxUnit = new javax.swing.JComboBox<>();
        labelUnit = new javax.swing.JLabel();
        buttonAddUnit = new javax.swing.JButton();
        textBoxUnitCost = new javax.swing.JTextField();
        textBoxReorderLevel = new javax.swing.JTextField();
        textBoxQuantity = new javax.swing.JTextField();
        buttonAction = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        buttonClear1 = new javax.swing.JButton();

        menuItemRemove.setText("Remove");
        menuItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRemoveActionPerformed(evt);
            }
        });
        popupMenuOption.add(menuItemRemove);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        textBoxItemCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelCategory.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelCategory.setText("Category");

        labelItemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode.setText("Item Code");

        textBoxItemName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemName.setText("Item Name");

        labelLocation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLocation.setText("Storage Location");

        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        scrollPaneItemTable.setViewportView(textAreaDescription);

        labelDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelDescription.setText("Description");

        addStorageLocation.setText("jButton1");

        buttonAddCategory.setText("jButton1");
        buttonAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelItemFormLayout = new javax.swing.GroupLayout(panelItemForm);
        panelItemForm.setLayout(panelItemFormLayout);
        panelItemFormLayout.setHorizontalGroup(
            panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textBoxItemName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneItemTable, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItemFormLayout.createSequentialGroup()
                            .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemFormLayout.createSequentialGroup()
                                    .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(45, 45, 45))
                                .addGroup(panelItemFormLayout.createSequentialGroup()
                                    .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelItemFormLayout.createSequentialGroup()
                                            .addComponent(labelCategory)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)))
                            .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboBoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelItemFormLayout.createSequentialGroup()
                                    .addComponent(labelLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addStorageLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(textBoxItemCode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelItemFormLayout.setVerticalGroup(
            panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelItemCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBoxItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBoxItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneItemTable, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCategory)
                    .addComponent(labelLocation)
                    .addComponent(addStorageLocation)
                    .addComponent(buttonAddCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelItemFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Supplier(s)"));

        ListSupplier.setToolTipText("Right click for more options");
        ListSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ListSupplierMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(ListSupplier);

        buttonSupplierAdd.setText("Add");
        buttonSupplierAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSupplierAddActionPerformed(evt);
            }
        });

        buttonAddSupplier.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(buttonAddSupplier)
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSupplierAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelReorderLevek.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelReorderLevek.setText("Re-Order Level");

        labelQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelQuantity.setText("Quantity");

        labelUnitCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelUnitCost.setText("Unit Cost");

        labelUnit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelUnit.setText("Unit");

        buttonAddUnit.setText("jButton1");

        textBoxUnitCost.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBoxUnitCost.setText("0.00");

        textBoxReorderLevel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBoxReorderLevel.setText("0");

        textBoxQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textBoxQuantity.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(textBoxUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelReorderLevek, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labelUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAddUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textBoxReorderLevel)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textBoxQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(comboBoxUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUnitCost)
                    .addComponent(labelReorderLevek))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textBoxReorderLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantity)
                    .addComponent(labelUnit)
                    .addComponent(buttonAddUnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addComponent(panelItemForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelItemForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void buttonAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCategoryActionPerformed
        
    }//GEN-LAST:event_buttonAddCategoryActionPerformed

    private void buttonActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionActionPerformed
        if(!this.validateForm()){
            return;
        }

        this.setItem();
        boolean result = false;
        if(this.isUpdate){
            result = this._dbService.update(this.item);
            if(result){
                MessageBox.infoBox(this, "- Result", "Item "+this.item.getItemName()+" update successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "Item "+this.item.getItemName()+" was unable to be updated. Please contact admin.");
                //TODO : log error
            }
        }else{
            result = this._dbService.create(this.item);
            if(result){
                MessageBox.infoBox(this, "- Result", "Item "+this.item.getItemName()+" create successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "Item "+this.item.getItemName()+" was unable to be created. Please contact admin.");
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

    private void menuItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRemoveActionPerformed
        int[] supplierIndices = this.ListSupplier.getSelectedIndices();
        if(supplierIndices.length > 0){
            for(int i : supplierIndices){
                this.removeSupplierFromList(i);
            }
        }
    }//GEN-LAST:event_menuItemRemoveActionPerformed

    private void ListSupplierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListSupplierMouseReleased
        this.popupMenuOption.show(this.ListSupplier, evt.getX(), evt.getY());
    }//GEN-LAST:event_ListSupplierMouseReleased

    private void buttonSupplierAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSupplierAddActionPerformed
        this.addSuppliersToList();
    }//GEN-LAST:event_buttonSupplierAddActionPerformed

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
            java.util.logging.Logger.getLogger(ItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ItemDialog dialog = new ItemDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListSupplier;
    private javax.swing.JButton addStorageLocation;
    private javax.swing.JButton buttonAction;
    private javax.swing.JButton buttonAddCategory;
    private javax.swing.JButton buttonAddSupplier;
    private javax.swing.JButton buttonAddUnit;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonClear1;
    private javax.swing.JButton buttonSupplierAdd;
    private javax.swing.JComboBox<String> comboBoxCategory;
    private javax.swing.JComboBox<String> comboBoxLocation;
    private javax.swing.JComboBox<String> comboBoxSupplier;
    private javax.swing.JComboBox<String> comboBoxUnit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCategory;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelItemCode;
    private javax.swing.JLabel labelItemName;
    private javax.swing.JLabel labelLocation;
    private javax.swing.JLabel labelQuantity;
    private javax.swing.JLabel labelReorderLevek;
    private javax.swing.JLabel labelUnit;
    private javax.swing.JLabel labelUnitCost;
    private javax.swing.JMenuItem menuItemRemove;
    private javax.swing.JPanel panelItemForm;
    private javax.swing.JPopupMenu popupMenuOption;
    private javax.swing.JScrollPane scrollPaneItemTable;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField textBoxItemCode;
    private javax.swing.JTextField textBoxItemName;
    private javax.swing.JTextField textBoxQuantity;
    private javax.swing.JTextField textBoxReorderLevel;
    private javax.swing.JTextField textBoxUnitCost;
    // End of variables declaration//GEN-END:variables
}
