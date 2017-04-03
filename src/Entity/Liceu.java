package Entity;

import Singleton.Singleton;
import Utility.Tuple;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ynad
 */
public class Liceu {
    
    private int ID;
    private String Nume;
    private String Adresa;
    private String Judet;
    private String Telefon;
    private String Web_page;
    private final ArrayList<Tuple> Specializari;
    
    public Liceu(){
        Specializari = new ArrayList<>();
    }
    
    

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNume(String Nume) {
        this.Nume = Nume;
    }

    public void setAdresa(String Adresa) {
        this.Adresa = Adresa;
    }

    public void setJudet(String Judet) {
        this.Judet = Judet;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public void setWeb_page(String Web_page) {
        this.Web_page = Web_page;
    }

    public int getID() {
        return ID;
    }

    public String getNume() {
        return Nume;
    }

    public String getAdresa() {
        return Adresa;
    }

    public String getJudet() {
        return Judet;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getWeb_page() {
        return Web_page;
    }

    public ArrayList<Tuple> getSpecializari() {
        return Specializari;
    }

    public void getLiceuInfo(Connection con) {
        try{
            Statement st = con.createStatement();
            String query = "Select * from Licee where ID ="+ID;
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                this.Nume = rs.getString("Nume");
                this.Adresa = rs.getString("Adresa");
                this.Judet = rs.getString("Judet");
                this.Telefon = rs.getString("Telefon");
                this.Web_page = rs.getString("Web_page");
            }
            
            query = "Select * from Specializari,Specializari_Licee where Specializare_ID = Specializari.ID "
                    + "and Liceu_ID="+ID;
            rs = st.executeQuery(query);
            while(rs.next()){
                Specializari.add(new Tuple(rs.getString("Denumire"), rs.getInt("Specializare_ID")));
            }
            
        }catch(SQLException e){
            Logger.getLogger(Liceu.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static Liceu getLiceuByID(int ID){
        for(Liceu aux:Singleton.getInstance().Licee){
            if(aux.ID == ID){
                return aux;
            }
        }
        return null;
    }
    
    public Tuple getSpecializareByID(int ID){
        for(Tuple aux:Specializari){
            if(aux.getID() == ID){
               return aux; 
            }
        }
        return null;
    }
    
    public static ArrayList<Liceu> getLiceeJudet(String Judet){
        ArrayList<Liceu> lista = new ArrayList<>();
        Singleton.getInstance().Licee.stream().filter((x) -> (x.Judet.equals(Judet))).forEach((x) -> {
            lista.add(x);
        });
        return lista;
    }
    
    @Override
    public String toString() {
        return Nume;
    }
}
