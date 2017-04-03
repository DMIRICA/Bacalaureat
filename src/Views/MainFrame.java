package Views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Singleton.Singleton;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author ynad
 */
public class MainFrame extends javax.swing.JFrame {

    public MainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, FileNotFoundException, DocumentException {
        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        initComponents();
        //MainView = new MainView();
        //MainView.getFrame(this);
        //setContentPane(new AddCandidate());   
        //setContentPane(new ModifyCandidate());
        setContentPane(new LoginView(this));
        //setContentPane(new RaportView());
        //this.setSize(400, 300);
        //this.setSize(860,580);
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuBar = new javax.swing.JMenuBar();
        Username_Menu = new javax.swing.JMenu();
        ChangePasswordItem = new javax.swing.JMenuItem();
        AccountInfoItem = new javax.swing.JMenuItem();
        LogoutItem = new javax.swing.JMenuItem();
        CloseAppItem = new javax.swing.JMenuItem();
        Rezultate_Menu = new javax.swing.JMenu();
        Organizare_Menu = new javax.swing.JMenu();
        AddCandidateItem = new javax.swing.JMenuItem();
        UpdateCandidateItem = new javax.swing.JMenuItem();
        Rapoarte_Menu = new javax.swing.JMenu();
        GenereazaRaport = new javax.swing.JMenuItem();
        Administrare_Menu = new javax.swing.JMenu();
        UsersAdministration = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(320, 280));
        setResizable(false);

        Username_Menu.setText("Username");

        ChangePasswordItem.setText("Schima parola");
        Username_Menu.add(ChangePasswordItem);

        AccountInfoItem.setText("Informatii cont");
        Username_Menu.add(AccountInfoItem);

        LogoutItem.setText("Logout");
        LogoutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutItemActionPerformed(evt);
            }
        });
        Username_Menu.add(LogoutItem);

        CloseAppItem.setText("Inchide Aplicatia");
        CloseAppItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseAppItemActionPerformed(evt);
            }
        });
        Username_Menu.add(CloseAppItem);

        MenuBar.add(Username_Menu);

        Rezultate_Menu.setText("Rezultate");
        Rezultate_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Rezultate_MenuMouseClicked(evt);
            }
        });
        MenuBar.add(Rezultate_Menu);

        Organizare_Menu.setText("Organizare");

        AddCandidateItem.setText("Adauga Candidat");
        AddCandidateItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCandidateItemActionPerformed(evt);
            }
        });
        Organizare_Menu.add(AddCandidateItem);

        UpdateCandidateItem.setText("Modifica Candidat");
        UpdateCandidateItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCandidateItemActionPerformed(evt);
            }
        });
        Organizare_Menu.add(UpdateCandidateItem);

        MenuBar.add(Organizare_Menu);

        Rapoarte_Menu.setText("Rapoarte");

        GenereazaRaport.setText("Generare Raport");
        GenereazaRaport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenereazaRaportActionPerformed(evt);
            }
        });
        Rapoarte_Menu.add(GenereazaRaport);

        MenuBar.add(Rapoarte_Menu);

        Administrare_Menu.setText("Administrare");

        UsersAdministration.setText("Administrare Useri");
        UsersAdministration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersAdministrationActionPerformed(evt);
            }
        });
        Administrare_Menu.add(UsersAdministration);

        MenuBar.add(Administrare_Menu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsersAdministrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersAdministrationActionPerformed
        setSize(820,650);
        setLocationRelativeTo(null);
        getContentPane().setVisible(false);
        setContentPane(new UsersAdministrationView());
        
    }//GEN-LAST:event_UsersAdministrationActionPerformed

    private void Rezultate_MenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Rezultate_MenuMouseClicked
        try {
            getContentPane().setVisible(false);
            MainView view = new MainView();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
            this.setSize((int)width, (int)height);
            this.setLocationRelativeTo(null);
            setContentPane(new MainView());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Rezultate_MenuMouseClicked

    private void LogoutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutItemActionPerformed
        int answer = JOptionPane.showConfirmDialog(null, "Sunteti sigur ca vreti sa va delogati?","Close",JOptionPane.YES_NO_OPTION);
        if(answer == 0){
            Singleton.getInstance().resetUser();
            this.getContentPane().setVisible(false);
            this.setSize(300, 270);
            this.setLocationRelativeTo(null);
            this.setContentPane(new LoginView(this));
        }
    }//GEN-LAST:event_LogoutItemActionPerformed

    private void CloseAppItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseAppItemActionPerformed
        int answer = JOptionPane.showConfirmDialog(null, "Inchideti aplicatia?","Close",JOptionPane.YES_NO_OPTION);
        if(answer == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_CloseAppItemActionPerformed

    private void AddCandidateItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCandidateItemActionPerformed
       this.getContentPane().setVisible(false);
       this.setContentPane(new AddCandidate());
       this.setSize(620, 430);
       setLocationRelativeTo(null);
    }//GEN-LAST:event_AddCandidateItemActionPerformed

    private void UpdateCandidateItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCandidateItemActionPerformed
        this.getContentPane().setVisible(false);
        this.setContentPane(new ModifyCandidate());
        this.setSize(860,580);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_UpdateCandidateItemActionPerformed

    private void GenereazaRaportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenereazaRaportActionPerformed
       setSize(400,300);
        setLocationRelativeTo(null);
        getContentPane().setVisible(false);
        setLocationRelativeTo(null);
        try {
            setContentPane(new RaportView());
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GenereazaRaportActionPerformed

    public void hideMenuBar(){
        this.MenuBar.setVisible(false);
    }
    
    public void setMenuBarVisible(){
        this.MenuBar.setVisible(true);
    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new MainFrame().setVisible(true);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | FileNotFoundException | DocumentException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JMenuItem AccountInfoItem;
    protected javax.swing.JMenuItem AddCandidateItem;
    protected javax.swing.JMenu Administrare_Menu;
    protected javax.swing.JMenuItem ChangePasswordItem;
    protected javax.swing.JMenuItem CloseAppItem;
    private javax.swing.JMenuItem GenereazaRaport;
    protected javax.swing.JMenuItem LogoutItem;
    protected javax.swing.JMenuBar MenuBar;
    protected javax.swing.JMenu Organizare_Menu;
    protected javax.swing.JMenu Rapoarte_Menu;
    protected javax.swing.JMenu Rezultate_Menu;
    protected javax.swing.JMenuItem UpdateCandidateItem;
    protected javax.swing.JMenu Username_Menu;
    private javax.swing.JMenuItem UsersAdministration;
    // End of variables declaration//GEN-END:variables
}
