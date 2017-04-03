/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Singleton.Singleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ynad
 */
public class Candidat {
    
    private int ID;
    private String Nume;
    private String Prenume;
    private String Initiala_Tata;
    private int An_Absolvire;
    private String Nationalitate;
    private String DataNasterii;
    private int LiceuID;
    private String Specializare;
    private int SpecializareID;
    private String PromotieAnterioara;
    private float Medie;
    private Liceu Liceu;
    private Proba Proba1;
    private Proba Proba2;
    private Proba Proba3;
    private Competenta CLbRomana;
    private Competenta CLbModerna;
    private Competenta CInformatica;
    //private String Judet;

    
     public Liceu getLiceu() {
        return Liceu;
    }

    public void setLiceu(Liceu Liceu) {
        this.Liceu = Liceu;
    }

  
   
    public Candidat(){
        Proba1  = new Proba();
        Proba2  = new Proba();
        Proba3  = new Proba();
        CLbRomana = new Competenta();
        CLbModerna = new Competenta();
        CInformatica = new Competenta();
        Liceu = new Liceu();
        Medie = 0.0f;
    }
    
    public float getMedie() {
        return Medie;
    }

    public void setMedie(float Medie) {
        this.Medie = Medie;
    }
    
    public String getPromotieAnterioara() {
        return PromotieAnterioara;
    }


    public void setSpecializareID(int SpecializareID) {
        this.SpecializareID = SpecializareID;
    }

    public void setPromotieAnterioara(String PromotieAnterioara) {
        this.PromotieAnterioara = PromotieAnterioara;
    }

