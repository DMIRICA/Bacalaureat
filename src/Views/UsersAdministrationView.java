package Views;

import Entity.User;
import SQLConnection.DBConnection;
import Singleton.Singleton;
import Utility.EncryptService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ynad
 */
public final class UsersAdministrationView extends javax.swing.JPanel {
    
    private User helper;
    private boolean AddOption;
    private String SearchString;
    
    public UsersAdministrationView() {
        initComponents();
        init();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UsersListPanel = new javax.swing.JScrollPane();
        UsersList = new javax.swing.JTable();
        TitleLabel = new javax.swing.JLabel();
        SearchLabel = new javax.swing.JLabel();
        SeachField = new javax.swing.JTextField();
        AccountInfoPanel = new javax.swing.JPanel();
        UsernameLabel = new javax.swing.JLabel();
        UserTypeLabel = new javax.swing.JLabel();
        ActiveStatusLabel = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        UserTypeCB = new javax.swing.JComboBox();
        ActiveStatusCB = new javax.swing.JComboBox();
        PasswordLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        PersonalInfoPanel = new javax.swing.JPanel();
        LastNameLabel = new javax.swing.JLabel();
        FirstNameField = new javax.swing.JTextField();
        FunctionField = new javax.swing.JTextField();
        PhoneField = new javax.swing.JTextField();
        FirstNameLabel = new javax.swing.JLabel();
        LastNameField = new javax.swing.JTextField();
        FunctionLabel = new javax.swing.JLabel();
        PhoneLabel = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        Cancel_AddAction = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        UsersList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Tip User", "Cont Activ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UsersList.getTableHeader().setReorderingAllowed(false);
        UsersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsersListMouseClicked(evt);
            }
        });
        UsersListPanel.setViewportView(UsersList);

        TitleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Administrare Useri");

        SearchLabel.setText("  Cauta");

        SeachField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        AccountInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informatii cont", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14))); // NOI18N

        UsernameLabel.setText("Username");

        UserTypeLabel.setText("Tip user");

        ActiveStatusLabel.setText("Cont Activ");

        UsernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UsernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameFieldFocusLost(evt);
            }
        });

        UserTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        UserTypeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserTypeCBActionPerformed(evt);
            }
        });

        ActiveStatusCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ActiveStatusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActiveStatusCBActionPerformed(evt);
            }
        });

        PasswordLabel.setText("Password");

        PasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout AccountInfoPanelLayout = new javax.swing.GroupLayout(AccountInfoPanel);
        AccountInfoPanel.setLayout(AccountInfoPanelLayout);
        AccountInfoPanelLayout.setHorizontalGroup(
            AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UsernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserTypeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ActiveStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(UsernameField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserTypeCB, 0, 165, Short.MAX_VALUE)
                    .addComponent(ActiveStatusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordField))
                .addContainerGap())
        );
        AccountInfoPanelLayout.setVerticalGroup(
            AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AccountInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ActiveStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ActiveStatusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PersonalInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informatii Personale", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14))); // NOI18N

        LastNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LastNameLabel.setText("Prenume");

        FirstNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FirstNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                FirstNameFieldFocusLost(evt);
            }
        });

        FunctionField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FunctionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                FunctionFieldFocusLost(evt);
            }
        });

        PhoneField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PhoneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneFieldFocusLost(evt);
            }
        });

        FirstNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FirstNameLabel.setText("Nume");

        LastNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LastNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                LastNameFieldFocusLost(evt);
            }
        });

        FunctionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FunctionLabel.setText("Functie");

        PhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PhoneLabel.setText("Telefon");

        javax.swing.GroupLayout PersonalInfoPanelLayout = new javax.swing.GroupLayout(PersonalInfoPanel);
        PersonalInfoPanel.setLayout(PersonalInfoPanelLayout);
        PersonalInfoPanelLayout.setHorizontalGroup(
            PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersonalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FunctionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(FirstNameField)
                    .addComponent(FunctionField)
                    .addComponent(PhoneField))
                .addGap(18, 18, 18))
        );
        PersonalInfoPanelLayout.setVerticalGroup(
            PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PersonalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FunctionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FunctionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddButton.setText("Adauga user");
        AddButton.setPreferredSize(new java.awt.Dimension(85, 23));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Salveaza");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        Cancel_AddAction.setText("Anuleaza");
        Cancel_AddAction.setPreferredSize(new java.awt.Dimension(85, 23));
        Cancel_AddAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel_AddActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(SearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeachField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cancel_AddAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(UsersListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AccountInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PersonalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchLabel)
                    .addComponent(SeachField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel_AddAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AccountInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PersonalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addComponent(UsersListPanel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UsersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsersListMouseClicked
        DefaultTableModel model = (DefaultTableModel) UsersList.getModel();
        helper = User.getUserFromList((String) model.getValueAt(UsersList.getSelectedRow(),0), Singleton.getInstance().AllUsers);
        if(helper != null){
            UsernameField.setText(helper.getUsername());
            PasswordField.setText("");
            UserTypeCB.setSelectedItem(helper.getType());
            ActiveStatusCB.setSelectedItem(helper.isActive());
            FirstNameField.setText(helper.getFirstName());
            LastNameField.setText(helper.getLastName());
            FunctionField.setText(helper.getFunction());
            PhoneField.setText(helper.getPhone());
        }
    }//GEN-LAST:event_UsersListMouseClicked

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        
        // WHEN WE ADD A NEW USER
        if(AddOption){
            if(isAbleToSave()){
                try {
                    Connection con = DBConnection.getConnection();
                    Statement st = con.createStatement();
                    String query = "Insert into UserProfiles values('"+helper.getFirstName()+"','"+helper.getLastName()+"','"+helper.getFunction()+
                                   "','"+helper.getPhone()+"')";
                    st.executeUpdate(query);
                    query = "Select ID from UserProfiles where Nume='"+helper.getFirstName()+"' AND"+
                            " Prenume='"+helper.getLastName()+"' AND Functie='"+helper.getFunction()+"' AND Telefon='"+helper.getPhone()+"'";
                    ResultSet rs = st.executeQuery(query);
                    if(rs.next()){
                        helper.setProfileID(rs.getInt("ID"));
                    }
                    query = "Insert into Users values('"+helper.getUsername()+"','"+EncryptService.getHashOfString(helper.getPassword())+"',"+
                            getUserTypeIDByType(UserTypeCB.getSelectedItem().toString())+","+helper.getProfileID()+",'"+helper.isActive()+"')";
                    st.executeUpdate(query);
                      clearJTable();
                      Singleton.getInstance().refreshUserList();
                      populateTable(Singleton.getInstance().AllUsers);
                      AddOption=false;
                      AddButton.setEnabled(true);
                      UsersList.setEnabled(true);
                      UsersList.getSelectionModel().clearSelection();
                      Cancel_AddAction.setVisible(false);
                      resetTextFields();
                }catch(ClassNotFoundException | SQLException | NoSuchAlgorithmException e){
                 Logger.getLogger(UsersAdministrationView.class.getName()).log(Level.SEVERE, null, e);
            }
            }else{
                JOptionPane.showMessageDialog(null,"Toate campurile sunt obligatorii!");
            }
        // WHEN WE UPDATE A USER
        }else{
            try{
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                String query1 = "Update Users set Username='"+helper.getUsername()+"',Password='"+helper.getPassword()+
                        "',UserType_ID=(Select ID from UserTypes where tip='"+helper.getType()+"'),Active='"+helper.isActive()+"' where ID = "+helper.getID();
                String query2 = "Update UserProfiles set Nume='"+helper.getFirstName()+"',Prenume='"+helper.getLastName()+
                        "',Functie='"+helper.getFunction()+"',Telefon='"+helper.getPhone()+"' where ID="+helper.getProfileID();
                int answer = JOptionPane.showConfirmDialog(null, "Sunteti sigur de modificari?","Update",JOptionPane.YES_NO_OPTION);
                if(answer == 0){
                    st.executeUpdate(query1);
                    st.executeUpdate(query2);
                    resetAfterUpdateOrAdd();
                    JOptionPane.showMessageDialog(null, "Salvat!");
                    clearJTable();
                    populateTable(Singleton.getInstance().AllUsers);
                }else{
                    Singleton.getInstance().refreshUserList();
                    clearJTable();
                    populateTable(Singleton.getInstance().AllUsers);
                    resetTextFields();
                }
            }catch(ClassNotFoundException | SQLException e){
                 Logger.getLogger(UsersAdministrationView.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void FirstNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FirstNameFieldFocusLost
        if(!isStringEmpty(FirstNameField.getText())){
            helper.setFirstName(FirstNameField.getText());
        }
    }//GEN-LAST:event_FirstNameFieldFocusLost

    private void UsernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFieldFocusLost
       if(!isStringEmpty(UsernameField.getText())){
            helper.setUsername(UsernameField.getText());
        }
    }//GEN-LAST:event_UsernameFieldFocusLost

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost
        if(!isStringEmpty(PasswordField.getText())){
            try {
                helper.setPassword(EncryptService.getHashOfString(PasswordField.getText()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsersAdministrationView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_PasswordFieldFocusLost

    private void LastNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastNameFieldFocusLost
        if(!isStringEmpty(LastNameField.getText())){
            helper.setLastName(LastNameField.getText());
        }
    }//GEN-LAST:event_LastNameFieldFocusLost

    private void FunctionFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FunctionFieldFocusLost
        if(!isStringEmpty(FunctionField.getText())){
            helper.setFunction(FunctionField.getText());
        }
    }//GEN-LAST:event_FunctionFieldFocusLost

    private void PhoneFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneFieldFocusLost
        if(!isStringEmpty(PhoneField.getText())){
        helper.setPhone(PhoneField.getText());
        }
    }//GEN-LAST:event_PhoneFieldFocusLost

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        AddOption = true;
        SaveButton.setEnabled(true);
        resetAfterUpdateOrAdd();
        addNewBlankRow();
        selectLastRow();
        AddButton.setEnabled(false);
        UsersList.setEnabled(false);
        Cancel_AddAction.setVisible(true);
    }//GEN-LAST:event_AddButtonActionPerformed

    private void UserTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserTypeCBActionPerformed
        if(helper != null && isRowSelected()){
            helper.setType(UserTypeCB.getSelectedItem().toString());
            DefaultTableModel model = (DefaultTableModel) UsersList.getModel();
            model.setValueAt(UserTypeCB.getSelectedItem().toString(), UsersList.getSelectedRow(), 1);
        }
    }//GEN-LAST:event_UserTypeCBActionPerformed

    private void ActiveStatusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActiveStatusCBActionPerformed
        if(helper != null && isRowSelected()){
            helper.setActive((boolean)ActiveStatusCB.getSelectedItem());
            DefaultTableModel model = (DefaultTableModel) UsersList.getModel();
            model.setValueAt(ActiveStatusCB.getSelectedItem().toString(), UsersList.getSelectedRow(), 2);
        }
    }//GEN-LAST:event_ActiveStatusCBActionPerformed

    private void Cancel_AddActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel_AddActionActionPerformed
        AddOption = false;
        SaveButton.setEnabled(false);
        resetAfterUpdateOrAdd();
        deleteLastRow();
        AddButton.setEnabled(true);
        UsersList.setEnabled(true);
        Cancel_AddAction.setVisible(false);
    }//GEN-LAST:event_Cancel_AddActionActionPerformed
    
    private void deleteLastRow(){
        DefaultTableModel model = (DefaultTableModel)this.UsersList.getModel();
        model.removeRow(UsersList.getRowCount()-1);
    }
    
    public void addNewBlankRow(){
        DefaultTableModel model = (DefaultTableModel)this.UsersList.getModel();
        model.addRow(new Object[]{"","",""});
    }
    
    public void selectLastRow(){
        ListSelectionModel selectionModel = UsersList.getSelectionModel();
        selectionModel.setSelectionInterval(UsersList.getRowCount()-1, UsersList.getRowCount()-1);
    }

    public void init(){
        helper = new User();
        usernameTextChangedListener(UsernameField);
        SearchTextFieldChangedListner(SeachField);
        AddOption = false;
        UserTypeCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().UserTypes.toArray()));
        ActiveStatusCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Active.toArray()));
        populateTable(Singleton.getInstance().AllUsers);
        SaveButton.setEnabled(false);
        Cancel_AddAction.setVisible(false);
        SelectionListener();
    }
    
    private void usernameTextChanged(){
        if(isRowSelected()){
            DefaultTableModel model = (DefaultTableModel) UsersList.getModel();
            model.setValueAt(UsernameField.getText(), UsersList.getSelectedRow(), 0);
        }
    }
    
    public void populateTable(ArrayList<User> x){
        clearJTable();
        DefaultTableModel model = (DefaultTableModel) UsersList.getModel();
        x.stream().forEach((aux) -> {
            model.addRow(new Object[]{aux.getUsername(),aux.getType(),aux.isActive()});
        });
    }
    
    public boolean isAbleToSave(){
        return !(isStringEmpty(UsernameField.getText()) || isStringEmpty(PasswordField.getText()) ||
                 isStringEmpty(FirstNameField.getText()) || isStringEmpty(LastNameField.getText()) ||
                 isStringEmpty(FunctionField.getText()) || isStringEmpty(PhoneField.getText()));
    }
    
    public void SelectionListener(){
        ListSelectionModel listSelectionModel = UsersList.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        SaveButton.setEnabled(!lsm.isSelectionEmpty());
        });
    }
    
    public void resetAfterUpdateOrAdd(){
        UsersList.getSelectionModel().clearSelection();
        helper = new User();
        resetTextFields();
    }
    
    public void resetTextFields(){
        UsernameField.setText("");
        PasswordField.setText("");
        FirstNameField.setText("");
        LastNameField.setText("");
        FunctionField.setText("");
        PhoneField.setText("");
    }
    
    public void clearJTable(){
        DefaultTableModel model = (DefaultTableModel)this.UsersList.getModel();
        model.setRowCount(0);
    }
    
    public boolean isStringEmpty(String x){
        return "".equals(x);
    }
    
    public void usernameTextChangedListener(javax.swing.JTextField x){
        x.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                usernameTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                usernameTextChanged();
            }
        
    });                
    }
    
    public void SearchTextFieldChangedListner(javax.swing.JTextField x){
        x.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                SearchString = x.getText().toLowerCase();
                populateTable(getListFiltred());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                SearchString = x.getText().toLowerCase();
                 if(isStringEmpty(SearchString)){
                    populateTable(Singleton.getInstance().AllUsers);
                }else{
                    populateTable(getListFiltred());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                SearchString = x.getText().toLowerCase();
                populateTable(getListFiltred());

            }
        
    });                
    }

    public boolean isRowSelected(){
        return UsersList.getSelectedRow() != -1;
    }

    public int getUserTypeIDByType(String type){
        switch (type) {
            case "Guest":
                return 1;
            case "Profesor":
                return 2;
            case "Comisie":
                return 3;
            case "Admin":
                return 4;
        }
        return 1;
    }
    
    public ArrayList<User> getListFiltred(){
        ArrayList<User> aux = new ArrayList<>();
        Singleton.getInstance().AllUsers.stream().filter((a) -> (a.getUsername().toLowerCase().contains(SearchString))).forEach((a) -> {
            aux.add(a);
        });
        return aux;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AccountInfoPanel;
    private javax.swing.JComboBox ActiveStatusCB;
    private javax.swing.JLabel ActiveStatusLabel;
    private javax.swing.JButton AddButton;
    private javax.swing.JButton Cancel_AddAction;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JLabel FirstNameLabel;
    private javax.swing.JTextField FunctionField;
    private javax.swing.JLabel FunctionLabel;
    private javax.swing.JTextField LastNameField;
    private javax.swing.JLabel LastNameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPanel PersonalInfoPanel;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JLabel PhoneLabel;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField SeachField;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JComboBox UserTypeCB;
    private javax.swing.JLabel UserTypeLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JTable UsersList;
    private javax.swing.JScrollPane UsersListPanel;
    // End of variables declaration//GEN-END:variables

    
}
