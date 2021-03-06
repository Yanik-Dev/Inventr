/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import common.AppLogger;
import entity.Permission;
import entity.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Yanik
 */
public class MainForm extends javax.swing.JFrame {
    //Global Class declaration of forms/views

    ItemInternalFrame itemView = null;
    DashboardInternalFrame dashboard = null;
    TransactionInternalFrame transFrame = null;
    SupplierInternalFrame supplierView = null;
    UserInternalFrame userView =null;
    CategoryViewDialog categoryView = null;
    UnitViewDialog unitView =null;
    LocationViewDialog locationView = null;    
    RequestFormDialog requestDialog = null;
    ReceivableFormDialog receivableDialog = null;

    
    User _user = null;
    /**
     * Creates new form main
     */
    public MainForm() {
        initComponents();
        dashboard = new DashboardInternalFrame(this._user);
        this.desktopPaneMain.add(dashboard);

        try {
            dashboard.setMaximum(true);
        } catch (PropertyVetoException e) {

        }

        dashboard.setBorder(null);
        dashboard.setVisible(true);
    }
    
    public MainForm(User user) {
        initComponents();
        this._user = user;
        this.lblUsername.setText("USER: "+user.getUsername().toUpperCase());
        dashboard = new DashboardInternalFrame(this._user);
        this.desktopPaneMain.add(dashboard);

        try {
            dashboard.setMaximum(true);
        } catch (PropertyVetoException e) {

        }

        dashboard.setBorder(null);
        dashboard.setVisible(true);
        this.checkPermission();
    }
    
