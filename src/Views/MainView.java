package Views;

import CustomTable.IModelFieldGroup;
import CustomTable.JBroTable;
import CustomTable.ModelData;
import CustomTable.ModelField;
import CustomTable.ModelFieldGroup;
import CustomTable.ModelRow;
import CustomTable.MultipleLine;
import CustomTable.Utils;
import Entity.Candidat;
import Singleton.Singleton;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;



public class MainView extends javax.swing.JPanel {

    javax.swing.JLabel JudetLabel;
    javax.swing.JComboBox JudetCB;
    javax.swing.JTextField  SearchField;
    javax.swing.JLabel SearchLabel;
    
    private MainFrame frame;
    private String SearchString;
    TableColumnModel cm;
    JBroTable table;
    IModelFieldGroup groups[];
    ArrayList<ModelRow> Randuri ;
    ModelField fields[];
    ModelData data;
    
    
    private javax.swing.JScrollPane ScrollPane;
    public MainView() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        initComponents();
        setLayout();
        init();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void getFrame(MainFrame f){
        frame = f;
        
    }
    
    private void setLayout(){

        JudetLabel = new javax.swing.JLabel();
        JudetCB = new javax.swing.JComboBox();
        SearchField = new javax.swing.JTextField();
        SearchLabel = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JudetLabel.setText(" Judet");



        SearchLabel.setText("Cauta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JudetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(SearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JudetLabel)
                    .addComponent(JudetCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        JudetCB.addActionListener((java.awt.event.ActionEvent evt) -> {
            JudetCBActionPerformed(evt);
        });
        SearchField.setHorizontalAlignment(0);
    }
    
    private void adjustRows(JTable table){
        
//        for (int row = 0; row < table.getRowCount(); row++)
//    {
//        int rowHeight = table.getRowHeight();
//
//        for (int column = 0; column < table.getColumnCount(); column++)
//        {
//            Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
//            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
//        }
//
//        table.setRowHeight(row, rowHeight);
//    }
        table.setRowHeight(65);
    
    }
    
    public final void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        buildTable();
        setHeadersFit();
        JudetCB.setModel(new DefaultComboBoxModel(Singleton.getInstance().Counties.toArray()));
        JudetCB.setSelectedItem("ALBA");
        //populateTable(Singleton.getInstance().Candidati);
        //populateTable(getCandidatiJudet());
        SearchTextFieldChangedListner(SearchField);
    }
    
    private void JudetCBActionPerformed(ActionEvent evt) {
        populateTable(Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()));
        table.revalidate();
    }
    