    public int getSpecializareID() {
        return SpecializareID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNume(String Nume) {
        this.Nume = Nume;
    }

    public void setPrenume(String Prenume) {
        this.Prenume = Prenume;
    }

    public void setInitiala_Tata(String Initiala_Tata) {
        this.Initiala_Tata = Initiala_Tata;
    }

    public void setAn_Absolvire(int An_Absolvire) {
        this.An_Absolvire = An_Absolvire;
    }

    public void setNationalitate(String Nationalitate) {
        this.Nationalitate = Nationalitate;
    }

    public void setDataNasterii(String DataNasterii) {
        this.DataNasterii = DataNasterii;
    }

    public void setLiceuID(int LiceuID) {
        this.LiceuID = LiceuID;
    }

    public void setSpecializare(String Specializare) {
        this.Specializare = Specializare;
    }
  
    public int getID() {
        return ID;
    }

    public String getNume() {
        return Nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public String getInitiala_Tata() {
        return Initiala_Tata;
    }

    public int getAn_Absolvire() {
        return An_Absolvire;
    }

    public String getNationalitate() {
        return Nationalitate;
    }

    public String getDataNasterii() {
        return DataNasterii;
    }

    public int getLiceuID() {
        return LiceuID;
    }
    
    public String getSpecializare() {
        return Specializare;
    }

    public Proba getProba1() {
        return Proba1;
    }

    public void setProba1(Proba Proba1) {
        this.Proba1 = Proba1;
    }

    public void setProba2(Proba Proba2) {
        this.Proba2 = Proba2;
    }

    public Competenta getCLbRomana() {
        return CLbRomana;
    }

    public Competenta getCLbModerna() {
        return CLbModerna;
    }

    public Competenta getCInformatica() {
        return CInformatica;
    }
    

    public void setProba3(Proba Proba3) {
        this.Proba3 = Proba3;
    }

    public void setCLbRomana(Competenta CLbRomana) {
        this.CLbRomana = CLbRomana;
    }

    public void setCLbModerna(Competenta CLbModerna) {
        this.CLbModerna = CLbModerna;
    }

    public void setCInformatica(Competenta CInformatica) {
        this.CInformatica = CInformatica;
    }

    public Proba getProba2() {
        return Proba2;
    }

    public Proba getProba3() {
        return Proba3;
    }
    
    public static ArrayList<Candidat> getCandidatiJudet(String judet){
        ArrayList<Candidat> aux = new ArrayList<>();
        Singleton.getInstance().Candidati.stream().filter((x) -> (x.getLiceu().getJudet().equals(judet))).forEach((x) -> {
            aux.add(x);
        });
        return aux;
    }
    
    public static Candidat getCandidatFromList(String Nume,String Initiala,String Prenume,String liceu,String judet){
        for(Candidat aux:getCandidatiJudet(judet)){
            if(aux.Nume.equals(Nume) && aux.Prenume.equals(Prenume) && aux.Initiala_Tata.equals(Initiala) && aux.getLiceu().getNume().equals(liceu)){
                return aux;
            }
        }
        return null;
    }
    
    public void getCandidatInfo(Connection con){
        try{
            Statement st = con.createStatement();
            String query = "Select * from Candidati where ID ="+ID;
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                Nume = rs.getString("Nume");
                Prenume = rs.getString("Prenume");
                Initiala_Tata = rs.getString("Initiala_tata");
                An_Absolvire = rs.getInt("An_absolvire");
                if(An_Absolvire == Calendar.getInstance().get(Calendar.YEAR) -1){
                    PromotieAnterioara = "NU";
                }else{
                    PromotieAnterioara = "DA";
                }
                Nationalitate = rs.getString("Nationalitate");
                DataNasterii = rs.getString("Data_Nastere");
                LiceuID = rs.getInt("Liceu_ID");
            }
            
            query = "Select * from Rezultate,Materii where Rezultate.Materie_ID = Materii.ID AND "
                    + "Candidat_ID = "+ID;
            rs = st.executeQuery(query);
            while(rs.next()){
                switch(rs.getString("Proba")){
                    case "A" : Proba1.setID(rs.getInt(1));
                               Proba1.setNota(rs.getFloat("Nota"));
                               Proba1.setDenumire(rs.getString("Denumire"));
                               break;
                    case "C" : Proba2.setID(rs.getInt(1));
                               Proba2.setNota(rs.getFloat("Nota"));
                               Proba2.setDenumire(rs.getString("Denumire"));
                               break;
                    case "D" : Proba3.setID(rs.getInt(1));
                               Proba3.setNota(rs.getFloat("Nota"));
                               Proba3.setDenumire(rs.getString("Denumire"));
                               break;
                }
            }
            if(Proba1.getNota() >= 5 && Proba2.getNota() >= 5 && Proba3.getNota() >= 5){
                Medie = (Proba1.getNota()+Proba2.getNota()+Proba3.getNota())/3;
            }
            
            query = "Select * from Specializari where ID = (SELECT Specializare_ID FROM Specializari"
                    + "_Licee where ID="+LiceuID+")";
            rs = st.executeQuery(query);
            if(rs.next()){
                Specializare = rs.getString("Denumire");
                SpecializareID = rs.getInt("ID");
            }
            
            query = "Select * from Licee where ID=(SELECT Liceu_ID from Specializari_Licee "
                    + "where id="+LiceuID+");";
            
            rs = st.executeQuery(query);
            if(rs.next()){
                Liceu.setID(rs.getInt("ID"));
                //DenumireLiceu = rs.getString("Nume");
                Liceu.getLiceuInfo(con);
                //Judet = rs.getString("Judet");
            }
            
            query = "Select * from Calificative,Competente where "
                    + "Calificative.Competenta_ID = Competente.ID AND Candidat_ID ="+ID;
            rs = st.executeQuery(query);
            while(rs.next()){
                switch(rs.getString("Denumire")){
                    case "Limba Romana" :        CLbRomana.setID(rs.getInt(1));
                                                 CLbRomana.setDenumire(rs.getString("Denumire"));
                                                 CLbRomana.setCalificativ(rs.getString("Calificativ"));
                                                 break;
                    case "Engleza"             : CLbModerna.setID(rs.getInt(1));
                                                 CLbModerna.setDenumire(rs.getString("Denumire"));
                                                 CLbModerna.setCalificativ(rs.getString("Calificativ"));
                                                 break;
                    case "Franceza"            : CLbModerna.setID(rs.getInt(1));
                                                 CLbModerna.setDenumire(rs.getString("Denumire"));
                                                 CLbModerna.setCalificativ(rs.getString("Calificativ"));
                                                 break;
                    case "Competenta Digitala" : CInformatica.setID(rs.getInt(1));
                                                 CInformatica.setDenumire(rs.getString("Denumire"));
                                                 CInformatica.setCalificativ(rs.getString("Calificativ"));
                                                 break;
                }
            }
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
