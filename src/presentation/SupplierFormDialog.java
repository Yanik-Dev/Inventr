/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.FormHelper;
import common.MessageBox;
import common.Validator;
import constant.PhoneNumberType;
import entity.Address;
import entity.ContactDetail;
import entity.Country;
import entity.Email;
import entity.PhoneNumber;
import entity.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import services.SupplierService;

/**
 *
 * @author Yanik
 */
public class SupplierFormDialog extends javax.swing.JDialog {
    
    private Supplier supplier = null;
    private DefaultListModel numberListModel; 
    private DefaultListModel emailListModel;
    private List<PhoneNumber> numberList = new ArrayList<>();
    private List<Email> emailList = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();
    SupplierService _dbService = null;
    /**
     * Creates new form NewJDialog
     */
    public SupplierFormDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Add New Supplier");
        this.getNumberType();
        this.getCountries();
    }
    
    public SupplierFormDialog(java.awt.Frame parent, boolean modal, Supplier supplier){
        super(parent, modal);
        initComponents();
        this.setTitle("Edit "+supplier.getName()+" Supplier");
        this.getNumberType();
        this.getCountries();
        this.setForm(supplier);
    }
    
    public void clearForm(){
        FormHelper.clear(this.panelForm);
        ((DefaultListModel) this.listPhoneNumber.getModel()).clear();
        ((DefaultListModel) this.listEmail.getModel()).clear();
        this.emailList.clear();
        this.numberList.clear();
    }
    
    public boolean validateForm(){
        boolean isValid = true;
        int count = 0;
        String errorMsg = "";
        if(this.textBoxSupplier.getText().trim().equals("") || this.textBoxSupplier.getText().length() < 3){
            count++;
            errorMsg += count+". Supplier name cannot be less than 3 letters \n";
        }
        if(this.textBoxCity.getText().trim().equals("") || this.textBoxCity.getText().length() < 3){
            count++;
            errorMsg += count+". City cannot be less than 3 letters \n";
        }
        if(this.textBoxStreet.getText().trim().equals("") || this.textBoxStreet.getText().length() < 3){
            count++;
            errorMsg += count+". Street cannot be less than 3 letters \n";
        }
        if(this.textBoxProvince.getText().trim().equals("") || this.textBoxProvince.getText().length() < 3){
            count++;
            errorMsg += count+". Province cannot be less than 3 letters \n";
        }
        if(this.comboBoxCountry.getSelectedIndex() < 0){
            count++;
            errorMsg += count+". A country must be selected \n";
        }
        if(this.textBoxWebsite.getText().trim().length() > 0){
           if(!Validator.isValidURL(this.textBoxWebsite.getText().trim())) {
                count++;
                errorMsg += count+". Invalid website url";
           }
        }
        
        if(count > 0){
            MessageBox.errorBox(this, "- Invalid Entries", errorMsg);
            isValid = false;
        }
        return isValid;
    }
    public void getNumberType(){
        this.comboBoxNumberType.removeAllItems();
        for(PhoneNumberType type : PhoneNumberType.values()){
            this.comboBoxNumberType.addItem(type.name());
        }
    }
    
    public void getCountries(){
        this.comboBoxCountry.removeAllItems();
        this._dbService = new SupplierService();
        this.countries = this._dbService.findAll(Country.class);
        if(this.countries != null){
            for(Country country : this.countries){
                this.comboBoxCountry.addItem(country.getName());
            }
        }
    }
    
    public void addPhoneNumbersToList(){
        if(!Validator.isValidPhoneNumber(this.textBoxNumber.getText().trim())){
            MessageBox.errorBox(this, "- Invalid Entry", "Invalid Phone Number.");
            return;
        }
        PhoneNumber number = new PhoneNumber();
        this.numberListModel = new DefaultListModel();
        number.setNumber(this.textBoxNumber.getText().trim());
        number.setType(PhoneNumberType.valueOf(this.comboBoxNumberType.getSelectedItem().toString()));
        this.numberListModel.addElement(number.getNumber()+" ("+ number.getType().name()+")");
        this.listPhoneNumber.setModel(this.numberListModel);
        this.numberList.add(number);
        this.textBoxNumber.setText("");
    }
    
    public void removePhoneNumbersFromList(int index){
        if(index < 0){
            MessageBox.warningBox(this, "", "No email selected");
            return;
        }
        ((DefaultListModel) this.listPhoneNumber.getModel()).remove(index);
        this.numberList.remove(index);
    }
    
    public void addEmailToList(){
        if(!Validator.isValidEmail(this.textBoxEmail.getText().trim())){
            MessageBox.errorBox(this, "- Invalid Entry", "Invalid Email.");
            return;
        }
        Email email = new Email();
        this.emailListModel = new DefaultListModel();
        email.setEmail(this.textBoxEmail.getText().trim());
        this.emailListModel.addElement(email.getEmail());
        this.listEmail.setModel(emailListModel);
        this.emailList.add(email);
        this.textBoxEmail.setText("");
    }
    
    public void removeEmailFromList(int index){        
        if(index < 0){
            MessageBox.warningBox(this, "", "No email selected");
            return;
        }
        ((DefaultListModel) this.listEmail.getModel()).remove(index);
        this.emailList.remove(index);
    }
    
    public void setForm(Supplier supplier){
        this.supplier = supplier;
        this.textBoxSupplier.setText(supplier.getName());
        this.textBoxCity.setText(supplier.getAddress().getCity());
        this.textBoxStreet.setText(supplier.getAddress().getStreet());
        this.textBoxProvince.setText(supplier.getAddress().getProvince());
        this.comboBoxCountry.setSelectedItem(supplier.getAddress().getCountry().getName());
        this.textBoxWebsite.setText(supplier.getContactDetail().getWebsite());
        
        List<PhoneNumber> numbers = null;
        numbers = supplier.getContactDetail().getPhoneNumbers();
        this.numberListModel = new DefaultListModel();
        
        for(PhoneNumber n : numbers){
            this.numberListModel.addElement(n.getNumber()+" ("+ n.getType().name()+")");
        }
        
        List<Email> emails = null;
        emails = supplier.getContactDetail().getEmails();
        this.emailListModel = new DefaultListModel();
        for(Email e : emails){
            this.emailListModel.addElement(e.getEmail());
        }
        this.listEmail.setModel(this.emailListModel);
        this.listPhoneNumber.setModel(this.numberListModel);

    }
    
    public void setSupplier(){
        if(supplier == null){ this.supplier = new Supplier(); }
        Address address = new Address();
        this.supplier.setName(this.textBoxSupplier.getText().trim());
        address.setCity(this.textBoxCity.getText().trim());
        address.setStreet(this.textBoxStreet.getText().trim());
        address.setProvince(this.textBoxProvince.getText().trim());
        address.setCountry(this.countries.get(this.comboBoxCountry.getSelectedIndex()));
        
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setWebsite(this.textBoxWebsite.getText().trim());
        contactDetail.setEmails(emailList);
        contactDetail.setPhoneNumbers(numberList);
        this.supplier.setAddress(address);
        this.supplier.setContactDetail(contactDetail);
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
        panelForm = new javax.swing.JPanel();
        textBoxSupplier = new javax.swing.JTextField();
        labelItemCode = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        textBoxStreet = new javax.swing.JTextField();
        textBoxCity = new javax.swing.JTextField();
        textBoxProvince = new javax.swing.JTextField();
        comboBoxCountry = new javax.swing.JComboBox<>();
        labelItemCode4 = new javax.swing.JLabel();
        labelItemCode5 = new javax.swing.JLabel();
        labelItemCode6 = new javax.swing.JLabel();
        labelItemCode7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        textBoxWebsite = new javax.swing.JTextField();
        textBoxNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPhoneNumber = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listEmail = new javax.swing.JList<>();
        textBoxEmail = new javax.swing.JTextField();
        comboBoxNumberType = new javax.swing.JComboBox<>();
        buttonAddEmail = new javax.swing.JButton();
        buttonAddNumber = new javax.swing.JButton();
        labelItemCode2 = new javax.swing.JLabel();
        labelItemCode1 = new javax.swing.JLabel();
        labelItemCode3 = new javax.swing.JLabel();
        buttonAction = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        menuItemRemove.setText("Remove");
        menuItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRemoveActionPerformed(evt);
            }
        });
        popupMenuOption.add(menuItemRemove);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        panelForm.setBackground(new java.awt.Color(255, 255, 255));

        textBoxSupplier.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode.setText("Supplier Name");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(226, 226, 226)), "Address"));

        textBoxStreet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        textBoxCity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        textBoxProvince.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        labelItemCode4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode4.setText("City");

        labelItemCode5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode5.setText("Province");

        labelItemCode6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode6.setText("Street");

        labelItemCode7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode7.setText("Country");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelItemCode6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textBoxStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelItemCode5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(textBoxCity)
                                        .addGap(36, 36, 36))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(labelItemCode4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(labelItemCode7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(textBoxProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(comboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelItemCode6)
                    .addComponent(labelItemCode4))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textBoxCity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelItemCode5)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(labelItemCode7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(226, 226, 226)), "Contact Details"));

        textBoxWebsite.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        textBoxNumber.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        listPhoneNumber.setToolTipText("Right Click to delete");
        listPhoneNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listPhoneNumberMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(listPhoneNumber);

        listEmail.setToolTipText("Right Click to delete");
        listEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listEmailMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(listEmail);

        textBoxEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buttonAddEmail.setText("Add");
        buttonAddEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddEmailActionPerformed(evt);
            }
        });

        buttonAddNumber.setText("Add");
        buttonAddNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNumberActionPerformed(evt);
            }
        });

        labelItemCode2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode2.setText("Phone Number(s)");

        labelItemCode1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode1.setText("Email(s)");

        labelItemCode3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelItemCode3.setText("Website");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textBoxWebsite)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(textBoxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(comboBoxNumberType, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonAddNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelItemCode2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelItemCode1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(textBoxEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonAddEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelItemCode3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelItemCode3)
                .addGap(2, 2, 2)
                .addComponent(textBoxWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelItemCode2)
                    .addComponent(labelItemCode1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAddNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textBoxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textBoxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAddEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboBoxNumberType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonAction.setText("Add");
        buttonAction.setBorder(null);
        buttonAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionActionPerformed(evt);
            }
        });

        buttonClear.setBackground(new java.awt.Color(35, 145, 205));
        buttonClear.setText("Clear");
        buttonClear.setBorder(null);
        buttonClear.setBorderPainted(false);
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        buttonCancel.setBackground(new java.awt.Color(35, 145, 205));
        buttonCancel.setText("Cancel");
        buttonCancel.setBorder(null);
        buttonCancel.setBorderPainted(false);
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelItemCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listPhoneNumberMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPhoneNumberMouseReleased
        int[] phoneNumberIndices = this.listPhoneNumber.getSelectedIndices();
        if(phoneNumberIndices.length < 1){ return;}
        if(evt.isPopupTrigger()){
            this.popupMenuOption.show(this.listPhoneNumber, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_listPhoneNumberMouseReleased

    private void listEmailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEmailMouseReleased
        int[] emailIndices = this.listEmail.getSelectedIndices();
        if(emailIndices.length < 1){ return;}
        if(evt.isPopupTrigger()){
            this.popupMenuOption.show(this.listEmail, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_listEmailMouseReleased

    private void buttonAddEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddEmailActionPerformed
        this.addEmailToList();
    }//GEN-LAST:event_buttonAddEmailActionPerformed

    private void buttonAddNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNumberActionPerformed
        this.addPhoneNumbersToList();
    }//GEN-LAST:event_buttonAddNumberActionPerformed

    private void buttonActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionActionPerformed
        if(!this.validateForm()){
            return;
        }
        
        this.setSupplier();
        boolean result = false;
        if(this.supplier.getId() > 0){
            result = this._dbService.update(this.supplier);
            if(result){
                MessageBox.infoBox(this, "- Result", "Supplier "+this.supplier.getName()+" update successfully");
                this.setVisible(false);
                this.dispose();
            }else{
                MessageBox.errorBox(this, "- Server Error", "Supplier "+this.supplier.getName()+" was unable to be updated. Please contact admin.");
            }
        }else{
            
            result = this._dbService.exist(this.supplier);
            if(result){
                MessageBox.infoBox(this, "- Result", "Supplier "+this.supplier.getName()+" already exist.");
                return;
            }
            
            if(this.emailList.size() < 1 || this.numberList.size() < 1){
                String message = " Are you want to insert supplier without "+((this.emailList.size() < 1)?" Email " :"")+((this.numberList.size() < 1)?" and Phone number " :"");
                if(JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)){
                    return;
                };
            }
            result = this._dbService.create(this.supplier);
            if(result){
                MessageBox.infoBox(this, "- Result", "Supplier "+this.supplier.getName()+" create successfully");
            }else{
                MessageBox.errorBox(this, "- Server Error", "Supplier "+this.supplier.getName()+" was unable to be created. Please contact admin.");
            }
        }
        if(result){ this.clearForm(); }
    }//GEN-LAST:event_buttonActionActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
        this.clearForm();
        
    }//GEN-LAST:event_buttonClearActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void menuItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRemoveActionPerformed
        int[] phoneNumberIndices = this.listPhoneNumber.getSelectedIndices();
        int[] emailIndices = this.listEmail.getSelectedIndices();
        if(phoneNumberIndices.length > 0){
            for(int i : phoneNumberIndices){
                this.removePhoneNumbersFromList(i);
            }
        }
        if(emailIndices.length > 0){
            for(int i : emailIndices){
                this.removeEmailFromList(i);
            }
        }
    }//GEN-LAST:event_menuItemRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAction;
    private javax.swing.JButton buttonAddEmail;
    private javax.swing.JButton buttonAddNumber;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonClear;
    private javax.swing.JComboBox<String> comboBoxCountry;
    private javax.swing.JComboBox<String> comboBoxNumberType;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelItemCode;
    private javax.swing.JLabel labelItemCode1;
    private javax.swing.JLabel labelItemCode2;
    private javax.swing.JLabel labelItemCode3;
    private javax.swing.JLabel labelItemCode4;
    private javax.swing.JLabel labelItemCode5;
    private javax.swing.JLabel labelItemCode6;
    private javax.swing.JLabel labelItemCode7;
    private javax.swing.JList<String> listEmail;
    private javax.swing.JList<String> listPhoneNumber;
    private javax.swing.JMenuItem menuItemRemove;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPopupMenu popupMenuOption;
    private javax.swing.JTextField textBoxCity;
    private javax.swing.JTextField textBoxEmail;
    private javax.swing.JTextField textBoxNumber;
    private javax.swing.JTextField textBoxProvince;
    private javax.swing.JTextField textBoxStreet;
    private javax.swing.JTextField textBoxSupplier;
    private javax.swing.JTextField textBoxWebsite;
    // End of variables declaration//GEN-END:variables
}