    public void buildTable() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        Utils.initSimpleConsoleLogger();
        //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        groups = new IModelFieldGroup[] {
        new ModelField( "Nume", "<HTML><center>Nume,iniţiala tatălui<br>prenume</center>" ),
        new ModelField( "Liceu", "Unitatea de învăţământ" ),
        new ModelField( "Promotie", "<HTML><CENTER>Promoţie<BR> anterioară</CENTER>" ),
        new ModelField( "Specializare", "Specializare" ),
        new ModelFieldGroup( "Proba1", "                Limba şi literatura română" )
                .withChild(new ModelField("Compententa1"," Competenţe "))
                .withChild(new ModelField("Scris1","  Scris  "))
                .withChild(new ModelField("Contestatie1","Contestaţie"))
                .withChild(new ModelField("Final1","Notă finală")),
        new ModelField( "LbModerna", "<HTML>Limba modernă<br>studiată" ),
        new ModelField( "NotaLbM", "<HTML>Notă" ),
        new ModelFieldGroup( "Proba2", "          Disciplina obligatorie a profilului" )
                .withChild(new ModelField("Nume2"," Denumire "))
                .withChild(new ModelField("Nota2","   Notă  "))
                .withChild(new ModelField("Contestatie2","Contestaţie"))
                .withChild(new ModelField("Final2","Notă finală")),
        new ModelFieldGroup( "Proba3", "                   Disciplina la alegere" )
                .withChild(new ModelField("Nume3","Denumire"))
                .withChild(new ModelField("Nota3","   Notă  "))
                .withChild(new ModelField("Contestatie3","Contestaţie"))
                .withChild(new ModelField("Final3","Notă finală")),
        new ModelField( "CompenteD", "<HTML>Competenţe<br>digitale" ),
        new ModelField( "Media", "<HTML>Media" ),
        new ModelField( "Rezultat", "<HTML> Rezultatul<br>final" ),
        };
        data = new ModelData( groups );
        fields = ModelFieldGroup.getBottomFields( groups );
        table = new JBroTable(data);
        table.setAutoCreateRowSorter( true );
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        table.setDefaultRenderer(Object.class, new MultipleLine());
        //setHeadersFit();
        ScrollPane.setViewportView(table);
    }
    
    public void setHeadersFit(){
        table.getTableHeader().setReorderingAllowed(false);
        TableCellRenderer rend = table.getTableHeader().getDefaultRenderer();
        TableColumnModel tcm = table.getColumnModel();
        for (int j=0; j < tcm.getColumnCount(); j+=1) {
            TableColumn tc = tcm.getColumn(j);
            tc.setHeaderRenderer(rend);
            tc.sizeWidthToFit();
        }
    }
    
    public void populateTable(ArrayList<Candidat> List){
        if(List!= null){
            Randuri = new ArrayList<>();
            
            for(Candidat aux : List){
                Randuri.add(new ModelRow(fields.length));

                int i = 0;
                while(i<fields.length){
                    switch(i){
                        case 0 : Randuri.get(Randuri.size()-1).setValue(i, aux.getNume()+" "+aux.getInitiala_Tata()+"\n"
                                + aux.getPrenume());
                                 break;
                        case 1 : Randuri.get(Randuri.size()-1).setValue(i, aux.getLiceu().getNume());
                                 break;
                        case 2 : Randuri.get(Randuri.size()-1).setValue(i, aux.getPromotieAnterioara());
                                 break;
                        case 3 : Randuri.get(Randuri.size()-1).setValue(i, aux.getSpecializare());
                                 break;
                        case 4 : Randuri.get(Randuri.size()-1).setValue(i, aux.getCLbRomana().getCalificativ());
                                 break;
                        case 5 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba1().getNota()>0 ? aux.getProba1().getNota() : "Absent");
                                 break;
                        case 6 : Randuri.get(Randuri.size()-1).setValue(i, "");
                                 break;
                        case 7 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba1().getNota()>0 ? aux.getProba1().getNota() : "Absent");
                                 break;
                        case 8 : Randuri.get(Randuri.size()-1).setValue(i, aux.getCLbModerna().getDenumire());
                                 break;
                        case 9 : Randuri.get(Randuri.size()-1).setValue(i, aux.getCLbModerna().getCalificativ());
                                 break;
                        case 10 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba2().getDenumire());
                                 break;
                        case 11 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba2().getNota()>0 ? aux.getProba2().getNota() : "Absent");
                                 break;
                        case 12 : Randuri.get(Randuri.size()-1).setValue(i, "");
                                 break;
                        case 13 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba2().getNota()>0 ? aux.getProba2().getNota() : "Absent");
                                 break;
                        case 14 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba3().getDenumire());
                                 break;
                        case 15 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba3().getNota()>0 ? aux.getProba3().getNota() : "Absent");
                                 break;
                        case 16 : Randuri.get(Randuri.size()-1).setValue(i, "");
                                 break;
                        case 17 : Randuri.get(Randuri.size()-1).setValue(i, aux.getProba3().getNota()>0 ? aux.getProba3().getNota() : "Absent");
                                 break;
                        case 18 : Randuri.get(Randuri.size()-1).setValue(i, aux.getCInformatica().getCalificativ());
                                 break;
                        case 19 : Randuri.get(Randuri.size()-1).setValue(i, aux.getMedie()!= 0.0f ? String.format("%.2f", aux.getMedie())  : "");
                                 break;
                        case 20 : Randuri.get(Randuri.size()-1).setValue(i, aux.getMedie()>= 6 ? "REUSIT" : "RESPINS");
                                    if(aux.getProba1().getNota() == 0.0f || aux.getProba2().getNota() == 0.0f || aux.getProba3().getNota()== 0.0f){

                                        Randuri.get(Randuri.size()-1).setValue(i,"NEPREZENTAT");
                                    }
                                 break;
                    }
                    i++;
                }
            }
            ModelRow[] rands = Randuri.toArray(new ModelRow[Randuri.size()]);
            data.setRows(rands);
            table.revalidate();
            adjustRows(table);
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
      
    
    
    public void clearJTable(){
        Randuri.clear();
    }
    
     public ArrayList<Candidat> getListFiltred(){
        ArrayList<Candidat> aux = new ArrayList<>();
        Candidat.getCandidatiJudet(JudetCB.getSelectedItem().toString()).stream().filter((x) -> (x.getNume().toUpperCase().contains(SearchString) || x.getLiceu().getNume().toUpperCase().contains(SearchString))).forEach((x) -> {
            aux.add(x);
        });
        return aux;
    }
     
     public boolean isStringEmpty(String x){
        return "".equals(x);
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
