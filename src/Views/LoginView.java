package Views;

import Singleton.Singleton;
import Entity.User;
import SQLConnection.DBConnection;
import Utility.EncryptService;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 *
 * @author ynad
 */
public final class LoginView extends javax.swing.JPanel {

    private MainFrame Frame;                

    public LoginView(MainFrame f) {
        init(f);
        initComponents();
        setListener();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UsernameLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        UsernameField = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(300, 270));

        UsernameLabel.setText("Username");

        PasswordLabel.setText("Password");

        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        PasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });

        UsernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UsernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UsernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(PasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PasswordField)
                            .addComponent(UsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLabel)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginButton)
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        login();
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void UsernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFieldFocusLost
        if(!isStringNullOrBlank(UsernameField.getText())){
            Singleton.getInstance().UserLogged.setUsername(UsernameField.getText().toLowerCase());
            //User.setUsername(UsernameField.getText().toLowerCase());
        }
    }//GEN-LAST:event_UsernameFieldFocusLost

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost
        if(!isStringNullOrBlank(PasswordField.getText())){
           Singleton.getInstance().UserLogged.setPassword(PasswordField.getText());
        }
    }//GEN-LAST:event_PasswordFieldFocusLost
   
    
    public void TextFieldChangedListner(javax.swing.JTextField x){
        x.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
               if(!isStringNullOrBlank(x.getText())){
                   if(x instanceof  JPasswordField){
                       Singleton.getInstance().UserLogged.setPassword(x.getText());
                   }else{
                        Singleton.getInstance().UserLogged.setUsername(x.getText().toLowerCase());
                   }
        }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!isStringNullOrBlank(x.getText())){
                    if(x instanceof JPasswordField){
                        Singleton.getInstance().UserLogged.setPassword(x.getText());
                    }else{
                        Singleton.getInstance().UserLogged.setUsername(x.getText().toLowerCase());
                    }
                }else{
                    if(x instanceof JPasswordField){
                        setGuestPassword();
                    }else{
                        Singleton.getInstance().UserLogged.setUsername("");
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
               if(!isStringNullOrBlank(x.getText())){
                   if(x instanceof  JPasswordField){
                       Singleton.getInstance().UserLogged.setPassword(x.getText());
                   }else{
                        Singleton.getInstance().UserLogged.setUsername(x.getText().toLowerCase());
                   }
                }
            }
    }); 
               
    }
    
    public void resetPasswordField(){
        PasswordField.setText("");
        setGuestPassword();
    }
    
    public void resetUsernameField(){
        Singleton.getInstance().UserLogged.setUsername("");
        UsernameField.setText("");
    }
    
    public void setGuestPassword(){
        Singleton.getInstance().UserLogged.setPassword("guest");
    }
    
    public static boolean isStringNullOrBlank(String param) { 
        return param == null || param.trim().length() == 0;
    }
    
    public void login(){
        try {
            Connection con = DBConnection.getConnection();
            
            if(checkUser(con)){
                Frame.Username_Menu.setText(Singleton.getInstance().UserLogged.getUsername());
                Singleton.getInstance().UserLogged.getUserInfo(con);
                checkUserType(Singleton.getInstance().UserLogged);
                successLogin();
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setListener(){
        EnterKeyListener(UsernameField);
        EnterKeyListener(PasswordField);
        TextFieldChangedListner(UsernameField);
        TextFieldChangedListner(PasswordField);
    }
    
    public void openMainView(){
        try {
            this.setVisible(false);
            Frame.setSize(1440,900);
            Frame.setLocationRelativeTo(null);
            MainView view = new MainView();
            view.getFrame(Frame);
            Frame.setContentPane(view);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(int tip){
        ImageIcon icon = new ImageIcon("Resources\\success.png");
        if(tip == 1){
            JOptionPane.showMessageDialog(null, "Bun venit "+Singleton.getInstance().UserLogged.getUsername()+"!","Succes", 3, icon);
        }else if(tip == 2){
            JOptionPane.showMessageDialog(null, "Parola incorecta!","Failed",0);
        }else if(tip == 3){
            JOptionPane.showMessageDialog(null, "User-ul "+Singleton.getInstance().UserLogged.getUsername()+" nu exista","Failed",0);
        }else if(tip == 4){
            JOptionPane.showMessageDialog(null,"Toate campurile sunt obligatorii!","Failed",0);
        }else if(tip == 5){
            JOptionPane.showMessageDialog(null,"Contul este inactiv.Contactati Adminul!","Failed",0);
        }
    }
    
    public boolean checkUser(Connection conn){
        try {
        if(!isStringNullOrBlank(Singleton.getInstance().UserLogged.getUsername())){
                Statement st = conn.createStatement();
                String query = "Select * from users where username = '"+Singleton.getInstance().UserLogged.getUsername()+"'";
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    Singleton.getInstance().UserLogged.setID(rs.getInt("ID"));
                    if(!isStringNullOrBlank(Singleton.getInstance().UserLogged.getPassword())){
                        if(rs.getString(3).equals(EncryptService.getHashOfString(Singleton.getInstance().UserLogged.getPassword()))){
                            if(rs.getBoolean("Active") == false){
                                showMessage(5);
                                return false;
                            }else{
                                return true;
                            }
                        }else{
                            showMessage(2);
                            resetPasswordField();
                            return false;
                        }
                    }
                }else{
                    showMessage(3);
                    resetPasswordField();
                    resetUsernameField();
                    return false;
                }
            }else{
                showMessage(4);
                resetPasswordField();
                resetUsernameField();
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void checkUserType(User user){
        switch (user.getType()) {
            case "Guest":
                Frame.Administrare_Menu.setVisible(false);
                Frame.Rapoarte_Menu.setVisible(false);
                Frame.Organizare_Menu.setVisible(false);
                Frame.ChangePasswordItem.setVisible(false);
                Frame.AccountInfoItem.setVisible(false);
                break;
            case "Admin":
                Frame.ChangePasswordItem.setVisible(false);
                Frame.AccountInfoItem.setVisible(false);
                Frame.Administrare_Menu.setVisible(true);
                Frame.Rapoarte_Menu.setVisible(true);
                Frame.Organizare_Menu.setVisible(true);
                break;
        }
    }
    
    public void successLogin(){
        showMessage(1);
        resetPasswordField();
        resetUsernameField();
        openMainView();
        Frame.setMenuBarVisible();
    }
    
    public void EnterKeyListener(javax.swing.JTextField x){
        x.addKeyListener(new KeyAdapter() 
    {
        @Override
        public void keyPressed(KeyEvent evt)
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                login();
            }
        }
    });
    }
    
    public void init(MainFrame f){
        
        Frame = f;
        Frame.hideMenuBar();
        setGuestPassword();
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    // End of variables declaration//GEN-END:variables
}
