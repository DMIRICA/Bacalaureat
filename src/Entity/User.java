/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ynad
 */
public class User {
    
    private int ID;
    private String Username;
    private String Password;
    private String Type;
    private String FirstName;
    private String LastName;
    private String Function;
    private String Phone;
    private boolean Active;
    private int ProfileID;
    private int TypeID;
    public User(){
        
    }
    
    public User(int ID,String Username,String Password,String Type,String FirstName,String LastName,String Function,String Phone,boolean Active,int ProfileID,int TypeID){
        this.ID = ID;
        this.Username = Username;
        this.Password = Password;
        this.Type = Type;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Function = Function;
        this.Phone = Phone;
        this.Active = Active;
        this.ProfileID = ProfileID;
        this.TypeID = TypeID;
    }

    public void setProfileID(int ProfileID) {
        this.ProfileID = ProfileID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public int getProfileID() {
        return ProfileID;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getType() {
        return Type;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFunction() {
        return Function;
    }

    public String getPhone() {
        return Phone;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setFunction(String Function) {
        this.Function = Function;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
    
    public void getUserInfo(Connection conn){
        try{
        Statement st = conn.createStatement();
        
        //GET Username&Password OF USER;
        
        String query = "Select * from users where ID ="+ID;
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            Username = rs.getString("Username");
            Password = rs.getString("Password");
            Active = rs.getBoolean("Active");
            ProfileID = rs.getInt("UserProfile_ID");
            TypeID = rs.getInt("UserType_ID");
        }
        

        //GET TYPE OF USER;
        
        query = "select tip from usertypes where id = (select UserType_ID from Users where ID ="+ID+")";
        rs = st.executeQuery(query);
        if(rs.next()){
            Type = rs.getString(1);
        }


        //GET Personal Info about user;
        
        
        query = "select * from userProfiles  where id = "+ProfileID;
        rs = st.executeQuery(query);
         if(rs.next()){
            FirstName = rs.getString("Nume");
            LastName = rs.getString("Prenume");
            if(rs.getString("Functie") != null){
               Function = rs.getString("Functie");
            }
            if(rs.getString("Telefon") != null){
                Phone = rs.getString("Telefon");
            }
        }
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static User getUserFromList(String Username,Collection<User> col){
        for(User aux:col){
            if(aux.Username.equals(Username)){
                return aux;   
            }
        }
        return null;
    }
}