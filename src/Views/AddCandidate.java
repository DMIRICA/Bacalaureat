package Views;

import Entity.Candidat;
import Entity.Competenta;
import Entity.Liceu;
import Entity.Proba;
import SQLConnection.DBConnection;
import Singleton.Singleton;
import Utility.Tuple;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ynad
 */
public class AddCandidate extends javax.swing.JPanel {

    private Candidat Candidat;
    private Liceu LiceuAles;
    private ArrayList<Proba> ProbaD;
    
    
    public AddCandidate() {
        initComponents();
        init();
    }

    public void init(){
        JudetCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Counties.toArray()));
        JudetCB.setSelectedItem(null);
        LbModernaCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().LimbiModerna.toArray()));
        LbModernaCB.setSelectedItem(null);
        ProbaD = new ArrayList<>();
        Candidat = new Candidat();
        setCompetente();
        addListeners();
    }
    
    public void addListeners(){
        numeTextFieldChangedListner();
        prenumeTextFieldChangedListner();
        initialatFieldChangedListner();
        nationalitateTextFieldChangedListner();
        anAbsolvireTextFieldChangedListner();
        dataNasteriiTextFieldChangedListner();
    }
    
    public void numeTextFieldChangedListner(){
        NumeTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                Candidat.setNume(NumeTextField.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(NumeTextField.getText())){
                    Candidat.setNume(NumeTextField.getText().toUpperCase());
                }else{
                    Candidat.setNume("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setNume(NumeTextField.getText().toUpperCase());
            }
        
    });                
    }
    
    public void prenumeTextFieldChangedListner(){
        PrenumeTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                Candidat.setPrenume(PrenumeTextField.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(PrenumeTextField.getText())){
                    Candidat.setPrenume(PrenumeTextField.getText().toUpperCase());
                }else{
                    Candidat.setPrenume("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setPrenume(PrenumeTextField.getText().toUpperCase());
            }
        
    });                
    }
    
    public void initialatFieldChangedListner(){
        InitTataTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                Candidat.setInitiala_Tata(InitTataTextField.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(InitTataTextField.getText())){
                    Candidat.setInitiala_Tata(InitTataTextField.getText().toUpperCase());
                }else{
                    Candidat.setInitiala_Tata("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setInitiala_Tata(InitTataTextField.getText().toUpperCase());
            }
        
    });                
    }
    
    public void nationalitateTextFieldChangedListner(){
        NationalitateTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                Candidat.setNationalitate(NationalitateTextField.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(NationalitateTextField.getText())){
                    Candidat.setNationalitate(NationalitateTextField.getText().toUpperCase());
                }else{
                    Candidat.setNationalitate("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setNationalitate(NationalitateTextField.getText().toUpperCase());
            }
        
    });                
    }
    
    public void anAbsolvireTextFieldChangedListner(){
        AbsolvireTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                 Candidat.setAn_Absolvire(Integer.parseInt(AbsolvireTextField.getText().toUpperCase()));
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(AbsolvireTextField.getText())){
                    Candidat.setAn_Absolvire(Integer.parseInt(AbsolvireTextField.getText().toUpperCase()));
                }else{
                    Candidat.setAn_Absolvire(0);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setAn_Absolvire(Integer.parseInt(AbsolvireTextField.getText().toUpperCase()));
            }
        
    });                
    }
    
    public void dataNasteriiTextFieldChangedListner(){
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                Candidat.setDataNasterii(dateEditor.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Candidat.setDataNasterii(dateEditor.getText().toUpperCase());
            }
        
    });                
    }
    
    public void adaugaCandidat() throws SQLException{
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Insert into Candidati values('"+Candidat.getNume()+"','"
                    +Candidat.getPrenume()+"','"+Candidat.getInitiala_Tata()+"',"
                    +Candidat.getAn_Absolvire()+",'"+Candidat.getNationalitate()+"','"
                    +Candidat.getDataNasterii()+"',"+Candidat.getLiceuID()+")";
                    st.executeUpdate(query);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean ableToAdd(){
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        return !(isStringEmpty(NumeTextField.getText()) || isStringEmpty(PrenumeTextField.getText()) ||
                 isStringEmpty(InitTataTextField.getText()) || isStringEmpty(NationalitateTextField.getText()) ||
                 isStringEmpty(AbsolvireTextField.getText()) || isStringEmpty(dateEditor.getText())
                 || SpecializareCB.getSelectedItem() == null || LbModernaCB.getSelectedItem() == null
                 || ProbaDCB.getSelectedItem() == null);
    }
    
    public boolean isStringEmpty(String x){
        return "".equals(x);
    }
     
    public void loadProbaDCB(){
       try {
            ProbaD.clear();
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM MATERII WHERE Specializare_ID="+Candidat.getSpecializareID()+" AND Proba='D'";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                ProbaD.add(new Proba(rs.getInt("ID"),rs.getString("Denumire"),0.0f));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setProba(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "select * from materii where Specializare_ID ="+Candidat.getSpecializareID()+" and (Proba = 'A' OR Proba = 'C')";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                switch (rs.getString("Proba")) {
                    case "A":
                        Candidat.setProba1(new Proba(rs.getInt("ID"),rs.getString("Denumire"),0.0f));
                        break;
                    case "C":
                        Candidat.setProba2(new Proba(rs.getInt("ID"),rs.getString("Denumire"),0.0f));
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCompetente(){
        Candidat.setCLbRomana(new Competenta(1,"","Limba Romana"));
        Candidat.setCInformatica(new Competenta(4,"","Competenta Digitala"));
    }
    
    public void adaugaRezultate(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Insert into Rezultate values("+Candidat.getID()+
                    ","+Candidat.getProba1().getID()+",NULL,NULL,NULL,"+Singleton.getInstance().UserLogged.getID()+")";
            st.executeUpdate(query);
            query = "Insert into Rezultate values("+Candidat.getID()+
                    ","+Candidat.getProba2().getID()+",NULL,NULL,NULL,"+Singleton.getInstance().UserLogged.getID()+")";
            st.executeUpdate(query);
            query = "Insert into Rezultate values("+Candidat.getID()+
                    ","+Candidat.getProba3().getID()+",NULL,NULL,NULL,"+Singleton.getInstance().UserLogged.getID()+")";
            st.executeUpdate(query);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void adaugaCalificative(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Insert into Calificative values("+Candidat.getID()+
                    ","+Candidat.getCLbRomana().getID()+",NULL)";
            st.executeUpdate(query);
            query = "Insert into Calificative values("+Candidat.getID()+
                    ","+Candidat.getCLbModerna().getID()+",NULL)";
            st.executeUpdate(query);
            query = "Insert into Calificative values("+Candidat.getID()+
                    ","+Candidat.getCInformatica().getID()+",NULL)";
            st.executeUpdate(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getCandidatDupaAdaugare(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Select * from Candidati where ID=(SELECT MAX(ID) FROM CANDIDATI)";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                Candidat.setID(rs.getInt("ID"));
                Candidat.getCandidatInfo(con);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void resetFields(){
        JudetCB.setSelectedIndex(-1);
        LiceuCB.setSelectedIndex(-1);
        SpecializareCB.setSelectedIndex(-1);
        ProbaDCB.setSelectedIndex(-1);   
        NumeTextField.setText("");
        PrenumeTextField.setText("");
        InitTataTextField.setText("");
        LbModernaCB.setSelectedIndex(-1);
        NationalitateTextField.setText("");
        AbsolvireTextField.setText("");
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.setText("");
        Candidat = new Candidat();
    }
    
    public void adaugaUser(){
        if(ableToAdd()){
            try {
                adaugaCandidat();
                Singleton.getInstance().IncarcaRezultate();
                getCandidatDupaAdaugare();
                adaugaCalificative();
                adaugaRezultate();
                Singleton.getInstance().IncarcaRezultate();
                JOptionPane.showMessageDialog(null, "Salvat!","Succes", 3, new ImageIcon("Resources\\success.png"));
            } catch (SQLException ex) {
                Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Toate campurile sunt obligatorii!");
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleLabel = new javax.swing.JLabel();
        JudetCB = new javax.swing.JComboBox();
        JudetLabel = new javax.swing.JLabel();
        LiceulLabel = new javax.swing.JLabel();
        LiceuCB = new javax.swing.JComboBox();
        SpecializareLabel = new javax.swing.JLabel();
        SpecializareCB = new javax.swing.JComboBox();
        AbsolvireLabel = new javax.swing.JLabel();
        AbsolvireTextField = new javax.swing.JTextField();
        NumeLabel = new javax.swing.JLabel();
        NumeTextField = new javax.swing.JTextField();
        PrenumeLabel = new javax.swing.JLabel();
        PrenumeTextField = new javax.swing.JTextField();
        InitTataLabel = new javax.swing.JLabel();
        InitTataTextField = new javax.swing.JTextField();
        DataNasteriiLabel = new javax.swing.JLabel();
        DateChooser = new com.toedter.calendar.JDateChooser();
        NationalitateLabel = new javax.swing.JLabel();
        NationalitateTextField = new javax.swing.JTextField();
        LbModernaLabel = new javax.swing.JLabel();
        LbModernaCB = new javax.swing.JComboBox();
        ProbaDLabel = new javax.swing.JLabel();
        ProbaDCB = new javax.swing.JComboBox();
        AdaugaButton = new javax.swing.JButton();

        TitleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Adaugare candidati");

        JudetCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JudetCBActionPerformed(evt);
            }
        });

        JudetLabel.setText("Judet");

        LiceulLabel.setText("Liceu");

        LiceuCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiceuCBActionPerformed(evt);
            }
        });

        SpecializareLabel.setText("Specializare");

        SpecializareCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpecializareCBActionPerformed(evt);
            }
        });

        AbsolvireLabel.setText("An absolvire");

        AbsolvireTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AbsolvireTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AbsolvireTextFieldKeyTyped(evt);
            }
        });

        NumeLabel.setText("Nume");

        NumeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        PrenumeLabel.setText("Prenume");

        PrenumeTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        InitTataLabel.setText("Initiala tata");

        InitTataTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        DataNasteriiLabel.setText("Data nasterii");

        DateChooser.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.setHorizontalAlignment(JTextField.CENTER);

        NationalitateLabel.setText("Nationalitate");

        NationalitateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        LbModernaLabel.setText("Limba moderna");

        LbModernaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LbModernaCBActionPerformed(evt);
            }
        });

        ProbaDLabel.setText("Optiune proba D");

        ProbaDCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProbaDCBActionPerformed(evt);
            }
        });

        AdaugaButton.setText("Adauga");
        AdaugaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdaugaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SpecializareLabel)
                            .addComponent(NumeLabel)
                            .addComponent(PrenumeLabel)
                            .addComponent(InitTataLabel))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(ProbaDLabel)
                                                    .addComponent(NationalitateLabel)
                                                    .addComponent(LbModernaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(DataNasteriiLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(AbsolvireLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(38, 38, 38)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NationalitateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AbsolvireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ProbaDCB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LbModernaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(InitTataTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PrenumeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NumeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SpecializareCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 151, Short.MAX_VALUE)
                                        .addComponent(LiceuCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(AdaugaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(79, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JudetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LiceulLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JudetLabel)
                    .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LbModernaLabel)
                    .addComponent(LbModernaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LiceulLabel)
                    .addComponent(LiceuCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProbaDLabel)
                    .addComponent(ProbaDCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpecializareLabel)
                    .addComponent(SpecializareCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NationalitateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NationalitateLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeLabel)
                    .addComponent(NumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AbsolvireLabel)
                    .addComponent(AbsolvireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PrenumeLabel)
                        .addComponent(PrenumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DataNasteriiLabel))
                    .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InitTataLabel)
                    .addComponent(InitTataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(AdaugaButton)
                .addContainerGap(123, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JudetCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JudetCBActionPerformed
        if(JudetCB.getSelectedItem() != null){
            LiceuCB.setModel(new DefaultComboBoxModel(Liceu.getLiceeJudet(JudetCB.getSelectedItem().toString()).toArray()));
            LiceuCB.setSelectedItem(null);
            SpecializareCB.setSelectedItem(null);
        }
    }//GEN-LAST:event_JudetCBActionPerformed

    private void LiceuCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiceuCBActionPerformed

        if(LiceuCB.getSelectedItem() != null){         
          Object x = LiceuCB.getItemAt(LiceuCB.getSelectedIndex());
             LiceuAles = (Liceu)x;
             SpecializareCB.setModel(new DefaultComboBoxModel(LiceuAles.getSpecializari().toArray()));
             SpecializareCB.setSelectedItem(null);
        }
    }//GEN-LAST:event_LiceuCBActionPerformed

    private void AbsolvireTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbsolvireTextFieldKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_ENTER )){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_AbsolvireTextFieldKeyTyped

    private void SpecializareCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpecializareCBActionPerformed
        if(SpecializareCB.getSelectedItem() != null){
            Candidat.setSpecializare(SpecializareCB.getSelectedItem().toString());
            for(Tuple aux:LiceuAles.getSpecializari()){
                if(Candidat.getSpecializare().equals(aux.getName())){
                    Candidat.setSpecializareID(aux.getID());
                    Candidat.setLiceuID(Singleton.getInstance().getLiceuID(LiceuAles.getID(),Candidat.getSpecializareID()));
                    break;
                }
            }
           setProba();
           loadProbaDCB();
           ProbaDCB.setModel(new DefaultComboBoxModel(ProbaD.toArray()));
           ProbaDCB.setSelectedItem(null);
        }
    }//GEN-LAST:event_SpecializareCBActionPerformed

    private void AdaugaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdaugaButtonActionPerformed
        adaugaUser();
        resetFields();
    }//GEN-LAST:event_AdaugaButtonActionPerformed

    private void LbModernaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LbModernaCBActionPerformed
       if(LbModernaCB.getSelectedItem() != null){
           if("Engleza".equals(LbModernaCB.getSelectedItem().toString())){
               Candidat.setCLbModerna(new Competenta(2,"","Engleza"));
           }else{
               Candidat.setCLbModerna(new Competenta(3,"","Franceza"));
           }
       }
    }//GEN-LAST:event_LbModernaCBActionPerformed

    private void ProbaDCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProbaDCBActionPerformed
        if(ProbaDCB.getSelectedItem() != null){
            Candidat.setProba3((Proba)ProbaDCB.getItemAt(ProbaDCB.getSelectedIndex()));
        }
    }//GEN-LAST:event_ProbaDCBActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AbsolvireLabel;
    private javax.swing.JTextField AbsolvireTextField;
    private javax.swing.JButton AdaugaButton;
    private javax.swing.JLabel DataNasteriiLabel;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JLabel InitTataLabel;
    private javax.swing.JTextField InitTataTextField;
    private javax.swing.JComboBox JudetCB;
    private javax.swing.JLabel JudetLabel;
    private javax.swing.JComboBox LbModernaCB;
    private javax.swing.JLabel LbModernaLabel;
    private javax.swing.JComboBox LiceuCB;
    private javax.swing.JLabel LiceulLabel;
    private javax.swing.JLabel NationalitateLabel;
    private javax.swing.JTextField NationalitateTextField;
    private javax.swing.JLabel NumeLabel;
    private javax.swing.JTextField NumeTextField;
    private javax.swing.JLabel PrenumeLabel;
    private javax.swing.JTextField PrenumeTextField;
    private javax.swing.JComboBox ProbaDCB;
    private javax.swing.JLabel ProbaDLabel;
    private javax.swing.JComboBox SpecializareCB;
    private javax.swing.JLabel SpecializareLabel;
    private javax.swing.JLabel TitleLabel;
    // End of variables declaration//GEN-END:variables
}
