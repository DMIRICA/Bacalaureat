package Singleton;


import Entity.Candidat;
import Entity.Liceu;
import Entity.User;
import SQLConnection.DBConnection;
import Views.AddCandidate;
import Views.LoginView;
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
public final class Singleton {
    
    private static Singleton singleton = null;
    public User UserLogged;
    public ArrayList<User> AllUsers;
    public ArrayList<String> UserTypes;
    public ArrayList<Boolean> Active;
    public ArrayList<String> Counties;
    public ArrayList<Candidat> Candidati;
    public ArrayList<Liceu> Licee;
    public ArrayList<String> LimbiModerna;
    public ArrayList<String> Calficative;
    
    
    protected Singleton() {
        init();
    }
    
    public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
 
    public  void init(){
        UserLogged = new User();
        AllUsers = new ArrayList<>();
        UserTypes = new ArrayList<>();
        Counties = new ArrayList<>();
        Candidati = new ArrayList<>();
        Licee = new ArrayList<>();
        LimbiModerna = new ArrayList<>();
        Calficative = new ArrayList<>();
        
        LoadUsers();
        populateCounties();
        IncarcaRezultate();
        IncarcaLicee();
        IncarcaLimbiModerne();
        IncarcaCalificative();
    }
    
    public void LoadUsers(){
        AllUsers.clear();
         try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM USERS";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                User aux = new User();
                aux.setID(rs.getInt("ID"));
                aux.getUserInfo(con);
                AllUsers.add(aux);
            }
            
            query = "SELECT * FROM USERTYPES";
            rs = st.executeQuery(query);
            while(rs.next()){
                UserTypes.add(rs.getString("TIP"));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        Active = new ArrayList();
        Active.add(Boolean.TRUE);
        Active.add(Boolean.FALSE);
    }
    
    public void refreshUserList(){
        AllUsers.clear();
         try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM USERS";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                User aux = new User();
                aux.setID(rs.getInt("ID"));
                aux.getUserInfo(con);
                AllUsers.add(aux);
            }          
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void populateCounties(){
        Counties.add("ALBA");
        Counties.add("ARAD");
        Counties.add("ARGES");
        Counties.add("BACAU");
        Counties.add("BIHOR");
        Counties.add("BISTRITA-NASAUD");
        Counties.add("BOTOSANI");
        Counties.add("BRASOV");
        Counties.add("BRAILA");
        Counties.add("BUCURESTI");
        Counties.add("BUZAU");
        Counties.add("CARAS-SEVERIN");
        Counties.add("CLUJ");
        Counties.add("CONSTANTA");
        Counties.add("COVASNA");
        Counties.add("CALARASI");
        Counties.add("DOLJ");
        Counties.add("DAMBOVITA");
        Counties.add("GALATI");
        Counties.add("GIURGIU");
        Counties.add("GORJ");
        Counties.add("HARGHITA");
        Counties.add("HUNEDOARA");
        Counties.add("IALOMITA");
        Counties.add("IASI");
        Counties.add("ILFOV");
        Counties.add("MARAMURES");
        Counties.add("MEHEDINTI");
        Counties.add("MURES");
        Counties.add("NEAMT");
        Counties.add("OLT");
        Counties.add("PRAHOVA");
        Counties.add("SATU MARE");
        Counties.add("SIBIU");
        Counties.add("SUCEAVA");
        Counties.add("SALAJ");
        Counties.add("TELEORMAN");
        Counties.add("TIMIS");
        Counties.add("TULCEA");
        Counties.add("VASLUI");
        Counties.add("VRANCEA");
        Counties.add("VALCEA");
    }
    
    public void IncarcaRezultate(){
        Candidati.clear();
         try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM Candidati order by Nume,Prenume";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Candidat aux = new Candidat();
                aux.setID(rs.getInt("ID"));
                aux.getCandidatInfo(con);
                Candidati.add(aux);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetUser(){
        UserLogged = new User();
    }
    
    public void IncarcaLicee(){
        Licee.clear();
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM Licee order by Nume";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Liceu aux = new Liceu();
                aux.setID(rs.getInt("ID"));
                aux.getLiceuInfo(con);
                Licee.add(aux);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void IncarcaLimbiModerne(){
        LimbiModerna.add("Engleza");
        LimbiModerna.add("Franceza");
    }
    
    public void IncarcaCalificative(){
        Calficative.add("Utilizator experimentat");
        Calficative.add("Utilizator avansat");
        Calficative.add("Utilizator mediu");
        Calficative.add("Utilizator începator");
    }
    
    public int getLiceuID(int LiceuID,int SpecializareID){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String query = "Select ID from Specializari_Licee where Liceu_ID="+LiceuID+" AND Specializare_ID="+SpecializareID;
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return rs.getInt("ID");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCandidate.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return 0;
    }

    
}