/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.beans.PropertyVetoException;
import javax.swing.JFrame;

/**
 *
 * @author Yanik
 */
public class MainView extends javax.swing.JFrame {
    //Global Class declaration of forms/views
    ItemFormView itemView = null;
    SupplierView supplierView = null;
    /**
     * Creates new form main
     */
    public MainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        desktopPaneMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        inventoryMenu = new javax.swing.JMenu();
        categoryMenuItem = new javax.swing.JMenuItem();
        itemMenuItem = new javax.swing.JMenuItem();
        supplierMenuItem = new javax.swing.JMenuItem();
        transactionMenu = new javax.swing.JMenu();
        receivableMenuItem = new javax.swing.JMenuItem();
        requestMenuItem = new javax.swing.JMenuItem();
        systemMenu = new javax.swing.JMenu();
        userMenuItem = new javax.swing.JMenuItem();
        logMenuItem = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneMainLayout = new javax.swing.GroupLayout(desktopPaneMain);
        desktopPaneMain.setLayout(desktopPaneMainLayout);
        desktopPaneMainLayout.setHorizontalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1186, Short.MAX_VALUE)
        );
        desktopPaneMainLayout.setVerticalGroup(
            desktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        inventoryMenu.setText("Inventory Management");

        categoryMenuItem.setText("Category Management");
        categoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(categoryMenuItem);

        itemMenuItem.setText("Item Management");
        itemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(itemMenuItem);

        supplierMenuItem.setText("Supplier Managment");
        supplierMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierMenuItemActionPerformed(evt);
            }
        });
        inventoryMenu.add(supplierMenuItem);

        jMenuBar1.add(inventoryMenu);

        transactionMenu.setText("Transactions");

        receivableMenuItem.setText("Receivables");
        transactionMenu.add(receivableMenuItem);

        requestMenuItem.setText("Request");
        transactionMenu.add(requestMenuItem);

        jMenuBar1.add(transactionMenu);

        systemMenu.setText("System Management");

        userMenuItem.setText("User Management");
        systemMenu.add(userMenuItem);

        logMenuItem.setText("Action Logs");
        systemMenu.add(logMenuItem);

        jMenuBar1.add(systemMenu);

        setJMenuBar(jMenuBar1);

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
        this.itemView = new ItemFormView();
        this.desktopPaneMain.add(this.itemView);
        this.itemView.show();
    }//GEN-LAST:event_itemMenuItemActionPerformed

    private void categoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryMenuItemActionPerformed
        CategoryView categoryView = new CategoryView(this, true);
        categoryView.setLocationRelativeTo(null);
        categoryView.setVisible(true);
    }//GEN-LAST:event_categoryMenuItemActionPerformed

    private void supplierMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierMenuItemActionPerformed
        this.supplierView = new SupplierView();
        this.desktopPaneMain.add(this.supplierView);
        try {
            supplierView.setMaximum(true);
          } catch (PropertyVetoException e) {
            // Vetoed by internalFrame
            // ... possibly add some handling for this case
          }
        this.supplierView.setBorder(null);
        this.supplierView.setVisible(true);
        
    }//GEN-LAST:event_supplierMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainView main = new MainView();
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem categoryMenuItem;
    private javax.swing.JDesktopPane desktopPaneMain;
    private javax.swing.JMenu inventoryMenu;
    private javax.swing.JMenuItem itemMenuItem;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JMenuItem logMenuItem;
    private javax.swing.JMenuItem receivableMenuItem;
    private javax.swing.JMenuItem requestMenuItem;
    private javax.swing.JMenuItem supplierMenuItem;
    private javax.swing.JMenu systemMenu;
    private javax.swing.JMenu transactionMenu;
    private javax.swing.JMenuItem userMenuItem;
    // End of variables declaration//GEN-END:variables
}
