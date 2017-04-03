package Views;

import Entity.Candidat;
import Entity.Proba;
import SQLConnection.DBConnection;
import Singleton.Singleton;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;


public final class ModifyCandidate extends javax.swing.JPanel {

   Candidat x;
   String SearchString;
   boolean blocked;

    public ModifyCandidate() {
        x = new Candidat();
        initComponents();
        init();
    }

    public void init(){
        SearchString = "";
        Table.setRowHeight(30);
        SelectionListener();
        disbleFixedFields();
        setCBModels();
        populateTable(Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()));
        setStuffEnabled();
        addListeners();
        
    }
    
    public void setStuffEnabled(){
        blocked = false;
        NumeRadioButton.setSelected(true);
        SalveazaButton.setEnabled(false);
        StergeButton.setEnabled(false);
        EditButton.setEnabled(false);
        InformatiiCandidatPanel.setVisible(false);
        ProbeScrisePanel.setVisible(false);
        CompetentePanel.setVisible(false);
        CancelButton.setEnabled(false);
    }
    
    public void setCBModels(){
        JudetCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Counties.toArray()));
        CalificativACB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Calficative.toArray()));
        CalificativACB.setSelectedItem(null);
        CalificativCCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Calficative.toArray()));
        CalificativCCB.setSelectedItem(null);
        ((JLabel)CalificativACB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)CalificativCCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        //((JLabel)CalificativACB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void populateTable(ArrayList<Candidat> lista){
        clearTable();
        DefaultTableModel model = (DefaultTableModel)Table.getModel();
        lista.stream().forEach((x) -> {
            model.addRow(new Object[]{x.getNume()+" "+x.getInitiala_Tata()+" "+x.getPrenume(),x.getLiceu().getNume()});
        });
    }
    
    public ArrayList<Candidat> getListFiltred(){
        ArrayList<Candidat> aux = new ArrayList<>();
        if(NumeRadioButton.isSelected()){
            Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()).stream().filter((a) -> (a.getNume().toUpperCase().contains(SearchString))).forEach((a) -> {
                aux.add(a);
            
            });
        }
        if(LiceuRadioButton.isSelected()){
            Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()).stream().filter((a) -> (a.getLiceu().getNume().toUpperCase().contains(SearchString))).forEach((a) -> {
                aux.add(a);
            });
        }
        return aux;
    }
    
    public void disbleFixedFields(){
        ProbaATF.setEditable(false);
        CompetentaATF.setEditable(false);
        CompetentaCTF.setEditable(false);
        ProbaCTF.setEditable(false);
        LiceuTF.setEditable(false);
        SpecializareTF.setEditable(false);
        ProbaDTF.setEditable(false);
        CompetentaBTF.setEditable(false);
    }
    
    public void getCanidatFromTable(){
         DefaultTableModel model = (DefaultTableModel) Table.getModel();
         String[] aux = new String[3];
         aux = model.getValueAt(Table.getSelectedRow(), 0).toString().split(" ");
         x = Candidat.getCandidatFromList(aux[0],aux[1],aux[2],model.getValueAt(Table.getSelectedRow(), 1).toString(), JudetCB.getSelectedItem().toString());
    }
    
    public void clearTable(){
       DefaultTableModel model = (DefaultTableModel)Table.getModel();
       model.setRowCount(0);
    }
    
    public void resetFields(){
        ProbaATF.setText("");
        ProbaCTF.setText("");
        NProbaATF.setText("");
        NProbaCTF.setText("");
        NProbaDTF.setText("");
        NumeTF.setText("");
        PrenumeTF.setText("");
        InitialaTF.setText("");
        NationalitateTF.setText("");
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.setText("");
        AbsolvireTF.setText("");
        CompetentaCTF.setText("");
        LiceuTF.setText("");
        SpecializareTF.setText("");
        ProbaDTF.setText("");
        CompetentaATF.setText("");
        CompetentaBTF.setText("");
        CalificativBTF.setText("");
        CalificativACB.setSelectedItem(null);
        CalificativCCB.setSelectedItem(null);
    }
    
    public int getCalificativString(String s){
        switch(s){
            case "Utilizator experimentat" : return 0;
            case "Utilizator avansat"      : return 1;
            case "Utilizator mediu"        : return 2;
            case "Utilizator începator"    : return 3;
        }
        return -1;
    }

    public void putInfoInView(){
        NumeTF.setText(x.getNume());
        PrenumeTF.setText(x.getPrenume());
        InitialaTF.setText(x.getInitiala_Tata());
        NationalitateTF.setText(x.getNationalitate());
        AbsolvireTF.setText(String.valueOf(x.getAn_Absolvire()));
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.setText(x.getDataNasterii());
        LiceuTF.setText(x.getLiceu().getNume());
        SpecializareTF.setText(x.getSpecializare());
        ProbaATF.setText(x.getProba1().getDenumire());
        ProbaCTF.setText(x.getProba2().getDenumire());
        ProbaDTF.setText(x.getProba3().getDenumire());
        CompetentaCTF.setText(x.getCInformatica().getDenumire());
        CompetentaATF.setText(x.getCLbRomana().getDenumire());
        CompetentaBTF.setText(x.getCLbModerna().getDenumire());
        NProbaATF.setText("0.0".equals(String.valueOf(x.getProba1().getNota())) ? "" : String.valueOf(x.getProba1().getNota()));
        NProbaCTF.setText("0.0".equals(String.valueOf(x.getProba2().getNota())) ? "" : String.valueOf(x.getProba2().getNota()));
        NProbaDTF.setText("0.0".equals(String.valueOf(x.getProba3().getNota())) ? "" : String.valueOf(x.getProba3().getNota()));
        CalificativBTF.setText(x.getCLbModerna().getCalificativ());
        if(x.getCLbModerna().getCalificativ() != null){
            CalificativACB.setSelectedIndex(getCalificativString(x.getCLbRomana().getCalificativ()));
        }else{
            CalificativACB.setSelectedIndex(-1);
        }
        if(x.getCInformatica().getCalificativ() != null){
            CalificativCCB.setSelectedIndex(getCalificativString(x.getCInformatica().getCalificativ()));
        }else{
            CalificativCCB.setSelectedIndex(-1);
        }
    }
    
    public void SearchTextFieldChangedListner(javax.swing.JTextField x){
        x.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                SearchString = x.getText().toUpperCase();
                populateTable(getListFiltred());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                SearchString = x.getText().toUpperCase();
                 if(isStringEmpty(SearchString)){
                    populateTable(Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()));

                }else{
                    populateTable(getListFiltred());

                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                SearchString = x.getText().toUpperCase();
                populateTable(getListFiltred());
            }
        
    });                
    }
    
    public void SelectionListener(){
        ListSelectionModel listSelectionModel = Table.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        //SalveazaButton.setEnabled(!lsm.isSelectionEmpty());
        StergeButton.setEnabled(!lsm.isSelectionEmpty());
        EditButton.setEnabled(!lsm.isSelectionEmpty());
        });
    }
    
    public boolean isStringEmpty(String x){
        return "".equals(x);
    }
    
    private void deleteCandidate(Candidat c){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Delete from Calificative where Candidat_ID="+x.getID();
            st.executeUpdate(query);
            query = "Delete from Rezultate where Candidat_ID="+x.getID();
            st.executeUpdate(query);
            query = "Delete from Candidati where ID="+x.getID();
            st.executeUpdate(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void NumeTextChangedListener(javax.swing.JTextField jtf){
        jtf.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                
                NameTextFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(NumeTF.getText())){
                    NameTextFieldChanged();
                }
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                NameTextFieldChanged();
            }
        
    });                
    }
    
    public void PreumeTextChangedListener(javax.swing.JTextField jtf){
        jtf.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                
                PrenumeTextFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(NumeTF.getText())){
                    PrenumeTextFieldChanged();
                }
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                PrenumeTextFieldChanged();
            }
        
    });                
    }
    
    public void InitialaTextChangedListener(javax.swing.JTextField jtf){
        jtf.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                
                InitialaTextFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if(!"".equals(NumeTF.getText())){
                    InitialaTextFieldChanged();
                }
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                InitialaTextFieldChanged();
            }
        
    });                
    }
    
    public void dataNasteriiTextFieldChangedListner(){
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent de) {
                x.setDataNasterii(dateEditor.getText().toUpperCase());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                x.setDataNasterii(dateEditor.getText().toUpperCase());
            }
        
    });                
    }
    
    public void addListeners(){
        dataNasteriiTextFieldChangedListner();
        SearchTextFieldChangedListner(SearchTextField);
        PreumeTextChangedListener(PrenumeTF);
        InitialaTextChangedListener(InitialaTF);
        NumeTextChangedListener(NumeTF);

    }
    
    private void NameTextFieldChanged(){
        if(isRowSelected()){
            DefaultTableModel model = (DefaultTableModel) Table.getModel();
            model.setValueAt(NumeTF.getText()+" "+x.getInitiala_Tata()+" "+x.getPrenume(), Table.getSelectedRow(), 0);
            x.setNume(NumeTF.getText());
        }
    }
    
    private void PrenumeTextFieldChanged(){
        if(isRowSelected()){
            DefaultTableModel model = (DefaultTableModel) Table.getModel();
            model.setValueAt(x.getNume()+" "+x.getInitiala_Tata()+" "+PrenumeTF.getText(), Table.getSelectedRow(), 0);
            x.setPrenume(PrenumeTF.getText());
            
        }
    }
    
    private void InitialaTextFieldChanged(){
        if(isRowSelected()){
            DefaultTableModel model = (DefaultTableModel) Table.getModel();
            model.setValueAt(x.getNume()+" "+InitialaTF.getText()+" "+x.getPrenume(), Table.getSelectedRow(), 0);
            x.setInitiala_Tata(InitialaTF.getText());
        }
    }
    
    public boolean isRowSelected(){
        return Table.getSelectedRow() != -1;
    }
    
    public boolean isValidNote(String s){
        if(!"".equals(s) && !"NOT A VALID NOTE".equals(s)){
            return !(Float.valueOf(s) < 1 || Float.valueOf(s) > 10);
        }
        return false;
    }
    
    public boolean isValidNote(float f){
        return !(f < 1 || f> 10);
    }
    
    public void saveCandidate(){
        try {
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Update Candidati set nume='"+x.getNume()+"',prenume='"+x.getPrenume()+"',initiala_tata='"+x.getInitiala_Tata()+"',an_absolvire="+x.getAn_Absolvire()+",nationalitate='"+x.getNationalitate()+"',data_nastere='"
                            +x.getDataNasterii()+"' where ID="+x.getID();
            st.executeUpdate(query);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModifyCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveNote(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Update Rezultate set nota = "+x.getProba1().getNota()+",nota_finala="+x.getProba1().getNota()+",Notat_De="+Singleton.getInstance().UserLogged.getID()
                            +" where id="+x.getProba1().getID();
            st.executeUpdate(query);
            query = "Update Rezultate set nota = "+x.getProba2().getNota()+",nota_finala="+x.getProba2().getNota()+",Notat_De="+Singleton.getInstance().UserLogged.getID()
                            +" where id="+x.getProba2().getID();
            st.executeUpdate(query);
            query = "Update Rezultate set nota = "+x.getProba3().getNota()+",nota_finala="+x.getProba3().getNota()+",Notat_De="+Singleton.getInstance().UserLogged.getID()
                            +" where id="+x.getProba3().getID();
            st.executeUpdate(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModifyCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveCalificative(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Update Calificative set calificativ = '"+x.getCLbRomana().getCalificativ()
                            +"' where id="+x.getCLbRomana().getID();
            st.executeUpdate(query);
            query = "Update Calificative set calificativ = '"+x.getCLbModerna().getCalificativ()
                            +"' where id="+x.getCLbModerna().getID();
            st.executeUpdate(query);
            query = "Update Calificative set calificativ = '"+x.getCInformatica().getCalificativ()
                            +"' where id="+x.getCInformatica().getID();
            st.executeUpdate(query);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ModifyCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelAction(){
        Table.setEnabled(true);
        EditButton.setEnabled(true);
        CancelButton.setEnabled(false);
        SearchTextField.setEnabled(true);
        StergeButton.setEnabled(true);
        NumeRadioButton.setEnabled(true);
        LiceuRadioButton.setEnabled(true);
        InformatiiCandidatPanel.setVisible(false);
        ProbeScrisePanel.setVisible(false);
        CompetentePanel.setVisible(false);
        SalveazaButton.setEnabled(false);
        blocked = false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchButtonGroup = new javax.swing.ButtonGroup();
        ScrollPane = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        JudetCB = new javax.swing.JComboBox();
        CandidatPanel = new javax.swing.JPanel();
        LiceuRadioButton = new javax.swing.JRadioButton();
        NumeRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        InformatiiCandidatPanel = new javax.swing.JPanel();
        NationalitateTF = new javax.swing.JTextField();
        PrenumeLBL = new javax.swing.JLabel();
        AbsolvireLBL = new javax.swing.JLabel();
        NationalitateLBL = new javax.swing.JLabel();
        NumeLBL = new javax.swing.JLabel();
        NumeTF = new javax.swing.JTextField();
        DataNasteriiLBL = new javax.swing.JLabel();
        PrenumeTF = new javax.swing.JTextField();
        AbsolvireTF = new javax.swing.JTextField();
        InitalaLBL = new javax.swing.JLabel();
        SpecializareLBL = new javax.swing.JLabel();
        LiceuLBL = new javax.swing.JLabel();
        InitialaTF = new javax.swing.JTextField();
        LiceuTF = new javax.swing.JTextField();
        SpecializareTF = new javax.swing.JTextField();
        DateChooser = new com.toedter.calendar.JDateChooser();
        ProbeScrisePanel = new javax.swing.JPanel();
        NProbaATF = new javax.swing.JTextField();
        NProbaCLBL = new javax.swing.JLabel();
        ProbaCTF = new javax.swing.JTextField();
        NProbaDLBL = new javax.swing.JLabel();
        NProbaALBL = new javax.swing.JLabel();
        NProbaDTF = new javax.swing.JTextField();
        ProbaATF = new javax.swing.JTextField();
        ProbaDLBL = new javax.swing.JLabel();
        ProbaALBL = new javax.swing.JLabel();
        NProbaCTF = new javax.swing.JTextField();
        ProbaCLBL = new javax.swing.JLabel();
        ProbaDTF = new javax.swing.JTextField();
        SearchTextField = new javax.swing.JTextField();
        SalveazaButton = new javax.swing.JButton();
        CompetentePanel = new javax.swing.JPanel();
        CalificativBTF = new javax.swing.JTextField();
        CompetentaBLBL = new javax.swing.JLabel();
        CompetentaALBL = new javax.swing.JLabel();
        CalificativALBL = new javax.swing.JLabel();
        CompetentaATF = new javax.swing.JTextField();
        CalificativACB = new javax.swing.JComboBox();
        CompetentaCLBL = new javax.swing.JLabel();
        CalificativCLBL = new javax.swing.JLabel();
        CalificativCCB = new javax.swing.JComboBox();
        CalificativBLBL = new javax.swing.JLabel();
        CompetentaCTF = new javax.swing.JTextField();
        CompetentaBTF = new javax.swing.JTextField();
        StergeButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "        Nume", "        Liceu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.getTableHeader().setReorderingAllowed(false);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        ScrollPane.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setResizable(false);
            Table.getColumnModel().getColumn(1).setResizable(false);
        }

        JudetCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JudetCBActionPerformed(evt);
            }
        });

        SearchButtonGroup.add(LiceuRadioButton);
        LiceuRadioButton.setText("dupa liceu");

        SearchButtonGroup.add(NumeRadioButton);
        NumeRadioButton.setText("dupa nume");

        jLabel1.setText("Cauta");

        InformatiiCandidatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informatii Candidat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        NationalitateTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NationalitateTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NationalitateTFFocusLost(evt);
            }
        });

        PrenumeLBL.setText("Prenume");

        AbsolvireLBL.setText("An absolvire");

        NationalitateLBL.setText("Nationalitate");

        NumeLBL.setText("Nume");

        NumeTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        DataNasteriiLBL.setText("Data naterii");

        PrenumeTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PrenumeTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PrenumeTFFocusLost(evt);
            }
        });

        AbsolvireTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AbsolvireTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                AbsolvireTFFocusLost(evt);
            }
        });
        AbsolvireTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AbsolvireTFKeyTyped(evt);
            }
        });

        InitalaLBL.setText("Initiala tata");

        SpecializareLBL.setText("Specializare");

        LiceuLBL.setText("Liceu");

        InitialaTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        LiceuTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        SpecializareTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        DateChooser.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        dateEditor.setHorizontalAlignment(JTextField.CENTER);

        javax.swing.GroupLayout InformatiiCandidatPanelLayout = new javax.swing.GroupLayout(InformatiiCandidatPanel);
        InformatiiCandidatPanel.setLayout(InformatiiCandidatPanelLayout);
        InformatiiCandidatPanelLayout.setHorizontalGroup(
            InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InformatiiCandidatPanelLayout.createSequentialGroup()
                                .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                                        .addComponent(PrenumeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(DataNasteriiLBL))
                                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(LiceuLBL)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InformatiiCandidatPanelLayout.createSequentialGroup()
                                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(SpecializareTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(LiceuTF, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(30, 30, 30))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InformatiiCandidatPanelLayout.createSequentialGroup()
                                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))
                            .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                                .addComponent(NumeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PrenumeLBL)
                            .addComponent(NumeLBL)
                            .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                                    .addComponent(NationalitateLBL)
                                    .addGap(12, 12, 12)
                                    .addComponent(NationalitateTF))
                                .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                                    .addComponent(InitalaLBL)
                                    .addGap(18, 18, 18)
                                    .addComponent(InitialaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AbsolvireLBL)
                            .addComponent(SpecializareLBL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AbsolvireTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        InformatiiCandidatPanelLayout.setVerticalGroup(
            InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeLBL)
                    .addComponent(AbsolvireLBL)
                    .addComponent(AbsolvireTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PrenumeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PrenumeLBL)
                            .addComponent(DataNasteriiLBL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InitalaLBL)
                            .addComponent(LiceuLBL)
                            .addComponent(InitialaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformatiiCandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NationalitateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NationalitateLBL)
                            .addComponent(SpecializareLBL)))
                    .addGroup(InformatiiCandidatPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(LiceuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpecializareTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        ProbeScrisePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evaluare probe scrise", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        NProbaATF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NProbaATF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NProbaATFFocusLost(evt);
            }
        });
        NProbaATF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NProbaATFKeyTyped(evt);
            }
        });

        NProbaCLBL.setText("Nota proba C");

        ProbaCTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        NProbaDLBL.setText("Nota proba D");

        NProbaALBL.setText("Nota proba A");

        NProbaDTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NProbaDTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NProbaDTFFocusLost(evt);
            }
        });
        NProbaDTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NProbaDTFKeyTyped(evt);
            }
        });

        ProbaATF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ProbaDLBL.setText("Proba D");

        ProbaALBL.setText("Proba A");

        NProbaCTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NProbaCTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NProbaCTFFocusLost(evt);
            }
        });
        NProbaCTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NProbaCTFKeyTyped(evt);
            }
        });

        ProbaCLBL.setText("Proba C");

        ProbaDTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout ProbeScrisePanelLayout = new javax.swing.GroupLayout(ProbeScrisePanel);
        ProbeScrisePanel.setLayout(ProbeScrisePanelLayout);
        ProbeScrisePanelLayout.setHorizontalGroup(
            ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProbeScrisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProbeScrisePanelLayout.createSequentialGroup()
                        .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NProbaALBL)
                            .addComponent(ProbaALBL)
                            .addComponent(ProbaCLBL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProbaATF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(NProbaATF)
                            .addComponent(ProbaCTF)))
                    .addGroup(ProbeScrisePanelLayout.createSequentialGroup()
                        .addComponent(NProbaCLBL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NProbaCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProbeScrisePanelLayout.createSequentialGroup()
                        .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NProbaDLBL)
                            .addComponent(ProbaDLBL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NProbaDTF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(ProbaDTF))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProbeScrisePanelLayout.setVerticalGroup(
            ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProbeScrisePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProbaALBL)
                    .addComponent(ProbaATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NProbaALBL)
                    .addComponent(NProbaATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProbaCLBL)
                    .addComponent(ProbaCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NProbaCLBL)
                    .addComponent(NProbaCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProbaDLBL)
                    .addComponent(ProbaDTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProbeScrisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NProbaDLBL)
                    .addComponent(NProbaDTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        SearchTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        SalveazaButton.setText("Salveaza");
        SalveazaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalveazaButtonActionPerformed(evt);
            }
        });

        CompetentePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evaluare competente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        CalificativBTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CalificativBTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CalificativBTFFocusLost(evt);
            }
        });

        CompetentaBLBL.setText("Competenta B");

        CompetentaALBL.setText("Competenta A");

        CalificativALBL.setText("Calificativ A");

        CompetentaATF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CalificativACB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalificativACBActionPerformed(evt);
            }
        });

        CompetentaCLBL.setText("Competenta C");

        CalificativCLBL.setText("Calificativ C");

        CalificativCCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalificativCCBActionPerformed(evt);
            }
        });

        CalificativBLBL.setText("Calificativ B");

        CompetentaCTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CompetentaBTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout CompetentePanelLayout = new javax.swing.GroupLayout(CompetentePanel);
        CompetentePanel.setLayout(CompetentePanelLayout);
        CompetentePanelLayout.setHorizontalGroup(
            CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CompetentePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CompetentePanelLayout.createSequentialGroup()
                        .addComponent(CalificativALBL, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CalificativACB, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CompetentePanelLayout.createSequentialGroup()
                        .addComponent(CompetentaALBL)
                        .addGap(18, 18, 18)
                        .addComponent(CompetentaATF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CompetentePanelLayout.createSequentialGroup()
                        .addComponent(CompetentaBLBL)
                        .addGap(18, 18, 18)
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CalificativBTF)
                            .addComponent(CompetentaBTF)))
                    .addComponent(CalificativBLBL)
                    .addGroup(CompetentePanelLayout.createSequentialGroup()
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CompetentaCLBL)
                            .addComponent(CalificativCLBL))
                        .addGap(18, 18, 18)
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CompetentePanelLayout.createSequentialGroup()
                                .addComponent(CalificativCCB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(CompetentaCTF))))
                .addContainerGap())
        );
        CompetentePanelLayout.setVerticalGroup(
            CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CompetentePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CompetentePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CalificativALBL)
                            .addComponent(CalificativACB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CompetentaBLBL)
                            .addComponent(CompetentaBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CalificativBLBL)
                            .addComponent(CalificativBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CompetentaALBL)
                        .addComponent(CompetentaATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CompetentaCLBL)
                    .addComponent(CompetentaCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CompetentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CalificativCLBL)
                    .addComponent(CalificativCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        StergeButton.setText("Sterge");
        StergeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StergeButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Anuleaza");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CandidatPanelLayout = new javax.swing.GroupLayout(CandidatPanel);
        CandidatPanel.setLayout(CandidatPanelLayout);
        CandidatPanelLayout.setHorizontalGroup(
            CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CandidatPanelLayout.createSequentialGroup()
                .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CandidatPanelLayout.createSequentialGroup()
                        .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CandidatPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(SalveazaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ProbeScrisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CandidatPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CompetentePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CandidatPanelLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(CandidatPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InformatiiCandidatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CandidatPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NumeRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(LiceuRadioButton)
                                .addGap(48, 48, 48)
                                .addComponent(StergeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        CandidatPanelLayout.setVerticalGroup(
            CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CandidatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeRadioButton)
                    .addComponent(LiceuRadioButton)
                    .addComponent(StergeButton))
                .addGap(22, 22, 22)
                .addComponent(InformatiiCandidatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CandidatPanelLayout.createSequentialGroup()
                        .addComponent(ProbeScrisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addComponent(CompetentePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CandidatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SalveazaButton)
                    .addComponent(CancelButton))
                .addGap(151, 151, 151))
        );

        EditButton.setText("Editeaza");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(CandidatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CandidatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        if(!blocked){
            if(Table.getSelectedRow() != -1){
                getCanidatFromTable();
                if(x != null && x.getID() != 0){
                    putInfoInView();

                }
            }
        }
    }//GEN-LAST:event_TableMouseClicked

    private void JudetCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JudetCBActionPerformed
       if(JudetCB.getSelectedItem() != null){
        clearTable();
        populateTable(Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()));
        resetFields();
       }
    }//GEN-LAST:event_JudetCBActionPerformed

    private void StergeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StergeButtonActionPerformed
        int answer = JOptionPane.showConfirmDialog(null, "Sunteti sigur de stergere?","Sterge",JOptionPane.YES_NO_OPTION);
        if(answer == 0){
            deleteCandidate(x);
            Singleton.getInstance().IncarcaRezultate();
            populateTable(getListFiltred());
            JOptionPane.showMessageDialog(this, "Sters");
        }
    }//GEN-LAST:event_StergeButtonActionPerformed

    private void SalveazaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalveazaButtonActionPerformed
        if(ableToSave()){
            saveCandidate();
            saveNote();
            saveCalificative();
            Singleton.getInstance().IncarcaRezultate();
            populateTable(getListFiltred());
            x = new Candidat();
            JOptionPane.showMessageDialog(this, "Salvat!");
            cancelAction();
            
        }else{
            JOptionPane.showMessageDialog(this, "Completati toate campurile corect!");
        }
    }//GEN-LAST:event_SalveazaButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
       CancelButton.setEnabled(true);
       Table.setEnabled(false);
       EditButton.setEnabled(false);
       StergeButton.setEnabled(false);
       NumeRadioButton.setEnabled(false);
       LiceuRadioButton.setEnabled(false);
       SearchTextField.setEnabled(false);
       InformatiiCandidatPanel.setVisible(true);
       ProbeScrisePanel.setVisible(true);
       CompetentePanel.setVisible(true);
       SalveazaButton.setEnabled(true);
       blocked = true;
    }//GEN-LAST:event_EditButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        cancelAction();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void PrenumeTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PrenumeTFFocusLost
        if(PrenumeTF.getText() != ""){
            x.setPrenume(PrenumeTF.getText());
        }
    }//GEN-LAST:event_PrenumeTFFocusLost

    private void NationalitateTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NationalitateTFFocusLost
        x.setNationalitate(NationalitateTF.getText());
        
    }//GEN-LAST:event_NationalitateTFFocusLost

    private void AbsolvireTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AbsolvireTFFocusLost
        if(!"".equals(AbsolvireTF.getText()) && !"NOT A VALID YEAR".equals(AbsolvireTF.getText())){
            int aux = Integer.valueOf(AbsolvireTF.getText());
            if(aux < 1950 || aux > Calendar.getInstance().get(Calendar.YEAR)){
                AbsolvireTF.setForeground(Color.RED);
                AbsolvireTF.setText("NOT A VALID YEAR");
                x.setAn_Absolvire(0);
            }else{
                AbsolvireTF.setForeground(Color.black);
                x.setAn_Absolvire(aux);
            }
        }
    }//GEN-LAST:event_AbsolvireTFFocusLost

    private void AbsolvireTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbsolvireTFKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_ENTER )){
            getToolkit().beep();
            evt.consume();
        }           
    }//GEN-LAST:event_AbsolvireTFKeyTyped

    private void NProbaATFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NProbaATFKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || c =='.' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_ENTER )){
            getToolkit().beep();
            evt.consume();
        }           
    }//GEN-LAST:event_NProbaATFKeyTyped

    private void NProbaATFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NProbaATFFocusLost
        if(!"".equals(NProbaATF.getText())){
            if(!isValidNote(NProbaATF.getText())){
                NProbaATF.setForeground(Color.red);
                NProbaATF.setText("NOT A VALID NOTE");
                x.getProba1().setNota(0);
            }else{
                NProbaATF.setForeground(Color.black);
                x.getProba1().setNota(Float.valueOf(NProbaATF.getText()));
            }
        }else{
            NProbaATF.setForeground(Color.red);
            NProbaATF.setText("NOT A VALID NOTE");
            x.getProba1().setNota(0);
        }
    }//GEN-LAST:event_NProbaATFFocusLost

    private void NProbaCTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NProbaCTFKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || c =='.' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_ENTER )){
            getToolkit().beep();
            evt.consume();
        }         
    }//GEN-LAST:event_NProbaCTFKeyTyped

    private void NProbaCTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NProbaCTFFocusLost
         if(!"".equals(NProbaCTF.getText())){
            if(!isValidNote(NProbaCTF.getText())){
                NProbaCTF.setForeground(Color.red);
                NProbaCTF.setText("NOT A VALID NOTE");
                x.getProba2().setNota(0);
            }else{
                NProbaCTF.setForeground(Color.black);
                x.getProba2().setNota(Float.valueOf(NProbaCTF.getText()));
            }
         }else{
            NProbaCTF.setForeground(Color.red);
            NProbaCTF.setText("NOT A VALID NOTE");
            x.getProba2().setNota(0);
        }
    }//GEN-LAST:event_NProbaCTFFocusLost

    private void NProbaDTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NProbaDTFKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || c =='.' || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_ENTER )){
            getToolkit().beep();
            evt.consume();
        }         
    }//GEN-LAST:event_NProbaDTFKeyTyped

    private void NProbaDTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NProbaDTFFocusLost
        if(!"".equals(NProbaDTF.getText())){ 
            if(!isValidNote(NProbaDTF.getText())){
                NProbaDTF.setForeground(Color.red);
                NProbaDTF.setText("NOT A VALID NOTE");
                x.getProba3().setNota(0);
            }else{
                NProbaDTF.setForeground(Color.black);
                x.getProba3().setNota(Float.valueOf(NProbaDTF.getText()));
            }
        }else{
            NProbaDTF.setForeground(Color.red);
            NProbaDTF.setText("NOT A VALID NOTE");
            x.getProba3().setNota(0);
        }
    }//GEN-LAST:event_NProbaDTFFocusLost

    private void CalificativACBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalificativACBActionPerformed
        if(CalificativACB.getSelectedItem() != null){
            x.getCLbRomana().setCalificativ(CalificativACB.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CalificativACBActionPerformed

    private void CalificativCCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalificativCCBActionPerformed
        if(CalificativCCB.getSelectedItem() != null){
            x.getCInformatica().setCalificativ(CalificativCCB.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CalificativCCBActionPerformed

    private void CalificativBTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CalificativBTFFocusLost
        if(!"".equals(CalificativBTF.getText())){
            x.getCLbModerna().setCalificativ(CalificativBTF.getText());
        }else{
            x.getCLbModerna().setCalificativ("");
        }
    }//GEN-LAST:event_CalificativBTFFocusLost

    private boolean ableToSave(){
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)DateChooser.getComponent(1);
        return !"".equals(x.getNume()) && !"".equals(x.getPrenume()) && x.getAn_Absolvire() != 0 && !"".equals(x.getInitiala_Tata()) && !"".equals(x.getNationalitate())
                && x.getDataNasterii() != "" && isValidNote(x.getProba1().getNota()) && isValidNote(x.getProba2().getNota())
                && isValidNote(x.getProba3().getNota()) && !"".equals(x.getCLbModerna().getCalificativ()) && !"".equals(x.getCLbRomana().getCalificativ()) 
                && !"".equals(x.getCInformatica().getCalificativ()) && x.getDataNasterii() != "" && !dateEditor.getText().equals("") && !NumeTF.getText().equals("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AbsolvireLBL;
    private javax.swing.JTextField AbsolvireTF;
    private javax.swing.JComboBox CalificativACB;
    private javax.swing.JLabel CalificativALBL;
    private javax.swing.JLabel CalificativBLBL;
    private javax.swing.JTextField CalificativBTF;
    private javax.swing.JComboBox CalificativCCB;
    private javax.swing.JLabel CalificativCLBL;
    private javax.swing.JButton CancelButton;
    private javax.swing.JPanel CandidatPanel;
    private javax.swing.JLabel CompetentaALBL;
    private javax.swing.JTextField CompetentaATF;
    private javax.swing.JLabel CompetentaBLBL;
    private javax.swing.JTextField CompetentaBTF;
    private javax.swing.JLabel CompetentaCLBL;
    private javax.swing.JTextField CompetentaCTF;
    private javax.swing.JPanel CompetentePanel;
    private javax.swing.JLabel DataNasteriiLBL;
    private com.toedter.calendar.JDateChooser DateChooser;
    private javax.swing.JButton EditButton;
    private javax.swing.JPanel InformatiiCandidatPanel;
    private javax.swing.JLabel InitalaLBL;
    private javax.swing.JTextField InitialaTF;
    private javax.swing.JComboBox JudetCB;
    private javax.swing.JLabel LiceuLBL;
    private javax.swing.JRadioButton LiceuRadioButton;
    private javax.swing.JTextField LiceuTF;
    private javax.swing.JLabel NProbaALBL;
    private javax.swing.JTextField NProbaATF;
    private javax.swing.JLabel NProbaCLBL;
    private javax.swing.JTextField NProbaCTF;
    private javax.swing.JLabel NProbaDLBL;
    private javax.swing.JTextField NProbaDTF;
    private javax.swing.JLabel NationalitateLBL;
    private javax.swing.JTextField NationalitateTF;
    private javax.swing.JLabel NumeLBL;
    private javax.swing.JRadioButton NumeRadioButton;
    private javax.swing.JTextField NumeTF;
    private javax.swing.JLabel PrenumeLBL;
    private javax.swing.JTextField PrenumeTF;
    private javax.swing.JLabel ProbaALBL;
    private javax.swing.JTextField ProbaATF;
    private javax.swing.JLabel ProbaCLBL;
    private javax.swing.JTextField ProbaCTF;
    private javax.swing.JLabel ProbaDLBL;
    private javax.swing.JTextField ProbaDTF;
    private javax.swing.JPanel ProbeScrisePanel;
    private javax.swing.JButton SalveazaButton;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.ButtonGroup SearchButtonGroup;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JLabel SpecializareLBL;
    private javax.swing.JTextField SpecializareTF;
    private javax.swing.JButton StergeButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