    public void checkPermission(){
        this.categoryMenuItem.setVisible(false);
        this.storageMenuItem.setVisible(false);
        this.recMenuItem.setVisible(false);
        this.reqMenuItem.setVisible(false);
        this.userMenuItem.setVisible(false);
        this.supplierMenuItem.setVisible(false);
        this.unitMenuItem.setVisible(false);
        buttonDashboard.setVisible(false);
        this.dashboard.setVisible(false);
        this.logMenuItem.setVisible(false);
        this.itemMenuItem.setVisible(false);
        for(Permission p : this._user.getRules()){
            if(p.getName().equals("View Dashboard")){
                buttonDashboard.setVisible(true);
                this.dashboard.setVisible(true);
                continue;
            }
            if(p.getName().equals("View Transaction Logs")){
                this.logMenuItem.setVisible(true);
                continue;
            }
            if(p.getName().equals("Manage Items")){
                this.itemMenuItem.setVisible(true);
                continue;
            }
            if(p.getName().equals("Manage Suppliers")){
                this.supplierMenuItem.setVisible(true);
                continue;
            }
            if(p.getName().equals("Manage Users")){
                this.userMenuItem.setVisible(true);
                continue;
            }
            if(p.getName().equals("Request Items")){
                this.reqMenuItem.setVisible(true);
                continue;
            }
            if(p.getName().equals("Add Receivables")){
                this.recMenuItem.setVisible(true);
            }
            if(p.getName().equals("Manage Units")){
                this.unitMenuItem.setVisible(true);
            }
            if(p.getName().equals("Manage Locations")){
                this.storageMenuItem.setVisible(true);
            }
            if(p.getName().equals("Manage  Categories")){
                this.categoryMenuItem.setVisible(true);
            }
            
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

        desktopPaneMain = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        buttonDashboard = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lblUsername = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        transMenu = new javax.swing.JMenu();
        logMenuItem = new javax.swing.JMenuItem();
        reqMenuItem = new javax.swing.JMenuItem();
        recMenuItem = new javax.swing.JMenuItem();
        inventoryMenu = new javax.swing.JMenu();
        itemMenuItem = new javax.swing.JMenuItem();
        supplierMenuItem = new javax.swing.JMenuItem();
        categoryMenuItem = new javax.swing.JMenuItem();
        unitMenuItem = new javax.swing.JMenuItem();
        storageMenuItem = new javax.swing.JMenuItem();
        systemMenu = new javax.swing.JMenu();
        userMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        buttonDashboard.setText("Dashboard");
        buttonDashboard.setFocusable(false);
        buttonDashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDashboard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDashboardActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonDashboard);
        jToolBar1.add(jSeparator1);
        jToolBar1.add(lblUsername);

        desktopPaneMain.setLayer(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneMainLayout = new javax.swing.GroupLayout(desktopPaneMain);
        desktopPaneMain.setLayout(desktopPaneMainLayout);
        desktopPaneMainLayout.setHorizontalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
        );
        desktopPaneMainLayout.setVerticalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneMainLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 491, Short.MAX_VALUE))
        );

        jLabel1.setText("jLabel1");

        menuBar.setBackground(new java.awt.Color(51, 51, 51));

        transMenu.setMnemonic('T');
        transMenu.setText("Transactions");
        transMenu.setToolTipText("View requests and receivable logs");
        transMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transMenuActionPerformed(evt);
            }
        });

        logMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        logMenuItem.setText("Logs");
        logMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logMenuItemActionPerformed(evt);
            }
        });
        transMenu.add(logMenuItem);

        reqMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        reqMenuItem.setText("Resquest Item");
        reqMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqMenuItemActionPerformed(evt);
            }
        });
        transMenu.add(reqMenuItem);

        recMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        recMenuItem.setText("Add Receivables");
        recMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recMenuItemActionPerformed(evt);
            }
        });
        transMenu.add(recMenuItem);

        menuBar.add(transMenu);

        inventoryMenu.setText("Inventory Management");

        itemMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        itemMenuItem.setText("Item Management");
        itemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(itemMenuItem);

        supplierMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        supplierMenuItem.setText("Supplier Managment");
        supplierMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(supplierMenuItem);

        categoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        categoryMenuItem.setMnemonic('C');
        categoryMenuItem.setText("Category Management");
        categoryMenuItem.setToolTipText("Manage categorues");
        categoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(categoryMenuItem);

        unitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        unitMenuItem.setText("Unit Management");
        unitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(unitMenuItem);

        storageMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        storageMenuItem.setText("Storage Management");
        storageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storageMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(storageMenuItem);

        menuBar.add(inventoryMenu);

        systemMenu.setText("System Management");

        userMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        userMenuItem.setMnemonic('U');
        userMenuItem.setText("User Management");
        userMenuItem.setToolTipText("Create, update and delete Users");
        userMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userMenuItemActionPerformed(evt);
            }
        });
        systemMenu.add(userMenuItem);

        menuBar.add(systemMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPaneMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPaneMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuItemActionPerformed
        this.itemView = new ItemInternalFrame();
        disableMenu(true);
        this.itemView.addInternalFrameListener(new InternalFrameAdapter(){
             public void internalFrameClosed(InternalFrameEvent e) {
               disableMenu(false);
               itemView = null;
             }
         });
        this.desktopPaneMain.add(this.itemView);
        
        try {
            itemView.setMaximum(true);
          } catch (PropertyVetoException e) {
          }
        this.itemView.setBorder(null);
        this.itemView.setVisible(true);
    }//GEN-LAST:event_itemMenuItemActionPerformed

    private void categoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryMenuItemActionPerformed
        categoryView = new CategoryViewDialog(this, true);
        disableMenu(true);
        this.categoryView.addWindowListener(new WindowAdapter()
            {
              public void windowClosed(WindowEvent e)
              {
                disableMenu(false);
                categoryView = null;
              }
        });
        categoryView.setLocationRelativeTo(null);
        categoryView.setVisible(true);
    }//GEN-LAST:event_categoryMenuItemActionPerformed

    private void supplierMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierMenuItemActionPerformed
        this.supplierView = new SupplierInternalFrame();
        disableMenu(true);
         this.supplierView.addInternalFrameListener(new InternalFrameAdapter(){
             public void internalFrameClosed(InternalFrameEvent e) {
               disableMenu(false);
               supplierView = null;
             }
         });
        this.desktopPaneMain.add(this.supplierView);
        
        try {
            supplierView.setMaximum(true);
        } catch (PropertyVetoException e) {
          
        }
        this.supplierView.setBorder(null);
        this.supplierView.setVisible(true);
        
    }//GEN-LAST:event_supplierMenuItemActionPerformed

    private void transMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transMenuActionPerformed
      
    }//GEN-LAST:event_transMenuActionPerformed

    private void logMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logMenuItemActionPerformed
         this.transFrame = new TransactionInternalFrame();
         disableMenu(true);
         this.transFrame.addInternalFrameListener(new InternalFrameAdapter(){
             public void internalFrameClosed(InternalFrameEvent e) {
               disableMenu(false);
               transFrame =null;
             }
         });
        this.desktopPaneMain.add(this.transFrame);
        try {
            transFrame.setMaximum(true);
          } catch (PropertyVetoException e) {
            
          }
        this.transFrame.setBorder(null);
        this.transFrame.setVisible(true);
    }//GEN-LAST:event_logMenuItemActionPerformed

    private void userMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userMenuItemActionPerformed
        this.userView = new UserInternalFrame();
        disableMenu(true);
        this.userView.addInternalFrameListener(new InternalFrameAdapter(){
             public void internalFrameClosed(InternalFrameEvent e) {
               disableMenu(false);
               userView = null;
             }
         });
        this.desktopPaneMain.add(this.userView);
        
        try {
            userView.setMaximum(true);
          } catch (PropertyVetoException e) {
          }
        this.userView.setBorder(null);
        this.userView.setVisible(true);
    }//GEN-LAST:event_userMenuItemActionPerformed

    private void unitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitMenuItemActionPerformed
        this.unitView = new UnitViewDialog(this, true);
        disableMenu(true);
        this.unitView.addWindowListener(new WindowAdapter()
            {
              public void windowClosed(WindowEvent e)
              {
                disableMenu(false);
                unitView = null;
              }
        });
        
        unitView.setLocationRelativeTo(null);
        unitView.setVisible(true);
    }//GEN-LAST:event_unitMenuItemActionPerformed

    private void storageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageMenuItemActionPerformed
       this.locationView = new LocationViewDialog(this, true);
        disableMenu(true);
        this.locationView.addWindowListener(new WindowAdapter()
            {
              public void windowClosed(WindowEvent e)
              {
                disableMenu(false);
                locationView = null;
              }
        });
        
        locationView.setLocationRelativeTo(null);
        locationView.setVisible(true);
    }//GEN-LAST:event_storageMenuItemActionPerformed

    private void recMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recMenuItemActionPerformed
        this.receivableDialog = new ReceivableFormDialog(this, true);
        disableMenu(true);
        this.receivableDialog.addWindowListener(new WindowAdapter()
            {
              public void windowClosed(WindowEvent e)
              {
                disableMenu(false);
                receivableDialog = null;
              }
        });
        
        receivableDialog.setLocationRelativeTo(null);
        receivableDialog.setVisible(true);
    }//GEN-LAST:event_recMenuItemActionPerformed

    private void reqMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqMenuItemActionPerformed
        this.requestDialog = new RequestFormDialog(this, true, this._user);
        disableMenu(true);
        this.requestDialog.addWindowListener(new WindowAdapter()
            {
              public void windowClosed(WindowEvent e)
              {
                disableMenu(false);
                requestDialog = null;
              }
        });
        
        requestDialog.setLocationRelativeTo(null);
        requestDialog.setVisible(true);
    }//GEN-LAST:event_reqMenuItemActionPerformed

    private void buttonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDashboardActionPerformed
       dashboard = new DashboardInternalFrame(this._user);
        this.desktopPaneMain.add(dashboard);

        try {
            dashboard.setMaximum(true);
        } catch (PropertyVetoException e) {

        }

        dashboard.setBorder(null);
        dashboard.setVisible(true);
    }//GEN-LAST:event_buttonDashboardActionPerformed
    
    public void disableMenu(boolean value){
        this.inventoryMenu.setEnabled(!value);
        this.transMenu.setEnabled(!value);
        this.systemMenu.setEnabled(!value);
        if(dashboard.isVisible()){
            dashboard.setVisible(false);
        }else{
            dashboard.setVisible(true);
        }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm main = new MainForm();
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDashboard;
    private javax.swing.JMenuItem categoryMenuItem;
    private javax.swing.JDesktopPane desktopPaneMain;
    private javax.swing.JMenu inventoryMenu;
    private javax.swing.JMenuItem itemMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenuItem logMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem recMenuItem;
    private javax.swing.JMenuItem reqMenuItem;
    private javax.swing.JMenuItem storageMenuItem;
    private javax.swing.JMenuItem supplierMenuItem;
    private javax.swing.JMenu systemMenu;
    private javax.swing.JMenu transMenu;
    private javax.swing.JMenuItem unitMenuItem;
    private javax.swing.JMenuItem userMenuItem;
    // End of variables declaration//GEN-END:variables
}
